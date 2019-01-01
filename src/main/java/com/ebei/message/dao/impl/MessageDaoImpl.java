package com.ebei.message.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ebei.message.bean.MessageBean;
import com.ebei.message.bean.MessageReq;
import com.ebei.message.utlis.DateUtils;
import com.ebei.message.vo.ResponsePageEx;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ebei.message.dao.MessageDao;
import com.mongodb.WriteResult;
import org.springframework.stereotype.Repository;

/**
 * 
 * @Description : Mongodb通用Dao实现层
 * @time 创建时间 : 2018年6月11日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
@Repository("messageDao")
public class MessageDaoImpl<T> implements MessageDao<T> {

	@Autowired
	protected MongoTemplate mongoTemplate;

	/**
	 * 创建一个 Class 的对象来获取泛型的 Class
	 */
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public Class<T> getClazz() {
		if (clazz == null) {
			clazz = ((Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
					.getActualTypeArguments()[0]));
		}
		return clazz;
	}

	@Override
	public void insert(T t) {
		mongoTemplate.insert(t);
	}

	@Override
	public void insertBatch(List<T> list) {
		mongoTemplate.insertAll(list);
	}

	@Override
	public boolean remove(String id) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
		Query query = new Query(criteria);
		WriteResult writeResult = mongoTemplate.remove(query, getClazz());
		return writeResult.getN() > 0 ? true : false;
	}

	@Override
	public T get(String id) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, getClazz());
	}


//	 "dateBegin":"发送开始时间",
//			 "dateEnd":"发送结束时间",
//			 "fortype":"接收人类型（PC/APP）",
//			 "msgType":"消息类型（审核/订单状态变更/认证/服务）", 多个以逗号分隔
//			 "toUserId":"接收人Id（PC userId / APP memberId）",
//			 "msgText":"模糊对比title/content"

//	sendType: 1:all 所有 toUserId不用判断 projectId或projectName必有一个不能为空    2: 指定人


	//1000
	public ResponsePageEx getMessage(MessageReq req) {

		Query query = this.getMessageListQuery(req);

		query.skip((req.getCurrent() - 1) * req.getSize()).limit(req.getSize());

		List returnList = mongoTemplate.find(query, MessageBean.class);
		return ResponsePageEx.createSuccess(returnList,this.selectCount(this.getMessageListQuery(req)).intValue(),req.getSize(),req.getCurrent());
	}

	private Query getMessageListQuery(MessageReq req) {
		Date dateBeginD = null;
		Date dateEndD = null;
		try {
			if (!StringUtils.isEmpty(req.getBeginDate())) {
				dateBeginD = DateUtils.parseToTime(req.getBeginDate());
			}
			if (!StringUtils.isEmpty(req.getEndDate())) {
				dateEndD = DateUtils.parseToTime(req.getEndDate());
			}

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		Criteria criteria = new Criteria();
		List<Criteria> criteria1List = new ArrayList<>();

		if (!StringUtils.isEmpty(req.getFortype())) {
			Criteria fortypeCriteria = Criteria.where("fortype").is(req.getFortype());
			criteria1List.add(fortypeCriteria);
		}
		if (!StringUtils.isEmpty(req.getMsgType())) {
			String[] msgTypeArr = req.getMsgType().split(",");
			Criteria msgTypeCriteria = Criteria.where("msgType").in(msgTypeArr);
			criteria1List.add(msgTypeCriteria);
		}


//		sendType: 1:all 所有 toUserId不用判断 projectId或projectName必有一个不能为空    2: 指定人


		if (!StringUtils.isEmpty(req.getSendType()) && "2".equals(req.getSendType()))
		{////发送给指定人
			if (StringUtils.isEmpty(req.getToUserId())) {
				throw new RuntimeException("toUserId为空");
			}
			Criteria toUserIdCriteria = Criteria.where("toUserId").is(req.getToUserId());
			criteria1List.add(toUserIdCriteria);
		} else if (!StringUtils.isEmpty(req.getSendType()) &&"1".equals(req.getSendType()))
		{//发送给所有人

			String projectId = req.getProjectId();
			String projectName = req.getProjectName();

			if (StringUtils.isEmpty(projectId) && StringUtils.isEmpty(projectName)) {
				throw new RuntimeException("projectId和projectName为空");
			}
			if (!StringUtils.isEmpty(projectId) && !StringUtils.isEmpty(projectName)) {
				Criteria orCri = new Criteria();
				Criteria contentCriteria = Criteria.where("projectId").is(projectId);
				Criteria titleCriteria = Criteria.where("projectName").regex(projectName);
				orCri.orOperator(contentCriteria, titleCriteria);
				criteria1List.add(orCri);
			} else if (!StringUtils.isEmpty(projectId)) {
				Criteria toUserIdCriteria = Criteria.where("projectId").is(projectId);
				criteria1List.add(toUserIdCriteria);
			} else if (!StringUtils.isEmpty(projectName)) {
				Criteria toUserIdCriteria = Criteria.where("projectName").is(projectName);
				criteria1List.add(toUserIdCriteria);
			}

		} else {
			throw new RuntimeException("不存在的查询类型");
		}


		String msgTxt = ".*" + req.getMsgText() + ".*";
		if (!StringUtils.isEmpty(req.getMsgText())) {
			Criteria orCri = new Criteria();
			Criteria contentCriteria = Criteria.where("content").regex(msgTxt);
			Criteria titleCriteria = Criteria.where("title").regex(msgTxt);
			orCri.orOperator(contentCriteria, titleCriteria);
			criteria1List.add(orCri);

		}

		if (dateBeginD != null) {

		}
		if (dateBeginD != null && dateEndD != null) {

			Criteria dateCriteria = Criteria.where("messageDate").gte(dateBeginD).lte(dateEndD);
			criteria1List.add(dateCriteria);
		} else if (dateBeginD != null) {
			Criteria dateCriteria = Criteria.where("messageDate").gte(dateBeginD);
			criteria1List.add(dateCriteria);
		} else if (dateEndD != null) {
			Criteria dateCriteria = Criteria.where("messageDate").lte(dateEndD);
			criteria1List.add(dateCriteria);
		}


		Criteria[] criArr = new Criteria[criteria1List.size()];
		criteria.andOperator(criteria1List.toArray(criArr));

		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.DESC, "messageDate"));
		return query;
	}

	@Override
	public boolean updateMessageRead(String msgId) {
		if (msgId == null || msgId.equals("")) {
			return false;
		}
		Update update = new Update();
		update.set("readDate", new Date());
		update.set("isRead", 1);
		WriteResult writeResult = mongoTemplate.updateFirst(new Query(Criteria.where("id").is(msgId)), update,MessageBean.class);

		return writeResult.getN() > 0 ? true : false;
	}

	@Override
	public List<T> list(Integer current, Integer size) {
		Query query = new Query();
		query.skip((current - 1) * size).limit(size);
		return mongoTemplate.find(query, getClazz());
	}

	@Override
	public boolean update(Update update, String id) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
		Query query = new Query(criteria);
		WriteResult writeResult = mongoTemplate.updateFirst(query, update, getClazz());
		return writeResult.getN() > 0 ? true : false;
	}

	@Override
	public Long selectCount() {
		Query query = new Query();
		return mongoTemplate.count(query, MessageBean.class);
	}


	public Long selectCount(Query query) {
		return mongoTemplate.count(query, MessageBean.class);
	}

}
