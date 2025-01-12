package org.dmit3ii.limitcontrolmicroservice.service;

import lombok.AllArgsConstructor;
import org.dmit3ii.limitcontrolmicroservice.client.ApiClient;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRatesDTO;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.dmit3ii.limitcontrolmicroservice.model.mapper.ExchangeRatesMapper;
import org.dmit3ii.limitcontrolmicroservice.repository.ExchangesRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@AllArgsConstructor
@Transactional
public class ExchangesRatesServiceImpl implements ExchangesRateService {
    private ExchangesRateRepository exchangesRateRepository;
    private ExchangeRatesMapper exchangeRatesMapper;
    private ApiClient apiClient;

    @Override
    public ExchangeRates saveExchangeRates(ExchangeRates exchangeRates) {
        return exchangesRateRepository.save(exchangeRates);
    }

    /**
     * Метод возвращает обменные курсы. Изначально берет из базы данных, а если дата получения не соответствует текущей,
     * то делает запрос на обновление данных с внешнего API
     *
     * @return
     */
    @Override
    public ExchangeRates getLastExchangeRates() {
        ExchangeRates exchangeRatesFromDB = exchangesRateRepository.findLast();
        if (!isActual(exchangeRatesFromDB)) {
            ExchangeRatesDTO exchangeRatesDTO = apiClient.getAllExchangeRates();
            saveExchangeRates(exchangeRatesMapper.toExchangeRates(exchangeRatesDTO));
            exchangeRatesFromDB = exchangesRateRepository.findLast();
        }
        return exchangeRatesFromDB;
    }

    /**
     * Метод для проверки актуальности информации по курсам обмена из БД
     *
     * @param exchangeRatesFromDB
     * @return
     */
    private static boolean isActual(ExchangeRates exchangeRatesFromDB) {
        if(exchangeRatesFromDB==null){
            return false;
        }
        return (!exchangeRatesFromDB.getTimestamp().toLocalDate().equals(LocalDate.now())) && (exchangeRatesFromDB.getDayOfReceivingInformation().equals(LocalDate.now()));
    }


    /**
     * Метод для перевода суммы транзакции из указанной в ней валюты в USD
     *
     * @param exchangeRates
     * @param transaction
     * @return
     */
    public BigDecimal convertSumToUSD(ExchangeRates exchangeRates, Transaction transaction) {
        if (exchangeRates == null || transaction == null) {
            throw new IllegalArgumentException("Exchange rates or transaction cannot be null");
        }
        Double value = exchangeRates.getRates().get(transaction.getCurrencyShortname().name());
        BigDecimal usd = BigDecimal.valueOf(value);
        return transaction.getSum().multiply(usd).setScale(6, RoundingMode.HALF_UP);
    }
}
