
/**
 *新增
**/
--新增商户分类表
/*==============================================================*/
/* Table: MERCHANT_CATEGORY                                     */
/*==============================================================*/
create table MERCHANT_CATEGORY  (
   ID                   NUMBER                          not null,
   NAME                 VARCHAR2(100)                   not null,
   PARENT_ID            NUMBER,
   constraint PK_MERCHANT_CATEGORY primary key (ID)
);
comment on table MERCHANT_CATEGORY is
'商户分类';
comment on column MERCHANT_CATEGORY.ID is
'ID';
comment on column MERCHANT_CATEGORY.NAME is
'区域名称';
comment on column MERCHANT_CATEGORY.PARENT_ID is
'父Id';

alter table MERCHANT_CATEGORY
   add constraint FK_MER_CAT_REF_MER_CAT foreign key (PARENT_ID)
      references MERCHANT_CATEGORY (ID);


create sequence SEQ_MERCHANT_CATEGORY
increment by 1
start with 10
 maxvalue 9999999
 minvalue 1
 cache 20;

      
/**
 * 商户分类初始数据
 */
INSERT INTO MERCHANT_CATEGORY(ID,NAME) values(0,'ROOT');
INSERT INTO MERCHANT_CATEGORY(ID,PARENT_ID,NAME) values(1,0,'餐饮美食');
INSERT INTO MERCHANT_CATEGORY(ID,PARENT_ID,NAME) values(2,0,'休闲娱乐');
INSERT INTO MERCHANT_CATEGORY(ID,PARENT_ID,NAME) values(3,0,'美容保健');
INSERT INTO MERCHANT_CATEGORY(ID,PARENT_ID,NAME) values(4,0,'运动健身');
INSERT INTO MERCHANT_CATEGORY(ID,PARENT_ID,NAME) values(5,0,'其他');

 
/**
 * 商户排序表
 */

create table MERCHANT_RECOMMEND  (
   ID                   NUMBER                          not null,
   SORT_ID              NUMBER,
   CUR_DATE             DATE                           default SYSDATE not null,
   constraint PK_MERCHANT_RECOMMEND primary key (ID)
);

/** 修改商户信息基本表  **/
alter table MERCHANT
add (
	CATEGORY_ID number,
	LATITUDE varchar2(50),
	LONGITUDE varchar2(50),
	HEAD varchar2(100),
	INFO varchar2(2000),
	SALES VARCHAR2(30),
	RECOMMEND_ID number,
	NUM NUMBER,
	POS_BUESSINESS_TYPE NUMBER DEFAULT 0,
	DISCOUNT_FLAG NUMBER DEFAULT 1
);

alter table MERCHANT
MODIFY BUSINESS_ADDRESS varchar2(120);

alter table MERCHANT_EXTEND_INFO
MODIFY REGIST_ADDRESS varchar2(120);
alter table MERCHANT_EXTEND_INFO
MODIFY BILL_ADDRESS varchar2(120);

alter table TRADE_ORDER 
MODIFY MONEY NUMBER(10,2);

alter table TRADE_DETAIL modify AMOUNT NUMBER(10,2);

alter table MERCHANT
   add constraint FK_MERCHANT_RECOMMEND foreign key (RECOMMEND_ID)
      references MERCHANT_RECOMMEND (ID);
alter table MERCHANT
   add constraint FK_MERCHANT_CATEGORY foreign key (CATEGORY_ID)
      references MERCHANT_CATEGORY (ID);
      
UPDATE MERCHANT SET area_id=2224, CATEGORY_ID = 2,LATITUDE='114.025974',LONGITUDE='22.546037',POS_BUESSINESS_TYPE=3;


/**
 * 插入排序数据同时更新商户的RECOMMEND_ID
 */
--为 merchant_recommend表插入数据
INSERT INTO MERCHANT_RECOMMEND(ID,SORT_ID) SELECT ID,1 FROM MERCHANT WHERE RECOMMEND_ID is null;
--为 merchant 表插入外键数据
UPDATE MERCHANT SET RECOMMEND_ID=ID WHERE RECOMMEND_ID IS NULL;

