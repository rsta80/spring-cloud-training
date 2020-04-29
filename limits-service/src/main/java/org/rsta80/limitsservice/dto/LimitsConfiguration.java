package org.rsta80.limitsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LimitsConfiguration {

    private int maximun;
    private int minimun;

}
