package view.telefonia;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.TelefoneController;
import model.exception.TelefoneJaUtilizadoException;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Telefone;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TelaCadastroTelefone {

	private JFrame frmNovoTelefone;
	private JLabel lblTipo;
	private JLabel lblNumero;
	private JLabel lblCliente;
	private JRadioButton rbMovel;
	private JRadioButton rbFixo;
	private JButton btnSalvar;
	private JComboBox cbClientes;

	private MaskFormatter mascaraTelefoneFixo;
	private MaskFormatter mascaraTelefoneMovel;
	private JFormattedTextField txtTelefoneFixo;
	private JFormattedTextField txtTelefoneMovel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroTelefone window = new TelaCadastroTelefone();
					window.frmNovoTelefone.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public TelaCadastroTelefone() throws ParseException {
		initialize();
		esconderTodosOsComponentes();
	}

	/**
	 * Método que será chamado ao iniciar a tela,
	 * escondendo todos os componentes exceto os 
	 * radiobuttons "Fixo" e "Móvel"
	 */
	private void esconderTodosOsComponentes() {
		lblNumero.setVisible(false);
		txtTelefoneFixo.setVisible(false);
		lblCliente.setVisible(false);
		cbClientes.setVisible(false);
		btnSalvar.setEnabled(false);
	}
	
	/**
	 * Mostra os componentes ao clicar em "Fixo" ou "Móvel"
	 */
	private void mostrarComponentesComuns() {
		lblNumero.setVisible(true);
		lblCliente.setVisible(true);
		cbClientes.setVisible(true);
		btnSalvar.setEnabled(true);
	}

	/**
	 * Método chamado no construtor da tela
	 * Cria e posiciona todos os componentes 
	 */
	private void initialize() throws ParseException {
		frmNovoTelefone = new JFrame();
		frmNovoTelefone.setTitle("Novo Telefone");
		frmNovoTelefone.setBounds(100, 100, 401, 251);
		frmNovoTelefone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoTelefone.getContentPane().setLayout(null);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(30, 30, 45, 14);
		frmNovoTelefone.getContentPane().add(lblTipo);
		
		lblNumero = new JLabel("Número");
		lblNumero.setBounds(30, 60, 45, 14);
		frmNovoTelefone.getContentPane().add(lblNumero);
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(30, 90, 45, 14);
		frmNovoTelefone.getContentPane().add(lblCliente);
		
		//Determina as máscaras para telefone. Exemplos:
		//Fixo: (48)3232-3232
		//Móvel:(48)98232-3232  
		mascaraTelefoneFixo = new MaskFormatter("(##)####-####");
		mascaraTelefoneMovel = new MaskFormatter("(##)9####-####");
		
		//Força o componente a informar apenas o valor SEM máscara 
		//FONTE: https://forums.oracle.com/ords/apexds/post/retrieve-unformatted-text-from-jformattedtextfield-2120
		mascaraTelefoneFixo.setValueContainsLiteralCharacters(false);
		mascaraTelefoneMovel.setValueContainsLiteralCharacters(false);
		
		rbMovel = new JRadioButton("Móvel");
		rbMovel.setBounds(260, 25, 100, 23);
		rbMovel.addActionListener(new ActionListener() {
			//Método de clique no radiobutton "Móvel"
			public void actionPerformed(ActionEvent arg0) {
				mostrarComponentesComuns();
				txtTelefoneMovel.setVisible(true);
				txtTelefoneFixo.setVisible(false);
			}
		});
		frmNovoTelefone.getContentPane().add(rbMovel);
		
		rbFixo = new JRadioButton("Fixo");
		rbFixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Método de clique no radiobutton "Móvel"
				mostrarComponentesComuns();
				txtTelefoneMovel.setVisible(false);
				txtTelefoneFixo.setVisible(true);
			}
		});
		rbFixo.setBounds(90, 26, 100, 23);
		frmNovoTelefone.getContentPane().add(rbFixo);
		
		//Usado para agrupar os radiobuttons "fixo" e "móvel"
		//tornando a seleção deles única
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbFixo);
		grupo.add(rbMovel);
		
		txtTelefoneFixo = new JFormattedTextField(mascaraTelefoneFixo);
		txtTelefoneFixo.setBackground(new Color(192, 192, 192));
		txtTelefoneFixo.setForeground(new Color(255, 0, 255));
		txtTelefoneFixo.setBounds(90, 60, 270, 20);
		frmNovoTelefone.getContentPane().add(txtTelefoneFixo);
		
		txtTelefoneMovel = new JFormattedTextField(mascaraTelefoneMovel);
		txtTelefoneMovel.setForeground(Color.BLUE);
		txtTelefoneMovel.setBounds(90, 60, 270, 20);
		txtTelefoneMovel.setVisible(false);
		frmNovoTelefone.getContentPane().add(txtTelefoneMovel);
		
		//Busca todos os clientes cadastrados no banco
		ClienteController cliController = new ClienteController();
		cbClientes = new JComboBox(cliController.consultarTodos().toArray());
		cbClientes.setBounds(90, 90, 270, 22);
		
		//Inicia sem preenchimento
		cbClientes.setSelectedItem(null); 
		frmNovoTelefone.getContentPane().add(cbClientes);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Telefone novoTelefone = new Telefone();
				novoTelefone.setMovel(rbMovel.isSelected());
				
				preencherNumeroDdd(novoTelefone);
				
				Cliente clienteSelecionado = (Cliente) cbClientes.getSelectedItem();
				if(clienteSelecionado != null) {
					novoTelefone.setIdCliente(clienteSelecionado.getId());
				}
				
				TelefoneController telController = new TelefoneController();
				try {
					telController.inserir(novoTelefone);
					JOptionPane.showMessageDialog(null, "Telefone salvo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (TelefoneJaUtilizadoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Atenção!", JOptionPane.ERROR_MESSAGE);
				}
				limparTela();
			}

			private void preencherNumeroDdd(Telefone novoTelefone) {
				String numeroCompletoDigitado = "";
				if(novoTelefone.isMovel()) {
					try {
						numeroCompletoDigitado = mascaraTelefoneMovel
								.stringToValue(txtTelefoneMovel.getText()) + "\n";
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Informe um número válido", "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}else {
					try {
						numeroCompletoDigitado = mascaraTelefoneFixo
								.stringToValue(txtTelefoneFixo.getText()) + "\n";
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Informe um número válido", "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				String ddd = numeroCompletoDigitado.substring(0,2);
				String numero = numeroCompletoDigitado.substring(2);
				
				novoTelefone.setDdd(ddd);
				novoTelefone.setNumero(numero);
			}
		});
		btnSalvar.setBounds(30, 123, 330, 62);
		frmNovoTelefone.getContentPane().add(btnSalvar);
	}

	/**
	 * Limpa todos os campos da tela (usado após salvar um telefone)
	 * */
	protected void limparTela() {
		this.rbFixo.setSelected(false);
		this.rbMovel.setSelected(false);
		this.txtTelefoneFixo.setText("");
		this.txtTelefoneMovel.setText("");
		this.cbClientes.setSelectedIndex(-1);
	}
}
