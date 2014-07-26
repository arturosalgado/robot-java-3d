// pinza 
// de los angeles salgado jose arturo 
// editor simulador de robots 
// 2001



import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;



class Hand extends ObjetoQueRota
{
		
		
		float     r_base   = 0.75f;
		float     h_base   = 2.0f;
		
		float     r_finger = 0.3f;
		float     h_finger = 1.8f;
		
		
		float     x_finger = 0.15f;
		float     y_finger = 1.0f;		
		float     z_finger = 0.6f;
		
		float     x_palm =1.2f;
		float     y_palm =0.20f;
		float     z_palm =1.0f;
		
		
		

	
	
	
		Cylinder  base;
		
		Box       palm;

		
		Cylinder  cil_low_left;
		Cylinder  cil_low_rite;
		
		Cylinder  cil_med_left;
		Cylinder  cil_med_rite;
		

		
		Box       finger_low_rite;
		Box       finger_low_left;
		
		Box       finger_med_rite;
		Box       finger_med_left;
		
		
		TransformGroup tg_palm;
		
		TransformGroup tg_rot_base;
		
		
		
		TransformGroup tg_finger_low_left;
		TransformGroup tg_finger_low_rite;
		TransformGroup tg_finger_med_left;
		TransformGroup tg_finger_med_rite;
		
		TransformGroup tg_cil_low_left;
		TransformGroup tg_cil_low_rite;
		TransformGroup tg_cil_med_left;
		TransformGroup tg_cil_med_rite;
		
		
				
		//AxisAngle4f angulo_eje_rotacion;
		AxisAngle4f angulo_eje_rotacion1;
		AxisAngle4f angulo_eje_rotacion3;
		AxisAngle4f angulo_eje_rotacion4;

		
		Hilo hilo;
		Hilo hilo1;
		Hilo hilo2;
		Hilo hilo3;
		Hilo hilo4;
		
		

// offset de la rotacion
		int  valor_inicial1=0;
		int  valor_final1 = 0;
		int  offset1  =45;
// abrir med 
		int  valor_inicial2=0;
		int  valor_final2 = 0;
		int  offset2  =45;
//cerrar med
		int  valor_inicial3=0;
		int  valor_final3 = 0;
		int  offset3  =45;

			
		final int id = v.HAND;
		
		
		Hand ()
		
		{
		
		
		crearHand();
		
		
			
		}
		
		
		Hand (float valor_1,float valor_2,float valor_3,float valor_4,float valor_5,
		float valor_6,float valor_7,float valor_8,float valor_9,float valor_10,
		String nombre )
		{
			
		r_base   =valor_1/2;
		h_base   =valor_2/2;
		
		r_finger = valor_3/2;
		h_finger = valor_4/2;
		
		
		x_finger =valor_5/2;
		y_finger = valor_6/2;
		z_finger = valor_7/2;
		
		x_palm =valor_8/2;
		y_palm =valor_9/2;
		z_palm =valor_10/2;
		
		this.nombre = nombre ;
		crearHand();
		
			
		}
				
	Hand (Datos datos )
		{
			
		r_base   =datos.valor_1/2;
		h_base   =datos.valor_2/2;
		
		r_finger = datos.valor_3/2;
		h_finger = datos.valor_4/2;
		
		
		x_finger =datos.valor_5/2;
		y_finger = datos.valor_6/2;
		z_finger = datos.valor_7/2;
		
		x_palm =datos.valor_8/2;
		y_palm =datos.valor_9/2;
		z_palm =datos.valor_10/2;
		
		this.nombre = datos.name ;
		crearHand();
		
			
		}


