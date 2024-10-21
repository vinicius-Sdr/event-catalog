package br.com.vinrei.event.service;

import br.com.vinrei.event.domain.address.Address;
import br.com.vinrei.event.domain.cep.CepResponse;

public interface AddressService {

    Address save(CepResponse response);

}
