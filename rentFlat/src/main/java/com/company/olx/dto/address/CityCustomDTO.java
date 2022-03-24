package com.company.olx.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityCustomDTO {
    private Integer id;
    @NotBlank(message = "kalla name_uz qani")
    private String name_uz;
    @NotBlank(message = "kalla name_en qani")
    private String name_en;
    @NotBlank(message = "kalla name_ru qani")
    private String name_ru;
}
