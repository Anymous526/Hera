<#if pagination.queryString??>
	<#assign queryString="&"+pagination.queryString>
<#else>
	<#assign queryString="">
</#if>
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tbody>
		<tr>
			<td height="30" width="2%"></td>
			<td width="25%">${pagination.begin+1} - ${pagination.end} 共 ${pagination.count} 条 ${pagination.pageCount} 页<br></td>
			<td align="right">
			 <#--第一页-->
				<#if (pagination.currentPage==1)>
					&nbsp;&nbsp;第一页
				<#else>
					&nbsp;&nbsp;<a href=${pagination.url}?${pagination.currentPageTarget}=1&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}>第一页</a>
				</#if>
				
				<#--上一页-->
				<#if (pagination.canGoPrevious)>
					&nbsp;&nbsp;<a href=${pagination.url}?${pagination.currentPageTarget}=${pagination.currentPage-1}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}>上一页</a>
				<#else>
					&nbsp;&nbsp;上一页					
				</#if>
				
				<#--向前跳转-->
				<#if (pagination.canSkipForward)>
					&nbsp;&nbsp;<a href=${pagination.url}?${pagination.currentPageTarget}=${pagination.skipForward}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}>...</a>
				</#if>
				
				<#--页码-->
				<#list (pagination.currentSkipPageNumbers) as number>
					 <#if (pagination.currentPage==number)>
					 		&nbsp;&nbsp;${number}
					 <#else>
					 		&nbsp;&nbsp;<a href=${pagination.url}?${pagination.currentPageTarget}=${number}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}>${number}</a>
					 </#if>
				</#list>
				
				<#--向后跳转-->
				<#if (pagination.canSkipBackward)>
					&nbsp;&nbsp;<a href=${pagination.url}?${pagination.currentPageTarget}=${pagination.skipBackward}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}>...</a>
				</#if>
				
				<#--下一页-->
				<#if (pagination.canGoNext)>
					&nbsp;&nbsp;<a href=${pagination.url}?${pagination.currentPageTarget}=${pagination.currentPage+1}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}>下一页</a>
				<#else>
					&nbsp;&nbsp;下一页					
				</#if>
				
				<#--最后一页-->
				<#if (pagination.canGoLast)>
					&nbsp;&nbsp;<a href=${pagination.url}?${pagination.currentPageTarget}=${pagination.pageCount}&${pagination.pageSizeTarget}=${pagination.pageSize}&${pagination.skipSizeTarget}=${pagination.skipSize}${queryString}>最后一页</a>
				<#else>
					&nbsp;&nbsp;最后一页
				</#if>
				
			</td>
			<td width="2%"></td>
		</tr>
	</tbody>
</table>