package com.justayar.springboot;

import com.justayar.springboot.service.CustomerAddressService;
import com.justayar.springboot.service.CustomerPhoneService;
import com.justayar.springboot.service.CustomerService;
import com.justayar.springboot.service.impl.CustomerAddressServiceImpl;
import com.justayar.springboot.service.impl.CustomerPhoneServiceImpl;
import com.justayar.springboot.service.impl.CustomerServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ZipkinApplication {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CustomerService customerService(){
        return new CustomerServiceImpl();
    }

    @Bean
    public CustomerAddressService customerAddressService(){
        return new CustomerAddressServiceImpl();
    }

    @Bean
    public CustomerPhoneService customerPhoneService(){
        return new CustomerPhoneServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }

}
