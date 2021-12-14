package com.fastcampus.investment.component.dto;

import com.fastcampus.investment.component.entity.InvestingStatusEntity;
import com.fastcampus.investment.constant.UserInvestingType;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {
    private Long id;

    @Builder.Default
    private Long investedAmount = 0L;

    private Long holdingAmount;

    @Builder.Default
    private int investedCount = 0;

    @Builder.Default
    private UserInvestingType status = UserInvestingType.NONE;

    private LocalDate investedAt;

    @Builder.Default
    @ToString.Exclude
    private List<InvestingStatusEntity> investingStatus = new ArrayList<>();
}
