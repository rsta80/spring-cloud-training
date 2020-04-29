package org.rsta80.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.rsta80.demo.dto.ExchangeValue;
import org.rsta80.demo.exceptions.ObjectNotFoundException;
import org.rsta80.demo.repository.ExchangeValueRepository;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private final Environment environment;
    private final ExchangeValueRepository repository;

    public CurrencyExchangeController(Environment environment, ExchangeValueRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<?> retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        ExchangeValue exchangeValue = repository.findByFromAndTo(from,to)
                .orElseThrow(() -> new ObjectNotFoundException("Conversion type not found :("));
        exchangeValue.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));
        log.info("{}",exchangeValue);
        return ResponseEntity.ok(exchangeValue);

    }

    @GetMapping
    public List<ExchangeValue> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ExchangeValue findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object with " + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<?> saveExchangeValue(@Valid @RequestBody ExchangeValue exchangeValue){

        ExchangeValue exchangeValueSaved = repository.save(exchangeValue);
        exchangeValue.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(exchangeValueSaved.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

}
