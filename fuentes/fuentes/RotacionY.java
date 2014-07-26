//de los angeles salgado jose arturo 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;


class RotacionY extends Transformacion
{
	
	float grados;
	
	Vector3f v3f;
	
	int id = v.ROTACION_Y;
	
	
	RotacionY(float grados)
	{
		this.nombre = "RotacionY";
		this.grados=grados;
		t3d.rotY(Math.toRadians(grados));
		tg.setTransform(t3d);
	
	}
	
	
	
		RotacionY(Datos datos)
	{
		this.nombre = "Rotacion Y";
		this.grados=datos.grados;
		
		t3d.rotY(Math.toRadians(grados));
		tg.setTransform(t3d);
	
	}
	

	public Datos getDatos()
	{
		
		Datos datos = new Datos(grados,id,nombre);
		return datos;
		
	}
	
}