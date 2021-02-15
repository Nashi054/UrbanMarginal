package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class demarrage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demarrage frame = new demarrage();
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
	public demarrage() {
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStartServer = new JLabel("Start a server :");
		lblStartServer.setBounds(10, 11, 81, 14);
		contentPane.add(lblStartServer);
		
		/**
		 * Click to show arena screen
		 */
		JButton btnStartServer = new JButton("Start");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arene.main(null);
			}
		});
		btnStartServer.setBounds(205, 7, 89, 23);
		contentPane.add(btnStartServer);
		
		JLabel lblConnect = new JLabel("Connect an existing server :");
		lblConnect.setBounds(10, 36, 144, 14);
		contentPane.add(lblConnect);
		
		JLabel lblIPServer = new JLabel("IP server :");
		lblIPServer.setBounds(10, 61, 51, 14);
		contentPane.add(lblIPServer);
		
		textField = new JTextField("127.0.0.1");
		textField.setBounds(62, 59, 86, 17);
		contentPane.add(textField);
		textField.setColumns(10);
		
		/**
		 * Click to show character selection screen
		 */
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChoixJoueur.main(null);
			}
		});
		btnConnect.setBounds(205, 57, 89, 23);
		contentPane.add(btnConnect);
		
		/**
		 * Click to exit app
		 */
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(205, 91, 89, 23);
		contentPane.add(btnExit);
	}
}
