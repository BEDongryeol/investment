package com.fastcampus.investment.component.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvestPostRequestDTO {

    private Long userId;

    private Long productId;

    private Long investAmount;

}
