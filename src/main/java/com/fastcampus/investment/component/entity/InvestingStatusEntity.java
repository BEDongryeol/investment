package com.fastcampus.investment.component.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Investing_status")
public class InvestingStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @JsonBackReference
    private ProductsEntity products;

    @Column(updatable = false)
    private LocalDate investedAt = LocalDate.now();

}
