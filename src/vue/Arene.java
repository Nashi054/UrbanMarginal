package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JScrollBar;

public class Arene extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Arene frame = new Arene();
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
	public Arene() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.getContentPane().setPreferredSize(new Dimension(800, 870));
		this.pack();
		
		/**
		 * Shows the arena background
		 */
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		String cheminFond = "fonds/fondarene.jpg";
		URL resourceFond = getClass().getClassLoader().getResource(cheminFond);
		lblFond.setIcon(new ImageIcon(resourceFond));
		
		/**
		 * Shows the chatbox
		 */
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textPane.setBounds(0, 600, 800, 20);
		contentPane.add(textPane);
		
		/**
		 * Shows the text area to chat with other players
		 */
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setFocusable(false);
		textArea.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textArea.setBounds(0, 620, 800, 250);
		contentPane.add(textArea);
	}
}
