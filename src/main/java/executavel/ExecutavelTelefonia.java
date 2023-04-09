package executavel;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.ClienteController;
import controller.EnderecoController;
import model.bo.ClienteBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfJaUtilizadoException;
import model.exception.EnderecoInvalidoException;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;

public class ExecutavelTelefonia {

	public static void main(String[] args) {
		
		Endereco endereco1 = new EnderecoController().consultarPorId(1);
		
		
		ClienteController controladorDeClientes = new ClienteController();
		
		Cliente novoCliente = new Cliente();
		novoCliente.setNome("Mário");
		novoCliente.setCpf("19122233312");
		novoCliente.setEndereco(endereco1);
		novoCliente.setAtivo(true);
		novoCliente.setTelefones(new ArrayList());

		try {
			novoCliente = controladorDeClientes.inserir(novoCliente);
			
			JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso! Id gerado: " + novoCliente.getId(), 
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
			//Exemplo de captura de múltiplas exceções, válido apenas a partir do Java versão 7
		} catch (CpfJaUtilizadoException 
					| EnderecoInvalidoException 
					| CampoInvalidoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Erro", JOptionPane.ERROR_MESSAGE);
		} 
	}
}
