package br.com.vinrei.event.domain.cep;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CepResponse {

    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
    private String cidade;
    private String bairro;
    private String estado;
    private String complemento;


}
