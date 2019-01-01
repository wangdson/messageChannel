package com.ebei.message.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ebei.message.bean.SMSLinkWSBean;
import com.ebei.message.dao.SMSLinkWSDao;

/**
 * @Author: wufq
 * @date 2018-09-21 15:00
 * @Description:
 */
@Repository("sMSLinkWSDao")
public class SMSLinkWSDaoImpl implements SMSLinkWSDao {

	@Autowired
	protected MongoTemplate mongoTemplate;


	@Override
	public void insert(SMSLinkWSBean t) {
		mongoTemplate.insert(t);
	}

	@Override
	public SMSLinkWSBean get(String id) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, SMSLinkWSBean.class);
	}

}
