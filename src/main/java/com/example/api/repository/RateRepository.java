package com.example.api.repository;

import com.example.api.model.CurrencyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends CrudRepository <CurrencyRate, Long> {
    List<CurrencyRate> findAllByOrderByIdDesc();
}
