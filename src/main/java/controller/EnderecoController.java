package controller;

import java.util.List;

import model.bo.EnderecoBO;
import model.exception.CampoInvalidoException;
import model.exception.EnderecoInvalidoException;
import model.vo.telefonia.Endereco;
import model.vo.telefonia.Telefone;

public class EnderecoController {

	private EnderecoBO bo = new EnderecoBO();
	
	public Endereco inserir(Endereco novoEndereco) throws CampoInvalidoException {
		validarCamposObrigatorios(novoEndereco);
		
		return bo.inserir(novoEndereco);
	}
	
	private void validarCamposObrigatorios(Endereco endereco) throws CampoInvalidoException {
		String mensagemValidacao = "";
		
		mensagemValidacao += validarString(endereco.getCep(), "cep");
		mensagemValidacao += validarString(endereco.getRua(), "rua");
		mensagemValidacao += validarString(endereco.getNumero(), "número");
		mensagemValidacao += validarString(endereco.getBairro(), "bairro");
		mensagemValidacao += validarString(endereco.getCidade(), "cidade");
		mensagemValidacao += validarString(endereco.getEstado(), "estado");
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}
	
	
	private String validarString(String texto, String nomeCampo) {
		boolean valido = (texto != null) && !texto.trim().isEmpty();
		
		if(valido) {
			return "";
		}else {
			return 	"- " + nomeCampo + "\n" ;
		}
	}
	

	public boolean atualizar(Endereco enderecoAlterado) throws CampoInvalidoException{
		validarCamposObrigatorios(enderecoAlterado);
		return bo.atualizar(enderecoAlterado);
	}
	
	public boolean excluir(int id) throws EnderecoInvalidoException {
		return bo.excluir(id);
	}
	
	public Endereco consultarPorCep(String cep) {
		return bo.consultarPorCep(cep);
	}
	
	public Endereco consultarPorId(int id) {
		return bo.consultarPorId(id);
	}
	
	public List<Endereco> consultarTodos() {
		return bo.consultarTodos();
	}
}
