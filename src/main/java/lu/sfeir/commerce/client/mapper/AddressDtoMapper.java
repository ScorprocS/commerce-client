package lu.sfeir.commerce.client.mapper;

import org.mapstruct.Mapper;

import lu.sfeir.commerce.client.clientapi.dto.AddressDto;
import lu.sfeir.commerce.client.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressDtoMapper {

	AddressDto map(Address address);

}
