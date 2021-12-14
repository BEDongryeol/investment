package com.fastcampus.investment.component.service;

import com.fastcampus.investment.component.dto.request.InvestPostRequestDTO;
import com.fastcampus.investment.component.entity.UserEntity;
import com.fastcampus.investment.component.repository.ProductsRepository;
import com.fastcampus.investment.component.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class InvestingServiceTest {
    @Autowired
    InvestingService investingService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;

    @DisplayName("Transaction Test")
    @Test
    @Transactional
    void test_3(){
        UserEntity user1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        InvestPostRequestDTO requestDTO = getTestModel();

        investingService.investPost(requestDTO);

        userRepository.findAll().get(0).getInvestingStatus().forEach(o-> System.out.println(o.getProducts()));

    }

    private InvestPostRequestDTO getTestModel(){
        InvestPostRequestDTO requestDTO = new InvestPostRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setProductId(1L);
        requestDTO.setInvestAmount(1_000_000L);
        return requestDTO;
    }


}