package org.rsta80.currencyconversionservice.service;

import org.rsta80.currencyconversionservice.dtos.CurrencyConverterDto;

import java.math.BigDecimal;


public interface CurrencyConverterService {

    CurrencyConverterDto getCurrencyExchange(String from, String to, BigDecimal quantity);

}
