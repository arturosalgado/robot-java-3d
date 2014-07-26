//de los angeles salgado jose arturo 
//editor simulador de robots orientado a objetos
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;



class Hand2 extends ObjetoQueRota
{
	
	Cylinder cil;
	Box      box_left;
	Box      box_rite;

	private float dim_x_box;
	private float dim_y_box;
	private float dim_z_box;

	private float cil_rad=0.3f;
	private float cil_high=0.6f;

	
	TransformGroup tg_cil;
	TransformGroup tg_box_left;
	TransformGroup tg_box_rite;
	TransformGroup tg_box_left_t;
	TransformGroup tg_box_rite_t;
	
	
	
	
	Hilo_Extend2 hilo_rite;
	Hilo_Extend2 hilo_left;
	
	
	int  valor_inicial_l=0;
	int  valor_final_l = 0;
	int  offset_l  =0;
  boolean flag=true;
	int  valor_inicial_r=0;
	int  valor_final_r = 0;
	int  offset_r  =0;

	final int id = v.HAND_2;
	
		Hand2()
	{
		dim_x_box=cil_rad*0.09f;
		dim_y_box=cil_high/2f;
		dim_z_box=cil_rad/2f;
		crearHand2();
		
	}

	Hand2(float valor_1, float valor_2, String nombre)
	{
		
		cil_rad=valor_1;
		cil_high=valor_2;
		this.nombre = nombre;
		
		dim_x_box=cil_rad*0.09f;
		dim_y_box=cil_high/2f;
		dim_z_box=cil_rad/2f;
		crearHand2();
		
	}
	
	
	Hand2(Datos datos)
	{
		
		cil_rad=datos.valor_1;
		cil_high=datos.valor_2;
		this.nombre = datos.name;
		
		dim_x_box=cil_rad*0.09f;
		dim_y_box=cil_high/2f;
		dim_z_box=cil_rad/2f;
		crearHand2();
		
	}


	public void crearHand2()

	{
		
		Box box_left = new Box(dim_x_box,dim_y_box,dim_z_box,apariencia);
		Box box_rite = new Box(dim_x_box,dim_y_box,dim_z_box,apariencia);
		Cylinder cil = new Cylinder (cil_rad,cil_high,apariencia);
		
		
		angulo_eje_rotacion.set(0.0f,1.0f,0.0f,0.0f);
		v3f.set(0.0f,cil_high/2f,0.0f);
		t3d.set(v3f);
		tg_cil=new TransformGroup(t3d);
		tg_cil.addChild(tg_rotar);
		tg_rotar.addChild(cil);
		
		
		//pinza 
		tg_box_left_t = new TransformGroup();
		tg_box_left_t.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg_box_left_t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		
		v3f.set(-cil_rad/1f+dim_x_box*2.6f,cil_high/2+dim_y_box,0.0f);
		t3d.set(v3f);
		tg_box_left = new TransformGroup(t3d);
		
		
		tg_box_left.addChild(tg_box_left_t);
		tg_box_left_t.addChild(box_left);
		tg_rotar.addChild(tg_box_left);
		
		
		
		tg_box_rite_t = new TransformGroup();
		tg_box_rite_t.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg_box_rite_t.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		
		
		v3f.set(cil_rad/1f-dim_x_box*2.6f,cil_high/2+dim_y_box,0.0f);
		t3d.set(v3f);
		tg_box_rite = new TransformGroup(t3d);
		tg_box_rite.addChild(tg_box_rite_t);
		tg_box_rite_t.addChild(box_rite);
		tg_rotar.addChild(tg_box_rite);
		
		v3f.set(0.0f,cil_high/2,0.0f);
		t3d.set(v3f);
		tg_adherir.setTransform(t3d);
		tg_rotar.addChild(tg_adherir);
		
		
		
		
		tg_all.addChild(tg_cil);
		addChild(tg_all);
		
	}
	
	
	public void cerrar(int valor)
	{
		
		
		
		offset_l = valor;
		offset_r = valor;
		valor_final_l=valor_final_l + offset_l;
		valor_final_r=valor_final_r - offset_r;	
		

		hilo_left = new Hilo_Extend2 (true, tg_box_left_t,  valor_inicial_l,valor_final_l);
		hilo_rite = new Hilo_Extend2 (false,tg_box_rite_t, valor_final_r,valor_inicial_r);
	
	
	
		hilo_rite.start();
		hilo_left.start();
		
		valor_inicial_l=valor_inicial_l+offset_l;
		valor_inicial_r=valor_inicial_r-offset_r;
		
			
		
	}
	
	public void abrir(int valor)
	
	{
		
		offset_l = valor;
		offset_r = valor;
		valor_final_l=valor_final_l - offset_l;
		valor_final_r=valor_final_r + offset_r;	
		

		hilo_left = new Hilo_Extend2 (false,tg_box_left_t,  valor_final_l,valor_inicial_l);
		
		hilo_rite = new Hilo_Extend2 (true,tg_box_rite_t, valor_inicial_r,valor_final_r);
	
	
	  
	
		hilo_rite.start();
		hilo_left.start();
		
		valor_inicial_l=valor_inicial_l-offset_l;
		valor_inicial_r=valor_inicial_r+offset_r;
		
		
	}
	
	
	public void sujetar()
	{
		Transform3D t3d2= new Transform3D();
		Vector3f v3f2 = new Vector3f();
		//if (cubo == null)
		{// haz nada
		}
	//	else
		{
		
		 tg_all.getTransform(t3d2);
		 t3d2.get(v3f2);
		 
		 System.out.println(v3f2);
			
		}
		
		
	}
	
	
	public Datos getDatos ()
	{
		
	Datos datos = new Datos(cil_rad,cil_high,id,nombre);	
		
		return datos;
		
	}
	
	
	
}