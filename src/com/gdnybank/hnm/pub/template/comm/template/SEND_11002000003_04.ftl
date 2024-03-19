<#--信息通知发送 单笔短信发送-->
<SDOROOT package_type="xml">
	<#include "DEFAULTXML_head.ftl">
	<BODY>
		<CHANNEL_CODE>${(BODY.channel_code)!""}</CHANNEL_CODE>
		<#if (BODY.msg_type)??><MSG_TYPE><![CDATA[${BODY.msg_type}]]></MSG_TYPE></#if>
		<FLAG><![CDATA[${(BODY.flag)!""}]]></FLAG>
		<#if (BODY.priority_level)??><PRIORITY_LEVEL><![CDATA[${BODY.priority_level}]]></PRIORITY_LEVEL></#if>
		<#if (BODY.inform_time)??><INFORM_TIME><![CDATA[${BODY.inform_time}]]></INFORM_TIME></#if>
		<#if (BODY.acct_no)??><ACCT_NO><![CDATA[${BODY.acct_no}]]></ACCT_NO></#if>
		<MOBILE><![CDATA[${(BODY.mobile)!""}]]></MOBILE>
		<MSG><![CDATA[${(BODY.msg)!""}]]></MSG>
		<VERIFY_TYPE><![CDATA[${(BODY.verify_type)!""}]]></VERIFY_TYPE>
		<VERIFY_VALUE><![CDATA[${(BODY.verify_value)!""}]]></VERIFY_VALUE>
	</BODY>
</SDOROOT>