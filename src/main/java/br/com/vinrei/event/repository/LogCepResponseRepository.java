package br.com.vinrei.event.repository;


import br.com.vinrei.event.domain.cep.LogCepResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogCepResponseRepository extends JpaRepository<LogCepResponse, UUID> {

}
