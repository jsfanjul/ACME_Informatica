package acme_informatica;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.SwingConstants;

public class FacDet_FRM extends JDialog {
	private JTextField txtId;
	private JTextField txtCantidad;
	private JButton btnBorrar, btnModificar, btnAnadir;
	private JLabel lblError;
	private int fila;
	private JTable table;
	private JComboBox cmbProducto;
	private JTextField txtPrecioVenta;

	private int facturaDetalle_id;

	public FacDet_FRM(int f, JTable jt, int id) {
		getContentPane().setBackground(Color.CYAN);

		facturaDetalle_id = id;
		fila = f;
		table = jt;
		if (fila == -1) {
			setTitle("Añadir productos a la factura");
		} else {
			setTitle("Modificar productos de la factura");
		}
		setSize(new Dimension(667, 392));
		getContentPane().setBounds(new Rectangle(600, 600, 600, 600));
		getContentPane().setPreferredSize(new Dimension(600, 600));
		getContentPane().setSize(new Dimension(600, 600));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(34, 16, 69, 20);
		getContentPane().add(lblId);

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(34, 55, 69, 20);
		getContentPane().add(lblProducto);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(34, 94, 69, 20);
		getContentPane().add(lblCantidad);

		JLabel lblPrecioVenta = new JLabel("Precio de venta");
		lblPrecioVenta.setBounds(34, 133, 159, 20);
		getContentPane().add(lblPrecioVenta);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resp = JOptionPane.showConfirmDialog(null, "¿Seguro quieres borrar este producto?", "AVISO",
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					int fila = table.getSelectedRow();
					dtm.removeRow(fila);
				}
				
				dispose();
			}
		});
		btnBorrar.setBounds(15, 212, 115, 29);
		getContentPane().add(btnBorrar);

		btnModificar = new JButton("Modificar");
		btnModificar.setSize(new Dimension(600, 600));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Validar() == false) {
					return;
				}
				
				ProductosMDL producto = (ProductosMDL) cmbProducto.getSelectedItem();
				
				Double precio = Double.parseDouble(txtPrecioVenta.getText());
				int cantidad = Integer.parseInt(txtCantidad.getText());
				table.setValueAt(producto.getProd_id(), fila, 0);
				table.setValueAt(producto.getProd_marca(), fila, 1);
				table.setValueAt(producto.getProd_modelo(), fila, 2);
				table.setValueAt(cantidad, fila, 3);
				table.setValueAt(precio, fila, 4);
				
				dispose();

			}
		});
		btnModificar.setBounds(243, 212, 115, 29);
		getContentPane().add(btnModificar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(126, 252, 115, 29);
		getContentPane().add(btnCancelar);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(515, 16, 115, 26);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		cmbProducto = new JComboBox<ProductosMDL>();
		cmbProducto.setBounds(515, 55, 115, 26);
		getContentPane().add(cmbProducto);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(515, 94, 115, 26);
		getContentPane().add(txtCantidad);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnadir.setSize(new Dimension(600, 600));
		btnAnadir.setBounds(126, 172, 115, 29);
		getContentPane().add(btnAnadir);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setBounds(515, 140, 115, 26);
		getContentPane().add(txtPrecioVenta);
		txtPrecioVenta.setColumns(10);

		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setAlignmentY(0.0f);
		lblError.setBounds(279, 273, 338, 20);
		getContentPane().add(lblError);
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (Validar() == false) {
					return;
				}

				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				Vector v = new Vector();
				ProductosMDL prod = (ProductosMDL) cmbProducto.getSelectedItem();
				v.add(prod.getProd_id());
				v.add(prod.getProd_marca());
				v.add(prod.getProd_modelo());
				v.add(Integer.parseInt(txtCantidad.getText()));
				v.add(Double.parseDouble(txtPrecioVenta.getText()));
				dtm.addRow(v);
				table.setModel(dtm);
				dispose();
			}
		});
		if (fila == -1) {
			btnModificar.setVisible(false);
			btnBorrar.setVisible(false);
			ProductosCTRLR.Rellenar_Combo(cmbProducto, 0);
		} else {
			btnAnadir.setVisible(false);

			/* RECUPERAR ID, cmbProductos, CANTIDAD y PRECIO */

			txtId.setText(facturaDetalle_id + "");

			int id_producto = (int) table.getValueAt(fila, 0);
			ProductosCTRLR.Rellenar_Combo(cmbProducto, id_producto);

			txtCantidad.setText(table.getValueAt(fila, 3) + "");
			txtPrecioVenta.setText(table.getValueAt(fila, 4) + "");
		}
	}

	public boolean Validar() {
		boolean v = true;
		lblError.setText("");

		if (txtCantidad.getText().trim().contentEquals("")) {
			lblError.setText("¡¡Introduce una cantidad!!");
			lblError.setForeground(Color.RED);
			txtCantidad.grabFocus();
			v = false;
		}

		if (txtPrecioVenta.getText().trim().contentEquals("")) {
			lblError.setText("¡¡El campo PRECIO no puede estar vacío!!");
			lblError.setForeground(Color.RED);
			txtPrecioVenta.grabFocus();
			v = false;
		}
		return v;
	}

	
}
