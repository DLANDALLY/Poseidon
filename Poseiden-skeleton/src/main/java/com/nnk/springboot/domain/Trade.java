package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte tradeId;

    @Column(length = 30, nullable = false)
    private String account;

    @Column(length = 30, nullable = false)
    private String type;

    private Double buyQuantity;
    private Double sellQuantity;
    private Double buyPrice;
    private Double sellPrice;

    private LocalDateTime tradeDate;

    @Column(length = 125)
    private String security;

    @Column(length = 10)
    private String status;

    @Column(length = 125)
    private String trader;

    @Column(length = 125)
    private String benchmark;

    @Column(length = 125)
    private String book;

    @Column(length = 125)
    private String creationName;

    private LocalDateTime creationDate;

    @Column(length = 125)
    private String revisionName;

    private LocalDateTime revisionDate;

    @Column(length = 125)
    private String dealName;

    @Column(length = 125)
    private String dealType;

    @Column(length = 125)
    private String sourceListId;

    @Column(length = 125)
    private String side;
}
