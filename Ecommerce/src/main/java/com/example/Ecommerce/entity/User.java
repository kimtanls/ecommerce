package com.example.Ecommerce.entity;

import com.example.Ecommerce.DTO.UserDTO;
import com.example.Ecommerce.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;


    private String password;

    private String fullName;

    private String phoneNumber;

    private String address;

    private UserRole userRole;

    private Date dateOfBirth;

    private Date createAt;

    private Date updateAt;

    public UserDTO getUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setFullName(fullName);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setAddress(address);
        userDTO.setUserRole(userRole);
        userDTO.setDateOfBirth(dateOfBirth);
        userDTO.setCreateAt(createAt);
        userDTO.setUpdateAt(updateAt);
        return userDTO;
    }
}
