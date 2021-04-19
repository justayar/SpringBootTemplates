package com.justayar.springboot.service;

import com.justayar.springboot.dto.CustomerAddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerAddressService {
    List<CustomerAddressDTO> getCustomerAddresses(String id);
}
