--liquibase formatted sql

--changeset dmiit3iy:1


ALTER TABLE exchange_rates
ALTER COLUMN timestamp TYPE TIMESTAMP;






