package org.dmit3ii.limitcontrolmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private long accountFrom;

    private long accountTo;

    private CurrencyShortname currencyShortname;

    private BigDecimal sum;

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
