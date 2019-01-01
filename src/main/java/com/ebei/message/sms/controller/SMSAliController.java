package com.ebei.message.sms.controller;


import com.ebei.message.bean.SMSAliReq;
import com.ebei.message.bean.SMSEmayReq;
import com.ebei.message.service.SMSAliService;
import com.ebei.message.service.SMSEmayService;
import com.ebei.message.sms.bean.AliTempleteConfig;
import com.ebei.message.utlis.SignUtil;
import com.ebei.message.vo.ResponseEx;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 16:36
 * @Description:阿里短信
 *
 * 业务限流
 * https://help.aliyun.com/knowledge_detail/57710.html?spm=a2c4g.11186623.6.614.6d275777wHel0M
 * 举例：在您网页、APP端等提供用户获取触发短信页面时，如用户频繁获取验证码或是密码找回短信下发时，为限制您平台短信被恶意调用频繁下发，阿里云短信进行的服务流控限制，此限制对于一般使用场景是满足的，具体限制为：
 *
 * 短信验证码 ：使用同一个签名，对同一个手机号码发送短信验证码，支持1条/分钟，5条/小时，10条/天。
 * 一个手机号码通过阿里云短信服务平台只能收到40条/天（天的计算方式是是当下时间往后推24小时，
 * 例如2017年8月24日：11:00发送一条短信，计算限流方式是2017年8月23日11:00点到8月24日：11:00点，是否满40条）。
 * （如您是在发送验证码时提示业务限流，建议您根据以上业务调整接口调用时间）
 *
 * 短信通知： 使用同一个签名和同一个短信模板ID，对同一个手机号码发送短信通知，
 * 支持50条/天（天的计算方式是是当下时间往后推24小时，例如2017年8月24日：11:00发送一条短信，计算限流方式是2017年8月23日11:00点到8月24日：11:00点，是否满50条）
 * （如您是在发短信通知时提示业务限流，建议您根据以上业务调整接口调用时间）
 *
 *
 * 以上总结 :   验证码: 同个签名 支持1条/分钟，5条/小时，10条/天   一个手机号码一天最多收到40条
 *             短信通知: 50条/天
 *
 */
@RestController
@RequestMapping("/sms/ali")
public class SMSAliController {

    @Autowired
    SMSAliService smsAliService;

    @Value("${sign.key}")
    String signKey;

    @Autowired
    AliTempleteConfig aliTempleteConfig;

    //接口过期时间 30秒
    long EX_TIME = 30 * 1000;

    @GetMapping("/getTest")
    public ResponseEx getTest() {
        this.smsAliService.sendMessage(null);
        return null;
    }

    @PostMapping("sendMessage")
    public ResponseEx sendMessage(@RequestBody SMSAliReq bean) {

        if (bean.getCurTime() == 0) {
            return ResponseEx.createException("curTime为空");
        }
        if (System.currentTimeMillis() - bean.getCurTime() > EX_TIME) {
            return ResponseEx.createException("请求过期,请重新发送");
        }

        if (!SignUtil.checkSignKey(bean, bean.getSign(),signKey)) {
            return ResponseEx.createException("签名错误");
        }

        return this.smsAliService.sendMessage(bean);
    }

    @GetMapping("/getMessage")
    public ResponseEx getMessage(String id) {

        if (StringUtils.isEmpty(id)) {
            return ResponseEx.createException("id为空");
        }

        aliTempleteConfig.getTemplate();

        return ResponseEx.createSuccess(this.smsAliService.get(id));
    }


}