UPDATE MERCHANT SET NUM=ID;

--为 merchant_recommend创建sequence，需要注意的是，开始值需要大于merchant的ID号当前的最大值
	create sequence SEQ_MER_RECOMMEND
	increment by 1
	start with 5000
	maxvalue 9999999
	cache 20
	nocycle;

 




/*增加用户表字段：头像、登录验证码、验证码创建时间、验证码创建次数*/
ALTER TABLE USERS
ADD(
	HEAD VARCHAR2(100),
	SECURITY_CODE VARCHAR2(10),
	CODE_CREATE_DATE DATE,
	CODE_CREATE_COUNT NUMBER default 0,
	SOURCE NUMBER
);
update users set source=1;
alter table users
modify source number not null;
alter table users
MODIFY NIKE_name varchar2(50);

alter table USERS 
modify EMAIL varchar2(50);
 

/** 会员表，新增会员来源 **/
ALTER TABLE MEMBER
ADD (SOURCE NUMBER);

UPDATE MEMBER SET SOURCE=1;

ALTER TABLE MEMBER
MODIFY SOURCE NUMBER NOT NULL;


/** 会员积分表：增加会员积分变更来源商户字段 **/
ALTER TABLE MEMBER_POINT_DETAIL
ADD MERCHANT_ID NUMBER;

UPDATE MEMBER_POINT_DETAIL MP SET MERCHANT_ID = (
   select O.MERCHANT_ID from  trade_order O WHERE MP.TRADE_ORDER_ID = O.ID  
);


UPDATE MEMBER_POINT_DETAIL MP SET MERCHANT_ID=(
	   SELECT MERCHANT_ID  FROM MEMBER M WHERE M.ID=MP.MEMBER_ID AND MP.TYPE IN(3,4,5)
);




/** 增加SNS网站关联账号表 **/

/*==============================================================*/
/* Table: RELATION_ACCOUNT                                      */
/*==============================================================*/
create table RELATION_ACCOUNT  (
   ID                   NUMBER                          not null,
   USER_ID              NUMBER                          not null,
   TYPE                 NUMBER                          not null,
   AUTHENTICATION_ENTRY VARCHAR2(500)                   not null,
   ENABLE               NUMBER                         default 1 not null,
   CREATE_DATE          DATE                            not null,
   constraint PK_RELATION_ACCOUNT primary key (ID)
);

comment on column RELATION_ACCOUNT.TYPE is
'关联帐号类型
1、新浪帐号
2、腾讯帐号';

/*==============================================================*/
/* Index: REL_ACCOUNT_UN                                        */
/*==============================================================*/
create unique index REL_ACCOUNT_UN on RELATION_ACCOUNT (
   USER_ID ASC,
   TYPE ASC,
   AUTHENTICATION_ENTRY ASC
);

alter table RELATION_ACCOUNT
   add constraint FK_RELATION_REFERENCE_USERS foreign key (USER_ID)
      references USERS (ID);



create sequence SEQ_RELATION_ACCOUNT
increment by 1
start with 1000
 maxvalue 999999999
 minvalue 1
 cache 20;



/**增加公告表：公告类型**/
ALTER TABLE BORDER
ADD TYPE NUMBER;

update BORDER set TYPE=2;


/**增加用户收藏商户表**/
/*==============================================================*/
/* Table: USER_FAV_MERCHANT                                     */
/*==============================================================*/
create table USER_FAV_MERCHANT  (
   ID                   NUMBER                          not null,
   USER_ID              NUMBER                          not null,
   MERCHANT_ID          NUMBER                          not null,
   CREATE_DATE          DATE                            not null,
   constraint PK_USER_FAV_MERCHANT primary key (ID)
);

/*==============================================================*/
/* Index: USR_FAV_UN                                            */
/*==============================================================*/
create unique index USR_FAV_UN on USER_FAV_MERCHANT (
   USER_ID ASC,
   MERCHANT_ID ASC
);

alter table USER_FAV_MERCHANT
   add constraint FK_USER_FAV_REF_MERCHANT foreign key (MERCHANT_ID)
      references MERCHANT (ID);

