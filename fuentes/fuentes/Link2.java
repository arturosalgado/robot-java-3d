import javax.media.j3d.*;
import javax.vecmath.*;

public class Link2 extends ObjetoFisico

{
  private final float lunit = 100;

  private float len = 7.0f;    
  private float grosor = 1.0f;
  private float rad_izq = 1.0f;
  private float rad_der = 0.6f;

  private int segments = 8; 

  private Shape3D arm_frente;
  private Shape3D arm_lado;
  private Shape3D arm_tras;
  private int numvertex;
 
  private int [] stripsofside = new int[1];
  private int [] stripsoffront = new int[2];
  private int [] stripsofrear = new int[2];
 

 
  Vector3f frontnormal = new Vector3f(0.0f, 0.0f, 1.0f);
  Vector3f rearnormal = new Vector3f(0.0f, 0.0f, -1.0f);
  Vector3f sidenormal = new Vector3f(1.0f, 0.0f, 0.0f);

  Point3f coord = new Point3f();
 	
 	TransformGroup tg_rot;

 	Transform3D    t3d =new Transform3D();
 	Transform3D    t3d1=new Transform3D();

 	AxisAngle4f    aa;
 	AxisAngle4f    aa1;

 	

  public Link2()
  
 {  
 		
 		
 		    CreaLink();
    
 
 }

 	
  public Link2(float valor_1,float valor_2,float valor_3, float valor_4, String nombre)
  
 {  
 
 
 		
 		rad_izq = valor_1;
 		rad_der = valor_2;
 		len     = valor_3;
 		this.grosor = valor_4;
 		this.nombre = nombre;
 		
 		CreaLink();
    
 
 }
 
   public Link2(Datos datos)
  
 {  
 		
 		rad_izq = datos.valor_1;
 		rad_der = datos.valor_2;
 		len     = datos.valor_3;
 		this.grosor = datos.valor_4;
 		this.nombre = datos.name;
 		
 		CreaLink();
    
 
 }
 

 
 
 
 
 
 
