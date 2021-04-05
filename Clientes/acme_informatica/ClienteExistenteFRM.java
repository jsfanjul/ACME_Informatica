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
import javax.swing.JComboBox;
import java.awt.Cursor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteExistenteFRM extends JDialog{
	
	private JTextField txtVia;
	private JTextField txtLocalidad;
	private JTextField txtCp;
	private JTextField txtCif;
	private JTextField txtProvincia;
	private JTextField txtPais;
	private JTextField txtContacto;
	private JTextField txtTelefono;
	private JComboBox cmbNombre;
	private JLabel lblNombre;
	
	public ClienteExistenteFRM(int id) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setTitle("FORMULARIO DE CLIENTE EXISTENTE");
		getContentPane().setBackground(Color.CYAN);
		setSize(new Dimension(473, 657));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(25, 27, 80, 21);
		getContentPane().add(lblNewLabel);
		
		txtVia = new JTextField();
		txtVia.setColumns(10);
		txtVia.setBounds(147, 128, 288, 22);
		getContentPane().add(txtVia);
		
		JLabel lblTipoDeVa = new JLabel("CIF");
		lblTipoDeVa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa.setBounds(25, 78, 103, 21);
		getContentPane().add(lblTipoDeVa);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(147, 180, 288, 22);
		getContentPane().add(txtLocalidad);
		
		JLabel lblTipoDeVa_1 = new JLabel("Tipo de v\u00EDa");
		lblTipoDeVa_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1.setBounds(25, 127, 103, 21);
		getContentPane().add(lblTipoDeVa_1);
		
		JLabel lblTipoDeVa_1_1 = new JLabel("Localidad");
		lblTipoDeVa_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_1.setBounds(25, 179, 103, 21);
		getContentPane().add(lblTipoDeVa_1_1);
		
		JLabel lblTipoDeVa_1_2 = new JLabel("C\u00F3digo postal");
		lblTipoDeVa_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_2.setBounds(25, 233, 128, 21);
		getContentPane().add(lblTipoDeVa_1_2);
		
		JLabel lblTipoDeVa_1_3 = new JLabel("Provincia");
		lblTipoDeVa_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_3.setBounds(25, 286, 103, 21);
		getContentPane().add(lblTipoDeVa_1_3);
		
		JLabel lblTipoDeVa_1_4 = new JLabel("Pa\u00EDs");
		lblTipoDeVa_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_4.setBounds(25, 340, 103, 21);
		getContentPane().add(lblTipoDeVa_1_4);
		
		JLabel lblTipoDeVa_1_5 = new JLabel("Contacto");
		lblTipoDeVa_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_5.setBounds(25, 394, 103, 21);
		getContentPane().add(lblTipoDeVa_1_5);
		
		JLabel lblTipoDeVa_1_6 = new JLabel("Tel\u00E9fono");
		lblTipoDeVa_1_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTipoDeVa_1_6.setBounds(25, 452, 103, 21);
		getContentPane().add(lblTipoDeVa_1_6);
		
		txtCp = new JTextField();
		txtCp.setColumns(10);
		txtCp.setBounds(147, 234, 288, 22);
		getContentPane().add(txtCp);
		
		txtCif = new JTextField();
		txtCif.setColumns(10);
		txtCif.setBounds(147, 79, 288, 22);
		getContentPane().add(txtCif);
		
		txtProvincia = new JTextField();
		txtProvincia.setColumns(10);
		txtProvincia.setBounds(147, 287, 288, 22);
		getContentPane().add(txtProvincia);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(147, 341, 288, 22);
		getContentPane().add(txtPais);
		
		txtContacto = new JTextField();
		txtContacto.setColumns(10);
		txtContacto.setBounds(147, 395, 288, 22);
		getContentPane().add(txtContacto);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(147, 453, 288, 22);
		getContentPane().add(txtTelefono);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?", "CONFIRMACIÓN PARA BORRAR", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (resp == JOptionPane.YES_OPTION) {
					int numregs = ClientesCTRLR.Borrar(id);
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
		btnBorrar.setBounds(173, 561, 119, 30);
		getContentPane().add(btnBorrar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
			
		});
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBackground(UIManager.getColor("Button.background"));
		btnCancelar.setBounds(25, 561, 119, 30);
		getContentPane().add(btnCancelar);
		
		JButton btnGrabar = new JButton("GRABAR");
		btnGrabar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ClientesComboMDL c_cli = (ClientesComboMDL) cmbNombre.getSelectedItem();
				
				ClientesMDL cli = new ClientesMDL(id, c_cli.getCli_nombre(), txtCif.getText(), txtVia.getText(), txtLocalidad.getText(), txtCp.getText(), txtProvincia.getText(), 
						txtPais.getText(), txtContacto.getText(), txtTelefono.getText());
				
				ClientesCTRLR.Grabar(cli);
				
				dispose();
				
			}	
			
		});
		btnGrabar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGrabar.setBackground(UIManager.getColor("Button.background"));
		btnGrabar.setBounds(316, 561, 119, 30);
		getContentPane().add(btnGrabar);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblError.setBounds(77, 508, 358, 22);
		getContentPane().add(lblError);
		
		cmbNombre = new JComboBox();
		cmbNombre.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == cmbNombre) {
					// lblNombre.setText(cmbNombre.getSelectedItem() + "");
					Object nombre = cmbNombre.getSelectedItem();
					
					ClientesMDL cli = ClientesCTRLR.Recupera_Por_Nombre(nombre);
					
					cmbNombre.setSelectedItem(nombre);
					txtCif.setText(cli.getCli_cif());
					txtVia.setText(cli.getCli_tipo_via());
					txtLocalidad.setText(cli.getCli_localidad());
					txtCp.setText(cli.getCli_codigo_postal());
					txtProvincia.setText(cli.getCli_provincia());
					txtPais.setText(cli.getCli_pais());
					txtContacto.setText(cli.getCli_contacto());
					txtTelefono.setText(cli.getCli_telefono());
					
				}
				
			}
			
		});
		cmbNombre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNombre.setBounds(147, 28, 288, 22);
		getContentPane().add(cmbNombre);
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(10, 508, 57, 22);
		getContentPane().add(lblNombre);
		
		if(id != 0) {
			
			ClientesMDL cli = ClientesCTRLR.Recupera_Por_Id(id);
			
			ClientesComboMDL cli_nom = ClientesCTRLR.Rellena_JCombo(cmbNombre, cli.getCli_id());
			txtCif.setText(cli.getCli_cif());
			txtVia.setText(cli.getCli_tipo_via());
			txtLocalidad.setText(cli.getCli_localidad());
			txtCp.setText(cli.getCli_codigo_postal());
			txtProvincia.setText(cli.getCli_provincia());
			txtPais.setText(cli.getCli_pais());
			txtContacto.setText(cli.getCli_contacto());
			txtTelefono.setText(cli.getCli_telefono());
			
		}
		
	}
}
