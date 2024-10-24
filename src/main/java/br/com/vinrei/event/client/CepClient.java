package br.com.vinrei.event.client;

import br.com.vinrei.event.domain.cep.CepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cepClient", url = "${external.url.api}")
public interface CepClient {

    @GetMapping("/{cep}")
    CepResponse getCepData(@PathVariable("cep") String cep);

}
