package com.company.olx.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "city")
@Getter
@Setter
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name_uz;
    @Column
    private String name_ru;
    @Column
    private String name_en;

    @OneToMany(mappedBy = "city",cascade = CascadeType.REMOVE)
    private List<DistrictEntity> districtEntities;
}
