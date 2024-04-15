package br.com.cdb.digitalbank.service.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {

    //TODO: Criar rotinas para rendimento de contas
    // TODO: Lembrar que contas correntes tem uma taxa mensal de manutenção, a ser descontada a cada mês
    // TODO: Lembrar que as contas poupança deve acumular conforme a taxa de rendimento.

    // TODO: Criar rotinas para contas poupança

    @Scheduled(cron = "0 0 0 * * *")
    public void execute() {
        //TODO: Criar rotinas para contas correntes
    }


    /*
    * 2. **Conta Poupança:**
- **Taxa de Rendimento Anual:** 0,5% ao ano para clientes Comuns, 0,7% ao ano para
clientes Super, e 0,9% ao ano para clientes Premium.
- O rendimento é calculado mensalmente usando a fórmula do juro composto,
baseando-se no saldo presente na conta no último dia do mês.
    * */


}
