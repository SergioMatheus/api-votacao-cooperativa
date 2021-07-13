package com.votacao.cooperativa.controller;

import com.votacao.cooperativa.dto.SessaoVotacaoAbrirDTO;
import com.votacao.cooperativa.dto.SessaoVotacaoDTO;
import com.votacao.cooperativa.dto.SessaoVotacaoEncerrarDTO;
import com.votacao.cooperativa.exception.SessaoEncerradaException;
import com.votacao.cooperativa.producer.VotacaoFinalizeProducer;
import com.votacao.cooperativa.service.SessaoVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/sessoes-votacao")
@Api(value = "Sessao Votacao", tags = "Sessao Votacao")
public class SessaoVotacaoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoVotacaoController.class);

    private final SessaoVotacaoService service;

    private final VotacaoFinalizeProducer votacaoFinalizeProducer;

    @Autowired
    public SessaoVotacaoController(SessaoVotacaoService service, VotacaoFinalizeProducer votacaoFinalizeProducer) {
        this.service = service;
        this.votacaoFinalizeProducer = votacaoFinalizeProducer;
    }

    @ApiOperation(value = "Abrir uma sessão de votação, referente a determinada pauta")
    @PostMapping(value = "/abrir-sessao")
    public ResponseEntity<SessaoVotacaoDTO> abrirSessaoVotacao(@Valid @RequestBody SessaoVotacaoAbrirDTO sessaoVotacaoAbrirDTO) {
        LOGGER.debug("Abrindo a sessao para votacao da pauta  id = {}", sessaoVotacaoAbrirDTO.getIdPauta());
        SessaoVotacaoDTO dto = service.abrirSessaoVotacao(sessaoVotacaoAbrirDTO);
        LOGGER.debug("Sessao para votacao da pauta  id = {} aberta", sessaoVotacaoAbrirDTO.getIdPauta());
        LOGGER.debug("Hora de inicio sessao para votacao {}", dto.getDataHoraInicio());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @ApiOperation(value = "Encerrar uma sessão de votação")
    @PostMapping(value = "/encerrar-sessao")
    public ResponseEntity<SessaoVotacaoDTO> encerrarSessaoVotacao(@Valid @RequestBody SessaoVotacaoEncerrarDTO sessaoVotacaoEncerrarDTO) {
        if (service.isSessaoVotacaoValida(sessaoVotacaoEncerrarDTO.getIdSessao())) {
            SessaoVotacaoDTO dto = service.encerrarSessaoVotacao(sessaoVotacaoEncerrarDTO);
            votacaoFinalizeProducer.send(dto.toString());
            LOGGER.debug("Sessao Encerrada, id = {}", sessaoVotacaoEncerrarDTO.getIdSessao());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } else {
            throw new SessaoEncerradaException("A sessão de votação já encontra-se encerrada.");
        }
    }
}
