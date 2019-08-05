drop table if exists admin CASCADE ;

create table admin(
    admin_id integer primary key not null,
    admin_name varchar(45) not null,
    password varchar(50) not null
);

drop table if exists airplane CASCADE ;

create table airplane(
    AIRPLANE_ID integer primary key not null,
    PRODUCER varchar(20),
    FIRSTCLASS_CAPACITY integer not null,
    ECONOMYCLASS_CAPACITY integer not null,
    BUSINESSCLASS_CAPACITY integer not null
);

drop table if exists booking_status CASCADE  ;

create table booking_status(
    STATUS varchar(20) primary key not null
);

drop table if exists  flight CASCADE  ;

create table flight(
    flight_id integer primary key not null,
    arrival_time timestamp not null,
    departure_time timestamp not null,
    departure_city varchar(45) not null,
    arrival_city varchar(45) not null,
    airplane_id integer not null,
    constraint fk_airplane foreign key (airplane_id)
    references airplane(airplane_id) ON DELETE CASCADE
);

drop table if exists  flight_class CASCADE  ;

create table flight_class(
    class varchar(15) primary key not null
);

drop table if exists flight_seat CASCADE ;


create table flight_seat(
    flight_id integer not null,
    FIRSTCLASS_LEFT integer not null,
    ECONOMYCLASS_LEFT integer not null,
    BUSINESSCLASS_LEFT integer not null,
    VERSION integer not null,
    constraint fk_flight_seat foreign key (flight_id)
    references flight(flight_id) ON DELETE CASCADE
);

drop table if exists  passenger CASCADE  ;

create table passenger(
    passenger_id integer primary key not null,
    email varchar(45) not null,
    password varchar(50) not null,
    firstname varchar(45) not null,
    lastname varchar(45) not null,
    gender varchar(6) not null,
    ssn varchar(9),
    age integer,
    street varchar(45),
    apartment_number integer,
    city varchar(45),
    state varchar(45),
    zip integer,
    tel_home varchar(45),
    tel_office varchar(45),
    CONSTRAINT email_unique UNIQUE (email)
);

drop table if exists  booking CASCADE ;

create table booking(
    booking_id integer primary key not null,
    passenger_id integer not null,
    flight_id integer not null,
    seat_number integer not null,
    baggage integer not null,
    class varchar(15) not null,
    status varchar(20) not null,
    constraint fk_booking_status foreign key (status) references booking_status(status) ON DELETE CASCADE,
    constraint fk_flight foreign key (flight_id) references flight(flight_id) ON DELETE CASCADE,
    constraint fk_flight_class foreign key (class) references flight_class(class) ON DELETE CASCADE,
    constraint fk_passenger foreign key (passenger_id) references passenger(passenger_id) ON DELETE CASCADE
);

drop sequence if exists Flight_seq;
drop sequence if exists Booking_seq;
drop sequence if exists passenger_seq;