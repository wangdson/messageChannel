package com.ebei.message.bean;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @Author: Huangweicai
 * @date 2018-09-03 12:17
 * @Description:
 */
public class SMSLinkWSBean extends SMSEmayReq{
    
	private static final long serialVersionUID = 1L;

	@Id
    private String id;

    private Date messageDate;

    private String resCode;

    private String seqid;

    private String resMessage;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public String getSeqid() {
        return seqid;
    }

    public void setSeqid(String seqid) {
        this.seqid = seqid;
    }
}
