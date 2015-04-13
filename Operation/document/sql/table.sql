-----------------------------------------------------
-- Export file for user BMP                        --
-- Created by Administrator on 2011/6/29, 17:03:34 --
-----------------------------------------------------

spool table.log

prompt
prompt Creating table NOTIFY_INFO
prompt ==========================
prompt
create table NOTIFY_INFO
(
  ID             NUMBER not null,
  MERCHANT_CODE  VARCHAR2(15) not null,
  NOTIFY_SUCCESS NUMBER not null,
  REMARKS        VARCHAR2(100),
  NOTIFY_TIME    DATE not null,
  NOTIFY_COUNT   NUMBER
)
;
alter table NOTIFY_INFO
  add constraint PK_NOTIFY_INFO primary key (ID);

prompt
prompt Creating table OPERATION_LOG
prompt ============================
prompt
create table OPERATION_LOG
(
  ID             NUMBER not null,
  OP_TIME        DATE,
  OP_ID          NUMBER,
  OP_NAME        VARCHAR2(20),
  OP_MOBILE      VARCHAR2(11),
  OP_DESCRIPTION VARCHAR2(200)
)
;
comment on table OPERATION_LOG
  is '��������־��';
alter table OPERATION_LOG
  add constraint PK_OPERATION_LOG primary key (ID);

prompt
prompt Creating table SALEPLOY_AGENT_INFO
prompt ==================================
prompt
create table SALEPLOY_AGENT_INFO
(
  ID     NUMBER not null,
  SIGNER VARCHAR2(32),
  OPER   VARCHAR2(32)
)
;
comment on table SALEPLOY_AGENT_INFO
  is 'Ӫ���������Ϣ��';
alter table SALEPLOY_AGENT_INFO
  add constraint PK_SALEPLOY_AGENT_INFO primary key (ID);

prompt
prompt Creating table SYS_AUTHORITY
prompt ============================
prompt
create table SYS_AUTHORITY
(
  ID          NUMBER not null,
  AUTHNAME    VARCHAR2(32) not null,
  DESCRIPTION VARCHAR2(255)
)
;
comment on table SYS_AUTHORITY
  is 'Ȩ�ޱ�';
comment on column SYS_AUTHORITY.ID
  is '����';
comment on column SYS_AUTHORITY.AUTHNAME
  is 'Ȩ������';
comment on column SYS_AUTHORITY.DESCRIPTION
  is '����';
alter table SYS_AUTHORITY
  add constraint PK_SYS_AUTHORITY primary key (ID);
alter table SYS_AUTHORITY
  add constraint UK_SYS_AUTHORITY unique (AUTHNAME);

prompt
prompt Creating table SYS_MENU
prompt =======================
prompt
create table SYS_MENU
(
  ID       NUMBER not null,
  MENUNAME VARCHAR2(32) not null,
  URL      VARCHAR2(100),
  ORDERNO  NUMBER default 0 not null,
  PARENTID NUMBER
)
;
comment on table SYS_MENU
  is '�˵���';
comment on column SYS_MENU.ID
  is '����';
comment on column SYS_MENU.MENUNAME
  is '�˵���ʾ����';
comment on column SYS_MENU.URL
  is '����';
comment on column SYS_MENU.ORDERNO
  is '��ʾ˳��';
comment on column SYS_MENU.PARENTID
  is '���˵�id';
alter table SYS_MENU
  add constraint PK_SYS_MENU primary key (ID);
alter table SYS_MENU
  add constraint FK_SYS_MENU foreign key (PARENTID)
  references SYS_MENU (ID);

prompt
prompt Creating table SYS_AUTH_MENU
prompt ============================
prompt
create table SYS_AUTH_MENU
(
  AUTH_ID NUMBER not null,
  MENU_ID NUMBER not null
)
;
comment on table SYS_AUTH_MENU
  is 'Ȩ�޲˵�������';
alter table SYS_AUTH_MENU
  add constraint FK_SYS_AUTH_MENU_TO_AUTH foreign key (AUTH_ID)
  references SYS_AUTHORITY (ID);
alter table SYS_AUTH_MENU
  add constraint FK_SYS_AUTH_MENU_TO_MENU foreign key (MENU_ID)
  references SYS_MENU (ID);

