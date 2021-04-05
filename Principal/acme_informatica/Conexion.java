package acme_informatica;

import java.sql.*;

public class Conexion {

	private Connection cnx = null;
	 
	public Connection getConection() 
	{
		if (cnx == null) 
		{
			try 
			{
				Driver driver = new com.mysql.jdbc.Driver();
				DriverManager.registerDriver(driver);
				cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_practicas", "root", "");
		    }
		    catch (SQLException ex) 
		    {
		    	ex.printStackTrace();
		    	return null;
		    }
		 }
	    return cnx;
	}
	
	public void Desconectar(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
