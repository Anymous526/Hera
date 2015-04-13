prompt PL/SQL Developer import file
prompt Created on 2011年6月27日 by MrXu
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
values (2723, 4, '名山县', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2724, 4, '荥经县', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2725, 4, '汉源县', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1346, 4, '泰宁县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1349, 4, '其它区', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1352, 4, '丰泽区', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2726, 4, '石棉县', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (681, 4, '双辽市', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (684, 4, '龙山区', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (687, 4, '东辽县', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (690, 4, '东昌区', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (693, 4, '辉南县', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (696, 4, '集安市', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (699, 4, '八道江区', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (703, 4, '江源县', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (706, 3, '松原市', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (709, 4, '长岭县', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (712, 4, '其它区', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (715, 4, '镇赉县', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (718, 4, '大安市', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (721, 4, '延吉市', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (724, 4, '珲春市', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (727, 4, '汪清县', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (730, 2, '黑龙江省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (733, 4, '南岗区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (736, 4, '动力区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (739, 4, '呼兰区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (742, 4, '宾县', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (947, 4, '铜山县', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (950, 4, '邳州市', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (953, 4, '天宁区', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (956, 4, '新北区', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (959, 4, '金坛市', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (962, 4, '沧浪区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (965, 4, '虎丘区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (968, 4, '常熟市', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (971, 4, '吴江市', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (974, 4, '园区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (977, 4, '崇川区', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (980, 4, '如东县', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (983, 4, '通州市', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (986, 4, '其它区', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (989, 4, '新浦区', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (992, 4, '东海县', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (995, 4, '其它区', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (998, 4, '楚州区', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1001, 4, '涟水县', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1004, 4, '金湖县', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1007, 4, '亭湖区', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1010, 4, '滨海县', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1013, 4, '建湖县', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1016, 4, '其它区', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1019, 4, '邗江区', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1022, 4, '仪征市', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1025, 4, '经济开发区', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1029, 4, '润州区', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1032, 4, '扬中市', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1035, 3, '泰州市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1038, 4, '兴化市', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1041, 4, '姜堰市', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1044, 4, '宿城区', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1047, 4, '泗阳县', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1050, 2, '浙江省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1053, 4, '下城区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1056, 4, '西湖区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1059, 4, '余杭区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1062, 4, '建德市', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1065, 4, '其它区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1068, 4, '江东区', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1071, 4, '镇海区', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1074, 4, '宁海县', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1077, 4, '奉化市', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1080, 4, '鹿城区', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1083, 4, '洞头县', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1086, 4, '苍南县', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1089, 4, '瑞安市', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1092, 3, '嘉兴市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1095, 4, '嘉善县', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1098, 4, '平湖市', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1101, 3, '湖州市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1104, 4, '德清县', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1107, 4, '其它区', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1110, 4, '绍兴县', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1113, 4, '上虞市', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1116, 3, '金华市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1119, 4, '武义县', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1325, 4, '集美区', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1328, 4, '其它区', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1331, 4, '涵江区', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1334, 4, '仙游县', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1337, 4, '梅列区', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1340, 4, '清流县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1343, 4, '尤溪县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1355, 4, '惠安县', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1358, 4, '德化县', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1361, 4, '晋江市', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1364, 3, '漳州市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1367, 4, '云霄县', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1370, 4, '长泰县', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1373, 4, '平和县', 1364);
commit;
prompt 100 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1376, 4, '其它区', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1379, 4, '顺昌县', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1382, 4, '松溪县', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1385, 4, '武夷山市', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1388, 4, '其它区', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1391, 4, '长汀县', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1394, 4, '武平县', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1397, 4, '其它区', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1400, 4, '霞浦县', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1403, 4, '寿宁县', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1406, 4, '福安市', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1409, 2, '江西省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1412, 4, '西湖区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1415, 4, '青山湖区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1419, 4, '进贤县', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1422, 4, '昌北区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1425, 4, '昌江区', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1428, 4, '乐平市', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1431, 4, '安源区', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1434, 4, '上栗县', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1437, 3, '九江市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1440, 4, '九江县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1443, 4, '永修县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1446, 4, '都昌县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1449, 4, '瑞昌市', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1452, 4, '渝水区', 1451);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1455, 3, '鹰潭市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1458, 4, '贵溪市', 1455);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1461, 4, '章贡区', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1464, 4, '大余县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1467, 4, '安远县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1470, 4, '全南县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1473, 4, '兴国县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1476, 4, '石城县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1479, 4, '南康市', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1482, 4, '吉州区', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1485, 4, '吉水县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1488, 4, '永丰县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1491, 4, '万安县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1494, 4, '井冈山市', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1497, 4, '袁州区', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1500, 4, '上高县', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1706, 4, '曹县', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1708, 4, '成武县', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1711, 4, '鄄城县', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1714, 4, '其它区', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1718, 4, '二七区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1721, 4, '上街区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1724, 4, '巩义市', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1727, 4, '新郑市', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1730, 4, '高新区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1733, 4, '龙亭区', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1736, 4, '禹王台区', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1739, 4, '通许县', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1742, 4, '兰考县', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1745, 4, '老城区', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1748, 4, '涧西区', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1751, 4, '孟津县', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1754, 4, '嵩县', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1757, 4, '洛宁县', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1760, 3, '平顶山市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1763, 4, '石龙区', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1766, 4, '叶县', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1769, 4, '舞钢市', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1772, 3, '安阳市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1775, 4, '殷都区', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1778, 4, '汤阴县', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1781, 4, '林州市', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1784, 4, '鹤山区', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1787, 4, '浚县', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1789, 4, '其它区', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1792, 4, '卫滨区', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1795, 4, '新乡县', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1798, 4, '延津县', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1801, 4, '卫辉市', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1804, 3, '焦作市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1807, 4, '马村区', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1810, 4, '博爱县', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1813, 4, '济源市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1816, 4, '其它区', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1819, 4, '清丰县', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1822, 4, '台前县', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1825, 3, '许昌市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1828, 4, '鄢陵县', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1831, 4, '长葛市', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1834, 4, '源汇区', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1837, 4, '舞阳县', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1840, 3, '三门峡市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1844, 4, '卢氏县', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1847, 4, '其它区', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1850, 4, '卧龙区', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1853, 4, '西峡县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1856, 4, '淅川县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1859, 4, '新野县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1862, 4, '其它区', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1865, 4, '睢阳区', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1868, 4, '宁陵县', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1871, 4, '夏邑县', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1874, 3, '信阳市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1877, 4, '罗山县', 1874);
commit;
prompt 200 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2924, 4, '马龙县', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2927, 4, '罗平县', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2930, 4, '沾益县', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2933, 3, '玉溪市', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2936, 4, '澄江县', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2939, 4, '易门县', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2942, 4, '元江哈尼族彝族傣族自治县', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2949, 4, '昌宁县', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2952, 4, '昭阳区', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2955, 4, '盐津县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2958, 4, '绥江县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2961, 4, '威信县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2964, 3, '丽江市', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2967, 4, '永胜县', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2970, 4, '其它区', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2973, 4, '宁洱哈尼族彝族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2980, 4, '澜沧拉祜族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2985, 4, '凤庆县', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3191, 3, '咸阳市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3194, 4, '渭城区', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3197, 4, '乾县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3200, 4, '彬县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3202, 4, '旬邑县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3205, 4, '兴平市', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3208, 4, '临渭区', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3211, 4, '大荔县', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3214, 4, '蒲城县', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3217, 4, '韩城市', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3220, 3, '延安市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3223, 4, '延川县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3226, 4, '志丹县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3229, 4, '富县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3231, 4, '宜川县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3234, 4, '其它区', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3237, 4, '南郑县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3240, 4, '西乡县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3243, 4, '略阳县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3246, 4, '佛坪县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3249, 4, '榆阳区', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3252, 4, '横山县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3255, 4, '绥德县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3258, 4, '吴堡县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3261, 4, '其它区', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3264, 4, '汉阴县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3267, 4, '紫阳县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3270, 4, '镇坪县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3273, 4, '其它区', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3276, 4, '洛南县', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3279, 4, '山阳县', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3282, 4, '其它区', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3286, 4, '七里河区', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3289, 4, '红古区', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3292, 4, '榆中县', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3295, 3, '金昌市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3298, 4, '其它区', 3295);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3301, 4, '平川区', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3304, 4, '景泰县', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3307, 4, '秦州区', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3310, 4, '秦安县', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3313, 4, '张家川回族自治县', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3319, 4, '天祝藏族自治县', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3324, 4, '民乐县', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3327, 4, '山丹县', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3330, 4, '崆峒区', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3333, 4, '崇信县', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3336, 4, '静宁县', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3339, 4, '肃州区', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3342, 4, '肃北蒙古族自治县', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3347, 3, '庆阳市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3350, 4, '环县', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3559, 4, '其它区', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3562, 4, '疏附县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3565, 4, '泽普县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3568, 4, '麦盖提县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3571, 4, '巴楚县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3574, 3, '和田地区', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3578, 4, '皮山县', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3581, 4, '于田县', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3584, 3, '伊犁哈萨克自治州', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3589, 4, '霍城县', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3592, 4, '昭苏县', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3595, 4, '其它区', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3598, 4, '乌苏市', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3601, 4, '托里县', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3604, 4, '其它区', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3607, 4, '布尔津县', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3611, 4, '青河县', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3614, 4, '石河子市', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3617, 4, '五家渠市', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3621, 4, '大同区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3624, 4, '大安区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3627, 4, '士林区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3630, 4, '南港区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3633, 3, '高雄市', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3636, 4, '芩雅区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3639, 4, '旗津区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3642, 4, '左营区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3645, 4, '其它区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3648, 4, '东区', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3650, 4, '北区', 3646);
commit;
prompt 300 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3653, 4, '其它区', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3656, 4, '东区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3658, 4, '西区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3661, 4, '西屯区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3664, 3, '金门县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3667, 4, '仁爱区', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3670, 4, '中山区', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3673, 4, '七堵区', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3676, 4, '东区', 3675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3678, 4, '香山区', 3675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3681, 4, '东区', 3680);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3684, 4, '台北县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3687, 4, '桃园县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3690, 4, '彰化县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3693, 4, '台南县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3696, 4, '台东县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3699, 2, '香港特别行政区', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3704, 3, '澳门半岛', 3703);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2727, 4, '天全县', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2728, 4, '芦山县', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2729, 4, '宝兴县', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2730, 4, '其它区', 2721);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2731, 3, '巴中市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2732, 4, '巴州区', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2733, 4, '通江县', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2734, 4, '南江县', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2735, 4, '平昌县', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2736, 4, '其它区', 2731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2737, 3, '资阳市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2738, 4, '雁江区', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2739, 4, '安岳县', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2740, 4, '乐至县', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2741, 4, '简阳市', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2742, 4, '其它区', 2737);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2743, 3, '阿坝藏族羌族自治州', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2744, 4, '汶川县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2745, 4, '理县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2746, 4, '茂县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2747, 4, '松潘县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2748, 4, '九寨沟县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2749, 4, '金川县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2750, 4, '小金县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2751, 4, '黑水县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2752, 4, '马尔康县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2753, 4, '壤塘县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2754, 4, '阿坝县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2755, 4, '若尔盖县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2756, 4, '红原县', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2757, 4, '其它区', 2743);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2758, 3, '甘孜藏族自治州', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2759, 4, '康定县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2760, 4, '泸定县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2761, 4, '丹巴县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2762, 4, '九龙县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2763, 4, '雅江县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2764, 4, '道孚县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2765, 4, '炉霍县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2766, 4, '甘孜县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2767, 4, '新龙县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2768, 4, '德格县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2769, 4, '白玉县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2770, 4, '石渠县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2771, 4, '色达县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2772, 4, '理塘县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2773, 4, '巴塘县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2774, 4, '乡城县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2775, 4, '稻城县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2776, 4, '得荣县', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2777, 4, '其它区', 2758);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2778, 3, '凉山彝族自治州', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2779, 4, '西昌市', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2780, 4, '木里藏族自治县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2781, 4, '盐源县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2782, 4, '德昌县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2783, 4, '会理县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2784, 4, '会东县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2785, 4, '宁南县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2786, 4, '普格县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2787, 4, '布拖县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2788, 4, '金阳县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2789, 4, '昭觉县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2790, 4, '喜德县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2791, 4, '冕宁县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2792, 4, '越西县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2793, 4, '甘洛县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2794, 4, '美姑县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2795, 4, '雷波县', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2796, 4, '其它区', 2778);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2797, 2, '贵州省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2798, 3, '贵阳市', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2799, 4, '南明区', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2800, 4, '云岩区', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2801, 4, '花溪区', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2802, 4, '乌当区', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2803, 4, '白云区', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2804, 4, '小河区', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2805, 4, '开阳县', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2806, 4, '息烽县', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2807, 4, '修文县', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2808, 4, '金阳开发区', 2798);
commit;
prompt 400 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2809, 4, '清镇市', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2810, 4, '其它区', 2798);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2811, 3, '六盘水市', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2812, 4, '钟山区', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2813, 4, '六枝特区', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2814, 4, '水城县', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2815, 4, '盘县', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2816, 4, '其它区', 2811);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2817, 3, '遵义市', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2818, 4, '红花岗区', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2819, 4, '汇川区', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2820, 4, '遵义县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2821, 4, '桐梓县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2822, 4, '绥阳县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2823, 4, '正安县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2824, 4, '道真仡佬族苗族自治县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2825, 4, '务川仡佬族苗族自治县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2826, 4, '凤冈县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2827, 4, '湄潭县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2828, 4, '余庆县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2829, 4, '习水县', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2830, 4, '赤水市', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2831, 4, '仁怀市', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2833, 3, '安顺市', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2834, 4, '西秀区', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2836, 4, '普定县', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2837, 4, '镇宁布依族苗族自治县', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2839, 4, '紫云苗族布依族自治县', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2840, 4, '其它区', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2841, 3, '铜仁地区', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2842, 4, '铜仁市', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2843, 4, '江口县', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2845, 4, '石阡县', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2846, 4, '思南县', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2847, 4, '印江土家族苗族自治县', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2848, 4, '德江县', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2850, 4, '松桃苗族自治县', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2851, 4, '万山特区', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2852, 4, '其它区', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2853, 3, '黔西南布依族苗族自治州', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2854, 4, '兴义市', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2856, 4, '普安县', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2857, 4, '晴隆县', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2859, 4, '望谟县', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2860, 4, '册亨县', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2862, 4, '其它区', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2863, 3, '毕节地区', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2865, 4, '大方县', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2866, 4, '黔西县', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2868, 4, '织金县', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2869, 4, '纳雍县', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2871, 4, '赫章县', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2872, 4, '其它区', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2873, 3, '黔东南苗族侗族自治州', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2874, 4, '凯里市', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2875, 4, '黄平县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2876, 4, '施秉县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2878, 4, '镇远县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2879, 4, '岑巩县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2881, 4, '锦屏县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2882, 4, '剑河县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2884, 4, '黎平县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2885, 4, '榕江县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2887, 4, '雷山县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2888, 4, '麻江县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2890, 4, '其它区', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2891, 3, '黔南布依族苗族自治州', 2797);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2893, 4, '福泉市', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2894, 4, '荔波县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2896, 4, '瓮安县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2897, 4, '独山县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2899, 4, '罗甸县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2900, 4, '长顺县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2902, 4, '惠水县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2903, 4, '三都水族自治县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2905, 2, '云南省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2906, 3, '昆明市', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2908, 4, '盘龙区', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2909, 4, '官渡区', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2911, 4, '东川区', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2912, 4, '呈贡县', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2914, 4, '富民县', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2915, 4, '宜良县', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2917, 4, '嵩明县', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2918, 4, '禄劝彝族苗族自治县', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2919, 4, '寻甸回族彝族自治县', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2920, 4, '安宁市', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2922, 3, '曲靖市', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2923, 4, '麒麟区', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2925, 4, '陆良县', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2926, 4, '师宗县', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2928, 4, '富源县', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2929, 4, '会泽县', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2931, 4, '宣威市', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2932, 4, '其它区', 2922);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2934, 4, '红塔区', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2935, 4, '江川县', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2937, 4, '通海县', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2938, 4, '华宁县', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2940, 4, '峨山彝族自治县', 2933);
commit;
prompt 500 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2941, 4, '新平彝族傣族自治县', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2943, 4, '其它区', 2933);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2944, 3, '保山市', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2945, 4, '隆阳区', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2946, 4, '施甸县', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2947, 4, '腾冲县', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2948, 4, '龙陵县', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2950, 4, '其它区', 2944);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2951, 3, '昭通市', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2953, 4, '鲁甸县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2954, 4, '巧家县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2956, 4, '大关县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2957, 4, '永善县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2959, 4, '镇雄县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2960, 4, '彝良县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2962, 4, '水富县', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2963, 4, '其它区', 2951);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2965, 4, '古城区', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2966, 4, '玉龙纳西族自治县', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2968, 4, '华坪县', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2969, 4, '宁蒗彝族自治县', 2964);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2971, 3, '普洱市', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2972, 4, '思茅区', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2974, 4, '墨江哈尼族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2987, 4, '永德县', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2988, 4, '镇康县', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2989, 4, '双江拉祜族佤族布朗族傣族自治县', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2990, 4, '耿马傣族佤族自治县', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2991, 4, '沧源佤族自治县', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2992, 4, '其它区', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2993, 3, '楚雄彝族自治州', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2994, 4, '楚雄市', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2995, 4, '双柏县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2996, 4, '牟定县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2997, 4, '南华县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2998, 4, '姚安县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2999, 4, '大姚县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3000, 4, '永仁县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3001, 4, '元谋县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3002, 4, '武定县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3003, 4, '禄丰县', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3004, 4, '其它区', 2993);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3005, 3, '红河哈尼族彝族自治州', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3006, 4, '个旧市', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3007, 4, '开远市', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3008, 4, '蒙自县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3009, 4, '屏边苗族自治县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3010, 4, '建水县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3011, 4, '石屏县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3012, 4, '弥勒县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3013, 4, '泸西县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3014, 4, '元阳县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3015, 4, '红河县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3016, 4, '金平苗族瑶族傣族自治县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3017, 4, '绿春县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3018, 4, '河口瑶族自治县', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3019, 4, '其它区', 3005);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3020, 3, '文山壮族苗族自治州', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3021, 4, '文山县', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3022, 4, '砚山县', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3023, 4, '西畴县', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3024, 4, '麻栗坡县', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3025, 4, '马关县', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3026, 4, '丘北县', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3027, 4, '广南县', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3028, 4, '富宁县', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3029, 4, '其它区', 3020);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3030, 3, '西双版纳傣族自治州', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3031, 4, '景洪市', 3030);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3032, 4, '勐海县', 3030);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3033, 4, '勐腊县', 3030);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3034, 4, '其它区', 3030);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3035, 3, '大理白族自治州', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3036, 4, '大理市', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3037, 4, '漾濞彝族自治县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3038, 4, '祥云县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3039, 4, '宾川县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3040, 4, '弥渡县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3041, 4, '南涧彝族自治县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3042, 4, '巍山彝族回族自治县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3043, 4, '永平县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3044, 4, '云龙县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3045, 4, '洱源县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3046, 4, '剑川县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3047, 4, '鹤庆县', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3048, 4, '其它区', 3035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3049, 3, '德宏傣族景颇族自治州', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3050, 4, '瑞丽市', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3051, 4, '潞西市', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3052, 4, '梁河县', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3053, 4, '盈江县', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3054, 4, '陇川县', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3055, 4, '其它区', 3049);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3056, 3, '怒江傈僳族自治州', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3057, 4, '泸水县', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3058, 4, '福贡县', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3059, 4, '贡山独龙族怒族自治县', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3060, 4, '兰坪白族普米族自治县', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3061, 4, '其它区', 3056);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3062, 3, '迪庆藏族自治州', 2905);
commit;
prompt 600 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3063, 4, '香格里拉县', 3062);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3064, 4, '德钦县', 3062);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3065, 4, '维西傈僳族自治县', 3062);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3066, 4, '其它区', 3062);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3067, 2, '西藏自治区', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3068, 3, '拉萨市', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3069, 4, '城关区', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1637, 4, '东平县', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1638, 4, '新泰市', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1639, 4, '肥城市', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1640, 4, '其它区', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1641, 3, '威海市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1642, 4, '环翠区', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1643, 4, '文登市', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1644, 4, '荣成市', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1645, 4, '乳山市', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1646, 4, '其它区', 1641);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1647, 3, '日照市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1648, 4, '东港区', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1649, 4, '岚山区', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1650, 4, '五莲县', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1651, 4, '莒县', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1652, 4, '其它区', 1647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1653, 3, '莱芜市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1654, 4, '莱城区', 1653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1655, 4, '钢城区', 1653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1656, 4, '其它区', 1653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1657, 3, '临沂市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1658, 4, '兰山区', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1659, 4, '罗庄区', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1660, 4, '河东区', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1661, 4, '沂南县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1662, 4, '郯城县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1663, 4, '沂水县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1664, 4, '苍山县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1665, 4, '费县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1666, 4, '平邑县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1667, 4, '莒南县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1668, 4, '蒙阴县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1669, 4, '临沭县', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1670, 4, '其它区', 1657);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1671, 3, '德州市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1672, 4, '德城区', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1673, 4, '陵县', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1674, 4, '宁津县', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1675, 4, '庆云县', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1676, 4, '临邑县', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1677, 4, '齐河县', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1678, 4, '平原县', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1679, 4, '夏津县', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1680, 4, '武城县', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1681, 4, '开发区', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1682, 4, '乐陵市', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1683, 4, '禹城市', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1684, 4, '其它区', 1671);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1685, 3, '聊城市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1686, 4, '东昌府区', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1687, 4, '阳谷县', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1688, 4, '莘县', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1689, 4, '茌平县', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1690, 4, '东阿县', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1691, 4, '冠县', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1692, 4, '高唐县', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1693, 4, '临清市', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1694, 4, '其它区', 1685);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1695, 3, '滨州市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1696, 4, '滨城区', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1697, 4, '惠民县', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1698, 4, '阳信县', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1699, 4, '无棣县', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1700, 4, '沾化县', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1701, 4, '博兴县', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1702, 4, '邹平县', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1703, 4, '其它区', 1695);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1704, 3, '菏泽市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1705, 4, '牡丹区', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1707, 4, '单县', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1709, 4, '巨野县', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1710, 4, '郓城县', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1712, 4, '定陶县', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1713, 4, '东明县', 1704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1715, 2, '河南省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1716, 3, '郑州市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1717, 4, '中原区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1719, 4, '管城回族区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1720, 4, '金水区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1722, 4, '惠济区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1723, 4, '中牟县', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1725, 4, '荥阳市', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1726, 4, '新密市', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1728, 4, '登封市', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1729, 4, '郑东新区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1731, 4, '其它区', 1716);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1732, 3, '开封市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1734, 4, '顺河回族区', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1735, 4, '鼓楼区', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1737, 4, '金明区', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1738, 4, '杞县', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1740, 4, '尉氏县', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1741, 4, '开封县', 1732);
commit;
prompt 700 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1743, 4, '其它区', 1732);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1744, 3, '洛阳市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1746, 4, '西工区', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1747, 4, '廛河回族区', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1749, 4, '吉利区', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1750, 4, '洛龙区', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1752, 4, '新安县', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1753, 4, '栾川县', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1755, 4, '汝阳县', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1756, 4, '宜阳县', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1758, 4, '伊川县', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1759, 4, '偃师市', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1761, 4, '新华区', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1762, 4, '卫东区', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1764, 4, '湛河区', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1765, 4, '宝丰县', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1767, 4, '鲁山县', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1768, 4, '郏县', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1770, 4, '汝州市', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1771, 4, '其它区', 1760);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1773, 4, '文峰区', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1774, 4, '北关区', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1776, 4, '龙安区', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1777, 4, '安阳县', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1779, 4, '滑县', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1780, 4, '内黄县', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1782, 4, '其它区', 1772);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1783, 3, '鹤壁市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1785, 4, '山城区', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1786, 4, '淇滨区', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1788, 4, '淇县', 1783);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1790, 3, '新乡市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1791, 4, '红旗区', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1793, 4, '凤泉区', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1794, 4, '牧野区', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1796, 4, '获嘉县', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1797, 4, '原阳县', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1799, 4, '封丘县', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1800, 4, '长垣县', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1802, 4, '辉县市', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1803, 4, '其它区', 1790);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1805, 4, '解放区', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1806, 4, '中站区', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1808, 4, '山阳区', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1809, 4, '修武县', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1811, 4, '武陟县', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1812, 4, '温县', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1814, 4, '沁阳市', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1815, 4, '孟州市', 1804);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1817, 3, '濮阳市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1818, 4, '华龙区', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1820, 4, '南乐县', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1821, 4, '范县', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1823, 4, '濮阳县', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1824, 4, '其它区', 1817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1826, 4, '魏都区', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1827, 4, '许昌县', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1829, 4, '襄城县', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1830, 4, '禹州市', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1832, 4, '其它区', 1825);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1833, 3, '漯河市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1835, 4, '郾城区', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1836, 4, '召陵区', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1838, 4, '临颍县', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1839, 4, '其它区', 1833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1841, 4, '湖滨区', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1842, 4, '渑池县', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1843, 4, '陕县', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1845, 4, '义马市', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1846, 4, '灵宝市', 1840);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1848, 3, '南阳市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1849, 4, '宛城区', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1851, 4, '南召县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1852, 4, '方城县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1854, 4, '镇平县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1855, 4, '内乡县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1857, 4, '社旗县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1858, 4, '唐河县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1860, 4, '桐柏县', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1861, 4, '邓州市', 1848);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1863, 3, '商丘市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1864, 4, '梁园区', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1866, 4, '民权县', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1867, 4, '睢县', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1869, 4, '柘城县', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1870, 4, '虞城县', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1872, 4, '永城市', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1873, 4, '其它区', 1863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1875, 4, '浉河区', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1876, 4, '平桥区', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1878, 4, '光山县', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1879, 4, '新县', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1881, 4, '固始县', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1882, 4, '潢川县', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1884, 4, '息县', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1885, 4, '其它区', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1886, 3, '周口市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1887, 4, '川汇区', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1888, 4, '扶沟县', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1889, 4, '西华县', 1886);
commit;
prompt 800 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1890, 4, '商水县', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1891, 4, '沈丘县', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1892, 4, '郸城县', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1893, 4, '淮阳县', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1894, 4, '太康县', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1895, 4, '鹿邑县', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1896, 4, '项城市', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1897, 4, '其它区', 1886);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1898, 3, '驻马店市', 1715);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1899, 4, '驿城区', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1900, 4, '西平县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1901, 4, '上蔡县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1902, 4, '平舆县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1903, 4, '正阳县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1904, 4, '确山县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1905, 4, '泌阳县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1906, 4, '汝南县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1907, 4, '遂平县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1908, 4, '新蔡县', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1909, 4, '其它区', 1898);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1910, 2, '湖北省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1911, 3, '武汉市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1912, 4, '江岸区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1913, 4, '江汉区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1914, 4, '硚口区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1915, 4, '汉阳区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1916, 4, '武昌区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1917, 4, '青山区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1918, 4, '洪山区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1919, 4, '东西湖区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1920, 4, '汉南区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1921, 4, '蔡甸区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1922, 4, '江夏区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1923, 4, '黄陂区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1924, 4, '新洲区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1925, 4, '其它区', 1911);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1926, 3, '黄石市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1927, 4, '黄石港区', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1928, 4, '西塞山区', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1929, 4, '下陆区', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1930, 4, '铁山区', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1931, 4, '阳新县', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1932, 4, '大冶市', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1933, 4, '其它区', 1926);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1934, 3, '十堰市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1935, 4, '茅箭区', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1936, 4, '张湾区', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1937, 4, '郧县', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1938, 4, '郧西县', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1939, 4, '竹山县', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1940, 4, '竹溪县', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1941, 4, '房县', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1942, 4, '丹江口市', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1943, 4, '城区', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1944, 4, '其它区', 1934);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1945, 3, '宜昌市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1946, 4, '西陵区', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1947, 4, '伍家岗区', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1948, 4, '点军区', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1949, 4, '猇亭区', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1950, 4, '夷陵区', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1951, 4, '远安县', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1952, 4, '兴山县', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1953, 4, '秭归县', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1954, 4, '长阳土家族自治县', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1955, 4, '五峰土家族自治县', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1956, 4, '葛洲坝区', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1957, 4, '开发区', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1958, 4, '宜都市', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1959, 4, '当阳市', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1960, 4, '枝江市', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1961, 4, '其它区', 1945);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1962, 3, '襄樊市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1963, 4, '襄城区', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1964, 4, '樊城区', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1965, 4, '襄阳区', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1966, 4, '南漳县', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1967, 4, '谷城县', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1968, 4, '保康县', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1969, 4, '老河口市', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1970, 4, '枣阳市', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1971, 4, '宜城市', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1972, 4, '其它区', 1962);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1973, 3, '鄂州市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1974, 4, '梁子湖区', 1973);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1975, 4, '华容区', 1973);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1976, 4, '鄂城区', 1973);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1977, 4, '其它区', 1973);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1978, 3, '荆门市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1979, 4, '东宝区', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1980, 4, '掇刀区', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1981, 4, '京山县', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1982, 4, '沙洋县', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1983, 4, '钟祥市', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1984, 4, '其它区', 1978);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1985, 3, '孝感市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1986, 4, '孝南区', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1987, 4, '孝昌县', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1988, 4, '大悟县', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1989, 4, '云梦县', 1985);
commit;
prompt 900 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1990, 4, '应城市', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1991, 4, '安陆市', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1992, 4, '汉川市', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1993, 4, '其它区', 1985);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1994, 3, '荆州市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1995, 4, '沙市区', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1996, 4, '荆州区', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1997, 4, '公安县', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1998, 4, '监利县', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1999, 4, '江陵县', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2000, 4, '石首市', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2001, 4, '洪湖市', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2002, 4, '松滋市', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2003, 4, '其它区', 1994);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2004, 3, '黄冈市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2005, 4, '黄州区', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2006, 4, '团风县', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2007, 4, '红安县', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2008, 4, '罗田县', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2009, 4, '英山县', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2010, 4, '浠水县', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2011, 4, '蕲春县', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2012, 4, '黄梅县', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3070, 4, '林周县', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3071, 4, '当雄县', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3072, 4, '尼木县', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3073, 4, '曲水县', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3074, 4, '堆龙德庆县', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3075, 4, '达孜县', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3076, 4, '墨竹工卡县', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3077, 4, '其它区', 3068);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3078, 3, '昌都地区', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3079, 4, '昌都县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3080, 4, '江达县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3081, 4, '贡觉县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3082, 4, '类乌齐县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3083, 4, '丁青县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3084, 4, '察雅县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3085, 4, '八宿县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3086, 4, '左贡县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3087, 4, '芒康县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3088, 4, '洛隆县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3089, 4, '边坝县', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3090, 4, '其它区', 3078);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3091, 3, '山南地区', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3092, 4, '乃东县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3093, 4, '扎囊县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3094, 4, '贡嘎县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3095, 4, '桑日县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3096, 4, '琼结县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3097, 4, '曲松县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3098, 4, '措美县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3099, 4, '洛扎县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3100, 4, '加查县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3101, 4, '隆子县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3102, 4, '错那县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3103, 4, '浪卡子县', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3104, 4, '其它区', 3091);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3105, 3, '日喀则地区', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3106, 4, '日喀则市', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3107, 4, '南木林县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3108, 4, '江孜县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3109, 4, '定日县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3110, 4, '萨迦县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3111, 4, '拉孜县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3112, 4, '昂仁县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3113, 4, '谢通门县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3114, 4, '白朗县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3115, 4, '仁布县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3116, 4, '康马县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3117, 4, '定结县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3118, 4, '仲巴县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3119, 4, '亚东县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3120, 4, '吉隆县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3121, 4, '聂拉木县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3122, 4, '萨嘎县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3123, 4, '岗巴县', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3124, 4, '其它区', 3105);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3125, 3, '那曲地区', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3126, 4, '那曲县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3127, 4, '嘉黎县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3128, 4, '比如县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3129, 4, '聂荣县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3130, 4, '安多县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3131, 4, '申扎县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3132, 4, '索县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3133, 4, '班戈县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3134, 4, '巴青县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3135, 4, '尼玛县', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3136, 4, '其它区', 3125);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3137, 3, '阿里地区', 3067);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3138, 4, '普兰县', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3139, 4, '札达县', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3140, 4, '噶尔县', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3141, 4, '日土县', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3142, 4, '革吉县', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3143, 4, '改则县', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3144, 4, '措勤县', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3145, 4, '其它区', 3137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3146, 3, '林芝地区', 3067);
commit;
prompt 1000 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3147, 4, '林芝县', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3148, 4, '工布江达县', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3149, 4, '米林县', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3150, 4, '墨脱县', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3151, 4, '波密县', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3152, 4, '察隅县', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3153, 4, '朗县', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3154, 4, '其它区', 3146);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3155, 2, '陕西省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3156, 3, '西安市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3157, 4, '新城区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3158, 4, '碑林区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3159, 4, '莲湖区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3160, 4, '灞桥区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3161, 4, '未央区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3162, 4, '雁塔区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3163, 4, '阎良区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3164, 4, '临潼区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3165, 4, '长安区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3166, 4, '蓝田县', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3167, 4, '周至县', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3168, 4, '户县', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3169, 4, '高陵县', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3170, 4, '其它区', 3156);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3171, 3, '铜川市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3172, 4, '王益区', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3173, 4, '印台区', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3174, 4, '耀州区', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3175, 4, '宜君县', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3176, 4, '其它区', 3171);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3177, 3, '宝鸡市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3178, 4, '渭滨区', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3179, 4, '金台区', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3180, 4, '陈仓区', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3181, 4, '凤翔县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3182, 4, '岐山县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3183, 4, '扶风县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3184, 4, '眉县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3185, 4, '陇县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3186, 4, '千阳县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3187, 4, '麟游县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3188, 4, '凤县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3189, 4, '太白县', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3190, 4, '其它区', 3177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3192, 4, '秦都区', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3193, 4, '杨凌区', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3195, 4, '三原县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3196, 4, '泾阳县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3198, 4, '礼泉县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3199, 4, '永寿县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3201, 4, '长武县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3203, 4, '淳化县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3204, 4, '武功县', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3206, 4, '其它区', 3191);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3207, 3, '渭南市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3209, 4, '华县', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3210, 4, '潼关县', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3212, 4, '合阳县', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3213, 4, '澄城县', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3215, 4, '白水县', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3216, 4, '富平县', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3218, 4, '华阴市', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3219, 4, '其它区', 3207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3221, 4, '宝塔区', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3222, 4, '延长县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3224, 4, '子长县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3225, 4, '安塞县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3227, 4, '吴起县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3228, 4, '甘泉县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3230, 4, '洛川县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3232, 4, '黄龙县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3233, 4, '黄陵县', 3220);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3235, 3, '汉中市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3236, 4, '汉台区', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3238, 4, '城固县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3239, 4, '洋县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3241, 4, '勉县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3242, 4, '宁强县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3244, 4, '镇巴县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3245, 4, '留坝县', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3247, 4, '其它区', 3235);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3248, 3, '榆林市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3250, 4, '神木县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3251, 4, '府谷县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3253, 4, '靖边县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3254, 4, '定边县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3256, 4, '米脂县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3257, 4, '佳县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3259, 4, '清涧县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3260, 4, '子洲县', 3248);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3262, 3, '安康市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3263, 4, '汉滨区', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3265, 4, '石泉县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3266, 4, '宁陕县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3268, 4, '岚皋县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3269, 4, '平利县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3271, 4, '旬阳县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3272, 4, '白河县', 3262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3274, 3, '商洛市', 3155);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3275, 4, '商州区', 3274);
commit;
prompt 1100 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3277, 4, '丹凤县', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3278, 4, '商南县', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3280, 4, '镇安县', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3281, 4, '柞水县', 3274);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3283, 2, '甘肃省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3284, 3, '兰州市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3285, 4, '城关区', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3287, 4, '西固区', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3288, 4, '安宁区', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3290, 4, '永登县', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3291, 4, '皋兰县', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3293, 4, '其它区', 3284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3294, 3, '嘉峪关市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3296, 4, '金川区', 3295);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3297, 4, '永昌县', 3295);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3299, 3, '白银市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3300, 4, '白银区', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3302, 4, '靖远县', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3303, 4, '会宁县', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3305, 4, '其它区', 3299);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3306, 3, '天水市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3308, 4, '麦积区', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3309, 4, '清水县', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3311, 4, '甘谷县', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3312, 4, '武山县', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3314, 4, '其它区', 3306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3315, 3, '武威市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3316, 4, '凉州区', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3317, 4, '民勤县', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3318, 4, '古浪县', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3320, 4, '其它区', 3315);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3321, 3, '张掖市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3322, 4, '甘州区', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3323, 4, '肃南裕固族自治县', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3325, 4, '临泽县', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3326, 4, '高台县', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3328, 4, '其它区', 3321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3329, 3, '平凉市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3331, 4, '泾川县', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3332, 4, '灵台县', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3334, 4, '华亭县', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3335, 4, '庄浪县', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3337, 4, '其它区', 3329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3338, 3, '酒泉市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3340, 4, '金塔县', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3341, 4, '安西县', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3343, 4, '阿克塞哈萨克族自治县', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3344, 4, '玉门市', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3345, 4, '敦煌市', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3346, 4, '其它区', 3338);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3348, 4, '西峰区', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3349, 4, '庆城县', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3351, 4, '华池县', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3352, 4, '合水县', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3353, 4, '正宁县', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3354, 4, '宁县', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3355, 4, '镇原县', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3356, 4, '其它区', 3347);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3357, 3, '定西市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3358, 4, '安定区', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3359, 4, '通渭县', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3360, 4, '陇西县', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3361, 4, '渭源县', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3362, 4, '临洮县', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3363, 4, '漳县', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3364, 4, '岷县', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3365, 4, '其它区', 3357);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3366, 3, '陇南市', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3367, 4, '武都区', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3368, 4, '成县', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3369, 4, '文县', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3370, 4, '宕昌县', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3371, 4, '康县', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3372, 4, '西和县', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3373, 4, '礼县', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3374, 4, '徽县', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3375, 4, '两当县', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3376, 4, '其它区', 3366);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3377, 3, '临夏回族自治州', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3378, 4, '临夏市', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3379, 4, '临夏县', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3380, 4, '康乐县', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3381, 4, '永靖县', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3382, 4, '广河县', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3383, 4, '和政县', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3384, 4, '东乡族自治县', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3385, 4, '积石山保安族东乡族撒拉族自治县', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3386, 4, '其它区', 3377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3387, 3, '甘南藏族自治州', 3283);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3388, 4, '合作市', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3389, 4, '临潭县', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3390, 4, '卓尼县', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3391, 4, '舟曲县', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3392, 4, '迭部县', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3393, 4, '玛曲县', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3394, 4, '碌曲县', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3395, 4, '夏河县', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3396, 4, '其它区', 3387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3397, 2, '青海省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3398, 3, '西宁市', 3397);
commit;
prompt 1200 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3399, 4, '城东区', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3400, 4, '城中区', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3401, 4, '城西区', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3402, 4, '城北区', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3403, 4, '大通回族土族自治县', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3404, 4, '湟中县', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3405, 4, '湟源县', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3406, 4, '其它区', 3398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3407, 3, '海东地区', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3408, 4, '平安县', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3409, 4, '民和回族土族自治县', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3410, 4, '乐都县', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3411, 4, '互助土族自治县', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3412, 4, '化隆回族自治县', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3413, 4, '循化撒拉族自治县', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3414, 4, '其它区', 3407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3415, 3, '海北藏族自治州', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3416, 4, '门源回族自治县', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3417, 4, '祁连县', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3418, 4, '海晏县', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3419, 4, '刚察县', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3420, 4, '其它区', 3415);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3421, 3, '黄南藏族自治州', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3422, 4, '同仁县', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3423, 4, '尖扎县', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3424, 4, '泽库县', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3425, 4, '河南蒙古族自治县', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3426, 4, '其它区', 3421);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3427, 3, '海南藏族自治州', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3428, 4, '共和县', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3429, 4, '同德县', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3430, 4, '贵德县', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3431, 4, '兴海县', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3432, 4, '贵南县', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3433, 4, '其它区', 3427);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3434, 3, '果洛藏族自治州', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3435, 4, '玛沁县', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3436, 4, '班玛县', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3437, 4, '甘德县', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3438, 4, '达日县', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3439, 4, '久治县', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3440, 4, '玛多县', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3441, 4, '其它区', 3434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3442, 3, '玉树藏族自治州', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3443, 4, '玉树县', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3444, 4, '杂多县', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3445, 4, '称多县', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3446, 4, '治多县', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3447, 4, '囊谦县', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3448, 4, '曲麻莱县', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3449, 4, '其它区', 3442);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3450, 3, '海西蒙古族藏族自治州', 3397);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3451, 4, '格尔木市', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3452, 4, '德令哈市', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3453, 4, '乌兰县', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3454, 4, '都兰县', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3455, 4, '天峻县', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3456, 4, '其它区', 3450);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3457, 2, '宁夏回族自治区', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3458, 3, '银川市', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3459, 4, '兴庆区', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3460, 4, '西夏区', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3461, 4, '金凤区', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3462, 4, '永宁县', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3463, 4, '贺兰县', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3464, 4, '灵武市', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3465, 4, '其它区', 3458);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3466, 3, '石嘴山市', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3467, 4, '大武口区', 3466);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3468, 4, '惠农区', 3466);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3469, 4, '平罗县', 3466);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3470, 4, '其它区', 3466);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3471, 3, '吴忠市', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3472, 4, '利通区', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3473, 4, '盐池县', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3474, 4, '同心县', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3475, 4, '青铜峡市', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3476, 4, '其它区', 3471);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3477, 3, '固原市', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3478, 4, '原州区', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3479, 4, '西吉县', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3480, 4, '隆德县', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3481, 4, '泾源县', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3482, 4, '彭阳县', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3483, 4, '其它区', 3477);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3484, 3, '中卫市', 3457);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3485, 4, '沙坡头区', 3484);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3486, 4, '中宁县', 3484);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3487, 4, '海原县', 3484);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3488, 4, '其它区', 3484);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3489, 2, '新疆维吾尔自治区', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3490, 3, '乌鲁木齐市', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3491, 4, '天山区', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3492, 4, '沙依巴克区', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3493, 4, '新市区', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3494, 4, '水磨沟区', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3495, 4, '头屯河区', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3496, 4, '达坂城区', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3497, 4, '东山区', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3498, 4, '米东区', 3490);
commit;
prompt 1300 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3499, 4, '乌鲁木齐县', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3500, 4, '其它区', 3490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3501, 3, '克拉玛依市', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3502, 4, '独山子区', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3503, 4, '克拉玛依区', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3504, 4, '白碱滩区', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3505, 4, '乌尔禾区', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3506, 4, '其它区', 3501);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3507, 3, '吐鲁番地区', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3508, 4, '吐鲁番市', 3507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3509, 4, '鄯善县', 3507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3510, 4, '托克逊县', 3507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3511, 4, '其它区', 3507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3512, 3, '哈密地区', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3513, 4, '哈密市', 3512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3514, 4, '巴里坤哈萨克自治县', 3512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3515, 4, '伊吾县', 3512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3516, 4, '其它区', 3512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3517, 3, '昌吉回族自治州', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3518, 4, '昌吉市', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3519, 4, '阜康市', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3520, 4, '米泉市', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3521, 4, '呼图壁县', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3522, 4, '玛纳斯县', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3523, 4, '奇台县', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3524, 4, '吉木萨尔县', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3525, 4, '木垒哈萨克自治县', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3526, 4, '其它区', 3517);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3527, 3, '博尔塔拉蒙古自治州', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3528, 4, '博乐市', 3527);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3529, 4, '精河县', 3527);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3530, 4, '温泉县', 3527);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3531, 4, '其它区', 3527);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3532, 3, '巴音郭楞蒙古自治州', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3533, 4, '库尔勒市', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3534, 4, '轮台县', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3535, 4, '尉犁县', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3536, 4, '若羌县', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3537, 4, '且末县', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3538, 4, '焉耆回族自治县', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3539, 4, '和静县', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3540, 4, '和硕县', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3541, 4, '博湖县', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3542, 4, '其它区', 3532);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3543, 3, '阿克苏地区', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3544, 4, '阿克苏市', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3545, 4, '温宿县', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3546, 4, '库车县', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3547, 4, '沙雅县', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3548, 4, '新和县', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3549, 4, '拜城县', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3550, 4, '乌什县', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3551, 4, '阿瓦提县', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3552, 4, '柯坪县', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3553, 4, '其它区', 3543);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3554, 3, '克孜勒苏柯尔克孜自治州', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3555, 4, '阿图什市', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3556, 4, '阿克陶县', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3557, 4, '阿合奇县', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3558, 4, '乌恰县', 3554);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3560, 3, '喀什地区', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3561, 4, '喀什市', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3563, 4, '疏勒县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3564, 4, '英吉沙县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3566, 4, '莎车县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3567, 4, '叶城县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3569, 4, '岳普湖县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3570, 4, '伽师县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3572, 4, '塔什库尔干塔吉克自治县', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3573, 4, '其它区', 3560);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3575, 4, '和田市', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3576, 4, '和田县', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3577, 4, '墨玉县', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3579, 4, '洛浦县', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3580, 4, '策勒县', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3582, 4, '民丰县', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3583, 4, '其它区', 3574);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3585, 4, '伊宁市', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3586, 4, '奎屯市', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3587, 4, '伊宁县', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3588, 4, '察布查尔锡伯自治县', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3590, 4, '巩留县', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3591, 4, '新源县', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3593, 4, '特克斯县', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3594, 4, '尼勒克县', 3584);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3596, 3, '塔城地区', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3597, 4, '塔城市', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3599, 4, '额敏县', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3600, 4, '沙湾县', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3602, 4, '裕民县', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3603, 4, '和布克赛尔蒙古自治县', 3596);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3605, 3, '阿勒泰地区', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3606, 4, '阿勒泰市', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3608, 4, '富蕴县', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3609, 4, '福海县', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3610, 4, '哈巴河县', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3612, 4, '吉木乃县', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3613, 4, '其它区', 3605);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3615, 4, '阿拉尔市', 3489);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3616, 4, '图木舒克市', 3489);
commit;
prompt 1400 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3618, 2, '台湾省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3619, 3, '台北市', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3620, 4, '中正区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3622, 4, '中山区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3623, 4, '松山区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3625, 4, '万华区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3626, 4, '信义区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3628, 4, '北投区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3629, 4, '内湖区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3631, 4, '文山区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3632, 4, '其它区', 3619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3634, 4, '新兴区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3635, 4, '前金区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3637, 4, '盐埕区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3638, 4, '鼓山区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3640, 4, '前镇区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3641, 4, '三民区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3643, 4, '楠梓区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3644, 4, '小港区', 3633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3646, 3, '台南市', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3647, 4, '中西区', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3649, 4, '南区', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3651, 4, '安平区', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3652, 4, '安南区', 3646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3654, 3, '台中市', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3655, 4, '中区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3657, 4, '南区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3659, 4, '北区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3660, 4, '北屯区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3662, 4, '南屯区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3663, 4, '其它区', 3654);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3665, 3, '南投县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3666, 3, '基隆市', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3668, 4, '信义区', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3669, 4, '中正区', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3671, 4, '安乐区', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3672, 4, '暖暖区', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3674, 4, '其它区', 3666);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3675, 3, '新竹市', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3677, 4, '北区', 3675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3679, 4, '其它区', 3675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3680, 3, '嘉义市', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3682, 4, '西区', 3680);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3683, 4, '其它区', 3680);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3685, 4, '宜兰县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3686, 4, '新竹县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3688, 4, '苗栗县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3689, 4, '台中县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3691, 4, '嘉义县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3692, 4, '云林县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3694, 4, '高雄县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3695, 4, '屏东县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3697, 4, '花莲县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3698, 4, '澎湖县', 3618);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3700, 3, '香港岛', 3699);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3701, 3, '九龙', 3699);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3702, 3, '新界', 3699);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3703, 2, '澳门特别行政区', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3705, 3, '离岛', 3703);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3706, 2, '海外', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3707, 3, '海外', 3706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (235, 4, '饶阳县', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (238, 4, '景县', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (240, 4, '冀州市', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (243, 2, '山西省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (246, 4, '迎泽区', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (249, 4, '万柏林区', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (253, 4, '娄烦县', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (256, 3, '大同市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (259, 4, '南郊区', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (262, 4, '天镇县', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (265, 4, '浑源县', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (268, 4, '其它区', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (271, 4, '矿区', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (273, 4, '平定县', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (276, 3, '长治市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (279, 4, '屯留县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (282, 4, '壶关县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (285, 4, '沁县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (288, 4, '城区', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (290, 4, '高新区', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (293, 4, '城区', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (296, 4, '陵川县', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (299, 4, '其它区', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (302, 4, '平鲁区', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (306, 4, '怀仁县', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (309, 4, '榆次区', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (312, 4, '和顺县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (315, 4, '太谷县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (318, 4, '灵石县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (321, 3, '运城市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (324, 4, '万荣县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (327, 4, '新绛县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (330, 4, '夏县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (332, 4, '芮城县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (335, 4, '其它区', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (338, 4, '定襄县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (341, 4, '繁峙县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (344, 4, '神池县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (347, 4, '河曲县', 336);
commit;
prompt 1500 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (350, 4, '原平市', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (353, 4, '尧都区', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (356, 4, '襄汾县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (359, 4, '安泽县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (362, 4, '乡宁县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (365, 4, '永和县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (368, 4, '侯马市', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (371, 3, '吕梁市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (374, 4, '交城县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (377, 4, '柳林县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (380, 4, '方山县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (383, 4, '孝义市', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (593, 4, '老边区', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (596, 4, '其它区', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (599, 4, '新邱区', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (602, 4, '细河区', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (605, 4, '其它区', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (608, 4, '文圣区', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (611, 4, '太子河区', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (614, 4, '其它区', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (617, 4, '兴隆台区', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (621, 3, '铁岭市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (624, 4, '铁岭县', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (627, 4, '调兵山市', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (630, 3, '朝阳市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (633, 4, '朝阳县', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (636, 4, '北票市', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (639, 3, '葫芦岛市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (643, 4, '绥中县', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (646, 4, '其它区', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (649, 4, '南关区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (652, 4, '二道区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (655, 4, '农安县', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (658, 4, '德惠市', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (661, 4, '经济技术开发区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (666, 4, '龙潭区', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (669, 4, '永吉县', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (672, 4, '舒兰市', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (675, 3, '四平市', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (678, 4, '梨树县', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2151, 4, '零陵区', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2154, 4, '东安县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2157, 4, '江永县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2160, 4, '新田县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2163, 3, '怀化市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2166, 4, '沅陵县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2169, 4, '会同县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2172, 4, '芷江侗族自治县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2177, 3, '娄底市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2180, 4, '新化县', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2183, 4, '其它区', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2186, 4, '泸溪县', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2189, 4, '保靖县', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2192, 4, '龙山县', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2196, 4, '荔湾区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2199, 4, '天河区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2202, 4, '番禺区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2205, 4, '萝岗区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2208, 4, '东山区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2211, 4, '武江区', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2214, 4, '始兴县', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2217, 4, '乳源瑶族自治县', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2222, 3, '深圳市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2225, 4, '南山区', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2228, 4, '盐田区', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2231, 4, '香洲区', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2234, 4, '金唐区', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2237, 3, '汕头市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2240, 4, '濠江区', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2243, 4, '澄海区', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2246, 3, '佛山市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2249, 4, '顺德区', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2252, 4, '其它区', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2255, 4, '江海区', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2258, 4, '开平市', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2467, 4, '富川瑶族自治县', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2472, 4, '天峨县', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2475, 4, '罗城仫佬族自治县', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2480, 4, '宜州市', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2483, 4, '兴宾区', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2486, 4, '武宣县', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2489, 4, '其它区', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2492, 4, '扶绥县', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2495, 4, '大新县', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2498, 4, '其它区', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2502, 4, '龙华区', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2506, 3, '三亚市', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2509, 4, '儋州市', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2512, 4, '东方市', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2515, 4, '澄迈县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2518, 4, '昌江黎族自治县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2523, 4, '西沙群岛', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2526, 4, '高新区', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2529, 3, '重庆市', 2528);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2532, 4, '渝中区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2535, 4, '沙坪坝区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2539, 4, '万盛区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2542, 4, '巴南区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2545, 4, '綦江县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2548, 4, '大足县', 2529);
commit;
prompt 1600 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2551, 4, '梁平县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2554, 4, '垫江县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2557, 4, '开县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2559, 4, '奉节县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2562, 4, '石柱土家族自治县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2568, 4, '永川区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2571, 2, '四川省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2574, 4, '青羊区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2577, 4, '成华区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2580, 4, '新都区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2583, 4, '双流县', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2586, 4, '蒲江县', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2589, 4, '彭州市', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2592, 4, '其它区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2595, 4, '贡井区', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2598, 4, '荣县', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2600, 4, '其它区', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2603, 4, '西区', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2606, 4, '盐边县', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2609, 4, '江阳区', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2612, 4, '泸县', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2614, 4, '叙永县', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2617, 3, '德阳市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2620, 4, '罗江县', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2623, 4, '绵竹市', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2626, 4, '涪城区', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2832, 4, '其它区', 2817);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2835, 4, '平坝县', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2838, 4, '关岭布依族苗族自治县', 2833);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2844, 4, '玉屏侗族自治县', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2849, 4, '沿河土家族自治县', 2841);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2855, 4, '兴仁县', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2858, 4, '贞丰县', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2861, 4, '安龙县', 2853);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2864, 4, '毕节市', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2867, 4, '金沙县', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2870, 4, '威宁彝族回族苗族自治县', 2863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2877, 4, '三穗县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2880, 4, '天柱县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2883, 4, '台江县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2886, 4, '从江县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2889, 4, '丹寨县', 2873);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2892, 4, '都匀市', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2895, 4, '贵定县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2898, 4, '平塘县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2901, 4, '龙里县', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2904, 4, '其它区', 2891);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2907, 4, '五华区', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2910, 4, '西山区', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2913, 4, '晋宁县', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2916, 4, '石林彝族自治县', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2921, 4, '其它区', 2906);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1, 1, '中国', null);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2, 2, '北京', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (3, 3, '北京市', 2);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (4, 4, '东城区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (5, 4, '西城区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (6, 4, '崇文区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (7, 4, '宣武区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (8, 4, '朝阳区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (9, 4, '丰台区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (10, 4, '石景山区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (11, 4, '海淀区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (12, 4, '门头沟区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (13, 4, '房山区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (14, 4, '通州区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (15, 4, '顺义区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (16, 4, '昌平区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (17, 4, '大兴区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (18, 4, '怀柔区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (19, 4, '平谷区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (20, 4, '密云县', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (21, 4, '延庆县', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (22, 4, '其它区', 3);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (23, 2, '天津', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (24, 3, '天津市', 23);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (25, 4, '和平区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (26, 4, '河东区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (27, 4, '河西区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (28, 4, '南开区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (29, 4, '河北区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (30, 4, '红桥区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (31, 4, '塘沽区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (32, 4, '汉沽区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (33, 4, '大港区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (34, 4, '东丽区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (35, 4, '西青区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (36, 4, '津南区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (37, 4, '北辰区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (38, 4, '武清区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (39, 4, '宝坻区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (40, 4, '宁河县', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (41, 4, '静海县', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (42, 4, '蓟县', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (43, 4, '其它区', 24);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (44, 2, '河北省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (45, 3, '石家庄市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (46, 4, '长安区', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (47, 4, '桥东区', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (48, 4, '桥西区', 45);
commit;
prompt 1700 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (49, 4, '新华区', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (50, 4, '井陉矿区', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (51, 4, '裕华区', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (52, 4, '井陉县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (53, 4, '正定县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (54, 4, '栾城县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (55, 4, '行唐县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (56, 4, '灵寿县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (57, 4, '高邑县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (58, 4, '深泽县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (59, 4, '赞皇县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (60, 4, '无极县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (61, 4, '平山县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (62, 4, '元氏县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (63, 4, '赵县', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (64, 4, '辛集市', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (65, 4, '藁城市', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (66, 4, '晋州市', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (67, 4, '新乐市', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (68, 4, '鹿泉市', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (69, 4, '其它区', 45);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (70, 3, '唐山市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (71, 4, '路南区', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (72, 4, '路北区', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (73, 4, '古冶区', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (74, 4, '开平区', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (75, 4, '丰南区', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (76, 4, '丰润区', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (77, 4, '滦县', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (78, 4, '滦南县', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (79, 4, '乐亭县', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (80, 4, '迁西县', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (81, 4, '玉田县', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (82, 4, '唐海县', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (83, 4, '遵化市', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (84, 4, '迁安市', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (85, 4, '其它区', 70);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (86, 3, '秦皇岛市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (87, 4, '海港区', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (88, 4, '山海关区', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (89, 4, '北戴河区', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (90, 4, '青龙满族自治县', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (91, 4, '昌黎县', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (92, 4, '抚宁县', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (93, 4, '卢龙县', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (94, 4, '其它区', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (95, 4, '经济技术开发区', 86);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (96, 3, '邯郸市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (97, 4, '邯山区', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (98, 4, '丛台区', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (99, 4, '复兴区', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (100, 4, '峰峰矿区', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (101, 4, '邯郸县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (102, 4, '临漳县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (103, 4, '成安县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (104, 4, '大名县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (105, 4, '涉县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (106, 4, '磁县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (107, 4, '肥乡县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (108, 4, '永年县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (109, 4, '邱县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (110, 4, '鸡泽县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (111, 4, '广平县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (112, 4, '馆陶县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (113, 4, '魏县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (114, 4, '曲周县', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (115, 4, '武安市', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (116, 4, '其它区', 96);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (117, 3, '邢台市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (118, 4, '桥东区', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (119, 4, '桥西区', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (120, 4, '邢台县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (121, 4, '临城县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (122, 4, '内丘县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (123, 4, '柏乡县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (124, 4, '隆尧县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (125, 4, '任县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (126, 4, '南和县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (127, 4, '宁晋县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (128, 4, '巨鹿县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (129, 4, '新河县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (130, 4, '广宗县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (131, 4, '平乡县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (132, 4, '威县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (133, 4, '清河县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (134, 4, '临西县', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (135, 4, '南宫市', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (136, 4, '沙河市', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (137, 4, '其它区', 117);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (138, 3, '保定市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (139, 4, '新市区', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (140, 4, '北市区', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (141, 4, '南市区', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (142, 4, '满城县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (143, 4, '清苑县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (144, 4, '涞水县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (145, 4, '阜平县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (146, 4, '徐水县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (147, 4, '定兴县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (148, 4, '唐县', 138);
commit;
prompt 1800 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (149, 4, '高阳县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (150, 4, '容城县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (151, 4, '涞源县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (152, 4, '望都县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (153, 4, '安新县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (154, 4, '易县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (155, 4, '曲阳县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (156, 4, '蠡县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (157, 4, '顺平县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (158, 4, '博野县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (159, 4, '雄县', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (160, 4, '涿州市', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (161, 4, '定州市', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (162, 4, '安国市', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (163, 4, '高碑店市', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (164, 4, '高开区', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (165, 4, '其它区', 138);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (166, 3, '张家口市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (167, 4, '桥东区', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (168, 4, '桥西区', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (169, 4, '宣化区', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (170, 4, '下花园区', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (171, 4, '宣化县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (172, 4, '张北县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (173, 4, '康保县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (174, 4, '沽源县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (175, 4, '尚义县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (176, 4, '蔚县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (177, 4, '阳原县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (178, 4, '怀安县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1880, 4, '商城县', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1883, 4, '淮滨县', 1874);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2089, 4, '大祥区', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2092, 4, '新邵县', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2095, 4, '洞口县', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2098, 4, '城步苗族自治县', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2103, 4, '云溪区', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2106, 4, '华容县', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2109, 4, '汨罗市', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2112, 3, '常德市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2115, 4, '安乡县', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2118, 4, '临澧县', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2121, 4, '津市市', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2124, 4, '永定区', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2127, 4, '桑植县', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2130, 4, '资阳区', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2133, 4, '桃江县', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2136, 4, '其它区', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2139, 4, '苏仙区', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2142, 4, '永兴县', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2145, 4, '汝城县', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2148, 4, '资兴市', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (179, 4, '万全县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (180, 4, '怀来县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (181, 4, '涿鹿县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (192, 4, '滦平县', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (193, 4, '隆化县', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (194, 4, '丰宁满族自治县', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (195, 4, '宽城满族自治县', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (196, 4, '围场满族蒙古族自治县', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (197, 4, '其它区', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (198, 3, '沧州市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (199, 4, '新华区', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (200, 4, '运河区', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (201, 4, '沧县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (202, 4, '青县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (203, 4, '东光县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (204, 4, '海兴县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (205, 4, '盐山县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (206, 4, '肃宁县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (207, 4, '南皮县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (208, 4, '吴桥县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (209, 4, '献县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (210, 4, '孟村回族自治县', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (211, 4, '泊头市', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (212, 4, '任丘市', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (213, 4, '黄骅市', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (214, 4, '河间市', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (215, 4, '其它区', 198);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (216, 3, '廊坊市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (217, 4, '安次区', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (218, 4, '广阳区', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (219, 4, '固安县', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (220, 4, '永清县', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (221, 4, '香河县', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (222, 4, '大城县', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (223, 4, '文安县', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (224, 4, '大厂回族自治县', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (225, 4, '开发区', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (226, 4, '燕郊经济技术开发区', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (227, 4, '霸州市', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (228, 4, '三河市', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (229, 4, '其它区', 216);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (230, 3, '衡水市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (231, 4, '桃城区', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (232, 4, '枣强县', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (233, 4, '武邑县', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (234, 4, '武强县', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (236, 4, '安平县', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (237, 4, '故城县', 230);
commit;
prompt 1900 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (239, 4, '阜城县', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (241, 4, '深州市', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (242, 4, '其它区', 230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (244, 3, '太原市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (245, 4, '小店区', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (247, 4, '杏花岭区', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (248, 4, '尖草坪区', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (250, 4, '晋源区', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (251, 4, '清徐县', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (252, 4, '阳曲县', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (254, 4, '古交市', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (255, 4, '其它区', 244);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (257, 4, '城区', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (258, 4, '矿区', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (260, 4, '新荣区', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (261, 4, '阳高县', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (263, 4, '广灵县', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (264, 4, '灵丘县', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (266, 4, '左云县', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (267, 4, '大同县', 256);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (269, 3, '阳泉市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (270, 4, '城区', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (272, 4, '郊区', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (274, 4, '盂县', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (275, 4, '其它区', 269);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (277, 4, '长治县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (278, 4, '襄垣县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (280, 4, '平顺县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (281, 4, '黎城县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (283, 4, '长子县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (284, 4, '武乡县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (286, 4, '沁源县', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (287, 4, '潞城市', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (289, 4, '郊区', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (291, 4, '其它区', 276);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (292, 3, '晋城市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (294, 4, '沁水县', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (295, 4, '阳城县', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (297, 4, '泽州县', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (298, 4, '高平市', 292);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (300, 3, '朔州市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (301, 4, '朔城区', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (303, 4, '山阴县', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (304, 4, '应县', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (305, 4, '右玉县', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (307, 4, '其它区', 300);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (308, 3, '晋中市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (310, 4, '榆社县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (311, 4, '左权县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (313, 4, '昔阳县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (314, 4, '寿阳县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (316, 4, '祁县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (317, 4, '平遥县', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (319, 4, '介休市', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (320, 4, '其它区', 308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (322, 4, '盐湖区', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (323, 4, '临猗县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (325, 4, '闻喜县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (326, 4, '稷山县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (328, 4, '绛县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (329, 4, '垣曲县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (331, 4, '平陆县', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (333, 4, '永济市', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (334, 4, '河津市', 321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (336, 3, '忻州市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (337, 4, '忻府区', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (339, 4, '五台县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (340, 4, '代县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (342, 4, '宁武县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (343, 4, '静乐县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (345, 4, '五寨县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (346, 4, '岢岚县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (348, 4, '保德县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (349, 4, '偏关县', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (351, 4, '其它区', 336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (352, 3, '临汾市', 243);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (354, 4, '曲沃县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (355, 4, '翼城县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (357, 4, '洪洞县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (358, 4, '古县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (360, 4, '浮山县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (361, 4, '吉县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (363, 4, '大宁县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (364, 4, '隰县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (366, 4, '蒲县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (367, 4, '汾西县', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (369, 4, '霍州市', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (370, 4, '其它区', 352);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (372, 4, '离石区', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (373, 4, '文水县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (375, 4, '兴县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (376, 4, '临县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (378, 4, '石楼县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (379, 4, '岚县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (381, 4, '中阳县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (382, 4, '交口县', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (384, 4, '汾阳市', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (385, 4, '其它区', 371);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (386, 2, '内蒙古自治区', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (387, 3, '呼和浩特市', 386);
commit;
prompt 2000 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (388, 4, '新城区', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (389, 4, '回民区', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (390, 4, '玉泉区', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (391, 4, '赛罕区', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (392, 4, '土默特左旗', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (393, 4, '托克托县', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (394, 4, '和林格尔县', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (395, 4, '清水河县', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (396, 4, '武川县', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (397, 4, '其它区', 387);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (398, 3, '包头市', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (399, 4, '东河区', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (400, 4, '昆都仑区', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (401, 4, '青山区', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (402, 4, '石拐区', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (403, 4, '白云矿区', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (404, 4, '九原区', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (405, 4, '土默特右旗', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (406, 4, '固阳县', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (407, 4, '达尔罕茂明安联合旗', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (408, 4, '其它区', 398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (409, 3, '乌海市', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (410, 4, '海勃湾区', 409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (411, 4, '海南区', 409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (412, 4, '乌达区', 409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (413, 4, '其它区', 409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (414, 3, '赤峰市', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (415, 4, '红山区', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (416, 4, '元宝山区', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (417, 4, '松山区', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (418, 4, '阿鲁科尔沁旗', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (419, 4, '巴林左旗', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (420, 4, '巴林右旗', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (421, 4, '林西县', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (422, 4, '克什克腾旗', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (423, 4, '翁牛特旗', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (424, 4, '喀喇沁旗', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (425, 4, '宁城县', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (426, 4, '敖汉旗', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (427, 4, '其它区', 414);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (428, 3, '通辽市', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (429, 4, '科尔沁区', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (430, 4, '科尔沁左翼中旗', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (431, 4, '科尔沁左翼后旗', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (432, 4, '开鲁县', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (433, 4, '库伦旗', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (434, 4, '奈曼旗', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (435, 4, '扎鲁特旗', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (436, 4, '霍林郭勒市', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (437, 4, '其它区', 428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (438, 3, '鄂尔多斯市', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (439, 4, '东胜区', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (440, 4, '达拉特旗', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (441, 4, '准格尔旗', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (442, 4, '鄂托克前旗', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (443, 4, '鄂托克旗', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (444, 4, '杭锦旗', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (445, 4, '乌审旗', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (446, 4, '伊金霍洛旗', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (447, 4, '其它区', 438);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (448, 3, '呼伦贝尔市', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (449, 4, '海拉尔区', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (450, 4, '阿荣旗', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (451, 4, '莫力达瓦达斡尔族自治旗', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (452, 4, '鄂伦春自治旗', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (453, 4, '鄂温克族自治旗', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (454, 4, '陈巴尔虎旗', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (455, 4, '新巴尔虎左旗', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (456, 4, '新巴尔虎右旗', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (457, 4, '满洲里市', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (458, 4, '牙克石市', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (459, 4, '扎兰屯市', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (460, 4, '额尔古纳市', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (461, 4, '根河市', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (462, 4, '其它区', 448);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (463, 3, '巴彦淖尔市', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (464, 4, '临河区', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (465, 4, '五原县', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (466, 4, '磴口县', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (467, 4, '乌拉特前旗', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (468, 4, '乌拉特中旗', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (469, 4, '乌拉特后旗', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (470, 4, '杭锦后旗', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (471, 4, '其它区', 463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (472, 3, '乌兰察布市', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (473, 4, '集宁区', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (474, 4, '卓资县', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (475, 4, '化德县', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (476, 4, '商都县', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (477, 4, '兴和县', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (478, 4, '凉城县', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (479, 4, '察哈尔右翼前旗', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (480, 4, '察哈尔右翼中旗', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (481, 4, '察哈尔右翼后旗', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (482, 4, '四子王旗', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (483, 4, '丰镇市', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (484, 4, '其它区', 472);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (485, 3, '兴安盟', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (486, 4, '乌兰浩特市', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (487, 4, '阿尔山市', 485);
commit;
prompt 2100 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (488, 4, '科尔沁右翼前旗', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (489, 4, '科尔沁右翼中旗', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (490, 4, '扎赉特旗', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (491, 4, '突泉县', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (492, 4, '其它区', 485);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (493, 3, '锡林郭勒盟', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (494, 4, '二连浩特市', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (495, 4, '锡林浩特市', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (496, 4, '阿巴嘎旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (497, 4, '苏尼特左旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (498, 4, '苏尼特右旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (499, 4, '东乌珠穆沁旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (500, 4, '西乌珠穆沁旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (501, 4, '太仆寺旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (502, 4, '镶黄旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (503, 4, '正镶白旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (504, 4, '正蓝旗', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (505, 4, '多伦县', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (506, 4, '其它区', 493);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (507, 3, '阿拉善盟', 386);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (508, 4, '阿拉善左旗', 507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (509, 4, '阿拉善右旗', 507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (510, 4, '额济纳旗', 507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (511, 4, '其它区', 507);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (512, 2, '辽宁省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (513, 3, '沈阳市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (514, 4, '和平区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (515, 4, '沈河区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (516, 4, '大东区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (517, 4, '皇姑区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (518, 4, '铁西区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (519, 4, '苏家屯区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (520, 4, '东陵区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (521, 4, '新城子区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (522, 4, '于洪区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (523, 4, '辽中县', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (524, 4, '康平县', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (525, 4, '法库县', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (526, 4, '新民市', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (527, 4, '浑南新区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (528, 4, '张士开发区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (529, 4, '沈北新区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (530, 4, '其它区', 513);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (531, 3, '大连市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (532, 4, '中山区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (533, 4, '西岗区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (534, 4, '沙河口区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (182, 4, '赤城县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (183, 4, '崇礼县', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (184, 4, '其它区', 166);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (185, 3, '承德市', 44);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (186, 4, '双桥区', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (187, 4, '双滦区', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (188, 4, '鹰手营子矿区', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (189, 4, '承德县', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (190, 4, '兴隆县', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (191, 4, '平泉县', 185);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (535, 4, '甘井子区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (536, 4, '旅顺口区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (537, 4, '金州区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (538, 4, '长海县', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (539, 4, '开发区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (540, 4, '瓦房店市', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (541, 4, '普兰店市', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (542, 4, '庄河市', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (543, 4, '岭前区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (544, 4, '其它区', 531);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (545, 3, '鞍山市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (546, 4, '铁东区', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (547, 4, '铁西区', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (548, 4, '立山区', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (549, 4, '千山区', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (550, 4, '台安县', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (551, 4, '岫岩满族自治县', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (552, 4, '高新区', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (553, 4, '海城市', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (554, 4, '其它区', 545);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (555, 3, '抚顺市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (556, 4, '新抚区', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (557, 4, '东洲区', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (558, 4, '望花区', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (559, 4, '顺城区', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (560, 4, '抚顺县', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (561, 4, '新宾满族自治县', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (562, 4, '清原满族自治县', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (563, 4, '其它区', 555);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (564, 3, '本溪市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (565, 4, '平山区', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (566, 4, '溪湖区', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (567, 4, '明山区', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (568, 4, '南芬区', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (569, 4, '本溪满族自治县', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (570, 4, '桓仁满族自治县', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (571, 4, '其它区', 564);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (572, 3, '丹东市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (573, 4, '元宝区', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (574, 4, '振兴区', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (575, 4, '振安区', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (576, 4, '宽甸满族自治县', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (577, 4, '东港市', 572);
commit;
prompt 2200 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (578, 4, '凤城市', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (579, 4, '其它区', 572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (580, 3, '锦州市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (581, 4, '古塔区', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (582, 4, '凌河区', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (583, 4, '太和区', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (584, 4, '黑山县', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (585, 4, '义县', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (586, 4, '凌海市', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (587, 4, '北镇市', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (588, 4, '其它区', 580);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (589, 3, '营口市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (590, 4, '站前区', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (591, 4, '西市区', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (592, 4, '鲅鱼圈区', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (594, 4, '盖州市', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (595, 4, '大石桥市', 589);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (597, 3, '阜新市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (598, 4, '海州区', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (600, 4, '太平区', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (601, 4, '清河门区', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (603, 4, '阜新蒙古族自治县', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (604, 4, '彰武县', 597);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (606, 3, '辽阳市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (607, 4, '白塔区', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (609, 4, '宏伟区', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (610, 4, '弓长岭区', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (612, 4, '辽阳县', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (613, 4, '灯塔市', 606);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (615, 3, '盘锦市', 512);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (616, 4, '双台子区', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (618, 4, '大洼县', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (619, 4, '盘山县', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (620, 4, '其它区', 615);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (622, 4, '银州区', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (623, 4, '清河区', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (625, 4, '西丰县', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (626, 4, '昌图县', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (628, 4, '开原市', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (629, 4, '其它区', 621);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (631, 4, '双塔区', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (632, 4, '龙城区', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (634, 4, '建平县', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (635, 4, '喀喇沁左翼蒙古族自治县', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (637, 4, '凌源市', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (638, 4, '其它区', 630);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (640, 4, '连山区', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (641, 4, '龙港区', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (642, 4, '南票区', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (644, 4, '建昌县', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (645, 4, '兴城市', 639);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (647, 2, '吉林省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (648, 3, '长春市', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (650, 4, '宽城区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (651, 4, '朝阳区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (653, 4, '绿园区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (654, 4, '双阳区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (656, 4, '九台市', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (657, 4, '榆树市', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (659, 4, '高新技术产业开发区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (660, 4, '汽车产业开发区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (662, 4, '净月旅游开发区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (663, 4, '其它区', 648);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (664, 3, '吉林市', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (665, 4, '昌邑区', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (667, 4, '船营区', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (668, 4, '丰满区', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (670, 4, '蛟河市', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (671, 4, '桦甸市', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (673, 4, '磐石市', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (674, 4, '其它区', 664);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (676, 4, '铁西区', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (677, 4, '铁东区', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (679, 4, '伊通满族自治县', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (680, 4, '公主岭市', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (682, 4, '其它区', 675);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (683, 3, '辽源市', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (685, 4, '西安区', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (686, 4, '东丰县', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (688, 4, '其它区', 683);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (689, 3, '通化市', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (691, 4, '二道江区', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (692, 4, '通化县', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (694, 4, '柳河县', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (695, 4, '梅河口市', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (697, 4, '其它区', 689);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (698, 3, '白山市', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (700, 4, '抚松县', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (701, 4, '靖宇县', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (702, 4, '长白朝鲜族自治县', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (704, 4, '临江市', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (705, 4, '其它区', 698);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (707, 4, '宁江区', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (708, 4, '前郭尔罗斯蒙古族自治县', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (710, 4, '乾安县', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (711, 4, '扶余县', 706);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (713, 3, '白城市', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (714, 4, '洮北区', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (716, 4, '通榆县', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (717, 4, '洮南市', 713);
commit;
prompt 2300 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (719, 4, '其它区', 713);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (720, 3, '延边朝鲜族自治州', 647);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (722, 4, '图们市', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (723, 4, '敦化市', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (725, 4, '龙井市', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (726, 4, '和龙市', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (728, 4, '安图县', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (729, 4, '其它区', 720);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (731, 3, '哈尔滨市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (732, 4, '道里区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (734, 4, '道外区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (735, 4, '香坊区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (737, 4, '平房区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (738, 4, '松北区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (740, 4, '依兰县', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (741, 4, '方正县', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (743, 4, '巴彦县', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (744, 4, '木兰县', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (745, 4, '通河县', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (746, 4, '延寿县', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (747, 4, '阿城市', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (748, 4, '双城市', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (749, 4, '尚志市', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (750, 4, '五常市', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (752, 4, '其它区', 731);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (753, 3, '齐齐哈尔市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (754, 4, '龙沙区', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (755, 4, '建华区', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (756, 4, '铁锋区', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (757, 4, '昂昂溪区', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (758, 4, '富拉尔基区', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (759, 4, '碾子山区', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (760, 4, '梅里斯达斡尔族区', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (761, 4, '龙江县', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (762, 4, '依安县', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (763, 4, '泰来县', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (764, 4, '甘南县', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (765, 4, '富裕县', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (766, 4, '克山县', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (767, 4, '克东县', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (768, 4, '拜泉县', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (769, 4, '讷河市', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (770, 4, '其它区', 753);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (771, 3, '鸡西市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (772, 4, '鸡冠区', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (773, 4, '恒山区', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (774, 4, '滴道区', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (775, 4, '梨树区', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (776, 4, '城子河区', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (777, 4, '麻山区', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (778, 4, '鸡东县', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (779, 4, '虎林市', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (780, 4, '密山市', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (781, 4, '其它区', 771);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (782, 3, '鹤岗市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (783, 4, '向阳区', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (784, 4, '工农区', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (785, 4, '南山区', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (786, 4, '兴安区', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (787, 4, '东山区', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (788, 4, '兴山区', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (789, 4, '萝北县', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (790, 4, '绥滨县', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (791, 4, '其它区', 782);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (792, 3, '双鸭山市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (793, 4, '尖山区', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (794, 4, '岭东区', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (795, 4, '四方台区', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (796, 4, '宝山区', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (797, 4, '集贤县', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (798, 4, '友谊县', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (799, 4, '宝清县', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (800, 4, '饶河县', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (801, 4, '其它区', 792);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (802, 3, '大庆市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (803, 4, '萨尔图区', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (804, 4, '龙凤区', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (805, 4, '让胡路区', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (806, 4, '红岗区', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (807, 4, '大同区', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (808, 4, '肇州县', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (809, 4, '肇源县', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (810, 4, '林甸县', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (811, 4, '杜尔伯特蒙古族自治县', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (812, 4, '其它区', 802);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (813, 3, '伊春市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (814, 4, '伊春区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (815, 4, '南岔区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (816, 4, '友好区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (817, 4, '西林区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (818, 4, '翠峦区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (819, 4, '新青区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (820, 4, '美溪区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (821, 4, '金山屯区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (822, 4, '五营区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (823, 4, '乌马河区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (824, 4, '汤旺河区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (825, 4, '带岭区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (826, 4, '乌伊岭区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (827, 4, '红星区', 813);
commit;
prompt 2400 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (828, 4, '上甘岭区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (829, 4, '嘉荫县', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (830, 4, '铁力市', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (831, 4, '其它区', 813);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (832, 3, '佳木斯市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (833, 4, '永红区', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (834, 4, '向阳区', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (835, 4, '前进区', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (836, 4, '东风区', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (837, 4, '郊区', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (838, 4, '桦南县', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (839, 4, '桦川县', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (840, 4, '汤原县', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (841, 4, '抚远县', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (842, 4, '同江市', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (843, 4, '富锦市', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (844, 4, '其它区', 832);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (845, 3, '七台河市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (846, 4, '新兴区', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (847, 4, '桃山区', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (848, 4, '茄子河区', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (849, 4, '勃利县', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (850, 4, '其它区', 845);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (851, 3, '牡丹江市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (852, 4, '东安区', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (853, 4, '阳明区', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (854, 4, '爱民区', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (855, 4, '西安区', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (856, 4, '东宁县', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (857, 4, '林口县', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (858, 4, '绥芬河市', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (859, 4, '海林市', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (860, 4, '宁安市', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (861, 4, '穆棱市', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (862, 4, '其它区', 851);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (863, 3, '黑河市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (864, 4, '爱辉区', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (865, 4, '嫩江县', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (866, 4, '逊克县', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (867, 4, '孙吴县', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (868, 4, '北安市', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (869, 4, '五大连池市', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (870, 4, '其它区', 863);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (871, 3, '绥化市', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (872, 4, '北林区', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (873, 4, '望奎县', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (874, 4, '兰西县', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (875, 4, '青冈县', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (876, 4, '庆安县', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (877, 4, '明水县', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (878, 4, '绥棱县', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (879, 4, '安达市', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (880, 4, '肇东市', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (881, 4, '海伦市', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (882, 4, '其它区', 871);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (883, 3, '大兴安岭地区', 730);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (884, 4, '呼玛县', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (885, 4, '塔河县', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (886, 4, '漠河县', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (887, 4, '加格达奇区', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (888, 4, '其它区', 883);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (889, 2, '上海', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (890, 3, '上海市', 889);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (891, 4, '黄浦区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (892, 4, '卢湾区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (893, 4, '徐汇区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (894, 4, '长宁区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (895, 4, '静安区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (896, 4, '普陀区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (897, 4, '闸北区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (898, 4, '虹口区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (899, 4, '杨浦区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (900, 4, '闵行区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (901, 4, '宝山区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (902, 4, '嘉定区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (903, 4, '浦东新区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (904, 4, '金山区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (905, 4, '松江区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (906, 4, '青浦区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (907, 4, '南汇区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (908, 4, '奉贤区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (909, 4, '川沙区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (910, 4, '崇明县', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (911, 4, '其它区', 890);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (912, 2, '江苏省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (913, 3, '南京市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (914, 4, '玄武区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (915, 4, '白下区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (916, 4, '秦淮区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (917, 4, '建邺区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (918, 4, '鼓楼区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (919, 4, '下关区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (920, 4, '浦口区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (921, 4, '栖霞区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (922, 4, '雨花台区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (923, 4, '江宁区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (924, 4, '六合区', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (925, 4, '溧水县', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (926, 4, '高淳县', 913);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (927, 4, '其它区', 913);
commit;
prompt 2500 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (928, 3, '无锡市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (929, 4, '崇安区', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (930, 4, '南长区', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (931, 4, '北塘区', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (932, 4, '锡山区', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (933, 4, '惠山区', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (934, 4, '滨湖区', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (935, 4, '江阴市', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (936, 4, '宜兴市', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (937, 4, '新区', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (938, 4, '其它区', 928);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (939, 3, '徐州市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (940, 4, '鼓楼区', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (941, 4, '云龙区', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (942, 4, '九里区', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (943, 4, '贾汪区', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (944, 4, '泉山区', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (945, 4, '丰县', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (946, 4, '沛县', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (948, 4, '睢宁县', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (949, 4, '新沂市', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (951, 4, '其它区', 939);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (952, 3, '常州市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (954, 4, '钟楼区', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (955, 4, '戚墅堰区', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (957, 4, '武进区', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (958, 4, '溧阳市', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (960, 4, '其它区', 952);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (961, 3, '苏州市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (963, 4, '平江区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (964, 4, '金阊区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (966, 4, '吴中区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (967, 4, '相城区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (969, 4, '张家港市', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (970, 4, '昆山市', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (972, 4, '太仓市', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (973, 4, '新区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (975, 4, '其它区', 961);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (976, 3, '南通市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (978, 4, '港闸区', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (979, 4, '海安县', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (981, 4, '启东市', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (982, 4, '如皋市', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (984, 4, '海门市', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (985, 4, '开发区', 976);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (987, 3, '连云港市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (988, 4, '连云区', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (990, 4, '海州区', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (991, 4, '赣榆县', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (993, 4, '灌云县', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (994, 4, '灌南县', 987);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (996, 3, '淮安市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (997, 4, '清河区', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (999, 4, '淮阴区', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1000, 4, '清浦区', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1002, 4, '洪泽县', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1003, 4, '盱眙县', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1005, 4, '其它区', 996);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1006, 3, '盐城市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1008, 4, '盐都区', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1009, 4, '响水县', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1011, 4, '阜宁县', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1012, 4, '射阳县', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1014, 4, '东台市', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1015, 4, '大丰市', 1006);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1017, 3, '扬州市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1018, 4, '广陵区', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1020, 4, '维扬区', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1021, 4, '宝应县', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1023, 4, '高邮市', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1024, 4, '江都市', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1026, 4, '其它区', 1017);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1027, 3, '镇江市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1028, 4, '京口区', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1030, 4, '丹徒区', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1031, 4, '丹阳市', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1033, 4, '句容市', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1034, 4, '其它区', 1027);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1036, 4, '海陵区', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1037, 4, '高港区', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1039, 4, '靖江市', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1040, 4, '泰兴市', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1042, 4, '其它区', 1035);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1043, 3, '宿迁市', 912);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1045, 4, '宿豫区', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1046, 4, '沭阳县', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1048, 4, '泗洪县', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1049, 4, '其它区', 1043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1051, 3, '杭州市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1052, 4, '上城区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1054, 4, '江干区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1055, 4, '拱墅区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1057, 4, '滨江区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1058, 4, '萧山区', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1060, 4, '桐庐县', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1061, 4, '淳安县', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1063, 4, '富阳市', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1064, 4, '临安市', 1051);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1066, 3, '宁波市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1067, 4, '海曙区', 1066);
commit;
prompt 2600 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1069, 4, '江北区', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1070, 4, '北仑区', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1072, 4, '鄞州区', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1073, 4, '象山县', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1075, 4, '余姚市', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1076, 4, '慈溪市', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1078, 4, '其它区', 1066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1079, 3, '温州市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1081, 4, '龙湾区', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1082, 4, '瓯海区', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1084, 4, '永嘉县', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1085, 4, '平阳县', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1087, 4, '文成县', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1088, 4, '泰顺县', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1090, 4, '乐清市', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1091, 4, '其它区', 1079);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1093, 4, '南湖区', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1094, 4, '秀洲区', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1096, 4, '海盐县', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1097, 4, '海宁市', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1099, 4, '桐乡市', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1100, 4, '其它区', 1092);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1102, 4, '吴兴区', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1103, 4, '南浔区', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1105, 4, '长兴县', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1106, 4, '安吉县', 1101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1108, 3, '绍兴市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1109, 4, '越城区', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1111, 4, '新昌县', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1112, 4, '诸暨市', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1114, 4, '嵊州市', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1115, 4, '其它区', 1108);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1117, 4, '婺城区', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1118, 4, '金东区', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1120, 4, '浦江县', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1121, 4, '磐安县', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1122, 4, '兰溪市', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1123, 4, '义乌市', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1124, 4, '东阳市', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1125, 4, '永康市', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1126, 4, '其它区', 1116);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1127, 3, '衢州市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1128, 4, '柯城区', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1129, 4, '衢江区', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1130, 4, '常山县', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1131, 4, '开化县', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1132, 4, '龙游县', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1133, 4, '江山市', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1134, 4, '其它区', 1127);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1135, 3, '舟山市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1136, 4, '定海区', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1137, 4, '普陀区', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1138, 4, '岱山县', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1139, 4, '嵊泗县', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1140, 4, '其它区', 1135);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1141, 3, '台州市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1142, 4, '椒江区', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1143, 4, '黄岩区', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1144, 4, '路桥区', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1145, 4, '玉环县', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1146, 4, '三门县', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1147, 4, '天台县', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1148, 4, '仙居县', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1149, 4, '温岭市', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1150, 4, '临海市', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1151, 4, '其它区', 1141);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1152, 3, '丽水市', 1050);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1153, 4, '莲都区', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1154, 4, '青田县', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1155, 4, '缙云县', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1156, 4, '遂昌县', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1157, 4, '松阳县', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1158, 4, '云和县', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1159, 4, '庆元县', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1160, 4, '景宁畲族自治县', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1161, 4, '龙泉市', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1162, 4, '其它区', 1152);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1163, 2, '安徽省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1164, 3, '合肥市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1165, 4, '瑶海区', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1166, 4, '庐阳区', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1167, 4, '蜀山区', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1168, 4, '包河区', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1169, 4, '长丰县', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1170, 4, '肥东县', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1171, 4, '肥西县', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1172, 4, '高新区', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1173, 4, '中区', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1174, 4, '其它区', 1164);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1175, 3, '芜湖市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1176, 4, '镜湖区', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1177, 4, '弋江区', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1178, 4, '鸠江区', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1179, 4, '三山区', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1180, 4, '芜湖县', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1181, 4, '繁昌县', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1182, 4, '南陵县', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1183, 4, '其它区', 1175);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1184, 3, '蚌埠市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1185, 4, '龙子湖区', 1184);
commit;
prompt 2700 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1186, 4, '蚌山区', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1187, 4, '禹会区', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1188, 4, '淮上区', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1189, 4, '怀远县', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1190, 4, '五河县', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1191, 4, '固镇县', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1192, 4, '其它区', 1184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1193, 3, '淮南市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1194, 4, '大通区', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1195, 4, '田家庵区', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1196, 4, '谢家集区', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1197, 4, '八公山区', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1198, 4, '潘集区', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1199, 4, '凤台县', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1200, 4, '其它区', 1193);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1201, 3, '马鞍山市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1202, 4, '金家庄区', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1203, 4, '花山区', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1204, 4, '雨山区', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1205, 4, '当涂县', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1206, 4, '其它区', 1201);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1207, 3, '淮北市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1208, 4, '杜集区', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1209, 4, '相山区', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1210, 4, '烈山区', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1211, 4, '濉溪县', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1212, 4, '其它区', 1207);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1213, 3, '铜陵市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1214, 4, '铜官山区', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1215, 4, '狮子山区', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1216, 4, '郊区', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1217, 4, '铜陵县', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1218, 4, '其它区', 1213);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1219, 3, '安庆市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1220, 4, '迎江区', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1221, 4, '大观区', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1222, 4, '宜秀区', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1223, 4, '怀宁县', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1224, 4, '枞阳县', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1225, 4, '潜山县', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1226, 4, '太湖县', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1227, 4, '宿松县', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1228, 4, '望江县', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1229, 4, '岳西县', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1230, 4, '桐城市', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1231, 4, '其它区', 1219);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1232, 3, '黄山市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1233, 4, '屯溪区', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1234, 4, '黄山区', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1235, 4, '徽州区', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1236, 4, '歙县', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1237, 4, '休宁县', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1238, 4, '黟县', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1239, 4, '祁门县', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1240, 4, '其它区', 1232);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1241, 3, '滁州市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1242, 4, '琅琊区', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1243, 4, '南谯区', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1244, 4, '来安县', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1245, 4, '全椒县', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1246, 4, '定远县', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1247, 4, '凤阳县', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1248, 4, '天长市', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1249, 4, '明光市', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1250, 4, '其它区', 1241);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1251, 3, '阜阳市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1252, 4, '颍州区', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1253, 4, '颍东区', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1254, 4, '颍泉区', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1255, 4, '临泉县', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1256, 4, '太和县', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1257, 4, '阜南县', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1258, 4, '颍上县', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1259, 4, '界首市', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1260, 4, '其它区', 1251);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1261, 3, '宿州市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1262, 4, '埇桥区', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1263, 4, '砀山县', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1264, 4, '萧县', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1265, 4, '灵璧县', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1266, 4, '泗县', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1267, 4, '其它区', 1261);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1268, 3, '巢湖市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1269, 4, '居巢区', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1270, 4, '庐江县', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1271, 4, '无为县', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1272, 4, '含山县', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1273, 4, '和县', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1274, 4, '其它区', 1268);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1275, 3, '六安市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1276, 4, '金安区', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1277, 4, '裕安区', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1278, 4, '寿县', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1279, 4, '霍邱县', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1280, 4, '舒城县', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1281, 4, '金寨县', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1282, 4, '霍山县', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1283, 4, '其它区', 1275);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1284, 3, '亳州市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1285, 4, '谯城区', 1284);
commit;
prompt 2800 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1286, 4, '涡阳县', 1284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1287, 4, '蒙城县', 1284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1288, 4, '利辛县', 1284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1289, 4, '其它区', 1284);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1290, 3, '池州市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1291, 4, '贵池区', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1292, 4, '东至县', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1293, 4, '石台县', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1294, 4, '青阳县', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1295, 4, '其它区', 1290);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1296, 3, '宣城市', 1163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1297, 4, '宣州区', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1298, 4, '郎溪县', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1299, 4, '广德县', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1300, 4, '泾县', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1301, 4, '绩溪县', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1302, 4, '旌德县', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1303, 4, '宁国市', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1304, 4, '其它区', 1296);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1305, 2, '福建省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1306, 3, '福州市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1307, 4, '鼓楼区', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1308, 4, '台江区', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1309, 4, '仓山区', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1310, 4, '马尾区', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1311, 4, '晋安区', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1312, 4, '闽侯县', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1313, 4, '连江县', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1314, 4, '罗源县', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1315, 4, '闽清县', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1316, 4, '永泰县', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1317, 4, '平潭县', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1318, 4, '福清市', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1319, 4, '长乐市', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1320, 4, '其它区', 1306);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1321, 3, '厦门市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1322, 4, '思明区', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1323, 4, '海沧区', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1324, 4, '湖里区', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1326, 4, '同安区', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1327, 4, '翔安区', 1321);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1329, 3, '莆田市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1330, 4, '城厢区', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1332, 4, '荔城区', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1333, 4, '秀屿区', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1335, 4, '其它区', 1329);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1336, 3, '三明市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1338, 4, '三元区', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1339, 4, '明溪县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1341, 4, '宁化县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1342, 4, '大田县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1344, 4, '沙县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1345, 4, '将乐县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1347, 4, '建宁县', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1348, 4, '永安市', 1336);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1350, 3, '泉州市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1351, 4, '鲤城区', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1353, 4, '洛江区', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1354, 4, '泉港区', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1356, 4, '安溪县', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1357, 4, '永春县', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1359, 4, '金门县', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1360, 4, '石狮市', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1362, 4, '南安市', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1363, 4, '其它区', 1350);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1365, 4, '芗城区', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1366, 4, '龙文区', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1368, 4, '漳浦县', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1369, 4, '诏安县', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1371, 4, '东山县', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1372, 4, '南靖县', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1374, 4, '华安县', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1375, 4, '龙海市', 1364);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1377, 3, '南平市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1378, 4, '延平区', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1380, 4, '浦城县', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1381, 4, '光泽县', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1383, 4, '政和县', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1384, 4, '邵武市', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1386, 4, '建瓯市', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1387, 4, '建阳市', 1377);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1389, 3, '龙岩市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1390, 4, '新罗区', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1392, 4, '永定县', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1393, 4, '上杭县', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1395, 4, '连城县', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1396, 4, '漳平市', 1389);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1398, 3, '宁德市', 1305);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1399, 4, '蕉城区', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1401, 4, '古田县', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1402, 4, '屏南县', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1404, 4, '周宁县', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1405, 4, '柘荣县', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1407, 4, '福鼎市', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1408, 4, '其它区', 1398);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1410, 3, '南昌市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1411, 4, '东湖区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1413, 4, '青云谱区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1414, 4, '湾里区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1416, 4, '南昌县', 1410);
commit;
prompt 2900 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1417, 4, '新建县', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1418, 4, '安义县', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1420, 4, '红谷滩新区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1421, 4, '经济技术开发区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1423, 4, '其它区', 1410);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1424, 3, '景德镇市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1426, 4, '珠山区', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1427, 4, '浮梁县', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1429, 4, '其它区', 1424);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1430, 3, '萍乡市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1432, 4, '湘东区', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1433, 4, '莲花县', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1435, 4, '芦溪县', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1436, 4, '其它区', 1430);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1438, 4, '庐山区', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1439, 4, '浔阳区', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1441, 4, '武宁县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1442, 4, '修水县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1444, 4, '德安县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1445, 4, '星子县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1447, 4, '湖口县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1448, 4, '彭泽县', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1450, 4, '其它区', 1437);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1451, 3, '新余市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1453, 4, '分宜县', 1451);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1454, 4, '其它区', 1451);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1456, 4, '月湖区', 1455);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1457, 4, '余江县', 1455);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1459, 4, '其它区', 1455);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1460, 3, '赣州市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1462, 4, '赣县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1463, 4, '信丰县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1465, 4, '上犹县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1466, 4, '崇义县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1468, 4, '龙南县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1469, 4, '定南县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1471, 4, '宁都县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1472, 4, '于都县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1474, 4, '会昌县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1475, 4, '寻乌县', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1477, 4, '黄金区', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1478, 4, '瑞金市', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1480, 4, '其它区', 1460);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1481, 3, '吉安市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1483, 4, '青原区', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1484, 4, '吉安县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1486, 4, '峡江县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1487, 4, '新干县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1489, 4, '泰和县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1490, 4, '遂川县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1492, 4, '安福县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1493, 4, '永新县', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1495, 4, '其它区', 1481);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1496, 3, '宜春市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1498, 4, '奉新县', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1499, 4, '万载县', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1501, 4, '宜丰县', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1502, 4, '靖安县', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1503, 4, '铜鼓县', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1504, 4, '丰城市', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1505, 4, '樟树市', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1506, 4, '高安市', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1507, 4, '其它区', 1496);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1508, 3, '抚州市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1509, 4, '临川区', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1510, 4, '南城县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1511, 4, '黎川县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1512, 4, '南丰县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1513, 4, '崇仁县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1514, 4, '乐安县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1515, 4, '宜黄县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1516, 4, '金溪县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1517, 4, '资溪县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1518, 4, '东乡县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1519, 4, '广昌县', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1520, 4, '其它区', 1508);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1521, 3, '上饶市', 1409);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1522, 4, '信州区', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1523, 4, '上饶县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1524, 4, '广丰县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1525, 4, '玉山县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1526, 4, '铅山县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1527, 4, '横峰县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1528, 4, '弋阳县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1529, 4, '余干县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1530, 4, '鄱阳县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1531, 4, '万年县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1532, 4, '婺源县', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1533, 4, '德兴市', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1534, 4, '其它区', 1521);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1535, 2, '山东省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1536, 3, '济南市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1537, 4, '历下区', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1538, 4, '市中区', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1539, 4, '槐荫区', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1540, 4, '天桥区', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1541, 4, '历城区', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1542, 4, '长清区', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1543, 4, '平阴县', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1544, 4, '济阳县', 1536);
commit;
prompt 3000 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1545, 4, '商河县', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1546, 4, '章丘市', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1547, 4, '其它区', 1536);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1548, 3, '青岛市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1549, 4, '市南区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1550, 4, '市北区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1551, 4, '四方区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1552, 4, '黄岛区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1553, 4, '崂山区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1554, 4, '李沧区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1555, 4, '城阳区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1556, 4, '开发区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1557, 4, '胶州市', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1558, 4, '即墨市', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1559, 4, '平度市', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1560, 4, '胶南市', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1561, 4, '莱西市', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1562, 4, '其它区', 1548);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1563, 3, '淄博市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1564, 4, '淄川区', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1565, 4, '张店区', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1566, 4, '博山区', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1567, 4, '临淄区', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1568, 4, '周村区', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1569, 4, '桓台县', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1570, 4, '高青县', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1571, 4, '沂源县', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1572, 4, '其它区', 1563);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1573, 3, '枣庄市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1574, 4, '市中区', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1575, 4, '薛城区', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1576, 4, '峄城区', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1577, 4, '台儿庄区', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1578, 4, '山亭区', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1579, 4, '滕州市', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1580, 4, '其它区', 1573);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1581, 3, '东营市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1582, 4, '东营区', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1583, 4, '河口区', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1584, 4, '垦利县', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1585, 4, '利津县', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1586, 4, '广饶县', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1587, 4, '西城区', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1588, 4, '东城区', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1589, 4, '其它区', 1581);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1590, 3, '烟台市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1591, 4, '芝罘区', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1592, 4, '福山区', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1593, 4, '牟平区', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1594, 4, '莱山区', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1595, 4, '长岛县', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1596, 4, '龙口市', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1597, 4, '莱阳市', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1598, 4, '莱州市', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1599, 4, '蓬莱市', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1600, 4, '招远市', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1601, 4, '栖霞市', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1602, 4, '海阳市', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1603, 4, '其它区', 1590);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1604, 3, '潍坊市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1605, 4, '潍城区', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1606, 4, '寒亭区', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1607, 4, '坊子区', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1608, 4, '奎文区', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1609, 4, '临朐县', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1610, 4, '昌乐县', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1611, 4, '开发区', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1612, 4, '青州市', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1613, 4, '诸城市', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1614, 4, '寿光市', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1615, 4, '安丘市', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1616, 4, '高密市', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1617, 4, '昌邑市', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1618, 4, '其它区', 1604);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1619, 3, '济宁市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1620, 4, '市中区', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1621, 4, '任城区', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1622, 4, '微山县', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1623, 4, '鱼台县', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1624, 4, '金乡县', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1625, 4, '嘉祥县', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1626, 4, '汶上县', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1627, 4, '泗水县', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1628, 4, '梁山县', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1629, 4, '曲阜市', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1630, 4, '兖州市', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1631, 4, '邹城市', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1632, 4, '其它区', 1619);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1633, 3, '泰安市', 1535);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1634, 4, '泰山区', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1635, 4, '岱岳区', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (1636, 4, '宁阳县', 1633);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2013, 4, '麻城市', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2014, 4, '武穴市', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2015, 4, '其它区', 2004);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2016, 3, '咸宁市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2017, 4, '咸安区', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2018, 4, '嘉鱼县', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2019, 4, '通城县', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2020, 4, '崇阳县', 2016);
commit;
prompt 3100 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2021, 4, '通山县', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2022, 4, '赤壁市', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2023, 4, '温泉城区', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2024, 4, '其它区', 2016);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2025, 3, '随州市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2026, 4, '曾都区', 2025);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2027, 4, '广水市', 2025);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2028, 4, '其它区', 2025);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2029, 3, '恩施土家族苗族自治州', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2030, 4, '恩施市', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2031, 4, '利川市', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2032, 4, '建始县', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2033, 4, '巴东县', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2034, 4, '宣恩县', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2035, 4, '咸丰县', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2036, 4, '来凤县', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2037, 4, '鹤峰县', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2038, 4, '其它区', 2029);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2039, 4, '仙桃市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2040, 4, '潜江市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2041, 4, '天门市', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2042, 4, '神农架林区', 1910);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2043, 2, '湖南省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2044, 3, '长沙市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2045, 4, '芙蓉区', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2046, 4, '天心区', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2047, 4, '岳麓区', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2048, 4, '开福区', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2049, 4, '雨花区', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2050, 4, '长沙县', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2051, 4, '望城县', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2052, 4, '宁乡县', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2053, 4, '浏阳市', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2054, 4, '其它区', 2044);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2055, 3, '株洲市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2056, 4, '荷塘区', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2057, 4, '芦淞区', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2058, 4, '石峰区', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2059, 4, '天元区', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2060, 4, '株洲县', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2061, 4, '攸县', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2062, 4, '茶陵县', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2063, 4, '炎陵县', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2064, 4, '醴陵市', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2065, 4, '其它区', 2055);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2066, 3, '湘潭市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2067, 4, '雨湖区', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2068, 4, '岳塘区', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2069, 4, '湘潭县', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2070, 4, '湘乡市', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2071, 4, '韶山市', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2072, 4, '其它区', 2066);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2073, 3, '衡阳市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2074, 4, '珠晖区', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2075, 4, '雁峰区', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2076, 4, '石鼓区', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2077, 4, '蒸湘区', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2078, 4, '南岳区', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2079, 4, '衡阳县', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2080, 4, '衡南县', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2081, 4, '衡山县', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2082, 4, '衡东县', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2083, 4, '祁东县', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2084, 4, '耒阳市', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2085, 4, '常宁市', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2086, 4, '其它区', 2073);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2087, 3, '邵阳市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2088, 4, '双清区', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2090, 4, '北塔区', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2091, 4, '邵东县', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2093, 4, '邵阳县', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2094, 4, '隆回县', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2096, 4, '绥宁县', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2097, 4, '新宁县', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2099, 4, '武冈市', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2100, 4, '其它区', 2087);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2101, 3, '岳阳市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2102, 4, '岳阳楼区', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2104, 4, '君山区', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2105, 4, '岳阳县', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2107, 4, '湘阴县', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2108, 4, '平江县', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2110, 4, '临湘市', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2111, 4, '其它区', 2101);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2113, 4, '武陵区', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2114, 4, '鼎城区', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2116, 4, '汉寿县', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2117, 4, '澧县', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2119, 4, '桃源县', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2120, 4, '石门县', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2122, 4, '其它区', 2112);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2123, 3, '张家界市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2125, 4, '武陵源区', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2126, 4, '慈利县', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2128, 4, '其它区', 2123);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2129, 3, '益阳市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2131, 4, '赫山区', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2132, 4, '南县', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2134, 4, '安化县', 2129);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2135, 4, '沅江市', 2129);
commit;
prompt 3200 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2137, 3, '郴州市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2138, 4, '北湖区', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2140, 4, '桂阳县', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2141, 4, '宜章县', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2143, 4, '嘉禾县', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2144, 4, '临武县', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2146, 4, '桂东县', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2147, 4, '安仁县', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2149, 4, '其它区', 2137);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2150, 3, '永州市', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2152, 4, '冷水滩区', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2153, 4, '祁阳县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2155, 4, '双牌县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2156, 4, '道县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2158, 4, '宁远县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2159, 4, '蓝山县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2161, 4, '江华瑶族自治县', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2162, 4, '其它区', 2150);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2164, 4, '鹤城区', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2165, 4, '中方县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2167, 4, '辰溪县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2168, 4, '溆浦县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2170, 4, '麻阳苗族自治县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2171, 4, '新晃侗族自治县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2173, 4, '靖州苗族侗族自治县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2174, 4, '通道侗族自治县', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2175, 4, '洪江市', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2176, 4, '其它区', 2163);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2178, 4, '娄星区', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2179, 4, '双峰县', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2181, 4, '冷水江市', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2182, 4, '涟源市', 2177);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2184, 3, '湘西土家族苗族自治州', 2043);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2185, 4, '吉首市', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2187, 4, '凤凰县', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2188, 4, '花垣县', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2190, 4, '古丈县', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2191, 4, '永顺县', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2193, 4, '其它区', 2184);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2194, 2, '广东省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2195, 3, '广州市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2197, 4, '越秀区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2198, 4, '海珠区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2200, 4, '白云区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2201, 4, '黄埔区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2203, 4, '花都区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2204, 4, '南沙区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2206, 4, '增城市', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2207, 4, '从化市', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2209, 4, '其它区', 2195);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2210, 3, '韶关市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2212, 4, '浈江区', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2213, 4, '曲江区', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2215, 4, '仁化县', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2216, 4, '翁源县', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2218, 4, '新丰县', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2219, 4, '乐昌市', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2220, 4, '南雄市', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2221, 4, '其它区', 2210);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2223, 4, '罗湖区', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2224, 4, '福田区', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2226, 4, '宝安区', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2227, 4, '龙岗区', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2229, 4, '其它区', 2222);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2230, 3, '珠海市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2232, 4, '斗门区', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2233, 4, '金湾区', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2235, 4, '南湾区', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2236, 4, '其它区', 2230);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2238, 4, '龙湖区', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2239, 4, '金平区', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2241, 4, '潮阳区', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2242, 4, '潮南区', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2244, 4, '南澳县', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2245, 4, '其它区', 2237);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2247, 4, '禅城区', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2248, 4, '南海区', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2250, 4, '三水区', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2251, 4, '高明区', 2246);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2253, 3, '江门市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2254, 4, '蓬江区', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2256, 4, '新会区', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2257, 4, '台山市', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2259, 4, '鹤山市', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2260, 4, '恩平市', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2261, 4, '其它区', 2253);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2262, 3, '湛江市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2263, 4, '赤坎区', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2264, 4, '霞山区', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2265, 4, '坡头区', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2266, 4, '麻章区', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2267, 4, '遂溪县', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2268, 4, '徐闻县', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2269, 4, '廉江市', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2270, 4, '雷州市', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2271, 4, '吴川市', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2272, 4, '其它区', 2262);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2273, 3, '茂名市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2274, 4, '茂南区', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2275, 4, '茂港区', 2273);
commit;
prompt 3300 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2276, 4, '电白县', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2277, 4, '高州市', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2278, 4, '化州市', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2279, 4, '信宜市', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2280, 4, '其它区', 2273);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2281, 3, '肇庆市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2282, 4, '端州区', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2283, 4, '鼎湖区', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2284, 4, '广宁县', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2285, 4, '怀集县', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2286, 4, '封开县', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2287, 4, '德庆县', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2288, 4, '高要市', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2289, 4, '四会市', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2290, 4, '其它区', 2281);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2291, 3, '惠州市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2292, 4, '惠城区', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2293, 4, '惠阳区', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2294, 4, '博罗县', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2295, 4, '惠东县', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2296, 4, '龙门县', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2297, 4, '其它区', 2291);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2298, 3, '梅州市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2299, 4, '梅江区', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2300, 4, '梅县', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2301, 4, '大埔县', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2302, 4, '丰顺县', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2303, 4, '五华县', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2304, 4, '平远县', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2305, 4, '蕉岭县', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2306, 4, '兴宁市', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2307, 4, '其它区', 2298);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2308, 3, '汕尾市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2309, 4, '城区', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2310, 4, '海丰县', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2311, 4, '陆河县', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2312, 4, '陆丰市', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2313, 4, '其它区', 2308);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2314, 3, '河源市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2315, 4, '源城区', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2316, 4, '紫金县', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2317, 4, '龙川县', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2318, 4, '连平县', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2319, 4, '和平县', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2320, 4, '东源县', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2321, 4, '其它区', 2314);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2322, 3, '阳江市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2323, 4, '江城区', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2324, 4, '阳西县', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2325, 4, '阳东县', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2326, 4, '阳春市', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2327, 4, '其它区', 2322);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2328, 3, '清远市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2329, 4, '清城区', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2330, 4, '佛冈县', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2331, 4, '阳山县', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2332, 4, '连山壮族瑶族自治县', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2333, 4, '连南瑶族自治县', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2334, 4, '清新县', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2335, 4, '英德市', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2336, 4, '连州市', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2337, 4, '其它区', 2328);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2338, 3, '东莞市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2339, 3, '中山市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2340, 3, '潮州市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2341, 4, '湘桥区', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2342, 4, '潮安县', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2343, 4, '饶平县', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2344, 4, '枫溪区', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2345, 4, '其它区', 2340);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2346, 3, '揭阳市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2347, 4, '榕城区', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2348, 4, '揭东县', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2349, 4, '揭西县', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2350, 4, '惠来县', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2351, 4, '普宁市', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2352, 4, '东山区', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2353, 4, '其它区', 2346);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2354, 3, '云浮市', 2194);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2355, 4, '云城区', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2356, 4, '新兴县', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2357, 4, '郁南县', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2358, 4, '云安县', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2359, 4, '罗定市', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2360, 4, '其它区', 2354);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2361, 2, '广西壮族自治区', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2362, 3, '南宁市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2363, 4, '兴宁区', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2364, 4, '青秀区', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2365, 4, '江南区', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2366, 4, '西乡塘区', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2367, 4, '良庆区', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2368, 4, '邕宁区', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2369, 4, '武鸣县', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2370, 4, '隆安县', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2371, 4, '马山县', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2372, 4, '上林县', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2373, 4, '宾阳县', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2374, 4, '横县', 2362);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2375, 4, '其它区', 2362);
commit;
prompt 3400 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2376, 3, '柳州市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2377, 4, '城中区', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2378, 4, '鱼峰区', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2379, 4, '柳南区', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2380, 4, '柳北区', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2381, 4, '柳江县', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2382, 4, '柳城县', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2383, 4, '鹿寨县', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2384, 4, '融安县', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2385, 4, '融水苗族自治县', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2386, 4, '三江侗族自治县', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2387, 4, '其它区', 2376);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2388, 3, '桂林市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2389, 4, '秀峰区', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2390, 4, '叠彩区', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2391, 4, '象山区', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2392, 4, '七星区', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2393, 4, '雁山区', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2394, 4, '阳朔县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2395, 4, '临桂县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2396, 4, '灵川县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2397, 4, '全州县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2398, 4, '兴安县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2399, 4, '永福县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2400, 4, '灌阳县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2401, 4, '龙胜各族自治县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2402, 4, '资源县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2403, 4, '平乐县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2404, 4, '荔浦县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2405, 4, '恭城瑶族自治县', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2406, 4, '其它区', 2388);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2407, 3, '梧州市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2408, 4, '万秀区', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2409, 4, '蝶山区', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2410, 4, '长洲区', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2411, 4, '苍梧县', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2412, 4, '藤县', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2413, 4, '蒙山县', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2414, 4, '岑溪市', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2415, 4, '其它区', 2407);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2416, 3, '北海市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2417, 4, '海城区', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2418, 4, '银海区', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2419, 4, '铁山港区', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2420, 4, '合浦县', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2421, 4, '其它区', 2416);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2422, 3, '防城港市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2423, 4, '港口区', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2424, 4, '防城区', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2425, 4, '上思县', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2426, 4, '东兴市', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2427, 4, '其它区', 2422);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2428, 3, '钦州市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2429, 4, '钦南区', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2430, 4, '钦北区', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2431, 4, '灵山县', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2432, 4, '浦北县', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2433, 4, '其它区', 2428);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2434, 3, '贵港市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2435, 4, '港北区', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2436, 4, '港南区', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2437, 4, '覃塘区', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2438, 4, '平南县', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2439, 4, '桂平市', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2440, 4, '其它区', 2434);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2441, 3, '玉林市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2442, 4, '玉州区', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2443, 4, '容县', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2444, 4, '陆川县', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2445, 4, '博白县', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2446, 4, '兴业县', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2447, 4, '北流市', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2448, 4, '其它区', 2441);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2449, 3, '百色市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2450, 4, '右江区', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2451, 4, '田阳县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2452, 4, '田东县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2453, 4, '平果县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2454, 4, '德保县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2455, 4, '靖西县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2456, 4, '那坡县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2457, 4, '凌云县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2458, 4, '乐业县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2459, 4, '田林县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2460, 4, '西林县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2461, 4, '隆林各族自治县', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2462, 4, '其它区', 2449);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2463, 3, '贺州市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2464, 4, '八步区', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2465, 4, '昭平县', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2466, 4, '钟山县', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2468, 4, '其它区', 2463);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2469, 3, '河池市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2470, 4, '金城江区', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2471, 4, '南丹县', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2473, 4, '凤山县', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2474, 4, '东兰县', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2476, 4, '环江毛南族自治县', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2477, 4, '巴马瑶族自治县', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2478, 4, '都安瑶族自治县', 2469);
commit;
prompt 3500 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2479, 4, '大化瑶族自治县', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2481, 4, '其它区', 2469);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2482, 3, '来宾市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2484, 4, '忻城县', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2485, 4, '象州县', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2487, 4, '金秀瑶族自治县', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2488, 4, '合山市', 2482);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2490, 3, '崇左市', 2361);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2491, 4, '江州区', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2493, 4, '宁明县', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2494, 4, '龙州县', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2496, 4, '天等县', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2497, 4, '凭祥市', 2490);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2499, 2, '海南省', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2500, 3, '海口市', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2501, 4, '秀英区', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2503, 4, '琼山区', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2504, 4, '美兰区', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2505, 4, '其它区', 2500);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2507, 4, '五指山市', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2508, 4, '琼海市', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2510, 4, '文昌市', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2511, 4, '万宁市', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2513, 4, '定安县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2514, 4, '屯昌县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2516, 4, '临高县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2517, 4, '白沙黎族自治县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2519, 4, '乐东黎族自治县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2520, 4, '陵水黎族自治县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2521, 4, '保亭黎族苗族自治县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2522, 4, '琼中黎族苗族自治县', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2524, 4, '南沙群岛', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2525, 4, '中沙群岛的岛礁及其海域', 2499);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2527, 4, '其它区', 1744);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2528, 2, '重庆', 1);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2530, 4, '万州区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2531, 4, '涪陵区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2533, 4, '大渡口区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2534, 4, '江北区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2536, 4, '九龙坡区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2537, 4, '南岸区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2538, 4, '北碚区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2540, 4, '双桥区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2541, 4, '渝北区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2543, 4, '黔江区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2544, 4, '长寿区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2546, 4, '潼南县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2547, 4, '铜梁县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2549, 4, '荣昌县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2550, 4, '璧山县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2552, 4, '城口县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2553, 4, '丰都县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2555, 4, '武隆县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2556, 4, '忠县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2558, 4, '云阳县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2560, 4, '巫山县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2561, 4, '巫溪县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2563, 4, '秀山土家族苗族自治县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2564, 4, '酉阳土家族苗族自治县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2565, 4, '彭水苗族土家族自治县', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2566, 4, '江津区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2567, 4, '合川区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2569, 4, '南川区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2570, 4, '其它区', 2529);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2572, 3, '成都市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2573, 4, '锦江区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2575, 4, '金牛区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2576, 4, '武侯区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2578, 4, '龙泉驿区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2579, 4, '青白江区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2581, 4, '温江区', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2582, 4, '金堂县', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2584, 4, '郫县', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2585, 4, '大邑县', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2587, 4, '新津县', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2588, 4, '都江堰市', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2590, 4, '邛崃市', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2591, 4, '崇州市', 2572);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2593, 3, '自贡市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2594, 4, '自流井区', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2596, 4, '大安区', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2597, 4, '沿滩区', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2599, 4, '富顺县', 2593);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2601, 3, '攀枝花市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2602, 4, '东区', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2604, 4, '仁和区', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2605, 4, '米易县', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2607, 4, '其它区', 2601);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2608, 3, '泸州市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2610, 4, '纳溪区', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2611, 4, '龙马潭区', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2613, 4, '合江县', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2615, 4, '古蔺县', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2616, 4, '其它区', 2608);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2618, 4, '旌阳区', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2619, 4, '中江县', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2621, 4, '广汉市', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2622, 4, '什邡市', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2624, 4, '其它区', 2617);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2625, 3, '绵阳市', 2571);
commit;
prompt 3600 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2627, 4, '游仙区', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2628, 4, '三台县', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2975, 4, '景东彝族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2976, 4, '景谷傣族彝族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2977, 4, '镇沅彝族哈尼族拉祜族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2978, 4, '江城哈尼族彝族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2979, 4, '孟连傣族拉祜族佤族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2981, 4, '西盟佤族自治县', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2982, 4, '其它区', 2971);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2983, 3, '临沧市', 2905);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2984, 4, '临翔区', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2986, 4, '云县', 2983);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2629, 4, '盐亭县', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2630, 4, '安县', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2631, 4, '梓潼县', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2632, 4, '北川羌族自治县', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2633, 4, '平武县', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2634, 4, '高新区', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2635, 4, '江油市', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2636, 4, '其它区', 2625);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2637, 3, '广元市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2638, 4, '利州区', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2639, 4, '元坝区', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2640, 4, '朝天区', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2641, 4, '旺苍县', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2642, 4, '青川县', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2643, 4, '剑阁县', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2644, 4, '苍溪县', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2645, 4, '其它区', 2637);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2646, 3, '遂宁市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2647, 4, '船山区', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2648, 4, '安居区', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2649, 4, '蓬溪县', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2650, 4, '射洪县', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2651, 4, '大英县', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2652, 4, '其它区', 2646);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2653, 3, '内江市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2654, 4, '市中区', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2655, 4, '东兴区', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2656, 4, '威远县', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2657, 4, '资中县', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2658, 4, '隆昌县', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2659, 4, '其它区', 2653);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2660, 3, '乐山市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2661, 4, '市中区', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2662, 4, '沙湾区', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2663, 4, '五通桥区', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2664, 4, '金口河区', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2665, 4, '犍为县', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2666, 4, '井研县', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2667, 4, '夹江县', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2668, 4, '沐川县', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2669, 4, '峨边彝族自治县', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2670, 4, '马边彝族自治县', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2671, 4, '峨眉山市', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2672, 4, '其它区', 2660);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2673, 3, '南充市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2674, 4, '顺庆区', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2675, 4, '高坪区', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2676, 4, '嘉陵区', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2677, 4, '南部县', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2678, 4, '营山县', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2679, 4, '蓬安县', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2680, 4, '仪陇县', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2681, 4, '西充县', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2682, 4, '阆中市', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2683, 4, '其它区', 2673);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2684, 3, '眉山市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2685, 4, '东坡区', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2686, 4, '仁寿县', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2687, 4, '彭山县', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2688, 4, '洪雅县', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2689, 4, '丹棱县', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2690, 4, '青神县', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2691, 4, '其它区', 2684);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2692, 3, '宜宾市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2693, 4, '翠屏区', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2694, 4, '宜宾县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2695, 4, '南溪县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2696, 4, '江安县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2697, 4, '长宁县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2698, 4, '高县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2699, 4, '珙县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2700, 4, '筠连县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2701, 4, '兴文县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2702, 4, '屏山县', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2703, 4, '其它区', 2692);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2704, 3, '广安市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2705, 4, '广安区', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2706, 4, '岳池县', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2707, 4, '武胜县', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2708, 4, '邻水县', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2709, 4, '华蓥市', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2710, 4, '市辖区', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2711, 4, '其它区', 2704);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2712, 3, '达州市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2713, 4, '通川区', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2714, 4, '达县', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2715, 4, '宣汉县', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2716, 4, '开江县', 2712);
commit;
prompt 3700 records committed...
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2717, 4, '大竹县', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2718, 4, '渠县', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2719, 4, '万源市', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2720, 4, '其它区', 2712);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2721, 3, '雅安市', 2571);
insert into AREA (ID, AREA_TYPE, AREA_NAME, PARENT_ID)
values (2722, 4, '雨城区', 2721);
commit;
prompt 3706 records loaded
prompt Enabling foreign key constraints for AREA...
alter table AREA enable constraint FK_AREA_REF_AREA;
prompt Enabling triggers for AREA...
alter table AREA enable all triggers;
set feedback on
set define on
prompt Done.
