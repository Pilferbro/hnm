<SYS_HEAD>
	<SERVICE_CODE><![CDATA[${SYS_HEADER.service_code!""}]]></SERVICE_CODE>
	<CONSUMER_ID><![CDATA[${SYS_HEADER.consumer_id!""}]]></CONSUMER_ID>
	<SERVICE_SCENE><![CDATA[${SYS_HEADER.service_scene!""}]]></SERVICE_SCENE>
	<ESB_SEQ_NO><![CDATA[${SYS_HEADER.esb_seq_no!""}]]></ESB_SEQ_NO>
	<#if (SYS_HEADER.module_id)??><MODULE_ID><![CDATA[${SYS_HEADER.module_id!""}]]></MODULE_ID></#if>
	<#if (SYS_HEADER.program_id)??> <PROGRAM_ID><![CDATA[${SYS_HEADER.program_id!""}]]></PROGRAM_ID></#if>
	<CONSUMER_SEQ_NO><![CDATA[${SYS_HEADER.consumer_seq_no!""}]]></CONSUMER_SEQ_NO>
	<#if (SYS_HEADER.org_sys_id)??> <ORG_SYS_ID><![CDATA[${SYS_HEADER.org_sys_id!""}]]></ORG_SYS_ID></#if>
	<#if (SYS_HEADER.consumer_svr_id)??> <CONSUMER_SVR_ID><![CDATA[${SYS_HEADER.consumer_svr_id!""}]]></CONSUMER_SVR_ID></#if>
	<#if (SYS_HEADER.ws_id)??> <WS_ID><![CDATA[${SYS_HEADER.ws_id!""}]]></WS_ID></#if>
	<TRAN_DATE><![CDATA[${SYS_HEADER.tran_date!""}]]></TRAN_DATE>
	<TRAN_TIMESTAMP><![CDATA[${SYS_HEADER.tran_timestamp!""}]]></TRAN_TIMESTAMP>
	<USER_LANG>CHINESE</USER_LANG>
	<RET_STATUS><![CDATA[${SYS_HEADER.ret_status!""}]]></RET_STATUS>
	<RET>
		<#list SYS_HEADER.ret as retarr>
		<SDO>
			<RET_CODE>${retarr.ret_code!""}</RET_CODE>
			<RET_MSG>${retarr.ret_msg!""}</RET_MSG>
		</SDO>
		</#list>
	</RET>
</SYS_HEAD>
<APP_HEAD>
	<#if (APP_HEADER.branch_id)??><BRANCH_ID><![CDATA[${APP_HEADER.branch_id!""}]]></BRANCH_ID></#if>
	<USER_ID><![CDATA[${APP_HEADER.user_id!""}]]></USER_ID>
	<#if (APP_HEADER.auth_user_id_array)??>
	<AUTH_USER_ID_ARRAY>
		<#list APP_HEADER.auth_user_id_array as auth_user>
		<SDO>
			<AUTH_USER_ID>${auth_user.auth_user_id!""}</AUTH_USER_ID>
			<AUTH_LEVEL>${auth_user.auth_level!""}</AUTH_LEVEL>
		</SDO>
		</#list>
	</AUTH_USER_ID_ARRAY>
	</#if>
	<#if (APP_HEADER.per_page_num)??><PER_PAGE_NUM><![CDATA[${APP_HEADER.per_page_num!""}]]></PER_PAGE_NUM></#if>
	<#if (APP_HEADER.query_key)??><QUERY_KEY><![CDATA[${APP_HEADER.query_key!""}]]></QUERY_KEY></#if>
	<#if (APP_HEADER.pgup_or_pgdn)??><PGUP_OR_PGDN><![CDATA[${APP_HEADER.pgup_or_pgdn!""}]]></PGUP_OR_PGDN></#if>
	<#-- “BIZ_SEQ_NO”这个key核心还有待改造，故而先注释掉 modify by chenhao 20170616 -->
	<#-- COR000031交易需要用到BIZ_SEQ_NO，故而，放开-->
	<#if (APP_HEADER.biz_seq_no)??><BIZ_SEQ_NO><![CDATA[${APP_HEADER.biz_seq_no!""}]]></BIZ_SEQ_NO></#if>
	<#if (APP_HEADER.serv_seq_no)??><SERV_SEQ_NO><![CDATA[${APP_HEADER.serv_seq_no!""}]]></SERV_SEQ_NO></#if>
</APP_HEAD>
<LOCAL_HEAD>
	<#if (LOCAL_HEADER.rural_branch_id)??><RURAL_BRANCH_ID><![CDATA[${LOCAL_HEADER.rural_branch_id!""}]]></RURAL_BRANCH_ID></#if>
	<BUS_SEQ_NO><![CDATA[${LOCAL_HEADER.bus_seq_no!""}]]></BUS_SEQ_NO>
	<CHANNEL_CODE><![CDATA[${LOCAL_HEADER.channel_code!""}]]></CHANNEL_CODE>
</LOCAL_HEAD>