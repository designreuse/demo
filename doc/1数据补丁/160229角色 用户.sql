
drop table if exists SYS_USER;

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
   ID          bigint not null auto_increment,
   LOGIN_ID             varchar(32) not null,
   IS_ADMIN             enum('Y','N'),
   USER_NAME            varchar(48) not null,
   USER_PSW             varchar(32) not null,
   USER_MOBILE          varchar(24),
   USER_TEL             varchar(24),
   USER_SEX_CODE        enum('0','1','2') not null,
   USER_EMAIL           varchar(32),
   REGISTER_DATE        timestamp default '0000-00-00 00:00:00',
      REG_ORG_ID           int,
   USER_ICON            varchar(60),
   IS_DELETE            enum('Y','N') not null,
   LAST_PSW_MODIFY_TIME time not null,
   LAST_BUY_TIME        timestamp not null,
    CREATE_BY            CHAR(32)             ,
   CREATE_DATE          TIMESTAMP            ,
   UPDATE_BY            CHAR(32),
   UPDATE_DATE          TIMESTAMP,
   LAST_MODIFIED_BY     TIMESTAMP,
   LAST_MODIFIED_DATE   time,
   primary key (ID)
)
drop table if exists SYS_ROLE;

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE
(
   ID          bigint not null auto_increment,
   SYS_ROLE_NM          varchar(32) not null,
   ROLE_TYPE_CODE       enum('0','1') not null,
   POSITION             int not null,
   SCORE                int not null,
   LAST_MODIFIED_BY     varchar(32),
   LAST_MODIFIED_DATE   time,
   CREATE_DATE          TIMESTAMP,
    CREATE_BY            CHAR(32)             ,
   primary key (ID)
)

drop table if exists SYS_USER_ROLE_RELATIONSHIP;
/*==============================================================*/
/* Table: SYS_USER_ROLE_RELATIONSHIP                            */
/*==============================================================*/
CREATE TABLE SYS_USER_ROLE_RELATIONSHIP
(
   ID bigint NOT NULL AUTO_INCREMENT,
   SYS_USER_ID          INT NOT NULL,
   SYS_ROLE_ID          INT NOT NULL,

   LAST_MODIFIED_BY     varchar(32),
   LAST_MODIFIED_DATE   time,
   CREATE_DATE          TIMESTAMP,
   CREATE_BY            CHAR(32)             ,
   PRIMARY KEY (ID)
)



