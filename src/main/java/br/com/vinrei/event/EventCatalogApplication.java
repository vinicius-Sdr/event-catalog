package br.com.vinrei.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventCatalogApplication.class, args);
    }

}
