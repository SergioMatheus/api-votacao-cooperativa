package com.votacao.cooperativa.repository;

import com.votacao.cooperativa.entity.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Integer> {


    @Query("select s from SessaoVotacao s where s.ativa=:ativo")
    List<SessaoVotacao> buscarTodasSessoesEmAndamento(Boolean ativo);

    Boolean existsByIdAndAndAtiva(Integer id, Boolean ativa);

    boolean existsById(Integer id);
}
