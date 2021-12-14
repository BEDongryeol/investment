package com.fastcampus.investment.component.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvestPutRequestDTO {

    private Long userId;

    private Long productId;

    private String status;

}
