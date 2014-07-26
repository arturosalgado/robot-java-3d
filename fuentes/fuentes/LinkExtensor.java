import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;



class LinkExtensor extends ObjetoQueSeExtiende

{
			
		Box link;	
		Box innerlink;
	  float dim_x=0.05f;
	  float dim_y=0.5f;
	  float dim_z=0.10f;
	  float inner_dim_x=dim_x-0.01f;
	  float inner_dim_y=dim_y-0.01f;
	  float inner_dim_z=dim_z-0.01f;
		
		final int id = v.LINK_EXTENSOR;
	  
	  LinkExtensor ()
	  
	  {
	  
	  crearLink();	
	  	
	  }
	
	  LinkExtensor (float valor_1, float valor_2, float valor_3, String nombre)
	  
	  {
	  
	  dim_x = valor_1;
	  dim_y = valor_2;
	  dim_z = valor_3;
	  
	  
	  dim_x = dim_x/2;
	  dim_y = dim_y/2;
	  dim_z = dim_z/2;
	  
	  
	  
	  
	  this.nombre = nombre;
	  
	  
	  inner_dim_x=dim_x-0.015f;
	  inner_dim_y=dim_y;
	  inner_dim_z=dim_z-0.015f;

	  
	  
	  
	  crearLink();	
	  	
	  }


	  LinkExtensor (Datos datos)
	  
	  {
	  
	  dim_x = datos.valor_1;
	  dim_y = datos.valor_2;
	  dim_z = datos.valor_3;
	  
	  this.nombre = datos.name;
	  
	  
	  
	  

	  
	  
	  
	  
	  
	  inner_dim_x=dim_x-0.015f;
	  inner_dim_y=dim_y;
	  inner_dim_z=dim_z-0.015f;

	  
	  
	  
	  crearLink();	
	  	
	  }




	  public void crearLink()
  	
  	{	

  	
  	link = new Box(dim_x,dim_y,dim_z,apariencia);
  	innerlink = new Box(inner_dim_x,inner_dim_y,inner_dim_z,apariencia);
  	
  	v3f.set(0.0f,dim_y,0.0f);
  	t3d.set(v3f);
  	tg_all.setTransform(t3d);
  	tg_all.addChild(link);
  	tg_translada.addChild(innerlink);
  	tg_all.addChild(tg_translada);
  	v3f.set(0.0f,inner_dim_y,0.0f);
  	t3d.set(v3f);
  	tg_adherir.setTransform(t3d);
  	
  	
  	tg_translada.addChild(tg_adherir);
  	addChild(tg_all);
  	
	  
	  }
	  	
	  
	  public Datos getDatos()
	  {
	  	
	  	Datos datos = new Datos (dim_x, dim_y,dim_z, id , nombre);
	  	
	  	return datos;
	  	
	  }
	  


	  
	
}