// de los angeles salgado jose arturo 
// editor simulador de robot orientado a objetos 
import javax.media.j3d.*;


abstract class Transformacion extends Componente
{
		
		TransformGroup tg;
		Transform3D    t3d;
		
		
		Transformacion ()
		{
		
			this.setCapability(BranchGroup.ALLOW_DETACH);
			tg = new TransformGroup();
			tg.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
			tg.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
			tg.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
			
			
			
			t3d = new Transform3D();
			
			this.addChild(tg);
			
			
			
		}
		public void adherirHijo(Componente componente)
		{
			
			tg.addChild(componente);
			
		}
		
		
	
}