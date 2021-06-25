show databases;
create database banking;
use banking;
create table temp_users(
	Account_id int(15) not null unique,
    Name Varchar(30) not null,
    Mobile_number Varchar(30) not null,
    Address Varchar(50) not null,
    Email_id Varchar(50) not null,
    User_name Varchar(50) not null unique,
    Password Varchar(50) not null,
    primary key(Account_id)
);
create table admin(
	admin_id int(10) not null unique auto_increment,
    Username varchar(20) not null unique,
    Password Varchar(20) not null unique,
    primary key(admin_id)
    );
create table temp_users1(
	Account_id int(15) not null unique,
    Name Varchar(30) not null,
    Mobile_number Varchar(30) not null,
    Address Varchar(50) not null,
    Email_id Varchar(50) not null,
    User_name Varchar(50) not null unique,
    Password Varchar(50) not null,
    primary key(Account_id)
);
create table balance(
	balance_id int(10) not null unique auto_increment,
    Account_id Varchar(30) not null,
    Username Varchar(30) not null,
    balance Varchar(10) not null,
    primary key(balance_id)
);
create table transaction1(
	transaction_id int(10) not null unique auto_increment,
    Account_id varchar(20) not null,
    from_account varchar(20) not null,
    to_account varchar(20) not null,
    Date timestamp,
    Amount varchar(20) not null,
    primary key(transaction_id)
);
select * from balance;
select * from temp_users1;
select * from transaction1;
select * from temp_users;
select * from admin;
