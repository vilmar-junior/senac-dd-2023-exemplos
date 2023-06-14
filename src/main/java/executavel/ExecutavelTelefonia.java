package executavel;

import java.util.List;

import javax.swing.JOptionPane;

import controller.ClienteController;
import model.gerador.GeradorPlanilha;
import model.vo.telefonia.Cliente;

public class ExecutavelTelefonia {

	public static void main(String[] args) {
		
		List<Cliente> clientes = new ClienteController().consultarTodos(); 
		String destinoPlanilhaClientes = "C:\\Windows\\planilhaClientes";
		
		GeradorPlanilha gerador = new GeradorPlanilha();
		String resultado = gerador.gerarPlanilhaClientes(clientes, destinoPlanilhaClientes);
		
		JOptionPane.showMessageDialog(null, resultado);
	}
}
