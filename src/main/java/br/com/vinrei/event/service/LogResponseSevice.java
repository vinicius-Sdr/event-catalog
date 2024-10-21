package br.com.vinrei.event.service;

import br.com.vinrei.event.domain.cep.CepResponse;

public interface LogResponseSevice {

    CepResponse consultaCep(String cep);
}
