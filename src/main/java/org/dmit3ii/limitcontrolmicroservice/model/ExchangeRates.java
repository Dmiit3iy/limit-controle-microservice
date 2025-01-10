package org.dmit3ii.limitcontrolmicroservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.dmit3ii.limitcontrolmicroservice.util.JsonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "exchange_rates")
@Data
public class ExchangeRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disclaimer;

    private String license;

    private LocalDateTime timestamp;
    //поле для отслеживания даты запроса
    private LocalDate dayOfReceivingInformation = LocalDate.now();

    private String base;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String ratesJson;

    @Transient // Поле не сохраняется напрямую в базе данных
    private Map<String, Double> rates;



    public Map<String, Double> getRates() {
        if (rates == null && ratesJson != null) {
            rates = JsonUtil.fromJson(ratesJson, Map.class);
        }
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
        this.ratesJson = JsonUtil.toJson(rates);
    }

    public String getRatesJson() {
        return ratesJson;
    }

    public void setRatesJson(String ratesJson) {
        this.ratesJson = ratesJson;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

