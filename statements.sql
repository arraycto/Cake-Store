## 创建库
create database cake;

## 顾客
create table customer(
	id int(11) not null comment 'ID',
	name varchar(20) not null comment '名字',
	password varchar(32) not null comment '密码',
	primary key(id)
) engine=innodb default charset=utf8;


alter table customer modify id int(11) auto_increment;


insert into customer(name,password) values ('pwd1333','1333');
select name , password from customer;
select name , password from customer where name='pwd1234';

alter table customer add constraint unique (name);

## 蛋糕
create table angel_cake(
	acid int(11) not null comment 'ID' auto_increment,
	cakename varchar(20) not null comment '名字' ,
	type varchar(86) not null comment '类型',
	price int(30) not null comment '价格',
	primary key(acid)
) engine=innodb default charset=utf8;

alter table angel_cake add constraint unique (cakename);

desc angel_cake;

alter table angel_cake add supplier varchar(92) comment '供应商'; 
alter table angel_cake add amount int(22) comment '库存'; 

alter table angel_cake change type caketype varchar(20) not null;

alter table angel_cake change cakename cakename varchar(50) not null;

| angel_cake | CREATE TABLE `angel_cake` (
  `acid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `cakename` varchar(50) NOT NULL,
  `caketype` varchar(20) NOT NULL,
  `price` int(30) NOT NULL COMMENT '价格',
  `supplier` varchar(92) DEFAULT NULL COMMENT '供应商',
  `amount` int(22) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`acid`),
  UNIQUE KEY `cakename` (`cakename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into angel_cake (cakename,caketype,price,supplier,amount) values('佩梯歌意蛋糕','千层蛋糕',20,'米莱斯糕点屋',103);

select acid,cakename,caketype,price,supplier,amount	 from  angel_cake;

insert into angel_cake (cakename,caketype,price,supplier,amount) values('法兰雪瑞','冰淇淋蛋糕',17,'佛丽莎糕点屋',63);

insert into angel_cake (cakename,caketype,price,supplier,amount) values('笛福朗诗','奶油蛋糕',25,'阿布来微糕点屋',80);


## 购物车
create table cake_cart(
	ccid int(11) not null comment 'ID' auto_increment,
	ccname varchar(20) not null comment '名字' ,
	cctype varchar(86) not null comment '类型',
	ccprice int(30) not null comment '价格',
	primary key(ccid)
) engine=innodb default charset=utf8;	

alter table cake_cart add constraint unique (ccname);


alter table cake_cart add supplier varchar(92) comment '供应商'; 

alter table cake_cart change cctype cctype varchar(20) not null;

alter table cake_cart change ccname ccname varchar(50) not null;

alter table cake_cart change supplier ccsupplier varchar(80) not null;

insert into cake_cart (ccname,cctype,ccprice,ccsupplier) values('法兰雪瑞','冰淇淋蛋糕',17,'佛丽莎糕点屋');


select ccid,ccname,cctype,ccprice,ccsupplier from  cake_cart;

alter table cake_cart drop index ccname;

select acid,cakename,caketype,price,supplier,amount from angel_cake where acid=2;

update angel_cake set amount=62 where acid=2;

alter table cake_cart add cuid varchar(36) not null comment '会员ID';	


select caketype from angel_cake group by caketype;

select acid,cakename,caketype,price,supplier,amount from angel_cake where caketype='奶油蛋糕';

insert into angel_cake (cakename,caketype,price,supplier,amount) values('莫波尔甜馨','千层蛋糕',19,'维卡塔荷糕点屋',100);

update angel_cake set cakename='佩梯歌意' where acid =1;	


select acid , cakename ,caketype , price , supplier , amount from angel_cake where cakename like '%卡%' or supplier like '%卡%' or caketype like '%卡%';	

select acid,cakename,caketype,price,supplier,amount from angel_cake group by caketype;

select distinct caketype from angel_cake;


select caketype from angel_cake where acid=4;



#领事
create table consul(
	csid int(11) not null AUTO_INCREMENT comment 'ID',
	account varchar(20) not null UNIQUE comment '账号',
	cspassword varchar(32) not null comment '密码',
	primary key(csid)
) engine=innodb default charset=utf8;

insert into consul (account,cspassword) values ('admin1234','1234');

select csid,account,cspassword from consul;

select csid,account,cspassword from consul where account='admin1234';


update angel_cake set cakename='',caketype='',price=,supplier='',amount= where acid=;


select acid,cakename,caketype,price,supplier,amount from angel_cake where cakename='ufo';


delete from angel_cake where acid=9;

select acid,cakename,caketype,price,supplier from angel_cake where FIND_IN_SET(acid,'1,2,3');

select acid,cakename,caketype,price,supplier from angel_cake where acid in(1,2,4);


alter table cake_cart change cuid cuid int(20) not null;

update angel_cake set amount=99 where acid in(2,3);

update angel_cake set amount=150 where acid in(1,2,3,4);

update angel_cake set amount = case acid when 1 then 1000 when 2 then 900 end where acid in (1,2);


delete from cake_cart where ccid in (33,34) and cuid=9;


## 用户表增加`年龄(出生年)`,`性别(3种)`,`电话(char)`
alter table customer add birth year comment '出生年';
alter table customer add gender int(3) comment '0-保密,1-男,2-女';
alter table customer add phone Char(30) NOT NULL comment '电话';

alter table customer add cancel_status int(3) NOT NULL comment '注销状态,0注销,1未注销';
alter table customer add portrait Char(99) comment '肖像';

alter table customer change birth birth date comment '出生年月日';

###修改蛋糕据ACId
update angel_cake set cakename='米卡提',caketype='奶酪蛋糕',price=23,supplier='艾赫瓦妮莎',amount=636 where acid=6;

update angel_cake set amount=99 where acid = 15;

alter table customer change portrait address varchar(60) comment '地址';

alter table customer alter column gender set default 0;

update customer set birth='2019-01-01' where id=1;

update customer set name='pwd111',gender=2 where id=3;

alter table customer alter column cancel_status set default 1;

select count(*) from customer where gender=0;

select count(id) from customer where gender=2;

select count(id) from customer where phone='200200';


#分页查询
select * from customer limit 1,4;

select id,name from customer order by id asc;

#
update customer set cancel_status=0 where id in(17,18);

select cuid,count(cuid) from cake_cart group by cuid;

#
select cuid,count(cuid) cc from cake_cart group by cuid order by cuid asc;

##
select count(acid) from angel_cake where cakename='哈形宫廷款';