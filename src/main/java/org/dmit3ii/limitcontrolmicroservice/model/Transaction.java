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
    @Column(name="account_from")
    private long accountFrom;
    @NonNull
    @Column(name = "account_to")
    private long accountTo;
    @NonNull
    @Column(name = "currency_shortname")
    private String currencyShortname;
    @NonNull
    @Column(name ="sum")
    private BigDecimal sum;
    @NonNull
    @Column(name = "expense_category")
    private String expenseCategory;
    @NonNull
    @Column(name = "datetime")
    private ZonedDateTime datetime;
    @ManyToOne
    @JoinColumn(name="limit_id")
    private Limit limit;
}
