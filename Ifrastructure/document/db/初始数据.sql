/*正式上线数据*/
INSERT INTO MERCHANT_CATEGORY(ID,NAME) values(1,'ROOT');

insert into resources  (id,path,description) values(1,'/manager/member/info/**','会员信息管理资源');
insert into resources  (id,path,description) values(2,'/manager/member/consume/**','会员消费查询资源');
insert into resources  (id,path,description) values(3,'/manager/member/level/**','会员等级管理资源');

insert into resources  (id,path,description) values(4,'/manager/saleploycreate/**','营销活动创建资源');
insert into resources  (id,path,description) values(5,'/manager/saleploy/**','营销活动管理资源');
insert into resources  (id,path,description) values(6,'/manager/sms/**','短信管理资源');

insert into resources  (id,path,description) values(7,'/manager/child/**','门店查询资源');

insert into resources  (id,path,description) values(8,'/manager/comment/**','点评管理资源');

insert into resources  (id,path,description) values(9,'/manager/internal/merchant/**','商户资料管理资源');
insert into resources  (id,path,description) values(10,'/manager/internal/pos/**','POS终端查询资源');

insert into resources  (id,path,description) values(11,'/manager/security/operator/**','帐户管理资源');
insert into resources  (id,path,description) values(12,'/manager/security/self/**','个人管理资源');
insert into resources  (id,path,description) values(13,'/manager/security/log/**','操作日志资源');

insert into resources  (id,path,description) values(14,'/manager/report/memberinfo/**','会员信息报表资源');
insert into resources  (id,path,description) values(15,'/manager/report/memberconsume/**','会员消费报表');
insert into resources  (id,path,description) values(16,'/manager/report/memberpoint/**','积分报表');
insert into resources  (id,path,description) values(17,'/manager/report/saleploy/**','营销报表资源');

insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(1,1,'会员信息管理','/manager/member/info/info.htm','/manager/image/dev/T_01.jpg','会员信息管理',1);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(2,1,'会员消费查询','/manager/member/consume/consume.htm','/manager/image/dev/T_06.jpg','会员消费查询',0);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(3,1,'会员等级管理','/manager/member/level/view.htm','/manager/image/dev/T_07.jpg','会员等级管理',0);

insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(4,2,'发布营销活动','/manager/saleploycreate/create_one.htm','/manager/image/dev/T_17.jpg','创建营销活动',1);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(5,2,'营销活动管理','/manager/saleploy/list.htm','/manager/image/dev/T_02.jpg','营销活动管理',1);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(6,2,'短信管理','/manager/sms/view.htm','/manager/image/dev/T_05.jpg','短信管理',1);

insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(7,3,'门店查询','/manager/child/list.htm','/manager/image/dev/T_06.jpg','门店查询',0);

insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(8,4,'点评管理','/manager/comment/list.htm','/manager/image/dev/T_07.jpg','点评管理',0);

insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(9,5,'商户资料管理','/manager/internal/merchant/index.htm','/manager/image/dev/T_08.jpg','商户资料管理',0);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(10,5,'POS终端查询','/manager/internal/pos/list.htm','/manager/image/dev/T_09.jpg','POS终端查询',0);

insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(11,6,'帐户管理','/manager/security/operator/list.htm','/manager/image/dev/T_04.jpg','管理员帐户管理',1);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(12,6,'密码修改','/manager/security/self/','/manager/image/dev/T_11.jpg','个人管理',0);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(13,6,'操作日志','/manager/security/log/list.htm','/manager/image/dev/T_11.jpg','管理员操作日志',0);

insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(14,7,'会员信息报表','/manager/report/memberinfo/today.htm','/manager/image/dev/T_12.jpg','会员信息报表',0);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(15,7,'会员消费报表','/manager/report/memberconsume/today.htm','/manager/image/dev/T_13.jpg','会员消费报表',1);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(16,7,'积分报表','/manager/report/memberpoint/find.htm','/manager/image/dev/T_14.jpg','积分报表',0);
insert into ROLE (id,role_group,name,entry_index,logo,description,SHORT_CUT) values(17,7,'营销报表','/manager/report/saleploy/find.htm','/manager/image/dev/T_03.jpg','会员信息报表资源',0);


insert into role_resource (id,role_id,resource_id)values(1,1,1);
insert into role_resource (id,role_id,resource_id)values(2,2,2);
insert into role_resource (id,role_id,resource_id)values(3,3,3);
insert into role_resource (id,role_id,resource_id)values(4,4,4);
insert into role_resource (id,role_id,resource_id)values(5,5,5);
insert into role_resource (id,role_id,resource_id)values(6,6,6);
insert into role_resource (id,role_id,resource_id)values(7,7,7);
insert into role_resource (id,role_id,resource_id)values(8,8,8);
insert into role_resource (id,role_id,resource_id)values(9,9,9);
insert into role_resource (id,role_id,resource_id)values(10,10,10);
insert into role_resource (id,role_id,resource_id)values(11,11,11);
insert into role_resource (id,role_id,resource_id)values(12,12,12);
insert into role_resource (id,role_id,resource_id)values(13,13,13);
insert into role_resource (id,role_id,resource_id)values(14,14,14);
insert into role_resource (id,role_id,resource_id)values(15,15,15);
insert into role_resource (id,role_id,resource_id)values(16,16,16);
insert into role_resource (id,role_id,resource_id)values(17,17,17);



 insert into manager (id,merchant_id,type,mobile,password,status,create_date,last_login_date)
 VALUES(1,null,1,'13888888888','47a0b74c9c802fb1f65fd86bdcc6ed9afad2cebe',1,TO_DATE('2010-08-26 12:12:12','yyyy-mm-dd hh24:mi:ss'),TO_DATE('2010-08-26 12:12:12','yyyy-mm-dd hh24:mi:ss'));
  insert into manager (id,merchant_id,type,mobile,password,status,create_date,last_login_date)
 VALUES(2,null,1,'13888888889','47a0b74c9c802fb1f65fd86bdcc6ed9afad2cebe',1,TO_DATE('2010-08-26 12:12:12','yyyy-mm-dd hh24:mi:ss'),TO_DATE('2010-08-26 12:12:12','yyyy-mm-dd hh24:mi:ss'));
 
 
