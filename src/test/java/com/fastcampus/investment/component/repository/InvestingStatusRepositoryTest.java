package com.fastcampus.investment.component.repository;

import com.fastcampus.investment.component.entity.InvestingStatusEntity;
import com.fastcampus.investment.component.entity.ProductsEntity;
import com.fastcampus.investment.component.entity.UserEntity;
import com.fastcampus.investment.component.service.InvestingService;
import com.fastcampus.investment.exception.IllegalUserException;
import com.fastcampus.investment.util.type.UserInvestingType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
class InvestingStatusRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private InvestingStatusRepository investingStatusRepository;
    @Autowired
    private InvestingService investingService;

    @DisplayName("findByUserIdAndProductsId() 테스트")
    @Test
    @Transactional
    void test_1(){

        UserEntity user = userRepository.findById(1L).orElseThrow(IllegalUserException::new);
        ProductsEntity products = productsRepository.findById(1L).orElseThrow(RuntimeException::new);

        InvestingStatusEntity investingStatus = new InvestingStatusEntity();
        investingStatus.setUser(user);
        investingStatus.setProducts(products);
        investingStatusRepository.save(investingStatus);

        InvestingStatusEntity entity = investingStatusRepository.findByUserIdAndProductsIdOrderByIdDesc(1L, 1L);
        UserInvestingType type = UserInvestingType.valueOf("CANCELED");
        entity.getUser().setStatus(type);
        System.out.println("1==========================");
        System.out.println(entity);
        System.out.println("2==========================");
        investingService.addInvestingStatus(user, products);
        System.out.println("3==========================");
        userRepository.getById(1L).getInvestingStatus().forEach(System.out::println);
        System.out.println("4==========================");
        productsRepository.getById(1L).getInvestingStatus().forEach(System.out::println);

    }


}