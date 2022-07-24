create table "User"(
                       user_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       firstName varchar(30),
                       lastName varchar(30),
                       amountOfMoney double precision
);
insert into "User"(firstName, lastName, amountOfMoney) values ('Ivan', 'Petrov',2450);
insert into "User"(firstName, lastName, amountOfMoney) values ('Roman', 'Kozak',850);
insert into "User"(firstName, lastName, amountOfMoney) values ('Max', 'Stasiv',5000);


create table Product(
                        product_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name varchar(100) NOT NULL,
                        price double precision NOT NULL check ( price>0 )
);
insert into Product(name,price) values ('milk',29);
insert into Product(name,price) values ('sugar',52.5);
insert into Product(name,price) values ('lemon',69);


CREATE table User_Product(
                             user_id int REFERENCES Product(product_id) ON DELETE SET NULL ,
                             product_id int REFERENCES "User"(user_id) ON DELETE SET NULL ,
                             PRIMARY KEY (user_id, product_id)
);
insert into User_Product values (1,1);
insert into User_Product values (1,3);
insert into User_Product values (2,2);
insert into User_Product values (2,3);
insert into User_Product values (3,1);
