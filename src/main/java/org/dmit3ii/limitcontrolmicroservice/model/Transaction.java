package org.dmit3ii.limitcontrolmicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Column(name="account_from", nullable=false)
    private long accountFrom;
    @NonNull
    @Column(name = "account_to", nullable=false)
    private long accountTo;
    @NonNull
    @Column(name = "currency_shortname", nullable=false)
    @Enumerated(EnumType.STRING)
    private CurrencyShortname currencyShortname;
    @NonNull
    @Column(name ="sum", nullable=false)
    private BigDecimal sum;
    @NonNull
    @Column(name = "expense_category", nullable=false)
    private String expenseCategory;
    @NonNull
    @Column(name = "datetime", nullable=false)
    private ZonedDateTime datetime;
    @ManyToOne
    @JoinColumn(name="limit_id")
    private Limit limit;
}
