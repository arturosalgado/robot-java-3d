
import java.io.*;

class TareaInfo implements Serializable 

{
	
		String tarea;
		int    identificador;
		int    totalpiezas;
	
		TareaInfo(String s, int id,int totalpiezas)
		{
		
			tarea= s;
			identificador=id;
			this.totalpiezas=totalpiezas;
			
		}
			TareaInfo()
		{
		
			
		}
	
		public String toString()
		{
			
			
		return tarea;	
			
		}
	
}