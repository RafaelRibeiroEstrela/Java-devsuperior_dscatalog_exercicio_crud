package rafaelribeiroestrela.com.github.client.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import rafaelribeiroestrela.com.github.client.entities.Client;

public class ClientDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String cpf;
	private Double income;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthDate;
	private Integer children;
	
	public ClientDTO() {
		
	}

	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client c) {
		id = c.getId();
		name = c.getName();
		cpf = c.getCpf();
		income = c.getIncome();
		birthDate = c.getBirthDate();
		children = c.getChildren();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}
	
	

}
