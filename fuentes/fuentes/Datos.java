
import java.io.*;


class  Datos implements Serializable 
{
	float  x_lo;
	float  x_hi;
	float  x;
	float  y;
	float  z;
	float  h;
	float  r;
	float  r2;
	float  offset;
	float  v1,v2,v3,v4,v5,v6;
	int    identificador;
	String name;
	float  valor_1;
	float  valor_2;
	float  valor_3;
	float  valor_4;
	float  valor_5;
	float  valor_6;
	float  valor_7;
	float  valor_8;
	float  valor_9;
	float  valor_10;
	
	float  grados;

	float escala;
	
	
	float radio;
	float altura;

	
	Datos()
	{
		
		this.x=0.0f;
		this.y=0.0f;
		this.z=0.0f;
		this.h=0.0f;
		this.r=0.0f;
		this.r2=0.0f;
		this.offset=0.0f;
		
	}
	
	Datos(float x,float y,float z)
	{
		
		this.x=	x;
		this.y=	y;
		this.z=	z;
		
	}
	
	Datos(float grados,int id,String nombre)
	{
		
		this.grados=grados;
		this.identificador=id;
		this.name=nombre;
		
	}
/*		Datos(float escala,int id,String nombre)
	{
		
		this.escala=escala;
		this.identificador=id;
		this.name=nombre;
		
	}
	*/
	
	
	
			Datos(float valor_1,float valor_2, int id, String name)
	{
	
		this.valor_1= valor_1;
		this.valor_2= valor_2;
		
		this.identificador=id;
		this.name=name;
		
	}
	
	
		Datos(float valor_1,float valor_2,float valor_3, int id, String name)
	{
	
		this.valor_1= valor_1;
		this.valor_2= valor_2;
		this.valor_3= valor_3;
		
		this.identificador=id;
		this.name=name;
		
	}

	
	Datos(float x, float y, float z, float radio, float altura)
	{
		
		this.x= x;
		this.y= y;
		this.z= z;
		this.r=radio;
		this.h=altura;
		
	}
	
	Datos(float valor_1, float valor_2, float valor_3, float valor_4, float valor_5, int id, String name)
	{
		
	this.valor_1=valor_1;
	this.valor_2=valor_2;
	this.valor_3=valor_3;
	this.valor_4=valor_4;
	this.valor_5=valor_5;
	this.identificador = id;
	this.name = name;
		
		
	}
	
	
			Datos(float valor_1, float valor_2, float valor_3, float valor_4, int id, String name)
	{
		
	this.valor_1=valor_1;
	this.valor_2=valor_2;
	this.valor_3=valor_3;
	this.valor_4=valor_4;
	this.identificador = id;
	this.name = name;
		
		
	}
	
	
	
		Datos(float valor_1, float valor_2, float valor_3, float valor_4, float valor_5,float valor_6, int id, String name)
	{
		
	this.valor_1=valor_1;
	this.valor_2=valor_2;
	this.valor_3=valor_3;
	this.valor_4=valor_4;
	this.valor_5=valor_5;
	this.valor_6=valor_6;
	
	this.identificador = id;
	this.name = name;
		
		
	}
		Datos(float valor_1, float valor_2, float valor_3, float valor_4, float valor_5,float valor_6, float valor_7,int id, String name)
	{
		
	this.valor_1=valor_1;
	this.valor_2=valor_2;
	this.valor_3=valor_3;
	this.valor_4=valor_4;
	this.valor_5=valor_5;
	this.valor_6=valor_6;
	this.valor_7=valor_7;

	this.identificador = id;
	this.name = name;
		
		
	}


		Datos(float valor_1, float valor_2, float valor_3, float valor_4, float valor_5,float valor_6, float valor_7,float valor_8,int id, String name)
	{
		
	this.valor_1=valor_1;
	this.valor_2=valor_2;
	this.valor_3=valor_3;
	this.valor_4=valor_4;
	this.valor_5=valor_5;
	this.valor_6=valor_6;
	this.valor_7=valor_7;
	this.valor_8=valor_8;

	this.identificador = id;
	this.name = name;
		
		
	}

	
	Datos(float valor_1, float valor_2, float valor_3, float valor_4, float valor_5,float valor_6,
		 float valor_7, float valor_8, float valor_9, float valor_10,int id, String name)
	{
		
	this.valor_1=valor_1;
	this.valor_2=valor_2;
	this.valor_3=valor_3;
	this.valor_4=valor_4;
	this.valor_5=valor_5;
	this.valor_6=valor_6;
	this.valor_7=valor_7;
	this.valor_8=valor_8;
	this.valor_9=valor_9;
	
	this.valor_10=valor_10;
	
	this.identificador = id;
	this.name = name;
		
		
	}
	

	
	

	
	
	
	
	
}