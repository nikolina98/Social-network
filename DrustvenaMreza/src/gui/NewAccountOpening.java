package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import crud.Crud;
import model.Korisnik;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.stream.IntStream;
import java.awt.event.ActionEvent;

public class NewAccountOpening extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtMail;
	private JPasswordField passwordField;
	private String defPicPath = "C:\\Users\\Nina\\Dekstop\\pic1.png";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewAccountOpening dialog = new NewAccountOpening();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewAccountOpening() {
		setResizable(false);
		setTitle("New account");
		setBounds(100, 100, 623, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsername = new JLabel("Enter your username");
			lblUsername.setFont(new Font("Arial", Font.BOLD, 18));
			lblUsername.setBounds(12, 13, 240, 22);
			contentPanel.add(lblUsername);
		}
		{
			txtUsername = new JTextField();
			txtUsername.setBounds(12, 44, 240, 30);
			contentPanel.add(txtUsername);
			txtUsername.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Enter your name");
			lblName.setFont(new Font("Arial", Font.BOLD, 18));
			lblName.setBounds(12, 87, 160, 16);
			contentPanel.add(lblName);
		}
		{
			txtName = new JTextField();
			txtName.setBounds(12, 116, 240, 30);
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			JLabel lblSurname = new JLabel("Enter your surname");
			lblSurname.setFont(new Font("Arial", Font.BOLD, 18));
			lblSurname.setBounds(12, 171, 183, 16);
			contentPanel.add(lblSurname);
		}
		{
			txtSurname = new JTextField();
			txtSurname.setBounds(12, 197, 240, 30);
			contentPanel.add(txtSurname);
			txtSurname.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Enter your birthday");
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_2.setBounds(12, 247, 212, 22);
			contentPanel.add(lblNewLabel_2);
		}
		
		JComboBox<Integer> combo1 = new JComboBox<>();
		combo1.setBounds(22, 294, 61, 22);
		contentPanel.add(combo1);
		IntStream.range(1, 32).forEach(broj -> combo1.addItem(broj));
		
		
		JComboBox<String> combo2 = new JComboBox<>();
		combo2.setBounds(95, 294, 100, 22);
		contentPanel.add(combo2);
		String[] meseci = new DateFormatSymbols().getMonths();
        for (String mesec : meseci) {
            combo2.addItem(mesec);
        }
		
		JComboBox<Integer> combo3 = new JComboBox<>();
		combo3.setBounds(207, 294, 61, 22);
		contentPanel.add(combo3);
		IntStream.range(1950, 2020).forEach(broj -> combo3.addItem(broj));
		
		JLabel lblMail = new JLabel("Enter e-mail");
		lblMail.setFont(new Font("Arial", Font.BOLD, 18));
		lblMail.setBounds(326, 17, 160, 16);
		contentPanel.add(lblMail);
		
		txtMail = new JTextField();
		txtMail.setBounds(326, 44, 252, 30);
		contentPanel.add(txtMail);
		txtMail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Enter password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 18));
		lblPassword.setBounds(326, 87, 147, 16);
		contentPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(326, 120, 252, 26);
		contentPanel.add(passwordField);
		passwordField.setEchoChar('*');
		{
			JButton btnUncoverPass = new JButton("UNCOVER");
			btnUncoverPass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					passwordField.setEchoChar((char)0);
				}
			});
			btnUncoverPass.setFont(new Font("Arial", Font.BOLD, 15));
			btnUncoverPass.setBounds(310, 168, 139, 25);
			contentPanel.add(btnUncoverPass);
		}
		{
			JButton btnCoverPass = new JButton("COVER");
			btnCoverPass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					passwordField.setEchoChar('*');
				}
			});
			btnCoverPass.setFont(new Font("Arial", Font.BOLD, 15));
			btnCoverPass.setBounds(461, 168, 144, 25);
			contentPanel.add(btnCoverPass);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton confirmButton = new JButton("Confirm");
				confirmButton.setFont(new Font("Arial", Font.BOLD, 13));
				confirmButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String username = txtUsername.getText();
						String name = txtName.getText();
						String surname = txtSurname.getText();
						Integer birthDay = (Integer) combo1.getSelectedItem();
						String birthMonth = (String) combo2.getSelectedItem();
						Integer birthYear = (Integer) combo3.getSelectedItem();
						String email = txtMail.getText();
						char[] password = passwordField.getPassword();
						Calendar birthday = Calendar.getInstance();
						birthday.set(birthYear, getIntMonth(birthMonth), birthDay);
						if(Crud.usernameExists(username)) {
							JOptionPane.showMessageDialog(NewAccountOpening.this, "Username already exists!\nEnter another.");
							return;
						}
						if(Crud.emailExists(email)) {
							JOptionPane.showMessageDialog(NewAccountOpening.this, "The email entered is already in use!");
							return;
						}
						Korisnik k = new Korisnik();
						k.setKorisnickoIme(username);
						k.setPunoIme(name);
						k.setPunoPrezime(surname);
						k.setDatumRodjenja(birthday);
						k.setEmail(email);
						k.setPassword(new String(password));
						k.setPutanjaSlike(defPicPath);//path to the default picture
						
						boolean added = Crud.addUser(k);
						if(added) {
							JOptionPane.showMessageDialog(NewAccountOpening.this, "You have successfully opened an account on our social network!\nThanks for registering.");
							txtUsername.setText("");
							txtName.setText("");
							txtSurname.setText("");
							txtMail.setText("");
							passwordField.setText("");
							combo1.setSelectedIndex(0);
							combo2.setSelectedIndex(0);
							combo3.setSelectedIndex(0);
						} else {
							JOptionPane.showMessageDialog(NewAccountOpening.this, "Upss! Something did not work out.");
							return;
						}
					}
				});
				confirmButton.setActionCommand("OK");
				buttonPane.add(confirmButton);
				getRootPane().setDefaultButton(confirmButton);
			}
			{
				JButton deleteButton = new JButton("Cancel");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				deleteButton.setFont(new Font("Arial", Font.BOLD, 13));
				deleteButton.setActionCommand("Cancel");
				buttonPane.add(deleteButton);
			}
		}
	}
	
	private int getIntMonth(String month) {
		String[] meseci = new DateFormatSymbols().getMonths();
        for (int i = 0; i < meseci.length; i++) {
           if(month.equals(meseci[i]))
        	   return i;
        }
        return -1;
	}
}
