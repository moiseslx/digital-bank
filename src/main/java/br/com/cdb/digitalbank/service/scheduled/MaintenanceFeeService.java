package br.com.cdb.digitalbank.service.scheduled;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.enums.AccountType;
import br.com.cdb.digitalbank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

@Service
public class MaintenanceFeeService {

    @Autowired
    private AccountService accountService;


    /**
     * A manutenção de contas deve correntes são executadas mensalmente
     * Taxa de Manutenção Mensal:
     * → R$ 12,00 para clientes Comuns
     * → R$ 8,00 para clientes Super
     * → R$ 5,00 para clientes Premium
     */
//    @Scheduled(cron = "* * * * * *")
//    public void execute() {
//        Stream<Account> accounts = accountService
//                .findByAccountsCompleteAnotherMonth()
//                .stream()
//                .filter(account -> account.getType().equals(AccountType.CHECKING));
//
//        accounts.forEach(account -> account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(12))));
//    }
}
