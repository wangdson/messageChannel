package com.ebei.message.bean;


import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.ebei.message.utlis.SignUtil;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 16:39
 * @Description:亿美短信
 */
public class SMSEmayReq implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cdkey;
    private String password;

    //前置内容 必填
    private String preName;
    //手机号码
    private String phone;
    //短信内容（UTF-8编码）（最多500个汉字或1000个纯英文）。
    private String content;
    //长整型值企业内部必须保持唯一，获取状态报告使用

    private long curTime;

    private String sign;

    public static void main(String[] args) {
        SMSEmayReq req = new SMSEmayReq();
        req.setContent("哈哈");
        req.setPhone("13861515855");
//        13951755802
        req.setPreName("公司");
        req.setCurTime(System.currentTimeMillis());
        req.setSign(SignUtil.getSignKey(req, "ebei_message_sign_key"));
        System.out.println(JSONObject.toJSONString(req));
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
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

    public String getCdkey() {
        return cdkey;
    }

    public void setCdkey(String cdkey) {
        this.cdkey = cdkey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
