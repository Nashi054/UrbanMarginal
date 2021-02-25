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

import controleur.Controle;
import controleur.Global;

import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Arene extends JFrame implements Global {
	
	/**
	 * Frame contenant les objets de l'arène
	 */
	private JPanel contentPane;
	/**
	 * Frame des murs de l'arène
	 */
	private JPanel jpnMurs;
	/**
	 * Frame des personnages dans l'arène
	 */
	private JPanel jpnJeu;
	/**
	 * Zone d'affichage du chat
	 */
	private JTextArea txtChat;
	/**
	 * Objet contrôleur
	 */
	private Controle controle;
	/**
	 * Permet de vérifier s'il s'agit de l'arène d'un serveur ou d'un client
	 */
	private Boolean client;

	/**
	 * getter sur txtChat
	 * @return txtChat
	 */
	public String getTxtChat() {
		return txtChat.getText();
	}
	/**
	 * setter sur txtChat
	 * @param txtChat
	 */
	public void setTxtChat(String txtChat) {
		this.txtChat.setText(txtChat);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
	
	/**
	 * getter sur jpnJeu
	 * @return jpnJeu
	 */
	public JPanel getJpnJeu() {
		return jpnJeu;
	}
	/**
	 * setter sur jpnJeu
	 * @param jpnJeu
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}

	/**
	 * getter sur jpnMurs
	 * @return jpnMurs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	/**
	 * setter sur jpnMurs
	 * @param jpnMurs
	 */
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}

	/**
	 * Create the frame.
	 */
	public Arene(Controle controle, String client) {
		
		if (client.contains(CLIENT)) {
			this.client = true;
		}
		else if (client.contains(SERVEUR)) {
			this.client = false;
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.getContentPane().setPreferredSize(new Dimension(800, 870));
		this.pack();
		
		/**
		 * Shows the characters
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
		
		if (this.client == true) {
			/**
		 	* Shows the chatbox
		 	*/
			JTextPane txtSaisie = new JTextPane();
			txtSaisie.setAutoscrolls(false);
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (txtSaisie.getText() != "") {
							controle.evenementArene(txtSaisie.getText());
							txtSaisie.setText(null);
						}
					}
				}
			});
			txtSaisie.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			txtSaisie.setBounds(0, 600, 800, 20);
			contentPane.add(txtSaisie);
		}
		
		/**
		 * Shows the scrollbar of the chat box
		 */
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 245);
		contentPane.add(jspChat);
		
		/**
		 * Shows the text area to chat with other players
		 */
		txtChat = new JTextArea();
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);
		
		this.controle = controle;
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
	
	/**
	 * Ajoute la phrase saisie dans la fenêtre de chat
	 * @param phrase
	 */
	public void ajoutTchat(String phrase) {
		setTxtChat(getTxtChat() + phrase + "\r\n");
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}
}
