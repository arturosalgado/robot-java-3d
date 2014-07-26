import javax.media.j3d.*;
import javax.vecmath.*;

class LinkCilindrico extends ObjetoFisico

{
	
		Cil   cilindro;	
		float radio_bas;
		float radio_sup;
		float altura;	
		
		final int id= v.LINK_CILINDRICO;
		
		TransformGroup tg_cilindro;
			
		LinkCilindrico(float valor_1, float valor_2, float valor_3, String nombre)
		{
			
			radio_bas = valor_1;
			radio_sup  = valor_2;
			altura=      valor_3;
			this.nombre = nombre;
			crearLink();
			
		}	
	
		LinkCilindrico(Datos datos)
		{
			
			radio_bas 	= datos.valor_1;
			radio_sup  	= datos.valor_2;
			altura			= datos.valor_3;
			this.nombre = datos.name;
			
			crearLink();
			
		}	
	
		public void crearLink()
		
		{
			
			cilindro= new Cil(radio_bas,radio_sup,altura, apariencia);
			
			v3f.set(0.0f,altura/2,0.0f);
			t3d.set(v3f);
			tg_cilindro= new TransformGroup(t3d);
			
			
			
			
			tg_cilindro.addChild(cilindro);
			
			addChild(tg_cilindro);
			
			
			tg_adherir.setTransform(t3d);
			
			tg_cilindro.addChild(tg_adherir);
			
		}
		
		
		 public Datos getDatos()
		{
			
			
			Datos datos = new Datos (radio_bas,radio_sup,altura, id, nombre);
			return datos;
			
			
			
		}
		
		
		
}