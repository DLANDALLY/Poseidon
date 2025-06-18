package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bidListId;

    @NotNull(message = "Account is mandatory")
    @Column(length = 30, nullable = false)
    private String account;

    @NotNull(message = "Type is mandatory")
    @Column(length = 30, nullable = false)
    private String type;

    private double bidQuantity;
    private double askQuantity;
    private double bid;
    private double ask;

    @Column(length = 125)
    private String benchmark;

    private LocalDateTime bidListDate;

    @Column(length = 125)
    private String commentary;

    @Column(length = 125)
    private String security;

    @Column(length = 10)
    private String status;

    @Column(length = 125)
    private String trader;

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
