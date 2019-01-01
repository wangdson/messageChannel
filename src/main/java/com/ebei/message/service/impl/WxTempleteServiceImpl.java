package com.ebei.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ebei.message.bean.WxTempleteBean;
import com.ebei.message.dao.WxTempleteDao;
import com.ebei.message.service.WxTempleteService;
import com.ebei.message.utlis.HttpClientUtils;
import com.ebei.message.utlis.HttpRequestResult;
import com.ebei.message.vo.ResponseEx;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 12:31
 * @Description:
 */
@Service("wxTempleteService")
public class WxTempleteServiceImpl implements WxTempleteService{

    private final String URL_SEND_TEMPLETE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    @Autowired
    WxTempleteDao wxTempleteDao;

    @Override
    public ResponseEx sendTemplete(WxTempleteBean bean) {
        if (StringUtils.isEmpty(bean.getToken())) {
            return ResponseEx.createException("token为空");
        }

        if (bean == null) {
            return ResponseEx.createException("参数错误");
        }
        String reqJson = bean.toJson();
        System.out.println(reqJson);
        HttpRequestResult result = HttpClientUtils.doPost(URL_SEND_TEMPLETE + bean.getToken(), reqJson);
        String resStr = result.getResult();
        JSONObject js = JSONObject.parseObject(resStr);
        if (js.getInteger("errcode") == 0) {
            return ResponseEx.createSuccess();
        }
        return ResponseEx.createError(js.getInteger("errcode"),js.getString("errmsg"));
    }

    public void insert(WxTempleteBean bean) {
        this.wxTempleteDao.insert(bean);
    }

    public WxTempleteBean get(String id) {
        WxTempleteBean bean = this.wxTempleteDao.get(id);
        return bean;
    }



}
