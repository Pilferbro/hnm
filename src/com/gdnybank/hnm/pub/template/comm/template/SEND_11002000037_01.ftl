<SDOROOT package_type="xml">
	<#include "DEFAULTXML_head.ftl">
	<#-- 新核心日切通知 -->
	<BODY>
		<ACCT_LCASS><![CDATA[${BODY.acct_lcass!""}]]></ACCT_LCASS>
		<JOB_FLAG><![CDATA[${BODY.job_flag!""}]]></JOB_FLAG>
		<BATCH_NO><![CDATA[${BODY.batch_no!""}]]></BATCH_NO>
		<JOB_NO><![CDATA[${BODY.job_no!""}]]></JOB_NO>
		<TRANS_DATE><![CDATA[${BODY.trans_date!""}]]></TRANS_DATE>
		<SET_DATE><![CDATA[${BODY.set_date!""}]]></SET_DATE>
		<FLAG><![CDATA[${BODY.flag!""}]]></FLAG>
		<HDL_FLAG><![CDATA[${BODY.hdl_flag!""}]]></HDL_FLAG>
	</BODY>
</SDOROOT>