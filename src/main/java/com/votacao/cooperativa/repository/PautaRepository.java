package com.votacao.cooperativa.repository;

import com.votacao.cooperativa.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {

    Boolean existsByid(Integer id);
}
