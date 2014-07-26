import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

 

class Joint3  extends ObjetoQueRota
{
		Cylinder  cilindro_rotor;
		float radio=0.15f;
		float alto=0.07f;
		TransformGroup tg_rotor;
		
		Joint3 (float valor_1, float valor_2, String nombre)
		{
			
			radio=valor_1;
			alto= valor_2;
			this.nombre = nombre;
			
			crearLink();
				
		}
		
		public void crearLink()
		{
			
			cilindro_rotor= new Cylinder(radio,alto,apariencia);	
			v3f.set(0.0f,alto/2,0.0f);
			t3d.set(v3f);
			tg_rotor = new TransformGroup(t3d);
			tg_rotor.addChild(cilindro_rotor);
			tg_all.addChild(tg_rotor);		
			
			
		}
}						
														