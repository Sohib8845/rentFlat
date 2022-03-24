package com.company.olx.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictCustomDTO {
    private Integer id;
    @NotEmpty(message = "name_uz can not be empty")
    private String name_uz;
    @NotEmpty(message = "name_en can not be empty")
    private String name_en;
    @NotEmpty(message = "name_ru can not be empty")
    private String name_ru;
    @NotNull(message = "jora city id qani")
    private Integer city_id;

    private CityDTO cityDTO;
}
