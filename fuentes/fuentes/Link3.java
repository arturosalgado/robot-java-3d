import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;


class Link3 extends ObjetoFisico

{
	
	HalfCircle hc;
	Wedge3     we;
	
	
	
	private float rad=0.2f;
	private float x_lo=0.25f;
	private float x_hi;
	private float offset;
	
	private float y=1.5f;
	private float z=0.2f;
	
	private float scale=1.0f;
	
	private float hi;//grosor del circulo 

	TransformGroup tg_we;
	TransformGroup tg_hc;

	
	Link3()
	{
		
	x_hi=rad*2;
	hi=z*2; 	
	offset= (x_lo-x_hi)/2f;
	crearLink();	
				
		
	}
	
	
		Link3(float valor_1, float valor_2, float valor_3, float valor_4, String nombre)
	{
	
	
	
	
	
	
	rad=valor_1;
	y=valor_2;
	x_lo =valor_3;
	z=valor_4;
	
	rad=rad/2f;
	y=(y/2)-rad;
	z=z/2;
	x_lo=x_lo/2;
	this.nombre = nombre;

	
	
		
	x_hi=rad*2;
	hi=z*2; 	
	offset= (x_lo-x_hi)/2f;



	crearLink();	
				
		
	}
	
			Link3(Datos datos	)
	{
	
	
	rad=datos.valor_1;
	y=datos.valor_2;
	x_lo = datos.valor_3;
	z= datos.valor_4;
	this.nombre = datos.name;





	x_hi=rad*2;
	hi=z*2; 	
	offset= (x_lo-x_hi)/2f;
	crearLink();	
				
		
	}
	
	
	public void crearLink()
	{
		
		
	id = v.LINK_3;

	hc = new HalfCircle(rad*2,hi,apariencia);
	we = new Wedge3(x_lo,x_hi,y,z,offset,apariencia);
	
	
	t3d= new Transform3D();
	v3f = new Vector3f(0.0f,-(y+rad*2),0.0f);
	
	t3d.set(v3f);
	t3d.setRotation(new AxisAngle4f(0.0f,1.0f,0.0f,(float)Math.toRadians(90)));
	tg_we = new TransformGroup(t3d); 
	tg_we.addChild(we);
	tg_all.addChild(tg_we);
	
	
	
	v3f.set(0.0f,y,0.0f);
	t3d.set(v3f);
	t3d.setRotation(new AxisAngle4f(1.0f,0.0f,0.0f,-(float)Math.toRadians(90)));
	tg_hc= new TransformGroup(t3d);
	tg_hc.addChild(hc);
	tg_we.addChild(tg_hc);


	t3d.rotZ(Math.toRadians(180));
	tg_all.setTransform(t3d);
	
	
	addChild(tg_all);
	
	
	v3f.set(0.0f,(y*2)+rad*2,0.0f);
	t3d.set(v3f);
	tg_adherir.setTransform(t3d);
	addChild(tg_adherir);
	
	
	
	
		
		
	}

			public Datos getDatos()
		{

		Datos datos = new Datos(rad, y, x_lo , z, id, nombre);	
			
		return datos;	
			
		}		
}