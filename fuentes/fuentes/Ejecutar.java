import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.media.j3d.Transform3D;



class Ejecutar extends Thread

{
	Transform3D t3d = new Transform3D();//util para cambiar los valores del Transformgroup
	
	AxisAngle4f aa;
	TransformGroup tg;
	int  valor_inicial;
	int  valor_final;
	boolean direccion;
	int resolucion=10;
	Robot robot;
	
	Ejecutar()
	{
		
		
	}

	
	Ejecutar(Robot robot, String string)
	
	{
		
	this.robot=robot;
		
		
	}
	

	

	
	

	
	public  void run ()
	
	{
		
		
		
		Joint c = (Joint)robot.componente.elementAt(0);
		c.rotar(10,10,true);


		
	}
	
	
	
}