package com.ebei.message.bean;


import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 12:49
 * @Description:
 */
public class WxSendTempleteReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
     * touser : OPENID
     * template_id : ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY
     * url : http://weixin.qq.com/download
     * miniprogram : {"appid":"xiaochengxuappid12345","pagepath":"index?foo=bar"}
     * data : {"first":{"value":"恭喜你购买成功！","color":"#173177"},"keyword1":{"value":"巧克力","color":"#173177"},"keyword2":{"value":"39.8元","color":"#173177"},"keyword3":{"value":"2014年9月22日","color":"#173177"},"remark":{"value":"欢迎再次购买！","color":"#173177"}}
     */

    private String touser;
    private String template_id;
    private String url;
    private MiniprogramBean miniprogram;
    private JSONObject data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MiniprogramBean getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MiniprogramBean miniprogram) {
        this.miniprogram = miniprogram;
    }


    public static class MiniprogramBean {
        /**
         * appid : wxeb39aa4f19c8ad20
         * pagepath : index?foo=bar
         */

        private String appid;
        private String pagepath;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagepath() {
            return pagepath;
        }

        public void setPagepath(String pagepath) {
            this.pagepath = pagepath;
        }
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public void addData(String key, Object val) {
        if (this.data == null) {
            this.data = new JSONObject();
        }
        this.data.put(key, val);
    }
}
