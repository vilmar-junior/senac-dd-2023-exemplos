package model.vo.telefonia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private Integer id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private List<Telefone> telefones;
	private boolean ativo;
	private Endereco endereco;
	
	public Cliente() {
		this.telefones = new ArrayList<>();
	}
	
	public Cliente(Integer id, String nome, String cpf, LocalDate dataNascimento, List<Telefone> telefones, boolean ativo, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefones = telefones;
		this.ativo = ativo;
		this.endereco = endereco;
	}



	public Cliente(String nome, String cpf, LocalDate dataNascimento, List<Telefone> telefones, boolean ativo, Endereco endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefones = telefones;
		this.ativo = ativo;
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Override
	public String toString() {
		return nome + " (" + cpf + ")";
	}
}