alter table USER_FAV_MERCHANT
   add constraint FK_USER_FAV_REF_USERS foreign key (USER_ID)
      references USERS (ID);
      
drop sequence SEQ_USER_FAV_MERCHANT;

create sequence SEQ_USER_FAV_MERCHANT
increment by 1
start with 1000
 maxvalue 9999999
 cache 20
nocycle;


/**增加商户相关统计表：四颗星、五颗星数、商户被收藏数、商户消费数**/
ALTER TABLE MERCHANT_REFERENCE_STATISTIC
ADD(
	FOUR_GRADE NUMBER DEFAULT 0,
	FIVE_GRADE NUMBER DEFAULT 0,
	FAV_COUNT NUMBER DEFAULT 0,
	CONSUME_COUNT NUMBER DEFAULT 0,
	CREDIT_GRADE NUMBER 
);
ALTER TABLE MERCHANT_REFERENCE_STATISTIC
MODIFY(
	FOUR_GRADE NUMBER DEFAULT 0 NOT NULL,
	FIVE_GRADE NUMBER DEFAULT 0 NOT NULL,
	FAV_COUNT NUMBER DEFAULT 0 NOT NULL,
	CONSUME_COUNT NUMBER DEFAULT 0 NOT NULL
);



/**POS表：增加POS机序列号 **/
ALTER TABLE POS
ADD SERIAL_NUMBER VARCHAR2(20);

--暂时该值为暂时定义，后期需要根据实际情况变更
UPDATE POS SET SERIAL_NUMBER='075500' WHERE SERIAL_NUMBER IS NULL;
--需要李钦刚提供POS 的SERIAL_NUMBER

ALTER TABLE POS
MODIFY SERIAL_NUMBER VARCHAR2(20) NOT NULL;


/**商户会员组表：增加会员数字段**/
ALTER TABLE MEMBER_GROUP
ADD MEMBER_COUNT NUMBER default 0;

update MEMBER_GROUP g set MEMBER_COUNT=(SELECT COUNT(*) FROM MEMBER M WHERE M.MEMBER_GROUP_ID=g.ID);

ALTER TABLE MEMBER_GROUP
MODIFY MEMBER_COUNT NUMBER NOT NULL;



/**评论表：增加评论来源**/
ALTER TABLE MERCHANT_COMMENT
ADD SOURCE NUMBER;

update MERCHANT_COMMENT set SOURCE = 3;

/**商户扩展表：增加费率、首次合作赠送短信数**/
ALTER TABLE MERCHANT_EXTEND_INFO
ADD(
	RATING NUMBER(10,2),
	PRESENT_SMS_COUNT NUMBER default 0
);

/**17、评论表：加入是否修改评论字段**/
ALTER TABLE MERCHANT_COMMENT
ADD(
	MODIFYED NUMBER default 0
);

/*
*找新鲜
*/

CREATE TABLE WHATS_NEW
(
  DATETIME        DATE                          NOT NULL,
  MEMBER_ID       NUMBER                        NOT NULL,
  MERCHANT_ID     NUMBER                        NOT NULL,
  ID              NUMBER                        NOT NULL,
  COMMENT_ID      NUMBER,
  TRADE_ORDER_ID  NUMBER,
  TYPE            NUMBER                        NOT NULL
);

COMMENT ON COLUMN WHATS_NEW.TYPE IS '1、消费 2、点评';


CREATE UNIQUE INDEX PK_WHATS_NEW ON WHATS_NEW (ID);


ALTER TABLE WHATS_NEW ADD (
  CONSTRAINT PK_WHATS_NEW
 PRIMARY KEY (ID));


ALTER TABLE WHATS_NEW ADD (
  CONSTRAINT FK_WHATS_NEW_REF_TRADE_ORDER 
 FOREIGN KEY (TRADE_ORDER_ID) 
 REFERENCES TRADE_ORDER (ID));

ALTER TABLE WHATS_NEW ADD (
  CONSTRAINT FK_WHATS_NEW_REF_COMMENT 
 FOREIGN KEY (COMMENT_ID) 
 REFERENCES MERCHANT_COMMENT (ID));

