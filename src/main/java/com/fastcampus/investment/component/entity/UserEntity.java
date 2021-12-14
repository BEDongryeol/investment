package com.fastcampus.investment.component.entity;

import com.fastcampus.investment.util.type.UserInvestingType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Builder.Default
    private Long investedAmount = 0L;

    @Column
    private Long holdingAmount;

    @Column
    @Builder.Default
    private int investedCount = 0;

    @Column
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserInvestingType status = UserInvestingType.NONE;

    @Column
    private LocalDate investedAt;

    @OneToMany
    @JoinColumn(name = "user_id")
    @Builder.Default
    @ToString.Exclude
    private List<InvestingStatusEntity> investingStatus = new ArrayList<>();

    public void addInvestingStatus(InvestingStatusEntity... investingStatuses){
        Collections.addAll(this.investingStatus, investingStatuses);
    }
}
