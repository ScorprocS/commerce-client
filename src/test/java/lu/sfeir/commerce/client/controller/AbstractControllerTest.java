package lu.sfeir.commerce.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lu.sfeir.commerce.client.AbstractIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

abstract class AbstractControllerTest extends AbstractIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

}
