package com.ebei.message.sms.controller;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebei.message.bean.SMSEmayReq;
import com.ebei.message.service.SMSEmayService;
import com.ebei.message.utlis.SignUtil;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 16:36
 * @Description:亿美短信
 */
@RestController
@RequestMapping("/sms/emay")
public class SMSEmayController {

    @Autowired
    SMSEmayService smsEmayService;

    @Value("${sign.key}")
    String signKey;

    //接口过期时间 30秒
    long EX_TIME = 30 * 1000;

    @GetMapping("/getTest")
    public ResponseEx getTest() {
        this.smsEmayService.sendMessage(null);
        return null;
    }

    @PostMapping("sendMessage")
    public ResponseEx sendMessage(@RequestBody SMSEmayReq bean) {

        if (StringUtils.isEmpty(bean.getPreName())) {
            return ResponseEx.createException("前置内容不能为空");
        }
        if (StringUtils.isEmpty(bean.getPhone())) {
            return ResponseEx.createException("手机号码不能为空");
        }
        if (StringUtils.isEmpty(bean.getContent())) {
            return ResponseEx.createException("内容不能为空");
        }
        String phone = bean.getPhone();
        if (phone.split(",").length == 0) {
            return ResponseEx.createException("手机号码不能为空");
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

        return this.smsEmayService.sendMessage(bean);
    }

    @GetMapping("/getMessage")
    public ResponseEx getMessage(String id) {

        if (StringUtils.isEmpty(id)) {
            return ResponseEx.createException("id为空");
        }
        this.smsEmayService.get(id);

        return null;
    }


}
