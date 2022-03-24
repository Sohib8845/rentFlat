package com.company.olx.dto.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
    private Integer id;
    @NotNull(message = "district id qani ?")
    private Integer district_id;
    @NotBlank(message = "Street nomi qani !")
    private String street;

    private DistrictDTO districtDTO;
}
