import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;



class ExtLinkRound extends ObjetoQueSeExtiende

{
			
		Cylinder link;	
		Cylinder innerlink;
	  float high=0.5f;
	  float rad=0.05f;
	  float inner_high=high-0.01f;
	  float inner_rad=rad-0.01f;
	  boolean flag=true;
	  
	  ExtLinkRound ()
	  
	  {
	  	
	  		
			tg_translada  = new TransformGroup();

			tg_translada.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			tg_translada.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

	  	
	  	link = new Cylinder(rad,high,apariencia);
	  	innerlink = new Cylinder(inner_rad,inner_high,apariencia);
	  	addChild(link);
	  	tg_translada.addChild(innerlink);
	  	addChild(tg_translada);
	  	
	  	
	  	
	  }
	

	  
	
}