<LOCAL_HEAD>
    <#if (LOCAL_HEADER.key_name)??>
        <KEY_NAME>${LOCAL_HEADER.key_name!""}</KEY_NAME></#if>
    <#if (LOCAL_HEADER.key_type)??>
        <KEY_TYPE>${LOCAL_HEADER.key_type!""}</KEY_TYPE></#if>
    <#if (LOCAL_HEADER.rural_branch_id)??>
        <RURAL_BRANCH_ID>${LOCAL_HEADER.rural_branch_id!""}</RURAL_BRANCH_ID></#if>
    <#if (LOCAL_HEADER.link_flag)??>
        <LINK_FLAG>${LOCAL_HEADER.link_flag!""}</LINK_FLAG></#if>
    <#if (LOCAL_HEADER.key_date)??>
        <KEY_DATE>${LOCAL_HEADER.key_date!""}</KEY_DATE></#if>
    <#if (LOCAL_HEADER.channel_code)??>
        <CHANNEL_CODE>${LOCAL_HEADER.channel_code!""}</CHANNEL_CODE></#if>
    <#if (LOCAL_HEADER.bus_seq_no)??>
        <BUS_SEQ_NO>${LOCAL_HEADER.bus_seq_no!""}</BUS_SEQ_NO></#if>
    <#if (LOCAL_HEADER.bus_seq_no)??>
        <BUS_SEQ_NO>${LOCAL_HEADER.bus_seq_no!""}</BUS_SEQ_NO></#if>
</LOCAL_HEAD>
<SYS_HEAD>
    <#if (SYS_HEADER.tran_date)??>
        <TRAN_DATE>${SYS_HEADER.tran_date!""}</TRAN_DATE></#if>
    <#if (SYS_HEADER.consumer_id)??>
        <CONSUMER_ID>${SYS_HEADER.consumer_id!""}</CONSUMER_ID></#if>
    <#if (SYS_HEADER.consumer_svr_id)??>
        <CONSUMER_SVR_ID>${SYS_HEADER.consumer_svr_id!""}</CONSUMER_SVR_ID></#if>
    <#if (SYS_HEADER.tran_timestamp)??>
        <TRAN_TIMESTAMP>${SYS_HEADER.tran_timestamp!""}</TRAN_TIMESTAMP></#if>
    <#if (SYS_HEADER.service_code)??>
        <SERVICE_CODE>${SYS_HEADER.service_code!""}</SERVICE_CODE></#if>
    <#if (SYS_HEADER.consumer_seq_no)??>
        <CONSUMER_SEQ_NO>${SYS_HEADER.consumer_seq_no!""}</CONSUMER_SEQ_NO></#if>
    <#if (SYS_HEADER.user_lang)??>
        <USER_LANG>${SYS_HEADER.user_lang!""}</USER_LANG></#if>

    <SERVICE_SCENE>${SYS_HEADER.service_scene!""}</SERVICE_SCENE>
    <RET_STATUS>${SYS_HEADER.ret_status!""}</RET_STATUS>
    <RET>
        <SDO>
            <RET_MSG>${SYS_HEADER.ret_msg!""}</RET_MSG>
            <RET_CODE>${SYS_HEADER.ret_code!""}</RET_CODE>
        </SDO>
    </RET>
    <#if (SYS_HEADER.org_sys_id)??>
        <ORG_SYS_ID>${SYS_HEADER.org_sys_id!""}</ORG_SYS_ID></#if>
</SYS_HEAD>
<APP_HEAD>
    <#if (APP_HEADER.biz_seq_no)??>
        <BIZ_SEQ_NO>${APP_HEADER.biz_seq_no!""}</BIZ_SEQ_NO></#if>
    <#if (APP_HEADER.per_page_num)??>
        <PER_PAGE_NUM>${APP_HEADER.per_page_num!""}</PER_PAGE_NUM></#if>
    <#if (APP_HEADER.user_id)??>
        <USER_ID>${APP_HEADER.user_id!""}</USER_ID></#if>
    <#if (APP_HEADER.branch_id)??>
        <BRANCH_ID>${APP_HEADER.branch_id!""}</BRANCH_ID></#if>
    <#if (APP_HEADER.reversal_seq_no)??>
        <REVERSAL_SEQ_NO>${APP_HEADER.reversal_seq_no!""}</REVERSAL_SEQ_NO></#if>
    <#if (APP_HEADER.pgup_or_pgdn)??>
        <PGUP_OR_PGDN>${APP_HEADER.pgup_or_pgdn!""}</PGUP_OR_PGDN></#if>
    <#if (APP_HEADER.reversal_date)??>
        <REVERSAL_DATE>${APP_HEADER.reversal_date!""}</REVERSAL_DATE></#if>
</APP_HEAD>
