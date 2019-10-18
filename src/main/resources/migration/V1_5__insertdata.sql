drop sequence if exists payment_seq;
create sequence payment_seq MINVALUE 1 start with 1 increment by 1 cache 20;