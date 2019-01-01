package com.ebei.message.sms.controller;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebei.message.bean.SMSLinkWSReq;
import com.ebei.message.service.SMSLinkWSService;
import com.ebei.message.utlis.SignUtil;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: wufq
 * @date 2018-09-21 16:36
 * @Description:LinkWS短信
 */
@RestController
@RequestMapping("/sms/linkWS")
public class SMSLinkWSController {

    @Autowired
    SMSLinkWSService smsLinkWSService;

    @Value("${sign.key}")
    String signKey;

    //接口过期时间 30秒
    long EX_TIME = 30 * 1000;

    @PostMapping("/sendMessage")
    public ResponseEx sendMessage(@RequestBody SMSLinkWSReq bean) {
    	
    	if (StringUtils.isEmpty(bean.getCdkey())) {
            return ResponseEx.createException("cdkey不能为空");
        }
        if (StringUtils.isEmpty(bean.getPassword())) {
            return ResponseEx.createException("password不能为空");
        }
        if (StringUtils.isEmpty(bean.getPhone())) {
            return ResponseEx.createException("手机号码不能为空");
        }
        if (StringUtils.isEmpty(bean.getContent())) {
            return ResponseEx.createException("内容不能为空");
        }
        if (bean.getCurTime() == 0) {
            return ResponseEx.createException("curTime为空");
        }
        if (System.currentTimeMillis() - bean.getCurTime() > EX_TIME) {
            return ResponseEx.createException("请求过期,请重新发送");
        }
        if (!SignUtil.checkSignKey(bean, bean.getSign(),signKey)) {
            return ResponseEx.createException("签名错误");
        }
      
        return this.smsLinkWSService.sendMessage(bean);
    }

//    @GetMapping("/getMessage")
//    public ResponseEx getMessage(String id) {
//
//        if (StringUtils.isEmpty(id)) {
//            return ResponseEx.createException("id为空");
//        }
//        this.smsLinkWSService.get(id);
//
//        return null;
//    }


}
