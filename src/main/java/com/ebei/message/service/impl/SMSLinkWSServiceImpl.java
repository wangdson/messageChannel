package com.ebei.message.service.impl;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ebei.message.bean.SMSLinkWSBean;
import com.ebei.message.bean.SMSLinkWSReq;
import com.ebei.message.dao.SMSLinkWSDao;
import com.ebei.message.service.SMSLinkWSService;
import com.ebei.message.utlis.HttpClientUtils;
import com.ebei.message.utlis.HttpRequestResult;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: wufq
 * @date 2018-09-21 16:43
 * @Description:
 */
@Service("sMSLinkMSService")
public class SMSLinkWSServiceImpl extends SMSLinkWSService{

    @Autowired
    SMSLinkWSDao smsLinkWSDao;

//    @Value("${emay.cdkey}")
//    String CD_KEY;
//    @Value("${emay.password}")
//    String PASSWORD;
//    @Value("${emay.url.sendSms}")
    String URL = "https://go.mb345.com/ws/LinkWS.asmx/BatchSend2";


    @Override
    public ResponseEx sendMessage(SMSLinkWSReq bean) {

        //获取唯一标识
        String seqid = getSeqid();
        //短信内容 规范:前置内容必须是【】 否则发送不成功
        String content = bean.getContent();
        Map<String, Object> params = new HashMap<>();
        params.put("CorpID", bean.getCdkey());
        params.put("Pwd", bean.getPassword());
        params.put("Mobile", bean.getPhone());
        try {
			params.put("Content", URLEncoder.encode(content, "GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        params.put("Cell", "");
        params.put("SendTime", "");

        //返回格式为xml形式 <?xml version="1.0" encoding="UTF-8"?><response><error>0</error><message></message></response>
        HttpRequestResult result = HttpClientUtils.doGet(URL, params);
        
        String res = result.getResult();
        int statusCode = result.getStatusCode();
        String message = result.getMessage();
        System.out.println("结果"+ res);
        
        SMSLinkWSBean smsBean = new SMSLinkWSBean();
        smsBean.setMessageDate(new Date());
        smsBean.setSeqid(seqid);
        smsBean.setContent(content);
        smsBean.setResCode(String.valueOf(statusCode));
        smsBean.setPhone(bean.getPhone());
        this.smsLinkWSDao.insert(smsBean);
        if (statusCode==200) {
            return ResponseEx.createSuccess();
        }
        return ResponseEx.createException(message);
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


//    @Override
//    public SMSLinkWSReq get(String id) {
//        SMSLinkWSReq bean = this.smsEmayDao.get(id);
//        return bean;
//    }
}
