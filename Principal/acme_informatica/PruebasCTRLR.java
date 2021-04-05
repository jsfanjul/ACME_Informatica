package acme_informatica;

import java.util.ArrayList;

public class PruebasCTRLR {

	public static void main(String[] args) {
		
	 // ======================================================= PRUEBA (TESTEO) DEL CORRECTO FUNCIONAMIENTO DE LOS CONTROLADORES DE CLIENTES ================================================================ //

		 // (1) Prueba del método Recupera_Todos():
		        /*
		           ArrayList<ClientesMDL> lista = ClientesCTRLR.Recupera_Todos("ve"); 
		           for (ClientesMDL cli : lista) { 
		   		      System.out.println("\n" + cli + "\n" + "=======================" +"\n"); 
		   	       }
		        */

		 // (2) Prueba del método Recupera_Por_Id():
		        /*
		           ClientesMDL cli = ClientesCTRLR.Recupera_Por_Id(16);
		           System.out.println(cli);
		       */

		// (3) Prueba del método Grabar():
		       // - Para la inserción de un nuevo cliente:
		   		    /*
		               ClientesMDL cli_1 = new ClientesMDL(0, "Microsoft", "M54738294", "Street", "New York", "44842", "New York", "España", "Bill", "745839234"); 
		               int numregs = ClientesCTRLR.Grabar(cli_1); 
		  
		 	           ArrayList<ClientesMDL> lista = ClientesCTRLR.Recupera_Todos(""); 
		 	           for (ClientesMDL cli_2 : lista) { 
		 			       System.out.println("\n" + cli_2 + "\n" + "=======================" + "\n"); 
		 		       }
		   		    */

		       // - Para la modificación de un cliente existente:
			        /*
			           ClientesMDL cli_1 = new ClientesMDL(26, "Asus", "M54738294", "Street", "New York", "44842", "New York", "España", "Bill", "745839234"); 
			           int numregs = ClientesCTRLR.Grabar(cli_1); 
			           
		               ArrayList<ClientesMDL> lista = ClientesCTRLR.Recupera_Todos(""); 
		               for (ClientesMDL cli_2 : lista) { 
		    	           System.out.println("\n" + cli_2 + "\n" + "=======================" + "\n"); 
		               }
		            */
        
		// (4) Prueba del método Borrar():
		       /*
		          int numregs = ClientesCTRLR.Borrar(26); 
		          ArrayList<ClientesMDL> lista = ClientesCTRLR.Recupera_Todos(""); 
		   
		          for (ClientesMDL cli : lista) { 
			          System.out.println("\n" + cli + "\n" + "=======================" + "\n"); 
		          }
		
		       */

		// (5) Prueba del método Recupera_Por_Nombre():
               /*
		          System.out.println(ClientesCTRLR.Recupera_Por_Nombre("Indra"));
		       */
		
    // =========/=========/===========/============/============/============/=== FIN DEL TESTEO ===/===========/============/==========/===========/=========/==========/=========/=========/============== //
		
		
		
	// ======================================================== PRUEBA (TESTEO) DEL CORRECTO FUNCIONAMIENTO DE LOS CONTROLADORES DE PROVEEDORES ============================================================ //
		
		// (1) Prueba del método Recupera_Todos():
			   /* 
			      ArrayList<ProveedoresMDL> lista = ProveedoresCTRLR.Recupera_Todos("ec");
				  for (ProveedoresMDL prov : lista) {
				  	  System.out.println("\n" + prov + "\n" + "=======================" + "\n");
				  }
			   */
		
		// (2) Prueba del método Recupera_Por_Id():
			   /*
				  ProveedoresMDL prov = ProveedoresCTRLR.Recupera_Por_Id(2);
				  System.out.println(prov);
			   */
		
		// (3) Prueba del método Grabar():
		       // - Para la inserción de un nuevo proveedor:
		            /*
		               ProveedoresMDL prov_1 = new ProveedoresMDL(0, "Asiam", "B37387487", "Ronda", "Paterna", "93842", "Valencia", "España", "Nuria", "638237273");
		               int numregs = ProveedoresCTRLR.Grabar(prov_1);
		           
				       ArrayList<ProveedoresMDL> lista = ProveedoresCTRLR.Recupera_Todos("");
				       for (ProveedoresMDL prov_2 : lista) {
						   System.out.println("\n" + prov_2 + "\n" + "=======================" + "\n");
				       }
		            */
		
		       // - Para la modificación de un proveedor existente:
		   		    /*
				        ProveedoresMDL prov_1 = new ProveedoresMDL(19, "American", "B37387487", "Ronda", "Paterna", "93842", "Valencia", "España", "Nuria", "638237273");
			            int numregs = ProveedoresCTRLR.Grabar(prov_1);
			       
				        ArrayList<ProveedoresMDL> lista = ProveedoresCTRLR.Recupera_Todos("");
				        for (ProveedoresMDL prov_2 : lista) {
					        System.out.println("\n" + prov_2 + "\n" + "=======================" + "\n");
				        }
		   		    */
		
		// (4) Prueba del método Borrar():
		       /*
		          int numregs = ProveedoresCTRLR.Borrar(19);
		          ArrayList<ProveedoresMDL> lista = ProveedoresCTRLR.Recupera_Todos("");
		          for (ProveedoresMDL prov : lista) {
			          System.out.println("\n" + prov + "\n" + "=======================" + "\n");
		          }
		       */
		
		// (5) Prueba del método Recupera_Por_Nombre():
		       /*
		          System.out.println(ProveedoresCTRLR.Recupera_Por_Nombre("Safira"));
		       */
		
	 // =========/=========/===========/============/============/============/=== FIN DEL TESTEO ===/===========/============/==========/===========/=========/==========/=========/=========/============== //
		
	}
	
}
