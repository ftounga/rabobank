package com.example.rabobank.domain.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordRequest {

    @NotNull
    private String accountNumber;

    private String description;

    @NotNull
    @XmlAttribute
    private BigInteger reference;

    @NotNull
    private BigDecimal startBalance;

    @NotNull
    private BigDecimal endBalance;

    @NotNull
    private BigDecimal mutation;
}

