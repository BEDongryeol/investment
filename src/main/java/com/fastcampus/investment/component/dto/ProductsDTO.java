package com.fastcampus.investment.component.dto;

import com.fastcampus.investment.component.entity.InvestingStatusEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductsDTO {

    private Long id;

    private String title;

    private Long totalInvestAmount;

    private int investedCount;

    private Long investedAmount;

    private LocalDate startedAt;

    private LocalDate finishedAt;
    @ToString.Exclude
    @Builder.Default
    private List<InvestingStatusDTO> investingStatus = new ArrayList<>();


}
