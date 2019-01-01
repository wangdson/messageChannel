package com.ebei.message.dao;


import com.ebei.message.bean.WxTempleteBean;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 15:00
 * @Description:
 */
public interface WxTempleteDao {

    void insert(WxTempleteBean t);

    WxTempleteBean get(String id);

}
