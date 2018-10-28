package com.application.sa.controller;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.sa.Entity.*;
import com.application.sa.repository.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer")
    public Collection<Customer> customer() {
        return customerRepository.findAll().stream()
                .collect(Collectors.toList());
    }

}
