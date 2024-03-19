<#--惠农系统用户同步-->
<SDOROOT package_type="xml">
	<#include "DEFAULTXML_head.ftl">
	<BODY>
    	<USER_NO><![CDATA[${BODY.user_no!""}]]></USER_NO>
    	<USER_NAME><![CDATA[${BODY.user_name!""}]]></USER_NAME>
    	<TRANS_TYPE><![CDATA[${BODY.trans_type!""}]]></TRANS_TYPE>
    	<BRANCH_NO><![CDATA[${BODY.branch_no!""}]]></BRANCH_NO>
    	<BRANCH_NAME><![CDATA[${BODY.branch_name!""}]]></BRANCH_NAME>
		<#if (BODY.iden_type)??><IDEN_TYPE><![CDATA[${BODY.iden_type!""}]]></IDEN_TYPE></#if>
		<#if (BODY.iden_no)??><IDEN_NO><![CDATA[${BODY.iden_no!""}]]></IDEN_NO></#if>
		<#if (BODY.phone_no)??><PHONE_NO><![CDATA[${BODY.phone_no!""}]]></PHONE_NO></#if>
		<#if (BODY.email)??><EMAIL><![CDATA[${BODY.email!""}]]></EMAIL></#if>
	</BODY>
</SDOROOT>