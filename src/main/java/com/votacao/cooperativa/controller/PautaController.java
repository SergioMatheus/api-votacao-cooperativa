package com.votacao.cooperativa.controller;


import com.votacao.cooperativa.dto.PautaDTO;
import com.votacao.cooperativa.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/pautas")
@Api(value = "Pauta", tags = "Pauta")
public class PautaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PautaController.class);

    private final PautaService service;

    @Autowired
    public PautaController(PautaService service) {
        this.service = service;
    }

    @ApiOperation(value = "Criar uma pauta para ser votada")
    @PostMapping
    public ResponseEntity<PautaDTO> salvarPauta(@Valid @RequestBody PautaDTO dto) {
        LOGGER.debug("Salvando a pauta  = {}", dto.getDescricao());
        dto = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @ApiOperation(value = "Buscar a pauta utilizando ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PautaDTO> buscarPautaPeloID(@PathVariable("id") Integer id) {
        LOGGER.debug("Buscando a pauta pelo ID = {}", id);
        return ResponseEntity.ok(service.buscarPautaPeloID(id));
    }
}