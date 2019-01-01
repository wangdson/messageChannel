package com.ebei.message.service;


import com.ebei.message.bean.SMSAliReq;
import com.ebei.message.bean.SMSEmayReq;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 16:43
 * @Description:
 */
public abstract class SMSAliService {

    public abstract ResponseEx sendMessage(SMSAliReq bean);

    public abstract SMSAliReq get(String id);
}
