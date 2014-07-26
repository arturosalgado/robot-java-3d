import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;


// cuando uno inicializa el constructor de la 
// subclase, inicializa tambien el constructor de 
// la clase madre 

class BaseCircular extends ObjetoQueRota

{
	Cil      cil_top;
	Cylinder cil_med;
	Cylinder cil_base; 
	
	private float rad_base=0.55f;
	private float high_base=0.09f;

	private float rad_med=0.5f;
	private float high_med=0.5f;

	private float rad_top_up=0.3f;
	private float rad_top_bot=0.5f;
	private float high_top=0.2f;
	
	private float scale=0.7f;
	
	final int id = v.BASE_CIRCULAR;
	
	TransformGroup tg_base;
	TransformGroup tg_med;
	TransformGroup tg_top;
	Transform3D    t3d_scale;
	
	
	
		BaseCircular(float valor_1, float valor_2,float valor_3, float valor_4,
								 float valor_5, float valor_6,float valor_7, String nombre)
	{
		
		
		
		
	rad_base=valor_1;
	high_base=valor_2;

	rad_med=valor_3;
	high_med=valor_4;

	rad_top_up=valor_5;
	rad_top_bot=valor_6;
	high_top=valor_7;
	
	this.nombre = nombre;
	

		
	crearBase();	
		
		
	}
	
	BaseCircular(Datos datos)
	{
		
		
		
		
	rad_base=datos.valor_1;
	high_base=datos.valor_2;

	rad_med=datos.valor_3;
	high_med=datos.valor_4;

	rad_top_up=datos.valor_5;
	rad_top_bot=datos.valor_6;
	high_top=datos.valor_7;
	
	this.nombre = datos.name;
	

		
	crearBase();	
		
		
	}
	
	
	
	
	BaseCircular()
	{
		
		
	crearBase();	
		
		
	}
	
	void crearBase()
	{
		
		//inicializar los componentes
		cil_top = new Cil(rad_top_bot,rad_top_up,high_top,apariencia);
		cil_med = new Cylinder(rad_med,high_med,apariencia);
		cil_base = new Cylinder(rad_base,high_base,apariencia);
		
		angulo_eje_rotacion.set(0.0f,1.0f,0.0f,0.0f);

		
		t3d = new Transform3D();
		t3d.set(new Vector3f(0.0f,high_base/2f,0.0f));		
	
		tg_base = new TransformGroup(t3d);
		tg_base.addChild(cil_base);
		tg_all.addChild(tg_base);
		
		t3d = new Transform3D();
		t3d.set(new Vector3f(0.0f,(high_base/2)+(high_med/2),0.0f));
		tg_med= new TransformGroup(t3d);
		tg_med.addChild(cil_med);
		tg_base.addChild(tg_med);
		
		t3d.set(new Vector3f(0.0f,(high_med/2)+(high_top/2),0.0f));
		tg_top= new TransformGroup(t3d);
		tg_top.addChild(tg_rotar);
		tg_rotar.addChild(cil_top);
		
		tg_med.addChild(tg_top);
	
		t3d.set(new Vector3f(0.0f,high_top/2,0.0f));
		tg_adherir.setTransform(t3d);
		tg_rotar.addChild( tg_adherir);
		
		addChild(tg_all);
		
		
		
	}
	
	
	
	
	public Datos getDatos()
	{
	
	this.nombre = nombre;
	Datos datos = new Datos(rad_base,high_base,rad_med,high_med,
	rad_top_up,rad_top_bot,high_top, id , nombre);	
	
	return datos;	
		
	}
			
}