ALTER TABLE WHATS_NEW ADD (
  CONSTRAINT FK_WHATS_NEW_REF_MEMBER 
 FOREIGN KEY (MEMBER_ID) 
 REFERENCES MEMBER (ID));

ALTER TABLE WHATS_NEW ADD (
  CONSTRAINT FK_WHATS_NEW_REF_MERCHANT 
 FOREIGN KEY (MERCHANT_ID) 
 REFERENCES MERCHANT (ID));
 
CREATE SEQUENCE SEQ_WHATS_NEW
  START WITH 1000
  MAXVALUE 9999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;

CREATE OR REPLACE PROCEDURE WHATS_NEW_PROC
is
begin
  delete from WHATS_NEW;
  insert into WHATS_NEW(ID, MEMBER_ID, MERCHANT_ID, COMMENT_ID, TRADE_ORDER_ID, TYPE, DATETIME)
    select SEQ_WHATS_NEW.NEXTVAL, MEMBER_ID, MERCHANT_ID, NULL, TRADE_ORDER_ID, 1, DATETIME from (
      select MEMBER_ID, MERCHANT_ID,ID as TRADE_ORDER_ID, TRADE_DATE as DATETIME
        from TRADE_ORDER where type is not null order by TRADE_DATE desc) where rownum <= 5;
  insert into WHATS_NEW(ID, MEMBER_ID, MERCHANT_ID, COMMENT_ID, TRADE_ORDER_ID, TYPE, DATETIME)
    select SEQ_WHATS_NEW.NEXTVAL, MEMBER_ID, MERCHANT_ID, COMMENT_ID, NULL, 2, DATETIME from (
      select MEMBER_ID, MERCHANT_ID,ID as COMMENT_ID, CREATE_DATE as DATETIME
        from MERCHANT_COMMENT order by CREATE_DATE desc) where rownum <= 5;
end;
/

/**
* SMS_RECORD表中新增sms_source
**/
ALTER TABLE SMS_RECORD
ADD SMS_SOURCE NUMBER DEFAULT 1;
ALTER TABLE SMS_RECORD
ADD METHOD NUMBER DEFAULT 0;

/*==============================================================*/
/* Table: MEMBER_IDEA_FEEDBACK                                  */
/*==============================================================*/
create table MEMBER_IDEA_FEEDBACK  (
   ID                   NUMBER                          not null,
   FEED_NAME            VARCHAR2(100),
   FEED_CONTENT         VARCHAR2(4000)                  not null,
   CREATE_USER          NUMBER,
   CREATE_DATE          DATE                           default SYSDATE not null,
   DEAL_STATUS          NUMBER                         default 0,
   constraint PK_MEMBER_IDEA_FEEDBACK primary key (ID)
);

create sequence SEQ_IDEA_FEEDBACK
increment by 1
start with 1000
maxvalue 9999999
cache 40
nocycle;

/**
 * 修改活动表
 */
ALTER TABLE SALE_PLOY
ADD(
	VALID_START_DATE DATE,
	VALID_END_DATE DATE,
	APPLY_MERCHANT VARCHAR2(300)
);

UPDATE SALE_PLOY SET VALID_START_DATE=TO_DATE('2011-07-01 00:00:00','yyyy-mm-dd hh24:mi:ss'),
VALID_END_DATE=TO_DATE('2011-08-15 23:59:59','yyyy-mm-dd hh24:mi:ss');



alter table SALE_PLOY
MODIFY VALID_START_DATE DATE not null;
alter table SALE_PLOY
MODIFY VALID_END_DATE DATE not null;

 
ALTER TABLE TEMP_SALE_PLOY
ADD(
	VALID_START_DATE DATE,
	VALID_END_DATE DATE,
	APPLY_MERCHANT VARCHAR2(300)
);





drop table MERCHANT_REPORT cascade constraints;

