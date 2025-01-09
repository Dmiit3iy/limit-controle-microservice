package org.dmit3ii.limitcontrolmicroservice.controller;

import lombok.AllArgsConstructor;
import org.dmit3ii.limitcontrolmicroservice.client.ApiClient;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRatesDTO;
import org.dmit3ii.limitcontrolmicroservice.model.mapper.ExchangeRatesMapper;
import org.dmit3ii.limitcontrolmicroservice.service.ExchangesRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchanges")
@AllArgsConstructor
public class ExchangesRateController {
    private ExchangesRateService exchangesRateService;
    private ApiClient apiClient;
    private ExchangeRatesMapper exchangeRatesMapper;


    @GetMapping
    public ResponseEntity<ExchangeRates> getExchangeRates() {
        ExchangeRatesDTO exchangeRatesDTO = apiClient.getAllExchangeRates();
        ExchangeRates exchangeRates = exchangeRatesMapper.toEntity(exchangeRatesDTO);
        exchangesRateService.saveExchangeRates(exchangeRates);
        return new ResponseEntity<>(exchangeRates, HttpStatus.OK);
    }

}
