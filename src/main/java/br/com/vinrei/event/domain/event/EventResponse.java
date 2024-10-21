package br.com.vinrei.event.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
	
    private Long id;
 	
    private String eventName;
    
    private int guests;
    
    private Date eventDate;

}
