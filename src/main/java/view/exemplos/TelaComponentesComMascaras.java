package view.exemplos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

/**
 * 
 * @author Vilmar César Pereira Júnior
 *
 */
public class TelaComponentesComMascaras extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField formattedTextFieldCnpj;
	private JFormattedTextField formattedTextFieldCpf;
	private JFormattedTextField formattedTextFieldCep;
	private JFormattedTextField formattedTextFieldTelefone;
	private JFormattedTextField formattedTextFieldPlaca;
	private JNumberFormatField txtValorEmReais;
	private JTextArea txtAreaValores;
	private JLabel lblPlaca;
	private JLabel lblTelefone;
	private JLabel lblCpf;
	private JLabel lblCnpj;
	private JLabel lblCep;
	private JLabel lblValorEmReais;
	private JButton btnPegarValoresEmString;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraCnpj;
	private MaskFormatter mascaraTelefone;
	private MaskFormatter mascaraPlaca;
	private MaskFormatter mascaraCep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaComponentesComMascaras frame = new TelaComponentesComMascaras();
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
	public TelaComponentesComMascaras() {
		setTitle("Exemplos de Máscaras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCnpj = new MaskFormatter("##.###.###/####-##");
			mascaraTelefone = new MaskFormatter("(##)####-####");
			mascaraCep = new MaskFormatter("#####-###");
			mascaraPlaca = new MaskFormatter("UUU-####");
			
			//Força o componente a informar apenas o valor SEM máscara no getValue() 
			//FONTE: https://forums.oracle.com/ords/apexds/post/retrieve-unformatted-text-from-jformattedtextfield-2120
			mascaraCpf.setValueContainsLiteralCharacters(false);
			mascaraCnpj.setValueContainsLiteralCharacters(false);
			mascaraTelefone.setValueContainsLiteralCharacters(false);
			mascaraCep.setValueContainsLiteralCharacters(false);
			mascaraPlaca.setValueContainsLiteralCharacters(false);

			formattedTextFieldPlaca = new JFormattedTextField(mascaraPlaca);
			formattedTextFieldPlaca.setBounds(95, 22, 105, 20);
			contentPane.add(formattedTextFieldPlaca);

			lblPlaca = new JLabel("Placa:");
			lblPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPlaca.setBounds(10, 25, 75, 14);
			contentPane.add(lblPlaca);

			lblTelefone = new JLabel("Telefone:");
			lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTelefone.setBounds(10, 55, 75, 14);
			contentPane.add(lblTelefone);

			formattedTextFieldTelefone = new JFormattedTextField(mascaraTelefone);
			formattedTextFieldTelefone.setBounds(94, 53, 95, 20);
			contentPane.add(formattedTextFieldTelefone);

			lblCpf = new JLabel("CPF:");
			lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCpf.setBounds(199, 25, 61, 14);
			contentPane.add(lblCpf);

			lblCnpj = new JLabel("CNPJ:");
			lblCnpj.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCnpj.setBounds(199, 56, 61, 14);
			contentPane.add(lblCnpj);

			lblCep = new JLabel("CEP:");
			lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCep.setBounds(10, 86, 74, 14);
			contentPane.add(lblCep);

			formattedTextFieldCep = new JFormattedTextField(mascaraCep);
			formattedTextFieldCep.setBounds(94, 83, 95, 20);
			contentPane.add(formattedTextFieldCep);

			formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
			formattedTextFieldCpf.setBounds(270, 22, 154, 20);
			contentPane.add(formattedTextFieldCpf);

			formattedTextFieldCnpj = new JFormattedTextField(mascaraCnpj);
			formattedTextFieldCnpj.setBounds(270, 53, 154, 20);
			contentPane.add(formattedTextFieldCnpj);

			lblValorEmReais = new JLabel("R$:");
			lblValorEmReais.setHorizontalAlignment(SwingConstants.RIGHT);
			lblValorEmReais.setBounds(199, 83, 61, 14);
			contentPane.add(lblValorEmReais);

			txtValorEmReais = new JNumberFormatField(2);
			txtValorEmReais.setBounds(270, 86, 155, 20);
			contentPane.add(txtValorEmReais);
			
			btnPegarValoresEmString = new JButton("Pegar valores em String");
			btnPegarValoresEmString.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String mensagem = "Valores convertidos para String e sem as máscaras: \n";
					
					try {
						mensagem += "Placa: "     + mascaraPlaca.stringToValue(formattedTextFieldPlaca.getText()) + "\n";
						mensagem += "CPF: "       + mascaraCpf.stringToValue(formattedTextFieldCpf.getText()) + "\n";
						mensagem += "Telefone: "  + mascaraTelefone.stringToValue(formattedTextFieldTelefone.getText()) + "\n";
						mensagem += "CNPJ: "      + mascaraCnpj.stringToValue(formattedTextFieldCnpj.getText()) + "\n";
						mensagem += "CEP: "       + mascaraCep.stringToValue(formattedTextFieldCep.getText()) + "\n";
						mensagem += "Dinheiros: " + txtValorEmReais.getText() + "\n";
					} catch (ParseException e) {
						//JOptionPane.showMessageDialog(null,"Erro ao obter valores digitados", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					
					txtAreaValores.setText(mensagem);
				}
			});
			btnPegarValoresEmString.setBounds(100, 126, 234, 23);
			contentPane.add(btnPegarValoresEmString);
			
			txtAreaValores = new JTextArea();
			txtAreaValores.setEditable(false);
			txtAreaValores.setBounds(24, 160, 385, 180);
			contentPane.add(txtAreaValores);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
