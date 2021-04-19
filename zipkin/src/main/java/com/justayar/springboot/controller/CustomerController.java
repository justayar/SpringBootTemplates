package com.justayar.springboot.controller;

import com.justayar.springboot.dto.CustomerAddressDTO;
import com.justayar.springboot.dto.CustomerDTO;
import com.justayar.springboot.dto.CustomerPhoneDTO;
import com.justayar.springboot.service.CustomerAddressService;
import com.justayar.springboot.service.CustomerPhoneService;
import com.justayar.springboot.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final RestTemplate restTemplate;
    private final CustomerService customerService;
    private final CustomerAddressService customerAddressService;
    private final CustomerPhoneService customerPhoneService;

    @Value("${server.port}")
    private String servicePort;

    @GetMapping(value="/{id}")
    public CustomerDTO getCustomerById(@PathVariable String id) {

        log.info("Customer api is called with id {}",id);

        CustomerDTO customerInfo = customerService.getCustomerInfo();

        customerInfo.setCustomerAddresses(restTemplate.exchange("http://localhost:" + servicePort + "/customer/address/" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerAddressDTO>>() {
                }).getBody());

        customerInfo.setCustomerPhones(restTemplate.exchange("http://localhost:" + servicePort + "/customer/phone/" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerPhoneDTO>>() {
                }).getBody());

        return customerInfo;
    }

    @GetMapping(value="/address/{id}")
    public List<CustomerAddressDTO> getCustomerAddressesById(@PathVariable String id) {

        log.info("Customer address api is called with id {}",id);

        return customerAddressService.getCustomerAddresses(id);
    }

    @GetMapping(value="/phone/{id}")
    public List<CustomerPhoneDTO> getCustomerPhonesById(@PathVariable String id) {
        log.info("Customer phone api is called with id {}",id);

        return customerPhoneService.getCustomerPhones(id);
    }
}
