package org.dmit3ii.limitcontrolmicroservice.model;

public enum CurrencyShortname {
    KZT("KZT"),
    USD("USD"),
    RUB("RUB");
    private final String code;

    CurrencyShortname(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CurrencyShortname fromCode(String code) {
        for (CurrencyShortname currency : values()) {
            if (currency.code.equals(code)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Неизвестный код валюты: " + code);
    }
}
