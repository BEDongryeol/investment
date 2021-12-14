package com.fastcampus.investment.component.repository;

import com.fastcampus.investment.component.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

    @Query (value = "select P FROM ProductsEntity P " +
                    "where startedAt <= current_date and finishedAt >= current_date " +
                    "and P.investedAmount <> P.totalInvestAmount")
    List<ProductsEntity> getValidProducts();


}
