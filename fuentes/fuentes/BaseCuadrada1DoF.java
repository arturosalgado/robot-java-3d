import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

class BaseCuadrada1DoF extends ObjetoQueRota
{
	
			Box box;
			float comp_x=0.20f;
			float comp_y=1.0f;
			float comp_z=0.20f;
			float cilindro_radio=0.05f;
			float cilindro_alto=0.07f;
			final int  id = v.BASE_CUADRADA_1DOF; 					




			Cylinder cilindro_rotor;		
					
			Transform3D    t3d_all;
		
			TransformGroup tg_translada;
			Transform3D    t3d_translada;
				
			TransformGroup tg_base;
			Transform3D    t3d_base;
				
			
			
		
			float escala = 1.0f;
			
			
			// offset de la rotacion
			int  valor_inicial=0;
			int  valor_final = 0;
			int  offset  =45;

			Hilo hilo;

			
			
			
			BaseCuadrada1DoF()	
			{
				
				comp_x=comp_x/2;
			  comp_y=comp_y/2;
				comp_z=comp_z/2;




				crearBase();
			
			}
			
			BaseCuadrada1DoF(float x, float y, float z, float r, float h, String name)	
			{
				comp_x=x;
				comp_y=y;
				comp_z=z;
				comp_x=comp_x/2;
			  comp_y=comp_y/2;
				comp_z=comp_z/2;
				this.nombre = name;
				
				cilindro_radio=r;
				cilindro_alto=h;
				

				crearBase();
			
			
			}
			
						BaseCuadrada1DoF(Datos datos)
			{
				comp_x=datos.valor_1;
				comp_y=datos.valor_2;
				comp_z=datos.valor_3;
				
				cilindro_radio=datos.valor_4;
				cilindro_alto=datos.valor_5;
				this.nombre = datos.name;

				
				
				
				
				comp_x=comp_x/2;
			  comp_y=comp_y/2;
				comp_z=comp_z/2;
				this.nombre = datos.name;
				
				
				
				
				
				//eje en que rota
//
				crearBase();
			
			}
			
			
	

	
			

		void crearBase()
		{


				
			angulo_eje_rotacion.set(0.0f,1.0f,0.0f,0.0f);

			
			
			box = new Box(comp_x,comp_y,comp_z,apariencia);	
			
			t3d_base = new Transform3D();
			t3d_base.set(new Vector3f(0.0f,comp_y,0.0f));
			tg_base = new TransformGroup(t3d_base);
			tg_base.addChild(box);

			tg_all.addChild(tg_base);	
			
			cilindro_rotor= new Cylinder(cilindro_radio,cilindro_alto,apariencia);
			t3d_translada= new Transform3D();
			t3d_translada.set(new Vector3f(0.0f,comp_y+cilindro_alto/2,0.0f));
			tg_translada = new TransformGroup(t3d_translada);
			
			
			tg_rotar.addChild(cilindro_rotor);
			tg_translada.addChild(tg_rotar);
			tg_base.addChild(tg_translada);
			
			
			t3d_translada.set(new Vector3f(0.0f,cilindro_alto/2,0.0f));
			tg_adherir.setTransform(t3d_translada);
			tg_rotar.addChild(tg_adherir);
			
			addChild(tg_all);
			
			

			
			
			
		}
		
		public Datos getDatos()
		{

			Datos datos = new Datos(comp_x*2,comp_y*2,comp_z*2,cilindro_radio,cilindro_alto,id,this.nombre);
			
			return 	datos;		
			
		}
		
		
}