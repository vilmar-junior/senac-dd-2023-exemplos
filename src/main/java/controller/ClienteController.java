package controller;

import java.util.List;

import model.bo.ClienteBO;
import model.exception.CampoInvalidoException;
import model.exception.ClienteComTelefoneException;
import model.exception.CpfAlteradoException;
import model.exception.CpfJaUtilizadoException;
import model.exception.EnderecoInvalidoException;
import model.vo.telefonia.Cliente;

public class ClienteController {

	private ClienteBO bo = new ClienteBO();
	
	public Cliente inserir(Cliente novoCliente) throws CpfJaUtilizadoException, 
			EnderecoInvalidoException, CampoInvalidoException {

		this.validarCamposObrigatorios(novoCliente);
		return bo.inserir(novoCliente);
	}
	
	public boolean atualizar(Cliente clienteAlterado) throws EnderecoInvalidoException, CpfAlteradoException {
		//TODO validar o preenchimento dos campos obrigatórios
		return bo.atualizar(clienteAlterado);
	}
	
	private void validarCamposObrigatorios(Cliente c) throws CampoInvalidoException {
		String mensagemValidacao = "";
		
		if(c.getNome() == null || c.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inválido \n";
		}
		
		mensagemValidacao += validarCpf(c);
		
		if(c.getEndereco() == null) {
			mensagemValidacao += "Informe um endereço\n";
		}		
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}
	
	private String validarCpf(Cliente c) throws CampoInvalidoException {
		String validacao = "";
		
		if(c.getCpf() == null) {
			validacao += "Informe um CPF \n" ;
		}else {
			String cpfSemMascara = c.getCpf().replace(".", "");
			cpfSemMascara = c.getCpf().replace("-", "");
			c.setCpf(cpfSemMascara);
			if(c.getCpf().length() != 11) {
				validacao += "CPF deve possuir 11 dígitos\n" ;	
			}
			
//			try {
//				Integer.valueOf(c.getCpf());
//			} catch (NumberFormatException ex) {
//				
//				//TODO conferir
//				validacao += "CPF deve possuir somente números\n";
//			}
		}
		
		return validacao;
	}

	public boolean excluir(int id) throws ClienteComTelefoneException {
		return bo.excluir(id);
	}
	
	public Cliente consultarPorId(int id) {
		return bo.consultarPorId(id);
	}
	
	public List<Cliente> consultarTodos() {
		return bo.consultarTodos();
	}
}
