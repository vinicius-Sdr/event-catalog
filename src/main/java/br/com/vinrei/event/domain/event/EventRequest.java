package br.com.vinrei.event.domain.event;

import org.springframework.web.multipart.MultipartFile;

public record EventRequest(String eventName, String eventDate,String cep, MultipartFile img) {
}


