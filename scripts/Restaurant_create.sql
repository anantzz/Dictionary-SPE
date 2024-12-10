CREATE TABLE Item(
    item_name varchar(20),
    item_id int primary key,
    price int,
    rating float,
    number_of_votes int
);
CREATE TABLE Tables(
    table_no int primary key,
    customer_id int,
    capacity int
);
CREATE TABLE Customer(
    customer_id int primary key,
    customer_name varchar(20),
    contact_no varchar(20),
    table_no int,
    customer_address varchar(20),
    delivery_id int
);
CREATE TABLE Orders(
    customer_id int,
    item_id int,
    quantity int,
    order_status varchar(20)
);
CREATE TABLE Delivery(
    delivery_id int primary key,
    deliverer_name varchar(20),
    contact_no varchar(20),
    rating float,
    number_of_votes int,
    customer_id int
);