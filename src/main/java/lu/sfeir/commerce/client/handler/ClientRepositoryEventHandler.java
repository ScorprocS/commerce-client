package lu.sfeir.commerce.client.handler;

import org.springframework.beans.factory.annotation.Autowired;

import lu.sfeir.commerce.client.entity.Client;
import lu.sfeir.commerce.client.service.ClientEventSender;
import lu.sfeir.commerce.client.service.EventWrapper;
import lu.sfeir.commerce.client.service.EventWrapper.EventType;


//@RepositoryEventHandler(Client.class) 
public class ClientRepositoryEventHandler {
	@Autowired
	private ClientEventSender clientEventSender;
	
	//@HandleAfterCreate
    public void handleClientAfterCreate(Client client){
		EventWrapper<Client> ew=new EventWrapper<Client>(client,EventType.CREATED);
	
		clientEventSender.sendMessage(ew);
    }
	
	//@HandleAfterSave
    public void handleClientAfterUpdate(Client client){
		EventWrapper<Client> ew=new EventWrapper<Client>(client,EventType.UPDATED);
		
		clientEventSender.sendMessage(ew);
    }
	
	//@HandleAfterDelete
    public void handleClientAfterDelete(Client client){
		EventWrapper<Client> ew=new EventWrapper<Client>(client,EventType.DELETED);
		
		clientEventSender.sendMessage(ew);
	}
}
