package br.com.vinrei.event.service;

import br.com.vinrei.event.domain.event.Event;
import br.com.vinrei.event.domain.event.EventRequest;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface EventService {

    Event save(EventRequest request, MultipartFile imageFile) throws ParseException;

    List<Event> findAllParties();

    Event findEventById(UUID id);

    void deleteEvent(UUID id);

    Event editEvent(UUID id, EventRequest eventRequest) throws ParseException;
}
 