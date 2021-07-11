package com.votacao.cooperativa.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoEncerrarDTO {

    @ApiModelProperty(value = "ID da Sessão que deseja encerrar a votação")
    @NotNull(message = "idSessao deve ser preenchido")
    private Integer idSessao;

}
