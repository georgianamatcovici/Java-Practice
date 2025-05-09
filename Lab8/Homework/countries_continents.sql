create sequence auto_increment
    start with 1
    increment by 1
    ;
create sequence countries_ids
    start with 1
    increment by 1
;

create sequence city_ids
    start with 1
    increment by 1
;

create table continents
(id integer primary key,
 name varchar(30)
);

create table countries
(id integer primary key,
 name varchar(30),
 code varchar(30),
 continent integer references continents(id));



create or replace trigger auto_increment_continents
  before insert on continents
  for each row
begin
  :new.id := auto_increment.nextval;
end;


create or replace trigger auto_increment_countries
  before insert on countries
  for each row
begin
  :new.id := countries_ids.nextval;
end;

create table cities
(id integer primary key,
country varchar(50),
name varchar(50),
capital integer,
latitude NUMBER(9,6),
longitude NUMBER(9,6)
);

create or replace trigger trg_city_id
    before insert on cities
    for each row
begin
    :new.id := city_ids.nextval;
end;





