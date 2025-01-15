package org.dmit3ii.limitcontrolmicroservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private LimitMapper limitMapper;
    private LimitService limitService;
    private TransactionService transactionService;

    @Operation(summary = "Установить лимит")
    @PostMapping("/limits")
    public ResponseEntity<Limit> setLimit(@RequestBody @NotBlank(message = "Нужно передать лимит в теле запроса") LimitDTO limitDTO) {
        Limit newLimit = limitService.setLimit(limitMapper.toLimit(limitDTO));
        log.info("Customer ID={} setting limit {}", newLimit.getAccountTo(), newLimit.getLimitSum());
        return ResponseEntity.ok(newLimit);
    }

    @Operation(summary = "Получить все лимиты клиента",
            description = "Получаем все лимиты клиента установленные в текущем месяце на основе переданного Id клиента и необходимой категории")
    @GetMapping("/{accountFrom}/{expenseCategory}/limits")
    public ResponseEntity<List<Limit>> getLimits(@PathVariable("accountFrom") @Positive(message = "accountFrom должно быть положительным.") long accountFrom, @PathVariable("expenseCategory") @NotBlank(message = "Категория расходов не должна быть пустой.") String expenseCategory) {
        List<Limit> limitList = limitService.getLimits(accountFrom, ExpenseCategory.valueOf(expenseCategory.toUpperCase()));
        log.info("For customer ID={} getting limit list for category {}", accountFrom, expenseCategory);
        return ResponseEntity.ok(limitList);
    }

    @Operation(summary = "Получить транзакции превысившие установленные лимиты",
            description = "Получаем транзакции которые превышали установленные лимиты в этом месяце по всем категориям, на основе переданного Id клиента")
    @GetMapping("/{accountFrom}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsExceedingLimits(@PathVariable("accountFrom") @Positive(message = "accountFrom должно быть положительным.") long accountFrom) {
        List<Transaction> transactionList = transactionService.getAllTransactionsInThisMonthWithLimitsExceeded(accountFrom);
        log.info("For customer ID={} requested all transactions that exceeded the established limits this month.", accountFrom);
        return ResponseEntity.ok(transactionList);
    }
}
