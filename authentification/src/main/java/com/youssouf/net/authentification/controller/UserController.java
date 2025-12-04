package com.youssouf.net.authentification.controller;


import com.youssouf.net.authentification.dto.LoginRequest;
import com.youssouf.net.authentification.dto.LoginResponse;
import com.youssouf.net.authentification.dto.RegisterRequest;
import com.youssouf.net.authentification.dto.RegisterResponse;
import com.youssouf.net.authentification.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
