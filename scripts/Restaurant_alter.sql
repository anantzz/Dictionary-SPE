alter table Tables
add constraint fk_customer_id_Tables FOREIGN KEY (customer_id) REFERENCES Customer(customer_id);

alter table Customer
add constraint fk_table_no_Customer FOREIGN KEY (table_no) REFERENCES Tables(table_no);

alter table Customer
add constraint fk_delivery_id_Customer FOREIGN KEY (delivery_id) REFERENCES Delivery(delivery_id);

alter table Orders
add constraint fk_customer_id_Orders FOREIGN KEY (customer_id) REFERENCES Customer(customer_id);

alter table Orders
add constraint fk_item_id_Orders FOREIGN KEY (item_id) REFERENCES Item(item_id);

alter table Delivery
add constraint fk_customer_id_Delivery FOREIGN KEY (customer_id) REFERENCES Customer(customer_id);