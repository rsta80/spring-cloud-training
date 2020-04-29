package org.rsta80.currencyconversionservice.proxy;

import org.rsta80.currencyconversionservice.dtos.CurrencyConverterDto;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Proxy interface that declares Feign and ribon client
 * Feign is a bette raltertive to comunicate with a service,  when it is declare with ribbon, it doesnt need to define
 * url. For example> @FeignClient(name = "currency-exchange-service", url = "localhost:8000")
 * Ribbon acts like load balancer in the client side
 * Feign will call directly to Zuul API gateway
 */
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netlfix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConverterDto retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
