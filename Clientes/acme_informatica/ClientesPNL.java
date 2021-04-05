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
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class ClientesPNL extends JPanel {
	
	private JTable tableClientes;
	private JTextField txtFiltro;
	
	public ClientesPNL() {
		setBackground(Color.CYAN);
		setSize(new Dimension(1244, 918));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CLIENTES");
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 36));
		lblNewLabel.setBounds(535, 33, 296, 51);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 198, 1198, 694);
		add(scrollPane);
		
		tableClientes = new JTable();
		tableClientes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int fila = tableClientes.getSelectedRow();
				int  id = Integer.parseInt(tableClientes.getValueAt(fila, 0) + "");
				ClienteExistenteFRM frm = new ClienteExistenteFRM(id);
				frm.setLocationRelativeTo(null);
				frm.setModal(true);
				frm.setVisible(true);
				
				ClientesCTRLR.Rellena_JTable(tableClientes, "");
				
			}
			
		});
		tableClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tableClientes.setModel(new DefaultTableModel(
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
		tableClientes.getColumnModel().getColumn(0).setMinWidth(0);
		tableClientes.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(tableClientes);
		
		JButton btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.setBackground(UIManager.getColor("Button.background"));
		btnFiltrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ClientesCTRLR.Rellena_JTable(tableClientes, txtFiltro.getText());
				txtFiltro.setText("");
				
			}
		});
		btnFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFiltrar.setBounds(985, 126, 107, 27);
		add(btnFiltrar);
		
		JLabel lblNewLabel_1 = new JLabel("Escriba un filtro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(23, 126, 166, 23);
		add(lblNewLabel_1);
		
		JButton btnNuevo = new JButton("NUEVO");
		btnNuevo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ClienteNuevoFRM frm = new ClienteNuevoFRM(0);
				frm.setLocationRelativeTo(null);
				frm.setModal(true);
				frm.setVisible(true);
				
				ClientesCTRLR.Rellena_JTable(tableClientes, "");
				
			}
			
		});
		btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevo.setBackground(UIManager.getColor("Button.background"));
		btnNuevo.setBounds(1114, 126, 107, 27);
		add(btnNuevo);
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(144, 127, 818, 26);
		add(txtFiltro);
	}
}
