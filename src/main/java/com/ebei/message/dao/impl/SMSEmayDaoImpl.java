package com.ebei.message.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ebei.message.bean.SMSEmayBean;
import com.ebei.message.dao.SMSEmayDao;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 15:00
 * @Description:
 */
@Repository("sMSEmayDao")
public class SMSEmayDaoImpl implements SMSEmayDao {

	@Autowired
	protected MongoTemplate mongoTemplate;


	@Override
	public void insert(SMSEmayBean t) {
		mongoTemplate.insert(t);
	}

	@Override
	public SMSEmayBean get(String id) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, SMSEmayBean.class);
	}

}
