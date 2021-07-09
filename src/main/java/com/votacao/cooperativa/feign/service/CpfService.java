package com.votacao.cooperativa.feign.service;

import com.votacao.cooperativa.feign.controller.CpfConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class CpfService {

    private CpfConsultaService consultaService;

    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    /**
     * Consulta status da validação do CPF do associado.
     * <p>
     * ABLE_TO_VOTE - Valido para votar
     * <p>
     * UNABLE_TO_VOTE - Invalido para votar
     *
     * @return - boolean
     */
    public boolean validaCpfAssociado(String cpf) {
        String status = consultaService.validaCpf(cpf);

        if (ABLE_TO_VOTE.equals(status)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
