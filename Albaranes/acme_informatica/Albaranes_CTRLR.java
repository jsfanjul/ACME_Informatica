package acme_informatica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Albaranes_CTRLR {
	public static ArrayList<Albaran_MDL> Recupera_Todos(String filtro) {
		ArrayList<Albaran_MDL> lista = new ArrayList<Albaran_MDL>();
		String sql = "SELECT a.*, p.prov_nombre " + 
		"FROM albaranes as a JOIN proveedores as p " +
		"ON a.alb_prov_id=p.prov_id " + 
		"WHERE p.prov_nombre like '%" + filtro + "%' "
		+ "ORDER BY a.alb_fecha_entrada";
		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();

			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while (rs.next() == true) {
				Albaran_MDL m = new Albaran_MDL(
						rs.getInt("alb_id"), 
						rs.getString("alb_fecha_prov"),
						rs.getInt("alb_prov_id"), 
						rs.getString("alb_fecha_entrada"), 
						rs.getString("prov_nombre"));
				lista.add(m);
			}
			c.Desconectar(cnx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static void Rellenar_JTable(JTable jt, String filtro) {
		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
		dtm.setRowCount(0);
		ArrayList<Albaran_MDL> lista = Recupera_Todos(filtro);
		for (Albaran_MDL am : lista) {
			Vector v = new Vector();
			v.add(am.getAlb_id());
			v.add(am.getProv_nombre());
			v.add(am.getAlb_fecha_prov());
			v.add(am.getAlb_fecha_entrada());
			dtm.addRow(v);
		}
	}

	public static Albaran_MDL Recupera_Por_Id(int id) {
		Albaran_MDL m = null;
		String sql = "SELECT * FROM albaranes WHERE alb_id=?";
		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				m = new Albaran_MDL(
						rs.getInt("alb_id"), 
						rs.getString("alb_fecha_prov"), 
						rs.getInt("alb_prov_id"),
						rs.getString("alb_fecha_entrada"));
				return m;
			}
			rs.close();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int Insertar(Albaran_MDL m, JTable jt) {
		String sql = "INSERT into albaranes VALUES "
				+ "(null, '" + m.getAlb_fecha_prov() + "',"
				+ "" + m.getAlb_prov_id() + ",'" + m.getAlb_fecha_entrada() + "')";
		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			//Empieza la transacción:
			cnx.setAutoCommit(false);
			Statement comando = cnx.createStatement();
			int n = comando.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (n != 0) {
				ResultSet rs = comando.getGeneratedKeys();
				if (rs.next()) {
					n = rs.getInt(1);
					System.out.println(n);
				}
			}
			DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
			int contador = 0;
			for (int i = 0; i < dtm.getRowCount(); i++) {
//				System.out.println(dtm.getValueAt(i, 0));
				Albdet_MDL am = new Albdet_MDL(
						0, 
						n, 
						(int) dtm.getValueAt(i, 0), 
						(int) dtm.getValueAt(i, 3), 
						(double) dtm.getValueAt(i, 4));
				int regs = Albdet_CTRL.Grabar(am, cnx);
				if(regs > 0) {
					contador++;
				}
			}
			if(contador == dtm.getRowCount()) {
				cnx.commit();
				n=contador;
			}else{
				cnx.rollback();
				n=0;
			}
			c.Desconectar(cnx);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int Modificar(Albaran_MDL m, JTable jt) {
		
		String sql = "UPDATE albaranes SET "
				+ "alb_fecha_prov='" + m.getAlb_fecha_prov() + "', "
				+ "alb_prov_id='" + m.getAlb_prov_id() + "', " 
				+ "alb_fecha_entrada='" + m.getAlb_fecha_entrada() + "' "
				+ "WHERE alb_id = '" + m.getAlb_id() + "'";
		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			cnx.setAutoCommit(false);
			Statement comando = cnx.createStatement();
			//Modificar albaranes:
			int nm = comando.executeUpdate(sql);
			//Borrar todo el detalle:
			Albdet_CTRL.Borrar(m.getAlb_id()+"", cnx);
			DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
			int contador = 0;
			for (int i = 0; i < dtm.getRowCount(); i++) {
				System.out.println(jt.getValueAt(i, 3));
				Albdet_MDL am = new Albdet_MDL(
						0, 
						m.getAlb_id(), 
						(int) dtm.getValueAt(i, 0), 
						(int) dtm.getValueAt(i, 3), 
						(double) dtm.getValueAt(i, 4));
				int regs = Albdet_CTRL.Grabar(am, cnx);
				if(regs > 0) {
					contador++;
				}
			}
			int n=0;
			if(contador == dtm.getRowCount()) {
				cnx.commit();
				n=contador;
			}else{
				cnx.rollback();
				n=0;
			}
			c.Desconectar(cnx);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int Borrar(String id) {
		
		String sql = "DELETE FROM albaranes WHERE alb_id = '" + id + "'";
		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			Albdet_CTRL.Borrar(id,cnx);
			Statement comando = cnx.createStatement();
			int n = comando.executeUpdate(sql);
			c.Desconectar(cnx);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void main(String[] args) {

		/*
		 * RECUPERA TODOS 
		 * ArrayList<Albaran_MDL> m = Recupera_Todos(""); for(Albaran_MDL
		 * a: m) { System.out.println(a.getAlb_id() + " - " + a.getAlb_fecha_prov() +
		 * " - " + a.getProv_nombre()); }
		 * 
		 * RECUPERA POR ID 
		 * Albaran_MDL a = Recupera_Por_Id(2);
		 * System.out.println(a.getAlb_id() + " - " + a.getAlb_fecha_entrada()); }
		 * 
		 * INSERTAR 
		 * Albaran_MDL a = new Albaran_MDL(0, "2021-07-09", 2, "2021-07-10");
		 * int id = Insertar(a); if(id != 0){ System.out.
		 * println("Se ha insertado un nuevo albaran \ncon la fecha de entrada: "+
		 * a.getAlb_fecha_entrada() + " \ny la fecha de provisión: " +
		 * a.getAlb_fecha_prov()); }else{ System.out.println("Error de inserción"); }
		 * 
		 * 
		 * MODIFICAR 
		 * Albaran_MDL a = new Albaran_MDL(7, "2021-07-05", 1, "2021-07-06");
		 * int id = Modificar(a);
		 * System.out.println("modificación de campos: Provisión de '2021-07-09' a "
		 * +a.getAlb_fecha_prov() + ";\nEntrada de '2021-07-10' a "
		 * +a.getAlb_fecha_entrada()); }
		 * 
		 * BORRAR
		 * int n=Borrar(7+""); if(n != 0){ System.out.println("Registro borrado");
		 * }else{ System.out.println("Fallo de supresión"); }
		 */
	}
}
