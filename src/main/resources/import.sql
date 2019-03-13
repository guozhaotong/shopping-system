drop table if exists user_info;
create table user_info (id int primary key auto_increment,
                        name varchar(255),
                        identity varchar(255),
                        password varchar(255));
insert into user_info (id, name, identity, password) values (1, 'buyer', 'buyer', 'cmV5dWI=');
insert into user_info (id, name, identity, password) values (2, 'seller', 'seller', 'cmVsbGVz');

drop table if exists commodity;
create table commodity (id int primary key auto_increment,
                        seller_id int,
                        title varchar(255),
                        brief varchar(255),
                        intro varchar(255),
                        price FLOAT(2),
                        pic_addr varchar(255));
insert into commodity (id, seller_id, title, brief, intro,price,pic_addr) values (1, 2, '钢琴', '雅马哈钢琴，你值得拥有', '雅马哈钢琴',15000.01, '1.jpg' );
insert into commodity (id, seller_id, title, brief, intro,price,pic_addr) values (2, 2, '小提琴', '木质小提琴，超级好用哒', '木质小提琴',2500.22,'2.jpg' );
insert into commodity (id, seller_id, title, brief, intro,price,pic_addr) values (3, 2, '笔记本电脑', '神州笔记本电脑，很坑人', '廉价笔记本电脑',2500.85,'3.jpg' );
insert into commodity (id, seller_id, title, brief, intro,price,pic_addr) values (4, 2, '钢笔', '很好的本子上就要用最好的钢笔', '英雄牌钢笔',369.5,'4.jpg' );
insert into commodity (id, seller_id, title, brief, intro,price,pic_addr) values (5, 2, '自行车', '自行车专家都推荐的自行车', '凤凰自行车，骑天下',2500.85,'5.jpg' );
insert into commodity (id, seller_id, title, brief, intro,price,pic_addr) values (6, 2, '特价彤彤', '特价郭朝彤大甩卖，', '大甩卖了！',9603000.68,'6.jpg' );



drop table if exists order_info;
create table order_info (id int primary key auto_increment,
                        buyer_id int,
                        commodity_id int,
                        finish_time timestamp,
                        num int,
                        price_when_buy float(2) not null);
insert into order_info (id, buyer_id, commodity_id, finish_time, num,price_when_buy) values (1, 1, 2 ,'2019-02-12 11:43:34.718000000',3,3600);
insert into order_info (id, buyer_id, commodity_id, finish_time, num,price_when_buy) values (2, 1, 4 ,'2019-01-16 12:33:14.518000000',17,320.5);
insert into order_info (id, buyer_id, commodity_id, finish_time, num,price_when_buy) values (3, 1, 4 ,'2019-02-19 12:33:14.518000000',3,450);



drop table if exists shopping_cart;
create table shopping_cart (id int primary key auto_increment,
                        buyer_id int,
                        commodity_id int,
                        num int,
                        add_time timestamp);
insert into shopping_cart (id, buyer_id, commodity_id, num, add_time) values (1, 1, 3, 2, '2018-03-11 11:43:34.718000000');
insert into shopping_cart (id, buyer_id, commodity_id, num, add_time) values (2, 1, 6, 1, '2012-03-02 11:43:34.718000000');
insert into shopping_cart (id, buyer_id, commodity_id, num, add_time) values (3, 1, 1, 4, '2019-05-12 11:43:34.718000000');

