create database if not exists day08;
use day08;
create table user (
  id int primary key auto_increment,
  name varchar(20),
  password varchar(40)
);
select * from user;