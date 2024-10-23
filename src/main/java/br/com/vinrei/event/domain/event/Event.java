package br.com.vinrei.event.domain.event;


import br.com.vinrei.event.domain.address.Address;
import br.com.vinrei.event.utils.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EVENT")
@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "EVENT_NAME", nullable = false)
    private String eventName;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    @Column(name = "EVENT_DATE", nullable = false)
    private Date eventDate;

    @Column(name = "img url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public Event(EventRequest request) throws ParseException {
        this.createdAt = DateUtils.formatDate(new Date());
        this.eventName = request.eventName();
        this.eventDate = DateUtils.formatStringToDate(request.eventDate());
    }


}
