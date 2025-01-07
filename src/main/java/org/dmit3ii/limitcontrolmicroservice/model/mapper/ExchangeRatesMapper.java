package org.dmit3ii.limitcontrolmicroservice.model.mapper;

import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRatesDTO;
import org.dmit3ii.limitcontrolmicroservice.util.JsonUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Map;

@Mapper(componentModel = "spring", imports = JsonUtil.class)
public interface ExchangeRatesMapper {
    @Mapping(source = "ratesJson", target = "rates", qualifiedByName = "jsonToMap")
    ExchangeRatesDTO toDTO(ExchangeRates exchangeRates);

    @Mapping(source = "rates", target = "ratesJson", qualifiedByName = "mapToJson")
    ExchangeRates toEntity(ExchangeRatesDTO exchangeRatesDTO);

    @Named("jsonToMap")
    static Map<String, Double> jsonToMap(String ratesJson) {
        return ratesJson != null ? JsonUtil.fromJson(ratesJson, Map.class) : null;
    }

    @Named("mapToJson")
    static String mapToJson(Map<String, Double> rates) {
        return rates != null ? JsonUtil.toJson(rates) : null;
    }
}