/*==============================================================*/
/* Table: MERCHANT_REPORT                                       */
/*==============================================================*/
create table MERCHANT_REPORT  (
   ID                   NUMBER                          not null,
   MERCHANT_ID          NUMBER                          not null,
   CREATE_DATE          DATE                            not null,
   PRE_DAY_ADD_MEMBER_COUNT NUMBER                          not null,
   TOTAL_MEMBER_COUNT   NUMBER                          not null,
   CASH_TRADE_MONEY     NUMBER(10,2)                    not null,
   CASH_TRADE_COUNT     NUMBER                          not null,
   CRAD_TRADE_MONEY     NUMBER(10,2)                    not null,
   CARD_TRADE_COUNT     NUMBER                          not null,
   PRE_DAY_ADD_SALE_PLOY_COUNT NUMBER                          not null,
   TOTAL_SALE_PLOY_COUNT NUMBER                          not null,
   SEND_SMS_COUNT       NUMBER                          not null,
   constraint PK_MERCHANT_REPORT primary key (ID)
);

comment on table MERCHANT_REPORT is
'商户统计日报表';

comment on column MERCHANT_REPORT.PRE_DAY_ADD_MEMBER_COUNT is
'会员当日新增数';

comment on column MERCHANT_REPORT.TOTAL_MEMBER_COUNT is
'totalMemberCount';

comment on column MERCHANT_REPORT.CASH_TRADE_COUNT is
'现金消费数';

comment on column MERCHANT_REPORT.SEND_SMS_COUNT is
'sendSmsCount';

drop sequence SEQ_MERCHANT_REPORT;

create sequence SEQ_MERCHANT_REPORT
increment by 1
start with 1
 maxvalue 99999999
 minvalue 1
 cache 20;
 
 alter table sms_record modify content    null;


 
 
 
 update pos set serial_number='A805800500000001' where code ='00000024';
update pos set serial_number='A806800600000001' where code ='00000025';
update pos set serial_number='A601600129067309' where code ='00000026';
update pos set serial_number='A601600129067308' where code ='00000027';
update pos set serial_number='A601600129067307' where code ='00000028';
update pos set serial_number='A601600129067309' where code ='00000029';
update pos set serial_number='A801800100000001' where code ='00000030';
update pos set serial_number='A801800100000002' where code ='00000031';
update pos set serial_number='A601600129067311' where code ='00000040';
update pos set serial_number='A601600129067323' where code ='00000041';
update pos set serial_number='A601600129067308' where code ='00000032';
update pos set serial_number='A601600129067305' where code ='00000039';
update pos set serial_number='A601600129067314' where code ='00000043';
update pos set serial_number='A601600129067307' where code ='00000044';
update pos set serial_number='A601600129067306' where code ='00000046';
update pos set serial_number='A601600129067327' where code ='00000047';
update pos set serial_number='A601600129067320' where code ='00000033';
update pos set serial_number='A801800100000003' where code ='00000034';
update pos set serial_number='A801800100000004' where code ='00000035';
update pos set serial_number='A601600129067321' where code ='00000036';
update pos set serial_number='A601600129067322' where code ='00000037';
update pos set serial_number='A601600129067310' where code ='00000038';
update pos set serial_number='A601600129067313' where code ='00000042';
update pos set serial_number='A601600129067326' where code ='00000048';
update pos set serial_number='A601600129067328' where code ='00000050';
update pos set serial_number='A601600129067324' where code ='00000045';
update pos set serial_number='A601600129067329' where code ='00000049';
update pos set serial_number='A801800100000005' where code ='00000051';
update pos set serial_number='A801800100000006' where code ='00000052';
update pos set serial_number='A601600129067333' where code ='00000053';
update pos set serial_number='A601600129067325' where code ='00000054';
update pos set serial_number='A601600129067304' where code ='00000055';
update pos set serial_number='A101100129003653' where code ='00000018';
update pos set serial_number='A101100129003654' where code ='00000019';
update pos set serial_number='A101100129003651' where code ='00000016';
update pos set serial_number='A101100129003652' where code ='00000017';
update pos set serial_number='A101100129003655' where code ='00000020';
update pos set serial_number='A802800200000001' where code ='00000021';
update pos set serial_number='A803800300000001' where code ='00000022';
update pos set serial_number='A804800400000001' where code ='00000023';
update pos set serial_number='A601600129067302' where code ='00000056';



