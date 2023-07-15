CREATE TABLE t_account
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(64)  NOT NULL,
    last_name     VARCHAR(64)  NOT NULL,
    email         VARCHAR(256) NOT NULL,
    phone_number  CHAR(11)     NOT NULL,
    creation_date DATE         NOT NULL
);

CREATE TABLE t_user
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(256) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    role     VARCHAR(32)  NOT NULL
);

CREATE TABLE t_wallet
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    balance       DOUBLE NOT NULL,
    creation_date DATE   NOT NULL,
    account_id    BIGINT REFERENCES t_account (id) ON DELETE CASCADE
);

CREATE TABLE t_transaction
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    sum                 DOUBLE    NOT NULL,
    date_time           TIMESTAMP NOT NULL,
    sender_wallet_id    BIGINT REFERENCES t_wallet (id) ON DELETE CASCADE,
    recipient_wallet_id BIGINT REFERENCES t_wallet (id) ON DELETE CASCADE
);

INSERT INTO t_user (username, password, role)
VALUES ('shyngys', '$2a$12$GqCYMieohm75qEoxlD2Z.Ow4DMiIpEESMpUBks2tYUV20/.Bs79Cy', 'USER');

