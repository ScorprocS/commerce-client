package lu.sfeir.commerce.client.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lu.sfeir.commerce.client.clientapi.dto.ClientDto;
import lu.sfeir.commerce.client.entity.Client;
import lu.sfeir.commerce.client.exception.EntityNotFoundException;
import lu.sfeir.commerce.client.mapper.ClientDtoMapper;
import lu.sfeir.commerce.client.repository.ClientRepository;

@RequiredArgsConstructor
@Service
public class ClientService {
	
	private final ClientRepository clientRepository;
	private final ClientDtoMapper clientDtoMapper;
	
	public List<ClientDto> getAll(){
		return this.clientRepository.findAll().stream().map(clientDtoMapper::map).toList();
	}
	
	public Optional<ClientDto> getById(Long id){
		return this.clientRepository.findById(id).map(clientDtoMapper::map);
	}
	
	public ClientDto createClient(ClientDto clientDto) {
		Client client = this.clientRepository.save(clientDtoMapper.map(clientDto));
		
		return clientDtoMapper.map(client);
	}
	
	public ClientDto updateClient(Long id, ClientDto clientDto) {
		Optional<Client> clientOpt = this.clientRepository.findById(id);
		if(clientOpt.isEmpty()) {
			throw new EntityNotFoundException("Entity with id "+id+" not found.");
		}
		Client client = this.clientRepository.save(clientDtoMapper.map(clientOpt.get(),clientDto));
		
		return clientDtoMapper.map(client);
	}

	public void deleteById(Long id){
		this.clientRepository.deleteById(id);
	}
}
