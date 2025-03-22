-- V20250322152348__create_offer_table.sql generated in /home/takion/all-projects/curso-java/leilao/martelandoapp/src/main/resources/db/migration
CREATE TABLE IF NOT EXISTS offer(
    id BIGSERIAL not null primary key,
    bidder_id bigint not null,
    product_id bigint not null,
    amount decimal(10, 2) not null,
    status varchar(100) not null,
    FOREIGN key (bidder_id) references users(id) on delete cascade on update cascade,
    FOREIGN key (product_id) references product(id) on delete cascade on update cascade
);