		public void crearHand()
		{
			
			
			angulo_eje_rotacion.set(0.0f,1.0f,0.0f,0.0f);
		
			base     = new Cylinder(r_base,h_base,apariencia);
			palm   = new Box(x_palm,y_palm,z_palm,apariencia);		
			
			cil_low_left = new Cylinder(r_finger,h_finger,apariencia);
			cil_low_rite = new Cylinder(r_finger,h_finger,apariencia);
			cil_med_left = new Cylinder(r_finger,h_finger,apariencia);
			cil_med_rite = new Cylinder(r_finger,h_finger,apariencia);
						
				 
				 
				 
		  finger_low_rite= new Box(x_finger,y_finger,z_finger,apariencia);
		  finger_low_left= new Box(x_finger,y_finger,z_finger,apariencia);
		  finger_med_rite= new Box(x_finger,y_finger,z_finger,apariencia);
		  finger_med_left= new Box(x_finger,y_finger,z_finger,apariencia);
			
			
			
	 // esacala todos los componentes 
		
		
		
		
			
			// t3d aux
			
	 		//--------add base o muneca
	 
	 		v3f.set(0.0f,h_base/2,0.0f);
	 		t3d.set(v3f);
	 		
	 		tg_rot_base = new TransformGroup(t3d);
	 		
	 		tg_rot_base.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	 		tg_rot_base.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	 		
	    tg_rot_base.addChild(base);   
	    
	    tg_rotar.addChild(tg_rot_base);
	    
	  	//---add palm
	  	
	  	Transform3D  t3d_temp = new Transform3D();
	  
	  	t3d_temp.set(new Vector3f(0.0f,h_base/2,0.0f));
	  	
	  	tg_palm = new TransformGroup(t3d_temp);
	  	
	  	tg_palm.addChild(palm);
	  	
	  	tg_rot_base.addChild(tg_palm);
	  	   
	    
	    
	    
	    //--- add cil low left 

			
	    Transform3D t3dr = new Transform3D();
	    t3dr.rotX(Math.toRadians(90));
	    TransformGroup tgr= new TransformGroup(t3dr);

	    
	    
	    t3d_temp.set(new Vector3f(-x_palm,(y_palm/2)+r_finger/2,0.0f));
	    TransformGroup tga= new TransformGroup(t3d_temp);
	    
	    
	    tg_cil_low_left = new TransformGroup();
	    tg_cil_low_left.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    tg_cil_low_left.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	   	//add cilindro 
	    //add a la base
	    
	    tga.addChild(tgr);
	    tgr.addChild(tg_cil_low_left);
	    tg_cil_low_left.addChild(cil_low_left);
			tg_palm.addChild(tga);
	    //--------
	    
	    	    
	    
	    
	    //add cil low rite 
	    
	    
	    
	    t3d_temp.set(new Vector3f(x_palm,(y_palm/2)+r_finger/2,0.0f));
	    t3dr.rotX(Math.toRadians(90));
	    
	    
	    TransformGroup tgr1= new TransformGroup(t3dr);
	    TransformGroup tga1= new TransformGroup(t3d_temp);
	    
	    
	    tg_cil_low_rite = new TransformGroup();
	    tg_cil_low_rite.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    tg_cil_low_rite.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


	    //add cilindro 


	    tg_cil_low_rite.addChild(cil_low_rite);
	    //add a la base
	    tga1.addChild(tgr1);
	    tgr1.addChild(tg_cil_low_rite);
	    
	    tg_palm.addChild(tga1);
	  
	    
	    
	    //----- add finger left low 
	    
	    
	    t3d_temp.set(new Vector3f(0.0f,0.0f,-z_finger));
	    
	    tg_finger_low_left = new TransformGroup(t3d_temp);
	   
	    //add finger
	    tg_finger_low_left.addChild(finger_low_left);
	    
	    //add a la articulacion
	    
	    tg_cil_low_left.addChild(tg_finger_low_left);
	    
    //--------add finger rite low
	    
	     t3d_temp.set(new Vector3f(0.0f,0.0f,-z_finger));
	    
	    tg_finger_low_rite = new TransformGroup(t3d_temp);
	   
	    //add finger
	    tg_finger_low_rite.addChild(finger_low_rite);
	    
	    //add a la base
	    
	    tg_cil_low_rite.addChild(tg_finger_low_rite);
	    //------------add cilindro medio izq
	   
	   
	   	t3d_temp.set(new Vector3f(0.0f,0.0f,-(z_finger+r_finger/2)));
	    
	    
	       
	    TransformGroup tga2= new TransformGroup(t3d_temp);
	 
	    
	    
	    
	    tg_cil_med_left = new TransformGroup();
	    tg_cil_med_left.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    tg_cil_med_left.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	   
	   //add cilindro 
	    tg_cil_med_left.addChild(cil_med_left);
	     //add a la base 
	    
	    tga2.addChild(tg_cil_med_left);
	    
	    tg_finger_low_left.addChild(tga2);
   //---------- add cilindro medio der
	   
	    t3d_temp.set(new Vector3f(0.0f,0.0f,-(z_finger+r_finger/2)));
	    
	    TransformGroup tga3= new TransformGroup(t3d_temp);


	    
	    tg_cil_med_rite = new TransformGroup();
	    tg_cil_med_rite.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    tg_cil_med_rite.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	   
	    //add cilindro 
	    tg_cil_med_rite.addChild(cil_med_rite);
	     //add a la base 
	    
	    tga3.addChild(tg_cil_med_rite);
	    
	    tg_finger_low_rite.addChild(tga3);
	  
	    
	   //----------- add finger sup izq 
	   
	   
	      
	    t3d_temp.set(new Vector3f(0.0f,0.0f,-(z_finger+r_finger/2)));
	    
	    tg_finger_med_left = new TransformGroup(t3d_temp);
	   
	    //add finger
	    tg_finger_med_left.addChild(finger_med_left);
	    
	    //add a la base
	    
	    tg_cil_med_left.addChild(tg_finger_med_left);
	   //------------add finger sup der 
	   
	    t3d_temp.set(new Vector3f(0.0f,0.0f,-(z_finger+r_finger/2)));
	    
	    tg_finger_med_rite = new TransformGroup(t3d_temp);
	   
	    //add finger
	    tg_finger_med_rite.addChild(finger_med_rite);
	    
	    //add a la base
	    
	    tg_cil_med_rite.addChild(tg_finger_med_rite);
	    
	   //-----------
	    tg_all.addChild(tg_rotar);
	    
	    
	    v3f.set(0.0f,y_palm,0.0f);
	    t3d.set(v3f);
	    tg_adherir.setTransform(t3d);
	    
	    tg_palm.addChild(tg_adherir);
	    
	    
	    addChild(tg_all);
			
		
		
		
		
		
		
		}
		
		
		

		
		
