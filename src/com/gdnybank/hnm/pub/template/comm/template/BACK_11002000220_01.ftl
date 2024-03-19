<#--助农交易信息查询-->
<?xml version="1.0" encoding="UTF-8"?>
<SDOROOT package_type="xml">
    <#include "RESPONSEDEFAULTXMLBACK_head.ftl">
    <BODY>
    <#if (BODY.result)??>
        <result>${BODY.result!""}</result></#if>
    <#if (BODY.pos_sn)??>
        <pos_sn>${BODY.pos_sn!""}</pos_sn></#if>
    </BODY>
</SDOROOT>