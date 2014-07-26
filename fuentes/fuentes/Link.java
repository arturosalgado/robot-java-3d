import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;



class Link extends ObjetoFisico

{
			
			
		Box link;	
	  float dim_x=0.5f;
	  float dim_y=0.5f;
	  float dim_z=0.5f;
	 
	  
	  
	  Link ()
	  
	  {
	  	
		crearLink();
	  
	  }
	
		  Link (float valor_1,float valor_2,float valor_3,String nom)
	  
	  {
	  	
	  	dim_x= valor_1;
	  	dim_y=valor_2;
	  	dim_z = valor_3;
	  	
	  	dim_x=dim_x/2;
	  	dim_y=dim_y/2;
	  	dim_z=dim_z/2;
  	
  	 	this.nombre = nom;
	  	
			crearLink();	  	
	  	
	  	
	  	
	  }
	
			  Link (Datos datos)
	  
	  {
	  	
	  	dim_x= datos.valor_1;
	  	dim_y=datos.valor_2;
	  	dim_z = datos.valor_3;
	  	
	  		  	dim_x=dim_x/2;
	  	dim_y=dim_y/2;
	  	dim_z=dim_z/2;

	  	
	  	
	  	
	  	this.nombre = datos.name;
	  	crearLink();	
	  	
	  }
	
	
		public void crearLink()
		{
			
			id = v.LINK;
			link = new Box(dim_x,dim_y,dim_z,apariencia);
			v3f.set(0.0f,dim_y,0.0f);
			t3d.set(v3f);
			tg_all.setTransform(t3d);
	  	tg_all.addChild(link);
	  	tg_all.addChild(tg_adherir);
			addChild(tg_all);
			
			
		}		
			
			
		public void adherirHijo	(Componente componente)
		{
			
			float x;
			float y;
			float z;
			Datos datos;
				

			
		if (componente.id ==v.JOINT_3)
				{
					datos=componente.getDatos();
					
					v3f.set(-dim_x,dim_y-datos.valor_1,0.0f);
					t3d.set(v3f);
					t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(90)));
					tg_adherir.setTransform(t3d);
					tg_adherir.addChild(componente);
					
					
				}
			
			else
			
			{
				
			v3f.set(0.0f,dim_y,0.0f);
	  	t3d.set(v3f);
	  	tg_adherir.setTransform(t3d);
			tg_adherir.addChild(componente);
			 	
				
				
			}
			
			
		}
			
			
			
    public Datos getDatos()
    {
    	
    Datos datos = new Datos (dim_x*2, dim_y*2, dim_z*2, id, nombre);	
    
    return datos;
    	
    	
    }
	  
	
}