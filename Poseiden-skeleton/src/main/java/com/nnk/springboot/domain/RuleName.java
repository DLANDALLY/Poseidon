package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(length = 125)
    private String name;

    @Column(length = 125)
    private String description;

    @Column(length = 125)
    private String json;

    @Column(length = 512)
    private String template;

    @Column(length = 125)
    private String sqlStr;

    @Column(length = 125)
    private String sqlPart;

}
