package acme_informatica;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class StockPNL extends JPanel {

	private JTable table;

	public StockPNL() {
		setBackground(Color.CYAN);
		setSize(new Dimension(1000, 800));
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 960, 503);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Marca", "Modelo", "Caracter\u00EDsticas", "Precio", "Stock" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, String.class, Float.class,
					Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(480);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		scrollPane.setViewportView(table);

		JButton btnRellenar = new JButton("Rellenar");
		btnRellenar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ProductosCTRLR ctrlr = new ProductosCTRLR();
				ctrlr.Rellenar_JTable(table);
			}

		});
		btnRellenar.setBounds(432, 9, 100, 25);
		add(btnRellenar);

	}

}
