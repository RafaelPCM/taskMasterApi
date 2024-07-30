package com.taskMasterApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = "com.taskMasterApi.domain.model")
@ComponentScan(basePackages= {"com.taskMasterApi.*", "com.taskMasterApi.security"})
@EnableJpaRepositories(basePackages = {"com.taskMasterApi.repository"})
@EnableTransactionManagement
public class TaskMasterApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskMasterApiApplication.class, args);
    }
}

