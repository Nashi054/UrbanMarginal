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
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChoixJoueur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixJoueur frame = new ChoixJoueur();
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
	public ChoixJoueur() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		
		/**
		 * Launch the game with the selected character
		 */
		JButton btnGO = new JButton("");
		btnGO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arene.main(null);
			}
		});
		btnGO.setOpaque(false);
		btnGO.setContentAreaFilled(false);
		btnGO.setBorderPainted(false);
		
		/**
		 * Select next character
		 */
		JButton btnFlecheDroite = new JButton("");
		btnFlecheDroite.setOpaque(false);
		btnFlecheDroite.setContentAreaFilled(false);
		btnFlecheDroite.setBorderPainted(false);
		btnFlecheDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("suivant");
			}
		});
		
		/**
		 * Select previous character
		 */
		JButton btnFlecheGauche = new JButton("");
		btnFlecheGauche.setOpaque(false);
		btnFlecheGauche.setContentAreaFilled(false);
		btnFlecheGauche.setBorderPainted(false);
		btnFlecheGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("précédent");
			}
		});
		
		/**
		 * Shows selected character
		 */
		JLabel lblPersonnage = new JLabel("");
		lblPersonnage.setBounds(180, 145, 44, 51);
		contentPane.add(lblPersonnage);
		btnFlecheGauche.setBounds(63, 145, 33, 44);
		contentPane.add(btnFlecheGauche);
		btnFlecheDroite.setBounds(302, 145, 33, 44);
		contentPane.add(btnFlecheDroite);
		btnGO.setBounds(309, 194, 67, 70);
		contentPane.add(btnGO);
		String cheminPerso = "personnages/perso1marche1d1.gif";
		URL resourcePerso = getClass().getClassLoader().getResource(cheminPerso);
		lblPersonnage.setIcon(new ImageIcon(resourcePerso));
		
		/**
		 * Text area to enter the player's name
		 */
		JTextPane txtPseudo = new JTextPane();
		txtPseudo.setBounds(144, 249, 118, 15);
		contentPane.add(txtPseudo);
		
		/**
		 * Shows the screen background
		 */
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		contentPane.add(lblFond);
		String cheminFond = "fonds/fondchoix.jpg";
		URL resourceFond = getClass().getClassLoader().getResource(cheminFond);
		lblFond.setIcon(new ImageIcon(resourceFond));
	}
}
