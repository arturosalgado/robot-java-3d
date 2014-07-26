import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;


class Joint2 extends ObjetoQueRota
{
	
	Cylinder cil_lo;
	Cylinder cil_hi;
	Box      box_lo;
	Box      box_hi;
	Caja     caja_rite;
	Caja     caja_left;
	//parametros para 
	//dos cilindros 
	private float    cil_rad1   =0.5f*0.25f;//llegan
	private float    cil_rad2   =0.5f*0.25f;//ll
	private float    cil_high  	=1.5f*0.25f;//ll
	
	
	//dos cajas rounded
	private float    caja_rad1  =cil_rad1;
	private float    caja_rad2  =cil_rad2;
	private float    caja_len  	=0.0f;//
	private float    caja_gros 	=0.2f*0.25f;//ll
	
	
	// dos boxes 
	private float    dim_x=0.5f*0.25f;//
	private float    dim_y=0.2f*0.25f;
	private float    dim_z=0.5f*0.25f;
	
	private float    scale = 1.0f;
	 
	 
	 
	private float d;
	
	
	final int id = v.JOINT_2;
 
	TransformGroup tg_box_lo;
	TransformGroup tg_box_hi;
	TransformGroup tg_caja_left;
	TransformGroup tg_caja_rite;
	TransformGroup tg_all;
	TransformGroup tg_cil_rot_hi;
	TransformGroup tg_cil_rot_lo;
	TransformGroup tg_cil_hi;
	TransformGroup tg_cil_lo;
	TransformGroup tg_rot1;
	TransformGroup tg_rot2;
	TransformGroup tg_rot3;


	Transform3D    t3d = new Transform3D();
	Transform3D    t3d1= new Transform3D();
	
	AxisAngle4f  aa=new AxisAngle4f();
	AxisAngle4f  angulo_eje_rotacion;
	AxisAngle4f  angulo_eje_rotacion1;
	
	
	Hilo hilo;
	
	int  valor_inicial=0;
	int  valor_final = 0;
	int  offset  =150;



	int  valor_inicial1=0;
	int  valor_final1 = 0;
	int  offset1  =150;
	
	Hilo hilo1;
	
	

	Joint2()
	{
		
		
	crearJoint2();
		
		
	} 
	
	Joint2(float valor_1, float valor_2, float valor_3, float valor_4,
	float valor_5, float valor_6, float valor_7, float valor_8, String nombre)
	{
	
	
	
	
		
	cil_rad1   =valor_1;//llegan
	cil_rad2   =valor_2;//ll
	cil_high   =valor_3;//ll
	
	
	//dos cajas rounded
	
	caja_rad1  =cil_rad1;
	caja_rad2  =cil_rad2;
	caja_len   =valor_4;
	caja_gros  =valor_5;
	
	
	// dos boxes 
	dim_x=valor_6;//
	dim_y=valor_7;
	dim_z=valor_8;
	
	
	d = caja_len-(caja_rad1+caja_rad2);
	
	
	
	this.nombre=nombre;
	
	
	
	
		
	crearJoint2();
		
		
	} 
		
	Joint2(Datos datos)
	{
		
	cil_rad1   =datos.valor_1;//llegan
	cil_rad2   =datos.valor_2;//ll
	cil_high   =datos.valor_3;//ll
	
	
	//dos cajas rounded
	
	caja_rad1  =cil_rad1;
	caja_rad2  =cil_rad2;
	caja_len   =datos.valor_4;
	caja_gros  =datos.valor_5;
	
	
	// dos boxes 
	dim_x=datos.valor_6;//
	dim_y=datos.valor_7;
	dim_z=datos.valor_8;
	
	d = caja_len-(caja_rad1+caja_rad2);

	
	this.nombre=datos.name;
	
	crearJoint2();
	
  }
	
	
	
	
	
