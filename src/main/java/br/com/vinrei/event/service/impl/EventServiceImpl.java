package br.com.vinrei.event.service.impl;


import br.com.vinrei.event.domain.party.Event;
import br.com.vinrei.event.domain.party.EventRequest;
import br.com.vinrei.event.domain.party.exception.EventNotFoundException;
import br.com.vinrei.event.repository.EventRepository;
import br.com.vinrei.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    private final EventRepository eventRepository;


    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(EventRequest request, MultipartFile imageFile) throws ParseException {
    String imgUrl = null;

        if(!imageFile.isEmpty()){
             imgUrl = this.uploadImg(imageFile);
        }

        Event event = new Event(request);
        event.setImgUrl(imgUrl);

        return eventRepository.save(event);
    }

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .contentType(multipartFile.getContentType())
                    .build();
            s3Client.putObject(putOb, RequestBody.fromBytes(multipartFile.getInputStream().readAllBytes()));
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();

            return s3Client.utilities().getUrl(request).toString();
        } catch (Exception e) {
            System.out.println("erro ao subir arquivo");
            System.out.println(e.getMessage());
            return "";
        }
    }




    @Override
    public List<Event> findAllParties() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event findEventById(UUID id) {
        Event event = this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
        return event;
    }


    @Override
    public void deleteEvent(UUID id) {
        Event event = this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        this.eventRepository.delete(event);
    }

    @Override
    public Event editEvent(UUID id, EventRequest eventRequest) throws ParseException {

        Event event = this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        if (!eventRequest.eventName().isEmpty()) event.setEventName(eventRequest.eventName());
//        if (eventRequest.guests() != 0) event.setMaximumGuests(eventRequest.guests());
//        if (!eventRequest.finishDate().isEmpty())
//            event.setFinishDate(DateUtils.formatStringToDate(eventRequest.finishDate()));
//        if (!eventRequest.startDate().isEmpty())
//            event.setStartDate(DateUtils.formatStringToDate(eventRequest.startDate()));

        this.eventRepository.save(event);


        return event;
    }

}
