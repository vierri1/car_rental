DELETE
FROM car;
DELETE
FROM user;
ALTER TABLE car
  AUTO_INCREMENT = 1;
ALTER TABLE user
  AUTO_INCREMENT = 1;
INSERT INTO car (brand, release_year)
VALUES ('Audi', '2017-01-01');
INSERT INTO car (brand, release_year)
VALUES ('Mercedes', '2018-01-01');
INSERT INTO user (name, birth_day)
VALUES ('Jankins', '1990-01-01');
INSERT INTO car (brand, release_year, user_id)
VALUES ('bmw', '2019-01-01', 1);