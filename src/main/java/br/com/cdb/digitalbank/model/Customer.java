package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.CustomerType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private CustomerType type;

    public Customer() {
    }

    public Customer(String name, String email, String phone, String cpf, LocalDate birthDate, Address address, CustomerType type) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(phone, customer.phone) && Objects.equals(cpf, customer.cpf) && Objects.equals(birthDate, customer.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, cpf, birthDate);
    }
}
