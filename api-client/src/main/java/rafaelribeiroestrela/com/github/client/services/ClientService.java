package rafaelribeiroestrela.com.github.client.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rafaelribeiroestrela.com.github.client.dtos.ClientDTO;
import rafaelribeiroestrela.com.github.client.entities.Client;
import rafaelribeiroestrela.com.github.client.exceptions.ApiException;
import rafaelribeiroestrela.com.github.client.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findPageable(Pageable pageable){
		return repository.findAll(pageable).map(obj -> new ClientDTO(obj));
	}
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(){
		return repository.findAll().stream()
				.map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> client = repository.findById(id);
		if (!client.isPresent()) {
			throw new ApiException("CLIENT NOT FOUND");
		}
		return new ClientDTO(client.get());
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findByCpf(String cpf) {
		Client client = repository.findByCpf(cpf);
		if (client == null) {
			return null;
		}
		return new ClientDTO(repository.findByCpf(cpf));
	}
	
	public ClientDTO save(ClientDTO dto) {
		Client client = new Client();
		verifyExistsCpf(dto.getCpf());
		copyDtoToEntity(dto, client);
		return new ClientDTO(repository.save(client));
	}
	
	public ClientDTO update(Long id, ClientDTO dto) {
		Optional<Client> client = repository.findById(id);
		if (!client.isPresent()) {
			throw new ApiException("Client not found");
		}
		if (!dto.getCpf().equals(client.get().getCpf())) {
			verifyExistsCpf(dto.getCpf());
		}
		copyDtoToEntity(dto, client.get());
		return new ClientDTO(repository.save(client.get()));
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	private void copyDtoToEntity(ClientDTO dto, Client c) {
		c.setName(dto.getName());
		c.setCpf(dto.getCpf());
		c.setIncome(dto.getIncome());
		c.setBirthDate(dto.getBirthDate());
		c.setChildren(dto.getChildren());
	}
	
	private void verifyExistsCpf(String cpf) {
		if (repository.findByCpf(cpf) != null) {
			throw new ApiException("THE CPF EXISTS IN DATABASE");
		}
	}
	
	
	
	
}
