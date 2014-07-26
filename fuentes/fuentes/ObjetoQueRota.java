
// de los angeles salgado jose arturo 
// editor de robots orientado a objetos
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.*;
import javax.vecmath.*;


  class   ObjetoQueRota extends ObjetoFisico implements InterfazRotar
{
	
	
		TransformGroup tg_rotar;
		AxisAngle4f angulo_eje_rotacion;
		Hilo hilo;

		
		// offset de la rotacion
		int  valor_inicial=0;
		int  valor_final = 0;
		int  offset  =45;
		
		
		


   ObjetoQueRota()
   {

		angulo_eje_rotacion = new AxisAngle4f();
		
		tg_rotar =new TransformGroup();	 ;
		tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	
		tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
   	tg_rotar.setCapability(Group.ALLOW_CHILDREN_EXTEND);	

   }	
	
	 synchronized public void rotar(int grados,int resolucion, boolean modo)
	 {

			offset = grados;
			
			valor_final=valor_final + offset;
			
			//si valor inicial es mayor que valor final 
			
		
			
			// cambia la direccion en que se llama al hilo
			if (valor_inicial<valor_final)
			{
				hilo = new Hilo (true,resolucion,angulo_eje_rotacion, tg_rotar, valor_inicial,valor_final);
				if (modo==false)
				{
					try
					{	
					hilo.start();
					hilo.join();
					}
					catch(InterruptedException e)
					{
					}		
					
				}
				else
				{
					
					hilo.start();	
					
				}	
				valor_inicial=valor_inicial+offset;			
			}

			//vf es menor
			else
			{
				
				hilo = new Hilo (false,resolucion,angulo_eje_rotacion, tg_rotar, valor_inicial,valor_final);
				if (modo==false)
				{
					try
					{
						hilo.start();
						hilo.join();
					}
					catch(InterruptedException e)
					{
					}
				}
				else
				{
					
				hilo.start();	
				}
				
				
				valor_inicial=valor_inicial+offset;			
			}
			

	 }//METODO 
	 
	 		boolean vive()
	 		{
	 			
	 			if (hilo.isAlive())
	 			return true;	
	 			else
	 			return false;
	 			
	 		}

}//CLASE