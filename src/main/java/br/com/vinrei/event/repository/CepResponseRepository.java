package br.com.vinrei.event.repository;


import br.com.vinrei.event.client.CepClient;
import br.com.vinrei.event.domain.LogResponse.LogResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepResponseRepository extends JpaRepository<LogResponse, Long> {

}
