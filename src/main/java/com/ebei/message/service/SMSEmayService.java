package com.ebei.message.service;


import com.ebei.message.bean.SMSEmayReq;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 16:43
 * @Description:
 */
public abstract class SMSEmayService {

    public abstract ResponseEx sendMessage(SMSEmayReq bean);

    public abstract SMSEmayReq get(String id);
}
