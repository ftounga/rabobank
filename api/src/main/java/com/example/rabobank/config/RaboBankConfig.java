package com.example.rabobank.config;

import com.example.rabobank.domain.RecordsList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@PropertySource("classpath:message.properties")
public class RaboBankConfig {


    @Bean(name = "threadPoolTaskRecordExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Bean
    public Unmarshaller getRecordsUnMarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(RecordsList.class);
        Unmarshaller recordsUnmarshaller = jaxbContext.createUnmarshaller();
        return recordsUnmarshaller;
    }

    @Bean
    public SchemaFactory getSchemaFactory(){
        SchemaFactory factory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return factory;
    }
}
