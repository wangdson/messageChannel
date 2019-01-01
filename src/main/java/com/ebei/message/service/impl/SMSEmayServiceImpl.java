package com.ebei.message.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ebei.message.bean.SMSEmayBean;
import com.ebei.message.bean.SMSEmayReq;
import com.ebei.message.dao.SMSEmayDao;
import com.ebei.message.service.SMSEmayService;
import com.ebei.message.utlis.HttpClientUtils;
import com.ebei.message.utlis.HttpRequestResult;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 16:43
 * @Description:
 */
@Service("sMSEmayService")
public class SMSEmayServiceImpl extends SMSEmayService{

    @Autowired
    SMSEmayDao smsEmayDao;

    @Value("${emay.cdkey}")
    String CD_KEY;
    @Value("${emay.password}")
    String PASSWORD;
//    @Value("${emay.url.sendSms}")
    String URL = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/sendsms.action";


    @Override
    public ResponseEx sendMessage(SMSEmayReq bean) {

        //获取唯一标识
        String seqid = getSeqid();
        //短信内容 规范:前置内容必须是【】 否则发送不成功
        String content = "【" + bean.getPreName() + "】" + bean.getContent();
        Map<String, Object> params = new HashMap<>();
        params.put("cdkey", StringUtils.isEmpty(bean.getCdkey()) ? CD_KEY : bean.getCdkey());
        params.put("password", StringUtils.isEmpty(bean.getPassword()) ? PASSWORD : bean.getPassword());
        params.put("phone", bean.getPhone());
        params.put("message", content);
        params.put("seqid", seqid);

        //返回格式为xml形式 <?xml version="1.0" encoding="UTF-8"?><response><error>0</error><message></message></response>
        HttpRequestResult result = HttpClientUtils.doGet(URL, params);
         String res = result.getResult();
        String code = getxmlAttr(res, "<error>", "</error>");
        String mes = getxmlAttr(res, "<message>", "</message>");


        SMSEmayBean smsBean = new SMSEmayBean();
        smsBean.setMessageDate(new Date());
        smsBean.setSeqid(seqid);
        smsBean.setContent(content);
        smsBean.setResCode(code);
        smsBean.setResMessage(mes);
        smsBean.setPreName(bean.getPreName());
        smsBean.setPhone(bean.getPhone());
        this.smsEmayDao.insert(smsBean);
        if ("0".equals(code)) {
            return ResponseEx.createSuccess();
        }
        return ResponseEx.createException(mes);
    }

    /**
     * 简单解析 暂不引入xml解析jar
     * @return
     */
    private String getxmlAttr(String xml, String startPre,String endPre) {
        int errorStartIndex = xml.indexOf(startPre);
        int errorEndIndex = xml.indexOf(endPre);
        if (errorStartIndex == -1) {
            return "-1";
        }
        if (errorEndIndex == -1) {
            return "-2";
        }
        String str = "";
        try {
            str = xml.substring(errorStartIndex + startPre.length(), errorEndIndex);
        } catch (Exception e) {
            return "-3";
        }

        return str;
    }

    /**
     * 唯一标识
     * @return
     */
    private String getSeqid() {
        //避免并发过多seqid一致
        String suf = String.format("%05d", new Random().nextInt(99999));
        return System.currentTimeMillis() + suf;
    }


    @Override
    public SMSEmayReq get(String id) {
        SMSEmayReq bean = this.smsEmayDao.get(id);
        return bean;
    }
}
