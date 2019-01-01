package com.ebei.message.service;


import com.ebei.message.bean.SMSLinkWSReq;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: wufq
 * @date 2018-09-21 16:43
 * @Description:
 */
public abstract class SMSLinkWSService {

    public abstract ResponseEx sendMessage(SMSLinkWSReq bean);

}
