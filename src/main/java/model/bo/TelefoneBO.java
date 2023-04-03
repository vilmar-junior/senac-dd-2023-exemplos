package model.bo;

import java.util.List;

import model.dao.telefonia.TelefoneDAO;
import model.vo.telefonia.Telefone;

public class TelefoneBO {

	private TelefoneDAO dao = new TelefoneDAO();
	
	public Telefone inserir(Telefone novoTelefone) {
		novoTelefone.setAtivo(novoTelefone.getIdCliente() != null);
		
		return dao.inserir(novoTelefone);
	}
	
	public boolean atualizar(Telefone telefoneAlterado) {
		telefoneAlterado.setAtivo(telefoneAlterado.getIdCliente() != null);

		return dao.atualizar(telefoneAlterado);
	}
	
	public boolean excluir(int id) {
		return dao.excluir(id);
	}
	
	public Telefone consultarPorId(int id) {
		return dao.consultarPorId(id);
	}
	
	public List<Telefone> consultarTodos() {
		return dao.consultarTodos();
	}
}
