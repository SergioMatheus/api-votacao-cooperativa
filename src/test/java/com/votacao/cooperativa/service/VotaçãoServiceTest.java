package com.votacao.cooperativa.service;

import com.votacao.cooperativa.dto.PautaDTO;
import com.votacao.cooperativa.dto.VotoDTO;
import com.votacao.cooperativa.exception.NotFoundException;
import com.votacao.cooperativa.exception.SessaoEncerradaException;
import com.votacao.cooperativa.exception.VotoInvalidoException;
import com.votacao.cooperativa.repository.VotacaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VotaçãoServiceTest {

    @Mock
    private VotacaoRepository repository;
    @Mock
    private PautaService pautaService;
    @Mock
    private SessaoVotacaoService sessaoVotacaoService;
    @Mock
    private AssociadoService associadoService;

    @InjectMocks
    private VotacaoService votacaoService;

    @Test(expected = NotFoundException.class)
    public void validaVotoPautaInvalida() {
        VotoDTO votoDTO = getVotoDTO();

        when(pautaService.isPautaValida(Mockito.any())).thenReturn(false);

        votacaoService.isValidaVoto(votoDTO);

        verify(pautaService).isPautaValida(Mockito.any());
    }

    @Test(expected = SessaoEncerradaException.class)
    public void validaVotoSessaoVotacaoInvalida() {
        VotoDTO votoDTO = getVotoDTO();

        when(pautaService.isPautaValida(Mockito.any())).thenReturn(true);
        when(sessaoVotacaoService.isSessaoVotacaoValida(Mockito.any())).thenReturn(false);

        votacaoService.isValidaVoto(votoDTO);

        verify(pautaService).isPautaValida(Mockito.any());
        verify(sessaoVotacaoService).isSessaoVotacaoValida(Mockito.any());
    }

    @Test(expected = VotoInvalidoException.class)
    public void validaVotoAssociadoInvalido() {
        VotoDTO votoDTO = getVotoDTO();

        when(pautaService.isPautaValida(Mockito.any())).thenReturn(true);
        when(sessaoVotacaoService.isSessaoVotacaoValida(Mockito.any())).thenReturn(true);
        when(associadoService.isAssociadoPodeVotar(Mockito.any())).thenReturn(false);

        votacaoService.isValidaVoto(votoDTO);

        verify(pautaService).isPautaValida(Mockito.any());
        verify(sessaoVotacaoService).isSessaoVotacaoValida(Mockito.any());
        verify(associadoService).isAssociadoPodeVotar(Mockito.any());
    }

    @Test(expected = VotoInvalidoException.class)
    public void validaDuplicidadeVotoAssociado() {
        VotoDTO votoDTO = getVotoDTO();

        when(pautaService.isPautaValida(Mockito.any())).thenReturn(true);
        when(sessaoVotacaoService.isSessaoVotacaoValida(Mockito.any())).thenReturn(true);
        when(associadoService.isAssociadoPodeVotar(Mockito.any())).thenReturn(true);
        when(associadoService.isValidaParticipacaoAssociadoVotacao(Mockito.any(),Mockito.any())).thenReturn(false);

        votacaoService.isValidaVoto(votoDTO);

        verify(pautaService).isPautaValida(Mockito.any());
        verify(sessaoVotacaoService).isSessaoVotacaoValida(Mockito.any());
        verify(associadoService).isAssociadoPodeVotar(Mockito.any());
        verify(associadoService).isValidaParticipacaoAssociadoVotacao(Mockito.any(),Mockito.any());
    }

    @Test
    public void validaVotoAssociado() {
        VotoDTO votoDTO = getVotoDTO();

        when(pautaService.isPautaValida(Mockito.any())).thenReturn(true);
        when(sessaoVotacaoService.isSessaoVotacaoValida(Mockito.any())).thenReturn(true);
        when(associadoService.isAssociadoPodeVotar(Mockito.any())).thenReturn(true);
        when(associadoService.isValidaParticipacaoAssociadoVotacao(Mockito.any(),Mockito.any())).thenReturn(true);

        votacaoService.isValidaVoto(votoDTO);

        verify(pautaService).isPautaValida(Mockito.any());
        verify(sessaoVotacaoService).isSessaoVotacaoValida(Mockito.any());
        verify(associadoService).isAssociadoPodeVotar(Mockito.any());
        verify(associadoService).isValidaParticipacaoAssociadoVotacao(Mockito.any(),Mockito.any());
    }

    @Test
    public void votarTeste() {
        VotoDTO votoDTO = getVotoDTO();

        when(pautaService.isPautaValida(Mockito.any())).thenReturn(true);
        when(sessaoVotacaoService.isSessaoVotacaoValida(Mockito.any())).thenReturn(true);
        when(associadoService.isAssociadoPodeVotar(Mockito.any())).thenReturn(true);
        when(associadoService.isValidaParticipacaoAssociadoVotacao(Mockito.any(),Mockito.any())).thenReturn(true);

        votacaoService.votar(votoDTO);

        verify(pautaService).isPautaValida(Mockito.any());
        verify(sessaoVotacaoService).isSessaoVotacaoValida(Mockito.any());
        verify(associadoService).isAssociadoPodeVotar(Mockito.any());
        verify(associadoService).isValidaParticipacaoAssociadoVotacao(Mockito.any(),Mockito.any());
    }

    @Test
    public void buscarResultadoValidacaoTeste() {
        when(repository.countVotacaoByIdPautaAndIdSessaoVotacaoAndVoto(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(2);

        votacaoService.buscarResultadoVotacao(1,1);

        verify(repository, atLeast(2)).countVotacaoByIdPautaAndIdSessaoVotacaoAndVoto(Mockito.any(),Mockito.any(),Mockito.any());
    }

    @Test
    public void buscarDadosResultadoValidacaoTeste() {
        when(sessaoVotacaoService.isSessaoVotacaoExiste(Mockito.any())).thenReturn(true);
        when(pautaService.isPautaValida(Mockito.any())).thenReturn(true);
        when(sessaoVotacaoService.isSessaoValidaParaContagem(Mockito.any())).thenReturn(true);
        when(pautaService.buscarPautaPeloID(Mockito.any())).thenReturn(PautaDTO.builder().id(1).descricao("teste").build());
        when(repository.countVotacaoByIdPautaAndIdSessaoVotacaoAndVoto(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(2);

        votacaoService.buscarDadosResultadoVotacao(1,1);

        verify(sessaoVotacaoService).isSessaoVotacaoExiste(Mockito.any());
        verify(pautaService).isPautaValida(Mockito.any());
        verify(sessaoVotacaoService).isSessaoValidaParaContagem(Mockito.any());
        verify(pautaService).buscarPautaPeloID(Mockito.any());
        verify(repository, atLeast(2)).countVotacaoByIdPautaAndIdSessaoVotacaoAndVoto(Mockito.any(),Mockito.any(),Mockito.any());
    }

    private VotoDTO getVotoDTO() {
        return new VotoDTO(1,1,true,"1212");
    }

}
