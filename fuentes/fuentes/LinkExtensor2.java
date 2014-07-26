import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;



class LinkExtensor2 extends ObjetoQueSeExtiende

{
			
		Cylinder link;	
		Cylinder innerlink;
	  float high=0.5f;
	  float rad=0.05f;
	  float inner_high;
	  float inner_rad;
	  
	  final int id = v.LINK_EXTENSOR2;
	  
	  boolean flag=true;
	  
	  LinkExtensor2 ()
	  
	  {
	  	
			crearLink();	  	
	  	
	  	
	  }
	
	  LinkExtensor2 (float valor_1, float valor_2, String  nombre)
	  
	  {
	  	
  		rad= valor_1;
  		high= valor_2;
  		this.nombre=nombre;
  		
			inner_high=high;
	  	inner_rad=rad-0.015f;

  		crearLink();
	  	
	  	
	  	
	  }
	  
	  	  LinkExtensor2 (Datos datos)
	  
	  {
	  	
  		rad= datos.valor_1;
  		high= datos.valor_2;
  		this.nombre=datos.name;
  		
			inner_high=high;
	  	inner_rad=rad-0.015f;

  		crearLink();
	  	
	  	
	  	
	  }
	  
	  
	  public void crearLink()
	  {
	  	
	  	link = new Cylinder(rad,high,apariencia);
	  	innerlink = new Cylinder(inner_rad,inner_high,apariencia);
	  	
	  	v3f.set(0.0f,high/2,0.0f);
	  	t3d.set(v3f);
	  	tg_all.setTransform(t3d);
	  	
	  	
	  	
	  	
	  	tg_all.addChild(link);
	  	tg_translada.addChild(innerlink);
	  	
	  	
	  	v3f.set(0.0f,high/2,0.0f);
	  	t3d.set(v3f);
	  	tg_adherir.setTransform(t3d);
	  	
	  	
	  	
	  	tg_translada.addChild(tg_adherir);
	  	
	  	
	  	tg_all.addChild(tg_translada);


			addChild(tg_all);
	  	
	  }
	
	 public Datos getDatos()
	 {
	 	
	 	Datos datos = new Datos(rad, high, id, nombre);
	 	return datos;
	 	
	 }
	
	
}