package org.rsta80.currencyconversionservice.service;

import org.rsta80.currencyconversionservice.dtos.CurrencyConverterDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    final RestTemplate restTemplate;


    public CurrencyConverterServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CurrencyConverterDto getCurrencyExchange(String from, String to, BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        CurrencyConverterDto response = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConverterDto.class, uriVariables).getBody();

        assert response != null;
        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));

        return response;

    }
}
