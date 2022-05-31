package rafaelribeiroestrela.com.github.client.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rafaelribeiroestrela.com.github.client.dtos.ClientDTO;
import rafaelribeiroestrela.com.github.client.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findPageable(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(service.findPageable(pageable));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ClientDTO>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@GetMapping("/cpf")
	public ResponseEntity<ClientDTO> findById(@RequestParam String cpf) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByCpf(cpf));
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
