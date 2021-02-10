package com.example.api.controller;

import com.example.api.model.CurrencyRate;
import com.example.api.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExchangeRateController {
    @Autowired
    private RateRepository rateRepository;

    @GetMapping("/rate")
    public ResponseEntity getCurrencyTable () {
        List<CurrencyRate> list = rateRepository.findAllByOrderByIdDesc();
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
