package com.example.rabobank.domain;

import com.example.rabobank.domain.request.RecordRequest;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordsList {

    @XmlElement(name = "record")
    private List<RecordRequest> records;
}
