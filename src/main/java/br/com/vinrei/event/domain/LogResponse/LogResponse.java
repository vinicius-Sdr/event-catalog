package br.com.vinrei.event.domain.LogResponse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "LOG_RESPONSE")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String data;

    private LocalDateTime timestamp;

}
