package lu.sfeir.commerce.client.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventWrapper<T> {
	
	public enum EventType{
		CREATED,UPDATED,DELETED
	}
	
	private T data;
	private EventType eventType;
	
	

}
