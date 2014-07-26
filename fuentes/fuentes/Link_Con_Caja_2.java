import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

class Link_Con_Caja_2 extends ObjetoQueSeExtiende implements InterfazRotar
{
		
	Cylinder   base;
	Cylinder   caja;
	
	private float cil_base_rad=0.05f;
	private float cil_base_high=1.0f;
	
	private float cil_caja_rad=cil_base_rad*1.25f;
	private float cil_caja_high=cil_base_high*0.25f;
	
	
	TransformGroup tg_base;
	TransformGroup tg_caja;
	
			TransformGroup tg_rotar;
		AxisAngle4f angulo_eje_rotacion;
		Hilo hilo;

		
		// offset de la rotacion
		int  valor_inicial_rotacion=0;
		int  valor_final_rotacion  = 0;
		int  offset_rotacion  =45;
		
	int  valor_inicial=0;
	int  valor_final = 0;
	int  offset  =45;
	Hilo  hilo_rotacion;
  boolean flag=true;
	
	
	Link_Con_Caja_2()
	{
		
		crearLink();
	}
	
		Link_Con_Caja_2(float valor_1, float valor_2, float valor_3, float valor_4, String nombre)
		
	{
	cil_base_rad=valor_1;
	cil_base_high=valor_2;
	
	cil_caja_rad=valor_3;
	cil_caja_high=valor_4;
	
	
	this.nombre=nombre;
		crearLink();
		
	}
	
	
Link_Con_Caja_2(Datos datos)
		
	{
	cil_base_rad=datos.valor_1;
	cil_base_high=datos.valor_2;
	
	cil_caja_rad=datos.valor_3;
	cil_caja_high=datos.valor_4;
	
	
	this.nombre=datos.name;
		crearLink();
		
	}
	
	
	
	public void crearLink()
	{
		
		angulo_eje_rotacion = new AxisAngle4f();
		tg_rotar =new TransformGroup();	 ;
		tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	
		tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
   	tg_rotar.setCapability(Group.ALLOW_CHILDREN_EXTEND);	
		
		
		
		
		base= new Cylinder(cil_base_rad,cil_base_high,apariencia);
		caja= new Cylinder(cil_caja_rad,cil_caja_high,apariencia);
		
		
		// inicio
		
		// base
		v3f.set(0.0f,cil_base_high/2f,0.0f);
//		t3d = new Transform3D();
		t3d.set(v3f);
		tg_base = new TransformGroup(t3d);
		tg_base.addChild(base);
		tg_all.addChild(tg_base);
		
		// caja 
		
			
		
		
		v3f.set(0.0f,(cil_base_high/2f)-(cil_caja_high/2f),0.0f);
		t3d.set(v3f);
		tg_caja = new TransformGroup(t3d);
		tg_caja.addChild(tg_translada); 
		
		
		tg_translada.addChild(tg_rotar);
		
		tg_rotar.addChild(caja);
		
		
		v3f.set(-cil_caja_rad,0.0f,0.0f);
		t3d.set(v3f);
		t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(90)));
		tg_adherir.setTransform(t3d);
	
		
		tg_rotar.addChild(tg_adherir);
		
		
		
				

		
		//establece el eje de rotacion
		angulo_eje_rotacion.set(0.0f,1.0f,0.0f,0.0f);
		
		tg_base.addChild(tg_caja);
		
	
		
		
		
		addChild(tg_all);
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
	 
	
	
	public Datos getDatos()
	{
	

		
		
		
		Datos datos = new Datos(cil_base_rad,cil_base_high,cil_caja_rad,cil_caja_high,id,nombre);
		return datos;	
		
		
	}
	
		
}	