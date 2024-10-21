package br.com.vinrei.event.domain.cep;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "LOG_RESPONSE")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogCepResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", updatable = false, unique = true, nullable = false)
    private UUID id;

    private String cep;

    private String data;

    private LocalDateTime timestamp;

    public LogCepResponse(String cep, String string, LocalDateTime now) {
    }


}
