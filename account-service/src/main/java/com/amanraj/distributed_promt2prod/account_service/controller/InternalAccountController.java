package com.amanraj.distributed_promt2prod.account_service.controller;


import com.amanraj.distributed_promt2prod.account_service.mapper.UserMapper;
import com.amanraj.distributed_promt2prod.account_service.repository.UserRepository;
import com.amanraj.distributed_promt2prod.account_service.service.SubscriptionService;
import com.amanraj.distributed_promt2prod.common_lib.dto.PlanDto;
import com.amanraj.distributed_promt2prod.common_lib.dto.UserDto;
import com.amanraj.distributed_promt2prod.common_lib.error.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/internal/v1")
@RequiredArgsConstructor
public class InternalAccountController {

    // Created some internal api that other microservices can call directly
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SubscriptionService subscriptionService;

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", id.toString()));
    }

    @GetMapping("/users/by-email")
    public Optional<UserDto> getUserByEmail(@RequestParam String email) {
        return userRepository.findByUsernameIgnoreCase(email)
                .map(userMapper::toUserDto);
    }

    @GetMapping("/billing/current-plan")
    public PlanDto getCurrentSubscribedPlan() {
        return subscriptionService.getCurrentSubscribedPlanByUser();
    }

}
