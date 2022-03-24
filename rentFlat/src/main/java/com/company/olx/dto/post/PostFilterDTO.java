package com.company.olx.dto.post;

import com.company.olx.enums.PostStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class
PostFilterDTO {
    private Integer rooms;
    private Double floor;
    private Double price;
    private Double area;
    private Integer address_id;
    private Integer profile_id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private PostStatus status;
}
