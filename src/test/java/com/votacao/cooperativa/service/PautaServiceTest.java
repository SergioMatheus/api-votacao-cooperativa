package com.votacao.cooperativa.service;

import com.votacao.cooperativa.dto.PautaDTO;
import com.votacao.cooperativa.entity.Pauta;
import com.votacao.cooperativa.exception.NotFoundException;
import com.votacao.cooperativa.repository.PautaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    @Test
    public void salvarPautaTest() {
        PautaDTO pautaDTO = getPautaDTO();

        when(pautaRepository.save(Mockito.any())).thenReturn(Pauta.builder().id(1).descricao("teste").build());
        pautaService.salvar(pautaDTO);

        verify(pautaRepository).save(Mockito.any());
    }

    @Test
    public void buscarPautaPeloIDTest() {

        when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.of(Pauta.builder().id(1).descricao("teste").build()));
        pautaService.buscarPautaPeloID(1);

        verify(pautaRepository).findById(Mockito.any());
    }

    @Test
    public void isPautaValidaTest() {

        when(pautaRepository.existsById(Mockito.any())).thenReturn(true);
        pautaService.isPautaValida(1);

        verify(pautaRepository).existsById(Mockito.any());
    }

    @Test(expected = NotFoundException.class)
    public void isPautaInvalidaTest() {

        when(pautaRepository.existsById(Mockito.any())).thenReturn(false);
        pautaService.isPautaValida(2);

        verify(pautaRepository).existsById(Mockito.any());
    }


    private PautaDTO getPautaDTO() {
        return new PautaDTO(1,"teste");
    }
}
