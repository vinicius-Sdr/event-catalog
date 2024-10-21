package br.com.vinrei.event.service.impl;

import br.com.vinrei.event.domain.address.Address;
import br.com.vinrei.event.domain.cep.CepResponse;
import br.com.vinrei.event.repository.AddressRepository;
import br.com.vinrei.event.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Address save(CepResponse response) {

        Address address = new Address(response);

        return addressRepository.save(address);
    }
}
