package com.justayar.springboot.service.impl;

import com.justayar.springboot.dto.CustomerDTO;
import com.justayar.springboot.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDTO getCustomerInfo() {
        return mockCustomerObject();
    }

    public CustomerDTO mockCustomerObject(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John Doe");
        customerDTO.setEmail("john.doe@gmail.com");
        customerDTO.setGender("male");

        return customerDTO;
    }
}
