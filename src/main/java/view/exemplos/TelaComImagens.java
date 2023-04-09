package view.exemplos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class TelaComImagens extends JFrame {

	private JPanel contentPane;
	private JLabel lblImagem;
	private JLabel lblNomeArquivo;
	private File arquivoSelecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaComImagens frame = new TelaComImagens();
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
	public TelaComImagens() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArquivoSelecionado = new JLabel("Arquivo selecionado:");
		lblArquivoSelecionado.setBounds(10, 70, 147, 14);
		contentPane.add(lblArquivoSelecionado);
		
		JButton btnProcurarImagem = new JButton("Procurar imagem");
		btnProcurarImagem.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					arquivoSelecionado = fileChooser.getSelectedFile();
					lblNomeArquivo.setText(arquivoSelecionado.getAbsolutePath());
				}
			}
		});
		btnProcurarImagem.setBounds(163, 28, 177, 23);
		contentPane.add(btnProcurarImagem);
		
		JButton btnMostrarImagem = new JButton("Mostrar imagem");
		btnMostrarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arquivoSelecionado != null) {
		              String path = arquivoSelecionado.getAbsolutePath();
		              ImageIcon icon = new ImageIcon(path);
		              lblImagem.setIcon(icon);
				}
			}
		});
		btnMostrarImagem.setBounds(163, 108, 177, 23);
		contentPane.add(btnMostrarImagem);
		
		lblImagem = new JLabel();
		lblImagem.setBounds(10, 161, 493, 252);
		contentPane.add(lblImagem);
		
		lblNomeArquivo = new JLabel("");
		lblNomeArquivo.setBounds(173, 70, 330, 14);
		contentPane.add(lblNomeArquivo);
	}
}
