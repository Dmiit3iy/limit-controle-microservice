--liquibase formatted sql

--changeset dmiit3iy:1

CREATE TABLE if not exists limits (
                                      id BIGSERIAL PRIMARY KEY,
                                      account_to BIGINT NOT NULL,
                                      expense_category VARCHAR(50) NOT NULL,
                                      limit_sum NUMERIC(19, 2) NOT NULL,
                                      limit_datetime TIMESTAMP NOT NULL,
                                      limit_currency_shortname VARCHAR(50) NOT NULL
);




CREATE TABLE if not exists transactions (
                                            id BIGSERIAL PRIMARY KEY,
                                            account_from BIGINT NOT NULL,
                                            account_to BIGINT NOT NULL,
                                            currency_shortname VARCHAR(50) NOT NULL,
                                            sum NUMERIC(19, 2) NOT NULL,
                                            expense_category VARCHAR(50) NOT NULL,
                                            datetime TIMESTAMP NOT NULL,
                                            limit_exceeded BOOLEAN DEFAULT FALSE NOT NULL,
                                            limit_id BIGINT,
                                            CONSTRAINT fk_transactions_limit FOREIGN KEY (limit_id) REFERENCES "limits" (id)
);



