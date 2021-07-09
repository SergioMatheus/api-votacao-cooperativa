package com.votacao.cooperativa.feign.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "CpfConsultaService", url = "https://user-info.herokuapp.com/users/")
public interface CpfConsultaService {

    @RequestMapping("{cpf}")
    String validaCpf(@PathVariable("cpf") String cpfValido);
}