prompt
prompt Creating table SYS_RESOURCE
prompt ===========================
prompt
create table SYS_RESOURCE
(
  ID           NUMBER not null,
  RESNAME      VARCHAR2(20) not null,
  TYPE         VARCHAR2(10) not null,
  FILTERSTRING VARCHAR2(255) not null
)
;
comment on table SYS_RESOURCE
  is '��Դ��';
comment on column SYS_RESOURCE.ID
  is '����';
comment on column SYS_RESOURCE.RESNAME
  is '��Դ����';
comment on column SYS_RESOURCE.TYPE
  is '���ͣ�URL��METHOD';
comment on column SYS_RESOURCE.FILTERSTRING
  is '��Դ��';
alter table SYS_RESOURCE
  add constraint PK_SYS_RESOURCE primary key (ID);
alter table SYS_RESOURCE
  add constraint UK_SYS_RESOURCR unique (RESNAME);

prompt
prompt Creating table SYS_AUTH_RES
prompt ===========================
prompt
create table SYS_AUTH_RES
(
  AUTH_ID NUMBER not null,
  RES_ID  NUMBER not null
)
;
comment on table SYS_AUTH_RES
  is 'Ȩ����Դ������';
alter table SYS_AUTH_RES
  add constraint FK_SYS_AUTH_RES_TO_AUTH foreign key (AUTH_ID)
  references SYS_AUTHORITY (ID);
alter table SYS_AUTH_RES
  add constraint FK_SYS_AUTH_RES_TO_RES foreign key (RES_ID)
  references SYS_RESOURCE (ID);

prompt
prompt Creating table SYS_OPERATE_LOG
prompt ==============================
prompt
create table SYS_OPERATE_LOG
(
  ID          NUMBER not null,
  USERID      NUMBER,
  TIME        DATE,
  NAME        VARCHAR2(128),
  DESCRIPTION VARCHAR2(255),
  FLAG        NUMBER(1),
  LOGINNAME   VARCHAR2(32)
)
;
comment on table SYS_OPERATE_LOG
  is '����Ա������־��';
comment on column SYS_OPERATE_LOG.ID
  is '����';
comment on column SYS_OPERATE_LOG.USERID
  is '�û�id';
comment on column SYS_OPERATE_LOG.TIME
  is '����ʱ��';
comment on column SYS_OPERATE_LOG.NAME
  is '��������';
comment on column SYS_OPERATE_LOG.DESCRIPTION
  is '����������������¼������Ϣ��';
comment on column SYS_OPERATE_LOG.FLAG
  is '�����ʶ0���ɹ�1��ʧ��';
comment on column SYS_OPERATE_LOG.LOGINNAME
  is '�����ĵ�¼�û���';
alter table SYS_OPERATE_LOG
  add constraint PK_SYS_OPERATE_LOG primary key (ID);

prompt
prompt Creating table SYS_OPERATE_LOG_PARAM
prompt ====================================
prompt
create table SYS_OPERATE_LOG_PARAM
(
  ID    NUMBER not null,
  KEY   VARCHAR2(128),
  VALUE VARCHAR2(255),
  LOGID NUMBER
)
;
comment on table SYS_OPERATE_LOG_PARAM
  is '������־������';
comment on column SYS_OPERATE_LOG_PARAM.ID
  is '����';
comment on column SYS_OPERATE_LOG_PARAM.KEY
  is '��������';
comment on column SYS_OPERATE_LOG_PARAM.VALUE
  is '����ֵ';
comment on column SYS_OPERATE_LOG_PARAM.LOGID
  is '��־id';
alter table SYS_OPERATE_LOG_PARAM
  add constraint PK_SYS_OPERATE_LOG_PARAM primary key (ID);
alter table SYS_OPERATE_LOG_PARAM
  add constraint FK_SYS_OPERATE_LOG_PARAM foreign key (LOGID)
  references SYS_OPERATE_LOG (ID);

prompt
prompt Creating table SYS_ROLE
prompt =======================
prompt
create table SYS_ROLE
(
  ID          NUMBER not null,
  ROLENAME    VARCHAR2(32) not null,
  DESCRIPTION VARCHAR2(255)
)
;
comment on table SYS_ROLE
  is '��ɫ��';
