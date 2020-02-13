package io.bnn.mongodbspringboot0211.controller;

import io.bnn.mongodbspringboot0211.dao.CustomerRepository;
import io.bnn.mongodbspringboot0211.po.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/to")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/findAll")
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    @GetMapping("/getById")
    public Customer getById(@RequestParam String customerId){
        Customer customer = customerRepository.findById(customerId).get();
        return customer;
    }
    @GetMapping("/getOneByFirstname")
    public Customer getOneByFirstname(@RequestParam String firstname){
        Customer customer = customerRepository.findByFirstName(firstname);
        return customer;
    }

    @GetMapping("/getListByLastname")
    public List<Customer> getListByLastname(@RequestParam String lastname){
        List<Customer> customers = customerRepository.findByLastName(lastname);
        return customers;
    }

    @GetMapping("/getListFirstLast")
    public List<Customer> getListFirstLast(String firstname, String lastname){
        List<Customer> customers = customerRepository.findByFirstNameAndLastName(firstname, lastname);
        return customers;
    }

    //模糊
    @GetMapping("/getContain")
    public List<Customer> getContain(@RequestParam String keyword){
        List<Customer> customers = customerRepository.findByFirstNameContaining(keyword);
        return customers;
    }

    @PostMapping("/create")
    public String create(){
        Customer customer01 = new Customer("AAA", "APP");
        Customer customer02 = new Customer("BBB", "ABB");
        Customer save01 = customerRepository.save(customer01);
        Customer save02 = customerRepository.save(customer02);
        return  save01.id + "---" +  save02.id;
    }

    @PostMapping("/update")
    public void update(){
        Customer customer = new Customer();
        customer.id = "asdafaf";
        customer.firstName = "bbbbbb";
        customer.lastName = "bbbbb";
        customerRepository.save(customer);
    }

    @PostMapping("/deleteAll")
    public void deleteAll(){
        customerRepository.deleteAll();
    }

    @PostMapping("/deleteById")
    public void deleteById(){
        customerRepository.deleteById("asdafaf");
    }
}
