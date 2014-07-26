import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;


class Joint7 extends ObjetoQueRota

{
	
	Wedge3   wed_left;
	Wedge3   wed_rite;
	Cylinder cil;
	Cylinder cil_left;
	Cylinder cil_rite;
	
	
	private float cil_left_rad=0.2f;
	private float cil_left_high=0.02f;
	
	private float cil_rite_rad=0.2f;
	private float cil_rite_high=0.02f;
	
	private float cil_rad=0.2f;
	private float cil_high=0.3f;
	
	private float wedge_left_x_lo;
	private float wedge_left_x_hi;
	private float wedge_left_y;
	private float wedge_left_z;
	private float wedge_left_offset;
	
	private float wedge_rite_x_lo;
	private float wedge_rite_x_hi;
	private float wedge_rite_y;
	private float wedge_rite_z;
	private float wedge_rite_offset;
	
	private float scale=1.0f;
	
	TransformGroup tg_cil;
	TransformGroup tg_we_left;
	TransformGroup tg_we_rite;
	TransformGroup tg_cil_left;
	TransformGroup tg_cil_rite;
	
	TransformGroup tg_rota_cil_left;
	TransformGroup tg_rota_cil_rite;
	TransformGroup tg_rota_cil_med;
	
	
	Transform3D t3d_tmp;
	
	int  valor_inicial_cil_med=0;
	int  valor_final_cil_med = 0;
	int  offset_cil_med  =45;
	
	int  valor_inicial_cil_left=0;
	int  valor_final_cil_left = 0;
	int  offset_cil_left  =45;

	int  valor_inicial_cil_rite=0;
	int  valor_final_cil_rite = 0;
	int  offset_cil_rite  =45;

	Hilo hilo_cil_med;
	Hilo hilo_cil_left;
	Hilo hilo_cil_rite;

	
	
	
	Joint7()
	{
		
	wedge_left_x_lo  = cil_left_rad*0.85f;
	wedge_left_x_hi  = cil_rad/4f;
	wedge_left_y     = cil_rad/2;
	wedge_left_z     = cil_left_rad/3f;
	wedge_left_offset= cil_left_rad/2f;
	
	wedge_rite_x_lo  = cil_rite_rad*0.85f;
	wedge_rite_x_hi  = cil_rad/4f;
	wedge_rite_y     = cil_rad/2;
	wedge_rite_z     = cil_rite_rad/3f;
	wedge_rite_offset= cil_rite_rad/2f;

	
	AxisAngle4f aa_cil_med;


	  	
	crearJoint();	
		
	}
	
	void crearJoint()
	{
	
	wed_left = new Wedge3(wedge_left_x_lo,wedge_left_x_hi,wedge_left_y,wedge_left_z,wedge_left_offset,apariencia);
	wed_rite = new Wedge3(wedge_rite_x_lo,wedge_rite_x_hi,wedge_rite_y,wedge_rite_z,wedge_rite_offset,apariencia);
	cil      = new Cylinder(cil_rad,cil_high,apariencia); 	
	cil_left = new Cylinder(cil_left_rad,cil_left_high,apariencia);
	cil_rite = new Cylinder(cil_rite_rad,cil_rite_high,apariencia);
	
	
	
	//cilindro izquierdo 
	
	tg_rota_cil_left = new TransformGroup();
	tg_rota_cil_left.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg_rota_cil_left.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

	
	v3f = new Vector3f(0.0f,cil_left_high/2,0.0f);
	t3d = new Transform3D();
	t3d.set(v3f);
	tg_cil_left = new TransformGroup(t3d);
	tg_cil_left.addChild(tg_rota_cil_left);
	tg_rota_cil_left.addChild(cil_left);
	tg_all.addChild(tg_cil_left);
	
	// wedge izquierdo
	
	
	v3f.set(0.0f,(cil_left_high/2)+wedge_left_y,0.0f);
	t3d.set(v3f);
	tg_we_left= new TransformGroup(t3d);
	tg_we_left.addChild(wed_left);
	tg_rota_cil_left.addChild(tg_we_left);
	
	// cilindro central
	
	tg_rota_cil_med = new TransformGroup();
	tg_rota_cil_med.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg_rota_cil_med.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

	v3f.set(cil_rad*0.8f,cil_rad*1.3f,0.0f);///
	t3d.set(v3f);
	t3d.setRotation(new AxisAngle4f(1.0f,0.0f,0.0f,(float)Math.toRadians(90)));
	tg_cil= new TransformGroup(t3d);
	tg_cil.addChild(tg_rota_cil_med);
	tg_rota_cil_med.addChild(cil);
	tg_we_left.addChild(tg_cil);
	
	
	//wedge rite
	v3f.set(-(cil_rad*0.8f),0.0f,-(cil_rad*1.3f));///
	t3d_tmp = new Transform3D();
	t3d_tmp.set(v3f);
	t3d_tmp.setRotation(new AxisAngle4f(1.0f,0.0f,0.0f,(float)Math.toRadians(90)));
	tg_we_rite= new TransformGroup(t3d_tmp);
	tg_we_rite.addChild(wed_rite);
	tg_rota_cil_med.addChild(tg_we_rite);

		//cilindro derecho
	
		
	tg_rota_cil_rite = new TransformGroup();
	tg_rota_cil_rite.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg_rota_cil_rite.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

	

	
	
	v3f.set(0.0f,-(cil_rite_high/2f+wedge_rite_y),0.0f);
	t3d_tmp.set(v3f);
	tg_cil_rite = new TransformGroup(t3d_tmp);
	tg_cil_rite.addChild(tg_rota_cil_rite);
	tg_rota_cil_rite.addChild(cil_rite);
	tg_we_rite.addChild(tg_cil_rite);
	
	// el proximo miembro que aparezca ser hijo de tg_rota_cil_rite 
	
	
	addChild(tg_all);
	
	}
	
