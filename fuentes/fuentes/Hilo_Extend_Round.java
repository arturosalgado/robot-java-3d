import javax.media.j3d.TransformGroup;
import javax.vecmath.*;
import javax.media.j3d.Transform3D;



class Hilo_Extend extends Thread

{
	Transform3D t3d = new Transform3D();//util para cambiar los valores del Transformgroup
	
	TransformGroup tg;
	int  valor_inicial;
	int  valor_final;
	Vector3f v3f = new Vector3f();
	boolean  t;
	int resolucion = 50;

	
	Hilo_Extend(boolean t,TransformGroup tg_in, int  vi, int  vf)
	
	{
		tg=tg_in;	
		
		valor_inicial=vi;
		valor_final=vf;
	
	
		
		this.t = t;
	}
	

	
	
	
	

	
	public  void run ()
	
	{
			if (t==true)
			grow();
			else 
			shrink();
		
	}
	
	void grow()
	{
	
			for ( int i = valor_inicial ; i  < valor_final ; i ++)
		
		{
			v3f.set((float)i/resolucion,0.0f,0.0f);
			tg.getTransform(t3d);	
			t3d.set(v3f);
			tg.setTransform(t3d);
			try 
			{
			sleep(10);
			}
			catch (InterruptedException e)
			{
			}
		}
		
		
	}
	
	void shrink()
	{
			for ( int i = valor_final ; i  > valor_inicial ; i --)
		
		{
			v3f.set((float)i/resolucion,0.0f,0.0f);
			tg.getTransform(t3d);	
			t3d.set(v3f);
			tg.setTransform(t3d);
			try 
			{
			sleep(10);
			}
			catch (InterruptedException e)
			{
			}
		}
	
	}

}	