package com.cy.spiderman;

import com.cy.spiderman.music.XiaMi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

/**
 * @author congyang.guo
 */
@SpringBootApplication
@EnableScheduling
public class SpidermanApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpidermanApplication.class, args);
    }
}
