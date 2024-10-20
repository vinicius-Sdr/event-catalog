package br.com.vinrei.event.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cepClient", url = "http://api.mock.com")
public interface CepClient {

    @GetMapping("/cep/{cep}")
    String getCepData(@PathVariable("cep") String cep);
}
