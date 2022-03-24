package com.company.olx.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictDTO {
    private Integer id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    private CityDTO cityDTO;
}
