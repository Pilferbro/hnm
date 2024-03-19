<sysHead>
	<consumerId><![CDATA[${sysHead.consumerId!""}]]></consumerId>
	<moduleId><![CDATA[${sysHead.moduleId!""}]]></moduleId>
	<programId><![CDATA[${sysHead.program_id!""}]]></programId>
	<consumerSeqNo><![CDATA[${sysHead.consumerSeqNo!""}]]></consumerSeqNo>
	<orgSysId><![CDATA[${sysHead.orgSysId!""}]]></orgSysId>
	<tranDate><![CDATA[${sysHead.tranDate!""}]]></tranDate>
	<tranTimestamp><![CDATA[${sysHead.tranTimestamp!""}]]></tranTimestamp>
</sysHead>
<appHead>
	<branchId><![CDATA[${appHead.branchId!""}]]></branchId>
	<userId><![CDATA[${appHead.userId!""}]]></userId>
	<#if (appHead.authUserIdArray)??>
		<#list appHead.authUserIdArray as auth_user>
			<authUserIdArray type="java.util.ArrayList">
				<authUserId>${auth_user.authUserId!""}</authUserId>
				<authLevel>${auth_user.authLevel!""}</authLevel>
			</authUserIdArray>
		</#list>
	</#if>
	<#if (appHead.perPageNum)??><perPageNum><![CDATA[${appHead.perPageNum!""}]]></perPageNum></#if>
	<#if (appHead.queryKey)??><queryKey><![CDATA[${appHead.queryKey!""}]]></queryKey></#if>
	<#if (appHead.pgupOrPgdn)??><pgupOrPgdn><![CDATA[${appHead.pgupOrPgdn!""}]]></pgupOrPgdn></#if>
	<#if (appHead.bizSeqNo)??><bizSeqNo><![CDATA[${appHead.bizSeqNo!""}]]></bizSeqNo></#if>
</appHead>
<localHead>
	<ruralBranchId><![CDATA[${localHead.ruralBranchId!""}]]></ruralBranchId>
	<busSeqNo><![CDATA[${localHead.busSeqNo!""}]]></busSeqNo>
	<channelCode><![CDATA[${localHead.channelCode!""}]]></channelCode>
	<#if localHead.check>
		<keyDate><![CDATA[${localHead.keyDate!""}]]></keyDate>
		<keyName><![CDATA[${localHead.keyName!""}]]></keyName>
		<keyType><![CDATA[${localHead.keyType!""}]]></keyType>
		<keyFactor><![CDATA[${localHead.keyFactor!""}]]></keyFactor>
	</#if>
	<#if (localHead.linkFlag)??><linkFlag><![CDATA[${localHead.linkFlag!""}]]></linkFlag></#if>
	<#if (localHead.linkTransCode)??><linkTransCode><![CDATA[${localHead.linkTransCode!""}]]></linkTransCode></#if>
</localHead>