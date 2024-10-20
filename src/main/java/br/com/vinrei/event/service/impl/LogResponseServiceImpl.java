package br.com.vinrei.event.service.impl;

import br.com.vinrei.event.client.CepClient;
import br.com.vinrei.event.repository.CepResponseRepository;
import br.com.vinrei.event.service.LogResponseSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogResponseServiceImpl implements LogResponseSevice {

    @Autowired
    CepResponseRepository cepRespondeRepository;

    @Autowired
    CepClient cepClient;

    @Override
    public String consultaCep(String cep) {
        return "";
    }

}
