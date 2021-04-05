package acme_informatica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FacDet_CTRLR {

	/* RELLENAR TABLA */

//	public static void Rellenar_JTable(JTable jt, int id) {
//		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
//		dtm.setRowCount(0);
//		ArrayList<FacDet_MDL> lista = Recupera_Por_Id(id);
//		for (FacDet_MDL f : lista) {
//			Vector v = new Vector();
//			v.add(f.getFacd_id());
//			v.add(f.getFacd_fac_id());
//			v.add(f.getCli_nombre());
//			v.add(f.getFacd_cantidad());
//			v.add(f.getFacd_precio_venta());
//			dtm.addRow(v);
//		}
//	}

	/* RELLENAR JTABLE PRODUCTOS */
	public static void Rellenar_JTable_Productos(JTable jt, int id) {
		FacDet_MDL f = null;

		String sql = "SELECT fd.*, p.prod_marca, p.prod_modelo " + "FROM facturas_detalle as fd, productos as p "
				+ "WHERE fd.facd_prod_id = p.prod_id AND facd_fac_id=?";

		System.out.println(sql);
		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
		dtm.setRowCount(0);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("facd_prod_id"));
				v.add(rs.getString("prod_marca"));
				v.add(rs.getString("prod_modelo"));
				v.add(rs.getInt("facd_cantidad"));
				v.add(rs.getDouble("facd_precio_venta"));
				dtm.addRow(v);
			}
			rs.close();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* RECUPERA POR ID */
	public static FacDet_MDL Recupera_Por_Id(int id) {
		FacDet_MDL f = null;
		String sql = "SELECT * FROM facturas_detalle WHERE facd_fac_id=?";
		System.out.println(sql + " " + id);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FacDet_MDL m = new FacDet_MDL(rs.getInt("facd_id"), rs.getInt("facd_fac_id"), rs.getInt("facd_prod_id"),
						rs.getInt("facd_cantidad"), rs.getInt("facd_precio_venta"));
				return f;
			}
			rs.close();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* GRABAR */
	public static int Grabar(FacDet_MDL mdl, Connection cnx) {

		String sql = "INSERT into facturas_detalle VALUES " + "(null," + "" + mdl.getFacd_fac_id() + "," + ""
				+ mdl.getFacd_prod_id() + "," + "" + mdl.getFacd_cantidad() + "," + "" + mdl.getFacd_precio_venta()
				+ ")";

		System.out.println(sql);

		try {
			Statement comando = cnx.createStatement();
			int regs = comando.executeUpdate(sql);
			return regs;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* BORRAR */
	public static int Borrar(String id, Connection cnx) {
		String sql = "DELETE FROM facturas_detalle WHERE facd_fac_id='" + id + "'";
		System.out.println(sql);
		try {
			Statement comando = cnx.createStatement();
			int n = comando.executeUpdate(sql);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
