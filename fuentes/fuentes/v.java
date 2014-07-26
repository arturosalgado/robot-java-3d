// variables usadas en 
// el proyecto 
// Editor simulador de Robots
// diseñado e implementado  
// por 
// Jose Arturo 
// de los Angeles
// Salgado
// Facultad de Computacion BUAP.
import javax.swing.*;
import javax.media.j3d.*;  

 

 abstract class v
 {
 	
// mesa 

	static JPanel    	jp_mesa;
	static float     	f_mesa_x,f_mesa_y,f_mesa_z;
	static int       	mesa_vi_x=50;
	static int       	mesa_vi_y=50;
	static int       	mesa_vi_z=50;
	static JSlider   	js_mesa_x,js_mesa_y,js_mesa_z;
	static JLabel    	jl_mesa_x,jl_mesa_y,jl_mesa_z;
	static JButton   	jb_mesa_insert, jb_mesa_eliminar;
	static Mesa   mesa;
	
	static JLabel     jl_nombre=new JLabel("Nombre");

// base  cuadrada 1 d o f 

	static JPanel    	jp_bc1dof;
	static float     	f_bc1dof_x,f_bc1dof_y,f_bc1dof_z,f_bc1dof_r,f_bc1dof_h;
	static int       	bc1dof_vi_x=40;
	static int       	bc1dof_vi_y=60;
	static int       	bc1dof_vi_z=40;
	static int       	bc1dof_vi_r=15;
	static int       	bc1dof_vi_h=10;
	
	static JSlider   	js_bc1dof_x,js_bc1dof_y,js_bc1dof_z,js_bc1dof_r,js_bc1dof_h;



	static JLabel    	jl_bc1dof_x,jl_bc1dof_y,jl_bc1dof_z,jl_bc1dof_r,jl_bc1dof_h;
	static JButton   	jb_bc1dof_insert;
	static BaseCuadrada1DoF   bc1dof;
	static BranchGroup bg_bc1dof;
	static JTextField  jtf_bc1dof_nombre = new JTextField(20);
	
	
	
	/// base wedge 
	static JPanel    	jp_basewedge;
	static float     	f_basewedge_x,f_basewedge_y,f_basewedge_z,f_basewedge_x2,f_basewedge_o;
	static int       	vi_basewedge_x=50;
	static int       	vi_basewedge_x2=100;
	
	static int       	vi_basewedge_y=50;
	static int       	vi_basewedge_z=50;
	static int       	vi_basewedge_o =25;
	static JSlider   	js_basewedge_x,js_basewedge_y,js_basewedge_z,js_basewedge_o,js_basewedge_x2;
	static JLabel    	jl_basewedge_x,jl_basewedge_y,jl_basewedge_z,jl_basewedge_o,jl_basewedge_x2;
	static JButton   	jb_basewedge_insert;
	static BaseWedge   basewedge;
	static BranchGroup bg_basewedge;
	static JTextField  jtf_basewedge_nombre = new JTextField(20);
	
		/// base wedge 
	static JPanel    	jp_traslada;
	static float     	f_traslada_x,f_traslada_y,f_traslada_z,f_traslada_x2,f_traslada_o;
	static int       	vi_traslada_x=50;
	static int       	vi_traslada_x2=100;
	
	static int       	vi_traslada_y=50;
	static int       	vi_traslada_z=50;
	static int       	vi_traslada_o =25;
	static JSlider   	js_traslada_x,js_traslada_y,js_traslada_z,js_traslada_o,js_traslada_x2;
	static JLabel    	jl_traslada_x,jl_traslada_y,jl_traslada_z,jl_traslada_o,jl_traslada_x2;
	static JButton   	jb_traslada_insert;
	static BranchGroup bg_traslada;
	static JTextField  jtf_traslada_nombre = new JTextField(20);
	

	//------------------------
	//base : BaseWedgeRotor 
	
	
	static JPanel    			jp_base_wedge_rotor;
	
	static float     			f_base_wedge_rotor_x,f_base_wedge_rotor_y,
												f_base_wedge_rotor_z,f_base_wedge_rotor_x2,
												f_base_wedge_rotor_o;

	static float     			f_base_wedge_rotor_b_b_x,f_base_wedge_rotor_b_y,
												f_base_wedge_rotor_b_z,f_base_wedge_rotor_b_x2,
												f_base_wedge_rotor_b_o;
	//laterales
	static int       			vi_base_wedge_rotor_x=50;
	static int       			vi_base_wedge_rotor_x2=100;	
	static int       			vi_base_wedge_rotor_y=50;
	static int       			vi_base_wedge_rotor_z=80;
	static int       			vi_base_wedge_rotor_o =25;
	
	
	//base
	static int       			vi_base_wedge_rotor_b_x=50;
	static int       			vi_base_wedge_rotor_b_x2=100;	
	static int       			vi_base_wedge_rotor_b_y=50;
	static int       			vi_base_wedge_rotor_b_z=50;
	static int       			vi_base_wedge_rotor_b_o =25;
	
	
	

	
	
	static JSlider   			js_base_wedge_rotor_x,js_base_wedge_rotor_y,js_base_wedge_rotor_z,js_base_wedge_rotor_o,js_base_wedge_rotor_x2;
	static JLabel    			jl_base_wedge_rotor_x,jl_base_wedge_rotor_y,jl_base_wedge_rotor_z,jl_base_wedge_rotor_o,jl_base_wedge_rotor_x2;
	static JButton   			jb_base_wedge_rotor_insert;
	static BaseWedgeRotor base_wedge_rotor;
	static BranchGroup 		bg_base_wedge_rotor;
	static JTextField  		jtf_base_wedge_rotor_nombre = new JTextField(20);
	
	static JSlider   			js_base_wedge_rotor_b_x,js_base_wedge_rotor_b_y,js_base_wedge_rotor_b_z,js_base_wedge_rotor_b_o,js_base_wedge_rotor_b_x2;
	static JLabel    			jl_base_wedge_rotor_b_x,jl_base_wedge_rotor_b_y,jl_base_wedge_rotor_b_z,jl_base_wedge_rotor_b_o,jl_base_wedge_rotor_b_x2;
	static JButton   			jb_base_wedge_rotor_b_insert;
	static BaseWedgeRotor base_wedge_rotor_b;
	static BranchGroup 		bg_base_wedge_rotor_b;
	static JTextField  		jtf_base_wedge_rotor_b_nombre = new JTextField(20);
	

	
	//base circular 
	
	
	static JPanel    			jp_base_circular;
	
	static float     			f_base_circular_valor_1,f_base_circular_valor_2,
												f_base_circular_valor_3,f_base_circular_valor_4,
												f_base_circular_valor_5,f_base_circular_valor_6,
												f_base_circular_valor_7,f_base_circular_valor_8;

	static int       			vi_base_circular_valor_1  =28;
	static int       			vi_base_circular_valor_2  =20;	
	
	static int       			vi_base_circular_valor_3  =26;
	static int       			vi_base_circular_valor_4  =38;
	
	static int       			vi_base_circular_valor_5  =14;
	static int       			vi_base_circular_valor_6  =26;
	
	static int       			vi_base_circular_valor_7  =20;
	
	
	//	
	static int       			vi_base_circular_valor_8  =50;
	static int       			vi_base_circular_valor_9  =50;
	static int       			vi_base_circular_valor_10 =25;
	
	static JSlider   			js_base_circular_valor_1,
												js_base_circular_valor_2,
												js_base_circular_valor_3,
												js_base_circular_valor_4,
												js_base_circular_valor_5,
												js_base_circular_valor_6,
												js_base_circular_valor_7,
												js_base_circular_valor_8,
												js_base_circular_valor_9,
												js_base_circular_valor_10,
												js_base_circular_valor_11;
												
	static JLabel    			jl_base_circular_valor_1,
												jl_base_circular_valor_2,
												jl_base_circular_valor_3,
												jl_base_circular_valor_4,
												jl_base_circular_valor_5,
												jl_base_circular_valor_6,
												jl_base_circular_valor_7,
												jl_base_circular_valor_8,
												jl_base_circular_valor_9,
												jl_base_circular_valor_10,
												jl_base_circular_valor_11;

static JLabel         jl_base_circular_var_1= new JLabel("R base ");			
static JLabel         jl_base_circular_var_2= new JLabel("H base ");			
static JLabel         jl_base_circular_var_3= new JLabel("R med  ");			
static JLabel         jl_base_circular_var_4= new JLabel("H med  ");			
static JLabel         jl_base_circular_var_5= new JLabel("R sup 1");			
static JLabel         jl_base_circular_var_6= new JLabel("R sup 2");			
static JLabel         jl_base_circular_var_7= new JLabel("H sup  ");			



static JLabel         jl_base_circular_var_8= new JLabel("Rsup 3");			
static JLabel         jl_base_circular_var_9= new JLabel("Rad 1");			
static JLabel         jl_base_circular_var_10= new JLabel("Rad 1");			




	static JButton   			jb_base_circular_insert;
	
	
	
	static BaseCircular   base_circular;
	static BranchGroup 		bg_base_circular;
	static JTextField  		jtf_base_circular_nombre = new JTextField(20);
	
	
	//base circular end 
	
	// link begin 
	
	static JPanel    			jp_link;
	
	static float     			f_link_valor_1,
												f_link_valor_2,
												f_link_valor_3,
												f_link_valor_4,
												f_link_valor_5,
												f_link_valor_6,
												f_link_valor_7,
												f_link_valor_8;

	static int       			vi_link_valor_1  =10;
	static int       			vi_link_valor_2  =80;	
	static int       			vi_link_valor_3  =20;
	static int       			vi_link_valor_4  =50;
	static int       			vi_link_valor_5  =25;
	static int       			vi_link_valor_6  =50;
	static int       			vi_link_valor_7  =100;	
	static int       			vi_link_valor_8  =50;
	static int       			vi_link_valor_9  =50;
	static int       			vi_link_valor_10 =25;
	
	static JSlider   			js_link_valor_1,
												js_link_valor_2,
												js_link_valor_3,
												js_link_valor_4,
												js_link_valor_5,
												js_link_valor_6,
												js_link_valor_7,
												js_link_valor_8,
												js_link_valor_9,
												js_link_valor_10,
												js_link_valor_11;
												
	static JLabel    			jl_link_valor_1,
												jl_link_valor_2,
												jl_link_valor_3;

static JLabel         jl_link_var_1= new JLabel("X");			
static JLabel         jl_link_var_2= new JLabel("Y");			
static JLabel         jl_link_var_3= new JLabel("Z");			


	static JButton   			jb_link_insert;
	
	
	
	static Link   link;
	static BranchGroup 		bg_link;
	static JTextField  		jtf_link_nombre = new JTextField(20);
	
	
	
	// link end 
	/*******************************************************************/
	
	
	
	
	//  link2 2 begin 
	static JPanel    			jp_link2;
	
	static float     			f_link2_valor_1,
												f_link2_valor_2,
												f_link2_valor_3,
												f_link2_valor_4;

	static int       			vi_link2_valor_1  =20;
	static int       			vi_link2_valor_2  =15;	
	static int       			vi_link2_valor_3  =95;
	static int       			vi_link2_valor_4  =15;
	
	static JSlider   			js_link2_valor_1,
												js_link2_valor_2,
												js_link2_valor_3,
												js_link2_valor_4;
												
	static JLabel    			jl_link2_valor_1,
												jl_link2_valor_2,
												jl_link2_valor_3,
												jl_link2_valor_4;

static JLabel         jl_link2_var_1= new JLabel("Rad 1");			
static JLabel         jl_link2_var_2= new JLabel("Rad 2");			
static JLabel         jl_link2_var_3= new JLabel("LARGO");			
static JLabel         jl_link2_var_4= new JLabel("GROSOR");			





	static JButton   			jb_link2_insert;
	
	
	
	static Link2   link2;
	static BranchGroup 		bg_link2;
	static JTextField  		jtf_link2_nombre = new JTextField(20);
	
	
	
	
	// link 2 end 
	
	
	// link 3 begins
	
	
	static JPanel    			jp_link3;
	
	static float     			f_link3_valor_1,
												f_link3_valor_2,
												f_link3_valor_3,
												f_link3_valor_4;

	static int       			vi_link3_valor_1  =15;
	static int       			vi_link3_valor_2  =80;	
	static int       			vi_link3_valor_3  =30;
	static int       			vi_link3_valor_4  =15;
	
	static JSlider   			js_link3_valor_1,
												js_link3_valor_2,
												js_link3_valor_3,
												js_link3_valor_4;
												
	static JLabel    			jl_link3_valor_1,
												jl_link3_valor_2,
												jl_link3_valor_3,
												jl_link3_valor_4;

static JLabel         jl_link3_var_1= new JLabel("Radio");			
static JLabel         jl_link3_var_2= new JLabel("Longitud");			
static JLabel         jl_link3_var_3= new JLabel("Extremo");			
static JLabel         jl_link3_var_4= new JLabel("GROSOR");			





	static JButton   			jb_link3_insert;
	
	
	
	static Link3   link3;
	static BranchGroup 		bg_link3;
	static JTextField  		jtf_link3_nombre = new JTextField(20);
	
	
	
	
	
	
	
	// link 3 end
	
	
	
	// link extend
		
	static JPanel    			jp_link_extend;
	
	static float     			f_link_extend_valor_1,
												f_link_extend_valor_2,
												f_link_extend_valor_3;

	static int       			vi_link_extend_valor_1  =20;
	static int       			vi_link_extend_valor_2  =80;	
	static int       			vi_link_extend_valor_3  =10;
	
	static JSlider   			js_link_extend_valor_1,
												js_link_extend_valor_2,
												js_link_extend_valor_3;
												
	static JLabel    			jl_link_extend_valor_1,
												jl_link_extend_valor_2,
												jl_link_extend_valor_3;

static JLabel         jl_link_extend_var_1= new JLabel("X");			
static JLabel         jl_link_extend_var_2= new JLabel("Y");			
static JLabel         jl_link_extend_var_3= new JLabel("Z");			





	static JButton   			jb_link_extend_insert;
	
	
	
	static LinkExtensor   link_extend;
	static BranchGroup 		bg_link_extend;
	static JTextField  		jtf_link_extend_nombre = new JTextField(20);
	
	//link extend ends 
	
	
	// link extend 2
	
		static JPanel    			jp_link_extend_2;
	
	static float     			f_link_extend_2_valor_1,
												f_link_extend_2_valor_2;

	static int       			vi_link_extend_2_valor_1  =10;
	static int       			vi_link_extend_2_valor_2  =85;	
	
	static JSlider   			js_link_extend_2_valor_1,
												js_link_extend_2_valor_2;
												
	static JLabel    			jl_link_extend_2_valor_1,
												jl_link_extend_2_valor_2;

static JLabel         jl_link_extend_2_var_1= new JLabel("Radio");			
static JLabel         jl_link_extend_2_var_2= new JLabel("Altura");			



static JButton   			jb_link_extend_2_insert;
	
static LinkExtensor2   link_extend_2;
static BranchGroup 		bg_link_extend_2;
static JTextField  		jtf_link_extend_2_nombre = new JTextField(20);
	
		// link con caja begins 
		
	static JPanel    			jp_link_con_caja;
	
	static float     			f_link_con_caja_valor_1,
												f_link_con_caja_valor_2,
												f_link_con_caja_valor_3,
												f_link_con_caja_valor_4,
												f_link_con_caja_valor_5,
												f_link_con_caja_valor_6;

	static int       			vi_link_con_caja_valor_1  =15;
	static int       			vi_link_con_caja_valor_2  =150;	
	static int       			vi_link_con_caja_valor_3  =15;
	static int       			vi_link_con_caja_valor_4  =18;
	static int       			vi_link_con_caja_valor_5  =20;
	static int       			vi_link_con_caja_valor_6  =18;

	
	static JSlider   			js_link_con_caja_valor_1,
												js_link_con_caja_valor_2,
												js_link_con_caja_valor_3,
												js_link_con_caja_valor_4,
												js_link_con_caja_valor_5,
												js_link_con_caja_valor_6;
																								
	static JLabel    			jl_link_con_caja_valor_1,
												jl_link_con_caja_valor_2,
												jl_link_con_caja_valor_3,
												jl_link_con_caja_valor_4,
												jl_link_con_caja_valor_5,
												jl_link_con_caja_valor_6;


static JLabel         jl_link_con_caja_var_1= new JLabel("x base");			
static JLabel         jl_link_con_caja_var_2= new JLabel("y base");			
static JLabel         jl_link_con_caja_var_3= new JLabel("z base");			
static JLabel         jl_link_con_caja_var_4= new JLabel("x caja");			
static JLabel         jl_link_con_caja_var_5= new JLabel("y caja");			
static JLabel         jl_link_con_caja_var_6= new JLabel("z caja");			




static JButton   			jb_link_con_caja_insert;



static Link_Con_Caja  link_con_caja;
static BranchGroup 		bg_link_con_caja;
static JTextField  		jtf_link_con_caja_nombre = new JTextField(20);


	

	// link con caja ends 
	
	
	
	// link cilindrico 
			
	static JPanel    			jp_link_cilindrico;
	
	static float     			f_link_cilindrico_valor_1,
												f_link_cilindrico_valor_2,
												f_link_cilindrico_valor_3;

	static int       			vi_link_cilindrico_valor_1  =15;
	static int       			vi_link_cilindrico_valor_2  =10;	
	static int       			vi_link_cilindrico_valor_3  =80;
	
	static JSlider   			js_link_cilindrico_valor_1,
												js_link_cilindrico_valor_2,
												js_link_cilindrico_valor_3;
												
	static JLabel    			jl_link_cilindrico_valor_1,
												jl_link_cilindrico_valor_2,
												jl_link_cilindrico_valor_3;

static JLabel         jl_link_cilindrico_var_1= new JLabel("R Inferior");			
static JLabel         jl_link_cilindrico_var_2= new JLabel("R Superior");			
static JLabel         jl_link_cilindrico_var_3= new JLabel("Altura");			



static JButton   			jb_link_cilindrico_insert;



static LinkCilindrico  link_cilindrico;
static BranchGroup 		bg_link_cilindrico;
static JTextField  		jtf_link_cilindrico_nombre = new JTextField(20);




///variable joint 5

	static JPanel    			jp_joint5;
	
	static float     			f_joint5_valor_1,
												f_joint5_valor_2,
												f_joint5_valor_3;

	static int       			vi_joint5_valor_1  =38;
	static int       			vi_joint5_valor_2  =48;	
	static int       			vi_joint5_valor_3  =48;
	
	static JSlider   			js_joint5_valor_1,
												js_joint5_valor_2,
												js_joint5_valor_3;
												
	static JLabel    			jl_joint5_valor_1,
												jl_joint5_valor_2,
												jl_joint5_valor_3;

static JLabel         jl_joint5_var_1= new JLabel("X");			
static JLabel         jl_joint5_var_2= new JLabel("Y");			
static JLabel         jl_joint5_var_3= new JLabel("Z");			



static JButton   			jb_joint5_insert;



static Joint5  joint5;
static BranchGroup 		bg_joint5;
static JTextField  		jtf_joint5_nombre = new JTextField(20);


//joint 6
	static JPanel    			jp_joint6;
	
	static float     			f_joint6_valor_1,
												f_joint6_valor_2,
												f_joint6_valor_3;

	static int       			vi_joint6_valor_1  =50;
	static int       			vi_joint6_valor_2  =60;	
	static int       			vi_joint6_valor_3  =50;
	
	static JSlider   			js_joint6_valor_1,
												js_joint6_valor_2,
												js_joint6_valor_3;
												
	static JLabel    			jl_joint6_valor_1,
												jl_joint6_valor_2,
												jl_joint6_valor_3;

static JLabel         jl_joint6_var_1= new JLabel("X");			
static JLabel         jl_joint6_var_2= new JLabel("Y");			
static JLabel         jl_joint6_var_3= new JLabel("Z");			



static JButton   			jb_joint6_insert;



static Joint6  joint6;
static BranchGroup 		bg_joint6;
static JTextField  		jtf_joint6_nombre = new JTextField(20);





///
	
	
	
 	// base 01 base rectangular 

	
	static JLabel jl_x = new JLabel("X");
	static JLabel jl_y = new JLabel("Y");
	static JLabel jl_z = new JLabel("Z");
	static JLabel jl_r = new JLabel("R");
	static JLabel jl_h = new JLabel("H");

	static JLabel jl_x_bc = new JLabel("X");
	static JLabel jl_y_bc = new JLabel("Y");
	static JLabel jl_z_bc = new JLabel("Z");
	static JLabel jl_r_bc = new JLabel("R");
	static JLabel jl_h_bc = new JLabel("H");

	static JLabel jl_x_hi_bw = new JLabel("Xsup ");
	static JLabel jl_x_lo_bw = new JLabel("Xinf");
	static JLabel jl_y_bw = new JLabel("Y");
	static JLabel jl_z_bw = new JLabel("Z");
	static JLabel jl_offset_bw = new JLabel("Offset");



 	static JLabel jl_x1 = new JLabel("X");
	static JLabel jl_y1 = new JLabel("Y");
	static JLabel jl_z1 = new JLabel("Z");





 	static JLabel jl_x_hi_bwr = new JLabel("Xsup");
	static JLabel jl_x_lo_bwr = new JLabel("Xinf");
	static JLabel jl_y_bwr    = new JLabel("Y");
 	static JLabel jl_y_b_bwr  = new JLabel("Ybase");
	static JLabel jl_z_bwr         = new JLabel("Z");
	static JLabel jl_offset_bwr    = new JLabel("Offset");

 	
// link con caja round 

	
	
	static JPanel    			jp_link_con_caja_2;
	
	static float     			f_link_con_caja_2_valor_1,
												f_link_con_caja_2_valor_2,
												f_link_con_caja_2_valor_3,
												f_link_con_caja_2_valor_4;

	static int       			vi_link_con_caja_2_valor_1  =10;
	static int       			vi_link_con_caja_2_valor_2  =150;	
	static int       			vi_link_con_caja_2_valor_3  =15;
	static int       			vi_link_con_caja_2_valor_4  =20;
	
	static JSlider   			js_link_con_caja_2_valor_1,
												js_link_con_caja_2_valor_2,
												js_link_con_caja_2_valor_3,
												js_link_con_caja_2_valor_4;
												
	static JLabel    			jl_link_con_caja_2_valor_1,
												jl_link_con_caja_2_valor_2,
												jl_link_con_caja_2_valor_3,
												jl_link_con_caja_2_valor_4;

static JLabel         jl_link_con_caja_2_var_1= new JLabel("R Base");			
static JLabel         jl_link_con_caja_2_var_2= new JLabel("H Base");			
static JLabel         jl_link_con_caja_2_var_3= new JLabel("R Caja");			
static JLabel         jl_link_con_caja_2_var_4= new JLabel("H Caja");			





	static JButton   			jb_link_con_caja_2_insert;
	
	
	
	static Link_Con_Caja_2   link_con_caja_2;
	static BranchGroup 		bg_link_con_caja_2;
	static JTextField  		jtf_link_con_caja_2_nombre = new JTextField(20);
	


//link con caja roud ends  	


// joint 2

	static JPanel    			jp_joint2;
	
	static float     			f_joint2_valor_1,
												f_joint2_valor_2,
												f_joint2_valor_3,
												f_joint2_valor_4,
												f_joint2_valor_5,
												f_joint2_valor_6,
												f_joint2_valor_7,
												f_joint2_valor_8;


	
	
	
	
	
	
	
	static int       			vi_joint2_valor_1  =50/4;
	static int       			vi_joint2_valor_2  =50/4	;
	static int       			vi_joint2_valor_3  =100/4;
	static int       			vi_joint2_valor_4  =300/4;
	static int       			vi_joint2_valor_5  =20/4;
	static int       			vi_joint2_valor_6  =50/4;
	static int       			vi_joint2_valor_7  =20/4;	
	static int       			vi_joint2_valor_8  =50/4;
	
	static JSlider   			js_joint2_valor_1,
												js_joint2_valor_2,
												js_joint2_valor_3,
												js_joint2_valor_4,
												js_joint2_valor_5,
												js_joint2_valor_6,
												js_joint2_valor_7,
												js_joint2_valor_8,
												js_joint2_valor_9,
												js_joint2_valor_10,
												js_joint2_valor_11;
												
	static JLabel    			jl_joint2_valor_1,
												jl_joint2_valor_2,
												jl_joint2_valor_3,
												jl_joint2_valor_4,
												jl_joint2_valor_5,
												jl_joint2_valor_6,
												jl_joint2_valor_7,
												jl_joint2_valor_8,
												jl_joint2_valor_9,
												jl_joint2_valor_10,
												jl_joint2_valor_11;

static JLabel         jl_joint2_var_1= new JLabel("Cil rad 1");			
static JLabel         jl_joint2_var_2= new JLabel("Cil rad 2");			
static JLabel         jl_joint2_var_3= new JLabel("Cil high");			
static JLabel         jl_joint2_var_4= new JLabel("longitud ");			
static JLabel         jl_joint2_var_5= new JLabel("grosor");			
static JLabel         jl_joint2_var_6= new JLabel("x caja");			
static JLabel         jl_joint2_var_7= new JLabel("y caja");			
static JLabel         jl_joint2_var_8= new JLabel("z caja");			




	static JButton   			jb_joint2_insert;
	
	
	
	static Joint2   joint2;
	static BranchGroup 		bg_joint2;
	static JTextField  		jtf_joint2_nombre = new JTextField(20);
	
	
	

//
	static JPanel    			jp_joint3;
	
	static float     			f_joint3_valor_1,
												f_joint3_valor_2;


	static int       			vi_joint3_valor_1  =20;
	static int       			vi_joint3_valor_2  =10	;
	
	static JSlider   			js_joint3_valor_1,
												js_joint3_valor_2;
												
	static JLabel    			jl_joint3_valor_1,
												jl_joint3_valor_2;

static JLabel         jl_joint3_var_1= new JLabel("R");			
static JLabel         jl_joint3_var_2= new JLabel("H");			




	static JButton   			jb_joint3_insert;
	
	
	
	static Joint3   joint3;
	static BranchGroup 		bg_joint3;
	static JTextField  		jtf_joint3_nombre = new JTextField(20);
	
	
	


//pinza 2

	static JPanel    			jp_hand2;
	
	static float     			f_hand2_valor_1,
												f_hand2_valor_2;


	static int       			vi_hand2_valor_1  =20;
	static int       			vi_hand2_valor_2  =30;
	
	static JSlider   			js_hand2_valor_1,
												js_hand2_valor_2;
												
	static JLabel    			jl_hand2_valor_1,
												jl_hand2_valor_2;

static JLabel         jl_hand2_var_1= new JLabel("R");			
static JLabel         jl_hand2_var_2= new JLabel("H");			




	static JButton   			jb_hand2_insert;
	
	
	
	static Hand2   hand2;
	static BranchGroup 		bg_hand2;
	static JTextField  		jtf_hand2_nombre = new JTextField(20);
	
	
	






// joint 

	static JPanel    			jp_joint;
	
	static float     			f_joint_valor_1,
												f_joint_valor_2,
												f_joint_valor_3,
												f_joint_valor_4,
												f_joint_valor_5,
												f_joint_valor_6;


	
	
	
		
	
	
	
	static int       			vi_joint_valor_1  =27;
	static int       			vi_joint_valor_2  =10	;
	static int       			vi_joint_valor_3  =10;
	static int       			vi_joint_valor_4  =68;
	static int       			vi_joint_valor_5  =15;
	static int       			vi_joint_valor_6  =7;
	
	static JSlider   			js_joint_valor_1,
												js_joint_valor_2,
												js_joint_valor_3,
												js_joint_valor_4,
												js_joint_valor_5,
												js_joint_valor_6;
												
	static JLabel    			jl_joint_valor_1,
												jl_joint_valor_2,
												jl_joint_valor_3,
												jl_joint_valor_4,
												jl_joint_valor_5,
												jl_joint_valor_6;

static JLabel         jl_joint_var_1= new JLabel("R Base");			
static JLabel         jl_joint_var_2= new JLabel("H Base");			
static JLabel         jl_joint_var_3= new JLabel("R Med");			
static JLabel         jl_joint_var_4= new JLabel("H Med");			
static JLabel         jl_joint_var_5= new JLabel("R Rot");			
static JLabel         jl_joint_var_6= new JLabel("H Rot");			




static JButton   			jb_joint_insert;
	
	
	
	static Joint   joint;
	static BranchGroup 		bg_joint;
	static JTextField  		jtf_joint_nombre = new JTextField(20);
	
	
	


//---


	static JPanel    			jp_joint4;
	
	static float     			f_joint4_valor_1,
												f_joint4_valor_2,
												f_joint4_valor_3,
												f_joint4_valor_4,
												f_joint4_valor_5,
												f_joint4_valor_6;

	static int       			vi_joint4_valor_1  =27;
	static int       			vi_joint4_valor_2  =10	;
	static int       			vi_joint4_valor_3  =27;
	static int       			vi_joint4_valor_4  =10;
	static int       			vi_joint4_valor_5  =27;
	static int       			vi_joint4_valor_6  =10;
	
	static JSlider   			js_joint4_valor_1,
												js_joint4_valor_2,
												js_joint4_valor_3,
												js_joint4_valor_4,
												js_joint4_valor_5,
												js_joint4_valor_6;
												
	static JLabel    			jl_joint4_valor_1,
												jl_joint4_valor_2,
												jl_joint4_valor_3,
												jl_joint4_valor_4,
												jl_joint4_valor_5,
												jl_joint4_valor_6;

static JLabel         jl_joint4_var_1= new JLabel("R inf");			
static JLabel         jl_joint4_var_2= new JLabel("H inf");			
static JLabel         jl_joint4_var_3= new JLabel("R sup");			
static JLabel         jl_joint4_var_4= new JLabel("H sup");			
static JLabel         jl_joint4_var_5= new JLabel("R med");			
static JLabel         jl_joint4_var_6= new JLabel("H med");			




static JButton   			jb_joint4_insert;
	
	
	
	static Joint4   joint4;
	static BranchGroup 		bg_joint4;
	static JTextField  		jtf_joint4_nombre = new JTextField(20);
	
	
	
	
//	


///--------------------------------------
//rotx
//---


	static JPanel    			jp_rotacion_x;
	
	static float     			f_rotacion_x_valor_1;

	static int       			vi_rotacion_x_valor_1  =900;
	
	static JSlider   			js_rotacion_x_valor_1;
												
	static JLabel    			jl_rotacion_x_valor_1;
	

static JLabel         jl_rotacion_x_var_1= new JLabel("Grados");			



static JButton   			jb_rotacion_x_insert;
	
	
	
	static RotacionX   		rotacion_x;
	static JTextField  		jtf_rotacion_x_nombre = new JTextField(20);
	
	
	
	
//	


///--------------------------------------
//---
	static JPanel    			jp_rotacion_y;
	
	static float     			f_rotacion_y_valor_1;

	static int       			vi_rotacion_y_valor_1  =900;
	
	static JSlider   			js_rotacion_y_valor_1;
												
	static JLabel    			jl_rotacion_y_valor_1;
	

static JLabel         jl_rotacion_y_var_1= new JLabel("Grados");			



static JButton   			jb_rotacion_y_insert;
	
	
	
	static RotacionY   		rotacion_y;
	static JTextField  		jtf_rotacion_y_nombre = new JTextField(20);
	
	
	

	
	
	
//	


///--------------------------------------
//---


//---
	static JPanel    			jp_rotacion_z;
	
	static float     			f_rotacion_z_valor_1;

	static int       			vi_rotacion_z_valor_1  =900;
	
	static JSlider   			js_rotacion_z_valor_1;
												
	static JLabel    			jl_rotacion_z_valor_1;
	

static JLabel         jl_rotacion_z_var_1= new JLabel("Grados");			



static JButton   			jb_rotacion_z_insert;
	
	
	
	static RotacionZ   		rotacion_z;
	static JTextField  		jtf_rotacion_z_nombre = new JTextField(20);
	
	
	

	


///--------------------------------------
//---


//---
	static JPanel    			jp_escalamiento;
	
	static float     			f_escalamiento_valor_1;

	static int       			vi_escalamiento_valor_1  =50;
	
	static JSlider   			js_escalamiento_valor_1;
												
	static JLabel    			jl_escalamiento_valor_1;
	

static JLabel         jl_escalamiento_var_1= new JLabel("Factor");			



static JButton   			jb_escalamiento_insert;
	
	
	
	static Escalamiento   		escalamiento;
	static JTextField  		jtf_escalamiento_nombre = new JTextField(20);
	
	
	

	


	
	
//	


///--------------------------------------

//---


	static JPanel    			jp_rotar_vector;
	
	static float     			f_rotar_vector_valor_1,
												f_rotar_vector_valor_2,
												f_rotar_vector_valor_3,
												f_rotar_vector_valor_4;
												
	static int       			vi_rotar_vector_valor_1  =9000;
	static int       			vi_rotar_vector_valor_2  =0	;
	static int       			vi_rotar_vector_valor_3  =10;
	static int       			vi_rotar_vector_valor_4  =0;
	
	static JSlider   			js_rotar_vector_valor_1,
												js_rotar_vector_valor_2,
												js_rotar_vector_valor_3,
												js_rotar_vector_valor_4;
												
												
												
	static JLabel    			jl_rotar_vector_valor_1,
												jl_rotar_vector_valor_2,
												jl_rotar_vector_valor_3,
												jl_rotar_vector_valor_4;

static JLabel         jl_rotar_vector_var_1= new JLabel("Grados");			
static JLabel         jl_rotar_vector_var_2= new JLabel("X");			
static JLabel         jl_rotar_vector_var_3= new JLabel("Y");			
static JLabel         jl_rotar_vector_var_4= new JLabel("Z");			




static JButton   			jb_rotar_vector_insert;
	
	
	
	static RotarVector    rotar_vector;
	static BranchGroup 		bg_rotar_vector;
	static JTextField  		jtf_rotar_vector_nombre = new JTextField(20);
	
	
	
	
//	


///--------------------------------------

	
// riel 



	static JPanel    			jp_riel;
	
	static float     			f_riel_valor_1,
												f_riel_valor_2,
												f_riel_valor_3,
												f_riel_valor_4,
												f_riel_valor_5,
												f_riel_valor_6;


	
	
	
		
		
	
	
	
	static int       			vi_riel_valor_1  =800;
	static int       			vi_riel_valor_2  =10	;
	static int       			vi_riel_valor_3  =15;
	static int       			vi_riel_valor_4  =50;
	static int       			vi_riel_valor_5  =20;
	static int       			vi_riel_valor_6  =50;
	
	static JSlider   			js_riel_valor_1,
												js_riel_valor_2,
												js_riel_valor_3,
												js_riel_valor_4,
												js_riel_valor_5,
												js_riel_valor_6;
												
	static JLabel    			jl_riel_valor_1,
												jl_riel_valor_2,
												jl_riel_valor_3,
												jl_riel_valor_4,
												jl_riel_valor_5,
												jl_riel_valor_6;

static JLabel         jl_riel_var_1= new JLabel("x riel");			
static JLabel         jl_riel_var_2= new JLabel("y riel");			
static JLabel         jl_riel_var_3= new JLabel("z riel");			
static JLabel         jl_riel_var_4= new JLabel("x base");			
static JLabel         jl_riel_var_5= new JLabel("y base");			
static JLabel         jl_riel_var_6= new JLabel("z base");			




static JButton   			jb_riel_insert;
	
	
	
static Riel   riel;
static BranchGroup 		bg_riel;
static JTextField  		jtf_riel_nombre = new JTextField(20);






//riel end	
	
//--hand 


	static JPanel    			jp_hand;
	
	static float     			f_hand_valor_1,
												f_hand_valor_2,
												f_hand_valor_3,
												f_hand_valor_4,
												f_hand_valor_5,
												f_hand_valor_6,
												f_hand_valor_7,
												f_hand_valor_8,
												f_hand_valor_9,
												f_hand_valor_10;

	
		
	static int       			vi_hand_valor_1  =75/4;
	static int       			vi_hand_valor_2  =200/4;
	static int       			vi_hand_valor_3  =30/4;
	static int       			vi_hand_valor_4  =180/4;
	static int       			vi_hand_valor_5  =15/4;
	static int       			vi_hand_valor_6  =100/4;
	static int       			vi_hand_valor_7  =60/4;	
	static int       			vi_hand_valor_8  =120/4;
	static int       			vi_hand_valor_9  =20/4;
	static int       			vi_hand_valor_10  =100/4;

	static JSlider   			js_hand_valor_1,
												js_hand_valor_2,
												js_hand_valor_3,
												js_hand_valor_4,
												js_hand_valor_5,
												js_hand_valor_6,
												js_hand_valor_7,
												js_hand_valor_8,
												js_hand_valor_9,
												js_hand_valor_10;
												
	static JLabel    			jl_hand_valor_1,
												jl_hand_valor_2,
												jl_hand_valor_3,
												jl_hand_valor_4,
												jl_hand_valor_5,
												jl_hand_valor_6,
												jl_hand_valor_7,
												jl_hand_valor_8,
												jl_hand_valor_9,
												jl_hand_valor_10;

static JLabel         jl_hand_var_1= new JLabel("R base");			
static JLabel         jl_hand_var_2= new JLabel("H base");			
static JLabel         jl_hand_var_3= new JLabel("R dedo");			
static JLabel         jl_hand_var_4= new JLabel("H dedo");			
static JLabel         jl_hand_var_5= new JLabel("x dedo");			
static JLabel         jl_hand_var_6= new JLabel("y dedo");			
static JLabel         jl_hand_var_7= new JLabel("z dedo");			
static JLabel         jl_hand_var_8= new JLabel("x palm");			
static JLabel         jl_hand_var_9= new JLabel("y palm");			
static JLabel        jl_hand_var_10= new JLabel("z palm");			





	static JButton   			jb_hand_insert;
	
	
	
	static Hand   hand;
	static BranchGroup 		bg_hand;
	static JTextField  		jtf_hand_nombre = new JTextField(20);
	
	



	



//----


  //reset
  
  static Transform3D t3d_reset = new Transform3D();
  
 	// translacion 
 	
 	static JPanel   	jp_translacion;
 	static float    	f_translacion_x,f_translacion_y,f_translacion_z;
 	static JSlider   	js_translacion_x,js_translacion_y,js_translacion_z;
	static JLabel    	jl_translacion_x,jl_translacion_y,jl_translacion_z;
	static JButton   	jb_translacion_insert, jb_translacion_eliminar;
	static int       	translacion_vi_x=0;
	static int       	translacion_vi_y=0;
	static int       	translacion_vi_z=0;

 	
 //
 
 	static final int TRANSLACION				 = 0007;
	static final int ROTACION_X          = 0017;
	static final int ROTACION_Y          = 0027;
	static final int ROTACION_Z          = 0037;
	static final int ESCALAMIENTO        = 0047;
	static final int ROTAR_VECTOR        = 0057;

 
	static final int MESA 						   = 1017;
	
	static final int BASE_CUADRADA_1DOF  = 1027;
	static final int BASE_WEDGE          = 1037;
	static final int BASE_WEDGE_ROTOR    = 1047;
	static final int BASE_CIRCULAR       = 1057;

	static final int LINK                = 1067;
	static final int LINK_2              = 1077;
	static final int LINK_3              = 1087;
	static final int LINK_EXTENSOR       = 1097;
	static final int LINK_EXTENSOR2      = 1107;
	static final int LINK_CON_CAJA       = 1117;
	static final int LINK_CON_CAJA_2     = 1127;

	static final int LINK_CILINDRICO     = 1137;

	static final int JOINT          	   = 1147;

	static final int JOINT_2             = 1157;
	static final int JOINT_3             = 1167;
	static final int JOINT_4             = 1177;
	static final int JOINT_5             = 1187;
	static final int JOINT_6             = 1197;
	static final int HAND                = 1207;
	static final int HAND_2              = 1217;
	static final int RIEL                = 1227;


	
	
 
	
 	
 }
