package com.ebei.message.bean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 12:28
 * @Description:
 */
public class WxTempleteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;
    // 消息接收方
    private String toUser;
    // 模板id
    private String templateId;
    // 模板消息详情链接
    private String url;
    private String token;

    //参数 key val 形式
    private List<WxTempleteParam> data;

    private Date messageDate;
    private int errorCode;
    private String errorMsg;

    public static void main(String[] args) {
        WxTempleteBean bean = new WxTempleteBean();
        bean.setToken("13_ESuvRcFnHEpGk8ave-Mjgz-5oo6CEtp3h7SdU5qr7jNyz36jMPexeMejynO70TqU7bWqHTZ70JnFisO7pIPCBLrgYpVA6NoLaAboAqbcbF3vzFg4Ggz9XGH93-Ad2axqrwAaKwYDhtU_42dSBBCeAAAGGH");
        bean.setTemplateId("E4akBx4owhkDQUC3q4sHfCfb3HulDvpjS29238sfCvU");
        bean.setUrl("www.rongtaioffice.com");
        bean.setToUser("haah");

        List<WxTempleteParam> params = new ArrayList<>();

        WxTempleteParam param = new WxTempleteParam();
        param.setKey("first.Data");
        param.setVal("哈哈");

        WxTempleteParam param2 = new WxTempleteParam();
        param2.setKey("remark.Data");
        param2.setVal("哈哈22");
        params.add(param);
        params.add(param2);

        bean.setData(params);


        System.out.println(bean.toJson());
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<WxTempleteParam> getData() {
        return data;
    }

    public void setData(List<WxTempleteParam> data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String toJson() {
        WxSendTempleteReq req = new WxSendTempleteReq();
        req.setTouser(toUser);
        req.setUrl(url);
        req.setTemplate_id(templateId);
        if (this.data != null && this.data.size() > 0) {
            for (WxTempleteParam bean : this.data) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", bean.getVal());
                req.addData(bean.getKey(), jsonObject);
            }
        }
        return JSONObject.toJSONString(req);
    }
}
