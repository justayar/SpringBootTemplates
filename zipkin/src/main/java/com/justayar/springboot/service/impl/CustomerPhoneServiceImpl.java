package com.justayar.springboot.service.impl;

import com.justayar.springboot.dto.CustomerPhoneDTO;
import com.justayar.springboot.service.CustomerPhoneService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerPhoneServiceImpl implements CustomerPhoneService {

    @Override
    public List<CustomerPhoneDTO> getCustomerPhones(String id) {
        return mockCustomerPhones();
    }

    public List<CustomerPhoneDTO> mockCustomerPhones(){
        List<CustomerPhoneDTO> customerPhoneDTOList = new ArrayList<>();
        for(int i=0;i<100000;i++){
            CustomerPhoneDTO phoneDto = new CustomerPhoneDTO();
            byte[] array = new byte[7];
            new Random().nextBytes(array);
            phoneDto.setPhoneType(new String(array, StandardCharsets.UTF_8));
            phoneDto.setPhoneNumber(new String(array, StandardCharsets.UTF_8));
            customerPhoneDTOList.add(phoneDto);
        }
        return customerPhoneDTOList;
    }
}
