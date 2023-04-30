package com.example.restapiinflearn;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiInflearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiInflearnApplication.class, args);
    }
    /*@Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }*/
}
