package view.telefonia;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;

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

	private void esconderTodosOsComponentes() {
		lblNumero.setVisible(false);
		txtTelefoneFixo.setVisible(false);
		lblCliente.setVisible(false);
		cbClientes.setVisible(false);
		btnSalvar.setEnabled(false);
	}
	
	private void mostrarComponentesComuns() {
		lblNumero.setVisible(true);
		lblCliente.setVisible(true);
		cbClientes.setVisible(true);
		btnSalvar.setEnabled(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
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
		
		mascaraTelefoneFixo = new MaskFormatter("(##)####-####");
		mascaraTelefoneMovel = new MaskFormatter("(##)9####-####");
		mascaraTelefoneFixo.setValueContainsLiteralCharacters(false);
		mascaraTelefoneMovel.setValueContainsLiteralCharacters(false);
		
		rbMovel = new JRadioButton("Móvel");
		rbMovel.setBounds(260, 25, 100, 23);
		rbMovel.addActionListener(new ActionListener() {
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
				mostrarComponentesComuns();
				txtTelefoneMovel.setVisible(false);
				txtTelefoneFixo.setVisible(true);
			}
		});
		rbFixo.setBounds(90, 26, 100, 23);
		frmNovoTelefone.getContentPane().add(rbFixo);
		
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
		
		ClienteController cliController = new ClienteController();
		cbClientes = new JComboBox(cliController.consultarTodos().toArray());
		cbClientes.setBounds(90, 90, 270, 22);
		cbClientes.setSelectedItem(null); //Inicia sem preenchimento
		frmNovoTelefone.getContentPane().add(cbClientes);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(30, 123, 330, 62);
		frmNovoTelefone.getContentPane().add(btnSalvar);
	}
}
