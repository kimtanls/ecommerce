package com.example.Ecommerce.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Long id;

    private String name;

    private String description;

    private Date createdAt;

    private Date updatedAt;
}
