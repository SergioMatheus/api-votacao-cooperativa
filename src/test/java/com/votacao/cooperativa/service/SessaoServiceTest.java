package com.votacao.cooperativa.service;

import com.votacao.cooperativa.dto.SessaoVotacaoAbrirDTO;
import com.votacao.cooperativa.dto.SessaoVotacaoEncerrarDTO;
import com.votacao.cooperativa.entity.SessaoVotacao;
import com.votacao.cooperativa.exception.NotFoundException;
import com.votacao.cooperativa.repository.PautaRepository;
import com.votacao.cooperativa.repository.SessaoVotacaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessaoServiceTest {

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private SessaoVotacaoService sessaoVotacaoService;

    @Test
    public void abrirSessaoVotacaoTeste() {
        SessaoVotacaoAbrirDTO sessaoVotacaoDTO = getSessaoVotacaoDTO();

        when(pautaService.isPautaValida(Mockito.any())).thenReturn(true);
        when(sessaoVotacaoRepository.save(Mockito.any())).thenReturn(SessaoVotacao.builder().build());

        sessaoVotacaoService.abrirSessaoVotacao(sessaoVotacaoDTO);

        verify(pautaService).isPautaValida(Mockito.any());
        verify(sessaoVotacaoRepository).save(Mockito.any());
    }

    @Test
    public void EncerrarSessaoVotacaoTeste() {
        SessaoVotacaoEncerrarDTO sessaoVotacaoDTO = getSessaoVotacaoDTOEncerrar();
        SessaoVotacao sessaoVotacao = SessaoVotacao.builder()
                .id(1)
                .dataHoraInicio(LocalDateTime.now())
                .ativa(true).build();


        when(sessaoVotacaoRepository.save(Mockito.any())).thenReturn(sessaoVotacao);
        when(sessaoVotacaoRepository.findById(Mockito.any())).thenReturn(
                Optional.of(sessaoVotacao));

        sessaoVotacaoService.encerrarSessaoVotacao(sessaoVotacaoDTO);

        verify(sessaoVotacaoRepository).findById(Mockito.any());
    }

    @Test(expected = NotFoundException.class)
    public void abrirSessaoVotacaoPautaInvalidaTeste() {
        SessaoVotacaoAbrirDTO sessaoVotacaoDTO = getSessaoVotacaoDTO();


        sessaoVotacaoService.abrirSessaoVotacao(sessaoVotacaoDTO);

    }

    private SessaoVotacaoAbrirDTO getSessaoVotacaoDTO() {
        return new SessaoVotacaoAbrirDTO(1);
    }

    private SessaoVotacaoEncerrarDTO getSessaoVotacaoDTOEncerrar() {
        return new SessaoVotacaoEncerrarDTO(1);
    }
}
