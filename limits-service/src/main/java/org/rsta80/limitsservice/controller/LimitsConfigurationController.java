package org.rsta80.limitsservice.controller;

import org.rsta80.limitsservice.config.ConfigLimitsProperties;
import org.rsta80.limitsservice.dto.LimitsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    final ConfigLimitsProperties config;

    public LimitsConfigurationController(ConfigLimitsProperties configLimitsProperties) {
        this.config = configLimitsProperties;
    }

    @GetMapping("limits")
    public ResponseEntity<LimitsConfiguration> retrieveLimitsConfiguration(){

        return ResponseEntity.ok(new LimitsConfiguration(config.getMaximum(),config.getMinimum()));
    }

}
