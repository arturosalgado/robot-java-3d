// de los angeles salgado jose arturo
//editor simulador de robots orientado a objetos
import javax.media.j3d.*;

abstract class Componente extends BranchGroup
{
	String nombre;
	int    id;
	public abstract void adherirHijo(Componente   componente);
	public abstract Datos getDatos();
	Componente()
	{
	this.setCapability(Group.ALLOW_CHILDREN_READ);	
	this.setCapability(Group.ALLOW_CHILDREN_WRITE);	
	this.setCapability(Group.ALLOW_CHILDREN_EXTEND);	
	}
	

}