-- V20250322150953__create_product_table.sql generated in /home/takion/all-projects/curso-java/leilao/martelandoapp/src/main/resources/db/migration
CREATE TABLE IF NOT EXISTS product(
    id BIGSERIAL not null primary key,
    owner_id bigint not null,
    image_url text not null,
    initial_offer decimal(10, 2) not null,
    title varchar(250) not null,
    description text not null,
    status varchar(50) not null,
    start_at TIMESTAMP not null default now(),
    end_at TIMESTAMP not null,
    FOREIGN key (owner_id)
    references users(id) on
    delete cascade on update cascade
);