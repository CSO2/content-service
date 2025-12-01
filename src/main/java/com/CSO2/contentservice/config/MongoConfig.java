package com.CSO2.contentservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.CSO2.contentservice.repository")
public class MongoConfig {
}
