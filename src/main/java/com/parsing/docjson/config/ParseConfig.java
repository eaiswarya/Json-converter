package com.parsing.docjson.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ParseConfig {
    @Bean
    public NewTopic javaTopic(){
        return TopicBuilder.name("doc-json").build();
    }
}
