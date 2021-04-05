package acme_informatica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ProductosFRM extends JDialog {
	
	private JTextField txtModelo;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTextField txtFoto;
	private JLabel lblError;
	private JLabel lblImagen;
	private JButton btnInsertar;
	private JTextField txtMarca;
	private JTextArea txtCaract;
	private JTextArea txtObserv;
	private JComboBox cmbCategoria;

	public ProductosFRM(int id) {
		
		setTitle("Productos");
		getContentPane().setBackground(Color.CYAN);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(new Dimension(650, 700));
		getContentPane().setLayout(null);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMarca.setBounds(28, 52, 66, 25);
		getContentPane().add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblModelo.setBounds(28, 95, 93, 25);
		getContentPane().add(lblModelo);

		JLabel lblCaractersticas = new JLabel("Caracter\u00EDsticas");
		lblCaractersticas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCaractersticas.setBounds(23, 144, 142, 25);
		getContentPane().add(lblCaractersticas);

		JLabel lblFoto = new JLabel("Foto");
		lblFoto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFoto.setBounds(28, 263, 46, 14);
		getContentPane().add(lblFoto);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrecio.setBounds(28, 309, 71, 25);
		getContentPane().add(lblPrecio);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(28, 356, 66, 25);
		getContentPane().add(lblStock);

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblObservaciones.setBounds(28, 405, 137, 25);
		getContentPane().add(lblObservaciones);

		JLabel lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategoria.setBounds(28, 506, 93, 25);
		getContentPane().add(lblCategoria);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblError.setBounds(164, 545, 304, 30);
		getContentPane().add(lblError);

		txtMarca = new JTextField();
		txtMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMarca.setSize(new Dimension(237, 25));
		txtMarca.setColumns(10);
		txtMarca.setBounds(203, 52, 237, 25);
		getContentPane().add(txtMarca);

		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtModelo.setSize(new Dimension(237, 25));
		txtModelo.setColumns(10);
		txtModelo.setBounds(203, 95, 237, 25);
		getContentPane().add(txtModelo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(203, 141, 360, 95);
		getContentPane().add(scrollPane);

		txtCaract = new JTextArea();
		txtCaract.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtCaract.setLineWrap(true);
		scrollPane.setViewportView(txtCaract);

		txtFoto = new JTextField();
		txtFoto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFoto.setBounds(203, 260, 237, 25);
		getContentPane().add(txtFoto);
		txtFoto.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) 
				        && (caracter != KeyEvent.VK_BACK_SPACE)
				        && (caracter != '.' || txtPrecio.getText().contains(".")) ) {
				            e.consume();
				}
			}
		});
		
		
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPrecio.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(203, 311, 71, 25);
		getContentPane().add(txtPrecio);

		txtStock = new JTextField();
		txtStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
									 
						 char c = e.getKeyChar();
						 
						 if (c == '.') {
							 e.consume();
						 }
						 
						 if(c < '0' || c > '9') {
							e.consume();
							 }
						
					 }
					
		});
		txtStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtStock.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtStock.setColumns(10);
		txtStock.setBounds(203, 355, 71, 25);
		getContentPane().add(txtStock);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(205, 406, 358, 82);
		getContentPane().add(scrollPane_1);

		txtObserv = new JTextArea();
		txtObserv.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtObserv.setLineWrap(true);
		scrollPane_1.setViewportView(txtObserv);

		cmbCategoria = new JComboBox<CategoriasMDL>();
		cmbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbCategoria.setBounds(203, 509, 237, 25);
		getContentPane().add(cmbCategoria);

		btnInsertar = new JButton("Enviar");
		btnInsertar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//VALIDAR
				if (Validar() == false) {
					return;
				} 
				
				
					
				
				
				
				//CARGAR COMBO
				JComboMDL jc = (JComboMDL) cmbCategoria.getSelectedItem();

				ProductosMDL prod = new ProductosMDL(
						id,
						txtMarca.getText(),
						txtModelo.getText(),
						txtCaract.getText(),
						txtFoto.getText(),
						Double.parseDouble(txtPrecio.getText()),
						Integer.parseInt(txtStock.getText()),
						txtObserv.getText(),
						jc.getId());
				
					ProductosCTRLR.Insertar(prod);
					dispose();
			}
		});

		btnInsertar.setBounds(409, 594, 89, 25);
		getContentPane().add(btnInsertar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?", "AVISO", JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
				
				ProductosCTRLR.Borrar(id);			
				
				int nregs = ProductosCTRLR.Borrar(id);
				
				if (nregs != 0) {
					lblError.setText("No he podido borrar");
					return;
				}
				dispose();
				
				}
			}
		});
		btnBorrar.setBounds(278, 594, 82, 25);
		getContentPane().add(btnBorrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(147, 594, 89, 25);
		getContentPane().add(btnCancelar);

		JButton btnExaminar = new JButton("Examinar...");
		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}

				File ArchivoSeleccionado;
				JFileChooser SeleccionarArchivo;
				SeleccionarArchivo = new JFileChooser();
				SeleccionarArchivo.showOpenDialog(null);
				ArchivoSeleccionado = SeleccionarArchivo.getSelectedFile();
				
				String nombre = ArchivoSeleccionado.getName();
				
				txtFoto.setText(nombre);
				
				ProductosCTRLR.AjustarImagen(lblImagen, "/Fotografias/" + txtFoto.getText());

				
			}
		});
		btnExaminar.setToolTipText("Seleccione una imagen");
		btnExaminar.setBounds(474, 260, 103, 25);
		getContentPane().add(btnExaminar);

		lblImagen = new JLabel("");
		lblImagen.setBounds(456, 294, 143, 101);
		getContentPane().add(lblImagen);
		
		JLabel lblTitulo = new JLabel("Nuevo Producto");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(123, 11, 410, 30);
		getContentPane().add(lblTitulo);

	

		if (id == 0) {
			btnBorrar.setVisible(false);
			CategoriasCTRLR.Rellena_Combo(cmbCategoria, 0);

		} else {

			lblTitulo.setText("Producto");
			btnInsertar.setText("Modificar");
			ProductosMDL mdl = ProductosCTRLR.Recupera_por_id(id);
			txtMarca.setText(mdl.getProd_marca());
			txtModelo.setText(mdl.getProd_modelo());
			txtCaract.setText(mdl.getProd_caracteristicas());
			txtFoto.setText(mdl.getProd_foto());
			ProductosCTRLR.AjustarImagen(lblImagen, "src/Fotografias/" + txtFoto.getText());
			txtPrecio.setText(mdl.getProd_precio_venta() + "");
			txtStock.setText(mdl.getProd_stock_teorico() + "");
			txtObserv.setText(mdl.getProd_observaciones());
			CategoriasCTRLR.Rellena_Combo(cmbCategoria, mdl.getProd_cat_id());

		}

	}
	public Boolean Validar() {
	
	lblError.setText("");
	
	if (txtMarca.getText().trim().equals("")) {
		lblError.setText("Por favor introduzca una marca");
		txtMarca.grabFocus();
		return false;
	}

	if (txtModelo.getText().trim().equals("")) {
		lblError.setText("Por favor introduzca un modelo");
		txtModelo.grabFocus();
		return false;
	}

	if (txtCaract.getText().trim().equals("")) {
		lblError.setText("Por favor introduzca características");
		txtCaract.grabFocus();
		return false;
	}
	if (txtFoto.getText().trim().equals("")) {
		lblError.setText("Por favor seleccione una imagen del producto");
		txtFoto.grabFocus();
		return false;
	}
	if (txtPrecio.getText().trim().equals("")) {
		lblError.setText("Por favor establezca un precio");
		txtPrecio.grabFocus();
		return false;
	}
	
	if (txtStock.getText().trim().equals("")) {
		lblError.setText("Por favor introduzca cantidad de stock ");
		txtStock.grabFocus();
		return false;
	}
	if (txtObserv.getText().trim().equals("")) {
		lblError.setText("Por favor introduzca observaciones");
		txtObserv.grabFocus();
		return false;
	}
	
	try {
		Double precio = Double.parseDouble(txtPrecio.getText().trim());
		int Stock = Integer.parseInt(txtStock.getText().trim());
		return true;
		
		} catch(Exception e) {
			return false;
		}
		
	}

}
