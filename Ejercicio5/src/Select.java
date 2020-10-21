import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
	
	boolean accion=true;
	Scanner teclado= new Scanner(System.in);
	
	public Select() {
		
		    String basedatos = "dbprueba";
		    String host = "localhost";
		    String port = "3306";
		    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
		    String user = "dennis";
		    String pwd = "Huayllas1";

		    try (
		            Connection c = DriverManager.getConnection(urlConnection, user, pwd);
		            Statement s = c.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		            ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES")) {

		     
		    	rs.absolute(1);
   	  			 System.out.println(" ||||Fila : "  + (rs.getRow())+"||||");   
   	  			 System.out.println(" DNI: " + rs.getString("DNI"));
				 System.out.println(" Apellidos: " + rs.getString("APELLIDOS"));
				 System.out.println(" CP: " + rs.getString("CP"));
				 
		      while(accion) {
		      System.out.println(" Introduzca una accion \"k\" \"d\" \"N� fila\"");
		      String opcion = teclado.nextLine();
		      
				      switch(opcion) {
				      
				      case "k": if(rs.next()) {
					    	 
					    	 System.out.println(" ||||Fila : "  + (rs.getRow())+"||||");   
					    	 System.out.println(" DNI: " + rs.getString("DNI"));
						     System.out.println(" Apellidos: " + rs.getString("APELLIDOS"));
						     System.out.println(" CP: " + rs.getString("CP"));
					    	 }else {
					    		 System.out.println("No Hay mas Filas que Mostrar");
					    		 accion=false;
					    	 }
				      		break;
				      case "d":
				    	  if(rs.previous()) {
						    	 System.out.println(" ||||Fila : "  + (rs.getRow())+"||||");   
						    	 System.out.println(" DNI: " + rs.getString("DNI"));
							     System.out.println(" Apellidos: " + rs.getString("APELLIDOS"));
							     System.out.println(" CP: " + rs.getString("CP"));
						    	 }else {
						    		System.out.println("No Hay mas Filas que Mostrar");
						    		 accion=false;
						    	 }
				    
				    	  break;		 
				   
				      case ".":
				    	  System.out.println(" Salido.....");
				    	  accion=false;
				    	  break;
				    	  
				      default :
				    	  try {
				    		  Integer nfilas=Integer.parseInt(opcion);
				    		  if(rs.absolute(nfilas)) {
				    	  			 System.out.println(" ||||Fila : "  + (rs.getRow())+"||||");   
							    	 System.out.println(" DNI: " + rs.getString("DNI"));
								     System.out.println(" Apellidos: " + rs.getString("APELLIDOS"));
								     System.out.println(" CP: " + rs.getString("CP"));
							    	 }else {
							    		System.out.println("No Existe esa fila");
							    		 accion=false;
							    	 }
				    	  	}catch (NumberFormatException e){
				             System.out.println("Opcion No valida"); 
				        
				      			
				      			}
				    	  	break;		
				      	}
		    	 
		      }
		     
		    

		    } catch (SQLException e) {
		      muestraErrorSQL(e);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    }
		  
	}
	public static void muestraErrorSQL(SQLException e) {
	    System.err.println("SQL ERROR mensaje: " + e.getMessage());
	    System.err.println("SQL Estado: " + e.getSQLState());
	    System.err.println("SQL código específico: " + e.getErrorCode());
	  }
	  

}
