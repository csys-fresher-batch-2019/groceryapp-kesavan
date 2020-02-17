create table products(
 product_name varchar2(30) not null,
 product_id number primary key,
 manufacturer varchar2(30),
 quantity float,
 unit varchar2(25),
 price_Rs number not null,
 stock int,
 status varchar2(25)
 );
 
 create table usersdata(
user_id number primary key,
user_name varchar2(25) unique,
password varchar2(15) not null,
mail_id varchar2(30) unique,
phone_no number(10) unique ,
delivery_address varchar2(60)
);
create sequence se_name start with 1100 increment by 1;

create table orderdata(
user_id number not null,
order_id number,
product_id number not null,
no_of_items  number not null,
price_per_item number,
total_amount number,
order_date date  not null,
delivery_date date,
order_status varchar2(30) default 'NOT ORDERED',
payment varchar2(30) default 'COD',
transaction_id number,
constraint product_id_fk foreign key(product_id) references products(product_id),
constraint user_id_fk foreign key(user_id) references usersdata(user_id)
);
create sequence seq_name start with 1000 increment by 2;

create table proreview(
product_id number,
rating float default 0,
review varchar2(10)
);

create table review(
order_id number unique,
product_id number not null,
rating float not null,
constraint product_fk foreign key(product_id) references products(product_id)
);
