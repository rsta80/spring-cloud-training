package org.rsta80.limitsservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="limits-service")
public class ConfigLimitsProperties {

    private int maximum;
    private int minimum;

}
