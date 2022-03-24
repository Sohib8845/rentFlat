package com.company.olx.dto.post;

import com.company.olx.dto.address.AddressDTO;
import com.company.olx.dto.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {
    private Integer id;
    private Integer rooms;
    private Double floor;
    private Double price;
    private Double area;
    private AddressDTO addressDTO;
    private Integer address_id;
    private ProfileDTO profileDTO;
    private Integer profile_id;
    private LocalDateTime createdDate;
}
