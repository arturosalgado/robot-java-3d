import javax.media.j3d.*;
import javax.vecmath.*;


class  BaseWedge extends ObjetoFisico

{
	float width_lo=2.0f;
	float width_hi=0.01f;
	float depth = 1.0f;
	float high  = 2.0f;
	float offset =2.0f;
	
	float x_left_lo=-width_lo/2;
	float x_rite_lo=width_lo/2;
	
	float x_left_hi=x_left_lo;
	float x_rite_hi=x_left_lo+width_hi;

	float z_front=  depth/2;
	float z_back = -depth/2;
	
	float y_top= high/2;
	float y_bot=-high/2;
	
	float scale = 2.0f;

 	int id = v.BASE_WEDGE;

 
	BaseWedge()
	{
		
		crearWedge();
		
	}

	
	BaseWedge(float new_width_hi, float new_width_lo,float new_high,float new_depth,float new_offset,String name)
	{
		width_lo=new_width_lo;
		width_hi=new_width_hi;
		depth = new_depth;
		high  = new_high;
		offset =new_offset;
	
	
		x_left_lo=-width_lo/2;
		x_rite_lo=width_lo/2;
		
		x_left_hi=x_left_lo;
		x_rite_hi=x_left_lo+width_hi;
	
		z_front=  depth/2;
		z_back = -depth/2;
		
		y_top= high/2;
		y_bot=-high/2;

		this.nombre = name;
		

		crearWedge();
		
	}







		BaseWedge(Datos datos)
	{
	
	
	
		width_hi=datos.valor_1;
		width_lo=datos.valor_2;
		depth = datos.valor_3;
		high  = datos.valor_4;
		offset =datos.valor_5;
	
		this.nombre = datos.name;

		
		
		
		
		
		
		x_left_lo=-width_lo/2;
		x_rite_lo=width_lo/2;
		
		x_left_hi=x_left_lo;
		x_rite_hi=x_left_lo+width_hi;
	
		z_front=  depth/2;
		z_back = -depth/2;
		
		y_top= high/2;
		y_bot=-high/2;

		

		crearWedge();
		
	}

	
	
	
	
	void crearWedge()
	{
		
		int [] strips = {4,4,4,4,4,4};
		IndexedTriangleFanArray itfa=new 
		IndexedTriangleFanArray(8,GeometryArray.COORDINATES|
		GeometryArray.NORMALS,24,strips);
				
				
																									
		itfa.setCoordinate( 0, new Point3f(x_left_lo, y_bot, z_front));
		itfa.setCoordinate( 1, new Point3f(x_rite_lo, y_bot, z_front));
		itfa.setCoordinate( 2, new Point3f(x_rite_hi+offset, y_top,  z_front));
		itfa.setCoordinate( 3, new Point3f(x_left_hi+offset, y_top,  z_front));
		
		itfa.setCoordinate( 4, new Point3f(x_left_lo, y_bot, z_back));
		itfa.setCoordinate( 5, new Point3f(x_rite_lo, y_bot, z_back));
		itfa.setCoordinate( 6, new Point3f(x_rite_hi+offset, y_top,  z_back));
		itfa.setCoordinate( 7, new Point3f(x_left_hi+offset, y_top,  z_back));
	
		itfa.setCoordinateIndex(0,0);
		itfa.setCoordinateIndex(1,1);
		itfa.setCoordinateIndex(2,2);
		itfa.setCoordinateIndex(3,3);
		
		itfa.setCoordinateIndex(4,3);
		itfa.setCoordinateIndex(5,2);
		itfa.setCoordinateIndex(6,6);
		itfa.setCoordinateIndex(7,7);
		
		itfa.setCoordinateIndex(8,5);
		itfa.setCoordinateIndex(9,4);
		itfa.setCoordinateIndex(10,7);
		itfa.setCoordinateIndex(11,6);
		
		itfa.setCoordinateIndex(12,0);
		itfa.setCoordinateIndex(13,4);
		itfa.setCoordinateIndex(14,5);
		itfa.setCoordinateIndex(15,1);


		itfa.setCoordinateIndex(16,1);
		itfa.setCoordinateIndex(17,5);
		itfa.setCoordinateIndex(18,6);
		itfa.setCoordinateIndex(19,2);


		itfa.setCoordinateIndex(20,0);
		itfa.setCoordinateIndex(21,3);
		itfa.setCoordinateIndex(22,7);
		itfa.setCoordinateIndex(23,4);

		
		
		itfa.setNormal( 0, new Vector3f(0.0f, 0.0f,  1.0f));//z
		itfa.setNormal( 1, new Vector3f(0.0f, 1.0f,  0.0f));//y
		itfa.setNormal( 2, new Vector3f(0.0f, 0.0f, -1.0f));//-z
		itfa.setNormal( 3, new Vector3f(0.0f,-1.0f,  1.0f));//-y
		itfa.setNormal( 4, new Vector3f(1.0f,0.0f,  0.0f));//x
		itfa.setNormal( 5, new Vector3f(-1.0f,0.0f,  0.0f));//-x
		
		
		
		
		itfa.setNormalIndex( 0, 0);
		itfa.setNormalIndex( 1, 0);
		itfa.setNormalIndex( 2, 0);
		itfa.setNormalIndex( 3, 0);
		
		itfa.setNormalIndex( 4, 1);
		itfa.setNormalIndex( 5, 1);
		itfa.setNormalIndex( 6, 1);
		itfa.setNormalIndex( 7, 1);
		
		itfa.setNormalIndex( 8, 2);
		itfa.setNormalIndex( 9, 2);
		itfa.setNormalIndex( 10, 2);
		itfa.setNormalIndex( 11, 2);
		
		itfa.setNormalIndex( 12, 3);
		itfa.setNormalIndex( 13, 3);
		itfa.setNormalIndex( 14, 3);
		itfa.setNormalIndex( 15, 3);
		
		itfa.setNormalIndex( 16, 4);
		itfa.setNormalIndex( 17, 4);
		itfa.setNormalIndex( 18, 4);
		itfa.setNormalIndex( 19, 4);
		
		itfa.setNormalIndex( 20, 5);
		itfa.setNormalIndex( 21, 5);
		itfa.setNormalIndex( 22, 5);
		itfa.setNormalIndex( 23, 5);

		Shape3D shape = new Shape3D(itfa,apariencia);
		
		t3d   = new Transform3D();
		t3d.set(new Vector3f(0.0f,high/2,0.0f));
		tg_all= new TransformGroup(t3d);
		tg_all.addChild(shape);
		
		t3d.set(new Vector3f(0.0f,high/2,0.0f));
		tg_adherir.setTransform(t3d);
		
		
		tg_all.addChild(tg_adherir);
		
		
		addChild(tg_all);
		
		
		
		
		
		
	}
	
	
	public Datos getDatos()
	
	{
		
	Datos datos = new Datos (width_hi,width_lo,high,depth,offset,id,nombre);
		
		
	return datos;	
	}

}		