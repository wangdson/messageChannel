package com.ebei.message.bean;


import java.io.Serializable;

/**
 * @Author: Huangweicai
 * @date 2018-08-29 17:07
 * @Description:
 */
public class MessageReq implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	String beginDate;
    String endDate;

    String msgText;
    Integer current = 1;
    Integer size = 1000;

    //必传
    String projectId;
    String projectName;
    String fortype;


    String msgType;


    String toUserId;

    //1:所有 2:指定人
    String sendType;



    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFortype() {
        return fortype;
    }

    public void setFortype(String fortype) {
        this.fortype = fortype;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
}
