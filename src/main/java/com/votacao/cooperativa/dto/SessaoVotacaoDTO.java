package com.votacao.cooperativa.dto;

import com.votacao.cooperativa.entity.SessaoVotacao;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel(value = "SessaoVotacaoDTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoDTO {

    @ApiModelProperty(value = "ID da sessão de votação aberta")
    private Integer id;

    @ApiModelProperty(value = "Data Hora de início da sessão de votação aberta")
    private LocalDateTime dataHoraInicio;

    @ApiModelProperty(value = "Status da sessão de votação aberta")
    private Boolean ativa;


    public static SessaoVotacao toEntity(SessaoVotacaoDTO dto) {
        return SessaoVotacao.builder()
                .id(dto.getId())
                .dataHoraInicio(dto.getDataHoraInicio())
                .ativa(dto.getAtiva())
                .build();
    }

    public static SessaoVotacaoDTO toDTO(SessaoVotacao sessaoVotacao) {
        return SessaoVotacaoDTO.builder()
                .id(sessaoVotacao.getId())
                .dataHoraInicio(sessaoVotacao.getDataHoraInicio())
                .ativa(sessaoVotacao.getAtiva())
                .build();
    }
}