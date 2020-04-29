package org.rsta80.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Document(collection = "ExchangeValues")
public class ExchangeValue {

    @Id
    private Object id;
    private String from;
    private String to;

    @Field(name = "conversion_multiple")
    private BigDecimal conversionMultiple;
    private Integer port;

}
