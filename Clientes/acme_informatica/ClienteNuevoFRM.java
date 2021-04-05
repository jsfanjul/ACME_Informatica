package acme_informatica;

import javax.swing.JDialog;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteNuevoFRM extends JDialog {
	
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
	
	public ClienteNuevoFRM(int id) {
		
		getContentPane().setBackground(Color.CYAN);
		
		setSize(new Dimension(473, 657));
		setTitle("FORMULARIO DE CLIENTE NUEVO");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(21, 26, 80, 21);
		getContentPane().add(lblNewLabel);
		
		txtVia = new JTextField();
		txtVia.setBounds(143, 127, 288, 22);
		getContentPane().add(txtVia);
		txtVia.setColumns(10);
		
		JLabel lblTipoDeVa = new JLabel("CIF");
		lblTipoDeVa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa.setBounds(21, 77, 103, 21);
		getContentPane().add(lblTipoDeVa);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(143, 179, 288, 22);
		getContentPane().add(txtLocalidad);
		
		JLabel lblTipoDeVa_1 = new JLabel("Tipo de v\u00EDa");
		lblTipoDeVa_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1.setBounds(21, 126, 103, 21);
		getContentPane().add(lblTipoDeVa_1);
		
		JLabel lblTipoDeVa_1_1 = new JLabel("Localidad");
		lblTipoDeVa_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_1.setBounds(21, 178, 103, 21);
		getContentPane().add(lblTipoDeVa_1_1);
		
		JLabel lblTipoDeVa_1_2 = new JLabel("C\u00F3digo postal");
		lblTipoDeVa_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_2.setBounds(21, 232, 128, 21);
		getContentPane().add(lblTipoDeVa_1_2);
		
		JLabel lblTipoDeVa_1_3 = new JLabel("Provincia");
		lblTipoDeVa_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_3.setBounds(21, 285, 103, 21);
		getContentPane().add(lblTipoDeVa_1_3);
		
		JLabel lblTipoDeVa_1_4 = new JLabel("Pa\u00EDs");
		lblTipoDeVa_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_4.setBounds(21, 339, 103, 21);
		getContentPane().add(lblTipoDeVa_1_4);
		
		JLabel lblTipoDeVa_1_5 = new JLabel("Contacto");
		lblTipoDeVa_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_5.setBounds(21, 393, 103, 21);
		getContentPane().add(lblTipoDeVa_1_5);
		
		JLabel lblTipoDeVa_1_6 = new JLabel("Tel\u00E9fono");
		lblTipoDeVa_1_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_6.setBounds(21, 451, 103, 21);
		getContentPane().add(lblTipoDeVa_1_6);
		
		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(143, 233, 288, 22);
		getContentPane().add(txtCp);
		
		txtCif = new JTextField();
		txtCif.setColumns(10);
		txtCif.setBounds(143, 78, 288, 22);
		getContentPane().add(txtCif);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(143, 28, 288, 22);
		getContentPane().add(txtNombre);
		
		txtProvincia = new JTextField();
		txtProvincia.setColumns(10);
		txtProvincia.setBounds(143, 286, 288, 22);
		getContentPane().add(txtProvincia);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(143, 340, 288, 22);
		getContentPane().add(txtPais);
		
		txtContacto = new JTextField();
		txtContacto.setColumns(10);
		txtContacto.setBounds(143, 394, 288, 22);
		getContentPane().add(txtContacto);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(143, 452, 288, 22);
		getContentPane().add(txtTelefono);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
			
		});
		btnCancelar.setBackground(UIManager.getColor("Button.background"));
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBounds(21, 560, 198, 30);
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
				
				boolean cif = ClientesCTRLR.ValidaCIF(txtCif.getText());
				
				if(cif == false) {
					lblError.setText("El CIF introducido no es válido. Inténtelo de nuevo.");
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
				
				ClientesMDL cli = new ClientesMDL(id, txtNombre.getText(), txtCif.getText(), txtVia.getText(), txtLocalidad.getText(), txtCp.getText(), txtProvincia.getText(), txtPais.getText(), txtContacto.getText(), txtTelefono.getText());
				ClientesCTRLR.Grabar(cli);
				dispose();
				
			}
			
		});
		btnGrabar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGrabar.setBackground(UIManager.getColor("Button.background"));
		btnGrabar.setBounds(241, 560, 190, 30);
		getContentPane().add(btnGrabar);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblError.setBounds(73, 507, 358, 22);
		getContentPane().add(lblError);
		
	}

}
