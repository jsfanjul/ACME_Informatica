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

public class Facturas_CTRLR {

	/* RECUPERA TODOS */
	public static ArrayList<Factura_MDL> Recupera_Todos(String filtro) {

		ArrayList<Factura_MDL> lista = new ArrayList<Factura_MDL>();
		String sql = "SELECT f.*, c.cli_nombre " + "FROM facturas as f JOIN clientes as c "
				+ "ON f.fac_cli_id=c.cli_id " + "WHERE c.cli_nombre like '%" + filtro + "%' "
				+ "ORDER BY f.fac_fecha_salida";

		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();

			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while (rs.next() == true) {
				Factura_MDL f = new Factura_MDL(rs.getInt("fac_id"), rs.getString("fac_fecha_venta"),
						rs.getInt("fac_cli_id"), rs.getString("fac_fecha_salida"), rs.getString("cli_nombre"));
				lista.add(f);
			}
			c.Desconectar(cnx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* RELLENAR TABLA */
	public static void Rellenar_JTable(JTable jt, String filtro) {
		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
		dtm.setRowCount(0);
		ArrayList<Factura_MDL> lista = Recupera_Todos(filtro);
		for (Factura_MDL f : lista) {
			Vector v = new Vector();
			v.add(f.getFac_id());
			v.add(f.getCli_nombre());
			v.add(f.getFac_fecha_venta());
			v.add(f.getFac_fecha_salida());

			dtm.addRow(v);
		}
	}

	/* RECUPERAR POR ID */
	public static Factura_MDL Recupera_Por_Id(int id) {
		Factura_MDL f = null;
		String sql = "SELECT * FROM facturas WHERE fac_id=?";
		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			PreparedStatement ps = cnx.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				f = new Factura_MDL(rs.getInt("fac_id"), rs.getString("fac_fecha_venta"), rs.getInt("fac_cli_id"),
						rs.getString("fac_fecha_salida"));

				return f;
			}
			rs.close();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* INSERTAR */
	public static int Insertar(Factura_MDL f, JTable jt) {
		String sql = "INSERT into facturas VALUES " + "(null, '" + f.getFac_fecha_venta() + "'," + ""
				+ f.getFac_cli_id() + ",'" + f.getFac_fecha_salida() + "')";

		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
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
				FacDet_MDL am = new FacDet_MDL(0, n, (int) dtm.getValueAt(i, 0), (int) dtm.getValueAt(i, 3),
						(double) dtm.getValueAt(i, 4));
				int regs = FacDet_CTRLR.Grabar(am, cnx);
				if (regs > 0) {
					contador++;
				}
			}
			if (contador == dtm.getRowCount()) {
				cnx.commit();
				n = contador;
			} else {
				cnx.rollback();
				n = 0;
			}
			c.Desconectar(cnx);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* MODIFICAR */
	public static int Modificar(Factura_MDL f, JTable jt) {

		String sql = "UPDATE facturas " + "SET 	" + "fac_fecha_venta='" + f.getFac_fecha_venta() + "'," + "fac_cli_id='"
				+ f.getFac_cli_id() + "', " + "fac_fecha_salida='" + f.getFac_fecha_salida() + "' " + "WHERE fac_id = '"
				+ f.getFac_id() + "'";

		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			cnx.setAutoCommit(false);
			Statement comando = cnx.createStatement();
			// Modificar facturas:
			int nm = comando.executeUpdate(sql);
			// Borrar todo el detalle:
			FacDet_CTRLR.Borrar(f.getFac_id()+"", cnx);
			DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
			int contador = 0;
			for (int i = 0; i < dtm.getRowCount(); i++) {
				System.out.println(jt.getValueAt(i, 0));
				System.out.println(jt.getValueAt(i, 3));
				System.out.println(jt.getValueAt(i, 4));
				System.out.println(f.getFac_id());
				FacDet_MDL fm = new FacDet_MDL(0, f.getFac_id(), (int) dtm.getValueAt(i, 0), (int) dtm.getValueAt(i, 3),
						(double) dtm.getValueAt(i, 4));
				int regs = FacDet_CTRLR.Grabar(fm, cnx);
				if (regs > 0) {
					contador++;
				}
			}
			int n = 0;
			if (contador == dtm.getRowCount()) {
				cnx.commit();
				n = contador;
			} else {
				cnx.rollback();
				n = 0;
			}
			c.Desconectar(cnx);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/* BORRAR */
	public static int Borrar(String id) {

		String sql = "DELETE FROM facturas " + "WHERE fac_id = '" + id + "'";
		System.out.println(sql);
		try {
			Conexion c = new Conexion();
			Connection cnx = c.getConection();
			FacDet_CTRLR.Borrar(id, cnx);
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
		
		/* PRUEBAS CONTROLADORES*/

//		// RECUPERA TODOS
//
//		ArrayList<Factura_MDL> m = Recupera_Todos("");
//		for (Factura_MDL f : m) {
//			System.out.println(f.getFac_id() + " - " + f.getFac_fecha_venta() + " - " + f.getCli_nombre());
//		}
//
//		// RECUPERA POR ID
//		
//		  Factura_MDL f = Recupera_Por_Id(1); System.out.println(f.getFac_id() + " - " +
//		  f.getFac_fecha_venta());
//		 
//
//		// INSERTAR
//		
//		  Factura_MDL f = new Factura_MDL(0, "2021-03-09", 2, "2021-03-10"); int id =
//		  Insertar(f); if (id != 0) {
//		  System.out.println("Se ha generado una nueva factura \ncon  fecha de venta: "
//		  + f.getFac_fecha_venta() + " \ny fecha de salida: " +
//		  f.getFac_fecha_salida()); } else { System.out.println("Error al insertar"); }
//		 
//
//		// MODIFICAR
//		
//		  Factura_MDL f = new Factura_MDL(2, "2021-03-05", 1, "2021-03-06"); int id =
//		  Modificar(f); System.out.println(
//		  "Se han modificado los siguientes campos: \nFECHA DE VENTA de: '2021-07-09' a NUEVA FECHA DE VENTA: "
//		  + f.getFac_fecha_venta() +
//		  ";\nFECHA DE SALIDA de: '2021-07-10' a NUEVA FECHA DE SALIDA: " +
//		  f.getFac_fecha_salida());
//		  
//		 
//		// BORRAR
//		
//		  int n = Borrar(2 + ""); if (n != 0) { System.out.println("Registro borrado");
//		  } else { System.out.println("��Fallo al borrar!!"); }
		 
	}

}
