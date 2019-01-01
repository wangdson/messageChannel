package com.ebei.message.controller;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebei.message.bean.MessageBean;
import com.ebei.message.bean.MessageReq;
import com.ebei.message.dao.MessageDao;
import com.ebei.message.vo.ResponseEx;
import com.ebei.message.vo.ResponsePageEx;

/**
 * @Author: Huangweicai
 * @date 2018-08-29 15:45
 * @Description:
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageDao messageDao;


    @GetMapping("/getTest")
    public ResponseEx getTest() {
        return ResponseEx.createSuccess();
    }


    @PostMapping("/save")
    public ResponseEx saveMessage(@RequestBody MessageBean bean) {
        bean.setMessageDate(new Date());
        messageDao.insert(bean);
        return ResponseEx.createSuccess();
    }
//    /wx/template/
//    com.ebei.message.wx.controller.WxController
//    /sms/ali/
//    com.ebei.message.sms.controller.AliController
//    com.ebei.message.sms.controller.YMControll
//
//    微信模板
//
//
//
//    sendTemplateMessage
//    TemplateMessage




    /**
     * "dateBegin":"发送开始时间",
     * "dateEnd":"发送结束时间",
     * "fortype":"接收人类型（PC/APP）",
     * "msgType":"消息类型（审核/订单状态变更/认证/服务）",
     * "toUserId":"接收人Id（PC userId / APP memberId）",
     * "msgText":"模糊对比title/content"
     * @return
     */
    @GetMapping("/getMessage")
    public ResponsePageEx getMessage(MessageReq messageReq) {

        if (StringUtils.isEmpty(messageReq.getFortype())) {
            return ResponsePageEx.createException("缺少fortype参数");
        }
        if (StringUtils.isEmpty(messageReq.getSendType())) {
            return ResponsePageEx.createException("缺少sendType参数");
        }
        if (!"1".equals(messageReq.getSendType()) && !"2".equals(messageReq.getSendType())) {
            return ResponsePageEx.createException("sendType参数错误");
        }
        if ("1".equals(messageReq.getSendType())) {
            if (StringUtils.isEmpty(messageReq.getProjectId()) && StringUtils.isEmpty(messageReq.getProjectName())) {
                return ResponsePageEx.createException("缺少项目参数");
            }
        }

        return this.messageDao.getMessage(messageReq);
    }

    @PostMapping("/updateMessageRead")
    public ResponseEx updateMessageRead(String msgId) {
        if (msgId == null || msgId.equals("")) {
            return ResponseEx.createException("msgId为空");
        }
        boolean result = this.messageDao.updateMessageRead(msgId);
        return result ? ResponseEx.createSuccess() : ResponseEx.createException("更新失败");
    }


}
