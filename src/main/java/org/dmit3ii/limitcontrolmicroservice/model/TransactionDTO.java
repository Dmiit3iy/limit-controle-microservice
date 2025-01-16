package org.dmit3ii.limitcontrolmicroservice.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    @Positive(message = "Поле 'accountFrom' должно быть положительным числом.")
    private long accountFrom;
    @Positive(message = "Поле 'accountTo' должно быть положительным числом.")
    private long accountTo;

    private CurrencyShortname currencyShortname;

    @NotNull(message = "Поле 'limitSum' не должно быть пустым.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Поле 'limitSum' должно быть больше 0.")
    private BigDecimal sum;
    @NotNull
    private ExpenseCategory expenseCategory;

    public long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public CurrencyShortname getCurrencyShortname() {
        return currencyShortname;
    }

    public void setCurrencyShortname(CurrencyShortname currencyShortname) {
        this.currencyShortname = currencyShortname;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
