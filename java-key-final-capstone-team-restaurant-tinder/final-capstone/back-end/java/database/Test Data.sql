begin transaction;

select * from users;

insert into users
(username, password_hash, role)
values ('clubber', 'mohawk', 'ROLE_USER');

insert into users
(username, password_hash, role)
values ('tswift', 'red', 'ROLE_USER');

insert into users
(username, password_hash, role)
values ('carlyslayjepsen', 'maybe', 'ROLE_USER');

insert into users
(username, password_hash, role)
values ('dagger', 'goose', 'ROLE_USER');

insert into users
(username, password_hash, role)
values ('pandacat', 'PBM', 'ROLE_USER');

select * from users;

select * from profile;

insert into profile
(user_id, first_name, last_name, date_of_birth, email, zip_code)
values ((select user_id from users where username = 'clubber'), 'Mr.', 'T', '1952-05-21', 'ipitythefool@mailinator.com', 44446);

insert into profile
(user_id, first_name, last_name, date_of_birth, email, zip_code)
values ((select user_id from users where username = 'tswift'), 'Taylor', 'Swift', '1989-12-13', 'wenevergooutofstyle1989@mailinator.com', 44473);

insert into profile
(user_id, first_name, last_name, date_of_birth, email, zip_code)
values ((select user_id from users where username = 'carlyslayjepsen'), 'Carly Rae', 'Jepsen', '1985-11-21', 'ididntjustcomeheretodance@mailinator.com', 44118);

insert into profile
(user_id, first_name, last_name, date_of_birth, email, zip_code)
values ((select user_id from users where username = 'dagger'), 'Israel', 'Dagg', '1988-06-06', 'daggertotheheart@mailinator.com', 44444);

insert into profile
(user_id, first_name, last_name, date_of_birth, email, zip_code)
values ((select user_id from users where username = 'pandacat'), 'Maks', 'Yanevich', '2000-02-24', 'blinkartemis@mailinator.com', 44482);

select * from profile;

select * from preferences;

insert into preferences
(user_id, cuisine_style_1, cuisine_style_2, cuisine_style_3, price_point, vegan, vegetarian, gluten_free)
values ((select user_id from users where username = 'clubber'), 'Italian', 'Chinese', 'American', '$$', false, false, true);

insert into preferences
(user_id, cuisine_style_1, cuisine_style_2, cuisine_style_3, price_point, vegan, vegetarian, gluten_free)
values ((select user_id from users where username = 'tswift'), 'Mexican', 'Indian', 'Italian', '$$$$', true, true, false);

insert into preferences
(user_id, cuisine_style_1, cuisine_style_2, cuisine_style_3, price_point, vegan, vegetarian, gluten_free)
values ((select user_id from users where username = 'carlyslayjepsen'), 'BBQ', 'Sushi', 'Indian', '$$$', false, true, false);

insert into preferences
(user_id, cuisine_style_1, cuisine_style_2, cuisine_style_3, price_point, vegan, vegetarian, gluten_free)
values ((select user_id from users where username = 'dagger'), 'Thai', 'Italian', 'American', '$', false, false, false);

insert into preferences
(user_id, cuisine_style_1, cuisine_style_2, cuisine_style_3, price_point, vegan, vegetarian, gluten_free)
values ((select user_id from users where username = 'pandacat'), 'Burgers', 'Greek', 'French', '$', true, false, false);



select * from preferences;

--select * from preferences where profile_id = (select profile_id from profile where last_name = 'swift');
--select * from preferences where profile_id = (select profile_id from profile where last_name = 'T');

--select * from user_profile;
--
--insert into user_profile
--(user_id, profile_id)
--values ((select user_id from users where username = 'clubber'),
--(select profile_id from profile where last_name = 'T'));
--
--insert into user_profile
--(user_id, profile_id)
--values ((select user_id from users where username = 'tswift'),
--(select profile_id from profile where last_name = 'Swift'));
--
--select * from user_profile;

--select * from profile_preferences;
--
--insert into profile_preferences
--(profile_id, preference_id)
--values ((select profile_id from profile where last_name = 'T'),
--(select preference_id from preferences where vegan = false));
--
--insert into profile_preferences
--(profile_id, preference_id)
--values ((select profile_id from profile where last_name = 'Swift'),
--(select preference_id from preferences where vegan = true));
--
--select * from profile_preferences;

select * from restaurants;

insert into restaurants
(location_id, restaurant_name, restaurant_phone, restaurant_website, restaurant_description, restaurant_image, price_range, cuisine_type_1, cuisine_type_2, cuisine_type_3, cuisine_type_4, cuisine_type_5, cuisine_type_6, cuisine_type_7,
address, lat, lon)
values (12345678, 'Taco Bell', '(330) 652-1234', 'www.tacobell.com', 'Yo Quiero Taco Bell', 'https://upload.wikimedia.org/wikipedia/en/thumb/b/b3/Taco_Bell_2016.svg/1200px-Taco_Bell_2016.svg.png',
'$', 'Mexican', 'Fast Food', null, null, null, null, null, 
'123 Main St, Niles, OH 44446', 40.523687, -2.345678); 

