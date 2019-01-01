package com.ebei.message.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Update;

import com.ebei.message.bean.MessageReq;
import com.ebei.message.vo.ResponsePageEx;

/**
 * 
 * @Description : Mongodb通用Dao层
 * @time 创建时间 : 2018年6月11日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
public interface MessageDao<T> {

	public void insert(T t);
	
	public void insertBatch(List<T> list);
	
	public boolean remove(String id);
	
	public Long selectCount();
	
	public boolean update(Update update,String id);
	
	public T get(String id);
	
	public List<T> list(Integer current, Integer size);

	public ResponsePageEx getMessage(MessageReq req);

	/**
	 * 更新已读标识
	 * @param msgId
	 * @return
	 */
	boolean updateMessageRead(String msgId);
}
