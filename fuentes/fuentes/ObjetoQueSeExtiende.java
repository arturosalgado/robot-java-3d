import javax.media.j3d.*;

class  ObjetoQueSeExtiende extends ObjetoFisico implements InterfazExtiende

{
	
	int  valor_inicial=0;
	int  valor_final = 0;
	int  offset  =10;

	Hilo_Extend hilo;
	Hilo_Extend_H hilo2;

	TransformGroup tg_translada;
	
	
	ObjetoQueSeExtiende()
	{

		tg_translada = new TransformGroup();		
		tg_translada.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);	
		tg_translada.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
   	tg_translada.setCapability(Group.ALLOW_CHILDREN_EXTEND);	

		
		
	}
	
	 public void extender(int distancia,int resolucion ,boolean modo)
	  {
	  	
	  	valor_final=valor_final + offset;
				
			
			hilo = new Hilo_Extend (true,resolucion,tg_translada, valor_inicial,valor_final);

				if (modo==false)
				{
					try
					{	
					hilo.start();
					hilo.join();
					}
					catch(InterruptedException e)
					{
					}		
					
				}
				else
				{
					
					hilo.start();	
					
				}				
			
			valor_inicial=valor_inicial+offset;
	  	
	  	
	  }


	  public void contraer(int distancia,int resolucion, boolean modo)
	  {
	  	valor_inicial=valor_inicial - offset;
				
			
			hilo = new Hilo_Extend (false,resolucion,tg_translada, valor_inicial,valor_final);


				if (modo==false)
				{
					try
					{	
					hilo.start();
					hilo.join();
					}
					catch(InterruptedException e)
					{
					}		
					
				}
				else
				{
					
					hilo.start();	
					
				}				
			
			valor_final=valor_final-offset;
	  	
	  	
	  }
		
	void desplazar (int distancia, int resolucion, boolean modo)
	
	{
		
		offset = distancia;
			
			valor_final=valor_final + offset;
			
			//si valor inicial es mayor que valor final 
			
		
			
			// cambia la direccion en que se llama al hilo
			if (valor_inicial<valor_final)
			{
				hilo = new Hilo_Extend (true,resolucion, tg_translada, valor_inicial,valor_final);
				if (modo==false)
				{
					try
					{	
					hilo.start();
					hilo.join();
					}
					catch(InterruptedException e)
					{
					}		
					
				}
				else
				{
					
					hilo.start();	
					
				}	
				valor_inicial=valor_inicial+offset;			
			}

			//vf es menor
			else
			{
				
				hilo = new Hilo_Extend(false,resolucion,tg_translada, valor_inicial,valor_final);
				if (modo==false)
				{
					try
					{
						hilo.start();
						hilo.join();
					}
					catch(InterruptedException e)
					{
					}
				}
				else
				{
					
				hilo.start();	
				}
				
				
				valor_inicial=valor_inicial+offset;			
			}
		
		
		
		
		
		
		
		
		
		
		
	}
	
		void desplazar_h(int distancia, int resolucion, boolean modo)
	
	{
		
		offset = distancia;
			
			valor_final=valor_final + offset;
			
			//si valor inicial es mayor que valor final 
			
		
			
			// cambia la direccion en que se llama al hilo
			if (valor_inicial<valor_final)
			{
				hilo2 = new Hilo_Extend_H (true,resolucion, tg_translada, valor_inicial,valor_final);
				if (modo==false)
				{
					try
					{	
					hilo2.start();
					hilo2.join();
					}
					catch(InterruptedException e)
					{
					}		
					
				}
				else
				{
					
					hilo2.start();	
					
				}	
				valor_inicial=valor_inicial+offset;			
			}

			//vf es menor
			else
			{
				
				hilo2 = new Hilo_Extend_H(false,resolucion,tg_translada, valor_inicial,valor_final);
				if (modo==false)
				{
					try
					{
						hilo2.start();
						hilo2.join();
					}
					catch(InterruptedException e)
					{
					}
				}
				else
				{
					
				hilo2.start();	
				}
				
				
				valor_inicial=valor_inicial+offset;			
			}
		
		
		
		
		
		
		
		
		
		
		
	}
}