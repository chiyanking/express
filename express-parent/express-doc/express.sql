CREATE database express character set 'utf8';
USE express;
create table company
(
  id bigint auto_increment comment '快递Id'
    primary key,
  code varchar(255) null comment '快递编号',
  name varchar(255) null comment '快递名称'
)
;

create table exp_model
(
  id bigint not null
    primary key,
  company_id bigint null comment '快递公司id',
  exp_model_name varchar(64) null comment '快递单子模板名称',
  exp_model_url varchar(128) null comment '模板路径',
  exp_model_status int(1) null comment '状态 0：正常  1：废除状态'
)
;

create table express
(
  id bigint not null
    primary key,
  exp_no varchar(20) null comment '快递单号',
  trade_no varchar(50) null comment '订单号',
  point_id bigint null comment '门店id',
  date datetime null comment '寄件时间',
  status tinyint(1) null comment '快递单状态',
  price decimal(10,4) null comment '快递价格',
  weight double null comment '包裹重量',
  volume varchar(200) null,
  company_id bigint null comment '寄件所使用快递Id',
  company_name varchar(50) null,
  exp_model_id bigint null comment '快递模板Id',
  pay_type int(1) null comment '支付方式： 1-现付，2-到付，3-月结，4-第三方支付',
  goods_type varchar(64) null comment '物品类型,保存格式如下   类型1，类型2，类型3',
  goods_count int(2) null comment '物品数量',
  remark varchar(255) null comment '备注信息',
  sender_id bigint null comment '寄件人Id',
  sender_company varchar(100) null,
  sender_name varchar(10) null comment '发件人姓名',
  sender_phone varchar(15) null,
  sender_province_id bigint null,
  sender_province_code bigint null,
  sender_province_name varchar(50) null,
  sender_city_id bigint null,
  sender_city_code bigint null,
  sender_city_name varchar(50) null,
  sender_district_id bigint null,
  sender_district_code bigint null,
  sender_district_name varchar(50) null,
  sender_address varchar(100) null,
  receiver_id bigint null,
  receiver_company varchar(100) null,
  receiver_name varchar(10) null comment '发件人姓名',
  receiver_phone varchar(15) null,
  receiver_province_id bigint null,
  receiver_province_code bigint null,
  receiver_province_name varchar(50) null,
  receiver_city_id bigint null,
  receiver_city_code bigint null,
  receiver_city_name varchar(50) null,
  receiver_district_id bigint null,
  receiver_district_code bigint null,
  receiver_district_name varchar(50) null,
  receiver_address varchar(100) null
)
  comment '快递信息'
;

create table express_item
(
  id bigint auto_increment comment '物品Id'
    primary key,
  item_name varchar(255) null comment '物品名称',
  item_num int null comment '数量',
  item_wight int null comment '物品重量',
  exp_id bigint null comment '快递Id'
)
  comment '快递物品'
;

create table express_trace
(
  id bigint auto_increment
    primary key,
  express_id bigint null,
  status int null,
  date date null,
  discribe varchar(1000) null
)
  comment '物流信息跟踪'
;

create table point
(
  id bigint not null
    primary key,
  name varchar(255) null comment '门店名称',
  phone varchar(20) null comment '手机号',
  password varchar(32) null comment '（手机号作为账号）登陆密码',
  province_id bigint null comment '门店所在的省份',
  province_code bigint default '20' null,
  city_id bigint null comment '门店所在市级Id',
  city_code bigint default '20' null,
  status int(1) default '0' null comment '门店状态',
  district_id bigint null comment '门店所在区域',
  district_code bigint null,
  address varchar(255) null comment '详细地址'
)
;

create table point_company_relation
(
  id bigint not null
    primary key,
  point_id bigint null comment '门店ID',
  point_name varchar(50) null comment '网点名称',
  company_id bigint null comment '快递公司Id',
  company_name varchar(50) null comment '快递公司名称',
  password varchar(50) null comment '电子面单密码',
  account varchar(100) null comment '电子面单账号',
  is_electronic tinyint null comment '是否电子面单	',
  exp_model_id bigint null comment '默认模板的id',
  exp_model_name varchar(255) null comment '模版名称',
  constraint point_company_relation_point_id_company_id_uindex
  unique (point_id, company_id)
)
;

create table region_city
(
  id bigint auto_increment comment '主键ID'
    primary key,
  name varchar(50) not null comment '城市名称',
  code bigint null comment '城市code',
  parent_code bigint null comment '上级code'
)
  comment '行政区域：城市'
;

create table region_district
(
  id bigint auto_increment comment '主键ID'
    primary key,
  name varchar(50) not null comment '区域名称',
  code bigint null comment '区域code',
  parent_code bigint null comment '上级code'
)
  comment '行政区域：区域'
;

create table region_province
(
  id bigint auto_increment comment '主键ID'
    primary key,
  name varchar(30) not null comment '省份名称',
  code bigint null comment '省份code'
)
  comment '行政区域：省份'
;

create table user
(
  id bigint not null
    primary key,
  name varchar(255) null,
  phone varchar(15) null,
  app_id bigint null comment '微信认证中心',
  province_id bigint null,
  province_code bigint null,
  province_name varchar(50) null,
  city_id bigint null,
  city_code bigint null,
  city_name varchar(50) null,
  district_id bigint null,
  district_code bigint null,
  district_name varchar(50) null,
  address varchar(100) null,
  last_send_date datetime null comment '最后寄件时间',
  send_times int default '1' null comment '寄件次数',
  last_receive_date datetime null comment '最后收件时间',
  receive_times int default '1' null comment '收件次数'
)
;

