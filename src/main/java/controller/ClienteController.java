package controller;

import java.util.List;

import model.bo.ClienteBO;
import model.exception.CpfAlteradoException;
import model.exception.CpfJaUtilizadoException;
import model.exception.EnderecoInvalidoException;
import model.vo.telefonia.Cliente;

public class ClienteController {

	private ClienteBO bo = new ClienteBO();
	
	public Cliente inserir(Cliente novoCliente) throws CpfJaUtilizadoException, 
			EnderecoInvalidoException {
		
		//TODO validar o preenchimento dos campos obrigatórios
		return bo.inserir(novoCliente);
	}
	
	public boolean atualizar(Cliente clienteAlterado) throws EnderecoInvalidoException, CpfAlteradoException {
		//TODO validar o preenchimento dos campos obrigatórios
		return bo.atualizar(clienteAlterado);
	}
	
	public boolean excluir(int id) {
		return bo.excluir(id);
	}
	
	public Cliente consultarPorId(int id) {
		return bo.consultarPorId(id);
	}
	
	public List<Cliente> consultarTodos() {
		return bo.consultarTodos();
	}
}
