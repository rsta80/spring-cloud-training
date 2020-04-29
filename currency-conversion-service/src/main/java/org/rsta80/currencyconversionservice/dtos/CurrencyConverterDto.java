package org.rsta80.currencyconversionservice.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyConverterDto {

    private Integer id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private Integer port;

}
