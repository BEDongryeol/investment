package com.fastcampus.investment.component.service;

import com.fastcampus.investment.component.dto.request.InvestPostRequestDTO;
import com.fastcampus.investment.component.dto.ProductsDTO;
import com.fastcampus.investment.component.dto.response.ResponseDTO;
import com.fastcampus.investment.component.entity.ProductsEntity;
import com.fastcampus.investment.component.repository.ProductsRepository;
import com.fastcampus.investment.util.mapper.ProductsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;

    // Service Layer -> Web Layer (DTO 리턴)
    @Transactional
    public ResponseDTO<List<ProductsDTO>> getValidProducts(){

        // 유효한 모집기간 상품 조회
        List<ProductsEntity> products = productsRepository.getValidProducts();
        // Mapping 후 return
        return new ResponseDTO<>(
            products.stream()
                    .map(ProductsMapper.INSTANCE::toDto)
                    .collect(Collectors.toList())
        );
    }

    // Service Layer -> Service Layer (Entity 리턴)
    @Transactional
    public ProductsEntity selectProduct(InvestPostRequestDTO investPostRequestDTO){

        Long productsId = investPostRequestDTO.getProductId();

        // 1. 유효한 상품 조회
        List<ProductsEntity> products = productsRepository.getValidProducts();

        ProductsEntity productsEntity =
                productsRepository.getById(productsId);

        if (products.contains(productsEntity)) {
            productsEntity.setInvestedAmount(productsEntity.getInvestedAmount()+ investPostRequestDTO.getInvestAmount());
            productsEntity.setInvestedCount(productsEntity.getInvestedCount()+1);
            return productsEntity;
        }
        return null;
    }

    // Service Layer -> Service Layer (Entity 리턴)
    public ProductsEntity selectFail(InvestPostRequestDTO investPostRequestDTO){
        Long productId = investPostRequestDTO.getProductId();
        return productsRepository.getById(productId);
    }

}
