--테이블 삭제

drop table prod_orders;
drop table prod_members;
drop table prod_sale;
drop table prod_manage;


--테이블 생성

CREATE TABLE prod_manage (
  prod_no VARCHAR2(10) primary key,
  prod_name VARCHAR2(20) not null,
  prod_desc VARCHAR2(200),
  prod_date VARCHAR2(20),
  price NUMBER(10),
  indoor NUMBER(10),
  stock NUMBER(10));

CREATE TABLE prod_sale (
  prod_no VARCHAR2(10),
  prod_name VARCHAR2(20) not null,
  prod_desc VARCHAR2(200),
  sell_unit NUMBER(10),
  CONSTRAINT tbl_psale_sell_no_fk FOREIGN KEY (prod_no)
    REFERENCES prod_manage(prod_no));

CREATE TABLE prod_members (
  user_id VARCHAR2(10) primary key,
  user_pw VARCHAR2(10) not null,
  user_name VARCHAR2(20) not null,
  phone VARCHAR2(20) not null,
  address VARCHAR2(100) not null,
  gen_date DATE default sysdate,
  is_manager VARCHAR2(1));

CREATE TABLE prod_orders (
  user_id VARCHAR2(10),
  order_no VARCHAR2(20) not null,
  order_date DATE default sysdate,
  order_list VARCHAR2(300),
  CONSTRAINT tbl_porder_user_id_fk FOREIGN KEY (user_id)
    REFERENCES prod_members(user_id));


--시퀀스 생성

CREATE SEQUENCE prod_seq
       INCREMENT BY 1
       START WITH 10000001
       MINVALUE 10000001
       MAXVALUE 99999999;

DROP SEQUENCE prod_seq;


--테이블 조회

select *
from prod_manage
order by prod_no;

select *
from prod_sale
order by prod_no;

select *
from prod_members
order by user_id;

select *
from prod_orders
order by user_id;


--데이터 입력(테스트 용)

insert into prod_manage (
            prod_no, prod_name, prod_desc, prod_date, price, indoor, stock)
values (prod_seq.NEXTVAL, '사과', '영주 제철 사과', '2024-07', 13000, 100, 200);

insert into prod_manage (
            prod_no, prod_name, prod_desc, prod_date, price, indoor, stock)
values (prod_seq.NEXTVAL, '복숭아', '영천 친환경 복숭아', '2024-06', 10000, 50, 150);

insert into prod_sale (
            prod_no, prod_name, prod_desc, sell_unit)
values ('10000001', '사과', '영주 제철 사과', 5);

insert into prod_sale (
            prod_no, prod_name, prod_desc, sell_unit)
values ('10000002', '복숭아', '영천 친환경 복숭아', 5);

insert into prod_members (
            user_id, user_pw, user_name, phone, address, is_manager)
values ('admin', 'admin', '관리자', '010-0101-1234', '대구 100', '1');

insert into prod_members (
            user_id, user_pw, user_name, phone, address, is_manager)
values ('user01', '1234', '홍길동', '010-1111-2222', '서울 101', '0');

insert into prod_members (
            user_id, user_pw, user_name, phone, address, is_manager)
values ('user02', '2345', '최우수', '010-2222-3333', '대전 201', '0');

insert into prod_orders (
            user_id, order_no, order_list)
values ('user01', '240709143311', '사과 5Kg 1개');


commit;




CREATE SEQUENCE prod_seq
       INCREMENT BY 1
       START WITH 10000001
       MINVALUE 10000001
       MAXVALUE 99999999;

DROP SEQUENCE prod_seq;
      


       
       
       
