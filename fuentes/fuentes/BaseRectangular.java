import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

class BaseRectangular extends ObjetoFisico
{
	
			Box box;
			float comp_x=0.08f;
			float comp_y=0.17f;
			float comp_z=0.08f;
			float cilindro_radio=0.05f;
			float cilindro_alto=0.07f;
					
			Cylinder cilindro_rotor;		
					
			TransformGroup tg_all;
			Transform3D    t3d_all;
		
			TransformGroup tg_translada;
			Transform3D    t3d_translada;
				
			TransformGroup tg_rotar;
			
		
			float escala = 1.0f;
			
			AxisAngle4f angulo_eje_rotacion;
			
			// offset de la rotacion
			int  valor_inicial=0;
			int  valor_final = 0;
			int  offset  =45;

			Hilo hilo;

			
			BranchGroup bg;
			
			
			BaseRectangular()	
			{
				
				
			
			bg= new BranchGroup();

				
				
			escala(escala);
			box = new Box(comp_x,comp_y,comp_z,apariencia);	
			
			
			tg_all.addChild(box);	
			
			cilindro_rotor= new Cylinder(cilindro_radio,cilindro_alto,apariencia);
			t3d_translada= new Transform3D();
			t3d_translada.set(new Vector3f(0.0f,0.18f,0.0f));
			tg_translada = new TransformGroup(t3d_translada);
			
			
			
			
			tg_rotar  = new TransformGroup();
	
			tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			tg_rotar.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
			
	
			tg_rotar.addChild(cilindro_rotor);
			
					
			
			
			tg_translada.addChild(tg_rotar);
			
			tg_all.addChild(tg_translada);
			
			addChild(tg_all);
			
			
			}
			
			
			BaseRectangular(float x,float y,float z)
			{
				escala(escala);
				comp_x=x;
				comp_y=y;
				comp_z=z;
				
			box = new Box(comp_x,comp_y,comp_z,apariencia);	
			tg_all.addChild(box);	
			addChild(tg_all);
			
				
				addChild(box);
					
			}
	
			void escala(float escala)
			{
				t3d_all = new Transform3D();
				t3d_all.set(escala);
				tg_all = new TransformGroup(t3d_all);
				
			}	
	
	
			
		public void rotar()		
		{
				//se crea aqui por que el hilo muere despues de que es ejecutado,
				// pa correr otra vez se crea un nuevo hilo
				// la memoria no se quema por que el hilo 
				// muere solo
				// por lo tanto una clase qye extiende a la clase Thread 
				// es distinta de otro tipo de clases en terminos de que 
				// la clase thread una vez que se ejecuta el metodo run 
				// muere la instancia sola,
				// no asi otras clases , que sus metodos permanecen
				// para posterioires llamados 
				// como el tg pasa por referencia 
				// no hace falta otra variable 
				// para guardar el resultado del tg pero si del valor inicial y del valor final
				//decidir donde va a ir el for aqui o en el hilo, normalmente va en el hilo
			angulo_eje_rotacion   = new AxisAngle4f(0.0f, 1.0f,0.0f, 0.0f);
	

			valor_final=valor_final + offset;
				
			
			hilo = new Hilo (angulo_eje_rotacion, tg_rotar, valor_inicial,valor_final);

			hilo.start();
			
			
			valor_inicial=valor_inicial+offset;			
			
			//valor_final += offset;
		
		}
		
		void addJoint(Joint  joint)
		
		{
				Transform3D t3= new Transform3D();
				t3.set(new Vector3f(0.0f,cilindro_alto/2,0.0f));
				TransformGroup tg=new  TransformGroup(t3);
		//		BranchGroup bg = new BranchGroup();
				
				tg.addChild(joint);//si puede tener un bg
				
		//		tg_rotar.addChild(tg);
				
				
				bg.addChild(tg);
				
				
				
				tg_rotar.addChild(bg);
			
			
		}
		
		
		
}