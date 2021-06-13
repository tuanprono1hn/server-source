create table tbl_category(
id_category number primary key,
category nvarchar2(1000) not null
);

create table tbl_product(
id_sp number primary key,
id_category number not null,
id_gallery number not null,
id_promo number not null,
id_cmt number not null,
prod_name nvarchar2(1000),
prod_link nvarchar2(2000),
prod_price number,
prod_color nvarchar2(1000),
prod_stoke number,
prod_released_date date,
status number);

create table tbl_user(
id_user number primary key,
username nvarchar2(1000),
password nvarchar2(2000),
dob date,
phone nvarchar2(20),
email nvarchar2(500),
address nvarchar2(1000),
firstname nvarchar2(1000),
lastname nvarchar2(1000)
);