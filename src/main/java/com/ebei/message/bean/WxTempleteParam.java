package com.ebei.message.bean;

import java.io.Serializable;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 12:30
 * @Description:
 */
public class WxTempleteParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String key;
    private Object val;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }
}
