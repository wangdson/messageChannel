package com.ebei.message.dao;


import com.ebei.message.bean.SMSEmayBean;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 15:00
 * @Description:
 */
public interface SMSEmayDao {

    void insert(SMSEmayBean t);

    SMSEmayBean get(String id);

}
