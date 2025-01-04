package org.dmit3ii.limitcontrolmicroservice.service;

import org.dmit3ii.limitcontrolmicroservice.model.ExpenseCategory;
import org.dmit3ii.limitcontrolmicroservice.model.Limit;

import java.math.BigDecimal;
import java.util.List;

public interface LimitService {

    Limit setLimit(Limit limit);

    List<Limit> getLimits(long accountTo, ExpenseCategory expenseCategory);

    BigDecimal getLastLimit(long accountTo, ExpenseCategory expenseCategory);
}
