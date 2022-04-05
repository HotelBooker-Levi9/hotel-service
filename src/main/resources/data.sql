INSERT INTO destination(`id`,`image_url`,`name`)
        VALUES (1, "slika", "Serbia");

INSERT INTO city(`id`,`image_url`,`name`,`destination_id`)
        VALUES (1, "slika", "Novi Sad", 1);

INSERT INTO hotel(`id`,`capacity`,`description`,`image_url`,`is_deleted`,`name`,`price_per_day`,`city_id`)
        VALUES (1, 34, "Ajmo", "slika", false, "Grand Hotel", 1400, 1);