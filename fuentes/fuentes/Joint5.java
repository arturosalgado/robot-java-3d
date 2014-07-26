import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

class Joint5 extends ObjetoQueRota
{
		
	Cylinder   hc_left;
	Cylinder   hc_rite;
	Box        box_left;
	Box        box_rite;
	Cylinder   cil;
	Box        base;
	
	private    float dim_base_x=1.2f;
	
	private    float y_total=1.60f;
	
	private    float dim_base_z=0.8f;


	private    float dim_y=y_total*0.8f;
	private    float dim_base_y=y_total*0.2f;
	private    float dim_x=dim_base_x;
	private    float dim_z=dim_base_z*0.15f;


	
	
	private    float high_cil=(dim_base_z*2)-(dim_z*2);
	private    float rad_cil=dim_base_x*1.5f;
	
	private    float rad_hc=dim_x*1.6f;
	private    float high_hc=dim_z*2;

	TransformGroup tg_base;
	TransformGroup tg_hc_left;
	TransformGroup tg_hc_rite;
	TransformGroup tg_box_left;
	TransformGroup tg_box_rite;
	TransformGroup tg_rotar_cil;
	
	
	final int id = v.JOINT_5;
	
	
		
	Joint5()
	{
	
	crearJoint();	
		
		
	}
		
	Joint5(float valor_1, float valor_2, float valor_3, String nombre)
	{
	
	dim_base_x=valor_1/2;
	y_total   =valor_2/2;

	dim_base_z=valor_3/2;
	
	

	
	
	
	dim_x=dim_base_x;
	dim_z=dim_base_z*0.15f;
		rad_hc=dim_x*1.6f;
	high_hc=dim_z*2;

	
	dim_y=y_total*0.8f;
	dim_base_y=y_total*0.2f;

	high_cil=(dim_base_z*2)-(dim_z*2);
	rad_cil=dim_base_x*1.5f;
	

	
	
	
	
	this.nombre = nombre ;
	

	
	
	
	crearJoint();	
		
		
	}
	
		Joint5(Datos datos)
	{
	
	dim_base_x=datos.valor_1/2;
	y_total   =datos.valor_2/2;

	dim_base_z=datos.valor_3/2;
	
	

	
	
	
	dim_x=dim_base_x;
	dim_z=dim_base_z*0.15f;
		rad_hc=dim_x*1.6f;
	high_hc=dim_z*2;

	
	dim_y=y_total*0.8f;
	dim_base_y=y_total*0.2f;

	high_cil=(dim_base_z*2)-(dim_z*2);
	rad_cil=dim_base_x*1.5f;
	

	
	
	
	
	this.nombre = datos.name ;
	

	
	
	
	crearJoint();	
		
		
	}
	
	
	
	void crearJoint()
	{
		//inicializa las variables
		base =new Box(dim_base_x,dim_base_y,dim_base_z,apariencia);
		box_left =new Box(dim_x,dim_y,dim_z,apariencia);
		box_rite =new Box(dim_x,dim_y,dim_z,apariencia);
		cil = new Cylinder(rad_cil,high_cil,apariencia);
		hc_left = new Cylinder(rad_hc,high_hc,apariencia);
		hc_rite = new Cylinder(rad_hc,high_hc,apariencia);
		
	
		// tg_all, raiz escala todo 
		// a la raiz anade directamente la base
		
		v3f.set(0.0f,dim_base_y,0.0f);
		t3d.set(v3f);
		tg_base=new TransformGroup(t3d);
		tg_base.addChild(base);
		
		
		tg_all.addChild(tg_base);
		
		// transforma la orientacion de la caja lateral izq
		// la anade a tg_all 
		t3d.set(new Vector3f(0.0f,dim_y+dim_base_y,dim_base_z-dim_z));
		tg_box_left= new TransformGroup(t3d);
		tg_box_left.addChild(box_left);
		tg_all.addChild(tg_box_left);
		
		//anade la caja lateral derecha a tg_all es decir a la raiz
		t3d.set(new Vector3f(0.0f,dim_y+dim_base_y,-dim_base_z+dim_z));
		tg_box_rite= new TransformGroup(t3d);
		tg_box_rite.addChild(box_rite);
		tg_all.addChild(tg_box_rite);

		//anade medio circulo izq
		//anade el circulo a la transformacion de la caja
		t3d.set(new Vector3f(0.0f,dim_y,0.0f));
		t3d.setRotation(new AxisAngle4f(1.0f,0.0f,0.0f,-(float)Math.toRadians(90)));
		tg_hc_left= new TransformGroup(t3d);
		tg_hc_left.addChild(hc_left);
		tg_box_left.addChild(tg_hc_left);

		// anade medio circulo der
		// anade el circulo a la transformacion de la caja
		t3d.set(new Vector3f(0.0f,dim_y,0.0f));
		t3d.setRotation(new AxisAngle4f(1.0f,0.0f,0.0f,-(float)Math.toRadians(90)));
		tg_hc_rite= new TransformGroup(t3d);
		tg_hc_rite.addChild(hc_rite);
		tg_box_rite.addChild(tg_hc_rite);
		
		//cilindro rotor se anade directamente a la raiz;
		
		
		
		angulo_eje_rotacion.set(0.0f,1.0f,0.0f,0.0f);
		t3d.set(new Vector3f(0.0f,(dim_y*2)+dim_base_y,0.0f));
		t3d.setRotation(new AxisAngle4f(1.0f,0.0f,0.0f,-(float)Math.toRadians(90)));
		tg_rotar_cil  = new TransformGroup(t3d);
		tg_rotar_cil.addChild(tg_rotar);
		tg_rotar.addChild(cil);
		
		tg_all.addChild(tg_rotar_cil);
		
		v3f.set(0.0f,0.0f,rad_cil);
		t3d.set(v3f);
		t3d.setRotation(new AxisAngle4f(1.0f,0.0f,0.0f,(float)Math.toRadians(90)));

		tg_adherir.setTransform(t3d);
		
		tg_rotar.addChild(tg_adherir);
		


		addChild(tg_all);
	}
	
	
	public Datos getDatos()
	{
	
	

	Datos datos = new Datos (dim_base_x*2, y_total*2,dim_base_z*2, id , nombre);	
		
		return datos;
		
	}
	
	
	
	
}