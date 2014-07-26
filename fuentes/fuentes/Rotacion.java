//de los angeles salgado jose arturo 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;


class RotacionX extends Transformacion
{
	
	int grados;
	
	Vector3f v3f;
	
	int id = v.ROTACION_X;
	
	
	RotacionX(int grados)
	{
		this.nombre = "Transformacion";
		t3d.rotX(Math.toRadians(grados));
		tg.setTransform(t3d);
	
	}
	
	
	
		RotacionX(Datos datos)
	{
		this.nombre = "Translacion";
		this.grados=datos.grados;
		
		t3d.rotX(Math.toRadians(grados));
		tg.setTransform(t3d);
	
	}
	

	public Datos getDatos()
	{
		
		Datos datos = new Datos(grados,id,nombre);
		return datos;
		
	}
	
}