package org.dmit3ii.limitcontrolmicroservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dmit3ii.limitcontrolmicroservice.model.ExpenseCategory;
import org.dmit3ii.limitcontrolmicroservice.model.Limit;
import org.dmit3ii.limitcontrolmicroservice.model.LimitDTO;
import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.dmit3ii.limitcontrolmicroservice.model.mapper.LimitMapper;
import org.dmit3ii.limitcontrolmicroservice.service.LimitService;
import org.dmit3ii.limitcontrolmicroservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private LimitMapper limitMapper;
    private LimitService limitService;
    private TransactionService transactionService;

    @Operation(summary = "Установить лимит")
    @PostMapping("/limits")
    public ResponseEntity<Limit> setLimit(@RequestBody LimitDTO limitDTO) {
        Limit newLimit = limitService.setLimit(limitMapper.toLimit(limitDTO));
        log.info("Customer ID={} setting limit {}", newLimit.getAccountTo(), newLimit.getLimitSum());
        return ResponseEntity.ok(newLimit);
    }

    @Operation(summary = "Получить все лимиты клиента",
            description = "Получаем все лимиты клиента установленные в текущем месяце на основе переданного Id клиента и необходимой категории")
    @GetMapping("/{accountFrom}/{expenseCategory}/limits")
    public ResponseEntity<List<Limit>> getLimits(@PathVariable("accountFrom") long accountFrom, @PathVariable("expenseCategory") String expenseCategory) {
        List<Limit> limitList = limitService.getLimits(accountFrom, ExpenseCategory.valueOf(expenseCategory.toUpperCase()));
        log.info("For customer ID={} getting limit list for category {}", accountFrom, expenseCategory);
        return ResponseEntity.ok(limitList);
    }

    @Operation(summary = "Получить транзакции превысившие установленные лимиты",
            description = "Получаем транзакции которые превышали установленные лимиты в этом месяце по всем категориям, на основе переданного Id клиента")
    @GetMapping("/{accountFrom}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsExceedingLimits(@PathVariable("accountFrom") long accountFrom) {
        List<Transaction> transactionList = transactionService.getAllTransactionsInThisMonthWithLimitsExceeded(accountFrom);
        log.info("For customer ID={} requested all transactions that exceeded the established limits this month.", accountFrom);
        return ResponseEntity.ok(transactionList);
    }
}
