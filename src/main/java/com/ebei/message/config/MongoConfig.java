package com.ebei.message.config;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @Description : Mongodb配置类
 * @time 创建时间 : 2018年6月11日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    private  Logger logger = LoggerFactory
            .getLogger(MongoConfig.class);

    @Value("${spring.mongodb.host}")
    private String mongodbHost;

    @Value("${spring.mongodb.port}")
    private int mongodbPort;

    @Value("${spring.mongodb.database}")
    private String mongodbName;

    @Value("${spring.mongodb.user}")
    private String mongodbUser;

    @Value("${spring.mongodb.password}")
    private String mongodbpwd;

    @Value("${spring.mongodb.authentification}")
    private boolean authentification;

    @Bean
    @Override
    public MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate = 
            new MongoTemplate(mongo(), mongodbName);
        logger.info("*******"+mongoTemplate.getDb().getName() +"基础库");
        return mongoTemplate;

    }
    @Override
    protected String getDatabaseName() {
        return mongodbName;
    }

    @Override
    public Mongo mongo() throws Exception {

         MongoClient mongoClient;
         MongoCredential credential = MongoCredential.createMongoCRCredential(mongodbUser,mongodbName,mongodbpwd.toCharArray());
         MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(3000)
                .threadsAllowedToBlockForConnectionMultiplier(10)
                .readPreference(ReadPreference.nearest())
                .build();
        List<ServerAddress> addresses = new ArrayList<ServerAddress>();
        String[] str = this.mongodbHost.split(",");
        for (String strHost : str) {
          ServerAddress address = new ServerAddress(strHost, mongodbPort);
          addresses.add(address);
        }
        if(authentification){
            mongoClient = new MongoClient(addresses,Arrays.asList(credential), options);
        }else{
             mongoClient = new MongoClient(addresses, options); 
        }

         return mongoClient;
    }
}
