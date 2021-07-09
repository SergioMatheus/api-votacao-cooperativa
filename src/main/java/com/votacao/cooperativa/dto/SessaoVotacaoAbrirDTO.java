package com.votacao.cooperativa.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel(value = "SessaoVotacaoAbrirDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoAbrirDTO {

    @ApiModelProperty(value = "ID da Pauta que se quer abrir sessão para votação")
    @NotNull(message = "idPauta deve ser preenchido")
    private Integer idPauta;

    @ApiModelProperty(value = "Tempo em MINUTOS que a sessão de votação deverá ficar disponível")
    private Integer tempo;
}
