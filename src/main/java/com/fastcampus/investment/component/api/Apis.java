package com.fastcampus.investment.component.api;

import com.fastcampus.investment.component.dto.*;
import com.fastcampus.investment.component.dto.request.InvestPostRequestDTO;
import com.fastcampus.investment.component.dto.request.InvestPutRequestDTO;
import com.fastcampus.investment.component.dto.response.InvestPutResponseDTO;
import com.fastcampus.investment.component.dto.response.ResponseDTO;
import com.fastcampus.investment.component.service.InvestingService;
import com.fastcampus.investment.component.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class Apis {

    private final ProductService productService;
    private final InvestingService investingService;

    @GetMapping("/product")
    public Object productGet(){ return productService.getValidProducts();}

    @PostMapping("/investment")
    public Object investmentPost
            (
            @RequestHeader("X-USER-ID") @NotNull Long userId,
            @RequestParam @NotNull Long productId,
            @RequestParam @Min(1L) Long investAmount
            )
    {
        InvestPostRequestDTO investPostRequestDTO = InvestPostRequestDTO.builder()
                .userId(userId)
                .productId(productId)
                .investAmount(investAmount)
                .build();

        return investingService.investPost(investPostRequestDTO);
    }

    @GetMapping("/investment")
    public Object investmentGet
            (
            @RequestHeader("X-USER-ID") @NotNull Long userId
            )
    {
        return investingService.getInvest(userId);
    }

    @PutMapping("/investment/{productId}")
    public Object investmentPost
        (
        @RequestHeader("X-USER-ID") @NotNull Long userId,
        @PathVariable @NotNull Long productId,
        @RequestParam @NotBlank String status
        )
    {
        InvestPutRequestDTO investPutRequestDTO = InvestPutRequestDTO.builder()
                .userId(userId)
                .productId(productId)
                .status(status)
                .build();
        return investingService.updateInvest(investPutRequestDTO);
    }
}
