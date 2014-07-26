import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;

class  BaseWedgeRotor extends ObjetoFisico
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
	
	
	TransformGroup tg_base;
	TransformGroup tg_left;
	TransformGroup tg_rite;
	TransformGroup tg_cil;
	TransformGroup tg_rotar;
	
	Hilo hilo;
	int  valor_inicial=0;
	int  valor_final = 0;
	int  offset  =45;
	AxisAngle4f angulo_eje_rotacion;

	
	
	
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
		tg_rotar  = new TransformGroup();
		tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg_rotar.addChild(cil);
		tg_cil.addChild(tg_rotar); 

		tg_base.addChild(tg_cil);
		
		
		
		
		tg_all.addChild(tg_base);
		addChild(tg_all);
		
	}
	
				public void rotar(int valor)		
		{

			offset=valor;
			
			angulo_eje_rotacion= new AxisAngle4f(0.0f,1.0f,0.0f,0.0f);

			valor_final=valor_final + offset;
				
			
			hilo = new Hilo (angulo_eje_rotacion, tg_rotar, valor_inicial,valor_final);

			hilo.start();
			
			
			valor_inicial=valor_inicial+offset;			
			
		
		}
	
	
}
