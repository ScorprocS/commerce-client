package lu.sfeir.commerce.client.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import lu.sfeir.commerce.client.entity.Client;

//@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
public interface ClientRestRepository  extends PagingAndSortingRepository<Client,Long>{

}
