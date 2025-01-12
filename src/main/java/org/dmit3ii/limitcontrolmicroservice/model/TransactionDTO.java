package org.dmit3ii.limitcontrolmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private long accountFrom;

    private long accountTo;

    private CurrencyShortname currencyShortname;

    private BigDecimal sum;

    private ExpenseCategory expenseCategory;

}
