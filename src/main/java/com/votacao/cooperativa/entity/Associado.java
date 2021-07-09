package com.votacao.cooperativa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "tbl_associado")
@AllArgsConstructor
@NoArgsConstructor
public class Associado {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "cpf_associado")
    private String cpfAssociado;

    @Column(name = "id_pauta")
    private Integer idPauta;
}
