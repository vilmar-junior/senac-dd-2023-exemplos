package view.telefonia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.EnderecoController;
import model.exception.CampoInvalidoException;
import model.vo.telefonia.Endereco;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Tela de cadastro/edição de endereço
 * 
 * O atributo 'endereco' que indica o que será feito
 * - está nulo: cadastro
 * - já veio preenchido no construtor: edição
 * 
 * @author Vilmar César Pereira Júnior
 *
 */
public class TelaCadastroEndereco {

	private JFrame frmCadastroDeEndereco;
	private JTextField txtCep;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblCep;
	private JLabel lblCidade;
	private JLabel lblNumero;
	private JLabel lblEstado;
	private JButton btnSalvar;
	private JComboBox cbEstado;
	
	//Objeto usado para armazenar o endereço que será criado ou editado
	private Endereco endereco;
	
	//TODO chamar API ou backend futuramente
	private String[] estados = {"PR", "RS", "SC"};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Inicia a tela com um endereço nulo
					TelaCadastroEndereco window = new TelaCadastroEndereco(null);
					window.frmCadastroDeEndereco.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param enderecoSelecionado 
	 */
	public TelaCadastroEndereco(Endereco enderecoSelecionado) {
		this.endereco = enderecoSelecionado;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeEndereco = new JFrame();
		frmCadastroDeEndereco.setTitle("Cadastro de endereço");
		frmCadastroDeEndereco.setBounds(100, 100, 380, 240);
		frmCadastroDeEndereco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeEndereco.getContentPane().setLayout(null);
		
		lblCep = new JLabel("CEP:");
		lblCep.setBounds(15, 15, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblCep);
		
		txtCep = new JTextField();
		txtCep.setBounds(60, 12, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtCep);
		txtCep.setColumns(10);
		
		lblRua = new JLabel("Rua:");
		lblRua.setBounds(15, 40, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblRua);
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(15, 65, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblBairro);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(15, 90, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblCidade);
		
		lblNumero = new JLabel("Número:");
		lblNumero.setBounds(15, 115, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblNumero);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(15, 140, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblEstado);
		
		txtRua = new JTextField();
		txtRua.setBounds(60, 37, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtRua);
		txtRua.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(60, 62, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(60, 87, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtCidade);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(60, 112, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtNumero);
		
		cbEstado = new JComboBox(estados);
		cbEstado.setToolTipText("Selecione");
		cbEstado.setSelectedIndex(-1);
		cbEstado.setBounds(60, 136, 300, 22);
		frmCadastroDeEndereco.getContentPane().add(cbEstado);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean edicao = false;
				if(endereco == null) {
					//Cadastro de novo endereço
					endereco = new Endereco();
				}else {
					//Edição de endereço passado por parâmetro no construtor
					edicao = true;
				}
				
				endereco.setCep(txtCep.getText());
				endereco.setRua(txtRua.getText());
				endereco.setNumero(txtNumero.getText());
				endereco.setCidade(txtCidade.getText());
				endereco.setEstado((String) cbEstado.getSelectedItem());
				endereco.setBairro(txtBairro.getText());
				
				EnderecoController controller = new EnderecoController();
				try {
					//Alterado aqui para contemplar tanto edição quanto cadastro de endereço
					if(edicao) {
						controller.atualizar(endereco);
					}else {
						controller.inserir(endereco);
						limparTela();
					}
					JOptionPane.showMessageDialog(null, "Endereço:" + (edicao ? " atualizado " : " criado ") + "com sucesso!",
														"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (CampoInvalidoException e) {
					JOptionPane.showMessageDialog(null,  "Preencha os seguintes campos: \n" + e.getMessage(), 
														 "Atenção", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(130, 170, 100, 23);
		frmCadastroDeEndereco.getContentPane().add(btnSalvar);
		
		//Preenche os campos na tela (binding)
		if(endereco != null) {
			txtCep.setText(endereco.getCep());
			txtRua.setText(endereco.getRua());
			txtNumero.setText(endereco.getNumero());
			txtCidade.setText(endereco.getCidade());
			txtBairro.setText(endereco.getBairro());
			
			cbEstado.setSelectedItem(endereco.getEstado());
		}
		
		frmCadastroDeEndereco.setVisible(true);
	}

	protected void limparTela() {
		this.endereco = null;
		txtCep.setText("");
		txtRua.setText("");
		txtNumero.setText("");
		txtCidade.setText("");
		txtBairro.setText("");
		cbEstado.setSelectedItem(null);
	}
}

