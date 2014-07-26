// de los angeles salgadjo jose arturo
// ed sim orientado a objetos
import javax.media.j3d.*;
import java.util.Vector;
import java.io.*;

class Robot extends BranchGroup implements Serializable 
{
	
	Vector  componente = new Vector(100);
	String    []  nombre = new String[100];
	int identificadores[] = new int [100];
	Datos datos [] = new Datos[100]; 
	String  nombre_robot;
	
	int indice;
	
int piloto=0;
	
	boolean vacio ;
	boolean queda1;
	
	
	Robot ()
	{
		 vacio = true;

		this.setCapability(Group.ALLOW_CHILDREN_EXTEND);	
		this.setCapability(BranchGroup.ALLOW_DETACH);	
		this.setCapability(Group.ALLOW_CHILDREN_READ);	
		this.setCapability(Group.ALLOW_CHILDREN_WRITE);	

		
		
		
	}
	
	
	public void adherirPieza(Componente pieza,int indice , int identificador)
	{
		
		if (piloto ==0)
	
		{
			
						vacio = false;

			this.addChild(pieza);
			componente.add(piloto,pieza);
			nombre[piloto]=pieza.nombre;
			identificadores[piloto]=identificador;
	//		System.out.println(pieza.nombre);	
		}
		else
		{
			Componente c = (Componente)componente.elementAt(piloto-1);
			c.adherirHijo(pieza);
			componente.add(piloto-1,c);
			componente.add(piloto,pieza);
			
			nombre[piloto]=pieza.nombre;
			
			identificadores[piloto]=identificador;
		//	System.out.println(pieza.nombre);			
		}
		
		
piloto++;
		
		
		
		
		
		
		
		
	//	System.out.println("bbbbpepepepe esta insertando ne la clase hecha con el vector");
	}
	
	
	
	public boolean quitarPieza(int pilotonovale)
	{
		
		if (vacio == false)
		
		piloto--;
	
			
			
			
		if (piloto>0)

		{
		Componente comp = (Componente)componente.elementAt(piloto);
		comp.detach();
		componente.remove(piloto);
		nombre[piloto]=null;
		identificadores[piloto]=-1;
		
		return true;
	//	System.out.println("eliminando...");

		}
	
		else 
		
		if ((piloto == 0)&&(vacio==false))
		
		{
		Componente comp = (Componente)componente.elementAt(piloto);
		comp.detach();
		componente.remove(piloto);
		nombre[piloto]=null;
		identificadores[piloto]=-1;
	//	System.out.println("eliminando...");
		vacio = true;
		return true;
		}
		

		
		else
		return false;

		
	}
	

	
	
}