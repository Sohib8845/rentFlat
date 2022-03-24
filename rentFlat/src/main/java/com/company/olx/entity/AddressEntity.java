package com.company.olx.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "address")
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String street;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private DistrictEntity district;
}
