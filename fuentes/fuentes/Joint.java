import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;



class Joint  extends ObjetoQueRota
{
		
		Cylinder  cilindro_base;
		Cylinder  cilindro_horizontal;
		Cylinder  cilindro_rotor;
		
		TransformGroup tg_cil_base;
		TransformGroup tg_cil_horizontal;
		TransformGroup tg_cil_rotor;
		
		float radio_base=0.27f;
		float alto_base =0.1f ;
		
		float radio_horizontal=0.1f;
		float alto_horizontal=0.68f;
		
		float radio_rotor=0.15f;
		float alto_rotor=0.07f;
		
	
		
		
		
			AxisAngle4f  angulo_eje_rotacion1;
	
	



	int  valor_inicial1=0;
	int  valor_final1 = 0;
	int  offset1  =150;
	
	Hilo hilo1;
	
		
		
		
		
		
		
		
		Joint ()
		{
				
		crearJoint();		
				
				
		}
		
			Joint (float valor_1, float valor_2, float valor_3, float valor_4, float valor_5, float valor_6, String nombre)
		{
		
		
		id = v.JOINT;
		
				
		radio_base=valor_1;
		alto_base =valor_2;
		
		radio_horizontal=valor_3;
		alto_horizontal=valor_4;
		
		radio_rotor=valor_5;
		alto_rotor=valor_6;
		
	
		this.nombre = nombre;
			
				
		crearJoint();		
				
				
		}
				
			Joint (Datos datos)
		{
		
				
		radio_base=datos.valor_1;
		alto_base =datos.valor_2;
		
		radio_horizontal=datos.valor_3;
		alto_horizontal=datos.valor_4;
		
		radio_rotor=datos.valor_5;
		alto_rotor=datos.valor_6;
		
	
		this.nombre = datos.name;
			
				
		crearJoint();		
				
				
		}	
			
		void crearJoint()
		{
			
			
			angulo_eje_rotacion.set(0.0f, 1.0f,0.0f, 0.0f);
				angulo_eje_rotacion1=new AxisAngle4f(0.0f, 1.0f,0.0f, 0.0f);

			cilindro_base = new Cylinder(radio_base,alto_base,apariencia);
			cilindro_horizontal = new Cylinder(radio_horizontal,alto_horizontal,apariencia);
			cilindro_rotor = new Cylinder(radio_rotor,alto_rotor,apariencia);
	
	
			v3f.set(0.0f,alto_base/2f,0.0f);
			t3d.set(v3f);
			tg_cil_base= new TransformGroup(t3d);
			tg_cil_base.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			tg_cil_base.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

			
			
			
			
			
			
			tg_cil_base.addChild(cilindro_base);

		
			
			t3d.rotZ(Math.toRadians(90));
			tg_cil_horizontal=new TransformGroup(t3d);
			
			Transform3D t3d_des= new Transform3D();
			t3d_des.set(new Vector3f(0.0f,alto_base/2,0.0f));
			TransformGroup tg_des = new TransformGroup(t3d_des);
			
			tg_cil_base.addChild(tg_des);
			
			tg_des.addChild(tg_cil_horizontal);
			
			tg_cil_horizontal.addChild(cilindro_horizontal);
			
		
		
			v3f.set(0.0f,alto_horizontal/2,0.0f);
			t3d.set(v3f);
			tg_cil_rotor= new TransformGroup(t3d);
			
			
			
			// inserta el grupo de rotacion 
			
			tg_cil_rotor.addChild(tg_rotar);
			
			tg_rotar.addChild(cilindro_rotor);
			
			
			
			// inserta el adherir 
			tg_rotar.addChild(tg_adherir);

	
			
			
			
			tg_cil_horizontal.addChild(tg_cil_rotor);
			
			
			

			tg_all.addChild(tg_cil_base);
			
			
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
			 
			 v3f.set(-radio_rotor,alto_rotor/2+x/2,0.0f);
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
			 
			 v3f.set(-radio_rotor,alto_rotor/2+x/2,0.0f);
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
			 
			 v3f.set(-radio_rotor,alto_rotor/2+x/2,0.0f);
			 t3d.set(v3f);
			 t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(-90)));
			 tg_adherir.setTransform(t3d);
			 tg_adherir.addChild(componente);
			 //System.out.println("Here 1");

	
	
		}

			
			else
			{
			
						
			v3f.set(0.0f,alto_rotor/2f,0.0f);
			t3d.set(v3f);
			tg_adherir.setTransform(t3d);

			tg_adherir.addChild(componente);
			
			

			
		  }
			
			
		}
			
			
			 synchronized public void rotar_a(int grados,int resolucion, boolean modo)
	 {

			offset1 = grados;
			
			valor_final1=valor_final1 + offset1;
			
			//si valor inicial es mayor que valor final 
			
		
			
			// cambia la direccion en que se llama al hilo
			if (valor_inicial1<valor_final1)
			{
				hilo1 = new Hilo (true,resolucion,angulo_eje_rotacion1, tg_cil_base, valor_inicial1,valor_final1);
				if (modo==false)
				{
					try
					{	
					hilo1.start();
					hilo1.join();
					}
					catch(InterruptedException e)
					{
					}		
					
				}
				else
				{
					
					hilo1.start();	
					
				}	
				valor_inicial1=valor_inicial1+offset1;			
			}

			//vf es menor
			else
			{
				
				hilo1 = new Hilo (false,resolucion,angulo_eje_rotacion1, tg_cil_base, valor_inicial1,valor_final1);
				if (modo==false)
				{
					try
					{
						hilo1.start();
						hilo1.join();
					}
					catch(InterruptedException e)
					{
					}
				}
				else
				{
					
				hilo1.start();	
				}
				
				
				valor_inicial1=valor_inicial1+offset1;			
			}
			

	 }//METODO 

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			public Datos getDatos()
			{
			
			Datos datos = new Datos(radio_base,alto_base,radio_horizontal,alto_horizontal,
			radio_rotor,alto_rotor,id,nombre);	
				
			return datos;	
			}


}




