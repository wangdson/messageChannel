package com.ebei.message.dao.impl;

import com.ebei.message.bean.SMSAliBean;
import com.ebei.message.bean.SMSEmayBean;
import com.ebei.message.dao.SMSAliDao;
import com.ebei.message.dao.SMSEmayDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 15:00
 * @Description:
 */
@Repository("sMSAliDao")
public class SMSAliDaoImpl implements SMSAliDao {

	@Autowired
	protected MongoTemplate mongoTemplate;


	@Override
	public void insert(SMSAliBean t) {
		mongoTemplate.insert(t);
	}

	@Override
	public SMSAliBean get(String id) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, SMSAliBean.class);
	}

}
