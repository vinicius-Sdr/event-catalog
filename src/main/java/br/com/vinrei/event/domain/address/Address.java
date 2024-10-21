package br.com.vinrei.event.domain.address;

import br.com.vinrei.event.domain.cep.CepResponse;
import br.com.vinrei.event.domain.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESS")
@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", updatable = false, unique = true, nullable = false)
    private UUID id;

    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
    private String cidade;
    private String bairro;
    private String estado;
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Address(CepResponse response){

        this.cep = response.getCep();
        this.logradouro = response.getLogradouro();
        this.localidade = response.getLocalidade();
        this.uf = response.getUf();
        this.cidade = response.getCidade();
        this.bairro = response.getBairro();
        this.estado = response.getEstado();
        this.complemento = response.getComplemento();

    }



}
