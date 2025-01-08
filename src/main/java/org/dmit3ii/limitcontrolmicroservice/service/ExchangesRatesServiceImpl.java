package org.dmit3ii.limitcontrolmicroservice.service;

import lombok.AllArgsConstructor;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.repository.ExchangesRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ExchangesRatesServiceImpl implements ExchangesRateService {
    private ExchangesRateRepository exchangesRateRepository;

    @Override
    public ExchangeRates saveExchangeRates(ExchangeRates exchangeRates) {
        return exchangesRateRepository.save(exchangeRates);
    }

    @Override
    public ExchangeRates getLastExchangeRates() {
        return exchangesRateRepository.findLast();
    }
}
