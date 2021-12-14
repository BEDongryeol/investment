package com.fastcampus.investment.component.service;

import com.fastcampus.investment.component.dto.request.InvestPostRequestDTO;
import com.fastcampus.investment.component.entity.UserEntity;
import com.fastcampus.investment.component.repository.UserRepository;
import com.fastcampus.investment.util.type.UserInvestingType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("buyProduct() Test")
    @Test
    void test_2(){
        userRepository.save(UserEntity.builder()
                        .id(1L)
                        .holdingAmount(1_000_000L)
                        .investedAmount(0L)
                        .status(UserInvestingType.NONE)
                        .investedCount(0)
                .build());

        InvestPostRequestDTO requestDTO = getTestModel();

        UserEntity user = userService.payForProduct(requestDTO);
        System.out.println(user);

        requestDTO.setInvestAmount(2_000_000L);

        user = userService.payForProduct(requestDTO);
        System.out.println(user);
    }

    private InvestPostRequestDTO getTestModel(){
        InvestPostRequestDTO requestDTO = new InvestPostRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setInvestAmount(1_000_000L);
        return requestDTO;
    }

}