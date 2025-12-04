package com.youssouf.net.authentification.service;

import com.youssouf.net.authentification.dto.LoginRequest;
import com.youssouf.net.authentification.dto.LoginResponse;
import com.youssouf.net.authentification.dto.RegisterRequest;
import com.youssouf.net.authentification.dto.RegisterResponse;
import com.youssouf.net.authentification.entity.UserInfo;
import com.youssouf.net.authentification.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.youssouf.net.authentification.exception.AuthException;


@Service
public class AuthService {

    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AuthException("User with email " + request.getEmail() + " already exists", HttpStatus.CONFLICT);
        }

        UserInfo user = new UserInfo();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setEnabled(true);
        userRepository.save(user);

        // String token = jwtService.generateToken(user);

        return RegisterResponse.builder()
                .message("User registered successfully")
                .build();
    }

    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new AuthException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }

        UserInfo user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("User not found", HttpStatus.NOT_FOUND));

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .accessToken(token)
                .username(user.getEmail())
                .roles(user.getRole().name())
                .id(user.getId())
                .build();
    }

}
