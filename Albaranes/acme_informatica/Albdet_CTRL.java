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

public class Albdet_CTRL {
	
//	public static void Rellenar_JTable(JTable jt, int id) {
//		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
//		dtm.setRowCount(0);
//		ArrayList<Albdet_MDL> lista = Recupera_Por_Id(id);
//		for (Albdet_MDL am : lista) {
//			Vector v = new Vector();
//			v.add(am.getAlbd_id());
//			v.add(am.getAlbd_alb_id());
//			v.add(am.getProd_modelo());
//			v.add(am.getAlbd_cantidad());
//			v.add(am.getAlbd_precio_entrada());
//			dtm.addRow(v);
//		}
//	}
	public static void Rellenar_JTable_Albaranes(JTable jt, int id) {
		
		String sql = "SELECT ad.*, p.prod_marca, p.prod_modelo "
				   + "FROM  albaranes_detalle as ad, productos as p "
				   + "WHERE ad.albd_prod_id = p.prod_id AND albd_alb_id=?";
		
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
				v.add(rs.getInt("albd_alb_id"));
				v.add( rs.getString("prod_marca"));
				v.add( rs.getString("prod_modelo"));
				v.add( rs.getInt("albd_cantidad") );
				v.add( rs.getDouble("albd_precio_entrada") );
				dtm.addRow(v);
			}
			rs.close();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	public static ArrayList<Albdet_MDL> Recupera_Por_Id(int id) {
		ArrayList<Albdet_MDL> lista = new ArrayList<Albdet_MDL>();
		String sql = "SELECT * FROM albaranes_detalle WHERE albd_alb_id=?";
		System.out.println(sql + " "+id);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Albdet_MDL m = new Albdet_MDL(
						rs.getInt("albd_id"), 
						rs.getInt("albd_alb_id"), 
						rs.getInt("albd_prod_id"), 
						rs.getInt("albd_cantidad"), 
						rs.getInt("albd_precio_entrada"));
				lista.add(m);
			}
			rs.close();
			cnx.close();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int Grabar(Albdet_MDL mdl, Connection cnx) {
		
		String sql = "INSERT into albaranes_detalle VALUES " 
					+ "(null," 
					+ ""+mdl.getAlbd_alb_id()+"," 
					+ ""+mdl.getAlbd_prod_id()+"," 
					+ ""+mdl.getAlbd_cantidad()+"," 
					+ ""+mdl.getAlbd_precio_entrada()+")";
		
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
	
	public static int Borrar(String id, Connection cnx) {
		String sql = "DELETE FROM albaranes_detalle WHERE albd_alb_id='"+id+"'";
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
