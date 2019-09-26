package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import crud.Crud;
import model.Korisnik;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class FriendsList extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Korisnik user;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			AddFriend dialog = new AddFriend();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FriendsList(Korisnik k) {
		user = k;
		setBounds(100, 100, 585, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 543, 235);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		List<Korisnik> users = Crud.getFriends1(user);
		TableModel tm = new TableModel(users);
		table.setModel(tm);
		setTitle("Number of friends: " + users.size());
		
		JPanel picPanel = new JPanel();
		picPanel.setBounds(92, 261, 140, 132);
		contentPanel.add(picPanel);
		
		JLabel lblFullName = new JLabel("");
		lblFullName.setFont(new Font("Candara", Font.BOLD, 18));
		lblFullName.setBounds(256, 261, 163, 21);
		contentPanel.add(lblFullName);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setFont(new Font("Candara", Font.BOLD, 15));
		lblUsername.setBounds(256, 295, 92, 21);
		contentPanel.add(lblUsername);
		
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!lsm.isSelectionEmpty()) {
					int maxSelInd = lsm.getMaxSelectionIndex();
					String username = tm.getValueAt(maxSelInd, 2).toString();
					Korisnik k = Crud.getKorisnik(username); 
					JLabel newPicture = new JLabel(new ImageIcon(k.getPutanjaSlike()));
					picPanel.add(newPicture);
					picPanel.repaint();
					picPanel.revalidate();
					lblFullName.setText(k.getPunoIme() + " " + k.getPunoPrezime());
					lblUsername.setText(k.getKorisnickoIme());
				} else {
					lblFullName.setText("");
					lblUsername.setText("");
					picPanel.removeAll();
				}
			}
		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDelete = new JButton("Delete friend");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int maxSlIndex = lsm.getMaxSelectionIndex();
						String username = tm.getValueAt(maxSlIndex, 2).toString();
						Korisnik to = Crud.getKorisnik(username);
						boolean friendship = Crud.deleteFriend(user, to);
						if(friendship) {
							JOptionPane.showMessageDialog(contentPanel, "Just deleted a friend with username: " + username);
							tm.removeFromList(maxSlIndex);
							tm.fireTableDataChanged();
							return;
						} else {
							JOptionPane.showMessageDialog(contentPanel, "Something went wrong! Try again ");
							return;
						}
					}
				});
				btnDelete.setFont(new Font("Candara", Font.BOLD, 15));
				btnDelete.setActionCommand("OK");
				buttonPane.add(btnDelete);
				getRootPane().setDefaultButton(btnDelete);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Candara", Font.BOLD, 15));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
