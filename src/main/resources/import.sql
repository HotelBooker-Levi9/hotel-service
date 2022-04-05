insert into destination(id,image_url,name) values(1," ","Serbia");
insert into destination(id,image_url,name) values(2," ","Hungary");
insert into destination(id,image_url,name) values(3," ","Italy");

insert into city(id,name,image_url,destination_id,is_deleted) values(1,"Belgrade","",1,false);
insert into city(id,name,image_url,destination_id,is_deleted) values(2,"Rome","",3,false);
insert into city(id,name,image_url,destination_id,is_deleted) values(3,"Budapest","",2,false);


insert into hotel(id,name,image_url,description,price_per_day,capacity,is_deleted,city_id) values(1,"Grand", "","This hotel is best for rest", 20, 20, false, 1);
insert into hotel(id,name,image_url,description,price_per_day,capacity,is_deleted,city_id) values(2,"Budapest Hotel", "","This hotel is in the centar of the city", 30, 40,false, 3);
insert into hotel(id,name,image_url,description,price_per_day,capacity,is_deleted,city_id) values(3,"Awesome hotel", "","Best price for good time", 55, 80, false,2);
