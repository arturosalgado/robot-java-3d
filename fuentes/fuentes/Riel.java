import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.Box;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



class Riel extends ObjetoQueSeExtiende
{
	
	Box riel;
	Box base;	
	
	private float dim_x_riel=3.0f;
	private float dim_y_riel=0.05f;
	private float dim_z_riel=0.04f;
	
	private float dim_x_base=0.08f;
	private float dim_y_base=0.02f;
	private float dim_z_base=0.12f;
	
	TransformGroup tg_base;
	TransformGroup tg_riel;
	
	int  valor_inicial=0;
	int  valor_final = 0;
	int  offset  =45;
	Hilo_Extend hilo;
  boolean flag=true;

	final int id = v.RIEL;

	
	Riel()
	{
		
		crearRiel();
		
	}
	
		Riel(float valor_1, float valor_2, float valor_3, float valor_4, float valor_5, float valor_6,String nombre)
	{
	
	
	
	dim_x_riel=valor_1/2;
	dim_y_riel=valor_2/2;
	dim_z_riel=valor_3/2;
	
	dim_x_base=valor_4/2;
	dim_y_base=valor_5/2;
	dim_z_base=valor_6/2;

		
	this.nombre = nombre;
		
		
		
		
		
		
		
		
		
		crearRiel();
		
	}


	Riel(Datos datos)
	{
	
	
	
	dim_x_riel=datos.valor_1/2;
	dim_y_riel=datos.valor_2/2;
	dim_z_riel=datos.valor_3/2;
	
	dim_x_base=datos.valor_4/2;
	dim_y_base=datos.valor_5/2;
	dim_z_base=datos.valor_6/2;

		
	this.nombre = datos.name;
		
		
		
		
		
		
		
		
		
		crearRiel();
		
	}

	
	
	public void crearRiel()
	{
		
		base = new Box(dim_x_base,dim_y_base,dim_z_base,apariencia);
		riel = new Box(dim_x_riel,dim_y_riel,dim_z_riel,apariencia);
					  		

		
		v3f.set(0.0f,dim_y_riel,0.0f);
		t3d.set(v3f);
		tg_riel = new TransformGroup(t3d);
		tg_riel.addChild(riel);
		tg_all.addChild(tg_riel);

		
		v3f.set(0.0f,(dim_y_riel)+(dim_y_base/2f),0.0f);
		t3d.set(v3f);
		tg_base=new TransformGroup(t3d);
		tg_base.addChild(tg_translada);
		tg_translada.addChild(base);
		tg_riel.addChild(tg_base);
		
		
		v3f.set(0.0f,dim_y_base,0.0f);
		t3d.set(v3f);
		tg_adherir.setTransform(t3d);
		tg_translada.addChild(tg_adherir);
		
		addChild(tg_all);
		
	}
	



	
	public Datos getDatos()
	{
		
	

	Datos  datos = new Datos(dim_x_riel*2,dim_y_riel*2,dim_z_riel*2,dim_x_base*2,dim_y_base*2,dim_z_base*2,id,nombre);
	
	return datos; 	
		
		
	}
	
	
}