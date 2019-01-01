package com.ebei.message.service.impl;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ebei.message.bean.SMSAliBean;
import com.ebei.message.bean.SMSAliReq;
import com.ebei.message.bean.SMSEmayBean;
import com.ebei.message.bean.SMSEmayReq;
import com.ebei.message.dao.SMSAliDao;
import com.ebei.message.dao.SMSEmayDao;
import com.ebei.message.service.SMSAliService;
import com.ebei.message.service.SMSEmayService;
import com.ebei.message.sms.bean.AliTempleteConfig;
import com.ebei.message.utlis.HttpClientUtils;
import com.ebei.message.utlis.HttpRequestResult;
import com.ebei.message.vo.ResponseEx;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 16:43
 * @Description:
 */
@Service("sMSAliService")
public class SMSAliServiceImpl extends SMSAliService {

    @Autowired
    SMSAliDao smsAliDao;

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";


    @Autowired
    AliTempleteConfig aliTempleteConfig;


    @Override
    public ResponseEx sendMessage(SMSAliReq bean) {
        if (StringUtils.isEmpty(bean.getPhone())) {
            return ResponseEx.createException("phone参数缺失");
        }
        if (bean.getPhone().split(",").length == 0) {
            return ResponseEx.createException("phone参数错误");
        }

        String template = bean.getTemplate();


//        Map<String, String> templateMap = getConfigMap(aliTempleteConfig.getTemplate(),template);

        SMSAliBean smsAliBean = new SMSAliBean();
        smsAliBean.setPhone(bean.getPhone());
        smsAliBean.setTemplate(template);
        smsAliBean.setTemplateParam(bean.getTemplateParam());
        smsAliBean.setMessageDate(new Date());

        ResponseEx responseEx = ResponseEx.createSuccess();
        try {
            String templateParam = bean.getTemplateParam() == null ? "" : bean.getTemplateParam().toString();
            if (StringUtils.isEmpty(aliTempleteConfig.getSignNameByKey(template)) || StringUtils.isEmpty(aliTempleteConfig.getTemplateCode(template))) {
                return ResponseEx.createException("signName或templateCode为空");
            }
            SendSmsResponse sendSmsResponse = this.sendSms(bean.getPhone(), aliTempleteConfig.getSignNameByKey(template), aliTempleteConfig.getTemplateCode(template), templateParam, aliTempleteConfig.getAccessKeyId(),aliTempleteConfig.getAccessKeySecret());
            smsAliBean.setResMessage(sendSmsResponse.getMessage());
            smsAliBean.setRequestId(sendSmsResponse.getRequestId());

            if (!sendSmsResponse.getCode().equals("OK")) {
                responseEx.setStatus(-2);
                responseEx.setMessage(sendSmsResponse.getMessage());
                smsAliBean.setResCode(sendSmsResponse.getCode());
            } else {
                smsAliBean.setResCode("0");
            }
        } catch (ClientException e) {

            smsAliBean.setResCode("-1");
            smsAliBean.setResMessage(e.getMessage());

            responseEx.setStatus(-1);
            responseEx.setMessage(e.getMessage());
        }
        this.smsAliDao.insert(smsAliBean);

        return responseEx;


    }

    private Map<String, String> getConfigMap(Map<String,Map<String,String>> temMap,String key) {
        if (temMap == null || temMap.size() == 0) {
            return null;
        }
        if (StringUtils.isEmpty(key)) {
            for (Map.Entry<String, Map<String, String>> entry : temMap.entrySet()) {
                return entry.getValue();
            }
            return null;
        }

        return temMap.get(key);
    }


    @Override
    public SMSAliBean get(String id) {
        SMSAliBean bean = this.smsAliDao.get(id);
        return bean;
    }

    /**
     * 阿里云 发送短信
     * @param phoneNumbers
     * @param signName
     * @param templateCode
     * @param templateParam
     * @return
     * @throws ClientException
     */
    private SendSmsResponse sendSms(String phoneNumbers,String signName,String templateCode,String templateParam,String accessKeyId,String accessKeySecret) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumbers);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

}
