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

import controleur.Global;

import javax.swing.UIManager;
import javax.swing.JScrollBar;

public class Arene extends JFrame implements Global {

	private JPanel contentPane;
	private JPanel jpnMurs;
	private JPanel jpnJeu;

	public JPanel getJpnJeu() {
		return jpnJeu;
	}

	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}

	public JPanel getJpnMurs() {
		return jpnMurs;
	}

	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}
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
		 * Shows the players' characters
		 */
		jpnJeu = new JPanel();
		jpnJeu.setOpaque(false);
		jpnJeu.setBounds(0, 0, 800, 600);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);
		
		/**
		 * Shows the walls
		 */
		jpnMurs = new JPanel();
		jpnMurs.setOpaque(false);
		jpnMurs.setBounds(0, 0, 800, 600);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
		
		/**
		 * Shows the arena background
		 */
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		URL resourceFond = getClass().getClassLoader().getResource(CHEMINFOND);
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
	
	/**
	 * Méthode d'ajout des murs à l'arène
	 * @param mur objet contenant un mur
	 */
	public void ajoutMurs(Object mur) {
		jpnMurs.add((JLabel)mur);
		jpnMurs.repaint();
	}
	
	/**
	 * Méthode d'ajout d'un personnage joueur
	 * @param jLabel
	 */
	public void ajoutJLabelJeu(JLabel jeu) {
		jpnJeu.add((JLabel)jeu);
		jpnJeu.repaint();
	}
}
