package acme_informatica;


import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductosCTRLR {

	public static ArrayList<ProductosMDL> Recupera_Todos(String filtro) {
		ArrayList<ProductosMDL> lista = new ArrayList<ProductosMDL>();

		String sql = "SELECT p.*, c.cat_categoria " +
				" 	  FROM productos as p INNER JOIN categorias as c " +
				"	  ON p.prod_cat_id = c.cat_id " +
				" 	  WHERE p.prod_marca LIKE '%" + filtro + "%' OR " +
				" 			p.prod_modelo LIKE '%" + filtro + "%' OR " +
				"			c.cat_categoria LIKE '%" + filtro + "%'" +
				" 	  ORDER BY p.prod_marca, c.cat_categoria";

		//System.out.println(sql);

		try {
			Connection cnx = new Conexion().getConection();
			Statement Comando = cnx.createStatement();
			ResultSet rs = Comando.executeQuery(sql);
			while (rs.next()) {
				ProductosMDL mdl = new ProductosMDL(
						rs.getInt("prod_id"),
						rs.getString("prod_marca"),
						rs.getString("prod_modelo"),
						rs.getString("prod_caracteristicas"),
						rs.getString("prod_foto"),
						rs.getDouble("prod_precio_venta"),
						rs.getInt("prod_stock_teorico"),
						rs.getString("prod_observaciones"),
						rs.getInt("prod_cat_id"),
						rs.getString("cat_categoria"));
				lista.add(mdl);
			}
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public static ProductosMDL Recupera_por_id(int id) {
		ProductosMDL mdl = null;
		String sql = "SELECT * FROM productos WHERE prod_id = " + id;
		//System.out.println(sql);

		try {
			Connection cnx = new Conexion().getConection();
			Statement Comando = cnx.createStatement();
			ResultSet rs = Comando.executeQuery(sql);
			while (rs.next()) {
				mdl = new ProductosMDL(
						rs.getInt("prod_id"),
						rs.getString("prod_marca"),
						rs.getString("prod_modelo"),
						rs.getString("prod_caracteristicas"),
						rs.getString("prod_foto"),
						rs.getDouble("prod_precio_venta"),
						rs.getInt("prod_stock_teorico"),
						rs.getString("prod_observaciones"),
						rs.getInt("prod_cat_id"));
			}
			rs.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mdl;

	}

	public static int Insertar(ProductosMDL mdl) {
		int reg = 0;
		String sql = "";
		if (mdl.getProd_id() == 0) {

			sql = "INSERT INTO productos " +
				  "SET prod_marca = '" + mdl.getProd_marca() + "', " +
				  "	 prod_modelo = '" + mdl.getProd_modelo() + "', " +
				  "	 prod_caracteristicas = '" + mdl.getProd_caracteristicas() + "', " +
				  "	 prod_foto = '" + mdl.getProd_foto() + "', " +
				  "	 prod_precio_venta = '" + mdl.getProd_precio_venta() + "', " +
				  "	 prod_stock_teorico = '" + mdl.getProd_stock_teorico() + "', " +
				  "	 prod_observaciones = '" + mdl.getProd_observaciones() + "', " +
				  "	 prod_cat_id = '"	+ mdl.getProd_cat_id() + "'";
		} else {
			
			sql = "UPDATE productos " +
				  "SET prod_marca = '"  + mdl.getProd_marca() + "', " +
				  "	 prod_modelo = '" + mdl.getProd_modelo() + "', " +
				  "	 prod_caracteristicas = '" + mdl.getProd_caracteristicas() + "', " +
				  "	 prod_foto = '" + mdl.getProd_foto() + "', " +
				  "	 prod_precio_venta = '"	+ mdl.getProd_precio_venta() + "', " +
				  "	 prod_stock_teorico = '" + mdl.getProd_stock_teorico() + "', " +
				  "	 prod_observaciones = '" + mdl.getProd_observaciones() + "', " +
				  "	prod_cat_id = '" + mdl.getProd_cat_id() + "'" +
				  "WHERE prod_id = " + mdl.getProd_id();
		}
		//System.out.println(sql);

		try {
			Connection cnx = new Conexion().getConection();
			Statement comando = cnx.createStatement();
			reg = comando.executeUpdate(sql);
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reg;
	}

	public static int Borrar(int id) {
		String sql = "DELETE FROM productos WHERE prod_id = " + id;
		//System.out.println(sql);
		
		int reg = 0;
		try {
			Connection cnx = new Conexion().getConection();
			Statement comando = cnx.createStatement();
			reg = comando.executeUpdate(sql);
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reg;
	}

	public static void Rellena_JTable(JTable tabla, String filtro) {
		DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
		dtm.setRowCount(0);
		ArrayList<ProductosMDL> lista = Recupera_Todos(filtro);

		for (ProductosMDL p : lista) {
			Vector v = new Vector();
			v.add(p.getProd_id());
			v.add(p.getProd_marca());
			v.add(p.getProd_modelo());
			v.add(p.getProd_caracteristicas());
			v.add(p.getProd_precio_venta());
			v.add(p.getProd_stock_teorico());
			v.add(p.getProd_observaciones());
			v.add(p.getCat_categoria());
			dtm.addRow(v);
		}

	}
	
	public ArrayList<ProductosMDL> Recupera_Todos(JButton btnRellenar){
		
		ArrayList<ProductosMDL> lista = new ArrayList<ProductosMDL>();
		String sql = "SELECT * FROM productos WHERE prod_id OR prod_marca OR prod_modelo " + 
					 "OR prod_caracteristicas OR prod_precio_venta OR prod_stock_teorico" + "\n";
		// System.out.println(sql);
		
		Conexion conexion = new Conexion();
		Connection cnx = conexion.getConection();
		
		if (cnx == null) {
			JOptionPane.showMessageDialog(null, "No tengo acceso a la BBDD pipiolo, inténtelo de nuevo más tarde");
			System.exit(-1);
		}
		
		try {
			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while (rs.next() == true) {
				ProductosMDL p = new ProductosMDL(
						rs.getInt("prod_id"),
						rs.getString("prod_marca"),
						rs.getString("prod_modelo"),
						rs.getString("prod_caracteristicas"),
						rs.getString("prod_foto"),
						rs.getFloat("prod_precio_venta"),
						rs.getInt("prod_stock_teorico"),
						rs.getString("prod_observaciones"), 
						0);
				lista.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	public void Rellenar_JTable(JTable jt) {
		
		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
		
		JButton btnRellenar = null;
		
		ArrayList<ProductosMDL> lista = Recupera_Todos(btnRellenar);
		for (ProductosMDL pm : lista) {
			
			Vector v = new Vector();
			v.add(pm.getProd_id());
			v.add(pm.getProd_modelo());
			v.add(pm.getProd_marca());
			v.add(pm.getProd_caracteristicas());
			v.add(pm.getProd_precio_venta());
			v.add(pm.getProd_stock_teorico());
			dtm.addRow(v);
			
		}
		
	}

	public static ImageIcon AjustarImagen(JLabel lbl, String ruta) {

		rsscalelabel.RSScaleLabel.setScaleLabel(lbl, ruta);
		return null;

	}
	
	public static void Rellenar_Combo(JComboBox<ProductosMDL> jc, int id) {
		
		ArrayList<ProductosMDL> lista = Recupera_Todos("");
		jc.removeAllItems();
		int pos = 0;
		for (ProductosMDL a : lista) {
			jc.addItem(a);
			if (a.getProd_id() == id) {
				jc.setSelectedIndex(pos);
			}
			pos++;
		}
	}
}
