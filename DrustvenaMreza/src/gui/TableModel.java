package gui;

import java.util.Calendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Korisnik;

public class TableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] attributes = {"User name", "User surname", "Username","Birthday year"};
	List<Korisnik> users;
	
	public TableModel(List<Korisnik> r) {
		users = r;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return users.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Korisnik k = (Korisnik) users.get(arg0);
		switch(arg1) {
		case 0:
			return k.getPunoIme();
		case 1:
			return k.getPunoPrezime();
		case 2:
			return k.getKorisnickoIme();
		case 3:
			return k.getDatumRodjenja().get(Calendar.YEAR);
		}
		return null;
	}

	@Override
	public String getColumnName(int col) {
		return attributes[col];
	}

	public void removeFromList(int index) {
		users.remove(index);
	}
}
