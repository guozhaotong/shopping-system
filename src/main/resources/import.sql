drop table if exists user_info;
create table user_info (id int primary key auto_increment, name varchar(255), identity varchar(255), password varchar(255));
insert into user_info (id, name, identity, password) values (1, 'buyer', 'buyer', 'cmV5dWI=');
insert into user_info (id, name, identity, password) values (2, 'seller', 'seller', 'cmVsbGVz');


drop table if exists order_info;
create table order_info (id int primary key auto_increment,
                        buyer_id int,
                        commodity_id int,
                        finish_time timestamp,
                        num int,
                        price_when_buy float(2) not null);


drop table if exists commodity;
create table commodity (id int primary key auto_increment,
                        seller_id int,
                        title varchar(255),
                        brief varchar(255),
                        intro varchar(255),
                        price FLOAT(2),
                        pic_addr varchar(255));
insert into commodity (seller_id, title, brief, intro,price,pic_addr) values (2, '钢琴', '雅马哈钢琴，你值得拥有', '雅马哈钢琴',15000.01,'eeeac404-4bce-4cb7-b9af-75aa50667968.jpg' );
insert into commodity (seller_id, title, brief, intro,price,pic_addr) values (2, '小提琴', '木质小提琴，超级好用哒', '木质小提琴',2500.22,NULL );

drop table if exists shopping_cart;
create table shopping_cart (id int primary key auto_increment, buyer_id int, commodity_id int, num int, add_time timestamp);
-- insert into shopping_cart (id, buyer_id, commodity_id, num, add_time) values (1, 1, 6, 9, select now());
