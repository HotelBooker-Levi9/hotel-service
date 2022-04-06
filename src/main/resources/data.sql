INSERT INTO destination(`id`,`image_url`,`name`)
        VALUES (1, "slika", "Serbia");
INSERT INTO destination(`id`,`image_url`,`name`)
        VALUES (2, "slika", "Montenegro");

INSERT INTO city(`id`,`image_url`,`name`,`destination_id`)
        VALUES (1, "slika", "Novi Sad", 1);
INSERT INTO city(`id`,`image_url`,`name`,`destination_id`)
        VALUES (2, "slika", "Tivat", 2);
INSERT INTO city(`id`,`image_url`,`name`,`destination_id`)
        VALUES (3, "slika", "Apatin", 1);
INSERT INTO city(`id`,`image_url`,`name`,`destination_id`)
        VALUES (4, "slika", "Podgorica", 2);
INSERT INTO city(`id`,`image_url`,`name`,`destination_id`)
        VALUES (5, "slika", "Ruski Krstur", 1);
INSERT INTO city(`id`,`image_url`,`name`,`destination_id`)
        VALUES (6, "slika", "Danilovgrad", 1);

INSERT INTO hotel(`id`,`capacity`,`description`,`image_url`,`is_deleted`,`name`,`price_per_day`,`city_id`)
        VALUES (1, 34, "Ajmo", "slika", false, "Grand Hotel", 1400, 1);
INSERT INTO hotel(`id`,`capacity`,`description`,`image_url`,`is_deleted`,`name`,`price_per_day`,`city_id`)
        VALUES (2, 35, "Idemo", "slika1", true, "Ami Hotel", 400, 5);
INSERT INTO hotel(`id`,`capacity`,`description`,`image_url`,`is_deleted`,`name`,`price_per_day`,`city_id`)
        VALUES (3, 13, "Najbolji", "slika2", false, "Prezident", 1900, 2);
INSERT INTO hotel(`id`,`capacity`,`description`,`image_url`,`is_deleted`,`name`,`price_per_day`,`city_id`)
        VALUES (4, 34, "Ajmo", "slika", false, "Moskva", 1400, 3);
INSERT INTO hotel(`id`,`capacity`,`description`,`image_url`,`is_deleted`,`name`,`price_per_day`,`city_id`)
        VALUES (5, 35, "Idemo", "slika1", true, "Suncica", 400, 2);
INSERT INTO hotel(`id`,`capacity`,`description`,`image_url`,`is_deleted`,`name`,`price_per_day`,`city_id`)
        VALUES (6, 13, "Najbolji", "slika2", false, "Hacker Burger", 999, 1);