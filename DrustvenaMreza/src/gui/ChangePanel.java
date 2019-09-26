package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.Crud;
import model.Korisnik;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class ChangePanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Korisnik user;
	
	 //*/
	 //* Launch the application.
	 //*/
	/*public static void main(String[] args) {
		try {
			ChangePanel dialog = new ChangePanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ChangePanel(Korisnik u) {
		this.user = u;
		setTitle("Information change panel");
		setBounds(100, 100, 561, 393);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Nina\\Desktop\\251133.jpg")));

		{
			JButton btnName = new JButton("Change name");
			btnName.setBackground(new Color(219, 112, 147));
			btnName.setForeground(new Color(255, 255, 255));
			btnName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewInfoDialog nid = new NewInfoDialog("Enter your new name");
					nid.setModal(true);
					nid.setVisible(true);
					String newName = nid.getInfo();
					if(!newName.equals("")) {
						boolean changed = Crud.updateName(user.getKorisnickoIme(), newName);
						if(changed) {
							JOptionPane.showMessageDialog(contentPanel, "Your name has changed successfully!");
							return;
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Something went wrong!");
							return;
						}
					}
				}
			});
			btnName.setFont(new Font("Candara", Font.BOLD, 17));
			btnName.setBounds(288, 30, 219, 41);
			contentPanel.add(btnName);
		}
		{
			JButton btnSurname = new JButton("Change surname");
			btnSurname.setBackground(new Color(219, 112, 147));
			btnSurname.setForeground(Color.WHITE);
			btnSurname.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewInfoDialog nid = new NewInfoDialog("Enter your new surname");
					nid.setModal(true);
					nid.setVisible(true);
					String newSurname = nid.getInfo();
					if(!newSurname.equals("")) {
						boolean changed = Crud.updateSurname(user.getKorisnickoIme(), newSurname);
						if(changed) {
							JOptionPane.showMessageDialog(contentPanel, "Your surname has changed successfully!");
							return;
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Something went wrong!");
							return;
						}
					}
				}
			});
			btnSurname.setFont(new Font("Candara", Font.BOLD, 17));
			btnSurname.setBounds(33, 110, 219, 41);
			contentPanel.add(btnSurname);
		}
		{
			JButton btnEmail = new JButton("Change e-mail");
			btnEmail.setForeground(new Color(255, 255, 255));
			btnEmail.setBackground(new Color(219, 112, 147));
			btnEmail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewInfoDialog nid = new NewInfoDialog("Enter your new e-mail");
					nid.setModal(true);
					nid.setVisible(true);
					String email = nid.getInfo();
					if(!email.equals("")) {
						boolean changed = Crud.updateEmail(user.getKorisnickoIme(), email);
						if(changed) {
							JOptionPane.showMessageDialog(contentPanel, "Your e-mail has changed successfully!");
							return;
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Something went wrong!");
							return;
						}
					}
				}
			});
			btnEmail.setFont(new Font("Candara", Font.BOLD, 17));
			btnEmail.setBounds(288, 109, 219, 42);
			contentPanel.add(btnEmail);
		}
		{
			JButton btnPass = new JButton("Change password");
			btnPass.setBackground(new Color(219, 112, 147));
			btnPass.setForeground(new Color(255, 255, 255));
			btnPass.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PasswordChangePanel pcp = new PasswordChangePanel(user.getKorisnickoIme());
					pcp.setModal(true);
					pcp.setVisible(true);
				}
			});
			btnPass.setFont(new Font("Candara", Font.BOLD, 20));
			btnPass.setBounds(179, 194, 199, 41);
			contentPanel.add(btnPass);
		}
		{
			JLabel lblNewLabel = new JLabel("Select:");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setFont(new Font("Candara", Font.BOLD, 35));
			lblNewLabel.setBounds(81, 30, 139, 41);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(219, 112, 147));
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setFont(new Font("Candara", Font.BOLD, 15));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
