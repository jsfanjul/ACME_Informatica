package acme_informatica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import com.toedter.calendar.JDateChooser;

import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Factura_Seleccion extends JPanel {
	private JTextField txtFiltrar;
	private JScrollPane scrollPane;
	private static JTable table;
	private JButton btnAgregar;
	private JLabel lblAviso;

	public Factura_Seleccion() {
		setBackground(Color.CYAN);
		setSize(new Dimension(1000, 800));
		setLayout(null);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Facturas_CTRLR.Rellenar_JTable(table, txtFiltrar.getText());
				if (txtFiltrar.getText().equals("")) {
					lblAviso.setText("Todos los resultados");
					txtFiltrar.setText("");
				} else {
					lblAviso.setText("Resultado para el siguiente filtro: " + "'" + txtFiltrar.getText() + "'");
					txtFiltrar.setText("");
				}
			}
		});
		btnFiltrar.setBounds(240, 3, 115, 29);
		add(btnFiltrar);

		txtFiltrar = new JTextField();
		txtFiltrar.setBounds(79, 4, 146, 26);
		add(txtFiltrar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 62, 952, 710);
		add(scrollPane);

		table = new JTable();
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int fila = table.getSelectedRow();
				int id = (int) table.getValueAt(fila, 0);

				Factura_FRM frm = new Factura_FRM(id);
				frm.setModal(true);
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);

				Facturas_CTRLR ctrlr = new Facturas_CTRLR();
				ctrlr.Rellenar_JTable(table, txtFiltrar.getText());
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Cliente", "Fecha de venta", "Fecha de salida" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(131);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(119);
		scrollPane.setViewportView(table);

		btnAgregar = new JButton("+");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Factura_FRM frm = new Factura_FRM(0);
				frm.setModal(true);
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);
				Facturas_CTRLR.Rellenar_JTable(table, txtFiltrar.getText());
			}
		});
		btnAgregar.setBounds(15, 3, 53, 29);
		add(btnAgregar);

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.BLUE);
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setBounds(25, 37, 330, 20);
		add(lblAviso);
		Facturas_CTRLR.Rellenar_JTable(table, "");
	}
}
