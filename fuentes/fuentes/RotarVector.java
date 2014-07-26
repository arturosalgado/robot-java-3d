//de los angeles salgado jose arturo 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;


class RotarVector extends Transformacion
{
	
	float grados;
	float x, y, z;
	AxisAngle4f aa= new AxisAngle4f();
	
	int id = v.ROTAR_VECTOR;
	
	
	RotarVector(float grados,float x,float y,float z,String nombre)
	{
		this.nombre = "Rotacion al Vector";
		this.grados=grados;
		this.x=x;
		this.y=y;
		this.z=z;
		
		
		aa.set(x,y,z,(float)Math.toRadians(grados));
		
		
		t3d.setRotation(aa);
		tg.setTransform(t3d);
	
	}
	
	
	
		RotarVector(Datos datos)
	{

		this.nombre = "Rotacion al Vector";
		this.grados=datos.valor_1;
		this.x=datos.valor_2;
		this.y=datos.valor_3;
		this.z=datos.valor_4;
		
		
		aa.set(x,y,z,(float)Math.toRadians(grados));
		t3d.setRotation(aa);
		tg.setTransform(t3d);
	
	}
	

	public Datos getDatos()
	{
		
		Datos datos = new Datos(grados,x,y,z,id,nombre);
		return datos;
		
	}
	
}