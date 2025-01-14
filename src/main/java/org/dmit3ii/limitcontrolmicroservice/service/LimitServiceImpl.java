package org.dmit3ii.limitcontrolmicroservice.service;

import lombok.AllArgsConstructor;
import org.dmit3ii.limitcontrolmicroservice.model.ExpenseCategory;
import org.dmit3ii.limitcontrolmicroservice.model.Limit;
import org.dmit3ii.limitcontrolmicroservice.repository.LimitRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LimitServiceImpl implements LimitService {
    LimitRepository limitRepository;

    @Override
    public Limit setLimit(Limit limit) {

        limit.setLimitDatetime(ZonedDateTime.now());
        return limitRepository.save(limit);
    }

    @Override
    public List<Limit> getLimits(long accountFrom, ExpenseCategory expenseCategory) {
        return limitRepository.findAllByAccountToAndExpenseCategoryAndCurrentMonth(accountFrom, expenseCategory);
    }

    @Override
    public Limit getLastLimit(long accountFrom, ExpenseCategory expenseCategory) {
        List<Limit> limits = getLimits(accountFrom, expenseCategory);
        if (limits.isEmpty()) {
            Limit defealutLimit = new Limit();
            defealutLimit.setAccountTo(accountFrom);
            defealutLimit.setExpenseCategory(expenseCategory);
            defealutLimit.setLimitSum(BigDecimal.valueOf(1000));
            return setLimit(defealutLimit);
        }
        return limits.get(0);
    }
}
