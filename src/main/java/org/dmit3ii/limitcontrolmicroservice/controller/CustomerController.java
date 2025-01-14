package org.dmit3ii.limitcontrolmicroservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dmit3ii.limitcontrolmicroservice.model.Limit;
import org.dmit3ii.limitcontrolmicroservice.model.LimitDTO;
import org.dmit3ii.limitcontrolmicroservice.model.mapper.LimitMapper;
import org.dmit3ii.limitcontrolmicroservice.service.LimitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final LimitMapper limitMapper;
    private LimitService limitService;

    @PostMapping
    public ResponseEntity<Limit> setLimit(@RequestBody LimitDTO limitDTO) {
        Limit newLimit = limitService.setLimit(limitMapper.toLimit(limitDTO));
        log.info("Customer ID={} setting limit {}", newLimit.getAccountTo(), newLimit.getLimitSum());
        return ResponseEntity.ok(newLimit);
    }
}
