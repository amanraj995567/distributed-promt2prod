package com.amanraj.distributed_promt2prod.workspace_service;

import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableFeignClinets
public class WorkspaceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkspaceServiceApplication.class, args);
	}

}
