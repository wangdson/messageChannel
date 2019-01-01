package com.ebei.message.sms.bean;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: Huangweicai
 * @date 2018-10-16 10:10
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "alisms")
public class AliTempleteConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private Map<String,Map<String,String>> template;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public Map<String, Map<String, String>> getTemplate() {
        return template;
    }

    public void setTemplate(Map<String, Map<String, String>> template) {
        this.template = template;
    }

    public String getSignNameByKey(String key) {
        Map<String,String> map = getConfigMap(key);
        if (map == null || map.size() == 0) {
            return "";
        }

        return map.get("signName");
    }

    public String getTemplateCode(String key) {
        Map<String,String> map = getConfigMap(key);
        if (map == null || map.size() == 0) {
            return "";
        }

        return map.get("templateCode");
    }

    private Map<String, String> getConfigMap(String key) {
        if (template == null || template.size() == 0) {
            return null;
        }
        if (StringUtils.isEmpty(key)) {
            for (Map.Entry<String, Map<String, String>> entry : template.entrySet()) {
                return entry.getValue();
            }
            return null;
        }

        return template.get(key);
    }

}
