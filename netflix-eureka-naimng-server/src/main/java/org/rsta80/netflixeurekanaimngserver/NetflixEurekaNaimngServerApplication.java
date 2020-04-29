package org.rsta80.netflixeurekanaimngserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaNaimngServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixEurekaNaimngServerApplication.class, args);
    }

}
