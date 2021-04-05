package acme_informatica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientesCTRLR {

	public static ArrayList<ClientesMDL> Recupera_Todos(String filtro){
		
		ArrayList<ClientesMDL> lista = new ArrayList<ClientesMDL>();
		String sql = "SELECT * FROM clientes WHERE cli_nombre LIKE '%"+ filtro +"%' OR cli_tipo_via LIKE '%"+ filtro +"%' OR cli_localidad LIKE '%"+ filtro +"%' OR cli_provincia LIKE '%"+ filtro +"%' OR cli_pais LIKE '%"+ filtro +"%' OR cli_contacto LIKE '%"+ filtro +"%'";
		try {
			Conexion con=new Conexion();
			Connection cnx= con.getConection();
			
			Statement comando=cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while (rs.next() == true) {
				ClientesMDL cli = new ClientesMDL(
						rs.getInt("cli_id"),
						rs.getString("cli_nombre"),
						rs.getString("cli_cif"),
						rs.getString("cli_tipo_via"),
						rs.getString("cli_localidad"),
						rs.getString("cli_codigo_postal"),
						rs.getString("cli_provincia"),
						rs.getString("cli_pais"),
						rs.getString("cli_contacto"),
						rs.getString("cli_telefono"));
				lista.add(cli);				
			}
			con.Desconectar(cnx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static void Rellena_JTable(JTable jt, String filtro) {
		
		ArrayList<ClientesMDL> lista = Recupera_Todos(filtro);
		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
		dtm.setRowCount(0);
		
		for (int i = 0; i < lista.size(); i++) {
			Vector v = new Vector();
			v.add(lista.get(i).getCli_id());
			v.add(lista.get(i).getCli_nombre());
			v.add(lista.get(i).getCli_cif());
			v.add(lista.get(i).getCli_tipo_via());
			v.add(lista.get(i).getCli_localidad());
			v.add(lista.get(i).getCli_codigo_postal());
			v.add(lista.get(i).getCli_provincia());
			v.add(lista.get(i).getCli_pais());
			v.add(lista.get(i).getCli_contacto());
			v.add(lista.get(i).getCli_telefono());
			
			dtm.addRow(v);
		}
		
	}
	
	public static ClientesMDL Recupera_Por_Id(int id) {
		
		ClientesMDL cli = null;
		
		String sql = "SELECT * FROM clientes WHERE cli_id = " + id;
		// System.out.println(sql);
		
		Connection cnx = new Conexion().getConection();
		try {
			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while(rs.next() == true) {
				cli = new ClientesMDL(rs.getInt("cli_id"), rs.getString("cli_nombre"), rs.getString("cli_cif"),
							rs.getString("cli_tipo_via"), rs.getString("cli_localidad"), rs.getString("cli_codigo_postal"),
							rs.getString("cli_provincia"), rs.getString("cli_pais"), rs.getString("cli_contacto"), rs.getString("cli_telefono"));
			}
			rs.close();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cli;
	}
	
	public static int Grabar(ClientesMDL cli) {
		
		ArrayList<ClientesMDL> lista = new ArrayList<ClientesMDL>();
		
		String sql = "";
		
		int numregs = 0;
		
		if(cli.getCli_id() == 0) {
			sql = "INSERT INTO clientes SET cli_nombre = '"+ cli.getCli_nombre() +"', cli_cif = '"+ cli.getCli_cif() +"', " +
						" cli_tipo_via = '"+ cli.getCli_tipo_via() +"', cli_localidad = '"+ cli.getCli_localidad() +"', cli_codigo_postal = '"+ cli.getCli_codigo_postal() +"', " +
						" cli_provincia = '"+ cli.getCli_provincia() +"', cli_pais = '"+ cli.getCli_pais() +"', cli_contacto = '"+ cli.getCli_contacto() +"', cli_telefono = '"+ cli.getCli_telefono() +"'";
		} else {
			sql = "UPDATE clientes SET cli_nombre = '"+ cli.getCli_nombre() +"', cli_cif = '"+ cli.getCli_cif() +"', " +
					" cli_tipo_via = '"+ cli.getCli_tipo_via() +"', cli_localidad = '"+ cli.getCli_localidad() +"', cli_codigo_postal = '"+ cli.getCli_codigo_postal() +"', " +
					" cli_provincia = '"+ cli.getCli_provincia() +"', cli_pais = '"+ cli.getCli_pais() +"', cli_contacto = '"+ cli.getCli_contacto() +"', cli_telefono = '"+ cli.getCli_telefono() +"' WHERE cli_id = "+ cli.getCli_id() +"";
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
		
		String sql = "DELETE FROM clientes WHERE cli_id = '" + id +"'";
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
	
	public static ClientesComboMDL Rellena_JCombo(JComboBox jc, int cliID) {
		DefaultComboBoxModel<ClientesComboMDL> dcm = new DefaultComboBoxModel<ClientesComboMDL>();
		String sql = "SELECT * FROM clientes ORDER BY cli_nombre";
		ClientesComboMDL cli = null;
		int seleccionado=0;
		int contador=0;
		try {
			Connection cnx = new Conexion().getConection();
			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while (rs.next()) {
				if (rs.getInt("cli_id") == cliID) {
					seleccionado = contador;
				}
				contador++;	
				cli = new ClientesComboMDL(
						rs.getInt("cli_id"), 
						rs.getString("cli_nombre")
				);
				dcm.addElement(cli);
			}
			jc.setModel(dcm);
			jc.setSelectedIndex(seleccionado);
			rs.close();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cli;
				
	}
	
	public static ClientesMDL Recupera_Por_Nombre(Object nombre) {
		
		ClientesMDL cli = null;
		
		String sql = "SELECT * FROM clientes WHERE cli_nombre = '" + nombre + "'";
		// System.out.println(sql);
		
		Connection cnx = new Conexion().getConection();
		try {
			Statement comando = cnx.createStatement();
			ResultSet rs = comando.executeQuery(sql);
			while(rs.next() == true) {
				cli = new ClientesMDL(rs.getInt("cli_id"), rs.getString("cli_nombre"), rs.getString("cli_cif"),
							rs.getString("cli_tipo_via"), rs.getString("cli_localidad"), rs.getString("cli_codigo_postal"),
							rs.getString("cli_provincia"), rs.getString("cli_pais"), rs.getString("cli_contacto"), rs.getString("cli_telefono"));
			}
			rs.close();
			cnx.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cli;
		
	}
	
	public static void Rellenar_Combo(JComboBox<JComboMDL> jc, int id) {

		ArrayList<ClientesMDL> lista = Recupera_Todos("");
		jc.removeAllItems();
		int pos = 0;
		for (ClientesMDL c : lista) {
			JComboMDL j = new JComboMDL(c.getCli_id(), c.getCli_nombre());
			jc.addItem(j);
			if (c.getCli_id() == id) {
				jc.setSelectedIndex(pos);
			}
			pos++;
		}
	}
	
}
