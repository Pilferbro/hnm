<#--风险客户查询-->
<?xml version="1.0" encoding="UTF-8"?>
<SDOROOT package_type="xml">
    <#include "RESPONSEDEFAULTXMLBACK_head.ftl">
    <BODY>
    <#if (BODY.MSG1)??>
        <MSG1>${BODY.MSG1!""}</MSG1></#if>
    <#if (BODY.MSG2)??>
        <MSG2>${BODY.MSG2!""}</MSG2></#if>
    </BODY>
</SDOROOT>