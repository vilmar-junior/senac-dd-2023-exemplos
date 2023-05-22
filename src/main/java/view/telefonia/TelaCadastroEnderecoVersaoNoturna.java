package view.telefonia;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.EnderecoController;
import model.exception.CampoInvalidoException;
import model.vo.telefonia.Endereco;

import java.awt.Color;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroEnderecoVersaoNoturna extends JFrame {

	private JPanel contentPane;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtMunicipio;
	private JTextField txtEstado;
	private JLabel lblCep;
	private JLabel lblRua;
	private JLabel lblNumero;
	private JLabel lblBairro;
	private JLabel lblMunicipio;
	private JLabel lblEstado;
	private JButton btnSalvar;

	private MaskFormatter mascaraCep;
	private JFormattedTextField txtCep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEnderecoVersaoNoturna frame = new TelaCadastroEnderecoVersaoNoturna();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroEnderecoVersaoNoturna() {
		setTitle("Cadastro de Endereço (V2)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 275);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(166dlu;default):grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblCep = new JLabel("Cep:");
		lblCep.setForeground(new Color(255, 255, 64));
		contentPane.add(lblCep, "2, 2");
		
		try {
			//define a máscara
			mascaraCep = new MaskFormatter("#####-###"); 
			//ajuste para devolver somente os números digitados
			mascaraCep.setValueContainsLiteralCharacters(false); 
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro ao criar máscara de Cep");
		}
		txtCep = new JFormattedTextField(mascaraCep);
		contentPane.add(txtCep, "4, 2, fill, default");
		
		lblRua = new JLabel("Rua:");
		lblRua.setForeground(new Color(255, 255, 64));
		contentPane.add(lblRua, "2, 4");
		
		txtRua = new JTextField();
		contentPane.add(txtRua, "4, 4, fill, default");
		txtRua.setColumns(10);
		
		lblNumero = new JLabel("Número:");
		lblNumero.setForeground(new Color(255, 255, 64));
		contentPane.add(lblNumero, "2, 6");
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		contentPane.add(txtNumero, "4, 6, fill, default");
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setForeground(new Color(255, 255, 64));
		contentPane.add(lblBairro, "2, 8");
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		contentPane.add(txtBairro, "4, 8, fill, default");
		
		lblMunicipio = new JLabel("Município:");
		lblMunicipio.setForeground(new Color(255, 255, 64));
		contentPane.add(lblMunicipio, "2, 10");
		
		txtMunicipio = new JTextField();
		txtMunicipio.setColumns(10);
		contentPane.add(txtMunicipio, "4, 10, fill, default");
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(new Color(255, 255, 64));
		contentPane.add(lblEstado, "2, 12");
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		contentPane.add(txtEstado, "4, 12, fill, default");
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Preencher um novo Endereco com o que foi informado na tela
				Endereco novoEndereco = new Endereco();
				novoEndereco.setBairro(txtBairro.getText());
				
				String cepInformado = "";
				try {
					//Obtém valor do CEP sem a máscara
					cepInformado = (String) mascaraCep.stringToValue(txtCep.getText());
				} catch (ParseException e1) {
					//silent
					JOptionPane.showMessageDialog(null, "Erro ao converter o CEP informado");
				}
				novoEndereco.setCep(cepInformado);
				novoEndereco.setNumero(txtNumero.getText());
				novoEndereco.setRua(txtRua.getText());
				novoEndereco.setCidade(txtMunicipio.getText());
				novoEndereco.setEstado(txtEstado.getText());
				
				//Instanciar um objeto de EnderecoController
				EnderecoController controller = new EnderecoController();
				try {
					controller.inserir(novoEndereco);
					JOptionPane.showMessageDialog(null, "Endereço cadastrado!");
				} catch (CampoInvalidoException e) {
					JOptionPane.showMessageDialog(null, "Informe os seguintes campos\n " + e.getMessage());
				}
			}
		});
		btnSalvar.setBackground(new Color(255, 255, 0));
		btnSalvar.setIcon(new ImageIcon(TelaCadastroEnderecoVersaoNoturna.class.getResource("/icones/icons8-endereço-48.png")));
		contentPane.add(btnSalvar, "2, 14, 3, 1");
	}

}
