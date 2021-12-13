package com.fastcampus.investment.component.dto;

import lombok.Getter;

@Getter
public class ResponseDTO<T> {

    private T data;

    public ResponseDTO(T data){
        this.data = data;
    }

}
