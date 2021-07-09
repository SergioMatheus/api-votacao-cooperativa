package com.votacao.cooperativa.dto;

import com.votacao.cooperativa.entity.Votacao;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "VotacaoDTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotacaoDTO {

    @ApiModelProperty(value = "ID da votação aberta")
    private Integer id;

    @ApiModelProperty(value = "ID da pauta da votação aberta")
    private Integer idPauta;

    @ApiModelProperty(value = "ID da sessão de votação aberta")
    private Integer idSessaoVotacao;

    @ApiModelProperty(value = "Voto")
    private Boolean voto;

    @ApiModelProperty(value = "Quantidade de votos positivos")
    private Integer quantidadeVotosSim;

    @ApiModelProperty(value = "Quantidade de votos negativos")
    private Integer quantidadeVotosNao;

    public static Votacao toEntity(VotacaoDTO dto) {
        return Votacao.builder()
                .id(dto.getId())
                .idPauta(dto.getIdPauta())
                .idSessaoVotacao(dto.getIdSessaoVotacao())
                .voto(dto.getVoto())
                .build();
    }
}
