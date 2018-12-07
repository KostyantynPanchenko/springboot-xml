package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class XmlConfig {

    @Bean
    public Jackson2ObjectMapperFactoryBean xmlMapper() {
        Jackson2ObjectMapperFactoryBean bean = new Jackson2ObjectMapperFactoryBean();
        JacksonXmlModule module = new JacksonXmlModule();
        // to default to using "unwrapped" Lists:
        module.setDefaultUseWrapper(false);
        bean.setObjectMapper(new XmlMapper(module));
        return bean;
    }
}
