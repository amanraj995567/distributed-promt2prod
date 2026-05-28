package com.amanraj.distributed_promt2prod.account_service.controller;

import com.amanraj.distributed_promt2prod.account_service.dto.auth.AuthResponse;
import com.amanraj.distributed_promt2prod.account_service.dto.auth.LoginRequest;
import com.amanraj.distributed_promt2prod.account_service.dto.auth.SignupRequest;
import com.amanraj.distributed_promt2prod.account_service.dto.auth.UserProfileResponse;
import com.amanraj.distributed_promt2prod.account_service.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {
     AuthService authService;
   //  UserService userService;



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authService.signup(signupRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
         return ResponseEntity.ok(authService.login(loginRequest));
    }

//    @GetMapping("me")
//    public  ResponseEntity<UserProfileResponse> getProfile(){
//        Long userId = 1L;
//        return ResponseEntity.ok(userService.getProfile(userId));
//    }


}
