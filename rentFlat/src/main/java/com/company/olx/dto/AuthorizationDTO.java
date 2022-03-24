package com.company.olx.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter

public class AuthorizationDTO {
    @Email(message = "Incorrect email")
    private String email;
    @Positive
    @NotEmpty(message = "Password can not be null")
    private String password;
}