	public void  crearJoint2()
	{
		
		cil_lo = new Cylinder(cil_rad1,cil_high,apariencia);
		cil_hi = new Cylinder(cil_rad2,cil_high,apariencia);
		
		
		
		
		box_lo = new Box(dim_x,dim_y,dim_z,apariencia);
		box_hi = new Box(dim_x,dim_y,dim_z,apariencia);
		
		
		
		caja_left = new Caja(caja_rad1,caja_rad2,caja_len,caja_gros,apariencia);
		caja_rite = new Caja(caja_rad1,caja_rad2,caja_len,caja_gros,apariencia);
		
	
		angulo_eje_rotacion = new AxisAngle4f(0.0f,1.0f,0.0f,0.0f);
		angulo_eje_rotacion1 = new AxisAngle4f(0.0f,1.0f,0.0f,0.0f);

		
		
		
		t3d.set(scale);
		tg_all = new TransformGroup(t3d);
		
		
		//box base
		
		t3d.set(new Vector3f(0.0f, dim_y ,0.0f));//que empiece en y=0
		tg_box_lo = new TransformGroup(t3d);
		tg_box_lo.addChild(box_lo);
		tg_all.addChild(tg_box_lo);
		
	
		//cilindro base offset
		t3d.set(new Vector3f((dim_y/2)+cil_rad1,0.0f,0.0f));//translada
		tg_cil_lo = new TransformGroup(t3d);
		
		t3d1.rotZ(Math.toRadians(90));//rota
		tg_rot1 = new TransformGroup(t3d1);
		
		
			// inicializa el tg rotor	
		tg_cil_rot_lo = new TransformGroup();
		tg_cil_rot_lo.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg_cil_rot_lo.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		
		
		
			//anade al offset el rotor
			//y al rotor le anade el cil		


		tg_rot1.addChild(tg_cil_lo);
		tg_cil_lo.addChild(tg_cil_rot_lo);	
		tg_cil_rot_lo.addChild(cil_lo);

			//anade esta rama al tg_box low
		tg_box_lo.addChild(tg_rot1);
		
		// anade los lados 
		
			//inicializa lado izq 
		aa.set(0.0f,0.0f,1.0f,-(float)Math.toRadians(90));	
		t3d.set(new Vector3f(0.0f,(-cil_high/2)-(caja_gros/2),0.0f));	
		t3d.setRotation(aa);	
		tg_caja_left= new TransformGroup(t3d);
			
		tg_caja_left.addChild(caja_left);
		
		tg_cil_rot_lo.addChild(tg_caja_left);
		
			// inicializa lado der
			
		aa.set(0.0f,0.0f,1.0f,-(float)Math.toRadians(90));	
		t3d.set(new Vector3f(0.0f,(cil_high/2)+(caja_gros/2),0.0f));	
		t3d.setRotation(aa);	
		tg_caja_rite= new TransformGroup(t3d);
			
		tg_caja_rite.addChild(caja_rite);
		
		tg_cil_rot_lo.addChild(tg_caja_rite);
			
		
		// cilindro superior 
		
		
		t3d.set(new Vector3f(d,0.0f,0.0f));//translada
		tg_cil_hi = new TransformGroup(t3d);
		
		
		
			// inicializa el tg rotor	
		tg_cil_rot_hi = new TransformGroup();
		tg_cil_rot_hi.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg_cil_rot_hi.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
			//anade al offset el rotor
			//y al rotor le anade el cil		


		tg_cil_hi.addChild(tg_cil_rot_hi);	
		tg_cil_rot_hi.addChild(cil_hi);

		
		tg_cil_rot_lo.addChild(tg_cil_hi);
		
		
		// anade la caja superior
		t3d.set(new Vector3f(0.0f,((dim_y/2)+caja_rad2),0.0f));
		//t3d.set(new Vector3f(0.0f,0.0f,0.0f));

		tg_box_hi=new TransformGroup(t3d);
		
		t3d1.rotZ(Math.toRadians(-90));
		tg_rot3=new TransformGroup(t3d1);
		
		tg_rot3.addChild(tg_box_hi);
		
		tg_box_hi.addChild(box_hi);
		
		v3f.set(0.0f,dim_y/2,0.0f);
		t3d.set(v3f);
		tg_adherir.setTransform(t3d);
		tg_box_hi.addChild(tg_adherir);
		
		
		tg_cil_rot_hi.addChild(tg_rot3);
		
		addChild(tg_all);
		
	}
	
	
	
	/*
	public synchronized void rotar_b(int grados,int resolucion, boolean modo)		
	{
		offset = grados;
			
			valor_final=valor_final + offset;
			
			//si valor inicial es mayor que valor final 
			// cambia la direccion en que se llama al hilo
			if (valor_inicial<valor_final)
			
			{
			
				hilo = new Hilo (true,resolucion,angulo_eje_rotacion, tg_cil_rot_hi, valor_inicial,valor_final);

				if (modo==true)
				{
						hilo.start();
				}	
				else
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
				
					valor_inicial=valor_inicial+offset;			

				
			
			}

			//vf es menor
			else
			{
				
				hilo = new Hilo (false,resolucion,angulo_eje_rotacion, tg_cil_rot_hi, valor_inicial,valor_final);

			
				if (modo==false)
				{
					
				hilo.start();

					
				}
				
				else
				{

				
						try
					{
						hilo.join();
					}
					catch(InterruptedException e)
					{
					
					}

					
				}
				
				 valor_inicial=valor_inicial+offset;			

			}
			

	}
	*/
	
	
		 synchronized public void rotar_b(int grados,int resolucion, boolean modo)
	 {

			offset = grados;
			
			valor_final=valor_final + offset;
			
			//si valor inicial es mayor que valor final 
			
		
			
			// cambia la direccion en que se llama al hilo
			if (valor_inicial<valor_final)
			{
				hilo = new Hilo (true,resolucion,angulo_eje_rotacion, tg_cil_rot_hi, valor_inicial,valor_final);
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
				
				hilo = new Hilo (false,resolucion,angulo_eje_rotacion, tg_cil_rot_hi, valor_inicial,valor_final);
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		 synchronized public void rotar_a(int grados,int resolucion, boolean modo)
	 {

			offset1 = grados;
			
			valor_final1=valor_final1 + offset1;
			
			//si valor inicial es mayor que valor final 
			
		
			
			// cambia la direccion en que se llama al hilo
			if (valor_inicial1<valor_final1)
			{
				hilo1 = new Hilo (true,resolucion,angulo_eje_rotacion1, tg_cil_rot_lo, valor_inicial1,valor_final1);
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
				
				hilo1 = new Hilo (false,resolucion,angulo_eje_rotacion1, tg_cil_rot_lo, valor_inicial1,valor_final1);
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
	


		
	Datos datos = new Datos(cil_rad1, cil_rad2,cil_high, caja_len,caja_gros,dim_x,dim_y,dim_z, id, nombre);	
		
	return datos;		
	}
	
	
	
}