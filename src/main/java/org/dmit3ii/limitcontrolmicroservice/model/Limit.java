package org.dmit3ii.limitcontrolmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "limits")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Positive(message = "Поле 'accountTo' должно быть положительным числом.")
    @Column(name = "account_to", nullable = false)
    private long accountTo;
    @NotNull
    @Column(name = "expense_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;
    @DecimalMin(value = "0.0", inclusive = false, message = "Поле 'limitSum' должно быть больше 0.")
    @Column(name = "limit_sum", nullable = false)
    private BigDecimal limitSum;
    @Column(name = "limit_datetime", nullable = false)
    private ZonedDateTime limitDatetime = ZonedDateTime.now();
    @Column(name = "limit_currency_shortname", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyShortname limitCurrencyShortname = CurrencyShortname.USD;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "limit")
    private List<Transaction> transactions = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public BigDecimal getLimitSum() {
        return limitSum;
    }

    public void setLimitSum(BigDecimal limitSum) {
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
