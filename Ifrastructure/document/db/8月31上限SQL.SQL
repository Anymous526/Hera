 
--2011-8-19 by Timmy
--for search area code

alter table area
add search_code varchar(100);


--为根节点添加数据
update area  set search_code = (select id from area where id=1)
	   where id =1;

update area a set a.search_code = (select a1.search_code||'_' ||a.id
	from area a1 where a1.id=a.parent_id and a1.search_code is not null)
	where search_code is null;



create index IDX_MERCHANT_NAME_ADDR ON MERCHANT(name);
create index IDX_MERCHANT_SORT_NAME_ADDR ON MERCHANT(short_name);
create index IDX_MERCHANT_ADDRESS_ADDR ON MERCHANT(business_address);
create index IDX_AREA_SEARCH_CODE on area(search_code);


commit;