package acme_informatica;

import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProveedorNuevoFRM extends JDialog {
	
	private JTextField txtVia;
	private JTextField txtLocalidad;
	private JTextField txtCp;
	private JTextField txtCif;
	private JTextField txtNombre;
	private JTextField txtProvincia;
	private JTextField txtPais;
	private JTextField txtContacto;
	private JTextField txtTelefono;
	private JLabel lblError;
	
	public ProveedorNuevoFRM(int id) {
		
		setTitle("FORMULARIO DE PROVEEDOR NUEVO");
		getContentPane().setBackground(Color.CYAN);
		setSize(new Dimension(473, 657));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(24, 27, 80, 21);
		getContentPane().add(lblNewLabel);
		
		txtVia = new JTextField();
		txtVia.setColumns(10);
		txtVia.setBounds(146, 128, 288, 22);
		getContentPane().add(txtVia);
		
		JLabel lblTipoDeVa = new JLabel("CIF");
		lblTipoDeVa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa.setBounds(24, 78, 103, 21);
		getContentPane().add(lblTipoDeVa);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(146, 180, 288, 22);
		getContentPane().add(txtLocalidad);
		
		JLabel lblTipoDeVa_1 = new JLabel("Tipo de v\u00EDa");
		lblTipoDeVa_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1.setBounds(24, 127, 103, 21);
		getContentPane().add(lblTipoDeVa_1);
		
		JLabel lblTipoDeVa_1_1 = new JLabel("Localidad");
		lblTipoDeVa_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_1.setBounds(24, 179, 103, 21);
		getContentPane().add(lblTipoDeVa_1_1);
		
		JLabel lblTipoDeVa_1_2 = new JLabel("C\u00F3digo postal");
		lblTipoDeVa_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_2.setBounds(24, 233, 128, 21);
		getContentPane().add(lblTipoDeVa_1_2);
		
		JLabel lblTipoDeVa_1_3 = new JLabel("Provincia");
		lblTipoDeVa_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_3.setBounds(24, 286, 103, 21);
		getContentPane().add(lblTipoDeVa_1_3);
		
		JLabel lblTipoDeVa_1_4 = new JLabel("Pa\u00EDs");
		lblTipoDeVa_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_4.setBounds(24, 340, 103, 21);
		getContentPane().add(lblTipoDeVa_1_4);
		
		JLabel lblTipoDeVa_1_5 = new JLabel("Contacto");
		lblTipoDeVa_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_5.setBounds(24, 394, 103, 21);
		getContentPane().add(lblTipoDeVa_1_5);
		
		JLabel lblTipoDeVa_1_6 = new JLabel("Tel\u00E9fono");
		lblTipoDeVa_1_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_6.setBounds(24, 452, 103, 21);
		getContentPane().add(lblTipoDeVa_1_6);
		
		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(146, 234, 288, 22);
		getContentPane().add(txtCp);
		
		txtCif = new JTextField();
		txtCif.setColumns(10);
		txtCif.setBounds(146, 79, 288, 22);
		getContentPane().add(txtCif);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(146, 29, 288, 22);
		getContentPane().add(txtNombre);
		
		txtProvincia = new JTextField();
		txtProvincia.setColumns(10);
		txtProvincia.setBounds(146, 287, 288, 22);
		getContentPane().add(txtProvincia);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(146, 341, 288, 22);
		getContentPane().add(txtPais);
		
		txtContacto = new JTextField();
		txtContacto.setColumns(10);
		txtContacto.setBounds(146, 395, 288, 22);
		getContentPane().add(txtContacto);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(146, 453, 288, 22);
		getContentPane().add(txtTelefono);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblError.setForeground(Color.RED);
		lblError.setBounds(74, 505, 360, 30);
		getContentPane().add(lblError);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
			
		});
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBackground(UIManager.getColor("Button.background"));
		btnCancelar.setBounds(24, 564, 202, 30);
		getContentPane().add(btnCancelar);
		
		JButton btnGrabar = new JButton("GRABAR");
		btnGrabar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				lblError.setText("");

				if (txtNombre.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de un nombre");
					txtNombre.requestFocus();
					return;
				}
				
				if (txtCif.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de un CIF");
					txtCif.requestFocus();
					return;
				}
				
				boolean cif = ProveedoresCTRLR.ValidaCIF(txtCif.getText());
				
				if(cif == false) {
					lblError.setText("El CIF introducido no es válido. Inténtelo de nuevo");
					txtCif.setText("");
					txtCif.requestFocus();
					return;
				}
				
				if (txtVia.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de un tipo de vía");
					txtVia.requestFocus();
					return;
				}
				
				if (txtLocalidad.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de una localidad");
					txtLocalidad.requestFocus();
					return;
				}
				
				if (txtCp.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de un código postal");
					txtCp.requestFocus();
					return;
				}
				
				if (txtProvincia.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de una provincia");
					txtProvincia.requestFocus();
					return;
				}
				
				if (txtPais.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de un país");
					txtPais.requestFocus();
					return;
				}
				
				if (txtContacto.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de un contacto");
					txtContacto.requestFocus();
					return;
				}
				
				if (txtTelefono.getText().trim().equals("")) {
					lblError.setText("Es obligatoria la introducción de un teléfono");
					txtTelefono.requestFocus();
					return;
				}
				
				ProveedoresMDL prov = new ProveedoresMDL(id, txtNombre.getText(), txtCif.getText(), txtVia.getText(), txtLocalidad.getText(), txtCp.getText(), txtProvincia.getText(), txtPais.getText(), txtContacto.getText(), txtTelefono.getText());
				ProveedoresCTRLR.Grabar(prov);
				dispose();
				
			}
			
		});
		btnGrabar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGrabar.setBackground(UIManager.getColor("Button.background"));
		btnGrabar.setBounds(247, 564, 187, 30);
		getContentPane().add(btnGrabar);
		
	}
}
