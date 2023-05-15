package view.telefonia;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import controller.TelefoneController;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Telefone;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PainelListagemTelefone extends JPanel {
	private JTable tblTelefones;
	private String[] nomesColunas = {  "Número Completo", "Tipo", "Nome Cliente", "Ativo?" };
	private List<Telefone> telefones = new ArrayList<>();
	private TelefoneController controller = new TelefoneController();
	
	private void limparTabela() {
		tblTelefones.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
	
	private void atualizarTabela() {
		this.limparTabela();
		

		DefaultTableModel model = (DefaultTableModel) tblTelefones.getModel();

		ClienteController clienteController = new ClienteController();
		for (Telefone t : this.telefones) {
			//TODO melhorar para usar DTO
			String nomeCliente = "Sem cliente";
			if(t.getIdCliente() != null) {
				Cliente clienteBuscado = clienteController.consultarPorId(t.getIdCliente());
				
				if(clienteBuscado != null) {
					nomeCliente = clienteBuscado.getNome();
				}
			}
			
			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = "("+ t.getDdd() + ") " + t.getNumero();
			novaLinhaDaTabela[1] = t.isMovel() ? "Móvel" : "Fixo";
			novaLinhaDaTabela[2] = nomeCliente;
			novaLinhaDaTabela[3] = t.isAtivo() ? "Sim" : "Não";

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void buscarTelefones() {
		this.telefones = controller.consultarTodos();
		this.atualizarTabela();
	}

	public PainelListagemTelefone() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		
		tblTelefones = new JTable();
		add(tblTelefones, "2, 4, fill, fill");
		buscarTelefones();
	}
}
