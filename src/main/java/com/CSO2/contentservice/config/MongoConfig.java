package com.CSO2.contentservice.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.CSO2.contentservice.repository")
public class MongoConfig {

    @Value("${spring.data.mongodb.uri:mongodb://localhost:27017/CSO2_content_service}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database:CSO2_content_service}")
    private String database;

    @Bean
    public MongoClient mongoClient() {
        System.out.println("MongoDB Connection URI: " + mongoUri.replaceAll(":[^:@]+@", ":****@"));
        
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .build();
        
        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), database);
    }
}
