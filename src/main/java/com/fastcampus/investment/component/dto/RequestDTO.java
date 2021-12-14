package com.fastcampus.investment.component.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private Long userId;

    private Long productId;

    private Long investAmount;

    private String status;


}
