package view.aula10.paineis;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PainelAzul extends JPanel {

	private JLabel lblLabelBoasVindas;
	private JButton btnGerarRelatorioAzul;

	/**
	 * Create the panel.
	 */
	public PainelAzul() {
		setBackground(new Color(0, 0, 255));
		setLayout(null);
		
		lblLabelBoasVindas = new JLabel("Painel Azul");
		lblLabelBoasVindas.setForeground(new Color(255, 255, 255));
		lblLabelBoasVindas.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabelBoasVindas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLabelBoasVindas.setBounds(30, 144, 373, 67);
		add(lblLabelBoasVindas);
		
		btnGerarRelatorioAzul = new JButton("\"Gerar relatório\" e mudar para painel amarelo");
		btnGerarRelatorioAzul.setForeground(new Color(0, 0, 0));
		btnGerarRelatorioAzul.setBackground(new Color(255, 255, 128));
		
		//Faz o botão mudar de cor de fato
		btnGerarRelatorioAzul.setOpaque(true);
		btnGerarRelatorioAzul.setBounds(30, 222, 410, 49);
		add(btnGerarRelatorioAzul);

	}
	
	public JButton getBtnGerarRelatorioAzul() {
		return this.btnGerarRelatorioAzul;
	}
}
