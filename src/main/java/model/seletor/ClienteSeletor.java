package model.seletor;

import java.time.LocalDate;

public class ClienteSeletor extends BaseSeletor{

	private String nome;
	private String cpf;
	private LocalDate dataNascimentoInicial;
	private LocalDate dataNascimentoFinal;
	
	@Override
	public boolean temFiltro() {
		return (this.nome != null && this.nome.trim().length() > 0)
			|| (this.cpf != null && this.cpf.trim().length() > 0)
			|| this.dataNascimentoInicial != null
			|| this.dataNascimentoFinal != null;
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

	public LocalDate getDataNascimentoInicial() {
		return dataNascimentoInicial;
	}

	public void setDataNascimentoInicial(LocalDate dataNascimentoInicial) {
		this.dataNascimentoInicial = dataNascimentoInicial;
	}

	public LocalDate getDataNascimentoFinal() {
		return dataNascimentoFinal;
	}

	public void setDataNascimentoFinal(LocalDate dataNascimentoFinal) {
		this.dataNascimentoFinal = dataNascimentoFinal;
	}
}
