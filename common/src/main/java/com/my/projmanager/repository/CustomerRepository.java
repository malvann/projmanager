package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Customer;

import java.sql.Timestamp;
import java.util.List;

public interface CustomerRepository extends CRUJpaRepository<Customer, Long> {

    List<Customer> findByCreatedBetween(Timestamp after, Timestamp before);

    List<Customer> findByNameAndLastname(String name, String lastName);

    boolean existsByMailAndPhone(String mail, String phone);

    boolean existsById(Long id);
}
