package com.company.olx.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "district")
@Getter
@Setter
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name_uz;
    @Column
    private String name_ru;
    @Column
    private String name_en;

    //TODO nega optional false ?
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "city_id")
    private CityEntity city;
}
