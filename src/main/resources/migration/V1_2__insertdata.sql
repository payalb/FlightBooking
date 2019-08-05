insert into admin values (1, 'admin', 'admin');

insert into airplane(AIRPLANE_ID, PRODUCER, businessclass_capacity, firstclass_capacity, economyclass_capacity) 
    values (747, 'Boeing', 20, 80, 500);
insert into airplane(AIRPLANE_ID, PRODUCER, businessclass_capacity, firstclass_capacity, economyclass_capacity) 
    values (777, 'Boeing', 15, 50, 300);
insert into airplane(AIRPLANE_ID, PRODUCER, businessclass_capacity, firstclass_capacity, economyclass_capacity) 
    values (380, 'Airbus', 30, 70, 550);

insert into booking_status values('CANCELLED');
insert into booking_status values('PAID');
insert into booking_status values('RESERVED');

insert into flight_class values('BUSINESSCLASS');
insert into flight_class values('ECONOMYCLASS');
insert into flight_class values('FIRSTCLASS');

create sequence Flight_seq MINVALUE 1 start with 1 increment by 1 cache 20;
create sequence Booking_seq MINVALUE 1 start with 1 increment by 1 cache 20;
create sequence passenger_seq MINVALUE 1 start with 1 increment by 1 cache 20;

insert into flight ( flight_id, arrival_time, departure_time ,departure_city ,
    arrival_city ,airplane_id) values (101,  '2019-08-05T8:30', '2019-08-5T10:30', 'delhi','bangalore',747 );
    commit;