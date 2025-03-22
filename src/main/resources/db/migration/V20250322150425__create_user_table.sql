-- V20250322150425__create_user_table.sql generated in /home/takion/all-projects/curso-java/leilao/martelandoapp/src/main/resources/db/migration
CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL not null primary key,
    name varchar(150) not null,
    email varchar(150) not null,
    phone char(11) not null,
    password varchar(100) not null,
    CONSTRAINT UK_EMAIL UNIQUE(email),
    CONSTRAINT UK_PHONE UNIQUE(phone)
);
