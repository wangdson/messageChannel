package com.ebei.message.dao;


import com.ebei.message.bean.SMSLinkWSBean;

/**
 * @Author: wufq
 * @date 2018-09-21 15:00
 * @Description:
 */
public interface SMSLinkWSDao {

    void insert(SMSLinkWSBean t);

    SMSLinkWSBean get(String id);

}
