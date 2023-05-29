package view.telefonia;

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.github.lgooddatepicker.components.DatePicker;

import controller.ClienteController;
import model.exception.ClienteComTelefoneException;
import model.seletor.ClienteSeletor;
import model.vo.telefonia.Cliente;
import javax.swing.SwingConstants;

public class PainelListagemCliente extends JPanel {
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
	private JButton btnBuscar;
	private JButton btnGerarPlanilha;
	private JButton btnExcluir;
	private JLabel lblCpf;
	private JLabel lblNome;
	private JLabel lblDataNascimentoDe;
	private JLabel lblAte;
	private JLabel lblPaginacao;
	
	private ClienteController controller = new ClienteController();
	private Cliente clienteSelecionado;
	
	//Atributos para a PAGINAÇÃO
	private final int TAMANHO_PAGINA = 5;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private JButton btnVoltarPagina;
	private JButton btnAvancarPagina;
	private ClienteSeletor seletor = new ClienteSeletor();
	
	private void limparTabelaClientes() {
		tblClientes.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaClientes() {
		this.limparTabelaClientes();

		DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();

		for (Cliente c : clientes) {
			Object[] novaLinhaDaTabela = new Object[5];
			novaLinhaDaTabela[0] = c.getNome();
			novaLinhaDaTabela[1] = c.getCpf();
			novaLinhaDaTabela[2] = c.getEndereco().getEnderecoResumido();
			novaLinhaDaTabela[3] = c.getTelefones().size();
			novaLinhaDaTabela[4] = c.isAtivo() ? "Sim" : "Não";

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	public PainelListagemCliente() {
		this.setLayout(null);

		btnBuscar = new JButton("Buscar COM FILTROS");
		btnBuscar.setBackground(new Color(255, 128, 192));
		btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarClientesComFiltros();
				atualizarTabelaClientes();
			}
		});
		btnBuscar.setBounds(160, 125, 515, 35);
		this.add(btnBuscar);

		tblClientes = new JTable();
		this.limparTabelaClientes(); // Adicionei essa linha

		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblClientes.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					clienteSelecionado = clientes.get(indiceSelecionado - 1);
				} else {
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
				}
			}
		});
		tblClientes.setBounds(25, 164, 650, 133);
		this.add(tblClientes);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 25, 61, 16);
		this.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(160, 20, 240, 28);
		this.add(txtNome);
		txtNome.setColumns(10);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(410, 25, 40, 16);
		this.add(lblCpf);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
			txtCPF = new JFormattedTextField(mascaraCpf);
			txtCPF.setBounds(450, 19, 120, 28);
			this.add(txtCPF);
			txtCPF.setColumns(10);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		lblDataNascimentoDe = new JLabel("Data de dascimento. De:");
		lblDataNascimentoDe.setBounds(10, 60, 154, 10);
		this.add(lblDataNascimentoDe);

		dtNascimentoInicial = new DatePicker();
		dtNascimentoInicial.setBounds(160, 55, 515, 30);
		this.add(dtNascimentoInicial);

		lblAte = new JLabel("Até:");
		lblAte.setBounds(10, 90, 175, 10);
		this.add(lblAte);

		dtNascimentoFinal = new DatePicker();
		dtNascimentoFinal.setBounds(160, 90, 515, 30);
		this.add(dtNascimentoFinal);

		btnGerarPlanilha = new JButton("Gerar Planilha (Aula 12)");
		btnGerarPlanilha.setEnabled(false);
		btnGerarPlanilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para a planilha...");

				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();
					//TODO decomentar na aula 11
					//controller.gerarRelatorio(clientes, caminhoEscolhido);
				}
			}
		});
		btnGerarPlanilha.setBounds(25, 375, 200, 45);
		this.add(btnGerarPlanilha);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(250, 375, 200, 45);
		btnEditar.setEnabled(false);
		this.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(475, 375, 200, 45);
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do cliente selecionado?");
				
				if(opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						controller.excluir(clienteSelecionado.getId());
						JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
						clientes = (ArrayList<Cliente>) controller.consultarTodos();
						atualizarTabelaClientes();
					} catch (ClienteComTelefoneException e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		this.add(btnExcluir);
		
		btnVoltarPagina = new JButton("<< Voltar");
		btnVoltarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarClientesComFiltros();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnVoltarPagina.setEnabled(false);
		btnVoltarPagina.setBounds(175, 319, 111, 23);
		add(btnVoltarPagina);
		
		btnAvancarPagina = new JButton("Avançar >>");
		btnAvancarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				buscarClientesComFiltros();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnAvancarPagina.setBounds(386, 319, 111, 23);
		add(btnAvancarPagina);
		
		lblPaginacao = new JLabel("1 / " + totalPaginas);
		lblPaginacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaginacao.setBounds(283, 323, 105, 14);
		add(lblPaginacao);
		
		atualizarQuantidadePaginas();
	}
	
	private void atualizarQuantidadePaginas() {
		//Cálculo do total de páginas (poderia ser feito no backend)
		int totalRegistros = controller.contarTotalRegistrosComFiltros(seletor);
		
		//QUOCIENTE da divisão inteira
		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		
		//RESTO da divisão inteira
		if(totalRegistros % TAMANHO_PAGINA > 0) { 
			totalPaginas++;
		}
		
		lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
	}

	protected void buscarClientesComFiltros() {
		seletor = new ClienteSeletor();
		seletor.setLimite(TAMANHO_PAGINA);
		seletor.setPagina(paginaAtual);
		seletor.setNome(txtNome.getText());
		
		String cpfSemMascara;
		try {
			cpfSemMascara = (String) mascaraCpf.stringToValue(
					txtCPF.getText());
			seletor.setCpf(cpfSemMascara);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		seletor.setDataNascimentoInicial(dtNascimentoInicial.getDate());
		seletor.setDataNascimentoFinal(dtNascimentoFinal.getDate());
		clientes = (ArrayList<Cliente>) controller.consultarComFiltros(seletor);
		atualizarTabelaClientes();
		atualizarQuantidadePaginas();
	}

	//Torna o btnEditar acessível externamente à essa classe
	public JButton getBtnEditar() {
		return this.btnEditar;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
}
