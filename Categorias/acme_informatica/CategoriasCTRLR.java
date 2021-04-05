package acme_informatica;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CategoriasCTRLR {

	public static ArrayList<CategoriasMDL> Recupera_Todo(String filtro) {
		ArrayList<CategoriasMDL> lista = new ArrayList<CategoriasMDL>();

		String sql = "SELECT * FROM categorias" + " WHERE cat_categoria LIKE '%" + filtro + "%'";
		//System.out.println(sql);

		try {
			Connection cnx = new Conexion().getConection();
			Statement Comando = cnx.createStatement();
			ResultSet rs = Comando.executeQuery(sql);
			while (rs.next()) {

				CategoriasMDL mdl = new CategoriasMDL(
						rs.getInt("cat_id"),
						rs.getString("cat_categoria"));
				lista.add(mdl);

			}
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;

	}

	public static CategoriasMDL Recupera_por_id(int id) {
		CategoriasMDL mdl = null;
		String sql = "SELECT * FROM categorias WHERE cat_id = " + id;
		//System.out.println(sql);

		try {
			Connection cnx = new Conexion().getConection();
			Statement Comando = cnx.createStatement();
			ResultSet rs = Comando.executeQuery(sql);
			while (rs.next()) {
				mdl = new CategoriasMDL(
						rs.getInt("cat_id"),
						rs.getString("cat_categoria"));

			}
			rs.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mdl;
	}

	public static int Insertar(CategoriasMDL mdl) {
		int reg = 0;
		
		String sql = "";
		
		if (mdl.getCat_id() == 0) {

			sql = "INSERT INTO categorias VALUES (null, '" + mdl.getCat_categoria() + "')";

		} else {
			sql = "UPDATE categorias SET " + "cat_categoria = '" + mdl.getCat_categoria() + "'" + " WHERE cat_id = "
					+ mdl.getCat_id();
		}
		System.out.println(sql);

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
		String sql = "DELETE FROM categorias WHERE cat_id = " + id;
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

	public static void Rellena_Combo(JComboBox<JComboMDL> jc, int cat_id) {
		ArrayList<CategoriasMDL> lista = Recupera_Todo("");
		jc.removeAllItems();
		int pos = 0;
		for (CategoriasMDL c : lista) {
			JComboMDL cat = new JComboMDL(c.getCat_id(), c.getCat_categoria());

			jc.addItem(cat);

			if (c.getCat_id() == cat_id) {
				jc.setSelectedIndex(pos);
			}
			pos++;
		}
	}

	public static void Rellena_Tabla(JTable tabla, String filtro) {
		DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
		dtm.setRowCount(0);
		ArrayList<CategoriasMDL> lista = Recupera_Todo(filtro);

		for (CategoriasMDL c : lista) {
			Vector v = new Vector();
			v.add(c.getCat_id());
			v.add(c.getCat_categoria());
			dtm.addRow(v);

		}
	}
}
