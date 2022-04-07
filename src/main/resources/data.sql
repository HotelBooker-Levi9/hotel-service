insert into destination(id,image_url,name,is_deleted) values(1,"https://img.theculturetrip.com/wp-content/uploads/2018/01/shutterstock_733210471.jpg","Serbia",false);
insert into destination(id,image_url,name,is_deleted) values(2,"https://www.icpdr.org/main/sites/default/files/styles/article_galleryfull/public/nodes/teaser_images/budapest_0.jpg?itok=u8IFUCRk","Hungary",false);
insert into destination(id,image_url,name,is_deleted) values(3,"https://deih43ym53wif.cloudfront.net/amalfi-italy-shutterstock_759048709_bdda191300.jpeg","Italy",false);

insert into city(id,name,image_url,destination_id,is_deleted) values(1,"Belgrade","https://lp-cms-production.imgix.net/features/2017/09/Belgrade-Knez-Mihailova-street-af958c3aa30c.jpg",1,false);
insert into city(id,name,image_url,destination_id,is_deleted) values(2,"Rome","https://media.timeout.com/images/105211701/750/422/image.jpg",3,false);
insert into city(id,name,image_url,destination_id,is_deleted) values(3,"Budapest","https://media.cntraveler.com/photos/5b8576320c5e123ef6ed3d55/master/pass/Budapest_GettyImages-512278610.jpg",2,false);


insert into hotel(id,name,image_url,description,price_per_day,capacity,is_deleted,city_id) values(1,"Grand", "https://www.princehotels.com/wp-content/uploads/2019/04/aboutslider2-1.jpg","This hotel is best for rest", 20, 20, false, 1);
insert into hotel(id,name,image_url,description,price_per_day,capacity,is_deleted,city_id) values(2,"Budapest Hotel", "https://www.gannett-cdn.com/-mm-/05b227ad5b8ad4e9dcb53af4f31d7fbdb7fa901b/c=0-64-2119-1259/local/-/media/USATODAY/USATODAY/2014/08/13/1407953244000-177513283.jpg","This hotel is in the centar of the city", 30, 40,false, 3);
insert into hotel(id,name,image_url,description,price_per_day,capacity,is_deleted,city_id) values(3,"Awesome hotel", "https://cdn.britannica.com/96/115096-050-5AFDAF5D/Bellagio-Hotel-Casino-Las-Vegas.jpg","Best price for good time", 55, 80, false,2);
insert into hotel(id,name,image_url,description,price_per_day,capacity,is_deleted,city_id) values(4,"Awesome hotel 2", "https://codedesign.org/storage/app/media/uploaded-files/digital-marketing-for-hotels.jpg","Best price for good time", 55, 80, false,1);
