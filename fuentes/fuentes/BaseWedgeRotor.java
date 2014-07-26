import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;

class  BaseWedgeRotor extends ObjetoQueRota
{

	Wedge3   base;
	Wedge3   rite;
	Wedge3   left;
	Cylinder cil;
	
	private float x_lo_base=0.5f;
	private float x_hi_base=0.5f;
	private float y_base=0.2f;
	private float z_base=0.5f;
	private float offset_base=0.0f;
	
	private float x_lo_rite;
	private float x_hi_rite=0.3f;
	private float y_rite=0.3f;
	private float z_rite;
	private float offset_rite;
	
	
	private float x_lo_left;
	private float x_hi_left=0.3f;
	private float y_left=0.3f;
	private float z_left;
	private float offset_left;
	
	
	private float cil_rad;
	private float cil_high;
	
	private float scale=1.0f;
	
	

	private float x_hi;
	private float x_lo;
	private float y;
	private float y_b;
	
	private float z;
	private float off;
	
	
  final int  id = v.BASE_WEDGE_ROTOR;



	
	TransformGroup tg_base;
	TransformGroup tg_left;
	TransformGroup tg_rite;
	TransformGroup tg_cil;
	

	
	
	
	BaseWedgeRotor()
	{
		
		x_lo_left=x_hi_base;
		x_lo_rite=x_hi_base;
		
		z_rite=z_base/3f;
		z_left=z_base/3f;
		
		
		cil_high = (z_base/3f)*2f;
		
		cil_rad =   y_left;
		
		offset_rite=(x_lo_rite-x_hi_rite)/2f;
		offset_left=(x_lo_left-x_hi_left)/2f;

		
		crearBase();
		
		
	}
	
	
BaseWedgeRotor(float x_hi, float x_lo,float y,float y_b, float z, float off,
									 String nom)
	{
		
	
	this.x_hi=x_hi;
	this.x_lo=x_lo;
	
	
	this.y=y;
	this.y_b=y_b;
	
	this.z=z;
	this.off=off;
	
	
	this.nombre = nom;
	
	
		
	x_hi=x_hi/2;
	x_lo=x_lo/2;
	
	
	y=y/2;
	y_b=y_b/2;
	
	z=z/2;
	off=off/2;
	

	
		
		
		
	x_lo_base=x_lo;
	x_hi_base=x_lo;
	y_base=y_b;
	z_base=z;
	offset_base=0.0f;
	
	
	
	x_lo_rite=x_hi_base;
	x_hi_rite=x_hi;
	y_rite=y;
	z_rite=z_base/3f;
		
	offset_rite=off;
	
	
	x_lo_left=x_hi_base;
	x_hi_left=x_hi;
	y_left=y;
	z_left=z_base/3f;
	offset_left=off;

	//--proporciones 	
		
		
		
		
		cil_high = (z_base/3f)*2f;
		
		cil_rad =   y_left;
		
		
		crearBase();
		
		
	}
	
	
	
BaseWedgeRotor(Datos datos)
	{
		


	this.x_hi = datos.valor_1;
	this.x_lo= datos.valor_2;
	this.y  = datos.valor_3;
	this.y_b=datos.valor_4;
	this.z = datos.valor_5;
	this.off = datos.valor_6;
	this.nombre= datos.name;
	

		x_hi=x_hi/4;
	x_lo=x_lo/4;
	
	
	y=y/4;
	y_b=y_b/4;
	
	z=z/4;
	off=off/4;
	


	
	
	
	
		
		

	
	
		
		
		
	x_lo_base=x_lo;
	x_hi_base=x_lo;
	y_base=y_b;
	z_base=z;
	offset_base=0.0f;
	
	
	
	x_lo_rite=x_hi_base;
	x_hi_rite=x_hi;
	y_rite=y;
	z_rite=z_base/3f;
		
	offset_rite=off;
	
	
	x_lo_left=x_hi_base;
	x_hi_left=x_hi;
	y_left=y;
	z_left=z_base/3f;
	offset_left=off;

	//--proporciones 	
		
		
		
		
		cil_high = (z_base/3f)*2f;
		
		cil_rad =   y_left;
		
		
		crearBase();
		
		
	}
	
	
	
	
	void crearBase()
	{
		
		base =new Wedge3(x_lo_base,x_hi_base,y_base,z_base,offset_base,apariencia);
		rite =new Wedge3(x_lo_rite,x_hi_rite,y_rite,z_rite,offset_rite,apariencia);
		left =new Wedge3(x_lo_left,x_hi_left,y_left,z_left,offset_left,apariencia);
		cil  =new Cylinder(cil_rad,cil_high,apariencia);	
		
		
//	t3d_scale=new Transform3D();
	//	t3d_scale.set(scale);
		tg_all= new TransformGroup(/*t3d_scale*/);
		
		// base
		v3f = new Vector3f(0.0f,y_base,0.0f);
		t3d = new Transform3D();
		t3d.set(v3f);
		tg_base = new TransformGroup(t3d);
		tg_base.addChild(base);
		
		// left 
		
		v3f.set(0.0f,y_base+y_left,z_base-z_left);
		t3d.set(v3f);
		tg_left = new TransformGroup(t3d);
		tg_left.addChild(left); 
		tg_base.addChild(tg_left);
		
		// rite
		
		v3f.set(0.0f,y_base+y_rite,-(z_base-z_rite));
		t3d.set(v3f);
		tg_rite = new TransformGroup(t3d);
		tg_rite.addChild(rite); 
		tg_base.addChild(tg_rite);

		// rotor
		
		v3f.set(0.0f,y_base+(cil_high/2f),0.0f);
		t3d.set(v3f);
		t3d.setRotation(new AxisAngle4f(1.0f,0.0f,0.0f,(float)Math.toRadians(90)));
		tg_cil = new TransformGroup(t3d);
		tg_rotar.addChild(cil);
		
		
		v3f.set(0.0f,cil_rad,0.0f);
		t3d.set(v3f);
		t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(90)));
		TransformGroup tg_temp= new TransformGroup();
		t3d.rotX(Math.toRadians(-90.0));
		tg_temp.setTransform(t3d);
		
		
		tg_temp.addChild(tg_adherir);
		
		tg_adherir.setTransform(t3d);
		
		
		tg_rotar.addChild(tg_temp);
		
		
		tg_cil.addChild(tg_rotar); 

		tg_base.addChild(tg_cil);
		
		
		
		
		tg_all.addChild(tg_base);
		
		
		//adherir all rotor
		v3f.set(0.0f,cil_rad,0.0f);
		t3d.set(v3f);
		tg_adherir.setTransform(t3d);
		
		
		addChild(tg_all);
		
		angulo_eje_rotacion.set(0.0f,1.0f,0.0f,0.0f);
		
		
	}
	
	public Datos getDatos()
	{
		
			

	
	Datos datos = new Datos(x_hi*2,x_lo*2,y*2,y_b*2,z*2,off*2,id,nombre);
		
		return datos;
		
		
	}
	
	
}
