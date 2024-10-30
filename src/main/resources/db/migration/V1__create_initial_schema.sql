CREATE TABLE users (
    id              BIGSERIAL PRIMARY KEY,
    username        VARCHAR(100) UNIQUE NOT NULL,
    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL,
    password        VARCHAR(100) NOT NULL,
    is_enabled      BOOLEAN NOT NULL
);

CREATE TABLE experiences (
    id                      BIGSERIAL PRIMARY KEY,
    user_id                 BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    job_title               VARCHAR(100) NOT NULL,
    company_name            VARCHAR(255) NOT NULL,
    started_month           SMALLINT NOT NULL CHECK (started_month BETWEEN 1 AND 12),
    started_year            SMALLINT NOT NULL CHECK (started_year BETWEEN 1000 AND 9999),
    ended_month             SMALLINT CHECK (ended_month BETWEEN 1 AND 12),
    ended_year              SMALLINT CHECK (ended_year BETWEEN 1000 AND 9999),
    is_still_in_role        BOOLEAN NOT NULL,
    description             TEXT NOT NULL,
    created_date            TIMESTAMPTZ NOT NULL,
    last_modified_date      TIMESTAMPTZ
);
