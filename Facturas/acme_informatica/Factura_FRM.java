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

public class Factura_FRM extends JDialog {
	private JTextField txtId;
	private JLabel lblError, lblFechaVenta, lblCliente, lblFechaSalida;
	private JButton btnCancelar, btnBorrar, btnGrabar;
	private JComboBox cmbCliente;
	private static JDateChooser txtFechaVenta, txtFechaSalida;
	private static Date f_venta, f_salida, f_hoy;
	private static SimpleDateFormat formato;
	private static Calendar cal;
	private JTable table;
	private int factura_id;

	public Factura_FRM(int id) {
		getContentPane().setBackground(Color.CYAN);

		factura_id = id;
		formato = new java.text.SimpleDateFormat("yyyy-MM-dd");
		if (id == 0) {
			setTitle("Factura nueva");
		} else {
			setTitle("Modificar factura");
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

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(15, 46, 69, 20);
		getContentPane().add(lblId);

		lblFechaVenta = new JLabel("Fecha de venta");
		lblFechaVenta.setBounds(15, 124, 139, 20);
		getContentPane().add(lblFechaVenta);

		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(15, 88, 90, 20);
		getContentPane().add(lblCliente);

		lblFechaSalida = new JLabel("Fecha de Salida");
		lblFechaSalida.setBounds(15, 160, 115, 20);
		getContentPane().add(lblFechaSalida);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(266, 429, 115, 29);
		getContentPane().add(btnCancelar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Seguro quieres borrar esta factura?", "AVISO",
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(txtId.getText());
					Facturas_CTRLR ctrlr = new Facturas_CTRLR();
					int nregs = ctrlr.Borrar(id + "");
					if (nregs == 0) {
						lblError.setText("No la he borrado");
						lblError.setForeground(Color.RED);
						return;
					}
					dispose();
					JOptionPane.showMessageDialog(null, "Factura nº " + txtId.getText() + " borrada");
				}
			}
		});
		btnBorrar.setBounds(131, 429, 129, 29);
		getContentPane().add(btnBorrar);

		btnGrabar = new JButton("Grabar");
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validar() == false) {
					return;
				}
				
				int id = 0;
				if (!txtId.getText().contentEquals("")) {
					id = Integer.parseInt(txtId.getText());
				}
				
				JComboMDL jcm = (JComboMDL) cmbCliente.getSelectedItem();
				Factura_MDL mdl = new Factura_MDL(id, formato.format(txtFechaVenta.getDate()), jcm.getId(),
						formato.format(txtFechaSalida.getDate()));
				int nregs = 0;
				if (id == 0) {
					nregs = Facturas_CTRLR.Insertar(mdl, table);
				} else {
					nregs = Facturas_CTRLR.Modificar(mdl, table);
				}
				if (nregs == 0) {
					lblError.setText("No he podido grabar la factura");
					lblError.setForeground(Color.RED);
					return;
				}
				if (!txtId.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Factura nº " + txtId.getText() + " modificada");
				} else {
					JOptionPane.showMessageDialog(null, "Factura creada");
				}
				dispose();
			}
		});
		btnGrabar.setBounds(6, 429, 115, 29);
		getContentPane().add(btnGrabar);

		cmbCliente = new JComboBox();
		cmbCliente.setBounds(235, 85, 146, 26);
		getContentPane().add(cmbCliente);

		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setAlignmentY(0.0f);
		lblError.setBounds(15, 16, 338, 20);
		getContentPane().add(lblError);

		txtFechaVenta = new JDateChooser();
		txtFechaVenta.setToolTipText("Elija una fecha");
		txtFechaVenta.setDateFormatString("yyyy-MM-dd");
		txtFechaVenta.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		txtFechaVenta.setBounds(235, 124, 146, 26);
		getContentPane().add(txtFechaVenta);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.setToolTipText("Elija una fecha");
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.setBounds(235, 166, 146, 26);
		getContentPane().add(txtFechaSalida);

		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(15, 210, 115, 20);
		getContentPane().add(lblProductos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 246, 366, 139);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.addMouseListener(new MouseAdapter() {
			@Override

			// AQUI HAY QUE IR A FacDet_FRM
			public void mouseClicked(MouseEvent arg0) {
				int fila = table.getSelectedRow();
				int id = (int) table.getValueAt(fila, 0);

				FacDet_FRM frm = new FacDet_FRM(fila, table, id);
				frm.setModal(true);
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Marca", "Modelo", "Cantidad", "Precio de venta"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(108);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(139);
		scrollPane.setViewportView(table);

		JButton btnAniadir = new JButton("A\u00F1adir");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FacDet_FRM frm = new FacDet_FRM(-1, table, 0);
				frm.setModal(true);
				frm.setLocationRelativeTo(null);
				frm.setVisible(true);
			}
		});
		btnAniadir.setBounds(131, 395, 129, 29);
		getContentPane().add(btnAniadir);

		if (id == 0) {
			btnBorrar.setVisible(false);
			ClientesCTRLR.Rellenar_Combo(cmbCliente, 0);
		} else {
			txtId.setText(id + "");
			Facturas_CTRLR ctrlr = new Facturas_CTRLR();
			Factura_MDL mdl = ctrlr.Recupera_Por_Id(id);
			ClientesCTRLR.Rellenar_Combo(cmbCliente, mdl.getFac_cli_id());
			FacDet_CTRLR.Rellenar_JTable_Productos(table, factura_id); // Aqui me falla al mostrar los productos,
																		// ï¿½cambiar por el otro metodo?
			txtFechaVenta.setDate(ConversionFechas(mdl.getFac_fecha_venta()));
			txtFechaSalida.setDate(ConversionFechas(mdl.getFac_fecha_salida()));
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

		Date f_venta_date = txtFechaVenta.getDate();
		Date f_salida_date = txtFechaSalida.getDate();

		if (txtFechaVenta.getDate() == null) {
			lblError.setText("Elija la fecha de proveedor");
			txtFechaVenta.grabFocus();
			v = false;
		}
		if (txtFechaSalida.getDate() == null) {
			lblError.setText("Elija la fecha de entrada");
			txtFechaSalida.grabFocus();
			v = false;
		}
		if (f_salida_date.compareTo(f_venta_date) < 0) {
			lblError.setText("La fecha de entrada no puede ser inferior a la de proveedor");
			v = false;
		}
		return v;
	}
}
