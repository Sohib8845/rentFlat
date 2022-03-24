package com.company.olx.entity;

import com.company.olx.enums.EmailStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_history")
@Getter
@Setter
public class EmailHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String fromAccount;
    @Column
    private String toAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmailStatus status;
    @Column
    private LocalDateTime usedAt;
}
