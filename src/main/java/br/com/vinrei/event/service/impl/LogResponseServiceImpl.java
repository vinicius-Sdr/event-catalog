package br.com.vinrei.event.service.impl;

import br.com.vinrei.event.client.CepClient;
import br.com.vinrei.event.domain.cep.CepResponse;
import br.com.vinrei.event.domain.cep.LogCepResponse;
import br.com.vinrei.event.repository.LogCepResponseRepository;
import br.com.vinrei.event.service.LogResponseSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogResponseServiceImpl implements LogResponseSevice {

    @Autowired
    LogCepResponseRepository cepRespondeRepository;

    @Autowired
    CepClient cepClient;

    public CepResponse consultaCep(String cep) {

        CepResponse response = cepClient.getCepData(cep);

        LogCepResponse logCepResponse = new LogCepResponse(cep, response.toString(), LocalDateTime.now());
        
        cepRespondeRepository.save(logCepResponse);

        return response;
    }

}
