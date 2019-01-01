package com.ebei.message.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ebei.message.bean.WxTempleteBean;
import com.ebei.message.dao.WxTempleteDao;

/**
 * @Author: Huangweicai
 * @date 2018-08-31 15:00
 * @Description:
 */
@Repository("wxTempleteDao")
public class WxTempleteDaoImpl implements WxTempleteDao {

	@Autowired
	protected MongoTemplate mongoTemplate;


	@Override
	public void insert(WxTempleteBean t) {
		mongoTemplate.insert(t);
	}

	@Override
	public WxTempleteBean get(String id) {
		Criteria criteria = Criteria.where("_id").is(new ObjectId(id));
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, WxTempleteBean.class);
	}

}
