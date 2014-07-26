
import java.io.*;

class RobotInfo implements Serializable 

{
	
		String nombre;
		int    identificador;
		Datos  datos;
		int    totalpiezas;
	
		RobotInfo(String s, int id, Datos dat, int totalpiezas)
		{
		
			nombre= s;
			identificador=id;
			datos = dat;
			this.totalpiezas=totalpiezas;
			
		}
	
	
		public String toString()
		{
			
			
		return "nombre = "+nombre+"identificador "+identificador+"datos "+datos.x+" "+datos.y+"  "+datos.z;	
			
		}
	
}