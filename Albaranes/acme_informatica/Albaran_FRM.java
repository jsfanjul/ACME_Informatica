package acme_informatica;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class Albaran_FRM extends JDialog {
	private JTextField txtId;
	private JLabel lblError, lblFProveedor, lblProveedor, lblFEntrada;
	private JButton btnCancelar, btnBorrar, btnGrabar;
	private JComboBox cmbProv;
	private static JDateChooser txtFProv, txtEntrada;
	private static Date f_prov, f_entr, f_hoy;
	private static SimpleDateFormat formato;
	private static Calendar cal;
	private int albaran_id;
	private JTable table;

	public Albaran_FRM(int id) {
		getContentPane().setBackground(Color.CYAN);
		
		albaran_id = id;
		formato = new java.text.SimpleDateFormat("yyyy-MM-dd");
		if (id == 0) {
			setTitle("Alta nueva");
		} else {
			setTitle("Modificación");
		}
		setResizable(false);
		setSize(new Dimension(402, 514));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(235, 43, 146, 26);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblId = new JLabel("id");
		lblId.setBounds(15, 46, 69, 20);
		getContentPane().add(lblId);

		lblFProveedor = new JLabel("Fecha Proveedor");
		lblFProveedor.setBounds(15, 124, 139, 20);
		getContentPane().add(lblFProveedor);

		lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(15, 88, 90, 20);
		getContentPane().add(lblProveedor);

		lblFEntrada = new JLabel("Fecha Entrada");
		lblFEntrada.setBounds(15, 160, 115, 20);
		getContentPane().add(lblFEntrada);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(15, 429, 115, 29);
		getContentPane().add(btnCancelar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿ Seguro ?", "AVISO", JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(txtId.getText());
					int nregs = Albaranes_CTRLR.Borrar(id + "");
					if (nregs == 0) {
						lblError.setText("No lo he borrado");
						return;
					}
					dispose();
					JOptionPane.showMessageDialog(null, "Albaran nº "+txtId.getText()+" borrado");
				}
			}
		});
		btnBorrar.setBounds(131, 429, 129, 29);
		getContentPane().add(btnBorrar);

		btnGrabar = new JButton("Grabar");
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Validar() == false) {
					return;
				}
				int id = 0;
				if (!txtId.getText().contentEquals("")) {
					id = Integer.parseInt(txtId.getText());
				}
				JComboMDL jcm = (JComboMDL) cmbProv.getSelectedItem();
				Albaran_MDL mdl = new Albaran_MDL(
						id, 
						formato.format(txtFProv.getDate()), 
						jcm.getId(),
						formato.format(txtEntrada.getDate()));
				int nregs = 0;
				if (id==0) {
					nregs = Albaranes_CTRLR.Insertar(mdl, table);
				}else {
					nregs = Albaranes_CTRLR.Modificar(mdl, table);
				}
				if (nregs == 0) {
					lblError.setText("No he podido grabar");
					lblError.setForeground(Color.RED);
					return;
				}
				if (!txtId.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Albaran nº "+txtId.getText()+" modificado");
				}else {
					JOptionPane.showMessageDialog(null, "Registro creado");
				}
				dispose();
			}
		});
		btnGrabar.setBounds(264, 429, 115, 29);
		getContentPane().add(btnGrabar);

		cmbProv = new JComboBox();
		cmbProv.setBounds(235, 85, 146, 26);
		getContentPane().add(cmbProv);

		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setAlignmentY(0.0f);
		lblError.setBounds(15, 16, 338, 20);
		getContentPane().add(lblError);

		txtFProv = new JDateChooser();
		txtFProv.setToolTipText("Elija una fecha");
		txtFProv.setDateFormatString("yyyy-MM-dd");
		txtFProv.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		txtFProv.setBounds(235, 124, 146, 26);
		getContentPane().add(txtFProv);

		txtEntrada = new JDateChooser();
		txtEntrada.setToolTipText("Elija una fecha");
		txtEntrada.setDateFormatString("yyyy-MM-dd");
		txtEntrada.setBounds(235, 166, 146, 26);
		getContentPane().add(txtEntrada);
		
		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(15, 210, 115, 20);
		getContentPane().add(lblProductos);
		
		JButton btnAniadir = new JButton("A\u00F1adir");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Albdet_FRM frm = new Albdet_FRM(-1, table, 0);
				frm.setModal(true);
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);
			}
		});
		btnAniadir.setBounds(145, 395, 115, 29);
		getContentPane().add(btnAniadir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 246, 366, 113);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = table.getSelectedRow();
				System.out.println("fila " + fila);
				int id = (int) table.getValueAt(fila, 0);

				Albdet_FRM frm = new Albdet_FRM(fila, table, id);
				frm.setModal(true);
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);
				frm.dispose();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Marca", "Modelo", "Cantidad", "Precio de entrada"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(137);
		scrollPane.setViewportView(table);

		if (id == 0) {
			btnBorrar.setVisible(false);
			ProveedoresCTRLR.Rellenar_Combo(cmbProv, 0);
		} else {
			txtId.setText(id+"");
			Albaran_MDL mdl = Albaranes_CTRLR.Recupera_Por_Id(id);
			ProveedoresCTRLR.Rellenar_Combo(cmbProv, mdl.getAlb_prov_id());
			Albdet_CTRL.Rellenar_JTable_Albaranes(table, albaran_id);
			txtFProv.setDate(ConversionFechas(mdl.getAlb_fecha_prov()));
			txtEntrada.setDate(ConversionFechas(mdl.getAlb_fecha_entrada()));
		}

	}
	public static Date ConversionFechas(String fecha) {
		try {
			SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyy-MM-dd");
			Date f = formato.parse(fecha);
			return f;
		} catch (ParseException e1) {
			e1.printStackTrace();
			return null;
		}
		
	}
	public boolean Validar() {
		boolean v = true;
		
		Date f_prov_date =txtFProv.getDate();
		Date f_entr_date =txtEntrada.getDate();
		
		if (txtFProv.getDate() == null){
			lblError.setText("Elija la fecha de proveedor");
			txtFProv.grabFocus();
			v = false;
		}
		if (txtEntrada.getDate() == null){
			lblError.setText("Elija la fecha de entrada");
			txtEntrada.grabFocus();
			v = false;
		}
		if(f_entr_date.compareTo(f_prov_date) < 0) {
			lblError.setText("La fecha de entrada no puede ser inferior a la de proveedor");
			v = false;
		}
		return v;
	}
}
