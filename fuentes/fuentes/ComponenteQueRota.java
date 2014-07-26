
// de los angeles salgado jose arturo 
// editor de robots orientado a objetos
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.*;
import javax.vecmath.*;


class ObjetoQueRota extends ObjetoFisico implements InterfazRotar
{
	
	

		AxisAngle4f angulo_eje_rotacion;
		Hilo hilo;

		
		// offset de la rotacion
		int  valor_inicial=0;
		int  valor_final = 0;
		int  offset  =45;
		
		
		


   ObjetoQueRota()
   {

		angulo_eje_rotacion = new AxisAngle4f();   	
   	tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	
		tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
   	
   }	
	
	 public void rotar(int grados)
	 {

			valor_final=valor_final + offset;
			
			hilo = new Hilo (angulo_eje_rotacion, tg_rotar, valor_inicial,valor_final);

			hilo.start();

			valor_inicial=valor_inicial+offset;			
			
		
			
			
	 }
	
}