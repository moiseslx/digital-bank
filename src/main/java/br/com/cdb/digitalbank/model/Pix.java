package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.KeyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "tb_pix")
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private KeyType type;
    private String pixKey;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Pix() {
    }

    public Pix(KeyType type, String pixKey, Account account) {
        this.type = type;
        this.pixKey = pixKey;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public KeyType getType() {
        return type;
    }

    public void setType(KeyType type) {
        this.type = type;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pix pix = (Pix) o;
        return Objects.equals(id, pix.id) && type == pix.type && Objects.equals(pixKey, pix.pixKey) && Objects.equals(account, pix.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, pixKey, account);
    }
}
