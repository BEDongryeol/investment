package com.fastcampus.investment.component.dto.response;

import com.fastcampus.investment.util.type.UserInvestingType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvestPutResponseDTO {

    private final UserInvestingType status;

}
