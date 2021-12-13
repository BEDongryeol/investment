package com.fastcampus.investment.component.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Long totalInvestAmount;

    @Column
    private int investedCount;

    @Column
    private Long investedAmount;

    @Column(updatable = false)
    private LocalDate startedAt;

    @Column
    private LocalDate finishedAt;

    @OneToMany
    @JoinColumn(name = "products_id")
    @Builder.Default
    @ToString.Exclude
    @JsonManagedReference
    private List<InvestingStatusEntity> investingStatus = new ArrayList<>();

    public void addInvestingStatus(InvestingStatusEntity... investingStatuses){
        Collections.addAll(this.investingStatus, investingStatuses);
    }

}
