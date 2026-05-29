package com.amanraj.distributed_promt2prod.workspace_service.client;

import com.amanraj.distributed_promt2prod.common_lib.dto.PlanDto;
import com.amanraj.distributed_promt2prod.common_lib.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
/// feign clinet will internally make a REST api call
@FeignClient(name="account-service", path="/account")
public interface AccountClient {
    @GetMapping("/internal/v1/users/by-email")
    Optional<UserDto> getUserByEmail(@RequestParam("email") String email);

    @GetMapping("/internal/v1/billing/current-plan")
    PlanDto getCurrentSubscribedPlanByUser();
}
