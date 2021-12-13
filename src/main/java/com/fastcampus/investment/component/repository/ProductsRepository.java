package com.fastcampus.investment.component.repository;

import com.fastcampus.investment.component.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

    List<ProductsEntity> findByStartedAtLessThanEqualAndFinishedAtGreaterThanEqual(LocalDate compareWithStartedAt, LocalDate compareWithFinishedAt);

    @Query (value = "select P FROM ProductsEntity P " +
                    "where startedAt <= current_date and finishedAt >= current_date ")
    List<ProductsEntity> getValidProducts();


}
