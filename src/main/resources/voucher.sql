create schema voucher;
use voucher;

CREATE TABLE voucher
(
    voucher_id BINARY(16) PRIMARY KEY,
    type varchar(20) NOT NULL,
    value BIGINT(50) NOT NULL
);