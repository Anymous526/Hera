prompt PL/SQL Developer import file
prompt Created on 2011年6月29日 by Administrator
set feedback off
set define off
prompt Loading SYS_AUTHORITY...
insert into SYS_AUTHORITY (ID, AUTHNAME, DESCRIPTION)
values (1, 'AUTH_LOGIN', '登录系统');
insert into SYS_AUTHORITY (ID, AUTHNAME, DESCRIPTION)
values (2, 'AUTH_SYS_MANAGE', '系统管理模块');
insert into SYS_AUTHORITY (ID, AUTHNAME, DESCRIPTION)
values (3, 'AUTH_BUSSINES_MANAGE', '业务管理模块');
commit;
prompt 3 records loaded
prompt Loading SYS_MENU...
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (23, '商户信息管理', '/view/merchant/listMerchant.jsp', 1, 22);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (321, '营销短信审核', '/view/cloudboss/jsp/listSmsAudit.jsp', 0, 461);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (461, '营销管理', null, 2, null);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (462, '系统设置', null, 0, 1);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (1, '系统管理', null, 5, null);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (22, '商户管理', null, 1, null);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (2, '安全管理', null, 2, 1);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (11, '资源管理', '/view/security/jsp/listResource.jsp', 1, 2);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (12, '权限管理', '/view/security/jsp/listAuthority.jsp', 4, 2);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (13, '菜单管理', '/view/security/jsp/listMenu.jsp', 2, 2);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (14, '用户管理', '/view/security/jsp/listUser.jsp', 6, 2);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (15, '角色管理', '/view/security/jsp/listRole.jsp', 5, 2);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (281, '日志管理', null, 4, null);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (282, '日志查询', '/view/log/listLog.jsp', 1, 281);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (301, '违禁字管理', '/view/cloudboss/jsp/listBanedWord.jsp', 0, 462);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (341, '营销短信代发', '/view/cloudboss/jsp/editSmsAgent.jsp', 0, 461);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (361, '短信数量增加', '/view/cloudboss/jsp/listSmsQuantity.jsp', 0, 461);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (441, '公告管理', '/view/cloudboss/jsp/listNotice.jsp', 0, 462);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (302, '会员管理', null, 3, null);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (303, '会员查看', '/view/cloudboss/member/listMember.jsp', 1, 302);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (304, '会员导入', '/view/cloudboss/member/memberUpload.jsp', 2, 302);
insert into SYS_MENU (ID, MENUNAME, URL, ORDERNO, PARENTID)
values (381, '商户信息审核', '/view/merchant/listMerchantAud.jsp', 2, 22);
commit;
prompt 22 records loaded
prompt Loading SYS_AUTH_MENU...
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 11);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 12);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 13);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 14);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 15);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 2);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 1);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 1);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 304);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 441);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 341);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 381);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 282);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 281);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 462);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 461);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 23);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 321);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 22);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 361);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 301);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 302);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (3, 303);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 11);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 12);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 13);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 14);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 15);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 2);
insert into SYS_AUTH_MENU (AUTH_ID, MENU_ID)
values (2, 1);
commit;
prompt 30 records loaded
prompt Loading SYS_RESOURCE...
insert into SYS_RESOURCE (ID, RESNAME, TYPE, FILTERSTRING)
values (1, '默认页面', 'URL', '/index.jsp');
insert into SYS_RESOURCE (ID, RESNAME, TYPE, FILTERSTRING)
values (2, '主界面', 'URL', '/view/index.jsp');
insert into SYS_RESOURCE (ID, RESNAME, TYPE, FILTERSTRING)
values (3, '权限管理页面', 'URL', '/view/security/jsp/listAuthority.jsp');
insert into SYS_RESOURCE (ID, RESNAME, TYPE, FILTERSTRING)
values (4, '权限管理方法调用', 'URL', '/auth.do*');
commit;
prompt 4 records loaded
prompt Loading SYS_AUTH_RES...
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (2, 3);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (2, 4);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (2, 1);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (2, 2);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (3, 2);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (3, 1);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (1, 2);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (1, 1);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (2, 3);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (2, 4);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (2, 1);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (2, 2);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (3, 2);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (3, 1);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (1, 2);
insert into SYS_AUTH_RES (AUTH_ID, RES_ID)
values (1, 1);
commit;
prompt 16 records loaded
prompt Loading SYS_OPERATE_LOG...
prompt Table is empty
prompt Loading SYS_OPERATE_LOG_PARAM...
prompt Table is empty
prompt Loading SYS_ROLE...
insert into SYS_ROLE (ID, ROLENAME, DESCRIPTION)
values (1, '系统管理员', '管理系统权限设置和菜单');
insert into SYS_ROLE (ID, ROLENAME, DESCRIPTION)
values (2, '业务管理员', '所有业务模块的管理');
commit;
prompt 2 records loaded
prompt Loading SYS_ROLE_AUTH...
insert into SYS_ROLE_AUTH (ROLE_ID, AUTH_ID)
values (1, 2);
insert into SYS_ROLE_AUTH (ROLE_ID, AUTH_ID)
values (1, 1);
insert into SYS_ROLE_AUTH (ROLE_ID, AUTH_ID)
values (2, 1);
insert into SYS_ROLE_AUTH (ROLE_ID, AUTH_ID)
values (2, 3);
insert into SYS_ROLE_AUTH (ROLE_ID, AUTH_ID)
values (2, 1);
insert into SYS_ROLE_AUTH (ROLE_ID, AUTH_ID)
values (2, 3);
insert into SYS_ROLE_AUTH (ROLE_ID, AUTH_ID)
values (1, 2);
insert into SYS_ROLE_AUTH (ROLE_ID, AUTH_ID)
values (1, 1);
commit;
prompt 8 records loaded
prompt Loading SYS_USER...
insert into SYS_USER (ID, USERNAME, PASSWORD, REALNAME, MOBILE, EMAIL, STATUS, LATESTLOGIN, SALT, PROVINCECODE)
values (1, 'admin', '44113881dff13758337d795f5405211e', '管理员', '13888888888', null, 1, to_date('29-06-2011 15:17:31', 'dd-mm-yyyy hh24:mi:ss'), '4961115982468162243', null);
commit;
prompt 1 records loaded
prompt Loading SYS_USER_ROLE...
insert into SYS_USER_ROLE (USER_ID, ROLE_ID)
values (1, 1);
insert into SYS_USER_ROLE (USER_ID, ROLE_ID)
values (1, 2);
insert into SYS_USER_ROLE (USER_ID, ROLE_ID)
values (1, 2);
insert into SYS_USER_ROLE (USER_ID, ROLE_ID)
values (1, 1);
commit;
prompt 4 records loaded
set feedback on
set define on
prompt Done.
