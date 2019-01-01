package com.ebei.message.service;


import com.ebei.message.bean.WxTempleteBean;
import com.ebei.message.vo.ResponseEx;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 12:31
 * @Description:
 */
public interface WxTempleteService {
    /**
     * 发送模板消息
     * @param bean
     * @return
     */
    ResponseEx sendTemplete(WxTempleteBean bean);

    /**
     * 保存模板
     * @param bean
     */
    void insert(WxTempleteBean bean);

    /**
     * 获取模板
     * @param id
     * @return
     */
    WxTempleteBean get(String id);

}
