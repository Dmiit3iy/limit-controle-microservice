package org.dmit3ii.limitcontrolmicroservice.model.mapper;

import org.dmit3ii.limitcontrolmicroservice.model.Transaction;
import org.dmit3ii.limitcontrolmicroservice.model.TransactionDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toTransaction(TransactionDTO transactionDTO);

    TransactionDTO toTransactionDTO(Transaction transaction);
}
