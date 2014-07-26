//de los angeles salgado jose arturo 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;


class RotacionZ extends Transformacion
{
	
	float grados;
	
	Vector3f v3f;
	
	int id = v.ROTACION_Z;
	
	
	RotacionZ(float grados)
	{
		this.nombre = "RotacionZ";
		this.grados=grados;
		t3d.rotZ(Math.toRadians(grados));
		tg.setTransform(t3d);
	
	}
	
	
	
		RotacionZ(Datos datos)
	{
		this.nombre = "Rotacion Z";
		this.grados=datos.grados;
		
		t3d.rotZ(Math.toRadians(grados));
		tg.setTransform(t3d);
	
	}
	

	public Datos getDatos()
	{
		
		Datos datos = new Datos(grados,id,nombre);
		return datos;
		
	}
	
}