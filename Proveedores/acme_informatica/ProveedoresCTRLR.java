package acme_informatica;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProveedoresCTRLR {

	public static ArrayList<ProveedoresMDL> Recupera_Todos(String filtro){
		
		ArrayList<ProveedoresMDL> lista = new ArrayList<ProveedoresMDL>();
		String sql = "SELECT * FROM proveedores WHERE prov_nombre LIKE '%"+ filtro +"%' OR prov_tipo_via LIKE '%"+ filtro +"%' OR prov_localidad LIKE '%"+ filtro +"%' OR prov_provincia LIKE '%"+ filtro +"%' OR prov_pais LIKE '%"+ filtro +"%' OR prov_contacto LIKE '%"+ filtro +"%'";
		// System.out.println(sql);
		try {
			Conexion con=new Conexion();
			Connection cnx= con.getConection();
			
			Statement comando=cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while (rs.next() == true) {
				ProveedoresMDL prov = new ProveedoresMDL(
						rs.getInt("prov_id"),
						rs.getString("prov_nombre"),
						rs.getString("prov_cif"),
						rs.getString("prov_tipo_via"),
						rs.getString("prov_localidad"),
						rs.getString("prov_codigo_postal"),
						rs.getString("prov_provincia"),
						rs.getString("prov_pais"),
						rs.getString("prov_contacto"),
						rs.getString("prov_telefono"));
				lista.add(prov);				
			}
			con.Desconectar(cnx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static void Rellena_JTable(JTable jt, String filtro) {
		
		ArrayList<ProveedoresMDL> lista = Recupera_Todos(filtro);
		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
		dtm.setRowCount(0);
		
		for (int i = 0; i < lista.size(); i++) {
			Vector v = new Vector();
			v.add(lista.get(i).getProv_id());
			v.add(lista.get(i).getProv_nombre());
			v.add(lista.get(i).getProv_cif());
			v.add(lista.get(i).getProv_tipo_via());
			v.add(lista.get(i).getProv_localidad());
			v.add(lista.get(i).getProv_codigo_postal());
			v.add(lista.get(i).getProv_provincia());
			v.add(lista.get(i).getProv_pais());
			v.add(lista.get(i).getProv_contacto());
			v.add(lista.get(i).getProv_telefono());
			
			dtm.addRow(v);
		}
		
	}
	
	public static ProveedoresMDL Recupera_Por_Id(int id) {
		
		ProveedoresMDL prov = null;
		
		String sql = "SELECT * FROM proveedores WHERE prov_id = " + id;
		// System.out.println(sql);
		
		Connection cnx = new Conexion().getConection();
		try {
			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while(rs.next() == true) {
				prov = new ProveedoresMDL(rs.getInt("prov_id"), rs.getString("prov_nombre"), rs.getString("prov_cif"),
							rs.getString("prov_tipo_via"), rs.getString("prov_localidad"), rs.getString("prov_codigo_postal"),
							rs.getString("prov_provincia"), rs.getString("prov_pais"), rs.getString("prov_contacto"), rs.getString("prov_telefono"));
			}
			rs.close();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return prov;
	}
	
	public static int Grabar(ProveedoresMDL prov) {
		
		ArrayList<ProveedoresMDL> lista = new ArrayList<ProveedoresMDL>();
		
		String sql = "";
		
		int numregs = 0;
		
		if(prov.getProv_id() == 0) {
			sql = "INSERT INTO proveedores SET prov_nombre = '"+ prov.getProv_nombre() +"', prov_cif = '"+ prov.getProv_cif() +"', " +
						" prov_tipo_via = '"+ prov.getProv_tipo_via() +"', prov_localidad = '"+ prov.getProv_localidad() +"', prov_codigo_postal = '"+ prov.getProv_codigo_postal() +"', " +
						" prov_provincia = '"+ prov.getProv_provincia() +"', prov_pais = '"+ prov.getProv_pais() +"', prov_contacto = '"+ prov.getProv_contacto() +"', prov_telefono = '"+ prov.getProv_telefono() +"'";
		} else {
			sql = "UPDATE proveedores SET prov_nombre = '"+ prov.getProv_nombre() +"', prov_cif = '"+ prov.getProv_cif() +"', " +
					    " prov_tipo_via = '"+ prov.getProv_tipo_via() +"', prov_localidad = '"+ prov.getProv_localidad() +"', prov_codigo_postal = '"+ prov.getProv_codigo_postal() +"', " +
					    " prov_provincia = '"+ prov.getProv_provincia() +"', prov_pais = '"+ prov.getProv_pais() +"', prov_contacto = '"+ prov.getProv_contacto() +"', prov_telefono = '"+ prov.getProv_telefono() +"' " +
				  " WHERE prov_id = "+ prov.getProv_id() +"";
		}
		
		// System.out.println(sql);
		
		Connection cnx = new Conexion().getConection();
		try {
			Statement comando = cnx.createStatement();
			numregs = comando.executeUpdate(sql);
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return numregs;
		
	}
	
	public static int Borrar(int id) {
		String sql = "DELETE FROM proveedores WHERE prov_id = '" + id +"'";
		// System.out.println(sql);
		try {
			Conexion con = new Conexion();
			Connection cnx = con.getConection();
			Statement comando= cnx.createStatement();
			int n = comando.executeUpdate(sql);
			con.Desconectar(cnx);
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean ValidaCIF(String cif) {
		
		return cif.matches("^[A-Z][0-9]{8}$");
		
	}
	
	public static ProveedoresComboMDL Rellena_JCombo(JComboBox jc, int provID) {
		DefaultComboBoxModel<ProveedoresComboMDL> dcm = new DefaultComboBoxModel<ProveedoresComboMDL>();
		String sql = "SELECT * FROM proveedores ORDER BY prov_nombre";
		ProveedoresComboMDL prov = null;
		int seleccionado=0;
		int contador=0;
		try {
			Connection cnx = new Conexion().getConection();
			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt("prov_id") == provID) {
					seleccionado = contador;
				}
				contador++;	
				prov = new ProveedoresComboMDL(
						rs.getInt("prov_id"), 
						rs.getString("prov_nombre")
				);
				dcm.addElement(prov);
			}
			jc.setModel(dcm);
			jc.setSelectedIndex(seleccionado);
			rs.close();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prov;
				
	}
	
	public static ProveedoresMDL Recupera_Por_Nombre(Object nombre) {
		
		ProveedoresMDL prov = null;
		
		String sql = "SELECT * FROM proveedores WHERE prov_nombre = '" + nombre + "'";
		// System.out.println(sql);
		
		Connection cnx = new Conexion().getConection();
		try {
			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while(rs.next() == true) {
				prov = new ProveedoresMDL(rs.getInt("prov_id"), rs.getString("prov_nombre"), rs.getString("prov_cif"),
							rs.getString("prov_tipo_via"), rs.getString("prov_localidad"), rs.getString("prov_codigo_postal"),
							rs.getString("prov_provincia"), rs.getString("prov_pais"), rs.getString("prov_contacto"), rs.getString("prov_telefono"));
			}
			rs.close();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return prov;
		
	}
	
	public static void Rellenar_Combo(JComboBox<JComboMDL> jc, int id) {
		
		ArrayList<ProveedoresMDL> lista = Recupera_Todos("");
		jc.removeAllItems();
		int pos = 0;
		for (ProveedoresMDL a : lista) {
			JComboMDL c = new JComboMDL(
					a.getProv_id(), 
					a.getProv_nombre());
			jc.addItem(c);
			if (a.getProv_id() == id) {
				jc.setSelectedIndex(pos);
			}
			pos++;
		}
	}
}
