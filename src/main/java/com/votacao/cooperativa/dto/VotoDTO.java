package com.votacao.cooperativa.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "VotoDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {

    @ApiModelProperty(value = "ID da pauta da votação aberta")
    @NotNull(message = "idPauta deve ser preenchido")
    private Integer idPauta;

    @ApiModelProperty(value = "ID da sessão de votação aberta")
    @NotNull(message = "idSessaoVotacao deve ser preenchido")
    private Integer idSessaoVotacao;

    @ApiModelProperty(value = "Voto")
    @NotNull(message = "Voto deve ser preenchido")
    private Boolean voto;

    @ApiModelProperty(value = "CPF valido do associado")
    @NotBlank(message = "cpf do associado deve ser preenchido")
    private String cpfAssociado;
}
