package lu.sfeir.commerce.client.service;

import lu.sfeir.commerce.client.clientapi.dto.ClientDto;
import lu.sfeir.commerce.client.entity.Client;
import lu.sfeir.commerce.client.mapper.ClientDtoMapper;
import lu.sfeir.commerce.client.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientDtoMapper clientDtoMapper;

    @InjectMocks
    private ClientService clientService;

    @DisplayName("Get By Id")
    @Nested
    class GetId {

        @Test
        void found(){
            final long userId = 1L;

            final Client clientEntity = new Client();
            final ClientDto clientDto = new ClientDto();

            when(clientRepository.findById(userId))
                    .thenReturn(Optional.of(clientEntity));

            when(clientDtoMapper.map(clientEntity))
                    .thenReturn(clientDto);

            final Optional<ClientDto> actual = clientService.getById(userId);

            assertThat(actual).contains(clientDto);
        }

        @Test
        void notFound(){
            final Optional<ClientDto> actual = clientService.getById(1L);

            assertThat(actual).isEmpty();
        }
    }

}