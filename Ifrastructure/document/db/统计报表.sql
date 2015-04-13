/**
 * 日报表
 */
select a.code 商户编号,a.name 商户名称,a.create_date 商户创建日期,
b.bcount 会员当日新增数,c.ccount 会员总数,d.dsum 现金消费金额,d.dcount 现金消费数,
e.esum 银行卡消费金额,e.ecount 银行卡消费数,f.fcount 营销活动日增数,g.gcount 营销活动到达数,hcount  商户已发短信数
from 
(select id,code,name,create_date from merchant where id not in(1019,1020)) a 
left join
(select m.id bid, count(m.id) bcount from  merchant m ,member_group mg, member mb
where m.member_group_id = mg.id and mg.id = mb.member_group_id 
and TRUNC(mb.create_date) = to_date(to_char(sysdate-1,'yyyy-mm-dd'),'yyyy-mm-dd')
group by m.id)
b on a.id =b.bid
left join
(select m.id cid, count(m.id) ccount from  merchant m ,member_group mg, member mb
where m.member_group_id = mg.id and mg.id = mb.member_group_id group by m.id)
c on a.id = c.cid
left join
(select tor.merchant_id did,sum(tor.money) dsum, count(tor.merchant_id) dcount from trade_detail td,trade_order tor
where tor.id = td.trade_order_id
and td.is_consume = 1
and tor.TYPE=2
group by tor.merchant_id)
d on a.id = d.did
left join
(select tor.merchant_id eid,sum(tor.money) esum, count(tor.merchant_id) ecount from trade_detail td,trade_order tor
where tor.id = td.trade_order_id
and td.is_consume = 1
and tor.TYPE=1
group by tor.merchant_id)
e on a.id = e.eid
 left join 
(select s.merchant_id fid,count(s.merchant_id) fcount from SALE_PLOY s where status in (1,3,4,6)
and TRUNC(s.create_date)  =to_date(to_char(sysdate-1,'yyyy-mm-dd'),'yyyy-mm-dd')
group by s.merchant_id 
) f on a.id = f.fid
left join
(select s.merchant_id gid,count(s.merchant_id) gcount from SALE_PLOY s where status in (1,3,4,6)
group by s.merchant_id 
)g on a.id =g.gid
left join
(select s.merchant_id hid,sum(s.SEND_COUNT) hcount from SALE_PLOY s  
group by s.merchant_id
)h on a.id =h.hid;


/**
 * 会员统计
 */
select u.mobile  手机号 ,m.create_date 会员加入日期,me.code 商户编号,me.name 商户名称 from member m,users u ,member_group mg,merchant me
where m.user_id = u.id and m.member_group_id = mg.id and mg.id = me.member_group_id
and me.id not in(1019,1020)
 order by me.code,m.create_date;