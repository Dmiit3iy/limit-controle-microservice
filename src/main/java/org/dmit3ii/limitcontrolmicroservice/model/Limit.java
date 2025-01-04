package org.dmit3ii.limitcontrolmicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "limits")
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "account_to", nullable = false)
    private long accountTo;
    @Column(name = "expense_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;
    @Column(name = "limit_sum", nullable = false)
    private BigDecimal limitSum;
    @Column(name = "limit_datetime", nullable = false)
    private ZonedDateTime limitDatetime = ZonedDateTime.now();
    @Column(name = "limit_currency_shortname", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyShortname limitCurrencyShortname = CurrencyShortname.USD;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "limit")
    private List<Transaction> transactions = new ArrayList<>();
}
