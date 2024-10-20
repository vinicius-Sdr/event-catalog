package br.com.vinrei.event.domain.party;

import org.springframework.web.multipart.MultipartFile;

public record EventRequest(int guests, String eventName, String startDate, String finishDate, MultipartFile img) {
}


