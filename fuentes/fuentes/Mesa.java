import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;



class Mesa extends ObjetoFisico
{
	
	Box box_lo_left;
	Box box_lo_rite;
	Box box_hi_left;
	Box box_hi_rite;
	Box box_table;
	
	private float largo_x=1.5f;
	private float alto_y=1.0f;
	private float ancho_z=1.22f;
	
	private float dim_x_feet;
	private float dim_y_feet;
	private float dim_z_feet;
	
	private float dim_x_table;
	private float dim_y_table;
	private float dim_z_table;
	
	TransformGroup tg_box_lo_left;
	TransformGroup tg_box_lo_rite;
	TransformGroup tg_box_hi_left;
	TransformGroup tg_box_hi_rite;
	TransformGroup tg_box_table;

	final int id = v.MESA;
	
	
	Appearance app;
	
	Color3f azul,blanco;
	
	Mesa(float x, float y, float z)
	{


	azul = new Color3f(0.0f,0.0f,1.0f);
	blanco = new Color3f(0.5f,0.5f,0.5f);
	app = new Appearance();
	Material mat = new Material(azul,blanco,azul,azul,64.0f);
	app.setMaterial(mat);

	
	this.nombre = "mesa";
	largo_x=x;
	alto_y=y;
	ancho_z=z;
	
	largo_x=largo_x/2f;
	alto_y = alto_y/2f;
	ancho_z = ancho_z/2f;
	

	
	
	
	dim_x_feet = largo_x*0.03f;
	dim_y_feet = alto_y*1.0f;
	dim_z_feet = largo_x*0.03f;
		
	dim_x_table=largo_x;
	dim_y_table=alto_y*0.05f;
	dim_z_table=ancho_z;
		
	crearMesa();
		
		
	}
	
	
		Mesa(Datos datos)
	{


		azul = new Color3f(0.0f,0.0f,1.0f);
		blanco = new Color3f(0.5f,0.5f,0.5f);
		app = new Appearance();
		Material mat = new Material(azul,blanco,azul,azul,64.0f);
		app.setMaterial(mat);
	
	
	this.nombre = "mesa";
	largo_x=datos.x;
	alto_y=datos.y;
	ancho_z=datos.z;
	
	largo_x=largo_x/2f;
	alto_y = alto_y/2f;
	ancho_z = ancho_z/2f;
	

	
	
	
	dim_x_feet = largo_x*0.03f;
	dim_y_feet = alto_y*1.0f;
	dim_z_feet = largo_x*0.03f;
		
	dim_x_table=largo_x;
	dim_y_table=alto_y*0.05f;
	dim_z_table=ancho_z;
	
	crearMesa();
		
		
	}
	
	Mesa()
	{
		
		azul = new Color3f(0.0f,0.0f,1.0f);
		blanco = new Color3f(0.5f,0.5f,0.5f);
		app = new Appearance();
		Material mat = new Material(azul,blanco,azul,azul,64.0f);
		app.setMaterial(mat);
		
		largo_x=largo_x/2f;
		alto_y = alto_y/2f;
		ancho_z = ancho_z/2f;
		
		dim_x_feet = largo_x*0.03f;
		dim_y_feet = alto_y*1.0f;
		dim_z_feet = largo_x*0.03f;
		
		dim_x_table=largo_x;
		dim_y_table=alto_y*0.05f;
		dim_z_table=ancho_z;
		
		
		
		crearMesa();
		
		
	}
	
	
	public void crearMesa()
	{
		
		
		
		box_lo_left = new Box(dim_x_feet,dim_y_feet,dim_z_feet,app);
		box_lo_rite = new Box(dim_x_feet,dim_y_feet,dim_z_feet,app);
		box_hi_left = new Box(dim_x_feet,dim_y_feet,dim_z_feet,app);
		box_hi_rite = new Box(dim_x_feet,dim_y_feet,dim_z_feet,app);
	
		box_table		= new Box(dim_x_table,dim_y_table, dim_z_table,app);
		
		v3f.set(0.0f,(alto_y*2)-dim_y_table,0.0f);
		t3d.set(v3f);
		tg_box_table = new TransformGroup(t3d);
	  tg_box_table.addChild(box_table);
		
		//abajo izquierdo
		
		v3f.set(-largo_x+dim_x_feet,-dim_y_feet+dim_y_table,ancho_z-dim_z_feet);
		t3d.set(v3f);
		tg_box_lo_left = new TransformGroup(t3d);
		tg_box_lo_left.addChild(box_lo_left);
		tg_box_table.addChild(tg_box_lo_left);
		
		
		// abajo derecho 
		
		v3f.set(largo_x-dim_x_feet,-dim_y_feet+dim_y_table,ancho_z-dim_z_feet);
		t3d.set(v3f);
		tg_box_lo_rite = new TransformGroup(t3d);
		tg_box_lo_rite.addChild(box_lo_rite);
		tg_box_table.addChild(tg_box_lo_rite);
		
		// arriba izq
		
		v3f.set(-largo_x+dim_x_feet,-dim_y_feet+dim_y_table,-ancho_z+dim_z_feet);
		t3d.set(v3f);
		tg_box_hi_left = new TransformGroup(t3d);
		tg_box_hi_left.addChild(box_hi_left);
		tg_box_table.addChild(tg_box_hi_left);
		
		// arriba der
		
		v3f.set(largo_x-dim_x_feet,-dim_y_feet+dim_y_table,-ancho_z+dim_z_feet);
		t3d.set(v3f);
		tg_box_hi_rite = new TransformGroup(t3d);
		tg_box_hi_rite.addChild(box_hi_rite);
		tg_box_table.addChild(tg_box_hi_rite);
		
		
		
		
		tg_all.addChild(tg_box_table);
		
	// prepara pa el siguiente elemento
		
		v3f.set(0.0f,alto_y*2,0.0f);
		t3d.set(v3f);
		tg_adherir.setTransform(t3d);
		tg_all.addChild(tg_adherir);
		
		
	}
	
	public Datos getDatos()
	{
		
	Datos datos = new Datos(largo_x*2,alto_y*2,ancho_z*2,id,nombre);	
	
	return datos;
		
		
		
		
	}
	
	
	
	
}