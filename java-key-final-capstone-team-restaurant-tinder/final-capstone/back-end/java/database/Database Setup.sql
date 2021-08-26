begin transaction;

drop table if exists users cascade;
drop table if exists user_profile cascade;
drop table if exists profile cascade;
drop table if exists profile_preferences cascade;
drop table if exists preferences cascade;
drop table if exists restaurants cascade;
drop table if exists restaurants_profile cascade;
drop table if exists cuisines cascade;

DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

create table profile
(user_id integer not null,
first_name character varying(50) not null,
last_name character varying(50) not null,
date_of_birth date not null,
email character varying(100) not null,
zip_code numeric(5,0) not null,
constraint pk_profile_user_id Primary Key (user_id));

create table preferences
(user_id integer not null,
cuisine_style_1 character varying(50) not null,
cuisine_style_2 character varying(50) not null,
cuisine_style_3 character varying(50) not null,
price_point character varying(4) not null,
dietary_restrictions character varying (32),
--vegan character varying (10),
--vegetarian character varying (10),
--gluten_free character varying (10),
constraint pk_preferences_user_id Primary Key (user_id));

create table restaurants
(restaurant_id serial not null, -- may need to change data type to long to use restaurant_id from API
location_id bigint not null,
restaurant_name character varying(100),
restaurant_phone character varying (20),
restaurant_website character varying(10000),
restaurant_description character varying (100000),
restaurant_image character varying (10000),
-- hours character varying (50),
price_range character varying (100),
-- price_range_num int,
cuisine_type_1 character varying (50),
cuisine_type_2 character varying (50),
cuisine_type_3 character varying (50),
cuisine_type_4 character varying (50),
cuisine_type_5 character varying (50),
cuisine_type_6 character varying (50),
cuisine_type_7 character varying (50),
-- city character varying (100),
-- state character varying (2),
-- postal_code numeric(5,0),
-- street character varying (200),
-- formatted address, API calls it just "formatted" ex: 123 Main St, Cleveland, OH 44444
address character varying (500),
lat double precision,
lon double precision,
constraint pk_resturants_restaurant_id Primary Key (restaurant_id));

create table restaurants_profile
(restaurant_id int not null,
user_id int not null,
constraint pk_restaurants_profile_restaurant_id_user_id Primary Key (restaurant_id, user_id));

create table cuisines
(cuisine_number int not null,
cuisine_name character varying (100),
constraint pk_cuisines_cuisine_number Primary Key (cuisine_number));

insert into cuisines (cuisine_number, cuisine_name)
values (9908, 'American');

insert into cuisines (cuisine_number, cuisine_name)
values (10641, 'Pizza');

insert into cuisines (cuisine_number, cuisine_name)
values (10660, 'Thai');

insert into cuisines (cuisine_number, cuisine_name)
values (10664, 'Greek');

insert into cuisines (cuisine_number, cuisine_name)
values (4617, 'Italian');

insert into cuisines (cuisine_number, cuisine_name)
values (10666, 'Deli');

insert into cuisines (cuisine_number, cuisine_name)
values (10687, 'Middle Eastern');

insert into cuisines (cuisine_number, cuisine_name)
values (5473, 'Japanese');

insert into cuisines (cuisine_number, cuisine_name)
values (10653, 'Sushi');

insert into cuisines (cuisine_number, cuisine_name)
values (10659, 'Asian');

insert into cuisines (cuisine_number, cuisine_name)
values (10649, 'Mediterranean');

insert into cuisines (cuisine_number, cuisine_name)
values (10640, 'Bar');

insert into cuisines (cuisine_number, cuisine_name)
values (10345, 'Steakhouse');

insert into cuisines (cuisine_number, cuisine_name)
values (10670, 'Pub');

insert into cuisines (cuisine_number, cuisine_name)
values (5379, 'Chinese');

insert into cuisines (cuisine_number, cuisine_name)
values (10642, 'Cafe');

insert into cuisines (cuisine_number, cuisine_name)
values (10679, 'Healthy');

insert into cuisines (cuisine_number, cuisine_name)
values (10634, 'Southwestern');

insert into cuisines (cuisine_number, cuisine_name)
values (5110, 'Mexican');

insert into cuisines (cuisine_number, cuisine_name)
values (10646, 'Fast Food');

insert into cuisines (cuisine_number, cuisine_name)
values (10665, 'Vegtarian Friendly');

insert into cuisines (cuisine_number, cuisine_name)
values (10697, 'Vegan Options');

insert into cuisines (cuisine_number, cuisine_name)
values (10992, 'Gluten Free Options');



-- removed user-profile relator table

--create table user_profile
--(user_id integer not null,
--profile_id integer not null,
--constraint pk_user_profile_user_id_profile_id Primary Key (user_id, profile_id));

-- removed profile_preference relator table

--create table profile_preferences
--(profile_id integer not null,
--preference_id integer not null,
--constraint pk_profile_preferences_profile_id_preference_id Primary Key (profile_id, preference_id));

--alter table user_profile add foreign key (profile_id) references profile (profile_id);
--alter table user_profile add foreign key (user_id) references users (user_id);

--alter table profile_preferences add foreign key (profile_id) references profile (profile_id);
--alter table profile_preferences add foreign key (preference_id) references preferences (preference_id);

alter table preferences add foreign key (user_id) references users (user_id);
alter table profile add foreign key (user_id) references users (user_id);

alter table restaurants_profile add foreign key (restaurant_id) references restaurants (restaurant_id);
alter table restaurants_profile add foreign key (user_id) references profile (user_id);


commit;