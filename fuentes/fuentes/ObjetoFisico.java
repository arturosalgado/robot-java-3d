// de los angeles salgado jose arturo 
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.applet.MainFrame;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.io.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import java.text.NumberFormat;
import java.awt.Font;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Box;
import java.util.StringTokenizer;
import java.awt.Dimension;
import javax.media.j3d.Canvas3D;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import java.awt.Color;

// crea la base del robot 
// de los angeles salgado jose arturo


class ObjetoFisico extends Componente
{
	
	 public Appearance apariencia;
	 protected Material   material;
	 public static  PolygonAttributes poli_atributos;
	 
	 Transform3D t3d;
 	 Transform3D t3d_translacion;

	 TransformGroup tg_all;
	 TransformGroup tg_adherir;
	 
	 Vector3f v3f;
	 Vector3f v3f_translacion;
	 
	 boolean mascaradealambre=false;
	
	 
	 
	 
	 
	 public ObjetoFisico()
	 {
		

		// permite que cualquier objeto pueda ser quitado del arbol.
		this.setCapability(BranchGroup.ALLOW_DETACH);
	 	
	 	// permite que los componentes puedan tener hijos
	 	tg_all = new TransformGroup();
	 	tg_all.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	 	tg_all.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
	 	tg_all.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
	 	tg_all.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	 	tg_all.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

	 	tg_adherir = new TransformGroup();
	 	tg_adherir.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	 	tg_adherir.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
	 	tg_adherir.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
	 	tg_adherir.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	 	tg_adherir.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


	 	
	 	
	 	
	 	t3d = new Transform3D();
	 	v3f= new Vector3f();
	 	
	 	v3f_translacion = new Vector3f();
	 	t3d_translacion = new Transform3D();

	 	
	 	
	 	apariencia = new Appearance();
	 	
	 	apariencia.setCapability(Appearance.ALLOW_POLYGON_ATTRIBUTES_WRITE);
	 		 	apariencia.setCapability(Appearance.ALLOW_POLYGON_ATTRIBUTES_READ);

	 	
	 	
		poli_atributos = new PolygonAttributes();
	 		 	poli_atributos.setCapability(PolygonAttributes.ALLOW_MODE_WRITE);
	 		 	poli_atributos.setCapability(PolygonAttributes.ALLOW_MODE_READ);

	 	
	 	
	 	
	 	material   = new Material ();
	 	material.setAmbientColor(  0.8f, 0.8f, 0.8f );//cuanta luz del ambiente es reflejada por
	 			// el material
		material.setDiffuseColor( new Color3f(Color.lightGray));//color del material cuando esta iluminado
		material.setEmissiveColor( 0.1f, 0.1f, 0.1f );//color de la luz que el material emite if any
		material.setSpecularColor( 0.4f, 0.4f, 0.4f );//hace que parezca shiny
		material.setShininess( 128.0f );//0 brilla mas 128 brilla menos 
		apariencia.setMaterial(material);
				
	 	
	 }



	
	 
	 public void adherirHijo(Componente componente)
	 {
	 	
	 	
	 	tg_adherir.addChild(componente);
	 	
	 	
	 }
	

 public  void ponMascara()
	{
	 
	 poli_atributos.setPolygonMode(PolygonAttributes.POLYGON_LINE);

	apariencia.setPolygonAttributes(poli_atributos);
	
	}

 public void quitaMascara()
	{
	 
	 poli_atributos.setPolygonMode(PolygonAttributes.POLYGON_FILL);

	apariencia.setPolygonAttributes(poli_atributos);
	
	}



	public  Datos getDatos()
	{
		//el metod sera sobre escrito override
		return null;
		
	}

	
}






