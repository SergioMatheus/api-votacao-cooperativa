package com.votacao.cooperativa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "tbl_votacao")
@AllArgsConstructor
@NoArgsConstructor
public class Votacao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "voto")
    private Boolean voto;

    @Column(name = "id_pauta")
    private Integer idPauta;

    @Column(name = "id_sessao_votacao")
    private Integer idSessaoVotacao;
}
