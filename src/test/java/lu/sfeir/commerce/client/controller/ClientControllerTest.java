package lu.sfeir.commerce.client.controller;

import lombok.Getter;
import lu.sfeir.commerce.client.clientapi.dto.ClientDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest extends AbstractControllerTest {

    @DisplayName("Get Client")
    @Nested
    class GetClient {

        @Test
        void found() throws Exception {
            var client = addClient(
                    new ClientDto(null, "john.smith@acme.com", "John", "Smith", null)
            );

            mockMvc.perform(
                            get("/clients/{id}", client.getId())
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email", equalTo("john.smith@acme.com")))
                    .andExpect(jsonPath("$.firstName", equalTo("John")))
                    .andExpect(jsonPath("$.lastName", equalTo("Smith")));
        }

        @Test
        void notFound() throws Exception {
            mockMvc.perform(
                            get("/clients/1234567890")
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message", equalTo("Entity with id 1234567890 not found.")));
        }
    }


    private ClientDto addClient(ClientDto client) throws Exception {
        final ClientResultHandler resultHandler = new ClientResultHandler();

        mockMvc.perform(
                        post("/clients")
                                .content(objectMapper.writeValueAsString(client))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(resultHandler);

        return resultHandler.getDto();
    }

    @Getter
    private final class ClientResultHandler implements ResultHandler {

        private ClientDto dto;

        @Override
        public void handle(MvcResult result) throws Exception {
            dto = objectMapper.readValue(result.getResponse().getContentAsByteArray(), ClientDto.class);
        }
    }
}