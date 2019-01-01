package com.ebei.message.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Huangweicai
 * @date 2018-08-29 15:51
 * @Description:
 */
public class MessageBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * fortype : 接收人类型（PC/APP）
     * msgType : 消息类型（审核/订单状态变更/认证/服务）
     * urlType : url类别(根据类别后台自定义URL)
     * urlParams : url参数
     * title : 消息标题
     * content : 消息内容
     * fromUserId: : 发送人ID
     * fromUserName : 发送人名称
     * toUserId : 接收人Id（PC userId / APP memberId）
     * toUserName : 接收人名称
     * operateUserId : 操作人Id
     * operateUserName : 操作人名称
     * projectId:项目ID(新增字段)【必传】
     projectName:项目名称（新增字段）【必传】
     companyId
     公司ID（新增字段）
     */

    @Id
    private String id;

    private String fortype;
    private String msgType;
    private String urlType;
    private String urlParams;
    private String title;
    private String content;
    private String fromUserId;
    private String fromUserName;
    private String toUserId;
    private String toUserName;
    private String operateUserId;
    private String operateUserName;

    private String projectId;
    private String projectName;

    private String companyId;


    private Date messageDate;

    //已读1  未读 0
    private int isRead = 0;
    private Date readDate;

    public static void main(String[] args) {
        MessageBean bean = new MessageBean();
        bean.setContent("哈哈");
        bean.setMsgType("1");
        bean.setProjectId("111");
        bean.setProjectName("项目");
        System.out.println(JSONObject.toJSONString(bean));
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }


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

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(String urlParams) {

        this.urlParams = urlParams;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
