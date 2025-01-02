package org.dmit3ii.limitcontrolmicroservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long accountTo;
    @Column(name = "limit_sum")
    private BigDecimal limitSum;
    @Column(name = "limit_datetime")
    private ZonedDateTime limitDatetime;
    @Column(name = "limit_currency_shortname")
    private CurrencyShortname limitCurrencyShortname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "limit")
    private List<Transaction> transactions= new ArrayList<>();
}
