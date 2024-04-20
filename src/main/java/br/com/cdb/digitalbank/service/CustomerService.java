package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Customer;
import br.com.cdb.digitalbank.repository.CustomerRepository;
import br.com.cdb.digitalbank.service.exceptions.DuplicateDataException;
import br.com.cdb.digitalbank.service.exceptions.EntityNotFoundException;
import br.com.cdb.digitalbank.service.exceptions.MultipleValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        List<Exception> exceptions = new ArrayList<>();

        if (customerRepository.findByCpf(customer.getCpf()) != null) {
            exceptions.add(new DuplicateDataException("Cliente existente com o CPF: " + customer.getCpf()));
        }

        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            exceptions.add(new DuplicateDataException("Cliente existente com o email: " + customer.getEmail()));
        }

        if (customerRepository.findByPhone(customer.getPhone()) != null) {
            exceptions.add(new DuplicateDataException("Cliente existente com o telefone: " + customer.getPhone()));
        }

        if (customer.getBirthDate() == null || customer.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            exceptions.add(new IllegalArgumentException("Cliente deve ter 18 anos ou mais"));
        }

        if (!exceptions.isEmpty()) {
            throw new MultipleValidationException(exceptions);
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