insert into manager_role (id,employee_id,role_id)values(1,1,1);
insert into manager_role (id,employee_id,role_id)values(2,1,2);
insert into manager_role (id,employee_id,role_id)values(3,1,3);
insert into manager_role (id,employee_id,role_id)values(4,1,4);
insert into manager_role (id,employee_id,role_id)values(5,1,5);
insert into manager_role (id,employee_id,role_id)values(6,1,6);
insert into manager_role (id,employee_id,role_id)values(7,1,7);
insert into manager_role (id,employee_id,role_id)values(8,1,8);
insert into manager_role (id,employee_id,role_id)values(9,1,9);
insert into manager_role (id,employee_id,role_id)values(10,1,10);
insert into manager_role (id,employee_id,role_id)values(11,1,11);
insert into manager_role (id,employee_id,role_id)values(12,1,12);
insert into manager_role (id,employee_id,role_id)values(13,1,13);
insert into manager_role (id,employee_id,role_id)values(14,1,14);
insert into manager_role (id,employee_id,role_id)values(15,1,15);
insert into manager_role (id,employee_id,role_id)values(16,1,16);
insert into manager_role (id,employee_id,role_id)values(17,1,17);


insert into manager_role (id,employee_id,role_id)values(18,2,1);
insert into manager_role (id,employee_id,role_id)values(19,2,2);
insert into manager_role (id,employee_id,role_id)values(20,2,3);
insert into manager_role (id,employee_id,role_id)values(21,2,4);
insert into manager_role (id,employee_id,role_id)values(22,2,5);
insert into manager_role (id,employee_id,role_id)values(23,2,6);
insert into manager_role (id,employee_id,role_id)values(24,2,7);
insert into manager_role (id,employee_id,role_id)values(25,2,8);
insert into manager_role (id,employee_id,role_id)values(26,2,9);
insert into manager_role (id,employee_id,role_id)values(27,2,10);
insert into manager_role (id,employee_id,role_id)values(28,2,11);
insert into manager_role (id,employee_id,role_id)values(29,2,12);
insert into manager_role (id,employee_id,role_id)values(30,2,13);
insert into manager_role (id,employee_id,role_id)values(31,2,14);
insert into manager_role (id,employee_id,role_id)values(32,2,15);
insert into manager_role (id,employee_id,role_id)values(33,2,16);
insert into manager_role (id,employee_id,role_id)values(34,2,17);


/*测试数据*/

 
INSERT INTO MEMBER_GROUP(ID) VALUES(1);

INSERT  INTO MERCHANT (
ID,
MEMBER_GROUP_ID,
CODE,
NAME,
STATUS,
SHORT_NAME,
PARENT_ORGANIZATION,
BUSINESS_ADDRESS,
CONTACT_NAME,
CONTACT_TELEPHONE,
WIFI,
CREATE_DATE) 
 VALUES (1,1,'123321','测试用商户',1,'WC','A','B','C','D',1,TO_DATE('2010-08-26 12:12:12','yyyy-mm-dd hh24:mi:ss'));
 


insert into manager (id,merchant_id,type,mobile,password,status,create_date)
 VALUES(3,1,2,'13880284172','47a0b74c9c802fb1f65fd86bdcc6ed9afad2cebe',1,TO_DATE('2010-08-26 12:12:12','yyyy-mm-dd hh24:mi:ss'));
 
insert into manager_role (id,employee_id,role_id)values(35,3,1);
insert into manager_role (id,employee_id,role_id)values(36,3,2);
insert into manager_role (id,employee_id,role_id)values(37,3,3);
insert into manager_role (id,employee_id,role_id)values(38,3,4);
insert into manager_role (id,employee_id,role_id)values(39,3,5);
insert into manager_role (id,employee_id,role_id)values(40,3,6);
insert into manager_role (id,employee_id,role_id)values(41,3,7);
insert into manager_role (id,employee_id,role_id)values(42,3,8);
insert into manager_role (id,employee_id,role_id)values(43,3,9);
insert into manager_role (id,employee_id,role_id)values(44,3,10);
insert into manager_role (id,employee_id,role_id)values(45,3,11);
insert into manager_role (id,employee_id,role_id)values(46,3,12);
insert into manager_role (id,employee_id,role_id)values(47,3,13);
insert into manager_role (id,employee_id,role_id)values(48,3,14);
insert into manager_role (id,employee_id,role_id)values(49,3,15);
insert into manager_role (id,employee_id,role_id)values(50,3,16);
insert into manager_role (id,employee_id,role_id)values(51,3,17);

 

