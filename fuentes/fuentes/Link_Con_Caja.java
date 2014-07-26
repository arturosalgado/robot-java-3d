import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

class Link_Con_Caja extends ObjetoQueSeExtiende
{
		
	Box        base;
	Box        caja;
	
	private float dim_x_base=0.05f;
	private float dim_y_base=0.5f;
	private float dim_z_base=0.05f;
	
	private float dim_x_caja;
	private float dim_y_caja;
	private float dim_z_caja;
	
	final int id = v.LINK_CON_CAJA;
	
	TransformGroup tg_base;
	TransformGroup tg_caja;
	
	
	int  valor_inicial=0;
	int  valor_final = 0;

  boolean flag=true;
	
	
	Link_Con_Caja()
	{
		
		dim_x_caja=dim_x_base+(dim_x_base*0.5f);
		dim_y_caja=dim_z_base+(dim_z_base*1.5f);
		dim_z_caja=dim_z_base+(dim_z_base*0.5f);

		crearLink();
		
		
	}
	
	
		Link_Con_Caja(float valor_1, float valor_2, float valor_3, float valor_4, float valor_5, float valor_6, String nombre)
	{
		
		
		dim_x_base=valor_1/2;
		dim_y_base=valor_2/2;
		dim_z_base=valor_3/2;
		dim_x_caja=valor_4/2;
		dim_y_caja=valor_5/2;
		dim_z_caja=valor_6/2;
		this.nombre = nombre;

		crearLink();
		
		
	}
	
			Link_Con_Caja(Datos datos)
	{
		
		
		dim_x_base=datos.valor_1;
		dim_y_base=datos.valor_2;
		dim_z_base=datos.valor_3;
		dim_x_caja=datos.valor_4;
		dim_y_caja=datos.valor_5;
		dim_z_caja=datos.valor_6;
		this.nombre = datos.name;

		crearLink();
		
		
	}
	public void crearLink()
	{
		
		base= new Box(dim_x_base,dim_y_base,dim_z_base,apariencia);
		caja= new Box(dim_x_caja,dim_y_caja,dim_z_caja,apariencia);
		
					
		
		// base
		v3f.set(0.0f,dim_y_base,0.0f);
		t3d = new Transform3D();
		t3d.set(v3f);
		tg_base = new TransformGroup(t3d);
		tg_base.addChild(base);
		tg_all.addChild(tg_base);
		
		// caja 
		
			  		

		
		
		v3f.set(0.0f,(dim_y_base)-dim_y_caja,0.0f);
		t3d.set(v3f);
		tg_caja = new TransformGroup(t3d);
		tg_caja.addChild(tg_translada); 
		tg_translada.addChild(caja);
		tg_base.addChild(tg_caja);
		
		
		v3f.set(-dim_x_caja,0.0f,0.0f);
		t3d.set(v3f);
		t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(90)));
		tg_adherir.setTransform(t3d);
		tg_translada.addChild(tg_adherir);
		
		
		
		
		addChild(tg_all);
	}
	
	public Datos getDatos()
	{
		
		Datos datos = new Datos (dim_x_base,dim_y_base,dim_z_base,dim_x_caja,dim_y_caja,dim_z_caja,id,nombre);
		return datos;
		
	}

}	