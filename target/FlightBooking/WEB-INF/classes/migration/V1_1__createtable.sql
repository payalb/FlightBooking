declare
  table_does_not_exist exception;
  PRAGMA EXCEPTION_INIT(table_does_not_exist, -942);
begin
  execute immediate 'drop table admin CASCADE CONSTRAINTS /*+ IF EXISTS */';
  exception when table_does_not_exist then 
        DBMS_OUTPUT.PUT_LINE('Ignoring table or view does not exist')
   ;
end;
/

create table admin(
    admin_id number(10) primary key not null,
    admin_name varchar2(45) not null,
    password varchar2(50) not null
);

declare
  table_does_not_exist exception;
  PRAGMA EXCEPTION_INIT(table_does_not_exist, -942);
begin
  execute immediate 'drop table airplane CASCADE CONSTRAINTS /*+ IF EXISTS */';
  exception when table_does_not_exist then 
        DBMS_OUTPUT.PUT_LINE('Ignoring table or view does not exist')
   ;
end;
/

create table airplane(
    AIRPLANE_ID number(10) primary key not null,
    PRODUCER varchar2(20),
    FIRSTCLASS_CAPACITY number(10) not null,
    ECONOMYCLASS_CAPACITY number(10) not null,
    BUSINESSCLASS_CAPACITY number(10) not null
);

declare
  table_does_not_exist exception;
  PRAGMA EXCEPTION_INIT(table_does_not_exist, -942);
begin
  execute immediate 'drop table booking_status CASCADE CONSTRAINTS /*+ IF EXISTS */';
  exception when table_does_not_exist then 
        DBMS_OUTPUT.PUT_LINE('Ignoring table or view does not exist')
   ;
end;
/

create table booking_status(
    STATUS varchar2(20) primary key not null
);

declare
  table_does_not_exist exception;
  PRAGMA EXCEPTION_INIT(table_does_not_exist, -942);
begin
  execute immediate 'drop table flight CASCADE CONSTRAINTS /*+ IF EXISTS */';
  exception when table_does_not_exist then 
        DBMS_OUTPUT.PUT_LINE('Ignoring table or view does not exist')
   ;
end;
/

create table flight(
    flight_id number(10) primary key not null,
    arrival_time timestamp not null,
    departure_time timestamp not null,
    departure_city varchar2(45) not null,
    arrival_city varchar2(45) not null,
    airplane_id number(10) not null,
    constraint fk_airplane foreign key (airplane_id)
    references airplane(airplane_id) ON DELETE CASCADE
);

declare
  table_does_not_exist exception;
  PRAGMA EXCEPTION_INIT(table_does_not_exist, -942);
begin
  execute immediate 'drop table flight_class CASCADE CONSTRAINTS /*+ IF EXISTS */';
  exception when table_does_not_exist then 
        DBMS_OUTPUT.PUT_LINE('Ignoring table or view does not exist')
   ;
end;
/

create table flight_class(
    class varchar2(15) primary key not null
);

declare
  table_does_not_exist exception;
  PRAGMA EXCEPTION_INIT(table_does_not_exist, -942);
begin
  execute immediate 'drop table flight_seat CASCADE CONSTRAINTS /*+ IF EXISTS */';
  exception when table_does_not_exist then 
        DBMS_OUTPUT.PUT_LINE('Ignoring table or view does not exist')
   ;
end;
/

create table flight_seat(
    flight_id number(10) not null,
    FIRSTCLASS_LEFT number(10) not null,
    ECONOMYCLASS_LEFT number(10) not null,
    BUSINESSCLASS_LEFT number(10) not null,
    VERSION number(10) not null,
    constraint fk_flight_seat foreign key (flight_id)
    references flight(flight_id) ON DELETE CASCADE
);

declare
  table_does_not_exist exception;
  PRAGMA EXCEPTION_INIT(table_does_not_exist, -942);
begin
  execute immediate 'drop table passenger CASCADE CONSTRAINTS /*+ IF EXISTS */';
  exception when table_does_not_exist then 
        DBMS_OUTPUT.PUT_LINE('Ignoring table or view does not exist')
   ;
end;
/

create table passenger(
    passenger_id number(10) primary key not null,
    email varchar2(45) not null,
    password varchar2(50) not null,
    firstname varchar2(45) not null,
    lastname varchar2(45) not null,
    gender varchar2(6) not null,
    ssn varchar2(9),
    age number(10),
    street varchar2(45),
    apartment_number number(10),
    city varchar2(45),
    state varchar2(45),
    zip number(10),
    tel_home varchar2(45),
    tel_office varchar2(45),
    CONSTRAINT email_unique UNIQUE (email)
);

declare
  table_does_not_exist exception;
  PRAGMA EXCEPTION_INIT(table_does_not_exist, -942);
begin
  execute immediate 'drop table booking CASCADE CONSTRAINTS /*+ IF EXISTS */';
  exception when table_does_not_exist then 
        DBMS_OUTPUT.PUT_LINE('Ignoring table or view does not exist')
   ;
end;
/

create table booking(
    booking_id number(10) primary key not null,
    passenger_id number(10) not null,
    flight_id number(10) not null,
    seat_number number(10) not null,
    baggage number(10) not null,
    class varchar2(15) not null,
    status varchar2(20) not null,
    constraint fk_booking_status foreign key (status) references booking_status(status) ON DELETE CASCADE,
    constraint fk_flight foreign key (flight_id) references flight(flight_id) ON DELETE CASCADE,
    constraint fk_flight_class foreign key (class) references flight_class(class) ON DELETE CASCADE,
    constraint fk_passenger foreign key (passenger_id) references passenger(passenger_id) ON DELETE CASCADE
);