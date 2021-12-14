package com.fastcampus.investment.component.service;

import com.fastcampus.investment.component.dto.request.InvestPostRequestDTO;
import com.fastcampus.investment.component.entity.UserEntity;
import com.fastcampus.investment.component.repository.UserRepository;
import com.fastcampus.investment.exception.IllegalUserException;
import com.fastcampus.investment.constant.UserInvestingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Service Layer -> Service Layer (Entity 리턴)
    @Transactional
    public UserEntity payForProduct(InvestPostRequestDTO InvestPostRequestDTO) {
        // Model에서 값 받아서 설정
        Long investAmount = InvestPostRequestDTO.getInvestAmount();
        Long userId = InvestPostRequestDTO.getUserId();
        UserEntity user = userRepository.findById(userId).orElseThrow(IllegalUserException::new);

        // 투자 금액 초과, 잔액 부족 시 실패
        if (!isValidUser(user, investAmount))
        {
            user.setInvestedAmount(investAmount);
            user.setStatus(UserInvestingType.FAIL);
            user.setInvestedAt(LocalDate.now());
            return user;
        }
        // 투자 성공
        return invest(user, investAmount);
    }

    private boolean isValidUser(UserEntity user, Long investAmount){
        return user.getHoldingAmount() > investAmount
                && user.getHoldingAmount() < 100_000_000_000L
                && investAmount < 100_000_000_000L;
    }

    private UserEntity invest(UserEntity user, Long investAmount){
        user.setInvestedAmount(user.getInvestedAmount()+investAmount);
        user.setHoldingAmount(user.getHoldingAmount()-investAmount);
        user.setInvestedCount(user.getInvestedCount()+1);
        user.setStatus(UserInvestingType.INVESTED);
        user.setInvestedAt(LocalDate.now());

        return user;
    }
}
