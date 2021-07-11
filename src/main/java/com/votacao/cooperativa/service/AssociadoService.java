package com.votacao.cooperativa.service;

import com.votacao.cooperativa.dto.AssociadoDTO;
import com.votacao.cooperativa.feign.service.CpfService;
import com.votacao.cooperativa.repository.AssociadoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AssociadoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssociadoService.class);
    private final AssociadoRepository repository;
    private final CpfService validaCPFClient;

    /**
     * Realiza a validacao se o associado ja votou na pauta informada pelo seu ID.
     * <p>
     * Se nao existir um registro na base, entao e considerado como valido para seu voto ser computado
     *
     * @return - boolean
     */
    @Transactional(readOnly = true)
    public boolean isValidaParticipacaoAssociadoVotacao(String cpfAssociado, Integer idPauta) {
        LOGGER.debug("Validando participacao do associado na votacao da pauta  id = {}", idPauta);
        if (repository.existsByCpfAssociadoAndIdPauta(cpfAssociado, idPauta)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    @Transactional
    public void salvarAssociado(AssociadoDTO dto) {
        LOGGER.debug("Registrando participacao do associado na votacao idAssociado = {}, idPauta = {}", dto.getCpfAssociado(), dto.getIdPauta());
        repository.save(AssociadoDTO.toEntity(dto));
    }

    /**
     * faz a chamada para metodo que realiza a consulta em API externa
     * para validar por meio de um cpf valido, se o associado esta habilitado para votar
     *
     * @param cpf - @{@link AssociadoDTO} CPF valido
     * @return - boolean
     */
    public boolean isAssociadoPodeVotar(String cpf) {
        return validaCPFClient.validaCpfAssociado(cpf);
    }
}