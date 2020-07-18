package com.example.rabobank.config;

import com.example.rabobank.domain.RecordsList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

@Configuration
@PropertySource("classpath:message.properties")
public class RaboBankConfig {


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
