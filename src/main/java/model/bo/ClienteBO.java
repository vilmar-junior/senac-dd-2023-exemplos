package model.bo;

import model.dao.telefonia.ClienteDAO;
import model.exception.CpfJaUtilizadoException;
import model.exception.EnderecoInvalidoException;
import model.vo.telefonia.Cliente;

public class ClienteBO {

	private ClienteDAO dao = new ClienteDAO();
	
	public Cliente inserir(Cliente novoCliente) throws CpfJaUtilizadoException, 
			EnderecoInvalidoException {
		if(dao.cpfJaUtilizado(novoCliente.getCpf())) {
			//Caso CPF já utilizado -> não salvar e devolver exceção
			//Não salvar -> lançar exceção
			throw new CpfJaUtilizadoException("CPF informado já foi utilizado");
		}
		
		if(novoCliente.getEndereco() == null || novoCliente.getEndereco().getId() == null) {
			throw new EnderecoInvalidoException("Endereço não informado");
		}

		//Caso CPF não utilizado -> salvar e devolver o cliente
		return dao.inserir(novoCliente);
	}
	
	public boolean excluir(int id) {
		//(i) não deixar excluir cliente que possua telefone associado
		
		//Criar exceção ClienteComTelefoneException
		
		//Caso cliente possua telefone(s): lançar ClienteComTelefoneException
		
		//Caso contrário: chamar dao.excluir(id)
		
		return false;
	}

}
