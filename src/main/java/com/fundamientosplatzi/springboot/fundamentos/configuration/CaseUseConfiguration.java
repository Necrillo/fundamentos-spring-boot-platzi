package com.fundamientosplatzi.springboot.fundamentos.configuration;

import com.fundamientosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamientosplatzi.springboot.fundamentos.caseuse.GetUserImplement;
import com.fundamientosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
