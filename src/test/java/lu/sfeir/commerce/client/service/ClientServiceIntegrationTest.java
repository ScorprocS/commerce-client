package lu.sfeir.commerce.client.service;

import jakarta.transaction.Transactional;
import lu.sfeir.commerce.client.AbstractIntegrationTest;
import lu.sfeir.commerce.client.clientapi.dto.ClientDto;
import lu.sfeir.commerce.client.entity.Client;
import lu.sfeir.commerce.client.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClientServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository repository;

    @DisplayName("Get By Id")
    @Nested
    class GetId {

        @Test
        @Transactional
        void found() {
            final Client clientEntity = repository.save(johnSmithClientEntity());

            final Optional<ClientDto> actual = clientService.getById(clientEntity.getId());

            assertThat(actual).get().extracting(ClientDto::getEmail).isEqualTo(clientEntity.getEmail());
            assertThat(actual).get().extracting(ClientDto::getFirstName).isEqualTo(clientEntity.getFirstName());
            assertThat(actual).get().extracting(ClientDto::getLastName).isEqualTo(clientEntity.getLastName());
        }

        @Test
        @Transactional
        void notFound() {
            final Client clientEntity = repository.save(johnSmithClientEntity()); // noise

            final Optional<ClientDto> actual = clientService.getById(clientEntity.getId() + 42);

            assertThat(actual).isEmpty();
        }
    }

    private Client johnSmithClientEntity() {
        final Client clientEntity = new Client();

        clientEntity.setEmail("john.smith@acme.com");
        clientEntity.setFirstName("John");
        clientEntity.setLastName("Smith");

        return clientEntity;
    }

}
