package org.dmit3ii.limitcontrolmicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_from", nullable = false)
    private long accountFrom;

    @Column(name = "account_to", nullable = false)
    private long accountTo;

    @Column(name = "currency_shortname", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyShortname currencyShortname;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @Column(name = "expense_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @Column(name = "datetime", nullable = false)
    private ZonedDateTime datetime = ZonedDateTime.now();
    @Column(name = "limit_exceeded", nullable = false)
    private boolean limitExceeded = false;
    @ManyToOne
    @JoinColumn(name = "limit_id")
    private Limit limit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    @NonNull
    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public @NonNull CurrencyShortname getCurrencyShortname() {
        return currencyShortname;
    }

    public void setCurrencyShortname(@NonNull CurrencyShortname currencyShortname) {
        this.currencyShortname = currencyShortname;
    }

    public @NonNull BigDecimal getSum() {
        return sum;
    }

    public void setSum(@NonNull BigDecimal sum) {
        this.sum = sum;
    }

    public @NonNull ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(@NonNull ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public @NonNull ZonedDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(@NonNull ZonedDateTime datetime) {
        this.datetime = datetime;
    }

    public boolean isLimitExceeded() {
        return limitExceeded;
    }

    public void setLimitExceeded(boolean limitExceeded) {
        this.limitExceeded = limitExceeded;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }
}
