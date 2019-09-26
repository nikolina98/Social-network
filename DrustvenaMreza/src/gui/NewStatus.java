package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.Crud;
import model.Korisnik;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewStatus extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private Korisnik user;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			NewStatus dialog = new NewStatus();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public NewStatus(Korisnik u) {
		user = u;
		setTitle("New status");
		setBounds(100, 100, 542, 376);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textArea = new JTextArea();
			textArea.setBounds(26, 13, 465, 268);
			contentPanel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton postBtn = new JButton("Post");
				postBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String text = textArea.getText();
						if(text.length() <= 25) {
							boolean added = Crud.addStatus(text, user);
							if(added) {
								JOptionPane.showMessageDialog(contentPanel, "Just posted a new status!");
								textArea.setText("");
								return;
							} else {
								JOptionPane.showMessageDialog(contentPanel, "Something went wrong! Try again ");
								return;
							}
						} else {
							JOptionPane.showMessageDialog(contentPanel, "The maximum length of the status is 25! Try again");
						}
					}
				});
				postBtn.setBackground(new Color(255, 255, 255));
				postBtn.setFont(new Font("Candara", Font.BOLD, 15));
				postBtn.setForeground(new Color(0, 0, 128));
				postBtn.setActionCommand("OK");
				buttonPane.add(postBtn);
				getRootPane().setDefaultButton(postBtn);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("Candara", Font.BOLD, 15));
				cancelButton.setForeground(new Color(0, 0, 128));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
