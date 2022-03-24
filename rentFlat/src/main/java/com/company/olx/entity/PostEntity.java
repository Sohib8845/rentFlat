package com.company.olx.entity;

import com.company.olx.enums.PostStatus;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post")
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer rooms;
    @Column
    private Double floor;
    @Column
    private Double price;
    @Column
    private Double area;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @Column
    private PostStatus status;
    @Column
    private LocalDateTime createdDate = LocalDateTime.now();
}
