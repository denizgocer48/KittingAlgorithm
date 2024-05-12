import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.JButton;

public class TasarýmFormülleri {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TasarýmFormülleri window = new TasarýmFormülleri();
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
	public TasarýmFormülleri() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 880, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("S\u0131kl\u0131k Hesaplama");
		lblNewLabel.setBounds(10, 10, 124, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("K Sabiti: ");
		lblNewLabel_1.setBounds(10, 33, 85, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(133, 30, 34, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sertlik Fakt\u00F6r\u00FC(V): ");
		lblNewLabel_2.setBounds(10, 56, 110, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(133, 53, 34, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(133, 76, 34, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("K\u0131vr\u0131m Fakt\u00F6r\u00FC(k):");
		lblNewLabel_3.setBounds(10, 79, 124, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u0130plik Numaras\u0131(N): ");
		lblNewLabel_4.setBounds(185, 33, 110, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(306, 30, 34, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u00D6rg\u00FC Fakt\u00F6r\u00FC(Fw):");
		lblNewLabel_5.setBounds(185, 56, 110, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(306, 53, 34, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("S\u0131kl\u0131k Hesapla");
		btnNewButton.setBounds(177, 75, 118, 21);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	sýklýkHesaplama();
            }
        });
		
		textField_5 = new JTextField();
		textField_5.setBounds(306, 76, 76, 19);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Gramaj Hesaplama");
		lblNewLabel_6.setBounds(10, 152, 110, 13);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("K Sabiti: ");
		lblNewLabel_7.setBounds(10, 178, 85, 13);
		frame.getContentPane().add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setBounds(133, 175, 34, 19);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("S\u0131kl\u0131k:");
		lblNewLabel_8.setBounds(10, 201, 45, 13);
		frame.getContentPane().add(lblNewLabel_8);
		
		textField_7 = new JTextField();
		textField_7.setBounds(133, 198, 34, 19);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("\u0130plik Numaras\u0131(N):");
		lblNewLabel_9.setBounds(185, 178, 110, 13);
		frame.getContentPane().add(lblNewLabel_9);
		
		textField_8 = new JTextField();
		textField_8.setBounds(321, 175, 34, 19);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Gramaj Hesapla");
		btnNewButton_1.setBounds(177, 197, 130, 21);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gramajHesapla();
            }
        });
		
		textField_9 = new JTextField();
		textField_9.setBounds(321, 198, 61, 19);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);
	}
	private void sýklýkHesaplama() {
        int kSabiti = Integer.parseInt(textField.getText());
        int sertlikFaktoru = Integer.parseInt(textField_1.getText());
        int kivrimFaktoru = Integer.parseInt(textField_2.getText());
        int iplikNumarasi = Integer.parseInt(textField_3.getText());
        int orguFaktoru = Integer.parseInt(textField_4.getText());

        double sonuc = (double) (kSabiti * sertlikFaktoru * kivrimFaktoru * iplikNumarasi * Math.sqrt(orguFaktoru));
        DecimalFormat df = new DecimalFormat("#.###");
        String sonucStr = df.format(sonuc);

        textField_5.setText(String.valueOf(sonucStr));
    }
	private void gramajHesapla() {
        int kSabiti = Integer.parseInt(textField_6.getText());
        int sýklýk = Integer.parseInt(textField_7.getText());
        int iplikNumarasý = Integer.parseInt(textField_8.getText());

        double sonuc = (double) (200 * kSabiti * sýklýk / iplikNumarasý);
        DecimalFormat df = new DecimalFormat("#.###");
        String sonucStr = df.format(sonuc);

        textField_9.setText(String.valueOf(sonucStr));
    }
}
