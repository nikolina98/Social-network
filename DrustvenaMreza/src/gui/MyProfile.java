package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.Crud;
import model.Korisnik;
import model.Status;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;

public class MyProfile extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JLabel profilePicture;
	private Korisnik user;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			MyProfile dialog = new MyProfile();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public MyProfile(Korisnik user) {
		this.user = user;
		setTitle("My profile - " + user.getKorisnickoIme().toUpperCase());
		setBounds(100, 100, 805, 629);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
//		final ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Nina\\Desktop\\back.png");
//		JLabel label = new JLabel(backgroundImage);
//		label.setBounds(0, 0, 786, 594);
//		getContentPane().add(label);
		
		profilePicture = new JLabel(new ImageIcon(user.getPutanjaSlike()));
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setFont(new Font("Candara", Font.BOLD, 15));
		btnNewButton.setBounds(12, 486, 108, 38);
		getContentPane().add(btnNewButton);
		
		panel = new JPanel();
		panel.setToolTipText("Profile picture");
		panel.setBounds(12, 13, 142, 134);
		getContentPane().add(panel);
		panel.add(profilePicture);
		panel.requestFocusInWindow();
		
		JButton lblChangePic = new JButton("Change picture");
		lblChangePic.requestFocusInWindow();
		lblChangePic.setBackground(new Color(255, 255, 255));
		lblChangePic.setFont(new Font("Candara", Font.BOLD, 15));
		lblChangePic.setForeground(new Color(0, 0, 128));
		lblChangePic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser ch = new JFileChooser();
				if(ch.showOpenDialog(contentPanel) == JFileChooser.APPROVE_OPTION) {
					File f = ch.getSelectedFile();
					JLabel newPicture = new JLabel(new ImageIcon(f.getAbsolutePath()));
					panel.remove(profilePicture);
					profilePicture = newPicture;
					panel.add(newPicture);
					Crud.updateImagePath(user, f.getPath());
					panel.repaint();
					panel.revalidate();
				}
			}
		});
		lblChangePic.setBounds(12, 154, 142, 27);
		getContentPane().add(lblChangePic);
		
		JLabel lblFullName = new JLabel(user.getPunoIme() + " " + user.getPunoPrezime());
		lblFullName.setForeground(new Color(0, 0, 128));
		lblFullName.setBackground(new Color(255, 255, 255));
		lblFullName.setFont(new Font("Candara", Font.BOLD, 20));
		lblFullName.setBounds(166, 24, 201, 27);
		getContentPane().add(lblFullName);
		
		JLabel lblBday = new JLabel("B-day: " + getStr(user.getDatumRodjenja()));
		lblBday.setForeground(new Color(0, 0, 128));
		lblBday.setBackground(new Color(255, 255, 255));
		lblBday.setFont(new Font("Candara", Font.BOLD, 17));
		lblBday.setBounds(166, 51, 201, 27);
		getContentPane().add(lblBday);
		
		JLabel lblUsername = new JLabel("Username: " + user.getKorisnickoIme());
		lblUsername.setBackground(new Color(255, 255, 255));
		lblUsername.setForeground(new Color(0, 0, 128));
		lblUsername.setFont(new Font("Candara", Font.BOLD, 17));
		lblUsername.setBounds(166, 80, 201, 27);
		getContentPane().add(lblUsername);
		
		JLabel lblMail = new JLabel("E-mail: " + user.getEmail());
		lblMail.setFont(new Font("Candara", Font.BOLD, 17));
		lblMail.setBackground(new Color(255, 255, 255));
		lblMail.setForeground(new Color(0, 0, 128));
		lblMail.setBounds(166, 114, 201, 16);
		getContentPane().add(lblMail);
		
		JButton btnAddFriend = new JButton("Add friend");
		btnAddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddFriend af = new AddFriend(user);
				af.setModal(true);
				af.setVisible(true);
			}
		});
		btnAddFriend.setBackground(new Color(255, 255, 255));
		btnAddFriend.setForeground(new Color(0, 0, 128));
		btnAddFriend.setFont(new Font("Candara", Font.BOLD, 15));
		btnAddFriend.setBounds(12, 196, 142, 25);
		getContentPane().add(btnAddFriend);
		
		JButton btnFriendsList = new JButton("Friends list");
		btnFriendsList.setBackground(new Color(255, 255, 255));
		btnFriendsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FriendsList fl = new FriendsList(getUser());
				fl.setModal(true);
				fl.setVisible(true);
			}
		});
		btnFriendsList.setForeground(new Color(0, 0, 128));
		btnFriendsList.setFont(new Font("Candara", Font.BOLD, 15));
		btnFriendsList.setBounds(12, 234, 142, 27);
		getContentPane().add(btnFriendsList);
		
		JButton btnRequest = new JButton("Friend request");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FriendRequest fr = new FriendRequest(user);
				fr.setModal(true);
				fr.setVisible(true);
			}
		});
		btnRequest.setBackground(new Color(255, 255, 255));
		btnRequest.setForeground(new Color(0, 0, 128));
		btnRequest.setFont(new Font("Candara", Font.BOLD, 15));
		btnRequest.setBounds(12, 273, 142, 25);
		getContentPane().add(btnRequest);
		
		JLabel lblNewLabel = new JLabel("Statuses");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Candara", Font.BOLD, 25));
		lblNewLabel.setBounds(480, 13, 123, 29);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Post a status");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewStatus ns = new NewStatus(user);
				ns.setModal(true);
				ns.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setFont(new Font("Candara", Font.BOLD, 20));
		btnNewButton_1.setBounds(12, 311, 201, 44);
		getContentPane().add(btnNewButton_1);
		
		int height = 54;
		List<JLabel> labels = new ArrayList<>();
		List<Status> statuses = Crud.getStatuses();
		for (int i = 0; i < 10; i++) {
			JLabel lblLabela = new JLabel("");
			lblLabela.setForeground(new Color(0, 0, 128));
			lblLabela.setFont(new Font("Candara", Font.BOLD, 16));
			lblLabela.setBounds(393, 65, 325, height);
			getContentPane().add(lblLabela);
			labels.add(lblLabela);
			if(statuses.size() > i) {
				String status = statuses.get(i).toString();
				lblLabela.setText(status);
			}
			height += 120;
		}
		//btnNewButton.requestFocusInWindow();
	}
	
	private String getStr(Calendar c) {
		String dayStr = null, monthStr = null;
		int day = c.get(Calendar.DAY_OF_MONTH);
		if(day < 10) {
			dayStr = 0 + "" + day;
		}
		int month = c.get(Calendar.MONTH);
		if(month < 10) {
			monthStr = 0 + "" + month;
		}
		return dayStr + "." + monthStr + "." + c.get(Calendar.YEAR);
	}
	
	private Korisnik getUser() {
		return user;
	}
}
