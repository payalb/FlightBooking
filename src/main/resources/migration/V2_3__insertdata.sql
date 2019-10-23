delete from seat;


do $$
declare
	fir_bus varchar[] := array['A','B','C','D','E']::varchar[];
	econ varchar[] := array['A','B','C','D','E','F','G','H','I','J']::varchar[];
 loc1 varchar[] := array['WINDOW','AISLE','AISLE','AISLE','WINDOW']::varchar[];
 loc2 varchar[] := array['WINDOW','OTHERS','AISLE','AISLE','OTHERS','OTHERS','AISLE','AISLE','OTHERS','WINDOW']::varchar[];
 v_idx integer := 1;
  aa varchar(5);
 x varchar(5);
  y varchar(5);
  z varchar(10):='OTHERS';


	ii integer:=1;
begin
  while v_idx <= 16 loop
  
  	 FOR ii IN 1..5 LOOP 
	
	aa=v_idx || fir_bus[ii];
    insert into seat(seat_id, flight_id, class, seat_status,seat_location) values (aa, 1, 'FIRSTCLASS', 'AVAILABLE', loc1[ii]);

	end loop;

    v_idx = v_idx + 1;
  end loop;
  
   while v_idx <= 20 loop
  
  	 FOR ii IN 1..5 LOOP 
	
	aa=v_idx || fir_bus[ii];
    insert into seat(seat_id, flight_id, class, seat_status,seat_location) values (aa, 1, 'BUSINESSCLASS', 'AVAILABLE', loc1[ii]);

	end loop;

    v_idx = v_idx + 1;
  end loop;
  
   while v_idx <= 70 loop
  
  	 FOR ii IN 1..10 LOOP 
	
	aa=v_idx || econ[ii];
    insert into seat(seat_id, flight_id, class, seat_status,seat_location) values (aa, 1, 'ECONOMYCLASS', 'AVAILABLE', loc2[ii]);

	end loop;

    v_idx = v_idx + 1;
  end loop;
end $$;

