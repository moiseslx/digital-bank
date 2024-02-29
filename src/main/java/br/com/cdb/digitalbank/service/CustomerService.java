package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Customer;
import br.com.cdb.digitalbank.repository.CustomerRepository;
import br.com.cdb.digitalbank.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado com id: " + id));
    }
}
