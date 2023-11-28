package lu.sfeir.commerce.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import lu.sfeir.commerce.client.clientapi.dto.ClientDto;
import lu.sfeir.commerce.client.entity.Client;

@Mapper(componentModel = "spring", uses = AddressDtoMapper.class)
public interface ClientDtoMapper {

	ClientDto map(Client client);

	Client map(@MappingTarget Client client, ClientDto dto);

	default Client map(ClientDto dto) {
		return map(new Client(), dto);
	}

}
