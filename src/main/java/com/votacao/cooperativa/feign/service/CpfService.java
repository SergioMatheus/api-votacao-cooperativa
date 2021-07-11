package com.votacao.cooperativa.feign.service;

import com.votacao.cooperativa.feign.controller.CpfConsultaService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CpfService {

    private final CpfConsultaService consultaService;

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
        try {
            consultaService.validaCpf(cpf);
            return Boolean.TRUE;
        } catch (FeignException feignException) {
            return Boolean.FALSE;
        }
    }
}
