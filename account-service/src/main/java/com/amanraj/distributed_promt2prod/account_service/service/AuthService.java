package com.amanraj.distributed_promt2prod.account_service.service;

import com.amanraj.distributed_promt2prod.account_service.dto.auth.AuthResponse;
import com.amanraj.distributed_promt2prod.account_service.dto.auth.LoginRequest;
import com.amanraj.distributed_promt2prod.account_service.dto.auth.SignupRequest;

public interface AuthService {
     AuthResponse signup(SignupRequest signupRequest);

     AuthResponse login(LoginRequest loginRequest);
}
