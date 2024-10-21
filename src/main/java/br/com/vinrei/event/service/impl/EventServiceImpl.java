package br.com.vinrei.event.service.impl;


import br.com.vinrei.event.domain.address.Address;
import br.com.vinrei.event.domain.cep.CepResponse;
import br.com.vinrei.event.domain.event.Event;
import br.com.vinrei.event.domain.event.EventRequest;
import br.com.vinrei.event.domain.event.exception.EventNotFoundException;
import br.com.vinrei.event.repository.EventRepository;
import br.com.vinrei.event.service.AddressService;
import br.com.vinrei.event.service.EventService;
import br.com.vinrei.event.utils.DateUtils;
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

    @Value("${AWS_BUCKET}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    LogResponseServiceImpl logResponseService;


    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(EventRequest request, MultipartFile imageFile) throws ParseException {
    String imgUrl = null;

        if(!imageFile.isEmpty()){
             imgUrl = this.uploadImg(imageFile);
        }

        CepResponse response = logResponseService.consultaCep(request.cep());

        Address address = addressService.save(response);

        Event event = new Event(request);
        event.setImgUrl(imgUrl);
        event.setAddress(address);

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
        return this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
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
        if (!eventRequest.eventDate().isEmpty()) event.setEventDate(DateUtils.formatStringToDate(eventRequest.eventDate()));
        this.eventRepository.save(event);


        return event;
    }

}
