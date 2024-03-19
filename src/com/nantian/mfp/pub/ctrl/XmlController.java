package com.nantian.mfp.pub.ctrl;

import com.nantian.mfp.framework.context.MfpContextHolder;
import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.service.TXService;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.framework.utils.PageInfo;
import com.nantian.mfp.framework.utils.RequestSystemUtils;
import com.nantian.mfp.framework.utils.XmlUtils;
import com.nantian.mfp.pub.dao.SysTxlogDao;
import com.nantian.mfp.pub.service.SysParamService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 提供ESB系统调用接口 请求如: /应用英文名/xmlCtrl
 *
 * @author
 */
@Controller
@RequestMapping("/xmlCtrl")
public class XmlController extends BaseController {

    Log logger = LogFactory.getLog(getClass());
    @Autowired
    SysTxlogDao sysTxlogDao;
    @Autowired
    SysParamService sysParamService;

    /***
     * 普通纯数据请求
     *
     * @param p
     * @param req
     * @param
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public String doTx(@RequestBody(required = false) String xml, HttpServletRequest req, Model model) throws Exception {
        Map<String, Object> reportMap = new XmlUtils().String2Map(xml);
        logger.info(xml);
        Map<String, Object> SYS_HEADER = (Map<String, Object>) reportMap.get("SYS_HEAD");

        String txcode = "esb" + SYS_HEADER.get("SERVICE_CODE") + SYS_HEADER.get("SERVICE_SCENE");
        String valueStr = (String) mfpRouteServce.callService(txcode, getTxEnv(req, txcode), reportMap);
//        String valueStr = mapToXmlStr(valueMap);
        logger.info("返回报文："+valueStr);
        return valueStr;
    }

    public static String mapToXmlStr(Map<?, ?> map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<root>\n");
        mapToXML(map, sb);
        sb.append("</root>");
        return sb.toString();
    }

    private static void mapToXML(Map<?, ?> map, StringBuffer sb) {
        Set<?> set = map.keySet();
        for (Object o : set) {
            String key = (String) o;
            Object value = map.get(key);
            if (value instanceof Map) {
                sb.append("<").append(key).append(">\n");
                mapToXML((Map<?, ?>) value, sb);
                sb.append("</").append(key).append(">\n");
            } else if (value instanceof List) {
                List<?> list = (List<?>) map.get(key);
                for (Object item : list) {
                    sb.append("<").append(key).append(">\n");
                    Map<?, ?> hm = (Map<?, ?>) item;
                    mapToXML(hm, sb);
                    sb.append("</").append(key).append(">\n");
                }
            } else {
                sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">\n");
            }
        }
    }


    protected Map<String, Object> mergeParameter(Map<String, Object> p, HttpServletRequest req) {
        if (p == null) {
            p = new HashMap<String, Object>();
        }
        Map<String, String[]> p1 = req.getParameterMap();
        for (Entry<String, String[]> entry : p1.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values.length > 1) {
                p.put(key, values);
            } else {
                String value = values[0];
                if (value.startsWith("{") && value.endsWith("}")) {
                    Map<String, Object> map = new HashMap<>();
                    String[] split = value.substring(1, value.length() - 1).split(",");
                    for (int i = 0; i < split.length; i++) {
                        String[] split1 = split[i].split("=");
                        if (split1.length > 1) {
                            map.put(split1[0].trim(), split1[1].trim());
                        } else {
                            map.put(split1[0].trim(), "");
                        }
                    }
                    p.put(key, map);

                } else {

                    p.put(key, value);
                }
            }
        }
        return p;
    }

    public Object callService(Map<String, Object> env, Map<String, Object> p, HttpServletRequest req, String txcode)
            throws Exception {
        Object bean = null;
        Object value = null;
        String beanName = "t" + txcode + "Service";
        if (logger.isDebugEnabled()) {
            logger.debug(
                    "交易码[" + txcode + "],对应的服务类应为[T" + txcode + "Service],或者服务名称应为[" + beanName + "],应该实现TXService接口");
        }
        try {
            bean = MfpContextHolder.getBean(beanName);

        } catch (Exception e) {
            logger.error("没有找到交易服务[" + beanName + "]" + "交易码[" + txcode + "],对应的服务类类名应为[T" + txcode
                    + "Service]或者服务名称应为[" + beanName + "].\n" + "该错误可能是服务类注解配置错误引起的,请确保满足下列条件之一 \n 1.服务类类名为[T" + txcode
                    + "Service],并且其中的注解为 @Service \n 2.服务类类名为任意名字,但是注解为 @Service(\"" + beanName + "\")", e);
            throw new BusinessException("txController45", "没有找到交易服务[" + beanName + "]");
        }
        if (bean instanceof TXService) {
            TXService service = (TXService) bean;
            // 新增请求参数和返回结果的输出、交易流水表的日志纪录
            logger.debug("请求参数: " + p.toString());
            int status = 0;// 执行结果，0:成功，1:失败
            Map<String, Object> params = new HashMap<String, Object>();// 用于传输日志纪录的参数
            long startTime = 0l;
            // txcode:交易码，tran_date:交易日期，start_time:交易开始时间，end_time:交易结束时间，use_time:交易耗时，user_id:客户号，req_flow_no：请求流水号，status：执行结果,errCode:
            try {
                params.put("txcode", txcode);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                params.put("tran_date", sdf.format(new Date()));
                params.put("user_id", env.get("userid"));
                params.put("req_flow_no", MfpContextHolder.getReqFlowNo());
                sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SS");
                startTime = System.currentTimeMillis();
                params.put("start_time", sdf.format(new Date(startTime)));
                value = service.doService(env, p);
            } catch (Exception e) {
                // 纪录日志表
                status = 1;
                e.printStackTrace();
                if (null == e.getMessage() || "".equals(e.getMessage())) {
                    params.put("err_msg", e.toString());
                } else {
                    params.put("err_msg", e.getMessage());
                }
                if (e instanceof BusinessException) {
                    params.put("err_code", ((BusinessException) e).getTerrcode());
                    throw new BusinessException(((BusinessException) e).getTerrcode(), e.getMessage());
                } else {
                    throw new Exception(e);
                }
            } finally {
                long endTime = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SS");
                params.put("end_time", sdf.format(new Date(endTime)));
                long useTime = (endTime - startTime);
                params.put("use_time", useTime);
                params.put("status", status);
                sysTxlogDao.save(params);
            }
            logger.debug("service的返回结果为" + value.toString());
        } else {
            throw new BusinessException("txController51", bean.getClass() + "没有实现TXService接口,不能作为交易服务进行调用");
        }
        return value;
    }

    public PageInfo initPageInfo(Map<String, Object> p) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setIDisplayStart((Integer) p.get("iDisplayStart"));
        pageInfo.setSEcho((Integer) p.get("sEcho"));
        pageInfo.setIDisplayLength((Integer) p.get("iDisplayLength"));
        // add by lanweizhi 目前排序只支持select语句不存在重名字段的排序
        int iSortCol = (Integer) p.get("iSortCol_0");
        String sortName = (String) p.get("mDataProp_" + iSortCol);
        if (!"".equals(sortName) && sortName != null) {
            String sSortDir = (String) p.get("sSortDir_0");
            pageInfo.setOrderBy(" order by " + sortName + " " + sSortDir + " ");
        }
        // add end
        return pageInfo;
    }

    public Map<String, Object> getTxEnv(HttpServletRequest req, String txcode) {
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("userid", MfpContextHolder.getLoginId());
        // env.put("userid", MfpContextHolder.getLoginId());
        env.put("txcode", txcode);
        env.put("sessionid", req.getSession().getId());
        String needSys = sysParamService.getSysParam("REQUEST_SYS_CONFIG", "NEED_SYS_TXCODE", "");
        Boolean checkFlag = -1 < needSys.indexOf(txcode) ? true : false;
        if (checkFlag) {
            String ipAddr = RequestSystemUtils.getIpAddr(req);
            //String hostName = RequestSystemUtils.getHostName(ipAddr);
            //String  macAddress= RequestSystemUtils.getMacAddress(ipAddr);
            env.put("ip_address", ipAddr);
            //env.put("host_name", hostName);
            //env.put("mac_address", macAddress);
            System.out.println("env----" + env);
        }
        return env;
    }

}
