package vue;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoixJoueur extends JFrame {

	private JPanel contentPane;
	private JLabel lblPersonnage;
	private JTextPane txtPseudo;
	private static Controle controle;
	private int numPerso;
	private static final int NBPERSO = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixJoueur frame = new ChoixJoueur(controle);
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
	public ChoixJoueur(Controle controle) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		
		/**
		 * Shows selected character
		 */
		lblPersonnage = new JLabel("");
		lblPersonnage.setBounds(180, 145, 44, 51);
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPersonnage);
		
		/**
		 * Launch the game with the selected character
		 */
		JButton btnGO = new JButton("");
		btnGO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		btnGO.setBounds(309, 194, 67, 70);
		contentPane.add(btnGO);
		btnGO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPseudo.getText() == "") {
					JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
					txtPseudo.requestFocus();
				}
				else {
					controle.evenementChoixJoueur(txtPseudo.getText(), numPerso);
				}
			}
		});
		btnGO.setOpaque(false);
		btnGO.setContentAreaFilled(false);
		btnGO.setBorderPainted(false);
		
		/**
		 * Select next character
		 */
		JButton btnFlecheDroite = new JButton("");
		btnFlecheDroite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		btnFlecheDroite.setOpaque(false);
		btnFlecheDroite.setContentAreaFilled(false);
		btnFlecheDroite.setBorderPainted(false);
		btnFlecheDroite.setBounds(302, 145, 33, 44);
		contentPane.add(btnFlecheDroite);
		btnFlecheDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("suivant");
				numPerso++;
				if (numPerso > NBPERSO) {
					numPerso = 1;
				}
				affichePerso(numPerso);
			}
		});
		
		/**
		 * Select previous character
		 */
		JButton btnFlecheGauche = new JButton("");
		btnFlecheGauche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		btnFlecheGauche.setOpaque(false);
		btnFlecheGauche.setContentAreaFilled(false);
		btnFlecheGauche.setBorderPainted(false);
		btnFlecheGauche.setBounds(63, 145, 33, 44);
		contentPane.add(btnFlecheGauche);
		btnFlecheGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("précédent");
				numPerso--;
				if (numPerso < 1) {
					numPerso = NBPERSO;
				}
				affichePerso(numPerso);
			}
		});
		
		/**
		 * Text area to enter the player's name
		 */
		txtPseudo = new JTextPane();
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
		
		this.controle = controle;
		numPerso = 1;
		affichePerso(numPerso);
	}
	
	/**
	 * Affiche l'image d'un personnage dans la fenêtre au centre
	 * @param numPerso numéro du personnage à afficher
	 */
	public void affichePerso(int numPerso) {
		String cheminPerso = "personnages/perso" + numPerso + "marche1d1.gif";
		URL resourcePerso = getClass().getClassLoader().getResource(cheminPerso);
		lblPersonnage.setIcon(new ImageIcon(resourcePerso));
	}
	
	/**
	 * affichage normal du curseur
	 */
	public void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * affichage du curseur en forme de doigt pointé
	 */
	public void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
