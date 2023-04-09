package view.exemplos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;

public class TelaComDatePickerExterno {

	private JFrame frame;
	private JLabel lblData;
	
	//FONTE: https://github.com/LGoodDatePicker/LGoodDatePicker	
	private DatePickerSettings dateSettings;
	private DateTimePicker dataTeste;
	private JButton btnPegarData;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaComDatePickerExterno window = new TelaComDatePickerExterno();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaComDatePickerExterno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 219);
		frame.setTitle("Componente com data e hora");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblData = new JLabel("Data:");
		lblData.setBounds(10, 64, 40, 20);
		frame.getContentPane().add(lblData);

		// Configurações da parte de DATAS do componente
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		dataTeste = new DateTimePicker(dateSettings, null);
		dataTeste.setBounds(80, 60, 300, 30);
		frame.getContentPane().add(dataTeste);

		btnPegarData = new JButton("Criar data");
		btnPegarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Atributos próprios do componente datePicker (date e time)
				LocalDate dataSelecionada = dataTeste.getDatePicker().getDate();
				LocalTime horaSelecionada = dataTeste.getTimePicker().getTime();
				
				LocalDateTime dataComHora = LocalDateTime.of(dataSelecionada, horaSelecionada);

				JOptionPane.showMessageDialog(null, "Data selecionada: " + dataSelecionada.toString());
				JOptionPane.showMessageDialog(null, "Horário selecionado: " + horaSelecionada.toString());
				JOptionPane.showMessageDialog(null, "Data e hora selecionada: " + dataComHora.toString());
				
				//Exemplo com java.util.Date (antigo)
//				new Date(dataSelecionada.getYear(), dataSelecionada.getMonthValue(),
//						dataSelecionada.getDayOfMonth(), horaSelecionada.getHour(), horaSelecionada.getMinute(),
//						horaSelecionada.getSecond());
			}
		});
		btnPegarData.setBounds(138, 134, 181, 23);
		frame.getContentPane().add(btnPegarData);
	}
}
