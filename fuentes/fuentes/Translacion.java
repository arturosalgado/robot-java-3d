//de los angeles salgado jose arturo 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;


class Translacion extends Transformacion
{
	
	float x;
	float y;
	float z;
	final int id = v.TRANSLACION;
	
	
	Vector3f v3f;
	
	Translacion(float x, float y, float z)
	{
		this.nombre = "Transformacion";
		this.x=x;
		this.y=y;
		this.z=z;
		
		v3f = new Vector3f(x,y,z);
		t3d.set(v3f);
		tg.setTransform(t3d);
	
	}
	
	
	
		Translacion(Datos datos)
	{
		this.nombre = "Translacion";
		this.x=datos.valor_1;
		this.y=datos.valor_2;
		this.z=datos.valor_3;
		
		v3f = new Vector3f(x,y,z);
		t3d.set(v3f);
		tg.setTransform(t3d);
	
	}
	

	public Datos getDatos()
	{
		
		Datos datos = new Datos(x,y,z,id,nombre);
		return datos;
		
	}
	
}