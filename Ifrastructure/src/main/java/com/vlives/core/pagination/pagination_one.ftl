<!-- 定义添加请求条件 -->
<#if pagination.queryString??>
	<#assign queryString="&"+pagination.queryString>
<#else>
	<#assign queryString="">
</#if>

<!-- currentPage默认为1，所有在没有数据时候不显示分页 -->
<#if (pagination.count>0) >






<#if ((pagination.currentPage-2)<0 )>
<#assign beginInx=0 >
<#else>
<#assign beginInx=pagination.currentPage-2 >
</#if>


<#if ((pagination.currentPage+2)>pagination.pageCount) >
<#assign endIdx=pagination.pageCount >
<#else>
<#assign endIdx=pagination.currentPage+2 >
</#if>


<span class="paging">
	<span style="font-weight:normal;font-size:12px;">显示&nbsp;${pagination.begin+1} - ${pagination.end}&nbsp;&nbsp;条&nbsp;&nbsp;共 ${pagination.count} 条${pagination.pageCount} 页&nbsp;&nbsp;</span>
 <#if  pagination.canGoPrevious >   
  <a class="prev"  href="${pagination.url}?${pagination.currentPageTarget}=${pagination.currentPage - 1}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}">&nbsp;</a>
</#if  >
 <#if  (beginInx>0)  >  
   <@displayNum  x=1 />
</#if>
 <#if  (beginInx>1) >  
     <@displayNum  x=2  />
</#if>
<#if  (beginInx>2) >  
  <span>...</span>
</#if>


<#list  (beginInx+1)..(endIdx) as i > 
<@displayNum  x=i />
</#list>


<#if  (endIdx<pagination.getPageCount()-2) >  
  <span>...</span>
</#if>

<#if  (endIdx<pagination.getPageCount()-1) >  
   <@displayNum  x=pagination.getPageCount()-1 />
</#if>
 <#if  endIdx<pagination.getPageCount() >  
   <@displayNum  x=pagination.getPageCount()/>
</#if>


<#if  pagination.canGoNext>
<A    class="next"
              href="${pagination.url}?${pagination.currentPageTarget}=${pagination.currentPage+1}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}">

            下一页
          </A>

</#if>



</span>


</#if>


<#macro displayNum  x>
<a  <#if x==pagination.currentPage>
  class=clicked
</#if>
  href="${pagination.url}?${pagination.currentPageTarget}=${x}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}">${x}</a>
<#return >
</#macro>




