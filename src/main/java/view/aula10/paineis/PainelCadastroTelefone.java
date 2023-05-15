package view.aula10.paineis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import controller.TelefoneController;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Telefone;

public class PainelCadastroTelefone extends JPanel {
	private JTextField txtCodigoPais;
	private JTextField txtDdd;
	private JTextField txtNumero;
	private JComboBox cbClientes;
	JCheckBox chkMovel;
	private JButton btnSalvar;
	private JButton btnLimpar;

	/**
	 * Create the panel.
	 */
	public PainelCadastroTelefone() {
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(115dlu;default):grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblCodigoPais = new JLabel("Código do país");
		add(lblCodigoPais, "4, 4");

		txtCodigoPais = new JTextField();
		add(txtCodigoPais, "8, 4");
		txtCodigoPais.setColumns(10);

		JLabel lblDdd = new JLabel("DDD");
		add(lblDdd, "4, 8");

		txtDdd = new JTextField();
		add(txtDdd, "8, 8, fill, default");
		txtDdd.setColumns(10);

		JLabel lblNumero = new JLabel("Número");
		add(lblNumero, "4, 12");

		txtNumero = new JTextField();
		add(txtNumero, "8, 12, fill, default");
		txtNumero.setColumns(10);

		JLabel lblDono = new JLabel("Dono (opcional)");
		add(lblDono, "4, 16");

		controller.ClienteController controladorClientes = new ClienteController();
		ArrayList<Cliente> clientes = (ArrayList<Cliente>) controladorClientes.consultarTodos();
		cbClientes = new JComboBox(clientes.toArray());
		cbClientes.setSelectedIndex(-1);
		add(cbClientes, "8, 16, fill, default");

		chkMovel = new JCheckBox("Móvel");
		add(chkMovel, "8, 20");

		btnSalvar = new JButton("Salvar");
		add(btnSalvar, "8, 22");

		btnLimpar = new JButton("Limpar");
		add(btnLimpar, "8, 24");

	}
}
