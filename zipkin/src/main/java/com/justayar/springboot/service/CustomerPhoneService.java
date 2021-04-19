package com.justayar.springboot.service;

import com.justayar.springboot.dto.CustomerPhoneDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerPhoneService {
    List<CustomerPhoneDTO> getCustomerPhones(String id);
}
