package com.example.Ecommerce.DTO;

import com.example.Ecommerce.enums.UserRole;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
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
}
