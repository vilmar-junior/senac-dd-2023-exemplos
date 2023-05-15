package view.aula10;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class TelaInternaCadastroCliente extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtSobrenome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaCadastroCliente frame = new TelaInternaCadastroCliente();
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
	public TelaInternaCadastroCliente() {
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setTitle("Cadastrar cliente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "6, 8");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "10, 8, fill, default");
		txtNome.setColumns(10);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		getContentPane().add(lblSobrenome, "6, 12");

		txtSobrenome = new JTextField();
		getContentPane().add(txtSobrenome, "10, 12, fill, bottom");
		txtSobrenome.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		getContentPane().add(btnSalvar, "10, 20");

	}

}
