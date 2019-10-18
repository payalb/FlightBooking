drop table if exists payment;

create table payment(
    payment_id integer primary key not null,
    passenger_id integer not null,
    booking_id integer not null,
    price NUMERIC not null,
    payment_time timestamp not null,
    constraint fk_booking_id foreign key (booking_id) references booking(booking_id) ON DELETE CASCADE,
    constraint fk_passenger foreign key (passenger_id) references passenger(passenger_id) ON DELETE CASCADE
);