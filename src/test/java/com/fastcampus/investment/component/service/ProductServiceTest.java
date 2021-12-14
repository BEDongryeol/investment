package com.fastcampus.investment.component.service;

import com.fastcampus.investment.component.dto.ProductsDTO;
import com.fastcampus.investment.component.dto.response.ResponseDTO;
import com.fastcampus.investment.component.entity.ProductsEntity;
import com.fastcampus.investment.component.repository.ProductsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductService productService;

    @DisplayName("투자 가능 상품 조회")
    @Test
    @Transactional
    void test_1(){

        // 총 모집 금액과 현재 모집 금액이 같은 상품
        ProductsEntity products = ProductsEntity.builder()
                .id(5L)
                .title("테스트 상품")
                .startedAt(LocalDate.now().minusDays(1L))
                .finishedAt(LocalDate.now().plusDays(2L))
                .investedCount(1)
                .totalInvestAmount(1_000_000L)
                .investedAmount(1_000_000L)
                .build();

        productsRepository.save(products);
        productsRepository.findAll().forEach(System.out::println);
        System.out.println("====================");

        ResponseDTO<List<ProductsDTO>> responseDTO = productService.getValidProducts();
        System.out.println(responseDTO);
        System.out.println(responseDTO.getData());

    }



}