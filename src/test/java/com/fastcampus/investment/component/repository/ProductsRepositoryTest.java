package com.fastcampus.investment.component.repository;

import com.fastcampus.investment.component.entity.ProductsEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProductsRepositoryTest {

    @Autowired
    private ProductsRepository productsRepository;

    @DisplayName("Repository 테스트")
    @Test
    void findByTitle() {
        ProductsEntity productsEntity = ProductsEntity.builder()
                .id(1L)
                .title("테스트 상품")
                .totalInvestAmount(1_000_000L)
                .startedAt(LocalDate.now().minusDays(1L))
                .finishedAt(LocalDate.now().plusDays(1L))
                .build();

        productsRepository.save(productsEntity);

        System.out.println(productsRepository.findById(1L).get());
    }

    @DisplayName("날짜가 유효한 상품 조회")
    @Test
    void test_2(){
        List<ProductsEntity> products = productsRepository.getValidProducts();
        products.forEach(System.out::println);

    }
}