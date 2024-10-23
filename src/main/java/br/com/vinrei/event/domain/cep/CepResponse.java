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

    @Override
    public String toString() {
        return "CepResponse{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", estado='" + estado + '\'' +
                ", complemento='" + complemento + '\'' +
                '}';
    }
}
