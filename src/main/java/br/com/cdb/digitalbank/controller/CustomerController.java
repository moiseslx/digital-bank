package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.CustomerDTO;
import br.com.cdb.digitalbank.model.Customer;
import br.com.cdb.digitalbank.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<Customer> save(@Valid @RequestBody CustomerDTO customer) {
        return ResponseEntity.ok(customerService.save(customer.toCustomer()));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }
}
