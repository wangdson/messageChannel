package com.ebei.message.bean;


import com.alibaba.fastjson.JSONObject;
import com.ebei.message.utlis.SignUtil;

import java.io.Serializable;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 16:39
 * @Description:阿里短信
 */
public class SMSAliReq implements Serializable {

	private static final long serialVersionUID = 1L;

//    //短信签名-可在短信控制台中找到 不填写默认
//    private String signName;
//    //短信模板-可在短信控制台中找到
//    private String templateCode;

    private String template;

    //手机号码
    private String phone;

    //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
    private JSONObject templateParam;

    private long curTime;

    private String sign;

    public static void main(String[] args) {
        SMSAliReq req = new SMSAliReq();
        req.setPhone("13951755802");
        req.setTemplate("ywrz");
        req.setCurTime(System.currentTimeMillis());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "123123");
        req.setTemplateParam(jsonObject);
        req.setSign(SignUtil.getSignKey(req, "ebei_message_sign_key"));
        System.out.println(JSONObject.toJSONString(req));
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getCurTime() {
        return curTime;
    }

    public void setCurTime(long curTime) {
        this.curTime = curTime;
    }

    public JSONObject getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(JSONObject templateParam) {
        this.templateParam = templateParam;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
