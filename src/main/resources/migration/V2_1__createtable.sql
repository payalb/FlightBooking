drop table if exists seat CASCADE ;

create table seat(
    seat_id varchar(20) not null,
    flight_id integer not null,
    class varchar(20) not null,
    seat_status varchar(20) not null,
    seat_location varchar(20) not null,
    constraint k_seat primary key (seat_id,flight_id), 
    constraint fk_flight foreign key (flight_id)
    references flight(flight_id) ON DELETE CASCADE
    
);

drop sequence if exists seat_seq;