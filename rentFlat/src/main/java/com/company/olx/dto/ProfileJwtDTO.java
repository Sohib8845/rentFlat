package com.company.olx.dto;

import com.company.olx.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileJwtDTO {
    private Integer id;
    private ProfileRole role;
}
