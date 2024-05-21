package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCpf(String cpf);
    Customer findByEmail(String email);
    Customer findByPhone(String phone);
}
