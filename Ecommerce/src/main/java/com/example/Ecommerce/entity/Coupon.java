package com.example.Ecommerce.entity;

import com.example.Ecommerce.enums.CouponStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private int discountPercentage;


    private Date validFrom;


    private Date validUntil;


    private CouponStatus couponStatus = CouponStatus.ACTIVE;


    private Date createdAt;

    private Date updatedAt;
}
