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
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProveedorExistenteFRM extends JDialog {
	
	private JTextField txtVia;
	private JTextField txtLocalidad;
	private JTextField txtCp;
	private JTextField txtCif;
	private JTextField txtProvincia;
	private JTextField txtPais;
	private JTextField txtContacto;
	private JTextField txtTelefono;
	private JComboBox cmbNombre;
	
	public ProveedorExistenteFRM(int id) {
		
		getContentPane().setBackground(Color.CYAN);
		setTitle("FORMULARIO DE PROVEEDOR EXISTENTE");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(479, 657));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(26, 26, 80, 21);
		getContentPane().add(lblNewLabel);
		
		txtVia = new JTextField();
		txtVia.setColumns(10);
		txtVia.setBounds(148, 127, 288, 22);
		getContentPane().add(txtVia);
		
		JLabel lblTipoDeVa = new JLabel("CIF");
		lblTipoDeVa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa.setBounds(26, 77, 103, 21);
		getContentPane().add(lblTipoDeVa);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(148, 179, 288, 22);
		getContentPane().add(txtLocalidad);
		
		JLabel lblTipoDeVa_1 = new JLabel("Tipo de v\u00EDa");
		lblTipoDeVa_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1.setBounds(26, 126, 103, 21);
		getContentPane().add(lblTipoDeVa_1);
		
		JLabel lblTipoDeVa_1_1 = new JLabel("Localidad");
		lblTipoDeVa_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_1.setBounds(26, 178, 103, 21);
		getContentPane().add(lblTipoDeVa_1_1);
		
		JLabel lblTipoDeVa_1_2 = new JLabel("C\u00F3digo postal");
		lblTipoDeVa_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_2.setBounds(26, 232, 128, 21);
		getContentPane().add(lblTipoDeVa_1_2);
		
		JLabel lblTipoDeVa_1_3 = new JLabel("Provincia");
		lblTipoDeVa_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_3.setBounds(26, 285, 103, 21);
		getContentPane().add(lblTipoDeVa_1_3);
		
		JLabel lblTipoDeVa_1_4 = new JLabel("Pa\u00EDs");
		lblTipoDeVa_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_4.setBounds(26, 339, 103, 21);
		getContentPane().add(lblTipoDeVa_1_4);
		
		JLabel lblTipoDeVa_1_5 = new JLabel("Contacto");
		lblTipoDeVa_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_5.setBounds(26, 393, 103, 21);
		getContentPane().add(lblTipoDeVa_1_5);
		
		JLabel lblTipoDeVa_1_6 = new JLabel("Tel\u00E9fono");
		lblTipoDeVa_1_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_6.setBounds(26, 451, 103, 21);
		getContentPane().add(lblTipoDeVa_1_6);
		
		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(148, 233, 288, 22);
		getContentPane().add(txtCp);
		
		txtCif = new JTextField();
		txtCif.setColumns(10);
		txtCif.setBounds(148, 78, 288, 22);
		getContentPane().add(txtCif);
		
		txtProvincia = new JTextField();
		txtProvincia.setColumns(10);
		txtProvincia.setBounds(148, 286, 288, 22);
		getContentPane().add(txtProvincia);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(148, 340, 288, 22);
		getContentPane().add(txtPais);
		
		txtContacto = new JTextField();
		txtContacto.setColumns(10);
		txtContacto.setBounds(148, 394, 288, 22);
		getContentPane().add(txtContacto);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(148, 452, 288, 22);
		getContentPane().add(txtTelefono);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?", "CONFIRMACIÓN PARA BORRAR", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resp == JOptionPane.YES_OPTION) {
					int numregs = ProveedoresCTRLR.Borrar(id);
					if (numregs == 0) {
						JOptionPane.showMessageDialog(null, "No ha podido ser borrado");
						return;
					}
				}
				
				dispose();
				
			}
			
		});
		btnBorrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBorrar.setBackground(UIManager.getColor("Button.background"));
		btnBorrar.setBounds(173, 563, 119, 30);
		getContentPane().add(btnBorrar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
			
		});
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBackground(UIManager.getColor("Button.background"));
		btnCancelar.setBounds(26, 563, 119, 30);
		getContentPane().add(btnCancelar);
		
		JButton btnGrabar = new JButton("GRABAR");
		btnGrabar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ProveedoresComboMDL c_prov = (ProveedoresComboMDL) cmbNombre.getSelectedItem();
				
				ProveedoresMDL prov = new ProveedoresMDL(id, c_prov.getProv_nombre(), txtCif.getText(), txtVia.getText(), txtLocalidad.getText(), txtCp.getText(), txtProvincia.getText(), 
						txtPais.getText(), txtContacto.getText(), txtTelefono.getText());
				
				ProveedoresCTRLR.Grabar(prov);
				
				dispose();
				
			}
			
		});
		btnGrabar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGrabar.setBackground(UIManager.getColor("Button.background"));
		btnGrabar.setBounds(317, 563, 119, 30);
		getContentPane().add(btnGrabar);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblError.setBounds(78, 509, 358, 22);
		getContentPane().add(lblError);
		
		cmbNombre = new JComboBox();
		cmbNombre.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == cmbNombre) {
					// lblNombre.setText(cmbNombre.getSelectedItem() + "");
					Object nombre = cmbNombre.getSelectedItem();
					
					ProveedoresMDL prov = ProveedoresCTRLR.Recupera_Por_Nombre(nombre);
					
					cmbNombre.setSelectedItem(nombre);
					txtCif.setText(prov.getProv_cif());
					txtVia.setText(prov.getProv_tipo_via());
					txtLocalidad.setText(prov.getProv_localidad());
					txtCp.setText(prov.getProv_codigo_postal());
					txtProvincia.setText(prov.getProv_provincia());
					txtPais.setText(prov.getProv_pais());
					txtContacto.setText(prov.getProv_contacto());
					txtTelefono.setText(prov.getProv_telefono());
					
				}
				
			}
			
		});
		cmbNombre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNombre.setBounds(148, 27, 288, 22);
		getContentPane().add(cmbNombre);
		
		if(id != 0) {
			
			ProveedoresMDL prov = ProveedoresCTRLR.Recupera_Por_Id(id);
			
			ProveedoresComboMDL prov_nom = ProveedoresCTRLR.Rellena_JCombo(cmbNombre, prov.getProv_id());
			txtCif.setText(prov.getProv_cif());
			txtVia.setText(prov.getProv_tipo_via());
			txtLocalidad.setText(prov.getProv_localidad());
			txtCp.setText(prov.getProv_codigo_postal());
			txtProvincia.setText(prov.getProv_provincia());
			txtPais.setText(prov.getProv_pais());
			txtContacto.setText(prov.getProv_contacto());
			txtTelefono.setText(prov.getProv_telefono());
			
		}
		
	}
}
