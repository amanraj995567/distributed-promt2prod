package com.amanraj.distributed_promt2prod.api_gateway;

import com.amanraj.distributed_promt2prod.api_gateway.config.SecuritiesProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GatewayJwtAuthFilter implements GlobalFilter, Ordered {

	private final SecuritiesProperty securitiesProperty;
	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	public GatewayJwtAuthFilter(SecuritiesProperty securitiesProperty) {
		this.securitiesProperty = securitiesProperty;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getURI().getPath();

		// Skip auth for public routes
		if (isPublicRoute(path)) {
			return chain.filter(exchange);
		}

		String authHeader = request.getHeaders().getFirst("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return onError(exchange, HttpStatus.UNAUTHORIZED);
		}

		String token = authHeader.substring(7);

		if (!isValid(token)) {
			return onError(exchange, HttpStatus.UNAUTHORIZED);
		}

		return chain.filter(exchange);
	}

	private boolean isPublicRoute(String path) {
		List<String> publicRoutes = securitiesProperty.getPublicRoutes();
		if (publicRoutes == null) {
			return false;
		}
		return publicRoutes.stream()
				.anyMatch(route -> pathMatcher.match(route, path));
	}

	private boolean isValid(String token) {
		// TODO: real JWT validation (e.g. with jjwt or nimbus)
		return token != null && !token.isBlank();
	}

	private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
		exchange.getResponse().setStatusCode(status);
		return exchange.getResponse().setComplete();
	}

	@Override
	public int getOrder() {
		return -1;
	}
}