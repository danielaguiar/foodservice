package com.gestaosimples.servico.services;

import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.gestaosimples.servico.domain.PagamentoComBoleto;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instante) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instante);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pgto.setDataVencimento(calendar.getTime());
    }

}
