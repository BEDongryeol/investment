package com.fastcampus.investment.util.mapper.entity;

import com.fastcampus.investment.component.dto.InvestingStatusDTO;
import com.fastcampus.investment.component.dto.response.ResponseDTO;
import com.fastcampus.investment.component.entity.InvestingStatusEntity;
import com.fastcampus.investment.component.entity.ProductsEntity;
import com.fastcampus.investment.component.entity.UserEntity;
import com.fastcampus.investment.component.repository.InvestingStatusRepository;
import com.fastcampus.investment.component.repository.ProductsRepository;
import com.fastcampus.investment.component.repository.UserRepository;
import com.fastcampus.investment.util.mapper.InvestingStatusMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class InvestingStatusMapperTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private InvestingStatusRepository investingStatusRepository;

    @DisplayName("MapperTest")
    @Test
    @Transactional
    void test_2(){
        UserEntity user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        ProductsEntity products = productsRepository.findById(1L).orElseThrow(RuntimeException::new);

        InvestingStatusEntity entity = new InvestingStatusEntity();
        entity.setUser(user);
        entity.setProducts(products);
        investingStatusRepository.save(entity);

        System.out.println(investingStatusRepository.findById(1L));

        System.out.println("=============================================");

        List<InvestingStatusDTO> list = InvestingStatusMapper.INSTANCE.toDtoList(userRepository.getById(1L).getInvestingStatus());
        ResponseDTO<List<InvestingStatusDTO>> res = new ResponseDTO<>(list);

        System.out.println(res.getData().get(0).getStatus());

//        InvestingStatusMapper.INSTANCE.toDtoList()

    }

}