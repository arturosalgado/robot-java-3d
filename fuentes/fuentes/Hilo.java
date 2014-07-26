import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.media.j3d.Transform3D;



class Hilo extends Thread

{
	Transform3D t3d = new Transform3D();//util para cambiar los valores del Transformgroup
	
	AxisAngle4f aa;
	TransformGroup tg;
	int  valor_inicial;
	int  valor_final;
	boolean direccion;
	int resolucion=10;
	
	Hilo()
	{
		
		
	}

	
	Hilo(boolean direccion,int resolucion,AxisAngle4f	aa_in, TransformGroup tg_in, int  vi, int  vf)
	
	{
		aa= aa_in;
		tg= tg_in;	
		valor_inicial=vi;
		valor_final=vf;
		this.direccion=direccion;
		this.resolucion = resolucion;
	}
	

	

	
	

	
	public  void run ()
	
	{
		
		
			if (direccion==true)
			girarDerecha();
		else
		girarIzquierda();
		
	}
	
	
  void   girarDerecha()
	{
		
		
		for ( int i = valor_inicial ; i  < valor_final ; i ++)
		
		{
			
			aa.angle = (float) Math.toRadians(i);

			tg.getTransform(t3d);	
			
			t3d.setRotation(aa);
			
			tg.setTransform(t3d);
			
			
			try 
			{
				sleep(resolucion);
			}
			catch (InterruptedException e)
			{
				
			}
			
			//System.out.println("i"+i);

		}	
		
	}
	
	
	
	
		
	 void  girarIzquierda()
	{
		
		
		for ( int i = valor_inicial ; i > valor_final ; i --)
		
		{
			aa.angle = (float) Math.toRadians(i);

			tg.getTransform(t3d);	
			
			t3d.setRotation(aa);
			
			tg.setTransform(t3d);
			
			
			try 
			{
				sleep(resolucion);
			}
				catch (InterruptedException e)
			{
			}
			
			//System.out.println("i"+i);

		}	
		
		
		
	}
	
	
	
	
	
	
	
}	