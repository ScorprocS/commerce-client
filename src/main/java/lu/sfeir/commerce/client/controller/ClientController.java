package lu.sfeir.commerce.client.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lu.sfeir.commerce.client.clientapi.api.ClientsApi;
import lu.sfeir.commerce.client.clientapi.dto.ClientDto;
import lu.sfeir.commerce.client.exception.EntityNotFoundException;
import lu.sfeir.commerce.client.service.ClientService;

@RequiredArgsConstructor
@RestController
public class ClientController implements ClientsApi {

	private final ClientService clientService;

	@Override
	public ResponseEntity<ClientDto> addClient(@Valid ClientDto clientDto) {
		return ResponseEntity.created(null).body(clientService.createClient(clientDto));
	}

	@Override
	public ResponseEntity<Void> deleteClient(Long clientId) {
		clientService.deleteById(clientId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<ClientDto>> getAllClients() {
		List<ClientDto> clients = clientService.getAll();
		if (clients.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clients);
	}

	@Override
	public ResponseEntity<ClientDto> getClientById(Long clientId) {
		return ResponseEntity.ok(clientService.getById(clientId).orElseThrow(() -> new EntityNotFoundException()));
	}

	@Override
	public ResponseEntity<ClientDto> updateClient(Long clientId, @Valid ClientDto clientDto) {
		return ResponseEntity.created(null).body(clientService.createClient(clientDto));
	}

}