		public void abrir_med()
		{
			
			angulo_eje_rotacion    = new AxisAngle4f(0.0f,  1.0f,0.0f,0.0f);
			angulo_eje_rotacion1   = new AxisAngle4f(0.0f, -1.0f,0.0f,0.0f);


			valor_final2=valor_final2 + offset2;
				
			
			hilo1 = new Hilo (true,50,angulo_eje_rotacion,tg_cil_low_left, valor_inicial2,valor_final2);

			
			
			hilo2 = new Hilo (true,50,angulo_eje_rotacion1,tg_cil_low_rite, valor_inicial2,valor_final2);

			hilo1.start();
			hilo2.start();
			
			valor_inicial2=valor_inicial2+offset2;			
			
			//valor_final += offset;
		
			
			
		}	
			
void abrir_med(int grados,int resolucion, boolean modo)
{
		angulo_eje_rotacion    = new AxisAngle4f(0.0f,  1.0f,0.0f,0.0f);
		angulo_eje_rotacion1   = new AxisAngle4f(0.0f, -1.0f,0.0f,0.0f);

		offset2 = grados;
			
		valor_final2=valor_final2 + offset2;
			
			//si valor inicial es mayor que valor final 
			
		
			
			// cambia la direccion en que se llama al hilo
			if (valor_inicial2<valor_final2)
			{
				
							
			hilo1 = new Hilo (true,resolucion,angulo_eje_rotacion,tg_cil_low_left, valor_inicial2,valor_final2);

			
			
			hilo2 = new Hilo (true,resolucion,angulo_eje_rotacion1,tg_cil_low_rite, valor_inicial2,valor_final2);

				
				
				if (modo==false)
				{
					try
					{	
					hilo1.start();
					hilo2.start();
					
					hilo1.join();
					hilo2.join();
					}
					catch(InterruptedException e)
					{
					}		
					
				}
				else
				{
					
					hilo1.start();	
					hilo2.start();
					
				}	
				valor_inicial2=valor_inicial2+offset2;			
			}

			//vf es menor
			else
			{
				
			hilo1 = new Hilo (false,resolucion,angulo_eje_rotacion,tg_cil_low_left, valor_inicial2,valor_final2);

			
			
			hilo2 = new Hilo (false,resolucion,angulo_eje_rotacion1,tg_cil_low_rite, valor_inicial2,valor_final2);
		
		
		
				if (modo==false)
				{
					try
					{
						hilo1.start();
						hilo2.start();
						hilo1.join();
						hilo2.join();
					}
					catch(InterruptedException e)
					{
					}
				}
				else
				{
					
				hilo1.start();	
				hilo2.start();
				}
				
				
				valor_inicial2=valor_inicial2+offset2;			
			}	
	
	
	
	
	
}

void abrir_sup(int grados,int resolucion, boolean modo)
{
		angulo_eje_rotacion    = new AxisAngle4f(0.0f,  1.0f,0.0f,0.0f);
		angulo_eje_rotacion1   = new AxisAngle4f(0.0f, -1.0f,0.0f,0.0f);

		offset3 = grados;
			
		valor_final3=valor_final3 + offset3;
			
			//si valor inicial es mayor que valor final 
			
		
			
			// cambia la direccion en que se llama al hilo
			if (valor_inicial3<valor_final3)
			{
				
							
			hilo3 = new Hilo (true,resolucion,angulo_eje_rotacion,tg_cil_med_left, valor_inicial3,valor_final3);

			
			
			hilo4 = new Hilo (true,resolucion,angulo_eje_rotacion1,tg_cil_med_rite, valor_inicial3,valor_final3);

				
				
				if (modo==false)
				{
					try
					{	
					hilo3.start();
					hilo4.start();
					
					hilo3.join();
					hilo3.join();
					}
					catch(InterruptedException e)
					{
					}		
					
				}
				else
				{
					
					hilo3.start();	
					hilo4.start();
					
				}	
				valor_inicial3=valor_inicial3+offset3;			
			}

			//vf es menor
			else
			{
				
			hilo3 = new Hilo (false,resolucion,angulo_eje_rotacion,tg_cil_med_left, valor_inicial3,valor_final3);

			
			
			hilo4 = new Hilo (false,resolucion,angulo_eje_rotacion1,tg_cil_med_rite, valor_inicial3,valor_final3);
		
		
		
				if (modo==false)
				{
					try
					{
						hilo3.start();
						hilo4.start();
						hilo3.join();
						hilo4.join();
					}
					catch(InterruptedException e)
					{
					}
				}
				else
				{
					
				hilo3.start();	
				hilo4.start();
				}
				
				
				valor_inicial3=valor_inicial3+offset3;			
			}	
	
	
	
	
	
}
		
		
		
			public void abrir_sup()
		{
			
			angulo_eje_rotacion    = new AxisAngle4f(0.0f,  1.0f,0.0f,0.0f);
			angulo_eje_rotacion1   = new AxisAngle4f(0.0f, -1.0f,0.0f,0.0f);


			valor_final3=valor_final3 + offset3;
				
			
			hilo1 = new Hilo (true,50,angulo_eje_rotacion,tg_cil_med_left, valor_inicial3,valor_final3);

			
			
			hilo2 = new Hilo (true,50,angulo_eje_rotacion1,tg_cil_med_rite, valor_inicial3,valor_final3);

			hilo1.start();
			hilo2.start();
			
			valor_inicial3=valor_inicial3+offset3;			
			
		
			
			
		}	
		
		
		
		
		public Datos getDatos()
		
		{
	
			
		Datos datos = new Datos (r_base*2,h_base*2,r_finger*2,h_finger*2,x_finger*2,y_finger*2,z_finger*2,
		x_palm*2,y_palm*2,z_palm*2,id,nombre);	
		
		return datos;
		
			
			
			
			
			
			
		}
		
		
}														