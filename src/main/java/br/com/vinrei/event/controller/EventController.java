package br.com.vinrei.event.controller;


import br.com.vinrei.event.domain.party.Event;
import br.com.vinrei.event.domain.party.EventRequest;
import br.com.vinrei.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Event> createEvent(@RequestParam int guests,
											 @RequestParam String eventName,
											 @RequestParam String startDate,
											 @RequestParam String endDate,
											 @RequestPart("image") MultipartFile imageFile) throws ParseException {
		EventRequest event = new EventRequest(guests, eventName, startDate, endDate, imageFile);

		return new ResponseEntity<>(eventService.save(event, imageFile), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Event>> getAllEvents() {
		 return  ResponseEntity.ok().body(eventService.findAllParties());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable UUID id) {
		 return ResponseEntity.ok().body(eventService.findEventById(id));
	}


	@PutMapping("/{id}")
	public ResponseEntity<Event> editEvent(@PathVariable UUID id, @RequestBody EventRequest eventRequest) throws ParseException {
		return ResponseEntity.ok().body(this.eventService.editEvent(id, eventRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Event> deleteEvent(@PathVariable UUID id){
		this.eventService.deleteEvent(id);

		return ResponseEntity.noContent().build();
	}

}
