package com.company.olx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDTO {
    private Integer id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Surname can not be empty")
    private String surname;
    @NotEmpty(message = "Phone can not be empty")
    private String phone;
    @Email(message = "Email is not validate")
    private String email;
    @Size(min = 4, max = 8, message = "size of password 4<size<8")
    @Positive
    private String password;
    private LocalDateTime createdDate;
}