  public void CreaLink() {



    float zfront = grosor/2f;
    float zrear = -grosor/2f;
    float d = len - rad_izq -rad_der;   
    double angle;
    int ind2, ind1;     
    float tmpx, tmpy;
    
     		id = v.LINK_2;

    aa= new AxisAngle4f(0.0f,1.0f,0.0f,(float)Math.toRadians(90));
    
   	t3d.setRotation(aa);
    
		aa1= new AxisAngle4f(1.0f,0.0f,0.0f,(float)Math.toRadians(90));
		
		t3d1.setRotation(aa1);
		
		
		
    
    tg_all = new TransformGroup(t3d);
    
    
    
    
    tg_rot = new TransformGroup(t3d1);
    
    
    
    
    Transform3D t3d2 = new Transform3D();
    t3d2.set(new Vector3f(0.0f,0.0f,-rad_izq));
    
    
    TransformGroup tg_orientacion = new TransformGroup(t3d2);
    
    
    
    v3f.set(0.0f,len,0.0f);
    t3d.set(v3f);
    tg_adherir.setTransform(t3d);
    
    this.addChild(tg_adherir);
    
    tg_rot.addChild(tg_orientacion);
    
    tg_orientacion.addChild(tg_all);
    
    
    numvertex = 2*(2*segments+2)+2;
    stripsofside[0] = numvertex;
      
    TriangleStripArray geomside
      = new TriangleStripArray(numvertex,
			       GeometryArray.COORDINATES
			       | GeometryArray.NORMALS,
			       stripsofside);
    
    
    
    sidenormal.set(0.0f, 1.0f, 0.0f);
    
    
    coord.set(0.0f, rad_izq, zrear);//1
    
    
    
    
    
    geomside.setCoordinate(0, coord);
    geomside.setNormal(0, sidenormal);
    
    
    
    coord.set(0.0f, rad_izq, zfront);//2
    
    
    geomside.setCoordinate(1, coord);
    geomside.setNormal(1, sidenormal);

    coord.set(d, rad_der, zrear);//3
    
  	
    
    ind2 = 2;
    
    
    
    geomside.setCoordinate(ind2, coord);
    
    
    geomside.setNormal(ind2, sidenormal);
    
    
    
    coord.set(d, rad_der, zfront);//4
    
    
    
    geomside.setCoordinate(ind2+1, coord);
    geomside.setNormal(ind2+1, sidenormal);

    sidenormal.set(0.0f, -1.0f, 0.0f);
    
    
    
    
    coord.set(0.0f, -rad_izq, zrear);//5
    
    
    
    ind1 = 4+segments*2;
    
    
    
    geomside.setCoordinate(ind1, coord);
    geomside.setNormal(ind1, sidenormal);
    
    
    
    coord.set(0.0f, -rad_izq, zfront); //6
    
    
    
    geomside.setCoordinate(ind1+1, coord);
    geomside.setNormal(ind1+1, sidenormal);

    
    
   // mitades de circulos al final de cada brazo
    
    
    for(int i=1; i<=segments; i++) 
    
    
    
    {
      
      
      
      angle = (double)i*Math.PI/(double)segments;

			
      sidenormal.set((float)Math.sin(angle),(float)Math.cos(angle), 0.0f);
      
      
      
      tmpx = d+(float)Math.sin(angle)*rad_der;
      tmpy = (float)Math.cos(angle)*rad_der;
      
      
      coord.set(tmpx, tmpy, zrear);  //7..11..con segementos = 3
      
      
      geomside.setCoordinate(ind2+i*2, coord);
      geomside.setNormal(ind2+i*2, sidenormal);
      
      
      
      coord.set(tmpx, tmpy, zfront);//8..12..
     
      
      
      geomside.setCoordinate(ind2+1+i*2, coord);
      geomside.setNormal(ind2+1+i*2, sidenormal);

      sidenormal.set(-(float)Math.sin(angle),-(float)Math.cos(angle), 0.0f);
      
      
      tmpx = -(float)Math.sin(angle)*rad_izq;
      tmpy = -(float)Math.cos(angle)*rad_izq;
      
      
      
      
      coord.set(tmpx, tmpy, zrear);//9..13
     
      
      
      geomside.setCoordinate(ind1+i*2, coord);
      geomside.setNormal(ind1+i*2, sidenormal);
      
      
      coord.set(tmpx, tmpy, zfront);//10

     
      
      
      
      
      geomside.setCoordinate(ind1+1+i*2, coord);
      geomside.setNormal(ind1+1+i*2, sidenormal);
    }

    arm_lado = new Shape3D(geomside,apariencia);
    
    
    tg_all.addChild(arm_lado);

    numvertex = (segments+5)+(segments+2);
    stripsoffront[0] = segments+5;
    stripsoffront[1] = segments+2;
    ind1 = segments+5;
    stripsofrear[0] = segments+5;
    stripsofrear[1] = segments+2;

    TriangleFanArray geomfront
      = new TriangleFanArray(numvertex,
			     GeometryArray.COORDINATES
			     | GeometryArray.NORMALS,
			     stripsoffront);

    TriangleFanArray geomrear
      = new TriangleFanArray(numvertex,
			     GeometryArray.COORDINATES
			     | GeometryArray.NORMALS,
			     stripsofrear);

    coord.set(0.0f, 0.0f, zfront);
    geomfront.setCoordinate(0,coord);
    geomfront.setNormal(0,frontnormal);
    coord.set(d, 0.0f, zfront);
    geomfront.setCoordinate(ind1,coord);
    geomfront.setNormal(ind1,frontnormal);

    coord.set(0.0f, 0.0f, zrear);
    geomrear.setCoordinate(0,coord);
    geomrear.setNormal(0,rearnormal);
    coord.set(d, 0.0f, zrear);
    geomrear.setCoordinate(ind1,coord);
    geomrear.setNormal(ind1,rearnormal);

    for(int i=0; i<=segments; i++)
    
     {
      angle = (double)i*Math.PI/(double)segments;

      tmpx = (float)Math.sin(angle)*rad_izq;
      tmpy = (float)Math.cos(angle)*rad_izq;
      coord.set(-tmpx, tmpy, zfront);
      geomfront.setCoordinate(1+i,coord);
      geomfront.setNormal(1+i,frontnormal);

      coord.set(-tmpx, -tmpy, zrear);
      geomrear.setCoordinate(1+i,coord);
      geomrear.setNormal(1+i,rearnormal);

      tmpx = d+(float)Math.sin(angle)*rad_der;
      tmpy = -(float)Math.cos(angle)*rad_der;
      coord.set(tmpx, tmpy, zfront);
      geomfront.setCoordinate(ind1+1+i,coord);
      geomfront.setNormal(ind1+1+i,frontnormal);

      coord.set(tmpx, -tmpy, zrear);
      geomrear.setCoordinate(ind1+1+i,coord);
      geomrear.setNormal(ind1+1+i,rearnormal);
    }

    coord.set(d, -rad_der, zfront);
    geomfront.setCoordinate(segments+2,coord);
    geomfront.setNormal(segments+2,frontnormal);
    coord.set(d, rad_der, zrear);
    geomrear.setCoordinate(segments+2,coord);
    geomrear.setNormal(segments+2,rearnormal);

    coord.set(d, rad_der, zfront);
    geomfront.setCoordinate(segments+3,coord);
    geomfront.setNormal(segments+3,frontnormal);
    coord.set(d, -rad_der, zrear);
    geomrear.setCoordinate(segments+3,coord);
    geomrear.setNormal(segments+3,rearnormal);

    coord.set(0.0f, rad_izq, zfront);
    geomfront.setCoordinate(segments+4,coord);
    geomfront.setNormal(segments+4,frontnormal);
    coord.set(0.0f, -rad_izq, zrear);
    geomrear.setCoordinate(segments+4,coord);
    geomrear.setNormal(segments+4,rearnormal);

    
    arm_frente = new Shape3D(geomfront,apariencia);
   	tg_all.addChild(arm_frente);
    arm_tras = new Shape3D(geomrear,apariencia);
    tg_all.addChild(arm_tras);
    
    addChild(tg_rot);
  }


		public void adherirHijo	(Componente componente)
		{
			
			float x;
			float y;
			float z;
			Datos datos;
				

			
		if (componente.id ==v.JOINT_3)
				{
					datos=componente.getDatos();
					
					v3f.set(-grosor/2,len-datos.valor_1,0.0f);
					t3d.set(v3f);
					t3d.setRotation(new AxisAngle4f(0.0f,0.0f,1.0f,(float)Math.toRadians(90)));
					tg_adherir.setTransform(t3d);
					tg_adherir.addChild(componente);
					
					
				}
			
			else
			
			{
				
			v3f.set(0.0f,len,0.0f);
	  	t3d.set(v3f);
	  	tg_adherir.setTransform(t3d);
			tg_adherir.addChild(componente);
			 	
				
				
			}
			
			
		}
			




		public Datos getDatos()
		{
			
		Datos datos = new Datos(rad_izq, rad_der, len , grosor, id, nombre);	
			
		return datos;	
			
		}


}
