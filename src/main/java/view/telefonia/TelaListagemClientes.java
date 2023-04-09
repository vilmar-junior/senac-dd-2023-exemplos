package view.telefonia;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;

import controller.ClienteController;
import model.vo.telefonia.Cliente;

public class TelaListagemClientes {

	private JFrame frmListagemDeClientes;
	private JTable tblClientes;
	private ArrayList<Cliente> clientes;
	private String[] nomesColunas = { "Nome", "CPF", "Endereço", "Total de Telefones", "Ativo?" };
	private JTextField txtNome;
	private MaskFormatter mascaraCpf;
	private JFormattedTextField txtCPF;
	
	//componentes externos -> dependência "LGoodDatePicker" foi adicionada no pom.xml
	private DatePicker dtNascimentoInicial;
	private DatePicker dtNascimentoFinal;
	private JButton btnEditar;
	private JLabel lblCpf;
	private JButton btnBuscar;
	private JLabel lblNome;
	private JLabel lblDataNascimentoDe;
	private JLabel lblAte;

	private void limparTabelaClientes() {
		tblClientes.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		tblClientes = new JTable(tblClientes.getModel()) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
	}

	private void atualizarTabelaClientes() {
		this.limparTabelaClientes();

		DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();

		for (Cliente c : clientes) {
			Object[] novaLinhaDaTabela = new Object[4];

			novaLinhaDaTabela[0] = c.getNome();
			novaLinhaDaTabela[1] = c.getCpf();
			novaLinhaDaTabela[2] = c.getEndereco().getEnderecoResumido();
			novaLinhaDaTabela[3] = c.getTelefones().size();
			novaLinhaDaTabela[4] = c.isAtivo() ? "Sim" : "Não";

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
					TelaListagemClientes window = new TelaListagemClientes();
					window.frmListagemDeClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemClientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemDeClientes = new JFrame();
		frmListagemDeClientes.setTitle("Listagem de Clientes");
		frmListagemDeClientes.setBounds(100, 100, 700, 600);
		frmListagemDeClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemDeClientes.getContentPane().setLayout(null);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO descomentar na aula 11
//				ClienteSeletor seletor = new ClienteSeletor();
//				seletor.setNome(txtNome.getText());
//				seletor.setSobrenome(txtSobrenome.getText());
//				seletor.setDataNascimentoInicial(dtNascimentoInicial.getDate());
//				seletor.setDataNascimentoFinal(dtNascimentoFinal.getDate());

				ClienteController controller = new ClienteController();
//				clientes = controller.listarClientes(seletor);

				atualizarTabelaClientes();
			}
		});
		btnBuscar.setBounds(285, 125, 120, 35);
		frmListagemDeClientes.getContentPane().add(btnBuscar);

		tblClientes = new JTable();
		this.limparTabelaClientes(); // Adicionei essa linha

		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblClientes.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnEditar.setEnabled(true);
				} else {
					btnEditar.setEnabled(false);
				}
			}
		});
		tblClientes.setBounds(25, 164, 650, 328);
		frmListagemDeClientes.getContentPane().add(tblClientes);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 25, 61, 16);
		frmListagemDeClientes.getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(150, 20, 250, 28);
		frmListagemDeClientes.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(410, 25, 40, 16);
		frmListagemDeClientes.getContentPane().add(lblCpf);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			txtCPF = new JFormattedTextField(mascaraCpf);
			txtCPF.setBounds(450, 19, 120, 28);
			frmListagemDeClientes.getContentPane().add(txtCPF);
			txtCPF.setColumns(10);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		lblDataNascimentoDe = new JLabel("Data de dascimento. De:");
		lblDataNascimentoDe.setBounds(25, 60, 175, 10);
		frmListagemDeClientes.getContentPane().add(lblDataNascimentoDe);

		dtNascimentoInicial = new DatePicker();
		dtNascimentoInicial.setBounds(150, 55, 450, 30);
		frmListagemDeClientes.getContentPane().add(dtNascimentoInicial);

		lblAte = new JLabel("Até:");
		lblAte.setBounds(25, 90, 175, 10);
		frmListagemDeClientes.getContentPane().add(lblAte);

		dtNascimentoFinal = new DatePicker();
		dtNascimentoFinal.setBounds(150, 90, 450, 30);
		frmListagemDeClientes.getContentPane().add(dtNascimentoFinal);

		JButton btnGerarPlanilha = new JButton("Gerar Planilha (Aula 12)");
		btnGerarPlanilha.setEnabled(false);
		
		btnGerarPlanilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para a planilha...");

				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();

					ClienteController controller = new ClienteController();
					//TODO decomentar na aula 11
					//controller.gerarRelatorio(clientes, caminhoEscolhido);
				}
			}
		});
		btnGerarPlanilha.setBounds(120, 500, 200, 45);
		frmListagemDeClientes.getContentPane().add(btnGerarPlanilha);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO exemplo de lógica para edição
				int linhaSelecionadaNaTabela = tblClientes.getSelectedRow();
				Cliente clienteSelecionado = clientes.get(linhaSelecionadaNaTabela - 1);

				JOptionPane.showMessageDialog(null, "Chamar a tela de edição e passar o objeto clienteSelecionado...");
			}
		});
		btnEditar.setBounds(350, 500, 200, 45);
		btnEditar.setEnabled(false);
		frmListagemDeClientes.getContentPane().add(btnEditar);
	}
}