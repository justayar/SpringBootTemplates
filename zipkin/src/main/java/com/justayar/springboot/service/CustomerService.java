package com.justayar.springboot.service;

import com.justayar.springboot.dto.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerDTO getCustomerInfo();
}
