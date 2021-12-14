package com.fastcampus.investment.component.service;

import com.fastcampus.investment.component.dto.request.InvestPostRequestDTO;
import com.fastcampus.investment.component.dto.request.InvestPutRequestDTO;
import com.fastcampus.investment.component.dto.InvestingStatusDTO;
import com.fastcampus.investment.component.dto.response.InvestPutResponseDTO;
import com.fastcampus.investment.component.dto.response.ResponseDTO;
import com.fastcampus.investment.component.entity.InvestingStatusEntity;
import com.fastcampus.investment.component.entity.ProductsEntity;
import com.fastcampus.investment.component.entity.UserEntity;
import com.fastcampus.investment.component.repository.InvestingStatusRepository;
import com.fastcampus.investment.component.repository.UserRepository;
import com.fastcampus.investment.util.mapper.InvestingStatusMapper;
import com.fastcampus.investment.util.type.UserInvestingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvestingService {

    private final UserService userService;
    private final ProductService productService;
    private final InvestingStatusRepository investingStatusRepository;
    private final UserRepository userRepository;


    @Transactional
    public ResponseDTO<InvestPutResponseDTO> investPost(InvestPostRequestDTO investPostRequestDTO){

        UserEntity user = userService.payForProduct(investPostRequestDTO);
        ProductsEntity products ;
        UserInvestingType status = user.getStatus();
        // Status 가 FAIL 이면 투자 실패
        if (status == UserInvestingType.FAIL){
            products = productService.selectFail(investPostRequestDTO);
        // 투자 성공 시 투자 상품에 반영
        } else {
            products = productService.selectProduct(investPostRequestDTO);
        }

        addInvestingStatus(user, products);
        log.info("투자 시도 : {}", status);
        return new ResponseDTO<>(new InvestPutResponseDTO(status));
    }

    @Transactional
    public ResponseDTO<List<InvestingStatusDTO>> getInvest(Long userId){
        UserEntity user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);

        return new ResponseDTO<>(InvestingStatusMapper.INSTANCE.toDtoList(user.getInvestingStatus()));
    }

    // Service Layer - Entity 리턴 -> Repository Layer -> Service Layer - DTO 변환
    @Transactional
    public ResponseDTO<InvestPutResponseDTO> updateInvest(InvestPutRequestDTO investPutRequestDTO){

        // 1. DTO에서 데이터 받아와서 처리
        InvestingStatusEntity entity = investingStatusRepository
                .findByUserIdAndProductsId(investPutRequestDTO.getUserId(), investPutRequestDTO.getProductId());

        entity.getUser().setStatus(UserInvestingType.valueOf(investPutRequestDTO.getStatus()));
        investingStatusRepository.save(entity);

        return new ResponseDTO<>(new InvestPutResponseDTO(UserInvestingType.valueOf(investPutRequestDTO.getStatus())));
    }

    public InvestingStatusDTO addInvestingStatus(UserEntity user, ProductsEntity products){

        InvestingStatusEntity investingStatus = new InvestingStatusEntity();
        investingStatus.setUser(user);
        investingStatus.setProducts(products);
        investingStatusRepository.save(investingStatus);

        return InvestingStatusMapper.INSTANCE.toDto(investingStatus);
    }

}
