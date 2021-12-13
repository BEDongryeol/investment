package com.fastcampus.investment.component.service;

import com.fastcampus.investment.component.dto.ProductsDTO;
import com.fastcampus.investment.component.dto.ResponseDTO;
import com.fastcampus.investment.component.entity.ProductsEntity;
import com.fastcampus.investment.component.repository.ProductsRepository;
import com.fastcampus.investment.util.mapper.ProductsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResponseDTO<List<ProductsDTO>> lookupValidProducts(){

        // 1. 유효한 모집기간 상품 조회
        List<ProductsEntity> products = productsRepository.getValidProducts();
        // 2. 총 투자 금액이 total 과 같은지 비교하여 제거
        products.removeIf(product -> product.getTotalInvestAmount() == product.getInvestedAmount());

        List<ProductsDTO> productsDTOS = new ArrayList<>();

        for (ProductsEntity productsEntity : products){
            productsDTOS.add(ProductsMapper.INSTANCE.toDto(productsEntity));
        }

        return new ResponseDTO<>(productsDTOS);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ProductsEntity selectProduct(Long productsId, Long investAmount){

        // 1. 유효한 모집기간 상품 조회
        List<ProductsEntity> products = productsRepository.getValidProducts();
        // 2. 총 투자 금액이 total 과 같은지 비교하여 제거
        products.removeIf(product -> product.getTotalInvestAmount() == product.getInvestedAmount());

        ProductsEntity productsEntity = productsRepository.getById(productsId);

        if (products.contains(productsEntity)) {
            productsEntity.setInvestedAmount(productsEntity.getInvestedAmount()+investAmount);
            productsEntity.setInvestedCount(productsEntity.getInvestedCount()+1);

            return productsEntity;
        }

        return null;
    }

    public ProductsEntity selectFail(Long productsId){
        return productsRepository.getById(productsId);
    }


}
