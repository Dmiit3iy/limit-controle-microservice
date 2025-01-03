package org.dmit3ii.limitcontrolmicroservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dmit3ii.limitcontrolmicroservice.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiClient {
    @Value("${url.api.exchange}")
    private String apiUrl;
    @Value("${app.id}")
    private String apiId;
    private final RestTemplate restTemplate;

    @GetMapping
    public ExchangeRates getAllHolidays() {
        log.info("Отправка запроса на API для получения курсов валют");
        ResponseEntity<ExchangeRates> response = restTemplate.exchange(apiUrl + "/api/latest.json?app_id=" + apiId, HttpMethod.GET, null,
                ExchangeRates.class);
        if (response.getBody() == null) {
            log.error("Ответ от API пустой");
            throw new IllegalStateException("Ошибка получения списка курса валют с внешнего API");
        }
        log.info("Курсы валют успешно получены");
        return response.getBody();
    }
}
