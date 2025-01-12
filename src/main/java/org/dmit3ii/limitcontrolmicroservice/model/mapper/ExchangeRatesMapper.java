package org.dmit3ii.limitcontrolmicroservice.model.mapper;

import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRatesDTO;
import org.dmit3ii.limitcontrolmicroservice.util.JsonUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Mapper(componentModel = "spring", imports = JsonUtil.class)
public interface ExchangeRatesMapper {
    @Mapping(source = "ratesJson", target = "rates", qualifiedByName = "jsonToMap")
    @Mapping(source = "timestamp", target = "timestamp", qualifiedByName = "localDateTimeToLong")
    ExchangeRatesDTO toDTO(ExchangeRates exchangeRates);

    @Mapping(source = "rates", target = "ratesJson", qualifiedByName = "mapToJson")
    @Mapping(source = "timestamp", target = "timestamp", qualifiedByName = "longToLocalDateTime")
    @Mapping(source = "license", target = "license")
    @Mapping(source = "disclaimer", target = "disclaimer")
    @Mapping(source = "base", target = "base")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dayOfReceivingInformation", ignore = true)
    ExchangeRates toEntity(ExchangeRatesDTO exchangeRatesDTO);

    @Named("jsonToMap")
    static Map<String, Double> jsonToMap(String ratesJson) {
        return ratesJson != null ? JsonUtil.fromJson(ratesJson, Map.class) : null;
    }

    @Named("mapToJson")
    static String mapToJson(Map<String, Double> rates) {
        return rates != null ? JsonUtil.toJson(rates) : null;
    }

    @Named("longToLocalDateTime")
    static LocalDateTime longToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    @Named("localDateTimeToLong")
    static long localDateTimeToLong(LocalDateTime timestamp) {
        return timestamp.atZone(ZoneId.systemDefault()).toEpochSecond();
    }
}
