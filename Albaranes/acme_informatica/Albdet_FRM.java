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
import javax.swing.SwingConstants;
import java.awt.Color;

public class Albdet_FRM extends JDialog {
	
	private JTextField txtId;
	private JTextField txtCantidad;
	private JButton btnBorrar, btnModificar, btnGrabar;
	private int fila,albd_det_id;
	private JTable table;
	private JComboBox cmbProducto;
	private JTextField txtP;
	private JLabel lblError;
	
	public Albdet_FRM(int f, JTable jt, int id) {
		getContentPane().setBackground(Color.CYAN);
		albd_det_id = id;
		fila = f;
		table = jt;
		if (fila == -1) {
			setTitle("Alta nueva");
		} else {
			setTitle("Modificación");
		}
		setSize(new Dimension(667, 392));
		getContentPane().setBounds(new Rectangle(600, 600, 600, 600));
		getContentPane().setPreferredSize(new Dimension(600, 600));
		getContentPane().setSize(new Dimension(600, 600));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(34, 16, 69, 20);
		getContentPane().add(lblId);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(34, 55, 69, 20);
		getContentPane().add(lblProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(34, 94, 69, 20);
		getContentPane().add(lblCantidad);
		
		JLabel lblPrecio = new JLabel("Precio de entrada");
		lblPrecio.setBounds(34, 133, 159, 20);
		getContentPane().add(lblPrecio);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Seguro quieres borrar este producto?", "AVISO",
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					System.out.println(fila);
					dtm.removeRow(fila);
					table.setModel(dtm);
					dispose();
				}
			}
		});
		btnBorrar.setBounds(15, 237, 115, 29);
		getContentPane().add(btnBorrar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setSize(new Dimension(600, 600));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validar() == false) {
					return;
				}
				ProductosMDL producto = (ProductosMDL) cmbProducto.getSelectedItem();
				
				Double precio = Double.parseDouble(txtP.getText());
				int cantidad = Integer.parseInt(txtCantidad.getText());
				
				System.out.println(fila);
				table.setValueAt(producto.getProd_id(), fila, 0);
				table.setValueAt(producto.getProd_marca(), fila, 1);
				table.setValueAt(producto.getProd_modelo(), fila, 2);
				table.setValueAt(cantidad,fila, 3);
				table.setValueAt(precio,fila, 4);
				dispose();
			}
		});
		btnModificar.setBounds(236, 237, 115, 29);
		getContentPane().add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(126, 291, 115, 29);
		getContentPane().add(btnCancelar);
		
		txtId = new JTextField();
		txtId.setVisible(false);
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
		
		btnGrabar = new JButton("Grabar");
		btnGrabar.setSize(new Dimension(600, 600));
		btnGrabar.setBounds(126, 194, 115, 29);
		getContentPane().add(btnGrabar);
		
		txtP = new JTextField();
		txtP.setBounds(515, 140, 115, 26);
		getContentPane().add(txtP);
		txtP.setColumns(10);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(15, 157, 468, 20);
		getContentPane().add(lblError);
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Validar() == false) {
					return;
				}
				DefaultTableModel dtm = (DefaultTableModel) table.getModel();
				ProductosMDL prod = (ProductosMDL) cmbProducto.getSelectedItem();

				Vector v = new Vector();
				v.add(prod.getProd_id());
				v.add(prod.getProd_marca());
				v.add(prod.getProd_modelo());
				v.add(Integer.parseInt(txtCantidad.getText()));
				v.add(Double.parseDouble(txtP.getText()));
				dtm.addRow(v);
				table.setModel(dtm);
				dispose();
			}
		});
		if (fila == -1) {
			btnModificar.setVisible(false);
			btnBorrar.setVisible(false);
			ProductosCTRLR.Rellenar_Combo(cmbProducto, 0);
		}else {
			btnGrabar.setVisible(false);
			txtId.setText(albd_det_id + "");
			int id_producto = (int) table.getValueAt(fila, 0);
			ProductosCTRLR.Rellenar_Combo(cmbProducto, id_producto);
			txtCantidad.setText(table.getValueAt(fila, 3)+"");
			txtP.setText(table.getValueAt(fila, 4)+"");
		}
	}
	public boolean Validar() {
		boolean v = true;
		lblError.setText("");
		if (txtCantidad.getText().trim().contentEquals("")) {
			lblError.setText("¡¡Introduce una cantidad!!");
			txtCantidad.grabFocus();
			v = false;
		}

		if (txtP.getText().trim().contentEquals("")) {
			lblError.setText("¡¡El campo PRECIO no puede estar vacío!!");
			txtP.grabFocus();
			v = false;
		}
		return v;
	}
}
