package com.fastcampus.investment.component.repository;

import com.fastcampus.investment.component.entity.InvestingStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestingStatusRepository extends JpaRepository<InvestingStatusEntity, Long> {
    InvestingStatusEntity findByUserIdAndProductsId(Long userId, Long productsId);

    InvestingStatusEntity findByUserIdAndProductsIdOrderByIdDesc(Long userId, Long productsId);
}
