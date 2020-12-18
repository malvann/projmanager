package com.my.projmanager.controller;

import com.my.projmanager.controller.request.CustomerCreateRequest;
import com.my.projmanager.controller.request.CustomerUpdateRequest;
import com.my.projmanager.model.impl.Customer;
import com.my.projmanager.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/app/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerRepository repository;
    private final SimpleDateFormat dataFormat;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(repository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerCreateRequest request){
        Customer customer = fillCustomerByRequest(new Customer(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(customer));
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerUpdateRequest request){
        Customer customer = fillCustomerByRequest(repository.findById(request.getId()).get(), request);
        return ResponseEntity.ok(repository.save(customer));
    }

    private static Customer fillCustomerByRequest(Customer customer, CustomerCreateRequest request){
        customer.setName(request.getName());
        customer.setLastname(request.getLastname());
        customer.setAddress(request.getAddress());
        customer.setMail(request.getMail());
        customer.setPhone(request.getPhone());
        customer.setSkype(request.getSkype());
        customer.setTelegramm(request.getTelegramm());
        return customer;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long id) {
        Customer customer = repository.findById(id).get();
        repository.delete(customer);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/search/{after}:{before}")
    public ResponseEntity<List<Customer>> findByCreatedBetween(
            @PathVariable String after,
            @PathVariable String before) throws ParseException {
        return ResponseEntity.ok().body(repository.findByCreatedBetween(new Timestamp(dataFormat.parse(after).getTime()), new Timestamp(dataFormat.parse(before).getTime())));
    }

    @GetMapping("/search/{name},{lastname}")
    public ResponseEntity<List<Customer>> findByNameAndLastname(
            @PathVariable String name,
            @PathVariable String lastname){
        return ResponseEntity.ok().body(repository.findByNameAndLastname(name, lastname));
    }

}