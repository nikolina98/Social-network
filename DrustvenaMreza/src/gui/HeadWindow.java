package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import crud.Crud;
import model.Korisnik;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class HeadWindow {

	private JFrame frmSocialNetwork;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeadWindow window = new HeadWindow();
					window.frmSocialNetwork.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HeadWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSocialNetwork = new JFrame();
		frmSocialNetwork.setTitle("SOCIAL NETWORK - NEW EXPERIENCE");
		frmSocialNetwork.setBounds(100, 100, 717, 545);
		frmSocialNetwork.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSocialNetwork.getContentPane().setLayout(null);
		frmSocialNetwork.setContentPane(new JLabel(new ImageIcon("")));
		
		JLabel lblNewLabel = new JLabel("WELCOME!");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Broadway", Font.BOLD, 40));
		lblNewLabel.setBounds(227, 13, 329, 59);
		frmSocialNetwork.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Enter your username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Broadway", Font.BOLD, 20));
		lblUsername.setBounds(32, 96, 251, 31);
		frmSocialNetwork.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(32, 139, 251, 31);
		frmSocialNetwork.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Enter your password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Broadway", Font.BOLD, 20));
		lblPassword.setBounds(32, 183, 251, 31);
		frmSocialNetwork.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(32, 227, 251, 31);
		frmSocialNetwork.getContentPane().add(passwordField);
		passwordField.setEchoChar('*');
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				char[] password = passwordField.getPassword();
				boolean checked = checkData(username, new String(password));
				if(checked) {
					Korisnik k = Crud.getKorisnik(username);
					MyProfile mp = new MyProfile(k);
					mp.setModal(true);
					mp.setVisible(true);
				} 
			}
		});
		btnLogin.setForeground(new Color(255, 20, 147));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setFont(new Font("Broadway", Font.BOLD, 20));
		btnLogin.setBounds(52, 280, 213, 35);
		frmSocialNetwork.getContentPane().add(btnLogin);
		btnLogin.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	String username = txtUsername.getText();
					char[] password = passwordField.getPassword();
					boolean checked = checkData(username, new String(password));
					if(checked) {
						Korisnik k = Crud.getKorisnik(username);
						MyProfile mp = new MyProfile(k);
						mp.setModal(true);
						mp.setVisible(true);
					} 
			    }
			}
		});
		
		JLabel lblNewAccount = new JLabel("Don't have an account?");
		lblNewAccount.setForeground(Color.WHITE);
		lblNewAccount.setFont(new Font("Broadway", Font.BOLD, 20));
		lblNewAccount.setBounds(385, 100, 313, 22);
		frmSocialNetwork.getContentPane().add(lblNewAccount);
		
		JButton btnMake = new JButton("MAKE ONE");
		btnMake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAccountOpening nao = new NewAccountOpening();
				nao.setModal(true);
				nao.setVisible(true);
			}
		});
		btnMake.setBackground(new Color(255, 255, 255));
		btnMake.setForeground(new Color(255, 20, 147));
		btnMake.setFont(new Font("Broadway", Font.BOLD, 20));
		btnMake.setBounds(442, 135, 168, 35);
		frmSocialNetwork.getContentPane().add(btnMake);
		btnMake.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					NewAccountOpening nao = new NewAccountOpening();
					nao.setModal(true);
					nao.setVisible(true);
				}
			}
		});
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.WHITE);
		btnExit.setForeground(new Color(255, 20, 147));
		btnExit.setFont(new Font("Broadway", Font.BOLD, 20));
		btnExit.setBounds(520, 427, 148, 46);
		frmSocialNetwork.getContentPane().add(btnExit);
		btnExit.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					System.exit(0);
				}
			}
		});
	}
	
	private boolean checkData(String username, String password) {
		Korisnik k = Crud.getKorisnik(username);
		if(k == null) {
			JOptionPane.showMessageDialog(frmSocialNetwork, "Entered username doesn't exists!");
			txtUsername.setText("");
			passwordField.setText("");
			return false;
		}
		if(!k.getPassword().equals(password)) {
			JOptionPane.showMessageDialog(frmSocialNetwork, "Invalid password!");
			passwordField.setText("");
			return false;
		}
		return true;
	}
}
