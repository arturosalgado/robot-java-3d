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
		
				Joint3 (Datos datos)
		{
			
			radio=datos.valor_1;
			alto= datos.valor_2;
			this.nombre =datos.name;
			
			crearLink();
				
		}
		
		
		public void crearLink()
		{
			
			id = v.JOINT_3;
			angulo_eje_rotacion.set(0.0f,1.0f,0.0f,0.0f);
			cilindro_rotor= new Cylinder(radio,alto,apariencia);	
			v3f.set(0.0f,alto/2,0.0f);
			t3d.set(v3f);
			tg_rotor = new TransformGroup(t3d);
			tg_rotor.addChild(tg_rotar);
			tg_rotar.addChild(cilindro_rotor);
		
		//	v3f.set(0.0f,alto/2,0.0f);
		//	t3d.set(v3f);
			
			
			
			
		//	tg_adherir.setTransform(t3d);
			tg_rotar.addChild(tg_adherir);
			
			
			
			tg_all.addChild(tg_rotor);		
			addChild(tg_all);
			
		}
		
		public void adherirHijo(Componente componente)
		{
						float x;
			float y;
			float z;
			Datos datos;
				
						
			
			if (componente.id == v.LINK)
			{
				
			 datos = componente.getDatos();
			 x = datos.valor_1;
			 y = datos.valor_2;
			 z = datos.valor_3;
			 
			 v3f.set(-radio,alto/2+x/2,0.0f);
			 t3d.set(v3f);
			 t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(-90)));
			 tg_adherir.setTransform(t3d);
			 tg_adherir.addChild(componente);
			 //System.out.println("Here");
			
				
			}
		
		else	
			if (componente.id == v.LINK_2)
			{
				
			 datos = componente.getDatos();
			 x = datos.valor_4;
			 
			 v3f.set(-radio,alto/2+x/2,0.0f);
			 t3d.set(v3f);
			 t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(-90)));
			 tg_adherir.setTransform(t3d);
			 tg_adherir.addChild(componente);
			 //System.out.println("Here 1");
			
				
			}
		else	
		if (componente.id == v.LINK_3)

		{
	
				 datos = componente.getDatos();
			 x = datos.valor_4*2;
			 
			 v3f.set(-radio,alto/2+x/2,0.0f);
			 t3d.set(v3f);
			 t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(-90)));
			 tg_adherir.setTransform(t3d);
			 tg_adherir.addChild(componente);
			// System.out.println("Here 1");

	
	
		}

			
			else
			{
			
						
			v3f.set(0.0f,alto/2f,0.0f);
			t3d.set(v3f);
			tg_adherir.setTransform(t3d);

			tg_adherir.addChild(componente);
			
			

			
		  }
			
			
			
			
		}
		
		
		
		
		public Datos getDatos()
		{
			
		Datos datos = new Datos(radio,alto,id, nombre);	
		return datos;
			
			
		}
}						
														