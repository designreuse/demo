drop table if exists PRD_PRODUCT;

/*==============================================================*/
/* Table: PRD_PRODUCT3                                          */
/*==============================================================*/
create table PRD_PRODUCT
(
   ID          bigint not null auto_increment,
   PRODUCT_NM           varchar(255) not null,
   SELLING_POINT        varchar(255),
   PRODUCT_CODING       varchar(128) not null,
   PRODUCT_TAG          varchar(255) not null,
   IMAGE_FILE           varchar(255) ,
   IS_ON_SALE           enum('Y','N') not null,
   MARKET_PRICE         double not null,
   PRODUCT_DESCR        longblob not null,
   IS_DELETE            enum('Y','N') not null,
   POSITION             int not null,/*排序*/
   SALES_VOLUME         int not null,/*销量*/
   LAST_ON_SALE_DATE    timestamp,/*最后 上架 日期*/
   LAST_MOD_TIME        timestamp not null,/*最后 修改 时间*/
   IS_INSTALLMENT       enum('Y','N'),/*是否 分期付款*/
   IS_SUPPORT_COD       enum('Y','N'),/*是否 支持 货到付款*/
   LAST_MODIFIED_BY     varchar(32),
   LAST_MODIFIED_DATE   time,
   CREATE_DATE          TIMESTAMP,
    CREATE_BY            CHAR(32)             ,
   primary key (ID)
);
