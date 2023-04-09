package view.telefonia;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EnderecoController;
import model.vo.telefonia.Endereco;

public class TelaListagemEnderecos {

	private JFrame frmListagemDeEnderecos;
	private JTable tblEnderecos;
	private ArrayList<Endereco> enderecos;
	private String[] nomesColunas = { "#", "CEP", "Rua", "Número", "Bairro", "Cidade", "Estado" };
	private JButton btnBuscar;

	private void limparTabela() {
		tblEnderecos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		tblEnderecos = new JTable(tblEnderecos.getModel()) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
	}

	private void atualizarTabelaEnderecos() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tblEnderecos.getModel();

		for (Endereco e : enderecos) {
			Object[] novaLinhaDaTabela = new Object[4];

			novaLinhaDaTabela[0] = e.getId();
			novaLinhaDaTabela[1] = e.getCep();
			novaLinhaDaTabela[2] = e.getRua();
			novaLinhaDaTabela[3] = e.getNumero();
			novaLinhaDaTabela[4] = e.getBairro();
			novaLinhaDaTabela[5] = e.getCidade();
			novaLinhaDaTabela[6] = e.getEstado();

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemEnderecos window = new TelaListagemEnderecos();
					window.frmListagemDeEnderecos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemEnderecos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemDeEnderecos = new JFrame();
		frmListagemDeEnderecos.setTitle("Listagem de Endereços");
		frmListagemDeEnderecos.setBounds(100, 100, 700, 525);
		frmListagemDeEnderecos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemDeEnderecos.getContentPane().setLayout(null);

		btnBuscar = new JButton("Buscar Todos");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnderecoController controller = new EnderecoController();
				enderecos = (ArrayList<Endereco>) controller.consultarTodos();
				
				atualizarTabelaEnderecos();
			}
		});
		btnBuscar.setBounds(285, 20, 120, 35);
		frmListagemDeEnderecos.getContentPane().add(btnBuscar);

		tblEnderecos = new JTable();
		this.limparTabela();

		tblEnderecos.setBounds(15, 70, 655, 400);
		frmListagemDeEnderecos.getContentPane().add(tblEnderecos);
	}
}