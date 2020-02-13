package io.bnn.mongodbspringboot0211.dao;

import io.bnn.mongodbspringboot0211.po.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstname);

    List<Customer> findByLastName(String lastname);

    List<Customer> findByFirstNameAndLastName(String firstname, String lastname);

    List<Customer> findByFirstNameContaining(String keyword);
}