	void rotar_cil_med()
	{
		
			AxisAngle4f aa_cil_med=new AxisAngle4f();
			
			aa_cil_med.set(0.0f,1.0f,0.0f,0.0f);
			
			valor_final_cil_med=valor_final_cil_med + offset_cil_med;
				
			hilo_cil_med = new Hilo (aa_cil_med,tg_rota_cil_med, valor_inicial_cil_med,valor_final_cil_med);

			hilo_cil_med.start();
				
			valor_inicial_cil_med=valor_inicial_cil_med+offset_cil_med;			
		
		
	}
	
	void rotar_cil_left()
	{
		
			AxisAngle4f aa_cil_left=new AxisAngle4f();
			
			aa_cil_left.set(0.0f,1.0f,0.0f,0.0f);
			
			valor_final_cil_left=valor_final_cil_left + offset_cil_left;
				
			hilo_cil_left = new Hilo (aa_cil_left,tg_rota_cil_left, valor_inicial_cil_left,valor_final_cil_left);

			hilo_cil_left.start();
				
			valor_inicial_cil_left=valor_inicial_cil_left+offset_cil_left;			
		
		
	}	
	
	
		void rotar_cil_rite()
	{
		
			AxisAngle4f aa_cil_rite=new AxisAngle4f();
			
			aa_cil_rite.set(0.0f,1.0f,0.0f,0.0f);
			
			valor_final_cil_rite=valor_final_cil_rite + offset_cil_rite;
				
			hilo_cil_rite = new Hilo (aa_cil_rite,tg_rota_cil_rite, valor_inicial_cil_rite,valor_final_cil_rite);

			hilo_cil_rite.start();
				
			valor_inicial_cil_rite=valor_inicial_cil_rite+offset_cil_rite;			
		
		
	}		
	
			void rotar_cil_rite(int valor)
	{
		
			AxisAngle4f aa_cil_rite=new AxisAngle4f();
			
			aa_cil_rite.set(0.0f,1.0f,0.0f,0.0f);
			
			offset_cil_rite=valor;
			
			valor_final_cil_rite=valor_final_cil_rite + offset_cil_rite;
				
			hilo_cil_rite = new Hilo (aa_cil_rite,tg_rota_cil_rite, valor_inicial_cil_rite,valor_final_cil_rite);

			hilo_cil_rite.start();
				
			valor_inicial_cil_rite=valor_inicial_cil_rite+offset_cil_rite;			
		
		
	}		
	
		void rotar_cil_left(int valor)
	{
		
			AxisAngle4f aa_cil_left=new AxisAngle4f();
			
			aa_cil_left.set(0.0f,1.0f,0.0f,0.0f);
			
			offset_cil_left=valor;
			
			valor_final_cil_left=valor_final_cil_left + offset_cil_left;
				
			hilo_cil_left = new Hilo (aa_cil_left,tg_rota_cil_left, valor_inicial_cil_left,valor_final_cil_left);

			hilo_cil_left.start();
				
			valor_inicial_cil_left=valor_inicial_cil_left+offset_cil_left;			
		
		
	}
	
	
	
}