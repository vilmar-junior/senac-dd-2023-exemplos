package view.aula10;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.aula10.paineis.PainelAmarelo;
import view.aula10.paineis.PainelAzul;
import view.aula10.paineis.PainelVerde;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipalAula10Noturno extends JFrame {

	private JPanel contentPane;
	
	private PainelAmarelo painelAmarelo;
	private PainelAzul painelAzul;
	private PainelVerde painelVerde;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalAula10Noturno frame = new TelaPrincipalAula10Noturno();
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
	public TelaPrincipalAula10Noturno() {
		setTitle("Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBarCerta = new JMenuBar();
		setJMenuBar(menuBarCerta);
		
		//Inicia a tela já maximizada
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenu menuRelatorios = new JMenu("Relatórios");
		menuRelatorios.setIcon(new ImageIcon(TelaPrincipalAula10Noturno.class.getResource("/icones/icons8-gráfico-combinado.png")));
		menuBarCerta.add(menuRelatorios);
		
		JMenuItem mntmRelatorioVerde = new JMenuItem("Relatório Verde");
		mntmRelatorioVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painelVerde = new PainelVerde();
				setContentPane(painelVerde);
				revalidate();
			}
		});
		mntmRelatorioVerde.setIcon(new ImageIcon(TelaPrincipalAula10Noturno.class.getResource("/icones/icons8-mais-20.png")));
		menuRelatorios.add(mntmRelatorioVerde);
		
		JMenuItem mntmRelatorioAzul = new JMenuItem("Relatório Azul");
		mntmRelatorioAzul.setIcon(new ImageIcon(TelaPrincipalAula10Noturno.class.getResource("/icones/icons8-lista-20.png")));
		menuRelatorios.add(mntmRelatorioAzul);
		mntmRelatorioAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelAzul = new PainelAzul();
				
				//Registra o evento de clique no botão btnGerarRelatorioAzul do PainelAzul
				painelAzul.getBtnGerarRelatorioAzul().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//"Gerar o relatório" --> chamar um controller para isso
						
						//Trocar para o painel amarelo
						painelAmarelo = new PainelAmarelo();
						setContentPane(painelAmarelo);
						revalidate();
					}
				});
				
				setContentPane(painelAzul);
				revalidate();
			}
		});
		
		//Inicia a tela com o painel amarelo
		painelAmarelo = new PainelAmarelo();
		setContentPane(painelAmarelo);
		
		//Atualiza a tela
		revalidate();
	}
}