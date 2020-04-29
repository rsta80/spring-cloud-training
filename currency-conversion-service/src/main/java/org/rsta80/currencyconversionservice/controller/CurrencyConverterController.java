package org.rsta80.currencyconversionservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.rsta80.currencyconversionservice.dtos.CurrencyConverterDto;
import org.rsta80.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.rsta80.currencyconversionservice.service.CurrencyConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/currency-converter")
public class CurrencyConverterController {

    final CurrencyConverterService service;
    final CurrencyExchangeServiceProxy proxy;

    public CurrencyConverterController(CurrencyConverterService service, CurrencyExchangeServiceProxy proxy) {
        this.service = service;
        this.proxy = proxy;
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<?> convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
        return ResponseEntity.ok(service.getCurrencyExchange(from,to,quantity));
    }

    @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<?> convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        CurrencyConverterDto response = proxy.retrieveExchangeValue(from, to);
        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
        log.info("{}",response);
        return ResponseEntity.ok(response);
    }

}
