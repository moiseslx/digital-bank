package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, Long> {

    Pix findByPixKey(String key);
}
