package com.votacao.cooperativa.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoAbrirDTO {

    @ApiModelProperty(value = "ID da Pauta que se quer abrir sessão para votação")
    @NotNull(message = "idPauta deve ser preenchido")
    private Integer idPauta;

}
