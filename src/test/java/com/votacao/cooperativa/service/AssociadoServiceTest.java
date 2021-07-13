package com.votacao.cooperativa.service;

import com.votacao.cooperativa.dto.AssociadoDTO;
import com.votacao.cooperativa.dto.PautaDTO;
import com.votacao.cooperativa.entity.Associado;
import com.votacao.cooperativa.exception.NotFoundException;
import com.votacao.cooperativa.feign.service.CpfService;
import com.votacao.cooperativa.repository.AssociadoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssociadoServiceTest {

    @Mock
    private AssociadoRepository repository;
    @Mock
    private CpfService validaCPFClient;

    @InjectMocks
    private AssociadoService associadoService;

    @Test
    public void isValidaParticipacaoAssociadoVotacaoTest() {

        when(repository.existsByCpfAssociadoAndIdPauta(Mockito.any(),Mockito.any())).thenReturn(false);
        associadoService.isValidaParticipacaoAssociadoVotacao("teste",1);

        verify(repository).existsByCpfAssociadoAndIdPauta(Mockito.any(),Mockito.any());
    }

    @Test
    public void salvarAssociadoTest() {

        when(repository.save(Mockito.any())).thenReturn(Associado.builder().cpfAssociado("121").id(1).idPauta(1).build());
        associadoService.salvarAssociado(AssociadoDTO.builder().build());

        verify(repository).save(Mockito.any());
    }

    @Test
    public void isAssociadoPodeVotarTest() {

        when(validaCPFClient.validaCpfAssociado(Mockito.any())).thenReturn(true);
        associadoService.isAssociadoPodeVotar("1");

        verify(validaCPFClient).validaCpfAssociado(Mockito.any());
    }


}
