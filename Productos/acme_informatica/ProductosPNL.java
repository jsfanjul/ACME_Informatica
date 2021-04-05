package acme_informatica;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class ProductosPNL extends JPanel {
	
	private JTextField txtFiltro;
	private JTable tabla;
	private JButton btnNuevo;
	
	public ProductosPNL() {
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
				
				ProductosCTRLR.Rellena_JTable(tabla, txtFiltro.getText());
				txtFiltro.setText("");
			}
		});
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFiltrar.setBounds(367, 49, 89, 23);
		add(btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 129, 856, 507);
		add(scrollPane);
		
		tabla = new JTable();
		tabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int fila = tabla.getSelectedRow();
				int id = (int) tabla.getValueAt(fila, 0);
				ProductosFRM frm = new ProductosFRM(id);
				frm.setLocationRelativeTo(null);
				frm.setModal(true);
				frm.setVisible(true);
				ProductosCTRLR.Rellena_JTable(tabla, txtFiltro.getText());
			}
		}); 
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Marca", "Modelo", "Caracter\u00EDsticas", "Precio", "Stock", "Observaciones", "Categor\u00EDa"
			}
		));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabla.getColumnModel().getColumn(0).setMinWidth(0);
		tabla.getColumnModel().getColumn(0).setMaxWidth(0);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(158);
		scrollPane.setViewportView(tabla);
		
		ProductosCTRLR.Rellena_JTable(tabla,"");
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ProductosFRM frm = new ProductosFRM(0);

				frm.setLocationRelativeTo(null);
				frm.setModal(true);
				frm.setVisible(true);
				
				//Refrescar la tabla 
				ProductosCTRLR.Rellena_JTable(tabla, txtFiltro.getText());
			}
		});
		btnNuevo.setBounds(723, 49, 89, 23);
		add(btnNuevo);
	}
}
