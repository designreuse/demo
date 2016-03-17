DROP TABLE IF EXISTS SHOPPING_CART;

/*==============================================================*/
/* Table: PRD_PRODUCT3                                          */
/*==============================================================*/
CREATE TABLE SHOPPING_CART_LIST
(
   ID          BIGINT NOT NULL AUTO_INCREMENT,
   USER_ID          BIGINT NOT NULL,
   PRODUCT_ID           BIGINT ,
   QUANTITY         INT ,
   CREATE_DATE DATE ,
   LAST_MODIFIED_DATE DATE ,
   LAST_MODIFIED_BY DATE ,
   IS_DELETE VARCHAR (1),
   PRIMARY KEY (ID)
);