comment on column SYS_ROLE.ID
  is '����';
comment on column SYS_ROLE.ROLENAME
  is '��ɫ����';
comment on column SYS_ROLE.DESCRIPTION
  is '����';
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ID);
alter table SYS_ROLE
  add constraint UK_SYS_ROLE unique (ROLENAME);

prompt
prompt Creating table SYS_ROLE_AUTH
prompt ============================
prompt
create table SYS_ROLE_AUTH
(
  ROLE_ID NUMBER not null,
  AUTH_ID NUMBER not null
)
;
comment on table SYS_ROLE_AUTH
  is '��ɫȨ�޹�����';
alter table SYS_ROLE_AUTH
  add constraint FK_SYS_ROLE_AUTH_TO_AUTH foreign key (AUTH_ID)
  references SYS_AUTHORITY (ID);
alter table SYS_ROLE_AUTH
  add constraint FK_SYS_ROLE_AUTH_TO_ROLE foreign key (ROLE_ID)
  references SYS_ROLE (ID);

prompt
prompt Creating table SYS_USER
prompt =======================
prompt
create table SYS_USER
(
  ID           NUMBER not null,
  USERNAME     VARCHAR2(32) not null,
  PASSWORD     VARCHAR2(32) not null,
  REALNAME     VARCHAR2(32),
  MOBILE       VARCHAR2(32),
  EMAIL        VARCHAR2(100),
  STATUS       NUMBER(1) not null,
  LATESTLOGIN  DATE,
  SALT         VARCHAR2(32),
  PROVINCECODE VARCHAR2(6)
)
;
comment on table SYS_USER
  is '�û���';
comment on column SYS_USER.ID
  is '����';
comment on column SYS_USER.USERNAME
  is '��¼��';
comment on column SYS_USER.PASSWORD
  is '����';
comment on column SYS_USER.REALNAME
  is '��ʵ����';
comment on column SYS_USER.MOBILE
  is '�ֻ�';
comment on column SYS_USER.EMAIL
  is '�����ʼ�';
comment on column SYS_USER.STATUS
  is '״̬:1,��Ч;0,��Ч';
comment on column SYS_USER.LATESTLOGIN
  is '���һ�ε�¼ʱ��';
comment on column SYS_USER.SALT
  is '������������';
alter table SYS_USER
  add constraint PK_SYS_USER primary key (ID);
alter table SYS_USER
  add constraint UK_SYS_USER unique (USERNAME);

prompt
prompt Creating table SYS_USER_ROLE
prompt ============================
prompt
create table SYS_USER_ROLE
(
  USER_ID NUMBER not null,
  ROLE_ID NUMBER not null
)
;
comment on table SYS_USER_ROLE
  is '�û���ɫ������';
alter table SYS_USER_ROLE
  add constraint FK_SYS_USER_ROLE_TO_ROLE foreign key (ROLE_ID)
  references SYS_ROLE (ID);
alter table SYS_USER_ROLE
  add constraint FK_SYS_USER_ROLE_TO_USER foreign key (USER_ID)
  references SYS_USER (ID);

prompt
prompt Creating sequence SEQ_NOTIFY_INFO
prompt =================================
prompt
create sequence SEQ_NOTIFY_INFO
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_OPERATION_LOG
prompt ===================================
prompt
create sequence SEQ_OPERATION_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SALEPLOY_AGENT_INFO
prompt =========================================
prompt
create sequence SEQ_SALEPLOY_AGENT_INFO
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_AUTHORITY
prompt ===================================
prompt
create sequence SEQ_SYS_AUTHORITY
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_MENU
prompt ==============================
prompt
create sequence SEQ_SYS_MENU
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_OPERATE_LOG
prompt =====================================
prompt
create sequence SEQ_SYS_OPERATE_LOG
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_OPERATE_LOG_PARAM
prompt ===========================================
prompt
create sequence SEQ_SYS_OPERATE_LOG_PARAM
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_RESOURCE
prompt ==================================
prompt
create sequence SEQ_SYS_RESOURCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_ROLE
prompt ==============================
prompt
create sequence SEQ_SYS_ROLE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_SYS_USER
prompt ==============================
prompt
create sequence SEQ_SYS_USER
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 20;


spool off
