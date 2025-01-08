package org.dmit3ii.limitcontrolmicroservice.model;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRatesDTO {
    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private Map<String, Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "disclaimer='" + disclaimer + '\'' +
                ", license='" + license + '\'' +
                ", timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", rates=" + rates +
                '}';
    }
}
