drop table if exists PRD_PRODUCT;

/*==============================================================*/
/* Table: PRD_PRODUCT3                                          */
/*==============================================================*/
create table PRODUCT
(
   ID          bigint not null auto_increment,
   PRODUCT_NM           varchar(255) not null,
   SELLING_POINT        varchar(255),
   PRODUCT_CODING       varchar(128) not null,
   PRODUCT_TAG          varchar(255) not null,
   IS_ON_SALE           enum('Y','N') not null,
   MARKET_PRICE         double not null,
   PRODUCT_DESCR        longblob not null,
   IS_DELETE            enum('Y','N') not null,
   POSITION             int not null,
   SALES_VOLUME         int not null,
   LAST_ON_SALE_DATE    timestamp,
   LAST_MOD_TIME        timestamp not null,
   IS_INSTALLMENT       enum('Y','N'),
   IS_SUPPORT_COD       enum('Y','N'),
   primary key (ID)
);
