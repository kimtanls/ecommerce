package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTO.UserDTO;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{userId}")
    ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) throws Exception {
        UserDTO updateUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping
    ResponseEntity<List<UserDTO>> getAllUsers() throws Exception {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUser(@PathVariable Long userId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }
}
