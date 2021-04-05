package acme_informatica;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CategoriasPNL extends JPanel {
	private JTextField txtFiltro;
	private JTable tabla;
	private JButton btnNuevo;

	public CategoriasPNL() {
		setBackground(Color.CYAN);
		setSize(new Dimension(1244, 918));
		setLayout(null);

		JLabel lblFiltro = new JLabel("Filtro");
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFiltro.setBounds(28, 47, 75, 27);
		add(lblFiltro);

		txtFiltro = new JTextField();
		txtFiltro.setBounds(113, 50, 202, 20);
		add(txtFiltro);
		txtFiltro.setColumns(10);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CategoriasCTRLR.Rellena_Tabla(tabla, txtFiltro.getText());
				txtFiltro.setText("");
			}
		});
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFiltrar.setBounds(367, 49, 89, 23);
		add(btnFiltrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(268, 125, 410, 596);
		add(scrollPane);

		tabla = new JTable();
		tabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabla.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				int fila = tabla.getSelectedRow();
				int id = (int) tabla.getValueAt(fila, 0);
				CategoriasFRM frm = new CategoriasFRM(id);
				frm.setLocationRelativeTo(null);
				frm.setModal(true);
				frm.setVisible(true);
				CategoriasCTRLR.Rellena_Tabla(tabla, txtFiltro.getText());
			}
		});
		tabla.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Categor\u00EDa" }) {
			boolean[] columnEditables = new boolean[] { true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabla.getColumnModel().getColumn(0).setMinWidth(0);
		tabla.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(tabla);
		
		CategoriasCTRLR.Rellena_Tabla(tabla, "");

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CategoriasFRM frm = new CategoriasFRM(0);

				frm.setLocationRelativeTo(null);
				frm.setModal(true);
				frm.setVisible(true);
				// Refrescar la tabla
				CategoriasCTRLR.Rellena_Tabla(tabla, "");
				

			}
		});
		btnNuevo.setBounds(723, 49, 89, 23);
		add(btnNuevo);
	}
}
