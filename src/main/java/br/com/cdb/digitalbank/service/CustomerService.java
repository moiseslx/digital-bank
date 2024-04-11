package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Customer;
import br.com.cdb.digitalbank.repository.CustomerRepository;
import br.com.cdb.digitalbank.service.exceptions.DuplicateDataException;
import br.com.cdb.digitalbank.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        if (customerRepository.findByCpf(customer.getCpf()) != null) {
            throw new DuplicateDataException("Cliente existente com o CPF: " + customer.getCpf());
        }

        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            throw new DuplicateDataException("Cliente existente com o email: " + customer.getEmail());
        }

        if (customer.getBirthDate() == null || customer.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("Cliente deve ter 18 anos ou mais");
        }

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
