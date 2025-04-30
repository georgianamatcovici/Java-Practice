create table countries
(
    id integer primary key generated always as identity,
    name varchar(30),
    code varchar(30), 
    continent integer references continents(id) on delete cascade

);

create table continents
(
id integer primary key generated always as identity,
name varchar(30)
);