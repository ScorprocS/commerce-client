package lu.sfeir.commerce.client.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lu.sfeir.commerce.client.entity.Client;

@Service
public class ClientEventSender {
	@Value("${kafka.topicName}")
	private String topicName;
	@Autowired
	private KafkaTemplate<String, EventWrapper<Client>> kafkaTemplate;
	
	public void sendMessage(EventWrapper<Client> clientEvent) {
		CompletableFuture <SendResult<String, EventWrapper<Client>>> future =kafkaTemplate.send(topicName, clientEvent);
	       
	}
	
	/* @Override
	        public void onSuccess(SendResult<String, EventWrapper<Client>> result) {
	            System.out.println("Sent message=[" + result + 
	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        @Override
	        public void onFailure(Throwable ex) {
	            System.out.println("Unable to send message=[" 
	              + ex.getMessage() + "] due to : " + ex.getMessage());
	        }*/
}
