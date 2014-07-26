//de los angeles salgado jose arturo 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;


class Escalamiento extends Transformacion
{
	
	float escala;
	

	
	int id = v.ESCALAMIENTO;
	
	
	Escalamiento(float escala)
	{
		this.nombre = "Escalamiento";
		this.escala=escala;
		t3d.set(escala);
		tg.setTransform(t3d);
	
	}
	
	
	
		Escalamiento(Datos datos)
	{
		this.nombre = "Escalamiento";
		this.escala=datos.grados;
		
		t3d.set(escala);
		tg.setTransform(t3d);
	
	}
	

	public Datos getDatos()
	{
		
		Datos datos = new Datos(escala,id,nombre);
		return datos;
		
	}
	
}