--liquibase formatted sql

--changeset dmiit3iy:1



CREATE TABLE if not exists exchange_rates
(
    id BIGSERIAL PRIMARY KEY,
    disclaimer VARCHAR(255),
    license    VARCHAR(255),
    timestamp  BIGINT NOT NULL,
    base       VARCHAR(50),
    rates_json TEXT

);






