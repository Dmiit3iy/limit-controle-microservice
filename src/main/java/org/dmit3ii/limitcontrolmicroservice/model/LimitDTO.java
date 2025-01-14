package org.dmit3ii.limitcontrolmicroservice.model;

import lombok.ToString;
import java.time.ZonedDateTime;

@ToString
public class LimitDTO {

    private long accountTo;

    private ExpenseCategory expenseCategory;

    private double limitSum;

    private ZonedDateTime limitDatetime = ZonedDateTime.now();

    private CurrencyShortname limitCurrencyShortname = CurrencyShortname.USD;

    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public double getLimitSum() {
        return limitSum;
    }

    public void setLimitSum(double limitSum) {
        this.limitSum = limitSum;
    }

    public ZonedDateTime getLimitDatetime() {
        return limitDatetime;
    }

    public void setLimitDatetime(ZonedDateTime limitDatetime) {
        this.limitDatetime = limitDatetime;
    }

    public CurrencyShortname getLimitCurrencyShortname() {
        return limitCurrencyShortname;
    }

    public void setLimitCurrencyShortname(CurrencyShortname limitCurrencyShortname) {
        this.limitCurrencyShortname = limitCurrencyShortname;
    }
}
