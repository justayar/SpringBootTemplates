package com.justayar.springboot.service.impl;

import com.justayar.springboot.dto.CustomerAddressDTO;
import com.justayar.springboot.service.CustomerAddressService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerAddressServiceImpl implements CustomerAddressService {

    @Override
    public List<CustomerAddressDTO> getCustomerAddresses(String id) {
        return mockCustomerAddresses();
    }

    public List<CustomerAddressDTO> mockCustomerAddresses(){
        List<CustomerAddressDTO> customerAddressDTOList = new ArrayList<>();
        for(int i=0;i<3;i++){
            CustomerAddressDTO addressDTO = new CustomerAddressDTO();
            byte[] array = new byte[7];
            new Random().nextBytes(array);
            addressDTO.setAddressName(new String(array, StandardCharsets.UTF_8));
            addressDTO.setDescription(new String(array, StandardCharsets.UTF_8));
            customerAddressDTOList.add(addressDTO);
        }
        return customerAddressDTOList;
    }
}
