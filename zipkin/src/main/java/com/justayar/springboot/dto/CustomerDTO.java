package com.justayar.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private String name;
    private String email;
    private String gender;
    private List<CustomerAddressDTO> customerAddresses;
    private List<CustomerPhoneDTO> customerPhones;
}
