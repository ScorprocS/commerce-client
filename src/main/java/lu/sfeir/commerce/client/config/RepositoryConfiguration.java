package lu.sfeir.commerce.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lu.sfeir.commerce.client.handler.ClientRepositoryEventHandler;

@Configuration
public class RepositoryConfiguration {
	 @Bean
	    ClientRepositoryEventHandler clientRepositoryEventHandler() {
	        return new ClientRepositoryEventHandler();
	    }

	   
}
