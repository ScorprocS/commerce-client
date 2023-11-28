package lu.sfeir.commerce.client.config;

//import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lu.sfeir.commerce.client.entity.Client;
import lu.sfeir.commerce.client.service.EventWrapper;

@Component
public class CustomSerializer {//implements Serializer<EventWrapper<Client>>{

	@Autowired
	private ObjectMapper objectMapper;

/*
	@Override
	public byte[] serialize(String topic, EventWrapper<Client> data) {
		
		try {
			return objectMapper.writeValueAsBytes(data);
		}catch(Exception e) {
			
		}
		return null;
	}*/

}
