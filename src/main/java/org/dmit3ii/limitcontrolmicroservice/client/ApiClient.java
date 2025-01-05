package org.dmit3ii.limitcontrolmicroservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dmit3ii.limitcontrolmicroservice.model.CurrencyShortname;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiClient {
    @Value("${url.api.exchange}")
    private String apiUrl;
    @Value("${APP_ID}")
    private String apiId;
    private String allsymbols = Arrays.stream(CurrencyShortname.values()).map(Enum::name).collect(Collectors.joining(","));
    private final RestTemplate restTemplate;

    public ExchangeRates getAllExchangeRates() {
        log.info("Отправка запроса на API для получения курсов валют");
        ResponseEntity<ExchangeRates> response = restTemplate.exchange(apiUrl + "/latest.json?app_id=" + apiId + "&symbols=" + allsymbols, HttpMethod.GET, null,
                ExchangeRates.class);
        if (response.getBody() == null) {
            log.error("Ответ от API пустой");
            throw new IllegalStateException("Ошибка получения списка курса валют с внешнего API");
        }
        log.info("Курсы валют успешно получены");
        return response.getBody();
    }
}
