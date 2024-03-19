<SYS_HEAD>
	<SERVICE_CODE><![CDATA[${SYS_HEADER.service_code!""}]]></SERVICE_CODE>
	<CONSUMER_ID><![CDATA[${SYS_HEADER.consumer_id!""}]]></CONSUMER_ID>
	<SERVICE_SCENE><![CDATA[${SYS_HEADER.service_scene!""}]]></SERVICE_SCENE>
	<MODULE_ID><![CDATA[${SYS_HEADER.module_id!""}]]></MODULE_ID>
	<PROGRAM_ID><![CDATA[${SYS_HEADER.program_id!""}]]></PROGRAM_ID>
	<CONSUMER_SEQ_NO><![CDATA[${SYS_HEADER.consumer_seq_no!""}]]></CONSUMER_SEQ_NO>
	<ORG_SYS_ID><![CDATA[${SYS_HEADER.org_sys_id!""}]]></ORG_SYS_ID>
	<TRAN_DATE><![CDATA[${SYS_HEADER.tran_date!""}]]></TRAN_DATE>
	<TRAN_TIMESTAMP><![CDATA[${SYS_HEADER.tran_timestamp!""}]]></TRAN_TIMESTAMP>
	<USER_LANG>CHINESE</USER_LANG>
</SYS_HEAD>
<APP_HEAD>
	<BRANCH_ID><![CDATA[${APP_HEADER.branch_id!""}]]></BRANCH_ID>
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
</APP_HEAD>
<LOCAL_HEAD>
	<RURAL_BRANCH_ID><![CDATA[${LOCAL_HEADER.rural_branch_id!""}]]></RURAL_BRANCH_ID>
	<BUS_SEQ_NO><![CDATA[${LOCAL_HEADER.bus_seq_no!""}]]></BUS_SEQ_NO>
	<CHANNEL_CODE><![CDATA[${LOCAL_HEADER.channel_code!""}]]></CHANNEL_CODE>
	<#if LOCAL_HEADER.check>
		<KEY_DATE><![CDATA[${LOCAL_HEADER.key_date!""}]]></KEY_DATE>
		<KEY_NAME><![CDATA[${LOCAL_HEADER.key_name!""}]]></KEY_NAME>
		<KEY_TYPE><![CDATA[${LOCAL_HEADER.key_type!""}]]></KEY_TYPE>
		<KEY_FACTOR><![CDATA[${LOCAL_HEADER.key_factor!""}]]></KEY_FACTOR>
	</#if>
	<#if (LOCAL_HEADER.link_flag)??><LINK_FLAG><![CDATA[${LOCAL_HEADER.link_flag!""}]]></LINK_FLAG></#if>
	<#if (LOCAL_HEADER.link_trans_code)??><LINK_TRANS_CODE><![CDATA[${LOCAL_HEADER.link_trans_code!""}]]></LINK_TRANS_CODE></#if>
</LOCAL_HEAD>