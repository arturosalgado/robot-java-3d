import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;

public class HalfCircle extends Group 

{
  
  private Appearance apariencia = new Appearance();
  private float cil_hi = 1.0f;
  private float cil_rad_lo = 1.0f;
  private float cil_rad_hi = 1.0f;
  
  
  private int segments = 10;

  private Shape3D shape_body;
  
  private int numvertex;
  
  private int [] strips_body = {2*segments+2};
  
  private int [] strips_frente = {segments+2};

  
  
  
  Vector3f facenormal = new Vector3f(-1.0f, 0.0f, 0.0f);
  
  Vector3f backnormal = new Vector3f(1.0f, 0.0f, 0.0f);
  
  Vector3f sidenormal = new Vector3f(0.0f, 1.0f, 0.0f);
  
  Vector3f v3f1 = new Vector3f(0.0f, 1.0f, 0.0f);

  
  Point3f  coord = new Point3f();
  

  public HalfCircle()
  
  {
    crearCil();
  }



  public HalfCircle(float radio, float high, Appearance app)
  
  {
  	
  	cil_hi  = high;
  	cil_rad_lo = radio;
  	cil_rad_hi = radio;
  	apariencia =app;
    crearCil();
    
  }


	  public HalfCircle(float radio_top,float radio_bot,float high, Appearance app)
  
  {
  	
  	cil_hi  = high;
  	cil_rad_lo = radio_bot;
  	cil_rad_hi = radio_top;
    crearCil();
  }


  public void crearCil() {
    
    double alfa ;
    float x, z;
    float x_lo, z_lo;
    
    float y_top= cil_hi/2;
    float y_bot=-cil_hi/2;
    
//  ----------crea el cuerpo del cilindro

    numvertex = 2*segments+2;;
    
     
    TriangleStripArray tsa
      = new TriangleStripArray(numvertex,
			       GeometryArray.COORDINATES
			       | GeometryArray.NORMALS,
			       strips_body);
    
    for(int i=0; i<=segments; i++) {
      
      alfa = (double)i*Math.PI/(double)segments;

      sidenormal.set((float)Math.cos(alfa),0.0f, (float)Math.sin(alfa));
      
      x_lo = (float)Math.cos(alfa)*cil_rad_lo;
      z_lo = (float)Math.sin(alfa)*cil_rad_lo;
      
      coord.set(x_lo, y_bot, z_lo);
      tsa.setCoordinate(i*2, coord);
      tsa.setNormal(i*2, sidenormal);
      
      
      x = (float)Math.cos(alfa)*cil_rad_hi;
      z = (float)Math.sin(alfa)*cil_rad_hi;
      

      coord.set(x, y_top,z);
      tsa.setCoordinate(1+i*2, coord);
      tsa.setNormal(1+i*2, sidenormal);
      
      
      
    }

    shape_body = new Shape3D(tsa,apariencia);
    
    addChild(shape_body);
   
   
   
   	//---------------fondo
   	
   	TriangleFanArray  tfa_frente = 
		
		new TriangleFanArray
		(segments+2,GeometryArray.COORDINATES
		|GeometryArray.NORMALS,strips_frente);
		
		
		
		
		coord.set(0.0f,y_bot,0.0f);
		tfa_frente.setCoordinate(0,coord);
		
		v3f1.set(0.0f,-1.0f,0.0f);
		tfa_frente.setNormal(0,v3f1);
	
	
		for (int i=0 ;i<=segments; i++)
		{
	    alfa = (double)i*Math.PI/(double)segments;
			x= (float)Math.cos(alfa)*cil_rad_lo;
			z= (float)Math.sin(alfa)*cil_rad_lo;
			
			coord.set(x,y_bot,z);
			tfa_frente.setCoordinate(i+1,coord);
			
			v3f1.set(0.0f,-1.0f, 0.0f);
			tfa_frente.setNormal(i+1,v3f1);
			  
		}
		
		
		Shape3D  shape_frente = new Shape3D(tfa_frente,apariencia);
		
	addChild(shape_frente);
   
   
   	
   	
   	
   	   	TriangleFanArray  tfa_back = 
		
		new TriangleFanArray
		(segments+2,GeometryArray.COORDINATES
		|GeometryArray.NORMALS,strips_frente);
		
		
		
		
		coord.set(0.0f,y_top,0.0f);
		tfa_back.setCoordinate(0,coord);
		
		v3f1.set(0.0f,1.0f,0.0f);
		tfa_back.setNormal(0,v3f1);

   	int k=segments;
   	
   	
   			for (int i=1 ;i<=segments;i++,k--)
		{
		
			alfa = (double)i*Math.PI/(double)segments;
			x = (float)Math.cos(alfa)*cil_rad_hi;
			z = (float)Math.sin(alfa)*cil_rad_hi;
	
			coord.set(x,y_top,z);
			tfa_back.setCoordinate(k,coord);
			
			v3f1.set(0.0f,1.0f, 0.0f);
			tfa_back.setNormal(k,v3f1);
		
  
		}
		
		alfa = (double)(k)*Math.PI/(double)segments;
		x = (float)Math.cos(alfa)*cil_rad_hi;
		z = (float)Math.sin(alfa)*cil_rad_hi;
		
		
		coord.set(x,y_top,z);
		tfa_back.setCoordinate(segments+1,coord);
		
		v3f1.set(0.0f,1.0f, 0.0f);
		tfa_back.setNormal(segments+1,v3f1);

	
		
		
   	Shape3D  shape_back = new Shape3D(tfa_back,apariencia);
		
		addChild(shape_back);	
			

   	
   
   
  }




}
