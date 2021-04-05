package acme_informatica;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ProveedoresPNL extends JPanel {
	
	private JTable tableProveedores;
	private JTextField txtFiltro;
	
	public ProveedoresPNL() {
		setBackground(Color.CYAN);
		setSize(new Dimension(1244, 918));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PROVEEDORES");
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 36));
		lblNewLabel.setBounds(524, 38, 288, 36);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setBounds(23, 199, 1198, 693);
		add(scrollPane);
		
		tableProveedores = new JTable();
		tableProveedores.setBackground(Color.WHITE);
		tableProveedores.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila = tableProveedores.getSelectedRow();
				int id = Integer.parseInt(tableProveedores.getValueAt(fila, 0) + "");
				ProveedorExistenteFRM frm = new ProveedorExistenteFRM(id);
				frm.setLocationRelativeTo(null);
				frm.setModal(true);
				frm.setVisible(true);
				
				ProveedoresCTRLR.Rellena_JTable(tableProveedores, "");
				
			}
		});
		tableProveedores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOMBRE", "CIF", "TIPO V\u00CDA", "LOCALIDAD", "C\u00D3DIGO POSTAL", "PROVINCIA", "PA\u00CDS", "CONTACTO", "TEL\u00C9FONO"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableProveedores.getColumnModel().getColumn(0).setMinWidth(0);
		tableProveedores.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(tableProveedores);
		
		JLabel lblNewLabel_1 = new JLabel("Escriba un filtro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(23, 128, 166, 23);
		add(lblNewLabel_1);
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(146, 128, 819, 26);
		add(txtFiltro);
		
		JButton btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFiltrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ProveedoresCTRLR.Rellena_JTable(tableProveedores, txtFiltro.getText());
				txtFiltro.setText("");
				
			}
			
		});
		btnFiltrar.setBackground(UIManager.getColor("Button.background"));
		btnFiltrar.setBounds(986, 128, 107, 26);
		add(btnFiltrar);
		
		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ProveedorNuevoFRM frm = new ProveedorNuevoFRM(0);
				frm.setLocationRelativeTo(null);
				frm.setModal(true);
				frm.setVisible(true);
				
				ProveedoresCTRLR.Rellena_JTable(tableProveedores, "");
				
			}
			
		});
		btnNuevo.setBackground(UIManager.getColor("Button.background"));
		btnNuevo.setBounds(1114, 129, 107, 25);
		add(btnNuevo);
	}
}
