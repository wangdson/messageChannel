package com.ebei.message.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: wufq
 * @date 2018-09-21 16:39
 * @Description:LinkWS短信
 */
public class SMSLinkWSReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cdkey;
    private String password;

	// 手机号码
	private String phone;
	// 短信内容（UTF-8编码）（最多500个汉字或1000个纯英文）。
	private String content;
	//
	private String cell;

//	private long sendTime;
	
	private long curTime;

	private String sign;

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

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

//	public long getSendTime() {
//		return sendTime;
//	}
//
//	public void setSendTime(long sendTime) {
//		this.sendTime = sendTime;
//	}

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

}