insert into restaurants
(location_id, restaurant_name, restaurant_phone, restaurant_website, restaurant_description, restaurant_image, price_range, cuisine_type_1, cuisine_type_2, cuisine_type_3, cuisine_type_4, cuisine_type_5, cuisine_type_6, cuisine_type_7,
address, lat, lon)
values (98765432, 'Wing Stop', '(330) 394-1234', 'www.wingstop.com', 'Where Flavor Gets Its Wings', 'https://www.wingstop.com/assets/images/wingstop-logo-green-340.png',
'$$', 'Chicken', 'Fast Food', null, null, null, null, null, 
'456 Market St, Warren, OH 44482', 140.523123, -21.345321); 

insert into restaurants
(location_id, restaurant_name, restaurant_phone, restaurant_website, restaurant_description, restaurant_image, price_range, cuisine_type_1, cuisine_type_2, cuisine_type_3, cuisine_type_4, cuisine_type_5, cuisine_type_6, cuisine_type_7,
address, lat, lon)
values (56238974, 'Hot Dog Shoppe', '(330) 555-1234', 'www.originalhotdogshoppe.com', '7 Days Without a Hotdog Makes One Weak', 'https://scontent-mia3-2.xx.fbcdn.net/v/t1.18169-9/10403390_719368844767157_7115626781461645112_n.jpg?_nc_cat=109&ccb=1-4&_nc_sid=09cbfe&_nc_ohc=Ov_EJMPat-AAX9noz_R&_nc_ht=scontent-mia3-2.xx&oh=b7da4287f26dc7aee9d467dc994bc5f9&oe=613619A3',
'$', 'American', 'Burgers', null, null, null, null, null, 
'1 Hot Dog Shoppe Alley, Warren, OH 44444', 88.123456, 205.987654); 

insert into restaurants
(location_id, restaurant_name, restaurant_phone, restaurant_website, restaurant_description, restaurant_image, price_range, cuisine_type_1, cuisine_type_2, cuisine_type_3, cuisine_type_4, cuisine_type_5, cuisine_type_6, cuisine_type_7,
address, lat, lon)
values (12457836, 'Maccas', '(330) 123-4567', 'www.maccas.nz', 'Lovin It', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/2339px-McDonald%27s_Golden_Arches.svg.png',
'$', 'American', 'Fast Food', 'Burgers', null, null, null, null, 
'22 Ladies Mile, Glenfield, NZ 12345', -230.523687, -182.345678); 

insert into restaurants
(location_id, restaurant_name, restaurant_phone, restaurant_website, restaurant_description, restaurant_image, price_range, cuisine_type_1, cuisine_type_2, cuisine_type_3, cuisine_type_4, cuisine_type_5, cuisine_type_6, cuisine_type_7,
address, lat, lon)
values (85749612, 'Trattoria', '(440) 123-1234', 'www.trattoriacle.com', 'Best Italian Food in Cleveland', 'https://static.stacker.com/s3fs-public/styles/slide_desktop/s3/20160809-173338-largejpg_0.jpg',
'$$$', 'Italian', 'Fine Dining', null, null, null, null, null, 
'1944 Italia Way, Cleveland, OH 98765', 125.523687, -283.345678); 

insert into restaurants
(location_id, restaurant_name, restaurant_phone, restaurant_website, restaurant_description, restaurant_image, price_range, cuisine_type_1, cuisine_type_2, cuisine_type_3, cuisine_type_4, cuisine_type_5, cuisine_type_6, cuisine_type_7,
address, lat, lon)
values (13467958, 'Panera Bread', '(123) 456-7890', 'www.panerabread.com', 'Fresh Bread Makes Friends', 'https://upload.wikimedia.org/wikipedia/en/thumb/c/cb/Panera_Bread_logo.svg/1200px-Panera_Bread_logo.svg.png',
'$$', 'Soup', 'Sandwiches', 'Wraps', 'Coffee', 'Bakery', null, null, 
'886 Bread Ave, Cleveland, OH 56234', 45.523687, -79.345678); 

select * from restaurants;

select * from restaurants_profile;

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (1, 3, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (2, 3, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (3, 3, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (4, 3, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (5, 3, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (6, 3, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (1, 4, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (2, 4, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (3, 4, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (4, 4, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (5, 4, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (6, 4, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (1, 5, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (2, 5, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (3, 5, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (4, 5, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (5, 5, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (6, 5, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (1, 6, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (2, 6, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (3, 6, false);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (4, 6, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (5, 6, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (6, 6, true);

insert into restaurants_profile
(restaurant_id, user_id, is_liked)
values (1, 7, true);

select * from restaurants_profile;


commit;