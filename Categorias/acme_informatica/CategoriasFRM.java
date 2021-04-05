package acme_informatica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CategoriasFRM extends JDialog {
	private JTextField txtCat;
	private JLabel lblError;

	public CategoriasFRM(int id) {
		getContentPane().setBackground(Color.CYAN);
		setSize(new Dimension(455, 300));
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		txtCat = new JTextField();
		txtCat.setBounds(86, 102, 275, 25);
		getContentPane().add(txtCat);
		txtCat.setColumns(10);

		JLabel lblCat = new JLabel("Nueva categor\u00EDa");
		lblCat.setHorizontalAlignment(SwingConstants.CENTER);
		lblCat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCat.setBounds(91, 29, 246, 25);
		getContentPane().add(lblCat);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(35, 194, 89, 29);
		getContentPane().add(btnCancelar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?", "AVISO", JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
				
				CategoriasCTRLR.Borrar(id);			
				
				int nregs = CategoriasCTRLR.Borrar(id);
				
				if (nregs != 0) {
					lblError.setText("No he podido borrar");
					return;
				}
				dispose();
				
				}
			}
		});
		btnBorrar.setBounds(168, 194, 89, 29);
		getContentPane().add(btnBorrar);

		JButton btnEnviar = new JButton("Agregar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Validar que los datos son correctos
				
				
				if (txtCat.getText().trim().equals("")) {
					lblError.setText("Por favor ingrese una categoría");
					txtCat.grabFocus();
					return;
				}
				
				
				CategoriasMDL mdl = new CategoriasMDL(
						id,
						txtCat.getText());
				
				CategoriasCTRLR.Insertar(mdl);				
				dispose();
			}
		});
		btnEnviar.setBounds(298, 194, 89, 29);
		getContentPane().add(btnEnviar);

		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblError.setForeground(Color.RED);
		lblError.setBounds(47, 148, 359, 25);
		getContentPane().add(lblError);

		if (id == 0) {
			btnBorrar.setVisible(false);

		} else {
			btnEnviar.setText("Modificar");
			CategoriasMDL mdl = CategoriasCTRLR.Recupera_por_id(id);
			lblCat.setText("Categoría");
			txtCat.setText(mdl.getCat_categoria());

		}
	}

	
}
