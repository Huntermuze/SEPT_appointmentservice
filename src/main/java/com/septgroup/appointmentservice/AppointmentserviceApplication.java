package com.septgroup.appointmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// CTRL + SHIFT + G NEW BIND FOR REFORMAT CODE.
@SpringBootApplication
@EnableEurekaClient
public class AppointmentserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentserviceApplication.class, args);
    }

}
