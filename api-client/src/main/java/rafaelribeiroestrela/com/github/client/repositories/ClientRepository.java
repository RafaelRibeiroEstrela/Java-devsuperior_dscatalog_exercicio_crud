package rafaelribeiroestrela.com.github.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rafaelribeiroestrela.com.github.client.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("SELECT cl FROM Client cl "
			+ "WHERE cl.cpf = :cpf")
	Client findByCpf(String cpf);

}
