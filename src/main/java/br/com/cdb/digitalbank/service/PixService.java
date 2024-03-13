package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Pix;
import br.com.cdb.digitalbank.repository.PixRepository;
import br.com.cdb.digitalbank.service.exceptions.DuplicateDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PixService {

    @Autowired
    private PixRepository pixRepository;

    public Pix save(Pix pix) {
        pix.setPixKey(generatePixKey(pix));

        if (pixRepository.findByPixKey(pix.getPixKey()) != null) {
            throw new DuplicateDataException("Essa chave pix já foi registrada.");
        }

        return pixRepository.save(pix);
    }

    private String generatePixKey(Pix pix) {
        return switch (pix.getType()) {
            case EMAIL -> pix.getAccount().getCustomer().getEmail();
            case PHONE -> pix.getAccount().getCustomer().getPhone();
            case CPF -> pix.getAccount().getCustomer().getCpf();
            case RANDOM -> generateRandomKey();
        };
    }

    private String generateRandomKey() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
}
