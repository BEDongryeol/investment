package com.fastcampus.investment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NO_VALID_PRODUCTS("모집 기간 내 투자 상품이 존재하지 않습니다."),
    NO_INVESTED_DATA("투자 내역이 존재하지 않습니다."),
    INVALID_USER("유효하지 않은 사용자입니다.");

    private final String message;

}
