package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(length = 125)
    private String moodysRating;

    @Column(length = 125)
    private String sandPRating;

    @Column(length = 125)
    private String fitchRating;

    private Byte orderNumber;
}
