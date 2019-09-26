package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.Crud;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordChangePanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtOld;
	private JTextField txtNew;
	private String userName;

	///**
	//* Launch the application.
	// */
	/*public static void main(String[] args) {
		try {
			PasswordChangePanel dialog = new PasswordChangePanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public PasswordChangePanel(String username) {
		this.userName = username;
		setTitle("Password change panel");
		setBounds(100, 100, 313, 322);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblOld = new JLabel("Old password");
			lblOld.setFont(new Font("Candara", Font.BOLD, 17));
			lblOld.setBounds(26, 24, 167, 22);
			contentPanel.add(lblOld);
		}
		{
			txtOld = new JTextField();
			txtOld.setBounds(26, 57, 230, 31);
			contentPanel.add(txtOld);
			txtOld.setColumns(10);
		}
		{
			JLabel lblNew = new JLabel("New password");
			lblNew.setFont(new Font("Candara", Font.BOLD, 17));
			lblNew.setBounds(26, 114, 143, 16);
			contentPanel.add(lblNew);
		}
		{
			txtNew = new JTextField();
			txtNew.setBounds(26, 143, 230, 31);
			contentPanel.add(txtNew);
			txtNew.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String oldPass = txtOld.getText();
						String newPass = txtNew.getText();
						if(!oldPass.equals("") && !newPass.equals("")) {
							boolean approved = Crud.updatePassword(getUsername(), oldPass, newPass);
							
							if(approved) {
								JOptionPane.showMessageDialog(contentPanel, "Your password has changed successfully!");
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(contentPanel, "Something went wrong!");
								return;
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
	
	private String getUsername() {
		return userName;
	}

}
