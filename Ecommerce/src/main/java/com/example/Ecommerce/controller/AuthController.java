package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTO.LoginRequest;
import com.example.Ecommerce.DTO.LoginResponse;
import com.example.Ecommerce.DTO.UserDTO;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.JWTService;
import com.example.Ecommerce.service.MyUserDetailsService;
import com.example.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    ResponseEntity<User> register(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String result = userService.verify(loginRequest);
        if (result.startsWith("Invalid")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        } else {
            return ResponseEntity.ok(new LoginResponse(result)); // Nếu token hợp lệ, trả về token
        }
    }

}
