package com.company.olx.entity;

import com.company.olx.enums.ProfileStatus;
import com.company.olx.enums.ProfileRole;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ProfileRole role;


    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

}
