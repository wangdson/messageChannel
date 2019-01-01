package com.ebei.message.wx.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ebei.message.bean.WxTempleteBean;
import com.ebei.message.service.WxTempleteService;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 13:01
 * @Description:
 */
@RestController
@RequestMapping("/wx/template")
public class WxController {

    @Autowired
    WxTempleteService wxTempleteService;

    /**
     * 发送模板消息
     * @param bean
     * @return
     */
    @PostMapping("/sendMessage")
    public ResponseEx sendTemplete(@RequestBody WxTempleteBean bean) {
        if (StringUtils.isEmpty(bean.getToken())) {
            return ResponseEx.createError("token为空");
        }
        if (StringUtils.isEmpty(bean.getToUser())) {
            return ResponseEx.createError("toUser为空");
        }
        if (StringUtils.isEmpty(bean.getTemplateId())) {
            return ResponseEx.createError("templateId为空");
        }

        ResponseEx responseEx = this.wxTempleteService.sendTemplete(bean);

        if (responseEx.isSuccess()) {
            bean.setErrorCode(0);
        } else {
            bean.setErrorCode(responseEx.getStatus());
            bean.setErrorMsg(responseEx.getMessage());
        }
        bean.setMessageDate(new Date());
        this.wxTempleteService.insert(bean);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", bean.getId());
        responseEx.setData(jsonObject);
        return responseEx;
    }


    /**
     * 根据id获取消息
     * @param id {@link WxTempleteBean}的id
     * @return
     */
    @GetMapping("/getMessage")
    public ResponseEx getMessage(String id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseEx.createException("id为空");
        }
        WxTempleteBean bean = this.wxTempleteService.get(id);
        bean.setToken(null);
        return ResponseEx.createSuccess(bean);
    }




}
