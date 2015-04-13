prompt PL/SQL Developer import file
prompt Created on 2011��6��27�� by MrXu
set feedback off
set define off
prompt Disabling triggers for AREA...
alter table AREA disable all triggers;
prompt Disabling foreign key constraints for AREA...
alter table AREA disable constraint FK_AREA_REF_AREA;
prompt Deleting AREA...
delete from AREA;
commit;
prompt Loading AREA...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2723, 4, '��ɽ��', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2724, 4, '������', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2725, 4, '��Դ��', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1346, 4, '̩����', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1349, 4, '������', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1352, 4, '������', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2726, 4, 'ʯ����', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (681, 4, '˫����', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (684, 4, '��ɽ��', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (687, 4, '������', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (690, 4, '������', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (693, 4, '������', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (696, 4, '������', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (699, 4, '�˵�����', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (703, 4, '��Դ��', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (706, 3, '��ԭ��', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (709, 4, '������', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (712, 4, '������', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (715, 4, '������', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (718, 4, '����', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (721, 4, '�Ӽ���', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (724, 4, '������', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (727, 4, '������', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (730, 2, '������ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (733, 4, '�ϸ���', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (736, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (739, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (742, 4, '����', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (947, 4, 'ͭɽ��', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (950, 4, '������', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (953, 4, '������', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (956, 4, '�±���', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (959, 4, '��̳��', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (962, 4, '������', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (965, 4, '������', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (968, 4, '������', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (971, 4, '�⽭��', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (974, 4, '԰��', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (977, 4, '�紨��', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (980, 4, '�綫��', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (983, 4, 'ͨ����', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (986, 4, '������', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (989, 4, '������', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (992, 4, '������', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (995, 4, '������', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (998, 4, '������', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1001, 4, '��ˮ��', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1004, 4, '�����', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1007, 4, 'ͤ����', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1010, 4, '������', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1013, 4, '������', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1016, 4, '������', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1019, 4, '������', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1022, 4, '������', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1025, 4, '���ÿ�����', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1029, 4, '������', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1032, 4, '������', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1035, 3, '̩����', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1038, 4, '�˻���', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1041, 4, '������', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1044, 4, '�޳���', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1047, 4, '������', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1050, 2, '�㽭ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1053, 4, '�³���', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1056, 4, '������', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1059, 4, '�ຼ��', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1062, 4, '������', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1065, 4, '������', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1068, 4, '������', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1071, 4, '����', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1074, 4, '������', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1077, 4, '���', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1080, 4, '¹����', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1083, 4, '��ͷ��', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1086, 4, '������', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1089, 4, '����', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1092, 3, '������', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1095, 4, '������', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1098, 4, 'ƽ����', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1101, 3, '������', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1104, 4, '������', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1107, 4, '������', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1110, 4, '������', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1113, 4, '������', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1116, 3, '����', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1119, 4, '������', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1325, 4, '������', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1328, 4, '������', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1331, 4, '������', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1334, 4, '������', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1337, 4, '÷����', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1340, 4, '������', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1343, 4, '��Ϫ��', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1355, 4, '�ݰ���', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1358, 4, '�»���', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1361, 4, '������', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1364, 3, '������', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1367, 4, '������', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1370, 4, '��̩��', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1373, 4, 'ƽ����', 1364);
commit;
prompt 100 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1376, 4, '������', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1379, 4, '˳����', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1382, 4, '��Ϫ��', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1385, 4, '����ɽ��', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1388, 4, '������', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1391, 4, '��͡��', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1394, 4, '��ƽ��', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1397, 4, '������', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1400, 4, 'ϼ����', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1403, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1406, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1409, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1412, 4, '������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1415, 4, '��ɽ����', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1419, 4, '������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1422, 4, '������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1425, 4, '������', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1428, 4, '��ƽ��', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1431, 4, '��Դ��', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1434, 4, '������', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1437, 3, '�Ž���', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1440, 4, '�Ž���', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1443, 4, '������', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1446, 4, '������', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1449, 4, '�����', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1452, 4, '��ˮ��', 1451);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1455, 3, 'ӥ̶��', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1458, 4, '��Ϫ��', 1455);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1461, 4, '�¹���', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1464, 4, '������', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1467, 4, '��Զ��', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1470, 4, 'ȫ����', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1473, 4, '�˹���', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1476, 4, 'ʯ����', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1479, 4, '�Ͽ���', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1482, 4, '������', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1485, 4, '��ˮ��', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1488, 4, '������', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1491, 4, '����', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1494, 4, '����ɽ��', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1497, 4, 'Ԭ����', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1500, 4, '�ϸ���', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1706, 4, '����', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1708, 4, '������', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1711, 4, '۲����', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1714, 4, '������', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1718, 4, '������', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1721, 4, '�Ͻ���', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1724, 4, '������', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1727, 4, '��֣��', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1730, 4, '������', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1733, 4, '��ͤ��', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1736, 4, '����̨��', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1739, 4, 'ͨ����', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1742, 4, '������', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1745, 4, '�ϳ���', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1748, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1751, 4, '�Ͻ���', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1754, 4, '����', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1757, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1760, 3, 'ƽ��ɽ��', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1763, 4, 'ʯ����', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1766, 4, 'Ҷ��', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1769, 4, '�����', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1772, 3, '������', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1775, 4, '����', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1778, 4, '������', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1781, 4, '������', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1784, 4, '��ɽ��', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1787, 4, '����', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1789, 4, '������', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1792, 4, '������', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1795, 4, '������', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1798, 4, '�ӽ���', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1801, 4, '������', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1804, 3, '������', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1807, 4, '�����', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1810, 4, '������', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1813, 4, '��Դ��', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1816, 4, '������', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1819, 4, '�����', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1822, 4, '̨ǰ��', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1825, 3, '�����', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1828, 4, '۳����', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1831, 4, '������', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1834, 4, 'Դ����', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1837, 4, '������', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1840, 3, '����Ͽ��', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1844, 4, '¬����', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1847, 4, '������', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1850, 4, '������', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1853, 4, '��Ͽ��', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1856, 4, '������', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1859, 4, '��Ұ��', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1862, 4, '������', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1865, 4, '�����', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1868, 4, '������', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1871, 4, '������', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1874, 3, '������', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1877, 4, '��ɽ��', 1874);
commit;
prompt 200 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2924, 4, '������', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2927, 4, '��ƽ��', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2930, 4, 'մ����', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2933, 3, '��Ϫ��', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2936, 4, '�ν���', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2939, 4, '������', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2942, 4, 'Ԫ���������������������', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2949, 4, '������', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2952, 4, '������', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2955, 4, '�ν���', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2958, 4, '�罭��', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2961, 4, '������', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2964, 3, '������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2967, 4, '��ʤ��', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2970, 4, '������', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2973, 4, '��������������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2980, 4, '����������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2985, 4, '������', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3191, 3, '������', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3194, 4, 'μ����', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3197, 4, 'Ǭ��', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3200, 4, '����', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3202, 4, 'Ѯ����', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3205, 4, '��ƽ��', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3208, 4, '��μ��', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3211, 4, '������', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3214, 4, '�ѳ���', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3217, 4, '������', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3220, 3, '�Ӱ���', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3223, 4, '�Ӵ���', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3226, 4, '־����', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3229, 4, '����', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3231, 4, '�˴���', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3234, 4, '������', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3237, 4, '��֣��', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3240, 4, '������', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3243, 4, '������', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3246, 4, '��ƺ��', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3249, 4, '������', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3252, 4, '��ɽ��', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3255, 4, '�����', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3258, 4, '�Ɽ��', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3261, 4, '������', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3264, 4, '������', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3267, 4, '������', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3270, 4, '��ƺ��', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3273, 4, '������', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3276, 4, '������', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3279, 4, 'ɽ����', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3282, 4, '������', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3286, 4, '�������', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3289, 4, '�����', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3292, 4, '������', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3295, 3, '�����', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3298, 4, '������', 3295);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3301, 4, 'ƽ����', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3304, 4, '��̩��', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3307, 4, '������', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3310, 4, '�ذ���', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3313, 4, '�żҴ�����������', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3319, 4, '��ף����������', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3324, 4, '������', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3327, 4, 'ɽ����', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3330, 4, '�����', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3333, 4, '������', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3336, 4, '������', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3339, 4, '������', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3342, 4, '�౱�ɹ���������', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3347, 3, '������', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3350, 4, '����', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3559, 4, '������', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3562, 4, '�踽��', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3565, 4, '������', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3568, 4, '�������', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3571, 4, '�ͳ���', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3574, 3, '�������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3578, 4, 'Ƥɽ��', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3581, 4, '������', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3584, 3, '���������������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3589, 4, '������', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3592, 4, '������', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3595, 4, '������', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3598, 4, '������', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3601, 4, '������', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3604, 4, '������', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3607, 4, '��������', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3611, 4, '�����', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3614, 4, 'ʯ������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3617, 4, '�������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3621, 4, '��ͬ��', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3624, 4, '����', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3627, 4, 'ʿ����', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3630, 4, '�ϸ���', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3633, 3, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3636, 4, '������', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3639, 4, '�����', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3642, 4, '��Ӫ��', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3645, 4, '������', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3648, 4, '����', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3650, 4, '����', 3646);
commit;
prompt 300 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3653, 4, '������', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3656, 4, '����', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3658, 4, '����', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3661, 4, '������', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3664, 3, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3667, 4, '�ʰ���', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3670, 4, '��ɽ��', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3673, 4, '�߶���', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3676, 4, '����', 3675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3678, 4, '��ɽ��', 3675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3681, 4, '����', 3680);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3684, 4, '̨����', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3687, 4, '��԰��', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3690, 4, '�û���', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3693, 4, '̨����', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3696, 4, '̨����', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3699, 2, '����ر�������', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3704, 3, '���Ű뵺', 3703);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2727, 4, '��ȫ��', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2728, 4, '«ɽ��', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2729, 4, '������', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2730, 4, '������', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2731, 3, '������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2732, 4, '������', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2733, 4, 'ͨ����', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2734, 4, '�Ͻ���', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2735, 4, 'ƽ����', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2736, 4, '������', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2737, 3, '������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2738, 4, '�㽭��', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2739, 4, '������', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2740, 4, '������', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2741, 4, '������', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2742, 4, '������', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2743, 3, '���Ӳ���Ǽ��������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2744, 4, '�봨��', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2745, 4, '����', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2746, 4, 'ï��', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2747, 4, '������', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2748, 4, '��կ����', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2749, 4, '����', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2750, 4, 'С����', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2751, 4, '��ˮ��', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2752, 4, '�������', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2753, 4, '������', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2754, 4, '������', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2755, 4, '��������', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2756, 4, '��ԭ��', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2757, 4, '������', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2758, 3, '���β���������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2759, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2760, 4, '����', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2761, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2762, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2763, 4, '�Ž���', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2764, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2765, 4, '¯����', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2766, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2767, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2768, 4, '�¸���', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2769, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2770, 4, 'ʯ����', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2771, 4, 'ɫ����', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2772, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2773, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2774, 4, '�����', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2775, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2776, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2777, 4, '������', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2778, 3, '��ɽ����������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2779, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2780, 4, 'ľ�����������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2781, 4, '��Դ��', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2782, 4, '�²���', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2783, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2784, 4, '�ᶫ��', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2785, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2786, 4, '�ո���', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2787, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2788, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2789, 4, '�Ѿ���', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2790, 4, 'ϲ����', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2791, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2792, 4, 'Խ����', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2793, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2794, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2795, 4, '�ײ���', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2796, 4, '������', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2797, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2798, 3, '������', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2799, 4, '������', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2800, 4, '������', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2801, 4, '��Ϫ��', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2802, 4, '�ڵ���', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2803, 4, '������', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2804, 4, 'С����', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2805, 4, '������', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2806, 4, 'Ϣ����', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2807, 4, '������', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2808, 4, '����������', 2798);
commit;
prompt 400 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2809, 4, '������', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2810, 4, '������', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2811, 3, '����ˮ��', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2812, 4, '��ɽ��', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2813, 4, '��֦����', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2814, 4, 'ˮ����', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2815, 4, '����', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2816, 4, '������', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2817, 3, '������', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2818, 4, '�컨����', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2819, 4, '�㴨��', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2820, 4, '������', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2821, 4, 'ͩ����', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2822, 4, '������', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2823, 4, '������', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2824, 4, '��������������������', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2825, 4, '������������������', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2826, 4, '�����', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2827, 4, '��̶��', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2828, 4, '������', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2829, 4, 'ϰˮ��', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2830, 4, '��ˮ��', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2831, 4, '�ʻ���', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2833, 3, '��˳��', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2834, 4, '������', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2836, 4, '�ն���', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2837, 4, '��������������������', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2839, 4, '�������岼����������', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2840, 4, '������', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2841, 3, 'ͭ�ʵ���', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2842, 4, 'ͭ����', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2843, 4, '������', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2845, 4, 'ʯ����', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2846, 4, '˼����', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2847, 4, 'ӡ������������������', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2848, 4, '�½���', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2850, 4, '��������������', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2851, 4, '��ɽ����', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2852, 4, '������', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2853, 3, 'ǭ���ϲ���������������', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2854, 4, '������', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2856, 4, '�հ���', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2857, 4, '��¡��', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2859, 4, '������', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2860, 4, '�����', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2862, 4, '������', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2863, 3, '�Ͻڵ���', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2865, 4, '����', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2866, 4, 'ǭ����', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2868, 4, '֯����', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2869, 4, '��Ӻ��', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2871, 4, '������', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2872, 4, '������', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2873, 3, 'ǭ�������嶱��������', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2874, 4, '������', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2875, 4, '��ƽ��', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2876, 4, 'ʩ����', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2878, 4, '��Զ��', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2879, 4, '᯹���', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2881, 4, '������', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2882, 4, '������', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2884, 4, '��ƽ��', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2885, 4, '�Ž���', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2887, 4, '��ɽ��', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2888, 4, '�齭��', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2890, 4, '������', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2891, 3, 'ǭ�ϲ���������������', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2893, 4, '��Ȫ��', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2894, 4, '����', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2896, 4, '�Ͱ���', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2897, 4, '��ɽ��', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2899, 4, '�޵���', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2900, 4, '��˳��', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2902, 4, '��ˮ��', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2903, 4, '����ˮ��������', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2905, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2906, 3, '������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2908, 4, '������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2909, 4, '�ٶ���', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2911, 4, '������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2912, 4, '�ʹ���', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2914, 4, '������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2915, 4, '������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2917, 4, '������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2918, 4, '»Ȱ��������������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2919, 4, 'Ѱ���������������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2920, 4, '������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2922, 3, '������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2923, 4, '������', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2925, 4, '½����', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2926, 4, 'ʦ����', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2928, 4, '��Դ��', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2929, 4, '������', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2931, 4, '������', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2932, 4, '������', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2934, 4, '������', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2935, 4, '������', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2937, 4, 'ͨ����', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2938, 4, '������', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2940, 4, '��ɽ����������', 2933);
commit;
prompt 500 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2941, 4, '��ƽ�������������', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2943, 4, '������', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2944, 3, '��ɽ��', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2945, 4, '¡����', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2946, 4, 'ʩ����', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2947, 4, '�ڳ���', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2948, 4, '������', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2950, 4, '������', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2951, 3, '��ͨ��', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2953, 4, '³����', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2954, 4, '�ɼ���', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2956, 4, '�����', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2957, 4, '������', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2959, 4, '������', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2960, 4, '������', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2962, 4, 'ˮ����', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2963, 4, '������', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2965, 4, '�ų���', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2966, 4, '����������������', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2968, 4, '��ƺ��', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2969, 4, '��������������', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2971, 3, '�ն���', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2972, 4, '˼é��', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2974, 4, 'ī��������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2987, 4, '������', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2988, 4, '����', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2989, 4, '˫�����������岼�������������', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2990, 4, '�����������������', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2991, 4, '��Դ����������', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2992, 4, '������', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2993, 3, '��������������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2994, 4, '������', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2995, 4, '˫����', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2996, 4, 'Ĳ����', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2997, 4, '�ϻ���', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2998, 4, 'Ҧ����', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2999, 4, '��Ҧ��', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3000, 4, '������', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3001, 4, 'Ԫı��', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3002, 4, '�䶨��', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3003, 4, '»����', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3004, 4, '������', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3005, 3, '��ӹ���������������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3006, 4, '������', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3007, 4, '��Զ��', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3008, 4, '������', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3009, 4, '��������������', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3010, 4, '��ˮ��', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3011, 4, 'ʯ����', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3012, 4, '������', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3013, 4, '������', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3014, 4, 'Ԫ����', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3015, 4, '�����', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3016, 4, '��ƽ�����������������', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3017, 4, '�̴���', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3018, 4, '�ӿ�����������', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3019, 4, '������', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3020, 3, '��ɽ׳������������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3021, 4, '��ɽ��', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3022, 4, '��ɽ��', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3023, 4, '������', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3024, 4, '��������', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3025, 4, '�����', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3026, 4, '����', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3027, 4, '������', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3028, 4, '������', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3029, 4, '������', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3030, 3, '��˫���ɴ���������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3031, 4, '������', 3030);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3032, 4, '�º���', 3030);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3033, 4, '������', 3030);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3034, 4, '������', 3030);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3035, 3, '�������������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3036, 4, '������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3037, 4, '�������������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3038, 4, '������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3039, 4, '������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3040, 4, '�ֶ���', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3041, 4, '�Ͻ�����������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3042, 4, 'Ρɽ�������������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3043, 4, '��ƽ��', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3044, 4, '������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3045, 4, '��Դ��', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3046, 4, '������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3047, 4, '������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3048, 4, '������', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3049, 3, '�º���徰����������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3050, 4, '������', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3051, 4, 'º����', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3052, 4, '������', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3053, 4, 'ӯ����', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3054, 4, '¤����', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3055, 4, '������', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3056, 3, 'ŭ��������������', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3057, 4, '��ˮ��', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3058, 4, '������', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3059, 4, '��ɽ������ŭ��������', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3060, 4, '��ƺ����������������', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3061, 4, '������', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3062, 3, '�������������', 2905);
commit;
prompt 600 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3063, 4, '���������', 3062);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3064, 4, '������', 3062);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3065, 4, 'ά��������������', 3062);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3066, 4, '������', 3062);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3067, 2, '����������', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3068, 3, '������', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3069, 4, '�ǹ���', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1637, 4, '��ƽ��', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1638, 4, '��̩��', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1639, 4, '�ʳ���', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1640, 4, '������', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1641, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1642, 4, '������', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1643, 4, '�ĵ���', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1644, 4, '�ٳ���', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1645, 4, '��ɽ��', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1646, 4, '������', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1647, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1648, 4, '������', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1649, 4, '�ɽ��', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1650, 4, '������', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1651, 4, '����', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1652, 4, '������', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1653, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1654, 4, '������', 1653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1655, 4, '�ֳ���', 1653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1656, 4, '������', 1653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1657, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1658, 4, '��ɽ��', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1659, 4, '��ׯ��', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1660, 4, '�Ӷ���', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1661, 4, '������', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1662, 4, '۰����', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1663, 4, '��ˮ��', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1664, 4, '��ɽ��', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1665, 4, '����', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1666, 4, 'ƽ����', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1667, 4, '������', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1668, 4, '������', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1669, 4, '������', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1670, 4, '������', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1671, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1672, 4, '�³���', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1673, 4, '����', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1674, 4, '������', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1675, 4, '������', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1676, 4, '������', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1677, 4, '�����', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1678, 4, 'ƽԭ��', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1679, 4, '�Ľ���', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1680, 4, '�����', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1681, 4, '������', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1682, 4, '������', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1683, 4, '�����', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1684, 4, '������', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1685, 3, '�ĳ���', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1686, 4, '��������', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1687, 4, '������', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1688, 4, 'ݷ��', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1689, 4, '��ƽ��', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1690, 4, '������', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1691, 4, '����', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1692, 4, '������', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1693, 4, '������', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1694, 4, '������', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1695, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1696, 4, '������', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1697, 4, '������', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1698, 4, '������', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1699, 4, '�����', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1700, 4, 'մ����', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1701, 4, '������', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1702, 4, '��ƽ��', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1703, 4, '������', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1704, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1705, 4, 'ĵ����', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1707, 4, '����', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1709, 4, '��Ұ��', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1710, 4, '۩����', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1712, 4, '������', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1713, 4, '������', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1715, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1716, 3, '֣����', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1717, 4, '��ԭ��', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1719, 4, '�ܳǻ�����', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1720, 4, '��ˮ��', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1722, 4, '�ݼ���', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1723, 4, '��Ĳ��', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1725, 4, '������', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1726, 4, '������', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1728, 4, '�Ƿ���', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1729, 4, '֣������', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1731, 4, '������', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1732, 3, '������', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1734, 4, '˳�ӻ�����', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1735, 4, '��¥��', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1737, 4, '������', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1738, 4, '���', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1740, 4, 'ξ����', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1741, 4, '������', 1732);
commit;
prompt 700 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1743, 4, '������', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1744, 3, '������', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1746, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1747, 4, '�ܺӻ�����', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1749, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1750, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1752, 4, '�°���', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1753, 4, '�ﴨ��', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1755, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1756, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1758, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1759, 4, '��ʦ��', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1761, 4, '�»���', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1762, 4, '������', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1764, 4, 'տ����', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1765, 4, '������', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1767, 4, '³ɽ��', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1768, 4, 'ۣ��', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1770, 4, '������', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1771, 4, '������', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1773, 4, '�ķ���', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1774, 4, '������', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1776, 4, '������', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1777, 4, '������', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1779, 4, '����', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1780, 4, '�ڻ���', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1782, 4, '������', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1783, 3, '�ױ���', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1785, 4, 'ɽ����', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1786, 4, '俱���', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1788, 4, '���', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1790, 3, '������', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1791, 4, '������', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1793, 4, '��Ȫ��', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1794, 4, '��Ұ��', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1796, 4, '�����', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1797, 4, 'ԭ����', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1799, 4, '������', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1800, 4, '��ԫ��', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1802, 4, '������', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1803, 4, '������', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1805, 4, '�����', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1806, 4, '��վ��', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1808, 4, 'ɽ����', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1809, 4, '������', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1811, 4, '������', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1812, 4, '����', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1814, 4, '������', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1815, 4, '������', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1817, 3, '�����', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1818, 4, '������', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1820, 4, '������', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1821, 4, '����', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1823, 4, '�����', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1824, 4, '������', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1826, 4, 'κ����', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1827, 4, '�����', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1829, 4, '�����', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1830, 4, '������', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1832, 4, '������', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1833, 3, '�����', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1835, 4, '۱����', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1836, 4, '������', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1838, 4, '�����', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1839, 4, '������', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1841, 4, '������', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1842, 4, '�ų���', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1843, 4, '����', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1845, 4, '������', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1846, 4, '�鱦��', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1848, 3, '������', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1849, 4, '�����', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1851, 4, '������', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1852, 4, '������', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1854, 4, '��ƽ��', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1855, 4, '������', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1857, 4, '������', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1858, 4, '�ƺ���', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1860, 4, 'ͩ����', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1861, 4, '������', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1863, 3, '������', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1864, 4, '��԰��', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1866, 4, '��Ȩ��', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1867, 4, '���', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1869, 4, '�ϳ���', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1870, 4, '�ݳ���', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1872, 4, '������', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1873, 4, '������', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1875, 4, '������', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1876, 4, 'ƽ����', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1878, 4, '��ɽ��', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1879, 4, '����', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1881, 4, '��ʼ��', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1882, 4, '�괨��', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1884, 4, 'Ϣ��', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1885, 4, '������', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1886, 3, '�ܿ���', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1887, 4, '������', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1888, 4, '������', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1889, 4, '������', 1886);
commit;
prompt 800 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1890, 4, '��ˮ��', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1891, 4, '������', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1892, 4, '������', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1893, 4, '������', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1894, 4, '̫����', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1895, 4, '¹����', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1896, 4, '�����', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1897, 4, '������', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1898, 3, 'פ�����', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1899, 4, '�����', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1900, 4, '��ƽ��', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1901, 4, '�ϲ���', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1902, 4, 'ƽ����', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1903, 4, '������', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1904, 4, 'ȷɽ��', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1905, 4, '������', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1906, 4, '������', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1907, 4, '��ƽ��', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1908, 4, '�²���', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1909, 4, '������', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1910, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1911, 3, '�人��', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1912, 4, '������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1913, 4, '������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1914, 4, '�~����', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1915, 4, '������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1916, 4, '�����', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1917, 4, '��ɽ��', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1918, 4, '��ɽ��', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1919, 4, '��������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1920, 4, '������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1921, 4, '�̵���', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1922, 4, '������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1923, 4, '������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1924, 4, '������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1925, 4, '������', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1926, 3, '��ʯ��', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1927, 4, '��ʯ����', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1928, 4, '����ɽ��', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1929, 4, '��½��', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1930, 4, '��ɽ��', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1931, 4, '������', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1932, 4, '��ұ��', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1933, 4, '������', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1934, 3, 'ʮ����', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1935, 4, 'é����', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1936, 4, '������', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1937, 4, '����', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1938, 4, '������', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1939, 4, '��ɽ��', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1940, 4, '��Ϫ��', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1941, 4, '����', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1942, 4, '��������', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1943, 4, '����', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1944, 4, '������', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1945, 3, '�˲���', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1946, 4, '������', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1947, 4, '��Ҹ���', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1948, 4, '�����', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1949, 4, '�Vͤ��', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1950, 4, '������', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1951, 4, 'Զ����', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1952, 4, '��ɽ��', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1953, 4, '������', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1954, 4, '����������������', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1955, 4, '���������������', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1956, 4, '���ް���', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1957, 4, '������', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1958, 4, '�˶���', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1959, 4, '������', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1960, 4, '֦����', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1961, 4, '������', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1962, 3, '�差��', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1963, 4, '�����', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1964, 4, '������', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1965, 4, '������', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1966, 4, '������', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1967, 4, '�ȳ���', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1968, 4, '������', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1969, 4, '�Ϻӿ���', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1970, 4, '������', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1971, 4, '�˳���', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1972, 4, '������', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1973, 3, '������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1974, 4, '���Ӻ���', 1973);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1975, 4, '������', 1973);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1976, 4, '������', 1973);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1977, 4, '������', 1973);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1978, 3, '������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1979, 4, '������', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1980, 4, '�޵���', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1981, 4, '��ɽ��', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1982, 4, 'ɳ����', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1983, 4, '������', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1984, 4, '������', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1985, 3, 'Т����', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1986, 4, 'Т����', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1987, 4, 'Т����', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1988, 4, '������', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1989, 4, '������', 1985);
commit;
prompt 900 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1990, 4, 'Ӧ����', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1991, 4, '��½��', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1992, 4, '������', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1993, 4, '������', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1994, 3, '������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1995, 4, 'ɳ����', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1996, 4, '������', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1997, 4, '������', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1998, 4, '������', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1999, 4, '������', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2000, 4, 'ʯ����', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2001, 4, '�����', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2002, 4, '������', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2003, 4, '������', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2004, 3, '�Ƹ���', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2005, 4, '������', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2006, 4, '�ŷ���', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2007, 4, '�찲��', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2008, 4, '������', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2009, 4, 'Ӣɽ��', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2010, 4, '�ˮ��', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2011, 4, 'ޭ����', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2012, 4, '��÷��', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3070, 4, '������', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3071, 4, '������', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3072, 4, '��ľ��', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3073, 4, '��ˮ��', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3074, 4, '����������', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3075, 4, '������', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3076, 4, 'ī�񹤿���', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3077, 4, '������', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3078, 3, '��������', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3079, 4, '������', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3080, 4, '������', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3081, 4, '������', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3082, 4, '��������', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3083, 4, '������', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3084, 4, '������', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3085, 4, '������', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3086, 4, '����', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3087, 4, 'â����', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3088, 4, '��¡��', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3089, 4, '�߰���', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3090, 4, '������', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3091, 3, 'ɽ�ϵ���', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3092, 4, '�˶���', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3093, 4, '������', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3094, 4, '������', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3095, 4, 'ɣ����', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3096, 4, '�����', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3097, 4, '������', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3098, 4, '������', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3099, 4, '������', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3100, 4, '�Ӳ���', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3101, 4, '¡����', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3102, 4, '������', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3103, 4, '�˿�����', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3104, 4, '������', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3105, 3, '�տ������', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3106, 4, '�տ�����', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3107, 4, '��ľ����', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3108, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3109, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3110, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3111, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3112, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3113, 4, 'лͨ����', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3114, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3115, 4, '�ʲ���', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3116, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3117, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3118, 4, '�ٰ���', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3119, 4, '�Ƕ���', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3120, 4, '��¡��', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3121, 4, '����ľ��', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3122, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3123, 4, '�ڰ���', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3124, 4, '������', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3125, 3, '��������', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3126, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3127, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3128, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3129, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3130, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3131, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3132, 4, '����', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3133, 4, '�����', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3134, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3135, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3136, 4, '������', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3137, 3, '�������', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3138, 4, '������', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3139, 4, '������', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3140, 4, '������', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3141, 4, '������', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3142, 4, '�Ｊ��', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3143, 4, '������', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3144, 4, '������', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3145, 4, '������', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3146, 3, '��֥����', 3067);
commit;
prompt 1000 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3147, 4, '��֥��', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3148, 4, '����������', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3149, 4, '������', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3150, 4, 'ī����', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3151, 4, '������', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3152, 4, '������', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3153, 4, '����', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3154, 4, '������', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3155, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3156, 3, '������', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3157, 4, '�³���', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3158, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3159, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3160, 4, '�����', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3161, 4, 'δ����', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3162, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3163, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3164, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3165, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3166, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3167, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3168, 4, '����', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3169, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3170, 4, '������', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3171, 3, 'ͭ����', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3172, 4, '������', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3173, 4, 'ӡ̨��', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3174, 4, 'ҫ����', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3175, 4, '�˾���', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3176, 4, '������', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3177, 3, '������', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3178, 4, 'μ����', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3179, 4, '��̨��', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3180, 4, '�²���', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3181, 4, '������', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3182, 4, '�ɽ��', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3183, 4, '������', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3184, 4, 'ü��', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3185, 4, '¤��', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3186, 4, 'ǧ����', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3187, 4, '������', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3188, 4, '����', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3189, 4, '̫����', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3190, 4, '������', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3192, 4, '�ض���', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3193, 4, '������', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3195, 4, '��ԭ��', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3196, 4, '������', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3198, 4, '��Ȫ��', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3199, 4, '������', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3201, 4, '������', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3203, 4, '������', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3204, 4, '�书��', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3206, 4, '������', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3207, 3, 'μ����', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3209, 4, '����', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3210, 4, '������', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3212, 4, '������', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3213, 4, '�γ���', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3215, 4, '��ˮ��', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3216, 4, '��ƽ��', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3218, 4, '������', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3219, 4, '������', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3221, 4, '������', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3222, 4, '�ӳ���', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3224, 4, '�ӳ���', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3225, 4, '������', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3227, 4, '������', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3228, 4, '��Ȫ��', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3230, 4, '�崨��', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3232, 4, '������', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3233, 4, '������', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3235, 3, '������', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3236, 4, '��̨��', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3238, 4, '�ǹ���', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3239, 4, '����', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3241, 4, '����', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3242, 4, '��ǿ��', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3244, 4, '�����', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3245, 4, '������', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3247, 4, '������', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3248, 3, '������', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3250, 4, '��ľ��', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3251, 4, '������', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3253, 4, '������', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3254, 4, '������', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3256, 4, '��֬��', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3257, 4, '����', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3259, 4, '�彧��', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3260, 4, '������', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3262, 3, '������', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3263, 4, '������', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3265, 4, 'ʯȪ��', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3266, 4, '������', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3268, 4, '᰸���', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3269, 4, 'ƽ����', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3271, 4, 'Ѯ����', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3272, 4, '�׺���', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3274, 3, '������', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3275, 4, '������', 3274);
commit;
prompt 1100 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3277, 4, '������', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3278, 4, '������', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3280, 4, '����', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3281, 4, '��ˮ��', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3283, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3284, 3, '������', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3285, 4, '�ǹ���', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3287, 4, '������', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3288, 4, '������', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3290, 4, '������', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3291, 4, '������', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3293, 4, '������', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3294, 3, '��������', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3296, 4, '����', 3295);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3297, 4, '������', 3295);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3299, 3, '������', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3300, 4, '������', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3302, 4, '��Զ��', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3303, 4, '������', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3305, 4, '������', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3306, 3, '��ˮ��', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3308, 4, '�����', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3309, 4, '��ˮ��', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3311, 4, '�ʹ���', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3312, 4, '��ɽ��', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3314, 4, '������', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3315, 3, '������', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3316, 4, '������', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3317, 4, '������', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3318, 4, '������', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3320, 4, '������', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3321, 3, '��Ҵ��', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3322, 4, '������', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3323, 4, '����ԣ����������', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3325, 4, '������', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3326, 4, '��̨��', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3328, 4, '������', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3329, 3, 'ƽ����', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3331, 4, '������', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3332, 4, '��̨��', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3334, 4, '��ͤ��', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3335, 4, 'ׯ����', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3337, 4, '������', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3338, 3, '��Ȫ��', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3340, 4, '������', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3341, 4, '������', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3343, 4, '��������������������', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3344, 4, '������', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3345, 4, '�ػ���', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3346, 4, '������', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3348, 4, '������', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3349, 4, '�����', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3351, 4, '������', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3352, 4, '��ˮ��', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3353, 4, '������', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3354, 4, '����', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3355, 4, '��ԭ��', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3356, 4, '������', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3357, 3, '������', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3358, 4, '������', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3359, 4, 'ͨμ��', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3360, 4, '¤����', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3361, 4, 'μԴ��', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3362, 4, '�����', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3363, 4, '����', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3364, 4, '���', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3365, 4, '������', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3366, 3, '¤����', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3367, 4, '�䶼��', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3368, 4, '����', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3369, 4, '����', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3370, 4, '崲���', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3371, 4, '����', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3372, 4, '������', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3373, 4, '����', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3374, 4, '����', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3375, 4, '������', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3376, 4, '������', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3377, 3, '���Ļ���������', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3378, 4, '������', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3379, 4, '������', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3380, 4, '������', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3381, 4, '������', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3382, 4, '�����', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3383, 4, '������', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3384, 4, '������������', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3385, 4, '��ʯɽ�����嶫����������������', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3386, 4, '������', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3387, 3, '���ϲ���������', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3388, 4, '������', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3389, 4, '��̶��', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3390, 4, '׿����', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3391, 4, '������', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3392, 4, '������', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3393, 4, '������', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3394, 4, 'µ����', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3395, 4, '�ĺ���', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3396, 4, '������', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3397, 2, '�ຣʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3398, 3, '������', 3397);
commit;
prompt 1200 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3399, 4, '�Ƕ���', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3400, 4, '������', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3401, 4, '������', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3402, 4, '�Ǳ���', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3403, 4, '��ͨ��������������', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3404, 4, '������', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3405, 4, '��Դ��', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3406, 4, '������', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3407, 3, '��������', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3408, 4, 'ƽ����', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3409, 4, '��ͻ�������������', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3410, 4, '�ֶ���', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3411, 4, '��������������', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3412, 4, '��¡����������', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3413, 4, 'ѭ��������������', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3414, 4, '������', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3415, 3, '��������������', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3416, 4, '��Դ����������', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3417, 4, '������', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3418, 4, '������', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3419, 4, '�ղ���', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3420, 4, '������', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3421, 3, '���ϲ���������', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3422, 4, 'ͬ����', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3423, 4, '������', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3424, 4, '�����', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3425, 4, '�����ɹ���������', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3426, 4, '������', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3427, 3, '���ϲ���������', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3428, 4, '������', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3429, 4, 'ͬ����', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3430, 4, '�����', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3431, 4, '�˺���', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3432, 4, '������', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3433, 4, '������', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3434, 3, '�������������', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3435, 4, '������', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3436, 4, '������', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3437, 4, '�ʵ���', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3438, 4, '������', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3439, 4, '������', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3440, 4, '�����', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3441, 4, '������', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3442, 3, '��������������', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3443, 4, '������', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3444, 4, '�Ӷ���', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3445, 4, '�ƶ���', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3446, 4, '�ζ���', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3447, 4, '��ǫ��', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3448, 4, '��������', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3449, 4, '������', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3450, 3, '�����ɹ������������', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3451, 4, '���ľ��', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3452, 4, '�������', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3453, 4, '������', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3454, 4, '������', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3455, 4, '�����', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3456, 4, '������', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3457, 2, '���Ļ���������', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3458, 3, '������', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3459, 4, '������', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3460, 4, '������', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3461, 4, '�����', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3462, 4, '������', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3463, 4, '������', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3464, 4, '������', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3465, 4, '������', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3466, 3, 'ʯ��ɽ��', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3467, 4, '�������', 3466);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3468, 4, '��ũ��', 3466);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3469, 4, 'ƽ����', 3466);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3470, 4, '������', 3466);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3471, 3, '������', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3472, 4, '��ͨ��', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3473, 4, '�γ���', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3474, 4, 'ͬ����', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3475, 4, '��ͭϿ��', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3476, 4, '������', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3477, 3, '��ԭ��', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3478, 4, 'ԭ����', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3479, 4, '������', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3480, 4, '¡����', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3481, 4, '��Դ��', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3482, 4, '������', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3483, 4, '������', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3484, 3, '������', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3485, 4, 'ɳ��ͷ��', 3484);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3486, 4, '������', 3484);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3487, 4, '��ԭ��', 3484);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3488, 4, '������', 3484);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3489, 2, '�½�ά���������', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3490, 3, '��³ľ����', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3491, 4, '��ɽ��', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3492, 4, 'ɳ���Ϳ���', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3493, 4, '������', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3494, 4, 'ˮĥ����', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3495, 4, 'ͷ�ͺ���', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3496, 4, '�������', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3497, 4, '��ɽ��', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3498, 4, '�׶���', 3490);
commit;
prompt 1300 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3499, 4, '��³ľ����', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3500, 4, '������', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3501, 3, '����������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3502, 4, '��ɽ����', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3503, 4, '����������', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3504, 4, '�׼�̲��', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3505, 4, '�ڶ�����', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3506, 4, '������', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3507, 3, '��³������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3508, 4, '��³����', 3507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3509, 4, '۷����', 3507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3510, 4, '�п�ѷ��', 3507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3511, 4, '������', 3507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3512, 3, '���ܵ���', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3513, 4, '������', 3512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3514, 4, '������������������', 3512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3515, 4, '������', 3512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3516, 4, '������', 3512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3517, 3, '��������������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3518, 4, '������', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3519, 4, '������', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3520, 4, '��Ȫ��', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3521, 4, '��ͼ����', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3522, 4, '����˹��', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3523, 4, '��̨��', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3524, 4, '��ľ������', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3525, 4, 'ľ�ݹ�����������', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3526, 4, '������', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3527, 3, '���������ɹ�������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3528, 4, '������', 3527);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3529, 4, '������', 3527);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3530, 4, '��Ȫ��', 3527);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3531, 4, '������', 3527);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3532, 3, '���������ɹ�������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3533, 4, '�������', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3534, 4, '��̨��', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3535, 4, 'ξ����', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3536, 4, '��Ǽ��', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3537, 4, '��ĩ��', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3538, 4, '���Ȼ���������', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3539, 4, '�;���', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3540, 4, '��˶��', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3541, 4, '������', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3542, 4, '������', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3543, 3, '�����յ���', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3544, 4, '��������', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3545, 4, '������', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3546, 4, '�⳵��', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3547, 4, 'ɳ����', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3548, 4, '�º���', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3549, 4, '�ݳ���', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3550, 4, '��ʲ��', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3551, 4, '��������', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3552, 4, '��ƺ��', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3553, 4, '������', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3554, 3, '�������տ¶�����������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3555, 4, '��ͼʲ��', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3556, 4, '��������', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3557, 4, '��������', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3558, 4, '��ǡ��', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3560, 3, '��ʲ����', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3561, 4, '��ʲ��', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3563, 4, '������', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3564, 4, 'Ӣ��ɳ��', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3566, 4, 'ɯ����', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3567, 4, 'Ҷ����', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3569, 4, '���պ���', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3570, 4, '٤ʦ��', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3572, 4, '��ʲ�����������������', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3573, 4, '������', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3575, 4, '������', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3576, 4, '������', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3577, 4, 'ī����', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3579, 4, '������', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3580, 4, '������', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3582, 4, '�����', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3583, 4, '������', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3585, 4, '������', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3586, 4, '������', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3587, 4, '������', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3588, 4, '�첼�������������', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3590, 4, '������', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3591, 4, '��Դ��', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3593, 4, '�ؿ�˹��', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3594, 4, '���տ���', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3596, 3, '���ǵ���', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3597, 4, '������', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3599, 4, '������', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3600, 4, 'ɳ����', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3602, 4, 'ԣ����', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3603, 4, '�Ͳ��������ɹ�������', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3605, 3, '����̩����', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3606, 4, '����̩��', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3608, 4, '������', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3609, 4, '������', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3610, 4, '���ͺ���', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3612, 4, '��ľ����', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3613, 4, '������', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3615, 4, '��������', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3616, 4, 'ͼľ�����', 3489);
commit;
prompt 1400 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3618, 2, '̨��ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3619, 3, '̨����', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3620, 4, '������', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3622, 4, '��ɽ��', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3623, 4, '��ɽ��', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3625, 4, '����', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3626, 4, '������', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3628, 4, '��Ͷ��', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3629, 4, '�ں���', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3631, 4, '��ɽ��', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3632, 4, '������', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3634, 4, '������', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3635, 4, 'ǰ����', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3637, 4, '������', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3638, 4, '��ɽ��', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3640, 4, 'ǰ����', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3641, 4, '������', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3643, 4, '�����', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3644, 4, 'С����', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3646, 3, '̨����', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3647, 4, '������', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3649, 4, '����', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3651, 4, '��ƽ��', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3652, 4, '������', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3654, 3, '̨����', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3655, 4, '����', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3657, 4, '����', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3659, 4, '����', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3660, 4, '������', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3662, 4, '������', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3663, 4, '������', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3665, 3, '��Ͷ��', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3666, 3, '��¡��', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3668, 4, '������', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3669, 4, '������', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3671, 4, '������', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3672, 4, 'ůů��', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3674, 4, '������', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3675, 3, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3677, 4, '����', 3675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3679, 4, '������', 3675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3680, 3, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3682, 4, '����', 3680);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3683, 4, '������', 3680);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3685, 4, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3686, 4, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3688, 4, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3689, 4, '̨����', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3691, 4, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3692, 4, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3694, 4, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3695, 4, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3697, 4, '������', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3698, 4, '�����', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3700, 3, '��۵�', 3699);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3701, 3, '����', 3699);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3702, 3, '�½�', 3699);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3703, 2, '�����ر�������', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3705, 3, '�뵺', 3703);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3706, 2, '����', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3707, 3, '����', 3706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (235, 4, '������', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (238, 4, '����', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (240, 4, '������', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (243, 2, 'ɽ��ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (246, 4, 'ӭ����', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (249, 4, '�������', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (253, 4, '¦����', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (256, 3, '��ͬ��', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (259, 4, '�Ͻ���', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (262, 4, '������', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (265, 4, '��Դ��', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (268, 4, '������', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (271, 4, '����', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (273, 4, 'ƽ����', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (276, 3, '������', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (279, 4, '������', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (282, 4, '������', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (285, 4, '����', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (288, 4, '����', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (290, 4, '������', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (293, 4, '����', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (296, 4, '�괨��', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (299, 4, '������', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (302, 4, 'ƽ³��', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (306, 4, '������', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (309, 4, '�ܴ���', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (312, 4, '��˳��', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (315, 4, '̫����', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (318, 4, '��ʯ��', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (321, 3, '�˳���', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (324, 4, '������', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (327, 4, '�����', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (330, 4, '����', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (332, 4, '�ǳ���', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (335, 4, '������', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (338, 4, '������', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (341, 4, '������', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (344, 4, '�����', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (347, 4, '������', 336);
commit;
prompt 1500 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (350, 4, 'ԭƽ��', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (353, 4, 'Ң����', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (356, 4, '�����', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (359, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (362, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (365, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (368, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (371, 3, '������', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (374, 4, '������', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (377, 4, '������', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (380, 4, '��ɽ��', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (383, 4, 'Т����', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (593, 4, '�ϱ���', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (596, 4, '������', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (599, 4, '������', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (602, 4, 'ϸ����', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (605, 4, '������', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (608, 4, '��ʥ��', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (611, 4, '̫�Ӻ���', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (614, 4, '������', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (617, 4, '��¡̨��', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (621, 3, '������', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (624, 4, '������', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (627, 4, '����ɽ��', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (630, 3, '������', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (633, 4, '������', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (636, 4, '��Ʊ��', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (639, 3, '��«����', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (643, 4, '������', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (646, 4, '������', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (649, 4, '�Ϲ���', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (652, 4, '������', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (655, 4, 'ũ����', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (658, 4, '�»���', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (661, 4, '���ü���������', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (666, 4, '��̶��', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (669, 4, '������', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (672, 4, '������', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (675, 3, '��ƽ��', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (678, 4, '������', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2151, 4, '������', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2154, 4, '������', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2157, 4, '������', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2160, 4, '������', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2163, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2166, 4, '������', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2169, 4, '��ͬ��', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2172, 4, '�ƽ�����������', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2177, 3, '¦����', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2180, 4, '�»���', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2183, 4, '������', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2186, 4, '��Ϫ��', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2189, 4, '������', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2192, 4, '��ɽ��', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2196, 4, '������', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2199, 4, '�����', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2202, 4, '��خ��', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2205, 4, '�ܸ���', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2208, 4, '��ɽ��', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2211, 4, '�佭��', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2214, 4, 'ʼ����', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2217, 4, '��Դ����������', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2222, 3, '������', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2225, 4, '��ɽ��', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2228, 4, '������', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2231, 4, '������', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2234, 4, '������', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2237, 3, '��ͷ��', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2240, 4, '婽���', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2243, 4, '�κ���', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2246, 3, '��ɽ��', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2249, 4, '˳����', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2252, 4, '������', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2255, 4, '������', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2258, 4, '��ƽ��', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2467, 4, '��������������', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2472, 4, '�����', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2475, 4, '�޳�������������', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2480, 4, '������', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2483, 4, '�˱���', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2486, 4, '������', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2489, 4, '������', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2492, 4, '������', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2495, 4, '������', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2498, 4, '������', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2502, 4, '������', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2506, 3, '������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2509, 4, '������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2512, 4, '������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2515, 4, '������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2518, 4, '��������������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2523, 4, '��ɳȺ��', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2526, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2529, 3, '������', 2528);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2532, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2535, 4, 'ɳƺ����', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2539, 4, '��ʢ��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2542, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2545, 4, '�뽭��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2548, 4, '������', 2529);
commit;
prompt 1600 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2551, 4, '��ƽ��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2554, 4, '�潭��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2557, 4, '����', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2559, 4, '�����', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2562, 4, 'ʯ��������������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2568, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2571, 2, '�Ĵ�ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2574, 4, '������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2577, 4, '�ɻ���', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2580, 4, '�¶���', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2583, 4, '˫����', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2586, 4, '�ѽ���', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2589, 4, '������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2592, 4, '������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2595, 4, '������', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2598, 4, '����', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2600, 4, '������', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2603, 4, '����', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2606, 4, '�α���', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2609, 4, '������', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2612, 4, '����', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2614, 4, '������', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2617, 3, '������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2620, 4, '�޽���', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2623, 4, '������', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2626, 4, '������', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2832, 4, '������', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2835, 4, 'ƽ����', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2838, 4, '���벼��������������', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2844, 4, '��������������', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2849, 4, '�غ�������������', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2855, 4, '������', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2858, 4, '�����', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2861, 4, '������', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2864, 4, '�Ͻ���', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2867, 4, '��ɳ��', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2870, 4, '���������������������', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2877, 4, '������', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2880, 4, '������', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2883, 4, '̨����', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2886, 4, '�ӽ���', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2889, 4, '��կ��', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2892, 4, '������', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2895, 4, '����', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2898, 4, 'ƽ����', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2901, 4, '������', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2904, 4, '������', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2907, 4, '�廪��', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2910, 4, '��ɽ��', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2913, 4, '������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2916, 4, 'ʯ������������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2921, 4, '������', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1, 1, '�й�', null);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2, 2, '����', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3, 3, '������', 2);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (4, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (5, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (6, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (7, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (8, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (9, 4, '��̨��', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (10, 4, 'ʯ��ɽ��', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (11, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (12, 4, '��ͷ����', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (13, 4, '��ɽ��', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (14, 4, 'ͨ����', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (15, 4, '˳����', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (16, 4, '��ƽ��', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (17, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (18, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (19, 4, 'ƽ����', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (20, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (21, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (22, 4, '������', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (23, 2, '���', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (24, 3, '�����', 23);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (25, 4, '��ƽ��', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (26, 4, '�Ӷ���', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (27, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (28, 4, '�Ͽ���', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (29, 4, '�ӱ���', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (30, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (31, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (32, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (33, 4, '�����', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (34, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (35, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (36, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (37, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (38, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (39, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (40, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (41, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (42, 4, '����', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (43, 4, '������', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (44, 2, '�ӱ�ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (45, 3, 'ʯ��ׯ��', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (46, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (47, 4, '�Ŷ���', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (48, 4, '������', 45);
commit;
prompt 1700 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (49, 4, '�»���', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (50, 4, '�������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (51, 4, 'ԣ����', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (52, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (53, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (54, 4, '�����', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (55, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (56, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (57, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (58, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (59, 4, '�޻���', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (60, 4, '�޼���', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (61, 4, 'ƽɽ��', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (62, 4, 'Ԫ����', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (63, 4, '����', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (64, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (65, 4, '޻����', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (66, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (67, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (68, 4, '¹Ȫ��', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (69, 4, '������', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (70, 3, '��ɽ��', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (71, 4, '·����', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (72, 4, '·����', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (73, 4, '��ұ��', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (74, 4, '��ƽ��', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (75, 4, '������', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (76, 4, '������', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (77, 4, '����', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (78, 4, '������', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (79, 4, '��ͤ��', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (80, 4, 'Ǩ����', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (81, 4, '������', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (82, 4, '�ƺ���', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (83, 4, '����', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (84, 4, 'Ǩ����', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (85, 4, '������', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (86, 3, '�ػʵ���', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (87, 4, '������', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (88, 4, 'ɽ������', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (89, 4, '��������', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (90, 4, '��������������', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (91, 4, '������', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (92, 4, '������', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (93, 4, '¬����', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (94, 4, '������', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (95, 4, '���ü���������', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (96, 3, '������', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (97, 4, '��ɽ��', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (98, 4, '��̨��', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (99, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (100, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (101, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (102, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (103, 4, '�ɰ���', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (104, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (105, 4, '����', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (106, 4, '����', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (107, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (108, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (109, 4, '����', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (110, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (111, 4, '��ƽ��', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (112, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (113, 4, 'κ��', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (114, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (115, 4, '�䰲��', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (116, 4, '������', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (117, 3, '��̨��', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (118, 4, '�Ŷ���', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (119, 4, '������', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (120, 4, '��̨��', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (121, 4, '�ٳ���', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (122, 4, '������', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (123, 4, '������', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (124, 4, '¡Ң��', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (125, 4, '����', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (126, 4, '�Ϻ���', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (127, 4, '������', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (128, 4, '��¹��', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (129, 4, '�º���', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (130, 4, '������', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (131, 4, 'ƽ����', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (132, 4, '����', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (133, 4, '�����', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (134, 4, '������', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (135, 4, '�Ϲ���', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (136, 4, 'ɳ����', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (137, 4, '������', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (138, 3, '������', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (139, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (140, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (141, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (142, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (143, 4, '��Է��', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (144, 4, '�ˮ��', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (145, 4, '��ƽ��', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (146, 4, '��ˮ��', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (147, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (148, 4, '����', 138);
commit;
prompt 1800 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (149, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (150, 4, '�ݳ���', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (151, 4, '�Դ��', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (152, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (153, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (154, 4, '����', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (155, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (156, 4, '���', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (157, 4, '˳ƽ��', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (158, 4, '��Ұ��', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (159, 4, '����', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (160, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (161, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (162, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (163, 4, '�߱�����', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (164, 4, '�߿���', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (165, 4, '������', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (166, 3, '�żҿ���', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (167, 4, '�Ŷ���', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (168, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (169, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (170, 4, '�»�԰��', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (171, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (172, 4, '�ű���', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (173, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (174, 4, '��Դ��', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (175, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (176, 4, 'ε��', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (177, 4, '��ԭ��', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (178, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1880, 4, '�̳���', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1883, 4, '������', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2089, 4, '������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2092, 4, '������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2095, 4, '������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2098, 4, '�ǲ�����������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2103, 4, '��Ϫ��', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2106, 4, '������', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2109, 4, '������', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2112, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2115, 4, '������', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2118, 4, '�����', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2121, 4, '������', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2124, 4, '������', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2127, 4, 'ɣֲ��', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2130, 4, '������', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2133, 4, '�ҽ���', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2136, 4, '������', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2139, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2142, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2145, 4, '�����', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2148, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (179, 4, '��ȫ��', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (180, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (181, 4, '��¹��', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (192, 4, '��ƽ��', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (193, 4, '¡����', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (194, 4, '��������������', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (195, 4, '�������������', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (196, 4, 'Χ�������ɹ���������', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (197, 4, '������', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (198, 3, '������', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (199, 4, '�»���', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (200, 4, '�˺���', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (201, 4, '����', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (202, 4, '����', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (203, 4, '������', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (204, 4, '������', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (205, 4, '��ɽ��', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (206, 4, '������', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (207, 4, '��Ƥ��', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (208, 4, '������', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (209, 4, '����', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (210, 4, '�ϴ����������', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (211, 4, '��ͷ��', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (212, 4, '������', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (213, 4, '������', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (214, 4, '�Ӽ���', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (215, 4, '������', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (216, 3, '�ȷ���', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (217, 4, '������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (218, 4, '������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (219, 4, '�̰���', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (220, 4, '������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (221, 4, '�����', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (222, 4, '�����', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (223, 4, '�İ���', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (224, 4, '�󳧻���������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (225, 4, '������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (226, 4, '�ོ���ü���������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (227, 4, '������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (228, 4, '������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (229, 4, '������', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (230, 3, '��ˮ��', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (231, 4, '�ҳ���', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (232, 4, '��ǿ��', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (233, 4, '������', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (234, 4, '��ǿ��', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (236, 4, '��ƽ��', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (237, 4, '�ʳ���', 230);
commit;
prompt 1900 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (239, 4, '������', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (241, 4, '������', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (242, 4, '������', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (244, 3, '̫ԭ��', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (245, 4, 'С����', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (247, 4, '�ӻ�����', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (248, 4, '���ƺ��', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (250, 4, '��Դ��', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (251, 4, '������', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (252, 4, '������', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (254, 4, '�Ž���', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (255, 4, '������', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (257, 4, '����', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (258, 4, '����', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (260, 4, '������', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (261, 4, '������', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (263, 4, '������', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (264, 4, '������', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (266, 4, '������', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (267, 4, '��ͬ��', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (269, 3, '��Ȫ��', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (270, 4, '����', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (272, 4, '����', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (274, 4, '����', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (275, 4, '������', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (277, 4, '������', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (278, 4, '��ԫ��', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (280, 4, 'ƽ˳��', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (281, 4, '�����', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (283, 4, '������', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (284, 4, '������', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (286, 4, '��Դ��', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (287, 4, 'º����', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (289, 4, '����', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (291, 4, '������', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (292, 3, '������', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (294, 4, '��ˮ��', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (295, 4, '������', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (297, 4, '������', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (298, 4, '��ƽ��', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (300, 3, '˷����', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (301, 4, '˷����', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (303, 4, 'ɽ����', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (304, 4, 'Ӧ��', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (305, 4, '������', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (307, 4, '������', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (308, 3, '������', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (310, 4, '������', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (311, 4, '��Ȩ��', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (313, 4, '������', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (314, 4, '������', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (316, 4, '����', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (317, 4, 'ƽң��', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (319, 4, '������', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (320, 4, '������', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (322, 4, '�κ���', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (323, 4, '�����', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (325, 4, '��ϲ��', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (326, 4, '�ɽ��', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (328, 4, '���', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (329, 4, 'ԫ����', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (331, 4, 'ƽ½��', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (333, 4, '������', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (334, 4, '�ӽ���', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (336, 3, '������', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (337, 4, '�ø���', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (339, 4, '��̨��', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (340, 4, '����', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (342, 4, '������', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (343, 4, '������', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (345, 4, '��կ��', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (346, 4, '����', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (348, 4, '������', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (349, 4, 'ƫ����', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (351, 4, '������', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (352, 3, '�ٷ���', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (354, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (355, 4, '�����', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (357, 4, '�鶴��', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (358, 4, '����', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (360, 4, '��ɽ��', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (361, 4, '����', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (363, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (364, 4, '����', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (366, 4, '����', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (367, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (369, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (370, 4, '������', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (372, 4, '��ʯ��', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (373, 4, '��ˮ��', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (375, 4, '����', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (376, 4, '����', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (378, 4, 'ʯ¥��', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (379, 4, '���', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (381, 4, '������', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (382, 4, '������', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (384, 4, '������', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (385, 4, '������', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (386, 2, '���ɹ�������', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (387, 3, '���ͺ�����', 386);
commit;
prompt 2000 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (388, 4, '�³���', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (389, 4, '������', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (390, 4, '��Ȫ��', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (391, 4, '������', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (392, 4, '��Ĭ������', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (393, 4, '�п�����', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (394, 4, '���ָ����', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (395, 4, '��ˮ����', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (396, 4, '�䴨��', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (397, 4, '������', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (398, 3, '��ͷ��', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (399, 4, '������', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (400, 4, '��������', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (401, 4, '��ɽ��', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (402, 4, 'ʯ����', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (403, 4, '���ƿ���', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (404, 4, '��ԭ��', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (405, 4, '��Ĭ������', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (406, 4, '������', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (407, 4, '�����ï����������', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (408, 4, '������', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (409, 3, '�ں���', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (410, 4, '��������', 409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (411, 4, '������', 409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (412, 4, '�ڴ���', 409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (413, 4, '������', 409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (414, 3, '�����', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (415, 4, '��ɽ��', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (416, 4, 'Ԫ��ɽ��', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (417, 4, '��ɽ��', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (418, 4, '��³�ƶ�����', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (419, 4, '��������', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (420, 4, '��������', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (421, 4, '������', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (422, 4, '��ʲ������', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (423, 4, '��ţ����', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (424, 4, '��������', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (425, 4, '������', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (426, 4, '������', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (427, 4, '������', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (428, 3, 'ͨ����', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (429, 4, '�ƶ�����', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (430, 4, '�ƶ�����������', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (431, 4, '�ƶ����������', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (432, 4, '��³��', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (433, 4, '������', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (434, 4, '������', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (435, 4, '��³����', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (436, 4, '���ֹ�����', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (437, 4, '������', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (438, 3, '������˹��', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (439, 4, '��ʤ��', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (440, 4, '��������', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (441, 4, '׼�����', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (442, 4, '���п�ǰ��', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (443, 4, '���п���', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (444, 4, '������', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (445, 4, '������', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (446, 4, '���������', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (447, 4, '������', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (448, 3, '���ױ�����', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (449, 4, '��������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (450, 4, '������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (451, 4, 'Ī�����ߴ��Ӷ���������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (452, 4, '���״�������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (453, 4, '���¿���������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (454, 4, '�°Ͷ�����', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (455, 4, '�°Ͷ�������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (456, 4, '�°Ͷ�������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (457, 4, '��������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (458, 4, '����ʯ��', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (459, 4, '��������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (460, 4, '���������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (461, 4, '������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (462, 4, '������', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (463, 3, '�����׶���', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (464, 4, '�ٺ���', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (465, 4, '��ԭ��', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (466, 4, '�����', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (467, 4, '������ǰ��', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (468, 4, '����������', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (469, 4, '�����غ���', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (470, 4, '��������', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (471, 4, '������', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (472, 3, '�����첼��', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (473, 4, '������', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (474, 4, '׿����', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (475, 4, '������', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (476, 4, '�̶���', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (477, 4, '�˺���', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (478, 4, '������', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (479, 4, '���������ǰ��', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (480, 4, '�������������', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (481, 4, '������������', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (482, 4, '��������', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (483, 4, '������', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (484, 4, '������', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (485, 3, '�˰���', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (486, 4, '����������', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (487, 4, '����ɽ��', 485);
commit;
prompt 2100 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (488, 4, '�ƶ�������ǰ��', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (489, 4, '�ƶ�����������', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (490, 4, '��������', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (491, 4, 'ͻȪ��', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (492, 4, '������', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (493, 3, '���ֹ�����', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (494, 4, '����������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (495, 4, '���ֺ�����', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (496, 4, '���͸���', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (497, 4, '����������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (498, 4, '����������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (499, 4, '������������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (500, 4, '������������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (501, 4, '̫������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (502, 4, '�����', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (503, 4, '�������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (504, 4, '������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (505, 4, '������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (506, 4, '������', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (507, 3, '��������', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (508, 4, '����������', 507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (509, 4, '����������', 507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (510, 4, '�������', 507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (511, 4, '������', 507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (512, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (513, 3, '������', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (514, 4, '��ƽ��', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (515, 4, '�����', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (516, 4, '����', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (517, 4, '�ʹ���', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (518, 4, '������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (519, 4, '�ռ�����', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (520, 4, '������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (521, 4, '�³�����', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (522, 4, '�ں���', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (523, 4, '������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (524, 4, '��ƽ��', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (525, 4, '������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (526, 4, '������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (527, 4, '��������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (528, 4, '��ʿ������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (529, 4, '������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (530, 4, '������', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (531, 3, '������', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (532, 4, '��ɽ��', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (533, 4, '������', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (534, 4, 'ɳ�ӿ���', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (182, 4, '�����', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (183, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (184, 4, '������', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (185, 3, '�е���', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (186, 4, '˫����', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (187, 4, '˫����', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (188, 4, 'ӥ��Ӫ�ӿ���', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (189, 4, '�е���', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (190, 4, '��¡��', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (191, 4, 'ƽȪ��', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (535, 4, '�ʾ�����', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (536, 4, '��˳����', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (537, 4, '������', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (538, 4, '������', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (539, 4, '������', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (540, 4, '�߷�����', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (541, 4, '��������', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (542, 4, 'ׯ����', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (543, 4, '��ǰ��', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (544, 4, '������', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (545, 3, '��ɽ��', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (546, 4, '������', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (547, 4, '������', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (548, 4, '��ɽ��', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (549, 4, 'ǧɽ��', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (550, 4, '̨����', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (551, 4, '�������������', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (552, 4, '������', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (553, 4, '������', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (554, 4, '������', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (555, 3, '��˳��', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (556, 4, '�¸���', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (557, 4, '������', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (558, 4, '������', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (559, 4, '˳����', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (560, 4, '��˳��', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (561, 4, '�±�����������', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (562, 4, '��ԭ����������', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (563, 4, '������', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (564, 3, '��Ϫ��', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (565, 4, 'ƽɽ��', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (566, 4, 'Ϫ����', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (567, 4, '��ɽ��', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (568, 4, '�Ϸ���', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (569, 4, '��Ϫ����������', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (570, 4, '��������������', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (571, 4, '������', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (572, 3, '������', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (573, 4, 'Ԫ����', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (574, 4, '������', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (575, 4, '����', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (576, 4, '�������������', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (577, 4, '������', 572);
commit;
prompt 2200 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (578, 4, '�����', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (579, 4, '������', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (580, 3, '������', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (581, 4, '������', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (582, 4, '�����', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (583, 4, '̫����', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (584, 4, '��ɽ��', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (585, 4, '����', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (586, 4, '�躣��', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (587, 4, '������', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (588, 4, '������', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (589, 3, 'Ӫ����', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (590, 4, 'վǰ��', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (591, 4, '������', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (592, 4, '����Ȧ��', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (594, 4, '������', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (595, 4, '��ʯ����', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (597, 3, '������', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (598, 4, '������', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (600, 4, '̫ƽ��', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (601, 4, '�������', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (603, 4, '�����ɹ���������', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (604, 4, '������', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (606, 3, '������', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (607, 4, '������', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (609, 4, '��ΰ��', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (610, 4, '��������', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (612, 4, '������', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (613, 4, '������', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (615, 3, '�̽���', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (616, 4, '˫̨����', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (618, 4, '������', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (619, 4, '��ɽ��', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (620, 4, '������', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (622, 4, '������', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (623, 4, '�����', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (625, 4, '������', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (626, 4, '��ͼ��', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (628, 4, '��ԭ��', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (629, 4, '������', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (631, 4, '˫����', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (632, 4, '������', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (634, 4, '��ƽ��', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (635, 4, '�����������ɹ���������', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (637, 4, '��Դ��', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (638, 4, '������', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (640, 4, '��ɽ��', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (641, 4, '������', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (642, 4, '��Ʊ��', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (644, 4, '������', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (645, 4, '�˳���', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (647, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (648, 3, '������', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (650, 4, '�����', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (651, 4, '������', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (653, 4, '��԰��', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (654, 4, '˫����', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (656, 4, '��̨��', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (657, 4, '������', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (659, 4, '���¼�����ҵ������', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (660, 4, '������ҵ������', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (662, 4, '�������ο�����', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (663, 4, '������', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (664, 3, '������', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (665, 4, '������', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (667, 4, '��Ӫ��', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (668, 4, '������', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (670, 4, '�Ժ���', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (671, 4, '�����', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (673, 4, '��ʯ��', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (674, 4, '������', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (676, 4, '������', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (677, 4, '������', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (679, 4, '��ͨ����������', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (680, 4, '��������', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (682, 4, '������', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (683, 3, '��Դ��', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (685, 4, '������', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (686, 4, '������', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (688, 4, '������', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (689, 3, 'ͨ����', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (691, 4, '��������', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (692, 4, 'ͨ����', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (694, 4, '������', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (695, 4, '÷�ӿ���', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (697, 4, '������', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (698, 3, '��ɽ��', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (700, 4, '������', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (701, 4, '������', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (702, 4, '���׳�����������', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (704, 4, '�ٽ���', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (705, 4, '������', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (707, 4, '������', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (708, 4, 'ǰ������˹�ɹ���������', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (710, 4, 'Ǭ����', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (711, 4, '������', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (713, 3, '�׳���', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (714, 4, '䬱���', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (716, 4, 'ͨ����', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (717, 4, '�����', 713);
commit;
prompt 2300 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (719, 4, '������', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (720, 3, '�ӱ߳�����������', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (722, 4, 'ͼ����', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (723, 4, '�ػ���', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (725, 4, '������', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (726, 4, '������', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (728, 4, '��ͼ��', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (729, 4, '������', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (731, 3, '��������', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (732, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (734, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (735, 4, '�㷻��', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (737, 4, 'ƽ����', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (738, 4, '�ɱ���', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (740, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (741, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (743, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (744, 4, 'ľ����', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (745, 4, 'ͨ����', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (746, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (747, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (748, 4, '˫����', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (749, 4, '��־��', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (750, 4, '�峣��', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (752, 4, '������', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (753, 3, '���������', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (754, 4, '��ɳ��', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (755, 4, '������', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (756, 4, '������', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (757, 4, '����Ϫ��', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (758, 4, '����������', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (759, 4, '����ɽ��', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (760, 4, '÷��˹���Ӷ�����', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (761, 4, '������', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (762, 4, '������', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (763, 4, '̩����', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (764, 4, '������', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (765, 4, '��ԣ��', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (766, 4, '��ɽ��', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (767, 4, '�˶���', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (768, 4, '��Ȫ��', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (769, 4, 'ګ����', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (770, 4, '������', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (771, 3, '������', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (772, 4, '������', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (773, 4, '��ɽ��', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (774, 4, '�ε���', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (775, 4, '������', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (776, 4, '���Ӻ���', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (777, 4, '��ɽ��', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (778, 4, '������', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (779, 4, '������', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (780, 4, '��ɽ��', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (781, 4, '������', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (782, 3, '�׸���', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (783, 4, '������', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (784, 4, '��ũ��', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (785, 4, '��ɽ��', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (786, 4, '�˰���', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (787, 4, '��ɽ��', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (788, 4, '��ɽ��', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (789, 4, '�ܱ���', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (790, 4, '�����', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (791, 4, '������', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (792, 3, '˫Ѽɽ��', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (793, 4, '��ɽ��', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (794, 4, '�붫��', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (795, 4, '�ķ�̨��', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (796, 4, '��ɽ��', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (797, 4, '������', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (798, 4, '������', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (799, 4, '������', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (800, 4, '�ĺ���', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (801, 4, '������', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (802, 3, '������', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (803, 4, '����ͼ��', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (804, 4, '������', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (805, 4, '�ú�·��', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (806, 4, '�����', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (807, 4, '��ͬ��', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (808, 4, '������', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (809, 4, '��Դ��', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (810, 4, '�ֵ���', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (811, 4, '�Ŷ������ɹ���������', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (812, 4, '������', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (813, 3, '������', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (814, 4, '������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (815, 4, '�ϲ���', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (816, 4, '�Ѻ���', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (817, 4, '������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (818, 4, '������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (819, 4, '������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (820, 4, '��Ϫ��', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (821, 4, '��ɽ����', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (822, 4, '��Ӫ��', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (823, 4, '�������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (824, 4, '��������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (825, 4, '������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (826, 4, '��������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (827, 4, '������', 813);
commit;
prompt 2400 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (828, 4, '�ϸ�����', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (829, 4, '������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (830, 4, '������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (831, 4, '������', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (832, 3, '��ľ˹��', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (833, 4, '������', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (834, 4, '������', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (835, 4, 'ǰ����', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (836, 4, '������', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (837, 4, '����', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (838, 4, '������', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (839, 4, '�봨��', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (840, 4, '��ԭ��', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (841, 4, '��Զ��', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (842, 4, 'ͬ����', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (843, 4, '������', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (844, 4, '������', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (845, 3, '��̨����', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (846, 4, '������', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (847, 4, '��ɽ��', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (848, 4, '���Ӻ���', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (849, 4, '������', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (850, 4, '������', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (851, 3, 'ĵ������', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (852, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (853, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (854, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (855, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (856, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (857, 4, '�ֿ���', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (858, 4, '��Һ���', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (859, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (860, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (861, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (862, 4, '������', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (863, 3, '�ں���', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (864, 4, '������', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (865, 4, '�۽���', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (866, 4, 'ѷ����', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (867, 4, '������', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (868, 4, '������', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (869, 4, '���������', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (870, 4, '������', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (871, 3, '�绯��', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (872, 4, '������', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (873, 4, '������', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (874, 4, '������', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (875, 4, '�����', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (876, 4, '�찲��', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (877, 4, '��ˮ��', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (878, 4, '������', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (879, 4, '������', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (880, 4, '�ض���', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (881, 4, '������', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (882, 4, '������', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (883, 3, '���˰������', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (884, 4, '������', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (885, 4, '������', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (886, 4, 'Į����', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (887, 4, '�Ӹ������', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (888, 4, '������', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (889, 2, '�Ϻ�', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (890, 3, '�Ϻ���', 889);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (891, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (892, 4, '¬����', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (893, 4, '�����', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (894, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (895, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (896, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (897, 4, 'բ����', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (898, 4, '�����', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (899, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (900, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (901, 4, '��ɽ��', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (902, 4, '�ζ���', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (903, 4, '�ֶ�����', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (904, 4, '��ɽ��', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (905, 4, '�ɽ���', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (906, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (907, 4, '�ϻ���', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (908, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (909, 4, '��ɳ��', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (910, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (911, 4, '������', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (912, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (913, 3, '�Ͼ���', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (914, 4, '������', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (915, 4, '������', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (916, 4, '�ػ���', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (917, 4, '������', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (918, 4, '��¥��', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (919, 4, '�¹���', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (920, 4, '�ֿ���', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (921, 4, '��ϼ��', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (922, 4, '�껨̨��', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (923, 4, '������', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (924, 4, '������', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (925, 4, '��ˮ��', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (926, 4, '�ߴ���', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (927, 4, '������', 913);
commit;
prompt 2500 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (928, 3, '������', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (929, 4, '�簲��', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (930, 4, '�ϳ���', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (931, 4, '������', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (932, 4, '��ɽ��', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (933, 4, '��ɽ��', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (934, 4, '������', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (935, 4, '������', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (936, 4, '������', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (937, 4, '����', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (938, 4, '������', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (939, 3, '������', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (940, 4, '��¥��', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (941, 4, '������', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (942, 4, '������', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (943, 4, '������', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (944, 4, 'Ȫɽ��', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (945, 4, '����', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (946, 4, '����', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (948, 4, '�����', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (949, 4, '������', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (951, 4, '������', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (952, 3, '������', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (954, 4, '��¥��', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (955, 4, '��������', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (957, 4, '�����', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (958, 4, '������', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (960, 4, '������', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (961, 3, '������', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (963, 4, 'ƽ����', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (964, 4, '������', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (966, 4, '������', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (967, 4, '�����', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (969, 4, '�żҸ���', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (970, 4, '��ɽ��', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (972, 4, '̫����', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (973, 4, '����', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (975, 4, '������', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (976, 3, '��ͨ��', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (978, 4, '��բ��', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (979, 4, '������', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (981, 4, '������', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (982, 4, '�����', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (984, 4, '������', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (985, 4, '������', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (987, 3, '���Ƹ���', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (988, 4, '������', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (990, 4, '������', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (991, 4, '������', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (993, 4, '������', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (994, 4, '������', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (996, 3, '������', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (997, 4, '�����', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (999, 4, '������', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1000, 4, '������', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1002, 4, '������', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1003, 4, '������', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1005, 4, '������', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1006, 3, '�γ���', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1008, 4, '�ζ���', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1009, 4, '��ˮ��', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1011, 4, '������', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1012, 4, '������', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1014, 4, '��̨��', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1015, 4, '�����', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1017, 3, '������', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1018, 4, '������', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1020, 4, 'ά����', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1021, 4, '��Ӧ��', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1023, 4, '������', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1024, 4, '������', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1026, 4, '������', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1027, 3, '����', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1028, 4, '������', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1030, 4, '��ͽ��', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1031, 4, '������', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1033, 4, '������', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1034, 4, '������', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1036, 4, '������', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1037, 4, '�߸���', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1039, 4, '������', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1040, 4, '̩����', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1042, 4, '������', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1043, 3, '��Ǩ��', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1045, 4, '��ԥ��', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1046, 4, '������', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1048, 4, '������', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1049, 4, '������', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1051, 3, '������', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1052, 4, '�ϳ���', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1054, 4, '������', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1055, 4, '������', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1057, 4, '������', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1058, 4, '��ɽ��', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1060, 4, 'ͩ®��', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1061, 4, '������', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1063, 4, '������', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1064, 4, '�ٰ���', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1066, 3, '������', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1067, 4, '������', 1066);
commit;
prompt 2600 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1069, 4, '������', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1070, 4, '������', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1072, 4, '۴����', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1073, 4, '��ɽ��', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1075, 4, '��Ҧ��', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1076, 4, '��Ϫ��', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1078, 4, '������', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1079, 3, '������', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1081, 4, '������', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1082, 4, '걺���', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1084, 4, '������', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1085, 4, 'ƽ����', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1087, 4, '�ĳ���', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1088, 4, '̩˳��', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1090, 4, '������', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1091, 4, '������', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1093, 4, '�Ϻ���', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1094, 4, '������', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1096, 4, '������', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1097, 4, '������', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1099, 4, 'ͩ����', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1100, 4, '������', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1102, 4, '������', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1103, 4, '�����', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1105, 4, '������', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1106, 4, '������', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1108, 3, '������', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1109, 4, 'Խ����', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1111, 4, '�²���', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1112, 4, '������', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1114, 4, '������', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1115, 4, '������', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1117, 4, '�ĳ���', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1118, 4, '����', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1120, 4, '�ֽ���', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1121, 4, '�Ͱ���', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1122, 4, '��Ϫ��', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1123, 4, '������', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1124, 4, '������', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1125, 4, '������', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1126, 4, '������', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1127, 3, '������', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1128, 4, '�³���', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1129, 4, '�齭��', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1130, 4, '��ɽ��', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1131, 4, '������', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1132, 4, '������', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1133, 4, '��ɽ��', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1134, 4, '������', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1135, 3, '��ɽ��', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1136, 4, '������', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1137, 4, '������', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1138, 4, '�ɽ��', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1139, 4, '������', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1140, 4, '������', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1141, 3, '̨����', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1142, 4, '������', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1143, 4, '������', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1144, 4, '·����', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1145, 4, '����', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1146, 4, '������', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1147, 4, '��̨��', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1148, 4, '�ɾ���', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1149, 4, '������', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1150, 4, '�ٺ���', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1151, 4, '������', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1152, 3, '��ˮ��', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1153, 4, '������', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1154, 4, '������', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1155, 4, '������', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1156, 4, '�����', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1157, 4, '������', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1158, 4, '�ƺ���', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1159, 4, '��Ԫ��', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1160, 4, '�������������', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1161, 4, '��Ȫ��', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1162, 4, '������', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1163, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1164, 3, '�Ϸ���', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1165, 4, '������', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1166, 4, '®����', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1167, 4, '��ɽ��', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1168, 4, '������', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1169, 4, '������', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1170, 4, '�ʶ���', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1171, 4, '������', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1172, 4, '������', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1173, 4, '����', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1174, 4, '������', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1175, 3, '�ߺ���', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1176, 4, '������', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1177, 4, '߮����', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1178, 4, '𯽭��', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1179, 4, '��ɽ��', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1180, 4, '�ߺ���', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1181, 4, '������', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1182, 4, '������', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1183, 4, '������', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1184, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1185, 4, '���Ӻ���', 1184);
commit;
prompt 2700 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1186, 4, '��ɽ��', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1187, 4, '�����', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1188, 4, '������', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1189, 4, '��Զ��', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1190, 4, '�����', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1191, 4, '������', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1192, 4, '������', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1193, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1194, 4, '��ͨ��', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1195, 4, '�������', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1196, 4, 'л�Ҽ���', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1197, 4, '�˹�ɽ��', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1198, 4, '�˼���', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1199, 4, '��̨��', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1200, 4, '������', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1201, 3, '��ɽ��', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1202, 4, '���ׯ��', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1203, 4, '��ɽ��', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1204, 4, '��ɽ��', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1205, 4, '��Ϳ��', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1206, 4, '������', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1207, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1208, 4, '�ż���', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1209, 4, '��ɽ��', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1210, 4, '��ɽ��', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1211, 4, '�Ϫ��', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1212, 4, '������', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1213, 3, 'ͭ����', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1214, 4, 'ͭ��ɽ��', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1215, 4, 'ʨ��ɽ��', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1216, 4, '����', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1217, 4, 'ͭ����', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1218, 4, '������', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1219, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1220, 4, 'ӭ����', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1221, 4, '�����', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1222, 4, '������', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1223, 4, '������', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1224, 4, '������', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1225, 4, 'Ǳɽ��', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1226, 4, '̫����', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1227, 4, '������', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1228, 4, '������', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1229, 4, '������', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1230, 4, 'ͩ����', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1231, 4, '������', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1232, 3, '��ɽ��', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1233, 4, '��Ϫ��', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1234, 4, '��ɽ��', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1235, 4, '������', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1236, 4, '���', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1237, 4, '������', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1238, 4, '����', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1239, 4, '������', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1240, 4, '������', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1241, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1242, 4, '������', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1243, 4, '������', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1244, 4, '������', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1245, 4, 'ȫ����', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1246, 4, '��Զ��', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1247, 4, '������', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1248, 4, '�쳤��', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1249, 4, '������', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1250, 4, '������', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1251, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1252, 4, '�����', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1253, 4, '򣶫��', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1254, 4, '�Ȫ��', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1255, 4, '��Ȫ��', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1256, 4, '̫����', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1257, 4, '������', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1258, 4, '�����', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1259, 4, '������', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1260, 4, '������', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1261, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1262, 4, '������', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1263, 4, '�ɽ��', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1264, 4, '����', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1265, 4, '�����', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1266, 4, '����', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1267, 4, '������', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1268, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1269, 4, '�ӳ���', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1270, 4, '®����', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1271, 4, '��Ϊ��', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1272, 4, '��ɽ��', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1273, 4, '����', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1274, 4, '������', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1275, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1276, 4, '����', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1277, 4, 'ԣ����', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1278, 4, '����', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1279, 4, '������', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1280, 4, '�����', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1281, 4, '��կ��', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1282, 4, '��ɽ��', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1283, 4, '������', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1284, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1285, 4, '�۳���', 1284);
commit;
prompt 2800 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1286, 4, '������', 1284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1287, 4, '�ɳ���', 1284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1288, 4, '������', 1284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1289, 4, '������', 1284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1290, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1291, 4, '�����', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1292, 4, '������', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1293, 4, 'ʯ̨��', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1294, 4, '������', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1295, 4, '������', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1296, 3, '������', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1297, 4, '������', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1298, 4, '��Ϫ��', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1299, 4, '�����', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1300, 4, '����', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1301, 4, '��Ϫ��', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1302, 4, '캵���', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1303, 4, '������', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1304, 4, '������', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1305, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1306, 3, '������', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1307, 4, '��¥��', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1308, 4, '̨����', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1309, 4, '��ɽ��', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1310, 4, '��β��', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1311, 4, '������', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1312, 4, '������', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1313, 4, '������', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1314, 4, '��Դ��', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1315, 4, '������', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1316, 4, '��̩��', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1317, 4, 'ƽ̶��', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1318, 4, '������', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1319, 4, '������', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1320, 4, '������', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1321, 3, '������', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1322, 4, '˼����', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1323, 4, '������', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1324, 4, '������', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1326, 4, 'ͬ����', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1327, 4, '�谲��', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1329, 3, '������', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1330, 4, '������', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1332, 4, '�����', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1333, 4, '������', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1335, 4, '������', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1336, 3, '������', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1338, 4, '��Ԫ��', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1339, 4, '��Ϫ��', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1341, 4, '������', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1342, 4, '������', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1344, 4, 'ɳ��', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1345, 4, '������', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1347, 4, '������', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1348, 4, '������', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1350, 3, 'Ȫ����', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1351, 4, '�����', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1353, 4, '�彭��', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1354, 4, 'Ȫ����', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1356, 4, '��Ϫ��', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1357, 4, '������', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1359, 4, '������', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1360, 4, 'ʯʨ��', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1362, 4, '�ϰ���', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1363, 4, '������', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1365, 4, 'ܼ����', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1366, 4, '������', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1368, 4, '������', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1369, 4, 'گ����', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1371, 4, '��ɽ��', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1372, 4, '�Ͼ���', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1374, 4, '������', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1375, 4, '������', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1377, 3, '��ƽ��', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1378, 4, '��ƽ��', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1380, 4, '�ֳ���', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1381, 4, '������', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1383, 4, '������', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1384, 4, '������', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1386, 4, '�����', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1387, 4, '������', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1389, 3, '������', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1390, 4, '������', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1392, 4, '������', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1393, 4, '�Ϻ���', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1395, 4, '������', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1396, 4, '��ƽ��', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1398, 3, '������', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1399, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1401, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1402, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1404, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1405, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1407, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1408, 4, '������', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1410, 3, '�ϲ���', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1411, 4, '������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1413, 4, '��������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1414, 4, '������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1416, 4, '�ϲ���', 1410);
commit;
prompt 2900 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1417, 4, '�½���', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1418, 4, '������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1420, 4, '���̲����', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1421, 4, '���ü���������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1423, 4, '������', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1424, 3, '��������', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1426, 4, '��ɽ��', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1427, 4, '������', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1429, 4, '������', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1430, 3, 'Ƽ����', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1432, 4, '�涫��', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1433, 4, '������', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1435, 4, '«Ϫ��', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1436, 4, '������', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1438, 4, '®ɽ��', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1439, 4, '�����', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1441, 4, '������', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1442, 4, '��ˮ��', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1444, 4, '�°���', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1445, 4, '������', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1447, 4, '������', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1448, 4, '������', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1450, 4, '������', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1451, 3, '������', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1453, 4, '������', 1451);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1454, 4, '������', 1451);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1456, 4, '�º���', 1455);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1457, 4, '�཭��', 1455);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1459, 4, '������', 1455);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1460, 3, '������', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1462, 4, '����', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1463, 4, '�ŷ���', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1465, 4, '������', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1466, 4, '������', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1468, 4, '������', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1469, 4, '������', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1471, 4, '������', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1472, 4, '�ڶ���', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1474, 4, '�����', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1475, 4, 'Ѱ����', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1477, 4, '�ƽ���', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1478, 4, '�����', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1480, 4, '������', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1481, 3, '������', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1483, 4, '��ԭ��', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1484, 4, '������', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1486, 4, 'Ͽ����', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1487, 4, '�¸���', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1489, 4, '̩����', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1490, 4, '�촨��', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1492, 4, '������', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1493, 4, '������', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1495, 4, '������', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1496, 3, '�˴���', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1498, 4, '������', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1499, 4, '������', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1501, 4, '�˷���', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1502, 4, '������', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1503, 4, 'ͭ����', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1504, 4, '�����', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1505, 4, '������', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1506, 4, '�߰���', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1507, 4, '������', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1508, 3, '������', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1509, 4, '�ٴ���', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1510, 4, '�ϳ���', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1511, 4, '�质��', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1512, 4, '�Ϸ���', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1513, 4, '������', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1514, 4, '�ְ���', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1515, 4, '�˻���', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1516, 4, '��Ϫ��', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1517, 4, '��Ϫ��', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1518, 4, '������', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1519, 4, '�����', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1520, 4, '������', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1521, 3, '������', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1522, 4, '������', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1523, 4, '������', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1524, 4, '�����', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1525, 4, '��ɽ��', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1526, 4, 'Ǧɽ��', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1527, 4, '�����', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1528, 4, '߮����', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1529, 4, '�����', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1530, 4, '۶����', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1531, 4, '������', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1532, 4, '��Դ��', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1533, 4, '������', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1534, 4, '������', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1535, 2, 'ɽ��ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1536, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1537, 4, '������', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1538, 4, '������', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1539, 4, '������', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1540, 4, '������', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1541, 4, '������', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1542, 4, '������', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1543, 4, 'ƽ����', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1544, 4, '������', 1536);
commit;
prompt 3000 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1545, 4, '�̺���', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1546, 4, '������', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1547, 4, '������', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1548, 3, '�ൺ��', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1549, 4, '������', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1550, 4, '�б���', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1551, 4, '�ķ���', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1552, 4, '�Ƶ���', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1553, 4, '��ɽ��', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1554, 4, '�����', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1555, 4, '������', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1556, 4, '������', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1557, 4, '������', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1558, 4, '��ī��', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1559, 4, 'ƽ����', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1560, 4, '������', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1561, 4, '������', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1562, 4, '������', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1563, 3, '�Ͳ���', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1564, 4, '�ʹ���', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1565, 4, '�ŵ���', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1566, 4, '��ɽ��', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1567, 4, '������', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1568, 4, '�ܴ���', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1569, 4, '��̨��', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1570, 4, '������', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1571, 4, '��Դ��', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1572, 4, '������', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1573, 3, '��ׯ��', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1574, 4, '������', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1575, 4, 'Ѧ����', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1576, 4, 'ỳ���', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1577, 4, '̨��ׯ��', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1578, 4, 'ɽͤ��', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1579, 4, '������', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1580, 4, '������', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1581, 3, '��Ӫ��', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1582, 4, '��Ӫ��', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1583, 4, '�ӿ���', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1584, 4, '������', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1585, 4, '������', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1586, 4, '������', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1587, 4, '������', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1588, 4, '������', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1589, 4, '������', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1590, 3, '��̨��', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1591, 4, '֥���', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1592, 4, '��ɽ��', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1593, 4, 'Ĳƽ��', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1594, 4, '��ɽ��', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1595, 4, '������', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1596, 4, '������', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1597, 4, '������', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1598, 4, '������', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1599, 4, '������', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1600, 4, '��Զ��', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1601, 4, '��ϼ��', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1602, 4, '������', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1603, 4, '������', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1604, 3, 'Ϋ����', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1605, 4, 'Ϋ����', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1606, 4, '��ͤ��', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1607, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1608, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1609, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1610, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1611, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1612, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1613, 4, '�����', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1614, 4, '�ٹ���', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1615, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1616, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1617, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1618, 4, '������', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1619, 3, '������', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1620, 4, '������', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1621, 4, '�γ���', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1622, 4, '΢ɽ��', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1623, 4, '��̨��', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1624, 4, '������', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1625, 4, '������', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1626, 4, '������', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1627, 4, '��ˮ��', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1628, 4, '��ɽ��', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1629, 4, '������', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1630, 4, '������', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1631, 4, '�޳���', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1632, 4, '������', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1633, 3, '̩����', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1634, 4, '̩ɽ��', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1635, 4, '�����', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1636, 4, '������', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2013, 4, '�����', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2014, 4, '��Ѩ��', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2015, 4, '������', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2016, 3, '������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2017, 4, '�̰���', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2018, 4, '������', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2019, 4, 'ͨ����', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2020, 4, '������', 2016);
commit;
prompt 3100 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2021, 4, 'ͨɽ��', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2022, 4, '�����', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2023, 4, '��Ȫ����', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2024, 4, '������', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2025, 3, '������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2026, 4, '������', 2025);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2027, 4, '��ˮ��', 2025);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2028, 4, '������', 2025);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2029, 3, '��ʩ����������������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2030, 4, '��ʩ��', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2031, 4, '������', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2032, 4, '��ʼ��', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2033, 4, '�Ͷ���', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2034, 4, '������', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2035, 4, '�̷���', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2036, 4, '������', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2037, 4, '�׷���', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2038, 4, '������', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2039, 4, '������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2040, 4, 'Ǳ����', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2041, 4, '������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2042, 4, '��ũ������', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2043, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2044, 3, '��ɳ��', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2045, 4, 'ܽ����', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2046, 4, '������', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2047, 4, '��´��', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2048, 4, '������', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2049, 4, '�껨��', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2050, 4, '��ɳ��', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2051, 4, '������', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2052, 4, '������', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2053, 4, '�����', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2054, 4, '������', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2055, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2056, 4, '������', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2057, 4, '«����', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2058, 4, 'ʯ����', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2059, 4, '��Ԫ��', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2060, 4, '������', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2061, 4, '����', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2062, 4, '������', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2063, 4, '������', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2064, 4, '������', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2065, 4, '������', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2066, 3, '��̶��', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2067, 4, '�����', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2068, 4, '������', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2069, 4, '��̶��', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2070, 4, '������', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2071, 4, '��ɽ��', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2072, 4, '������', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2073, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2074, 4, '������', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2075, 4, '�����', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2076, 4, 'ʯ����', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2077, 4, '������', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2078, 4, '������', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2079, 4, '������', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2080, 4, '������', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2081, 4, '��ɽ��', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2082, 4, '�ⶫ��', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2083, 4, '���', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2084, 4, '������', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2085, 4, '������', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2086, 4, '������', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2087, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2088, 4, '˫����', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2090, 4, '������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2091, 4, '�۶���', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2093, 4, '������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2094, 4, '¡����', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2096, 4, '������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2097, 4, '������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2099, 4, '�����', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2100, 4, '������', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2101, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2102, 4, '����¥��', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2104, 4, '��ɽ��', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2105, 4, '������', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2107, 4, '������', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2108, 4, 'ƽ����', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2110, 4, '������', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2111, 4, '������', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2113, 4, '������', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2114, 4, '������', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2116, 4, '������', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2117, 4, '���', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2119, 4, '��Դ��', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2120, 4, 'ʯ����', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2122, 4, '������', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2123, 3, '�żҽ���', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2125, 4, '����Դ��', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2126, 4, '������', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2128, 4, '������', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2129, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2131, 4, '��ɽ��', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2132, 4, '����', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2134, 4, '������', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2135, 4, '�佭��', 2129);
commit;
prompt 3200 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2137, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2138, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2140, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2141, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2143, 4, '�κ���', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2144, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2146, 4, '����', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2147, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2149, 4, '������', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2150, 3, '������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2152, 4, '��ˮ̲��', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2153, 4, '������', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2155, 4, '˫����', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2156, 4, '����', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2158, 4, '��Զ��', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2159, 4, '��ɽ��', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2161, 4, '��������������', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2162, 4, '������', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2164, 4, '�׳���', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2165, 4, '�з���', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2167, 4, '��Ϫ��', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2168, 4, '������', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2170, 4, '��������������', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2171, 4, '�»ζ���������', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2173, 4, '�������嶱��������', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2174, 4, 'ͨ������������', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2175, 4, '�齭��', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2176, 4, '������', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2178, 4, '¦����', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2179, 4, '˫����', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2181, 4, '��ˮ����', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2182, 4, '��Դ��', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2184, 3, '��������������������', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2185, 4, '������', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2187, 4, '�����', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2188, 4, '��ԫ��', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2190, 4, '������', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2191, 4, '��˳��', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2193, 4, '������', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2194, 2, '�㶫ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2195, 3, '������', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2197, 4, 'Խ����', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2198, 4, '������', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2200, 4, '������', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2201, 4, '������', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2203, 4, '������', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2204, 4, '��ɳ��', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2206, 4, '������', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2207, 4, '�ӻ���', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2209, 4, '������', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2210, 3, '�ع���', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2212, 4, '䥽���', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2213, 4, '������', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2215, 4, '�ʻ���', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2216, 4, '��Դ��', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2218, 4, '�·���', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2219, 4, '�ֲ���', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2220, 4, '������', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2221, 4, '������', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2223, 4, '�޺���', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2224, 4, '������', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2226, 4, '������', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2227, 4, '������', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2229, 4, '������', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2230, 3, '�麣��', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2232, 4, '������', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2233, 4, '������', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2235, 4, '������', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2236, 4, '������', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2238, 4, '������', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2239, 4, '��ƽ��', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2241, 4, '������', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2242, 4, '������', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2244, 4, '�ϰ���', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2245, 4, '������', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2247, 4, '������', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2248, 4, '�Ϻ���', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2250, 4, '��ˮ��', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2251, 4, '������', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2253, 3, '������', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2254, 4, '���', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2256, 4, '�»���', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2257, 4, '̨ɽ��', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2259, 4, '��ɽ��', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2260, 4, '��ƽ��', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2261, 4, '������', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2262, 3, 'տ����', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2263, 4, '�࿲��', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2264, 4, 'ϼɽ��', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2265, 4, '��ͷ��', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2266, 4, '������', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2267, 4, '��Ϫ��', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2268, 4, '������', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2269, 4, '������', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2270, 4, '������', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2271, 4, '�⴨��', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2272, 4, '������', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2273, 3, 'ï����', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2274, 4, 'ï����', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2275, 4, 'ï����', 2273);
commit;
prompt 3300 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2276, 4, '�����', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2277, 4, '������', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2278, 4, '������', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2279, 4, '������', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2280, 4, '������', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2281, 3, '������', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2282, 4, '������', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2283, 4, '������', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2284, 4, '������', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2285, 4, '������', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2286, 4, '�⿪��', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2287, 4, '������', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2288, 4, '��Ҫ��', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2289, 4, '�Ļ���', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2290, 4, '������', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2291, 3, '������', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2292, 4, '�ݳ���', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2293, 4, '������', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2294, 4, '������', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2295, 4, '�ݶ���', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2296, 4, '������', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2297, 4, '������', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2298, 3, '÷����', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2299, 4, '÷����', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2300, 4, '÷��', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2301, 4, '������', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2302, 4, '��˳��', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2303, 4, '�廪��', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2304, 4, 'ƽԶ��', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2305, 4, '������', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2306, 4, '������', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2307, 4, '������', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2308, 3, '��β��', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2309, 4, '����', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2310, 4, '������', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2311, 4, '½����', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2312, 4, '½����', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2313, 4, '������', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2314, 3, '��Դ��', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2315, 4, 'Դ����', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2316, 4, '�Ͻ���', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2317, 4, '������', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2318, 4, '��ƽ��', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2319, 4, '��ƽ��', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2320, 4, '��Դ��', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2321, 4, '������', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2322, 3, '������', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2323, 4, '������', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2324, 4, '������', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2325, 4, '������', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2326, 4, '������', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2327, 4, '������', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2328, 3, '��Զ��', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2329, 4, '�����', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2330, 4, '�����', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2331, 4, '��ɽ��', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2332, 4, '��ɽ׳������������', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2333, 4, '��������������', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2334, 4, '������', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2335, 4, 'Ӣ����', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2336, 4, '������', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2337, 4, '������', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2338, 3, '��ݸ��', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2339, 3, '��ɽ��', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2340, 3, '������', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2341, 4, '������', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2342, 4, '������', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2343, 4, '��ƽ��', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2344, 4, '��Ϫ��', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2345, 4, '������', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2346, 3, '������', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2347, 4, '�ų���', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2348, 4, '�Ҷ���', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2349, 4, '������', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2350, 4, '������', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2351, 4, '������', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2352, 4, '��ɽ��', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2353, 4, '������', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2354, 3, '�Ƹ���', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2355, 4, '�Ƴ���', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2356, 4, '������', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2357, 4, '������', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2358, 4, '�ư���', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2359, 4, '�޶���', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2360, 4, '������', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2361, 2, '����׳��������', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2362, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2363, 4, '������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2364, 4, '������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2365, 4, '������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2366, 4, '��������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2367, 4, '������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2368, 4, '������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2369, 4, '������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2370, 4, '¡����', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2371, 4, '��ɽ��', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2372, 4, '������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2373, 4, '������', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2374, 4, '����', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2375, 4, '������', 2362);
commit;
prompt 3400 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2376, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2377, 4, '������', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2378, 4, '�����', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2379, 4, '������', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2380, 4, '������', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2381, 4, '������', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2382, 4, '������', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2383, 4, '¹կ��', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2384, 4, '�ڰ���', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2385, 4, '��ˮ����������', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2386, 4, '��������������', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2387, 4, '������', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2388, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2389, 4, '�����', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2390, 4, '������', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2391, 4, '��ɽ��', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2392, 4, '������', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2393, 4, '��ɽ��', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2394, 4, '��˷��', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2395, 4, '�ٹ���', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2396, 4, '�鴨��', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2397, 4, 'ȫ����', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2398, 4, '�˰���', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2399, 4, '������', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2400, 4, '������', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2401, 4, '��ʤ����������', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2402, 4, '��Դ��', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2403, 4, 'ƽ����', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2404, 4, '������', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2405, 4, '��������������', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2406, 4, '������', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2407, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2408, 4, '������', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2409, 4, '��ɽ��', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2410, 4, '������', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2411, 4, '������', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2412, 4, '����', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2413, 4, '��ɽ��', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2414, 4, '�Ϫ��', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2415, 4, '������', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2416, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2417, 4, '������', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2418, 4, '������', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2419, 4, '��ɽ����', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2420, 4, '������', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2421, 4, '������', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2422, 3, '���Ǹ���', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2423, 4, '�ۿ���', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2424, 4, '������', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2425, 4, '��˼��', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2426, 4, '������', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2427, 4, '������', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2428, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2429, 4, '������', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2430, 4, '�ձ���', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2431, 4, '��ɽ��', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2432, 4, '�ֱ���', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2433, 4, '������', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2434, 3, '�����', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2435, 4, '�۱���', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2436, 4, '������', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2437, 4, '������', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2438, 4, 'ƽ����', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2439, 4, '��ƽ��', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2440, 4, '������', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2441, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2442, 4, '������', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2443, 4, '����', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2444, 4, '½����', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2445, 4, '������', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2446, 4, '��ҵ��', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2447, 4, '������', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2448, 4, '������', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2449, 3, '��ɫ��', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2450, 4, '�ҽ���', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2451, 4, '������', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2452, 4, '�ﶫ��', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2453, 4, 'ƽ����', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2454, 4, '�±���', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2455, 4, '������', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2456, 4, '������', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2457, 4, '������', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2458, 4, '��ҵ��', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2459, 4, '������', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2460, 4, '������', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2461, 4, '¡�ָ���������', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2462, 4, '������', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2463, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2464, 4, '�˲���', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2465, 4, '��ƽ��', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2466, 4, '��ɽ��', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2468, 4, '������', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2469, 3, '�ӳ���', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2470, 4, '��ǽ���', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2471, 4, '�ϵ���', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2473, 4, '��ɽ��', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2474, 4, '������', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2476, 4, '����ë����������', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2477, 4, '��������������', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2478, 4, '��������������', 2469);
commit;
prompt 3500 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2479, 4, '������������', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2481, 4, '������', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2482, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2484, 4, '�ó���', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2485, 4, '������', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2487, 4, '��������������', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2488, 4, '��ɽ��', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2490, 3, '������', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2491, 4, '������', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2493, 4, '������', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2494, 4, '������', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2496, 4, '�����', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2497, 4, 'ƾ����', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2499, 2, '����ʡ', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2500, 3, '������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2501, 4, '��Ӣ��', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2503, 4, '��ɽ��', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2504, 4, '������', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2505, 4, '������', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2507, 4, '��ָɽ��', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2508, 4, '����', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2510, 4, '�Ĳ���', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2511, 4, '������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2513, 4, '������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2514, 4, '�Ͳ���', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2516, 4, '�ٸ���', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2517, 4, '��ɳ����������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2519, 4, '�ֶ�����������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2520, 4, '��ˮ����������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2521, 4, '��ͤ��������������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2522, 4, '������������������', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2524, 4, '��ɳȺ��', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2525, 4, '��ɳȺ���ĵ������亣��', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2527, 4, '������', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2528, 2, '����', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2530, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2531, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2533, 4, '��ɿ���', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2534, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2536, 4, '��������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2537, 4, '�ϰ���', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2538, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2540, 4, '˫����', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2541, 4, '�山��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2543, 4, 'ǭ����', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2544, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2546, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2547, 4, 'ͭ����', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2549, 4, '�ٲ���', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2550, 4, '�ɽ��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2552, 4, '�ǿ���', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2553, 4, '�ᶼ��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2555, 4, '��¡��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2556, 4, '����', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2558, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2560, 4, '��ɽ��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2561, 4, '��Ϫ��', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2563, 4, '��ɽ����������������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2564, 4, '��������������������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2565, 4, '��ˮ����������������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2566, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2567, 4, '�ϴ���', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2569, 4, '�ϴ���', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2570, 4, '������', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2572, 3, '�ɶ���', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2573, 4, '������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2575, 4, '��ţ��', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2576, 4, '�����', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2578, 4, '��Ȫ����', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2579, 4, '��׽���', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2581, 4, '�½���', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2582, 4, '������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2584, 4, 'ۯ��', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2585, 4, '������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2587, 4, '�½���', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2588, 4, '��������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2590, 4, '������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2591, 4, '������', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2593, 3, '�Թ���', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2594, 4, '��������', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2596, 4, '����', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2597, 4, '��̲��', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2599, 4, '��˳��', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2601, 3, '��֦����', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2602, 4, '����', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2604, 4, '�ʺ���', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2605, 4, '������', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2607, 4, '������', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2608, 3, '������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2610, 4, '��Ϫ��', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2611, 4, '����̶��', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2613, 4, '�Ͻ���', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2615, 4, '������', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2616, 4, '������', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2618, 4, '�����', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2619, 4, '�н���', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2621, 4, '�㺺��', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2622, 4, 'ʲ����', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2624, 4, '������', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2625, 3, '������', 2571);
commit;
prompt 3600 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2627, 4, '������', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2628, 4, '��̨��', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2975, 4, '��������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2976, 4, '���ȴ�������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2977, 4, '�������������������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2978, 4, '���ǹ���������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2979, 4, '������������������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2981, 4, '��������������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2982, 4, '������', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2983, 3, '�ٲ���', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2984, 4, '������', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2986, 4, '����', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2629, 4, '��ͤ��', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2630, 4, '����', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2631, 4, '������', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2632, 4, '����Ǽ��������', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2633, 4, 'ƽ����', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2634, 4, '������', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2635, 4, '������', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2636, 4, '������', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2637, 3, '��Ԫ��', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2638, 4, '������', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2639, 4, 'Ԫ����', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2640, 4, '������', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2641, 4, '������', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2642, 4, '�ന��', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2643, 4, '������', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2644, 4, '��Ϫ��', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2645, 4, '������', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2646, 3, '������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2647, 4, '��ɽ��', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2648, 4, '������', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2649, 4, '��Ϫ��', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2650, 4, '�����', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2651, 4, '��Ӣ��', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2652, 4, '������', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2653, 3, '�ڽ���', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2654, 4, '������', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2655, 4, '������', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2656, 4, '��Զ��', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2657, 4, '������', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2658, 4, '¡����', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2659, 4, '������', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2660, 3, '��ɽ��', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2661, 4, '������', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2662, 4, 'ɳ����', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2663, 4, '��ͨ����', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2664, 4, '��ں���', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2665, 4, '��Ϊ��', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2666, 4, '������', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2667, 4, '�н���', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2668, 4, '�崨��', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2669, 4, '�������������', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2670, 4, '�������������', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2671, 4, '��üɽ��', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2672, 4, '������', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2673, 3, '�ϳ���', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2674, 4, '˳����', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2675, 4, '��ƺ��', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2676, 4, '������', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2677, 4, '�ϲ���', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2678, 4, 'Ӫɽ��', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2679, 4, '���', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2680, 4, '��¤��', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2681, 4, '������', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2682, 4, '������', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2683, 4, '������', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2684, 3, 'üɽ��', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2685, 4, '������', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2686, 4, '������', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2687, 4, '��ɽ��', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2688, 4, '������', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2689, 4, '������', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2690, 4, '������', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2691, 4, '������', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2692, 3, '�˱���', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2693, 4, '������', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2694, 4, '�˱���', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2695, 4, '��Ϫ��', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2696, 4, '������', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2697, 4, '������', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2698, 4, '����', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2699, 4, '����', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2700, 4, '������', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2701, 4, '������', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2702, 4, '��ɽ��', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2703, 4, '������', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2704, 3, '�㰲��', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2705, 4, '�㰲��', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2706, 4, '������', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2707, 4, '��ʤ��', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2708, 4, '��ˮ��', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2709, 4, '������', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2710, 4, '��Ͻ��', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2711, 4, '������', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2712, 3, '������', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2713, 4, 'ͨ����', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2714, 4, '����', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2715, 4, '������', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2716, 4, '������', 2712);
commit;
prompt 3700 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2717, 4, '������', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2718, 4, '����', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2719, 4, '��Դ��', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2720, 4, '������', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2721, 3, '�Ű���', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2722, 4, '�����', 2721);
commit;
prompt 3706 records loaded
prompt Enabling foreign key constraints for AREA...
alter table AREA enable constraint FK_AREA_REF_AREA;
prompt Enabling triggers for AREA...
alter table AREA enable all triggers;
set feedback on
set define on
prompt Done.
