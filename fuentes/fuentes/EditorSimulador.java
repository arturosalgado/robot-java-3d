
import javax.swing.*;
import javax.swing.event.*;  
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import java.util.*;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.io.*;
import java.text.*;
import com.sun.j3d.utils.geometry.ColorCube;





public class EditorSimulador extends JFrame implements  ActionListener, ListSelectionListener

{ 


	//elementos  para la interfaz principal
	JMenuBar    jmb_bar;
	JMenu       jm_menu;
	JMenu       jm_ayuda;
	JMenu       jm_tarea;
	JMenuItem   jmi_item_salir;
	JMenuItem   jmi_item_abrir;
	JMenuItem   jmi_item_guardar;
	JMenuItem   jmi_item_tarea_abrir;
	JMenuItem   jmi_item_tarea_guardar;
	JMenuItem   jmi_item_nuevo;

	JMenuItem 	jmi_acerca_de = new JMenuItem("Acerca de...");
	JMenuItem 	jmi_ayuda = new JMenuItem("Ayuda");
	JMenuItem 	jmi_item_tarea_nueva 		= new JMenuItem("Nueva Tarea");
	JMenuItem 	jmi_item_guardar_robot_como 		= new JMenuItem("Guardar Como");
	JMenuItem 	jmi_item_guardar_tarea_como 		= new JMenuItem("Guardar Como");




 	JPanel      jp_main;
	JTabbedPane jtp_edit_program;
	ImageIcon   i_icono;
	JPanel      jp_panel_editar;
	JPanel      jp_panel_programar;
	JPanel      jp_panel_lista;
	
	JLabel      jl_metodos;
	
	
	
	
	JPanel 			jp_interno_bot = new JPanel();
	
	
	
	

	final JFileChooser jfc_robot = new JFileChooser("archivos de programa/EditorDeRobots");
	final JFileChooser jfc_tarea = new JFileChooser("archivos de programa/editorderobots");
	
    ExampleFileFilter filtro_rbt=new ExampleFileFilter("rbt","Archivos de Robot");
    ExampleFileFilter filtro_tar=new ExampleFileFilter("tar","Archivos de Tarea");;
    

	//elemntos para el pane editar 
	private final String s_piezas[]= 
	{
		
	"Seleccionar Componente",
	"Base Cuadrada 1DoF",
	"Base Wedge",
	"Base Wedge Rotor",
	"Base Circular",
	"Link",
	"Link 2",
	"Link 3",
	"Link 4",
	"Joint",
	"Joint 2",
	"Joint 3",
	"Joint 4",
	"Joint 5",
	"Joint 6",
	"JointLineal 1",
	"JointLineal 2",
	"JointLineal 3",
	"JointLineal 4",
	"Pinza",
	"Pinza 2",
	"Riel",
	"Translacion",
	"Rotacion X",
	"Rotacion Y",
	"Rotacion Z",
	"Escalamiento",
	"Rotacion al Vector"
	
	};

	JComboBox   jc_combo_piezas;
	JLabel      jl_show_picture;
	private  JPanel 			jp_cardmain;
	JToggleButton   jtb_ejes;
	JButton         jb_reset;
	boolean     ejesOn=false;
	//temporalmente 
	JToggleButton     jb_mascara;
	JButton     jb_abrir;
	JButton     jb_eliminar;




	//elementos para el pane programar
  JScrollPane jsp_scrollpane;
	JTextArea 	jt_texto = new JTextArea();
	JButton   	jb_ejecutar;
	String      s="";
	String      [] s_arreglo = new String[200];
	float valf = 0.0f;



	//elementos j3d 	
	GraphicsConfiguration configuracion;
	Canvas3D canvas;
	SimpleUniverse universe;
	BranchGroup bg_main;
	TransformGroup tg_all;
	BranchGroup bg_ejes;


  Robot robot  = new Robot();
  
  
  
  int indice_robot=0;
  int indice_temp=0;
  

	Componente comp;
	
  Mesa mesa;

  Translacion translacion;	
  
  /******************************************************/
  
  int entero;

JList	lista;
DefaultListModel  	dlm_listmodel;
	//otros
			JavaBrowser ayuda = new JavaBrowser();

	boolean cambios = false;
	boolean cambios_tarea=false;
		static boolean paralelo = false;
	static boolean continuar = true;
  
  
  String nombre_de_archivo="";
  String nombre_de_tarea="";












//constructor de la clase 
	EditorSimulador(String Name)
	
	{
		super(Name);
// inicializa elementos para la interfaz principal
		
		jmb_bar = new JMenuBar();
		setJMenuBar(jmb_bar);
		jm_menu = new JMenu("Robot");
		jmb_bar.add(jm_menu);
		jm_tarea = new JMenu ("Tarea");
	
		jmb_bar.add(jm_tarea);
		
		jm_ayuda = new JMenu ("Ayuda");
	
		jmb_bar.add(jm_ayuda);
		
		
		
		/****/
		
		
		  
			jfc_robot.addChoosableFileFilter(filtro_rbt);
			jfc_robot.setFileFilter(filtro_rbt);	
		   
			jfc_tarea.addChoosableFileFilter(filtro_tar);
			jfc_tarea.setFileFilter(filtro_tar);	
		  
		
		
		/*************/
		jmi_item_tarea_guardar  = new JMenuItem("Guardar Tarea");
		jmi_item_tarea_abrir 		= new JMenuItem("Abrir Tarea");

		jmi_item_salir = new JMenuItem("Salir");
		jmi_item_abrir = new JMenuItem("Abrir Robot");
		jmi_item_guardar = new JMenuItem("Guardar Robot");
		jmi_item_nuevo = new JMenuItem("Nuevo Robot");

		
				jm_ayuda.add(jmi_acerca_de);
				jm_ayuda.add(jmi_ayuda);

		
		jmi_item_tarea_guardar.addActionListener(this);
		jmi_item_tarea_abrir.addActionListener(this);
		jmi_item_tarea_nueva.addActionListener(this);

		jmi_item_salir.addActionListener(this);
		jmi_item_abrir.addActionListener(this);
		jmi_item_guardar.addActionListener(this);
		jmi_acerca_de.addActionListener(this);
		
		jmi_ayuda.addActionListener(this);
		jmi_item_nuevo.addActionListener(this);

		jmi_item_guardar_robot_como.addActionListener(this);
		jmi_item_guardar_tarea_como.addActionListener(this);




		
		jm_menu.add(jmi_item_nuevo);
		jm_menu.add(jmi_item_abrir);
		jm_menu.add(jmi_item_guardar);
		jm_menu.add(jmi_item_guardar_robot_como);
		

		jm_menu.addSeparator();
		jm_menu.add(jmi_item_salir);
		
	
	
		jm_tarea.add(jmi_item_tarea_nueva);
		jm_tarea.add(jmi_item_tarea_abrir);
		jm_tarea.add(jmi_item_tarea_guardar);
		jm_tarea.add(jmi_item_guardar_tarea_como);
	
		jp_main = new JPanel(new GridBagLayout());
		jtp_edit_program = new JTabbedPane();
		i_icono = new ImageIcon("imagenes/robot.jpg");
		jp_panel_editar = new JPanel();
		jp_panel_programar = new JPanel();
		crearPanelEditar(jp_panel_editar);
		crearPanelProgramar(jp_panel_programar);
	
		jtp_edit_program.addTab("Editar",i_icono,jp_panel_editar,"Editar Robot");
		jtp_edit_program.setSelectedIndex(0);
		jtp_edit_program.addTab("Programar",i_icono,jp_panel_programar,"Programar Robot");

		jl_metodos= new JLabel("  ");

		jp_main.add(jtp_edit_program);
		
		
		jp_main.add(jp_panel_lista);
		
		
		dlm_listmodel=new DefaultListModel();
		
		
		
		lista = new JList (dlm_listmodel);
		
		lista.addListSelectionListener(this);
		
		
		JScrollPane jsp_lista= new JScrollPane(lista);
		
		TitledBorder tb_lista = new TitledBorder(new EtchedBorder());
		tb_lista.setTitle("Lista de Componentes");
		jp_panel_lista.setBorder(tb_lista);

		
		
		
		jp_panel_lista.add(jsp_lista);
	
	
	
	

		
		adherirAGridBag(jp_main,jtp_edit_program,0,0,1,1,1.0,0.5,true);
		adherirAGridBag(jp_main,jp_panel_lista	,0,1,1,1,1.0,0.40,true);
		adherirAGridBag(jp_main,jl_metodos    	,0,2,1,1,1.0,0.10,true);

		
				
		
		
		jp_main.setPreferredSize( new Dimension( 200, 768) );
		// adhiere al JFrame principal
		getContentPane().add(jp_main,BorderLayout.WEST );
		
// inicializa elementos 3D

		configuracion = SimpleUniverse.getPreferredConfiguration();
		canvas = new Canvas3D(configuracion);
		universe = new SimpleUniverse(canvas);
		universe.getViewingPlatform().setNominalViewingTransform();
		bg_main = crearBranchPrincipal();
		bg_main.compile();
		universe.addBranchGraph(bg_main); 
		getContentPane().add(canvas,BorderLayout.CENTER);

	
		
		
		setSize(1024,768);
		
		
		setVisible(true);
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent evt)
		{
			if (verificarSalida()==true)		
			{
			System.exit(0);
			}
			else
			{
			}
			
		}});

		
	}
	 
	void mostrarDialogoSalvar()
	
	{ 
			File archivo; 
		int returnVal=jfc_robot.showSaveDialog(this);
			if (returnVal==JFileChooser.APPROVE_OPTION)
				{
						archivo = jfc_robot.getSelectedFile();
						salvar(archivo.getName()	);
				} 
	 
	}
	
	
	
	
	boolean verificarSalida() 
	{


	Object [] opciones = {"Si","No","Cancelar"};
	int res;

	
	if (((robot.vacio==false)&&(cambios==true))||(jt_texto.getText().compareTo("")!=0))
	{
		if((robot.vacio==false)&&(cambios==true))
		{
			res=JOptionPane.showOptionDialog(this,"Desea guardar los cambios ?","Antes de Continuar ",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[2]);		
			if (res==JOptionPane.YES_OPTION)
			{
				mostrarDialogoSalvar();
				if (jt_texto.getText().compareTo("")!=0)
				mostrarDialogoTarea();
				
				return true;	
			}
			else
			if (res==JOptionPane.NO_OPTION)
			{
				return true;
			}
		}
		else
		if ((jt_texto.getText().compareTo("")!=0)&&(cambios_tarea=true))
		{
			res=JOptionPane.showOptionDialog(this,"Desea guardar los cambios ?","Antes de Continuar ",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[2]);		
			if (res==JOptionPane.YES_OPTION)
			{	
			mostrarDialogoTarea();
			return true;
			}
			if (res==JOptionPane.NO_OPTION)
			{
			return true;	
			}
		}
	}





	else
	if ((robot.vacio==true)||(cambios==false))
	{
		
	return true;	
		
	}
	
		return false;	


}

boolean verificarAbrir() 
	{


	Object [] opciones = {"Si","No","Cancelar"};
	int res;

	//if ((robot.vacio==false)||(jt_texto.getText().compareTo("")!=0))
	if ((robot.vacio==false)&&(cambios==true))
	{
			
			
			
			
			res=JOptionPane.showOptionDialog(this,"Desea guardar los cambios ?","Antes de Continuar ",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[2]);		
			if (res==JOptionPane.YES_OPTION)
			{
				if (nombre_de_archivo.compareTo("")==0)
				{
				mostrarDialogoSalvar();
				}
				else
				{
				salvar(nombre_de_archivo);	
				}
				return true;	
			}
			else
			if (res==JOptionPane.NO_OPTION)
			{
				return true;
			}
	
		
		
	}

	else
	if ((robot.vacio==true)||(cambios==false))
	{
		
	return true;	
		
	}
	
		return false;	


}

	 
	 
	BranchGroup  crearBranchPrincipal()
	{
		BranchGroup bg_root;
		Group     g_ambiente;
		Transform3D  t3d;
		Vector3f     v3f;
		Ejes ejes;
		
		
		
		
		
		
		v3f= new Vector3f(0.0f,-1.0f,-4.0f);
		
		t3d = new Transform3D();
		t3d.set(v3f);
		tg_all = new TransformGroup(t3d);
		tg_all.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tg_all.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg_all.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		tg_all.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		tg_all.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		
		MouseRotate behavior = new MouseRotate();
		behavior.setTransformGroup(tg_all);
		tg_all.addChild(behavior);
		behavior.setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0));
		
		
		MouseTranslate behaviorT = new MouseTranslate();
		behaviorT.setTransformGroup(tg_all);
		tg_all.addChild(behaviorT);
		behaviorT.setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0));
	
		
		MouseZoom behaviorZoom = new MouseZoom();
		behaviorZoom.setTransformGroup(tg_all);
		tg_all.addChild(behaviorZoom);
		behaviorZoom.setSchedulingBounds(new BoundingSphere(new Point3d(), 100.0));

		ejes = new Ejes();
		bg_ejes = new BranchGroup();
		bg_ejes.setCapability(BranchGroup.ALLOW_DETACH);
		bg_ejes.addChild(ejes);
		bg_ejes.compile();
			
	
	
		
		bg_root = new BranchGroup();
		bg_root.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		g_ambiente = crearAmbiente();
		bg_root.addChild(g_ambiente);
		
		
		tg_all.addChild(robot);
		

	
		bg_root.addChild(tg_all);		
		
		
		
		
		
		return bg_root;	
		
		
	}
	

	Group crearAmbiente()
	{
	
		Group g_ambiente = new BranchGroup();
		
		BoundingSphere bounds=new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		
		
		Color3f back_color = new Color3f(0.2f, 0.2f, 0.4f);
		Background back_ground = new Background(back_color);
		back_ground.setApplicationBounds(bounds);
		
		//color del ambiente 
		Color3f ambient_color = new Color3f(0.1f, 0.1f, 0.1f);
		AmbientLight ambient_lite = new AmbientLight(ambient_color);
		ambient_lite.setInfluencingBounds(bounds);
		
		// luz direccional 
		Color3f lite_color_1 = new Color3f(1.0f, 1.0f, 0.9f);
		Vector3f lite_direction_1  = new Vector3f(4.0f, -7.0f, -12.0f);
		Color3f lite_color_2 = new Color3f(0.3f, 0.3f, 0.4f);
		Vector3f lite_direction_2  = new Vector3f(-6.0f, -2.0f, -1.0f);
		
		DirectionalLight luz_1= new DirectionalLight(lite_color_1,lite_direction_1);
		luz_1.setInfluencingBounds(bounds);
		
		DirectionalLight luz_2= new DirectionalLight(lite_color_2,lite_direction_2);
		luz_2.setInfluencingBounds(bounds);
	
	
		g_ambiente.addChild(back_ground);
		g_ambiente.addChild(ambient_lite);
		g_ambiente.addChild(luz_1);
		g_ambiente.addChild(luz_2);
		
		return g_ambiente;
   		
			
		
	}
	
	void crearPanelEditar(JPanel panel)
	{
		
		panel.setLayout(new GridBagLayout());
		
		
		JPanel jp_interno_up = new JPanel();
		jp_interno_up.setLayout(new GridBagLayout());
		TitledBorder tb_u = new TitledBorder(new EtchedBorder());
		tb_u.setTitle("Seleccionar Componente");
		jp_interno_up.setBorder(tb_u);
		
		jp_interno_bot.setLayout(new GridBagLayout());
		TitledBorder tb_b = new TitledBorder(new EtchedBorder());
		tb_b.setTitle("Propiedades del Universo");
		jp_interno_bot.setBorder(tb_b);
		
		
		
		jp_panel_lista = new JPanel();
		jp_panel_lista.setLayout(new GridLayout(1,0));
		
		//elementos contenidos en el panel seleccioner elemento 
		jc_combo_piezas = new JComboBox(s_piezas);
		jc_combo_piezas.addActionListener(this);
		jl_show_picture = new JLabel(new ImageIcon("imagenes/Seleccionar Pieza.jpg"),JLabel.CENTER);
		jp_cardmain = new JPanel();
		jp_cardmain.setLayout(new CardLayout());
		TitledBorder tb_card = new TitledBorder(new EtchedBorder());
		tb_card.setTitle("Propiedades");
		jp_cardmain.setBorder(tb_card);

		
		
		
		
		v.jp_mesa = new JPanel();
		crearPanelMesa(v.jp_mesa);

		v.jp_bc1dof = new JPanel();
		crearPanelBaseCuadrada1DoF(v.jp_bc1dof);

		v.jp_basewedge = new JPanel();
		crearPanelBaseWedge(v.jp_basewedge);

		v.jp_base_wedge_rotor = new JPanel();
		crearPanelBaseWedgeRotor(v.jp_base_wedge_rotor);

		v.jp_base_circular  = new JPanel();
		crearPanelBaseCircular(v.jp_base_circular);

		v.jp_link  = new JPanel();
		crearPanelLink(v.jp_link);

		v.jp_link2  = new JPanel();
		crearPanelLink2(v.jp_link2);

		v.jp_link3  = new JPanel();
		crearPanelLink3(v.jp_link3);

		v.jp_link_extend  = new JPanel();
		crearPanelLinkExtend(v.jp_link_extend);

		v.jp_link_extend  = new JPanel();
		crearPanelLinkExtend(v.jp_link_extend);

		v.jp_link_extend_2  = new JPanel();
		crearPanelLinkExtend2(v.jp_link_extend_2);
	
		v.jp_link_con_caja  = new JPanel();
		crearPanelLinkConCaja(v.jp_link_con_caja);

		v.jp_link_con_caja_2  = new JPanel();
		crearPanelLinkConCaja2(v.jp_link_con_caja_2);

		v.jp_link_cilindrico  = new JPanel();
		crearPanelLinkCilindrico(v.jp_link_cilindrico);

		v.jp_joint  = new JPanel();
		crearPanelJoint(v.jp_joint);


		v.jp_joint2  = new JPanel();
		crearPanelJoint2(v.jp_joint2);

		v.jp_joint3  = new JPanel();
		crearPanelJoint3(v.jp_joint3);

		v.jp_joint4  = new JPanel();
		crearPanelJoint4(v.jp_joint4);
		
		v.jp_joint5  = new JPanel();
		crearPanelJoint5(v.jp_joint5);

		v.jp_joint6  = new JPanel();
		crearPanelJoint6(v.jp_joint6);
		
		v.jp_hand  = new JPanel();
		crearPanelHand(v.jp_hand);

		v.jp_hand2  = new JPanel();
		crearPanelHand2(v.jp_hand2);

		v.jp_riel  = new JPanel();
		crearPanelRiel(v.jp_riel);

		v.jp_translacion = new JPanel();
		crearPanelTranslacion(v.jp_translacion);
	
	
	
	//-----
	
	
		v.jp_rotacion_x = new JPanel();
		crearPanelRotacionX(v.jp_rotacion_x);

		v.jp_rotacion_y = new JPanel();
		crearPanelRotacionY(v.jp_rotacion_y);

		v.jp_rotacion_z = new JPanel();
		crearPanelRotacionZ(v.jp_rotacion_z);

		v.jp_escalamiento = new JPanel();
		crearPanelEscalamiento(v.jp_escalamiento);

			v.jp_rotar_vector = new JPanel();
		crearPanelRotarVector(v.jp_rotar_vector);

		
		
		JPanel jp_void = new JPanel();
		jp_cardmain.add(jp_void ,s_piezas[0]);
		jp_cardmain.add(v.jp_bc1dof,s_piezas[1]);
		jp_cardmain.add(v.jp_basewedge,s_piezas[2]);
		jp_cardmain.add(v.jp_base_wedge_rotor,s_piezas[3]);
		jp_cardmain.add(v.jp_base_circular,s_piezas[4]);
		jp_cardmain.add(v.jp_link,s_piezas[5]);
		jp_cardmain.add(v.jp_link2,s_piezas[6]);
		jp_cardmain.add(v.jp_link3,s_piezas[7]);
		jp_cardmain.add(v.jp_link_cilindrico,s_piezas[8]);
		jp_cardmain.add(v.jp_joint,s_piezas[9]);
		jp_cardmain.add(v.jp_joint2,s_piezas[10]);
		jp_cardmain.add(v.jp_joint3,s_piezas[11]);
		jp_cardmain.add(v.jp_joint4,s_piezas[12]);
		jp_cardmain.add(v.jp_joint5,s_piezas[13]);
		jp_cardmain.add(v.jp_joint6,s_piezas[14]);
		
		
		
		jp_cardmain.add(v.jp_link_extend,s_piezas[15]);
		jp_cardmain.add(v.jp_link_extend_2,s_piezas[16]);
		jp_cardmain.add(v.jp_link_con_caja,s_piezas[17]);
		jp_cardmain.add(v.jp_link_con_caja_2,s_piezas[18]);
		
		
		jp_cardmain.add(v.jp_hand,s_piezas[19]);
		jp_cardmain.add(v.jp_hand2,s_piezas[20]);
		jp_cardmain.add(v.jp_riel,s_piezas[21]);
		jp_cardmain.add(v.jp_translacion,s_piezas[22]);
		jp_cardmain.add(v.jp_rotacion_x,s_piezas[23]);
		jp_cardmain.add(v.jp_rotacion_y,s_piezas[24]);
		jp_cardmain.add(v.jp_rotacion_z,s_piezas[25]);
		jp_cardmain.add(v.jp_escalamiento,s_piezas[26]);
		jp_cardmain.add(v.jp_rotar_vector,s_piezas[27]);
	
		
		//adhiere los elementos al panel interno up
		adherirAGridBag(jp_interno_up,jc_combo_piezas,0,0,1,1,1.0,0.01,true);
		adherirAGridBag(jp_interno_up,jl_show_picture,0,1,1,1,1.0,0.09,true);
		adherirAGridBag(jp_interno_up,jp_cardmain,    0,2,1,1,1.0,0.9,true);	

		
		//propiedades del universo
		jb_eliminar = new JButton("Eliminar Componente");
		jb_eliminar.addActionListener(this);
		adherirAGridBag(jp_interno_bot,jb_eliminar,0,0,1,1,1.0,0.33,true);
		
		
		
		jtb_ejes = new JToggleButton("Ver Ejes");
		jtb_ejes.addActionListener(this);
		adherirAGridBag(jp_interno_bot,jtb_ejes,0,1,1,1,1.0,0.33,true);
		
		jb_reset = new JButton("Reset");
		jb_reset.addActionListener(this);
		adherirAGridBag(jp_interno_bot,jb_reset,0,2,1,1,1.0,0.33,true);
	
				
	
//adhiere panel up y panel bot al panel editar 
		adherirAGridBag(panel,jp_interno_up ,0,0,1,1,1.0,0.9,true);
		adherirAGridBag(panel,jp_interno_bot,0,1,1,1,1.0,0.1,true);

		
	}





	Componente crearPieza(RobotInfo ri)
	{
		
		
		switch(ri.identificador)
		
		{
			
		case v.BASE_CUADRADA_1DOF: 	
				{	
					comp = new BaseCuadrada1DoF(ri.datos);
				}
				break;
				
		case v.BASE_WEDGE: 	
		{	
			comp = new BaseWedge(ri.datos);
		}
		
 		break;				
			
		case v.BASE_WEDGE_ROTOR: 	
		{	
			comp = new BaseWedgeRotor(ri.datos);
		}
		
 		break;				
			

						
					case v.BASE_CIRCULAR: 	
		{	
			comp = new BaseCircular(ri.datos);
		}
		
 		break;				
	
		case v.LINK: 	
		{	
			comp = new Link(ri.datos);
		}
		break;				

		
		
		case v.LINK_2: 	
		{	
		comp = new Link2(ri.datos);
		}
		break;				
		
		case v.LINK_3: 	
		{	
		comp = new Link3(ri.datos);
		}
		break;				
		
		case v.LINK_EXTENSOR: 	
		{	
		comp = new LinkExtensor(ri.datos);
		}
		break;				
		case v.LINK_EXTENSOR2: 	
		{	
		comp = new LinkExtensor2(ri.datos);
		}
		break;				
		
		case v.LINK_CON_CAJA: 	
		{	
		comp = new Link_Con_Caja(ri.datos);
		}
		break;				
		
		case v.LINK_CON_CAJA_2: 	
		{	
		comp = new Link_Con_Caja_2(ri.datos);
		}
		break;				
		

		
		case v.LINK_CILINDRICO: 	
		{	
		comp = new LinkCilindrico(ri.datos);
		}
		break;	
		case v.JOINT_2: 	
		{	
		comp = new Joint2(ri.datos);
		}
		break;				
		case v.JOINT: 	
		{	
		comp = new Joint(ri.datos);
		}
		break;				
			case v.JOINT_3: 	
		{	
		comp = new Joint3(ri.datos);
		}
		break;	
			case v.JOINT_4: 	
		{	
		comp = new Joint4(ri.datos);
		}
		break;	
			case v.JOINT_5: 	
		{	
		comp = new Joint5(ri.datos);
		}
		break;	
			case v.JOINT_6: 	
		{	
		comp = new Joint6(ri.datos);
		}
		break;	
			case v.HAND: 	
		{	
		comp = new Hand(ri.datos);
		}
		break;	
		
		case v.HAND_2: 	
		{	
		comp = new Hand2(ri.datos);
		}
		break;	
		
		case v.RIEL: 	
		{	
		comp = new Riel(ri.datos);
		}
		break;	
		case v.TRANSLACION: 	
		{	
		comp = new Translacion(ri.datos);
		}
		break;	
				
		case v.ROTACION_X: 	
		{	
		comp = new RotacionX(ri.datos);
		}
		break;	
		
				case v.ROTACION_Y: 	
		{	
		comp = new RotacionY(ri.datos);
		}
		break;	
		case v.ROTACION_Z: 	
		{	
		comp = new RotacionZ(ri.datos);
		}
		break;	
		case v.ESCALAMIENTO: 	
		{	
		comp = new Escalamiento(ri.datos);
		}	
		break;	

		case v.ROTAR_VECTOR: 	
		{	
		comp = new RotarVector(ri.datos);
		}
		break;	

		
		
		

	}


		return comp;
		
	}


	void crearPanelBaseCuadrada1DoF(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_bc1dof_x = new JSlider (JSlider.HORIZONTAL,0,1000,v.bc1dof_vi_x);
		
 		v.f_bc1dof_x  =(float) (v.js_bc1dof_x.getValue()/100f);
 		v.jl_bc1dof_x = new JLabel(Float.toString(v.f_bc1dof_x));

 		v.js_bc1dof_x.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_bc1dof_x =(float)v.js_bc1dof_x.getValue()/100;
		v.jl_bc1dof_x.setText(String.valueOf(v.f_bc1dof_x));
		}});
		

		v.js_bc1dof_y = new JSlider (JSlider.HORIZONTAL,0,1000,v.bc1dof_vi_y);
 		v.f_bc1dof_y  =(float) v.js_bc1dof_y.getValue()/100;
 		v.jl_bc1dof_y = new JLabel(String.valueOf(v.f_bc1dof_y));
 		
 		v.js_bc1dof_y.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_bc1dof_y =(float)v.js_bc1dof_y.getValue()/100;
		v.jl_bc1dof_y.setText(String.valueOf(v.f_bc1dof_y));
		
		}});
		
		v.js_bc1dof_z = new JSlider (JSlider.HORIZONTAL,0,1000,v.bc1dof_vi_z);
 		v.f_bc1dof_z  =(float) v.js_bc1dof_z.getValue()/100;
 		v.jl_bc1dof_z = new JLabel(String.valueOf(v.f_bc1dof_z));
 		
 		v.js_bc1dof_z.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_bc1dof_z =(float)v.js_bc1dof_z.getValue()/100;
		v.jl_bc1dof_z.setText(String.valueOf(v.f_bc1dof_z));
		
		}});
		
		
		v.js_bc1dof_h = new JSlider (JSlider.HORIZONTAL,0,1000,v.bc1dof_vi_h);
 		v.f_bc1dof_h  =(float) v.js_bc1dof_h.getValue()/100;
 		v.jl_bc1dof_h = new JLabel(String.valueOf(v.f_bc1dof_h));
 		
 		v.js_bc1dof_h.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_bc1dof_h =(float)v.js_bc1dof_h.getValue()/100;
		v.jl_bc1dof_h.setText(String.valueOf(v.f_bc1dof_h));
		
		}});
		
		v.js_bc1dof_r = new JSlider (JSlider.HORIZONTAL,0,1000,v.bc1dof_vi_r);
 		v.f_bc1dof_r  =(float) v.js_bc1dof_r.getValue()/100;
 		v.jl_bc1dof_r = new JLabel(String.valueOf(v.f_bc1dof_r));
 		
 		v.js_bc1dof_r.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_bc1dof_r =(float)v.js_bc1dof_r.getValue()/100;
		v.jl_bc1dof_r.setText(String.valueOf(v.f_bc1dof_r));
		
		}});
		

		
		
		v.jb_bc1dof_insert = new JButton("Insertar Componente");
		v.jb_bc1dof_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					
					
					String s = v.jtf_bc1dof_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
								v.bc1dof = new BaseCuadrada1DoF(v.f_bc1dof_x,v.f_bc1dof_y,v.f_bc1dof_z,v.f_bc1dof_r,v.f_bc1dof_h,v.jtf_bc1dof_nombre.getText());
								p(v.jtf_bc1dof_nombre.getText());
								robot.adherirPieza(v.bc1dof,indice_robot,v.BASE_CUADRADA_1DOF);
								cambios=true;
							}
						}
					
					
					
					
					
					
					
					
				}	
				
		  }	
			
			
			);
		
		adherirAGridBag(panel,v.jl_x_bc       ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_bc1dof_x,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_bc1dof_x,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_y_bc       ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_bc1dof_y,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_bc1dof_y,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_z_bc       ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_bc1dof_z,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_bc1dof_z,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_r_bc       ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_bc1dof_r		,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_bc1dof_r		,2,3,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_h_bc       ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_bc1dof_h,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_bc1dof_h,2,4,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_nombre				  ,0,5,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_bc1dof_nombre ,0,6,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_bc1dof_insert  ,0,7,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()			  ,0,8,3,1,1.0,0.2,true);
		



		
	}




		void crearPanelBaseWedge(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_basewedge_x = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_basewedge_x);
		
 		v.f_basewedge_x  =(float) (v.js_basewedge_x.getValue()/100f);
 		v.jl_basewedge_x = new JLabel(Float.toString(v.f_basewedge_x));
 		
 		v.js_basewedge_x.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_basewedge_x =(float)v.js_basewedge_x.getValue()/100;
		v.jl_basewedge_x.setText(String.valueOf(v.f_basewedge_x));
		}});
		



		
		v.js_basewedge_x2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_basewedge_x2);
 		v.f_basewedge_x2  =(float) v.js_basewedge_x2.getValue()/100;
 		v.jl_basewedge_x2 = new JLabel(String.valueOf(v.f_basewedge_x2));
 		
 		v.js_basewedge_x2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_basewedge_x2 =(float)v.js_basewedge_x2.getValue()/100;
		v.jl_basewedge_x2.setText(String.valueOf(v.f_basewedge_x2));
		
		}});




		v.js_basewedge_y = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_basewedge_y);
 		v.f_basewedge_y  =(float) v.js_basewedge_y.getValue()/100;
 		v.jl_basewedge_y = new JLabel(String.valueOf(v.f_basewedge_y));
 		
 		v.js_basewedge_y.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_basewedge_y =(float)v.js_basewedge_y.getValue()/100;
		v.jl_basewedge_y.setText(String.valueOf(v.f_basewedge_y));
		
		}});
		
	
	
		v.js_basewedge_z = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_basewedge_z);
 		v.f_basewedge_z  =(float) v.js_basewedge_z.getValue()/100;
 		v.jl_basewedge_z = new JLabel(String.valueOf(v.f_basewedge_z));
 		
 		v.js_basewedge_z.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_basewedge_z =(float)v.js_basewedge_z.getValue()/100;
		v.jl_basewedge_z.setText(String.valueOf(v.f_basewedge_z));
		
		}});
		
		
	
		v.js_basewedge_o = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_basewedge_o);
 		v.f_basewedge_o  =(float) v.js_basewedge_o.getValue()/100;
 		v.jl_basewedge_o = new JLabel(String.valueOf(v.f_basewedge_o));
 		
 		v.js_basewedge_o.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_basewedge_o =(float)v.js_basewedge_o.getValue()/100;
		v.jl_basewedge_o.setText(String.valueOf(v.f_basewedge_o));
		
		}});
		

		
		
		v.jb_basewedge_insert = new JButton("Insertar Componente");
		v.jb_basewedge_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.basewedge = new BaseWedge(v.f_basewedge_x,v.f_basewedge_x2,v.f_basewedge_y,v.f_basewedge_z,v.f_basewedge_o,"Base Wedge");
					p("Base Wedge");
					robot.adherirPieza(v.basewedge,indice_robot,v.BASE_WEDGE);
  				cambios=true;

				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_x_hi_bw       ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_basewedge_x,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_basewedge_x,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_x_lo_bw       ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_basewedge_x2,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_basewedge_x2,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_y_bw       ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_basewedge_y,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_basewedge_y,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_z_bw       ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_basewedge_z,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_basewedge_z,2,3,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_offset_bw  ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_basewedge_o,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_basewedge_o,2,4,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jb_basewedge_insert  ,0,5,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()			  ,0,6,3,1,1.0,0.2,true);
		



		
	}

		

		void crearPanelBaseWedgeRotor(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_base_wedge_rotor_x = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_wedge_rotor_x);
		
 		v.f_base_wedge_rotor_x  =(float) (v.js_base_wedge_rotor_x.getValue()/100f);
 		v.jl_base_wedge_rotor_x = new JLabel(Float.toString(v.f_base_wedge_rotor_x));
 		
 		v.js_base_wedge_rotor_x.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_wedge_rotor_x =(float)v.js_base_wedge_rotor_x.getValue()/100;
		v.jl_base_wedge_rotor_x.setText(String.valueOf(v.f_base_wedge_rotor_x));
		}});
		



		
		v.js_base_wedge_rotor_x2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_wedge_rotor_x2);
 		v.f_base_wedge_rotor_x2  =(float) v.js_base_wedge_rotor_x2.getValue()/100;
 		v.jl_base_wedge_rotor_x2 = new JLabel(String.valueOf(v.f_base_wedge_rotor_x2));
 		
 		v.js_base_wedge_rotor_x2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_wedge_rotor_x2 =(float)v.js_base_wedge_rotor_x2.getValue()/100;
		v.jl_base_wedge_rotor_x2.setText(String.valueOf(v.f_base_wedge_rotor_x2));
		
		}});


		v.js_base_wedge_rotor_y = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_wedge_rotor_y);
 		v.f_base_wedge_rotor_y  =(float) v.js_base_wedge_rotor_y.getValue()/100;
 		v.jl_base_wedge_rotor_y = new JLabel(String.valueOf(v.f_base_wedge_rotor_y));
 		
 		v.js_base_wedge_rotor_y.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_wedge_rotor_y =(float)v.js_base_wedge_rotor_y.getValue()/100;
		v.jl_base_wedge_rotor_y.setText(String.valueOf(v.f_base_wedge_rotor_y));
		
		}});
		
	
	
	
		v.js_base_wedge_rotor_b_y = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_wedge_rotor_b_y);
 		v.f_base_wedge_rotor_b_y  =(float) v.js_base_wedge_rotor_b_y.getValue()/100;
 		v.jl_base_wedge_rotor_b_y = new JLabel(String.valueOf(v.f_base_wedge_rotor_b_y));
 		
 		v.js_base_wedge_rotor_b_y.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_wedge_rotor_b_y =(float)v.js_base_wedge_rotor_b_y.getValue()/100;
		v.jl_base_wedge_rotor_b_y.setText(String.valueOf(v.f_base_wedge_rotor_b_y));
		
		}});
		
	

	
		v.js_base_wedge_rotor_z = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_wedge_rotor_z);
 		v.f_base_wedge_rotor_z  =(float) v.js_base_wedge_rotor_z.getValue()/100;
 		v.jl_base_wedge_rotor_z = new JLabel(String.valueOf(v.f_base_wedge_rotor_z));
 		
 		v.js_base_wedge_rotor_z.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_wedge_rotor_z =(float)v.js_base_wedge_rotor_z.getValue()/100;
		v.jl_base_wedge_rotor_z.setText(String.valueOf(v.f_base_wedge_rotor_z));
		
		}});
		
		
	
		v.js_base_wedge_rotor_o = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_wedge_rotor_o);
 		v.f_base_wedge_rotor_o  =(float) v.js_base_wedge_rotor_o.getValue()/100;
 		v.jl_base_wedge_rotor_o = new JLabel(String.valueOf(v.f_base_wedge_rotor_o));
 		
 		v.js_base_wedge_rotor_o.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_wedge_rotor_o =(float)v.js_base_wedge_rotor_o.getValue()/100;
		v.jl_base_wedge_rotor_o.setText(String.valueOf(v.f_base_wedge_rotor_o));
		
		}});
		

		
		
		v.jb_base_wedge_rotor_insert = new JButton("Insertar Componente");
		v.jb_base_wedge_rotor_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					String s = v.jtf_base_wedge_rotor_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
				
								v.base_wedge_rotor = new BaseWedgeRotor(v.f_base_wedge_rotor_x,v.f_base_wedge_rotor_x2,v.f_base_wedge_rotor_y,v.f_base_wedge_rotor_b_y,v.f_base_wedge_rotor_z,v.f_base_wedge_rotor_o,v.jtf_base_wedge_rotor_nombre.getText());
								p(v.jtf_base_wedge_rotor_nombre.getText());
								robot.adherirPieza(v.base_wedge_rotor,indice_robot,v.BASE_WEDGE_ROTOR);
								cambios=true;


							}
						}
					
				
				
				
				
				
				
				
				
				}	
				
			}
			
			
			);
			
			
	
			
		
		adherirAGridBag(panel,v.jl_x_hi_bwr       					 ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_wedge_rotor_x,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_wedge_rotor_x,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_x_lo_bwr       						,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_wedge_rotor_x2,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_wedge_rotor_x2,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_y_bwr     					   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_wedge_rotor_y,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_wedge_rotor_y,2,2,1,1,0.1,0.1,true);
		
		
		adherirAGridBag(panel,v.jl_y_b_bwr       						 ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_wedge_rotor_b_y,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_wedge_rotor_b_y,2,3,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_z_bwr       					 ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_wedge_rotor_z,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_wedge_rotor_z,2,4,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_offset_bwr,0,5,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_wedge_rotor_o,1,5,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_wedge_rotor_o,2,5,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_nombre				  					,0,6,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_base_wedge_rotor_nombre ,0,7,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_base_wedge_rotor_insert  ,0,8,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()			  					,0,9,3,1,1.0,0.1,true);
		



		
	}


	//------------------------------------------------//
	
	


		void crearPanelBaseCircular(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_base_circular_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_circular_valor_1);
		
 		v.f_base_circular_valor_1  =(float) (v.js_base_circular_valor_1.getValue()/100f);
 		v.jl_base_circular_valor_1 = new JLabel(Float.toString(v.f_base_circular_valor_1));
 		
 		v.js_base_circular_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_circular_valor_1 =(float)v.js_base_circular_valor_1.getValue()/100;
		v.jl_base_circular_valor_1.setText(String.valueOf(v.f_base_circular_valor_1));
		}});
		



		
		v.js_base_circular_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_circular_valor_2);
 		v.f_base_circular_valor_2  =(float) v.js_base_circular_valor_2.getValue()/100;
 		v.jl_base_circular_valor_2 = new JLabel(String.valueOf(v.f_base_circular_valor_2));
 		
 		v.js_base_circular_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_circular_valor_2 =(float)v.js_base_circular_valor_2.getValue()/100;
		v.jl_base_circular_valor_2.setText(String.valueOf(v.f_base_circular_valor_2));
		
		}});











		v.js_base_circular_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_circular_valor_3);
 		v.f_base_circular_valor_3  =(float) v.js_base_circular_valor_3.getValue()/100;
 		v.jl_base_circular_valor_3 = new JLabel(String.valueOf(v.f_base_circular_valor_3));
 		
 		v.js_base_circular_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_circular_valor_3 =(float)v.js_base_circular_valor_3.getValue()/100;
		v.jl_base_circular_valor_3.setText(String.valueOf(v.f_base_circular_valor_3));
		
		}});
		
	
	
	
		v.js_base_circular_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_circular_valor_4);
 		v.f_base_circular_valor_4  =(float) v.js_base_circular_valor_4.getValue()/100;
 		v.jl_base_circular_valor_4 = new JLabel(String.valueOf(v.f_base_circular_valor_4));
 		
 		v.js_base_circular_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_circular_valor_4 =(float)v.js_base_circular_valor_4.getValue()/100;
		v.jl_base_circular_valor_4.setText(String.valueOf(v.f_base_circular_valor_4));
		
		}});
		
	

	
		v.js_base_circular_valor_5 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_circular_valor_5);
 		v.f_base_circular_valor_5  =(float) v.js_base_circular_valor_5.getValue()/100;
 		v.jl_base_circular_valor_5 = new JLabel(String.valueOf(v.f_base_circular_valor_5));
 		
 		v.js_base_circular_valor_5.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_circular_valor_5 =(float)v.js_base_circular_valor_5.getValue()/100;
		v.jl_base_circular_valor_5.setText(String.valueOf(v.f_base_circular_valor_5));
		
		}});
		
		
	
		v.js_base_circular_valor_6 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_circular_valor_6);
 		v.f_base_circular_valor_6  =(float) v.js_base_circular_valor_6.getValue()/100;
 		v.jl_base_circular_valor_6 = new JLabel(String.valueOf(v.f_base_circular_valor_6));
 		
 		v.js_base_circular_valor_6.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_circular_valor_6 =(float)v.js_base_circular_valor_6.getValue()/100;
		v.jl_base_circular_valor_6.setText(String.valueOf(v.f_base_circular_valor_6));
		
		}});
		
		
		
		v.js_base_circular_valor_7= new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_base_circular_valor_7);
 		v.f_base_circular_valor_7 =(float) v.js_base_circular_valor_7.getValue()/100;
 		v.jl_base_circular_valor_7= new JLabel(String.valueOf(v.f_base_circular_valor_7));
 		
 		v.js_base_circular_valor_7.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_base_circular_valor_7=(float)v.js_base_circular_valor_7.getValue()/100;
		v.jl_base_circular_valor_7.setText(String.valueOf(v.f_base_circular_valor_7));
		
		}});
		

		
		
		v.jb_base_circular_insert = new JButton("Insertar Componente");
		v.jb_base_circular_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
						String s = v.jtf_base_circular_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
								v.base_circular = new BaseCircular(v.f_base_circular_valor_1,v.f_base_circular_valor_2,v.f_base_circular_valor_3,v.f_base_circular_valor_4,v.f_base_circular_valor_5,v.f_base_circular_valor_6,v.f_base_circular_valor_7,v.jtf_base_circular_nombre.getText());
								p(v.jtf_base_circular_nombre.getText());
								robot.adherirPieza(v.base_circular,indice_robot,v.BASE_CIRCULAR);
								cambios=true;
								


							}
						}
					
				
				
				
				
				
				
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_base_circular_var_1  ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_circular_valor_1,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_circular_valor_1,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_base_circular_var_2  ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_circular_valor_2,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_circular_valor_2,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_base_circular_var_3  ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_circular_valor_3,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_circular_valor_3,2,2,1,1,0.1,0.1,true);
		
		
		adherirAGridBag(panel,v.jl_base_circular_var_4  ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_circular_valor_4,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_circular_valor_4,2,3,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_base_circular_var_5  ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_circular_valor_5,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_circular_valor_5,2,4,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_base_circular_var_6  ,0,5,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_circular_valor_6,1,5,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_circular_valor_6,2,5,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_base_circular_var_7  ,0,6,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_base_circular_valor_7,1,6,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_base_circular_valor_7,2,6,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_nombre							   ,0,7,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_base_circular_nombre ,0,8,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_base_circular_insert  ,0,9,3,1,1.0,0.1,true);
		



		
	}


	
		void crearPanelLink(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_link_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_valor_1);
		
 		v.f_link_valor_1  =(float) (v.js_link_valor_1.getValue()/100f);
 		v.jl_link_valor_1 = new JLabel(Float.toString(v.f_link_valor_1));
 		
 		v.js_link_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_valor_1 =(float)v.js_link_valor_1.getValue()/100;
		v.jl_link_valor_1.setText(String.valueOf(v.f_link_valor_1));
		}});

	
	
	
		v.js_link_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_valor_2);
 		v.f_link_valor_2  =(float) v.js_link_valor_2.getValue()/100;
 		v.jl_link_valor_2 = new JLabel(String.valueOf(v.f_link_valor_2));
 		
 		v.js_link_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_valor_2 =(float)v.js_link_valor_2.getValue()/100;
		v.jl_link_valor_2.setText(String.valueOf(v.f_link_valor_2));
		
		}});

		v.js_link_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_valor_3);
 		v.f_link_valor_3  =(float) v.js_link_valor_3.getValue()/100;
 		v.jl_link_valor_3 = new JLabel(String.valueOf(v.f_link_valor_3));
 		
 		v.js_link_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_valor_3 =(float)v.js_link_valor_3.getValue()/100;
		v.jl_link_valor_3.setText(String.valueOf(v.f_link_valor_3));
		
		}});
		

		v.jb_link_insert = new JButton("Insertar Componente");
		v.jb_link_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.link = new Link(v.f_link_valor_1,v.f_link_valor_2,v.f_link_valor_3,"Link");
					p("Link");
					robot.adherirPieza(v.link,indice_robot,v.LINK);
					indice_robot++;
													cambios=true;

				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_link_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_link_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jb_link_insert  ,0,3,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()			,0,4,3,1,1.0,0.4,true);




		
	}

		void crearPanelLink2(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_link2_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link2_valor_1);
		
 		v.f_link2_valor_1  =(float) (v.js_link2_valor_1.getValue()/100f);
 		v.jl_link2_valor_1 = new JLabel(Float.toString(v.f_link2_valor_1));
 		
 		v.js_link2_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link2_valor_1 =(float)v.js_link2_valor_1.getValue()/100;
		v.jl_link2_valor_1.setText(String.valueOf(v.f_link2_valor_1));
		}});

		v.js_link2_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link2_valor_2);
 		v.f_link2_valor_2  =(float) v.js_link2_valor_2.getValue()/100;
 		v.jl_link2_valor_2 = new JLabel(String.valueOf(v.f_link2_valor_2));
 		
 		v.js_link2_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link2_valor_2 =(float)v.js_link2_valor_2.getValue()/100;
		v.jl_link2_valor_2.setText(String.valueOf(v.f_link2_valor_2));
		
		}});

		v.js_link2_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link2_valor_3);
 		v.f_link2_valor_3  =(float) v.js_link2_valor_3.getValue()/100;
 		v.jl_link2_valor_3 = new JLabel(String.valueOf(v.f_link2_valor_3));
 		
 		v.js_link2_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link2_valor_3 =(float)v.js_link2_valor_3.getValue()/100;
		v.jl_link2_valor_3.setText(String.valueOf(v.f_link2_valor_3));
		
		}});
		
		v.js_link2_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link2_valor_4);
 		v.f_link2_valor_4  =(float) v.js_link2_valor_4.getValue()/100;
 		v.jl_link2_valor_4 = new JLabel(String.valueOf(v.f_link2_valor_4));
 		
 		v.js_link2_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link2_valor_4 =(float)v.js_link2_valor_4.getValue()/100;
		v.jl_link2_valor_4.setText(String.valueOf(v.f_link2_valor_4));
		
		}});

		v.jb_link2_insert = new JButton("Insertar Componente");
		v.jb_link2_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.link2 = new Link2(v.f_link2_valor_1,v.f_link2_valor_2,v.f_link2_valor_3,v.f_link2_valor_4,"Link2");
					p("Link2");
					robot.adherirPieza(v.link2,indice_robot,v.LINK_2);
			//		indice_robot++;
													cambios=true;

				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_link2_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link2_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link2_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link2_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link2_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link2_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_link2_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link2_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link2_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link2_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link2_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link2_valor_4 ,2,3,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jb_link2_insert  ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()			,0,5,3,1,1.0,0.3,true);




		
	}


		void crearPanelLink3(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_link3_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link3_valor_1);
		
 		v.f_link3_valor_1  =(float) (v.js_link3_valor_1.getValue()/100f);
 		v.jl_link3_valor_1 = new JLabel(Float.toString(v.f_link3_valor_1));
 		
 		v.js_link3_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link3_valor_1 =(float)v.js_link3_valor_1.getValue()/100;
		v.jl_link3_valor_1.setText(String.valueOf(v.f_link3_valor_1));
		}});

		v.js_link3_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link3_valor_2);
 		v.f_link3_valor_2  =(float) v.js_link3_valor_2.getValue()/100;
 		v.jl_link3_valor_2 = new JLabel(String.valueOf(v.f_link3_valor_2));
 		
 		v.js_link3_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link3_valor_2 =(float)v.js_link3_valor_2.getValue()/100;
		v.jl_link3_valor_2.setText(String.valueOf(v.f_link3_valor_2));
		
		}});

		v.js_link3_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link3_valor_3);
 		v.f_link3_valor_3  =(float) v.js_link3_valor_3.getValue()/100;
 		v.jl_link3_valor_3 = new JLabel(String.valueOf(v.f_link3_valor_3));
 		
 		v.js_link3_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link3_valor_3 =(float)v.js_link3_valor_3.getValue()/100;
		v.jl_link3_valor_3.setText(String.valueOf(v.f_link3_valor_3));
		
		}});
		
		v.js_link3_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link3_valor_4);
 		v.f_link3_valor_4  =(float) v.js_link3_valor_4.getValue()/100;
 		v.jl_link3_valor_4 = new JLabel(String.valueOf(v.f_link3_valor_4));
 		
 		v.js_link3_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link3_valor_4 =(float)v.js_link3_valor_4.getValue()/100;
		v.jl_link3_valor_4.setText(String.valueOf(v.f_link3_valor_4));
		
		}});

		v.jb_link3_insert = new JButton("Insertar Componente");
		v.jb_link3_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.link3 = new Link3(v.f_link3_valor_1,v.f_link3_valor_2,v.f_link3_valor_3,v.f_link3_valor_4,"Link3");
					p("Link3");
					robot.adherirPieza(v.link3,indice_robot,v.LINK_3);
					indice_robot++;
					cambios=true;
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_link3_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link3_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link3_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link3_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link3_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link3_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_link3_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link3_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link3_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link3_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link3_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link3_valor_4 ,2,3,1,1,0.1,0.1,true);
		

		adherirAGridBag(panel,v.jb_link3_insert  ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()			,0,5,3,1,1.0,0.3,true);




		
	}


		void crearPanelLinkExtend(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_link_extend_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_extend_valor_1);
		
 		v.f_link_extend_valor_1  =(float) (v.js_link_extend_valor_1.getValue()/100f);
 		v.jl_link_extend_valor_1 = new JLabel(Float.toString(v.f_link_extend_valor_1));
 		
 		v.js_link_extend_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_extend_valor_1 =(float)v.js_link_extend_valor_1.getValue()/100;
		v.jl_link_extend_valor_1.setText(String.valueOf(v.f_link_extend_valor_1));
		}});

		v.js_link_extend_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_extend_valor_2);
 		v.f_link_extend_valor_2  =(float) v.js_link_extend_valor_2.getValue()/100;
 		v.jl_link_extend_valor_2 = new JLabel(String.valueOf(v.f_link_extend_valor_2));
 		
 		v.js_link_extend_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_extend_valor_2 =(float)v.js_link_extend_valor_2.getValue()/100;
		v.jl_link_extend_valor_2.setText(String.valueOf(v.f_link_extend_valor_2));
		
		}});

		v.js_link_extend_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_extend_valor_3);
 		v.f_link_extend_valor_3  =(float) v.js_link_extend_valor_3.getValue()/100;
 		v.jl_link_extend_valor_3 = new JLabel(String.valueOf(v.f_link_extend_valor_3));
 		
 		v.js_link_extend_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_extend_valor_3 =(float)v.js_link_extend_valor_3.getValue()/100;
		v.jl_link_extend_valor_3.setText(String.valueOf(v.f_link_extend_valor_3));
		
		}});
		

		v.jb_link_extend_insert = new JButton("Insertar Componente");
		v.jb_link_extend_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
				String s = v.jtf_link_extend_nombre.getText();
						
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{

					v.link_extend = new LinkExtensor(v.f_link_extend_valor_1,v.f_link_extend_valor_2,v.f_link_extend_valor_3,v.jtf_link_extend_nombre.getText());
					p(v.jtf_link_extend_nombre.getText());
					robot.adherirPieza(v.link_extend,indice_robot,v.LINK_EXTENSOR);
	

								cambios=true;
							}
						}	
							
						
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_link_extend_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_extend_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_extend_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link_extend_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_extend_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_extend_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_link_extend_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_extend_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_extend_valor_3 ,2,2,1,1,0.1,0.1,true);
		

		adherirAGridBag(panel,v.jl_nombre		 				   ,0,3,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_link_extend_nombre ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_link_extend_insert  ,0,5,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()					   ,0,6,3,1,1.0,0.3,true);




		
	}
		void crearPanelLinkCilindrico(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_link_cilindrico_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_cilindrico_valor_1);
		
 		v.f_link_cilindrico_valor_1  =(float) (v.js_link_cilindrico_valor_1.getValue()/100f);
 		v.jl_link_cilindrico_valor_1 = new JLabel(Float.toString(v.f_link_cilindrico_valor_1));
 		
 		v.js_link_cilindrico_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_cilindrico_valor_1 =(float)v.js_link_cilindrico_valor_1.getValue()/100;
		v.jl_link_cilindrico_valor_1.setText(String.valueOf(v.f_link_cilindrico_valor_1));
		}});

		v.js_link_cilindrico_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_cilindrico_valor_2);
 		v.f_link_cilindrico_valor_2  =(float) v.js_link_cilindrico_valor_2.getValue()/100;
 		v.jl_link_cilindrico_valor_2 = new JLabel(String.valueOf(v.f_link_cilindrico_valor_2));
 		
 		v.js_link_cilindrico_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_cilindrico_valor_2 =(float)v.js_link_cilindrico_valor_2.getValue()/100;
		v.jl_link_cilindrico_valor_2.setText(String.valueOf(v.f_link_cilindrico_valor_2));
		
		}});

		v.js_link_cilindrico_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_cilindrico_valor_3);
 		v.f_link_cilindrico_valor_3  =(float) v.js_link_cilindrico_valor_3.getValue()/100;
 		v.jl_link_cilindrico_valor_3 = new JLabel(String.valueOf(v.f_link_cilindrico_valor_3));
 		
 		v.js_link_cilindrico_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_cilindrico_valor_3 =(float)v.js_link_cilindrico_valor_3.getValue()/100;
		v.jl_link_cilindrico_valor_3.setText(String.valueOf(v.f_link_cilindrico_valor_3));
		
		}});
		

		v.jb_link_cilindrico_insert = new JButton("Insertar Componente");
		v.jb_link_cilindrico_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.link_cilindrico = new LinkCilindrico(v.f_link_cilindrico_valor_1,v.f_link_cilindrico_valor_2,v.f_link_cilindrico_valor_3,"Link4");
					p("Link4");
					robot.adherirPieza(v.link_cilindrico,indice_robot,v.LINK_CILINDRICO);
										cambios=true;
			indice_robot++;
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_link_cilindrico_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_cilindrico_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_cilindrico_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link_cilindrico_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_cilindrico_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_cilindrico_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_link_cilindrico_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_cilindrico_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_cilindrico_valor_3 ,2,2,1,1,0.1,0.1,true);
		

//		adherirAGridBag(panel,v.jl_nombre		 				   ,0,3,3,1,1.0,0.1,true);
//		adherirAGridBag(panel,v.jtf_link_cilindrico_nombre ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_link_cilindrico_insert  ,0,3,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()					   ,0,4,3,1,1.0,0.3,true);




		
	}

		void crearPanelJoint5(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_joint5_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint5_valor_1);
		
 		v.f_joint5_valor_1  =(float) (v.js_joint5_valor_1.getValue()/100f);
 		v.jl_joint5_valor_1 = new JLabel(Float.toString(v.f_joint5_valor_1));
 		
 		v.js_joint5_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint5_valor_1 =(float)v.js_joint5_valor_1.getValue()/100;
		v.jl_joint5_valor_1.setText(String.valueOf(v.f_joint5_valor_1));
		}});

		v.js_joint5_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint5_valor_2);
 		v.f_joint5_valor_2  =(float) v.js_joint5_valor_2.getValue()/100;
 		v.jl_joint5_valor_2 = new JLabel(String.valueOf(v.f_joint5_valor_2));
 		
 		v.js_joint5_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint5_valor_2 =(float)v.js_joint5_valor_2.getValue()/100;
		v.jl_joint5_valor_2.setText(String.valueOf(v.f_joint5_valor_2));
		
		}});

		v.js_joint5_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint5_valor_3);
 		v.f_joint5_valor_3  =(float) v.js_joint5_valor_3.getValue()/100;
 		v.jl_joint5_valor_3 = new JLabel(String.valueOf(v.f_joint5_valor_3));
 		
 		v.js_joint5_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint5_valor_3 =(float)v.js_joint5_valor_3.getValue()/100;
		v.jl_joint5_valor_3.setText(String.valueOf(v.f_joint5_valor_3));
		
		}});
		

		v.jb_joint5_insert = new JButton("Insertar Componente");
		v.jb_joint5_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
				String s = v.jtf_joint5_nombre.getText();
						
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{

								v.joint5 = new Joint5(v.f_joint5_valor_1,v.f_joint5_valor_2,v.f_joint5_valor_3,v.jtf_joint5_nombre.getText());
								p(v.jtf_joint5_nombre.getText());
								robot.adherirPieza(v.joint5,indice_robot,v.JOINT_5);
								indice_robot++;



								cambios=true;
							}
						}	
							
						
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_joint5_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint5_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint5_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint5_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint5_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint5_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_joint5_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint5_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint5_valor_3 ,2,2,1,1,0.1,0.1,true);
		

		adherirAGridBag(panel,v.jl_nombre		 				   ,0,3,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_joint5_nombre ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_joint5_insert  ,0,5,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()					   ,0,6,3,1,1.0,0.3,true);




		
	}

		void crearPanelJoint6(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_joint6_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint6_valor_1);
		
 		v.f_joint6_valor_1  =(float) (v.js_joint6_valor_1.getValue()/100f);
 		v.jl_joint6_valor_1 = new JLabel(Float.toString(v.f_joint6_valor_1));
 		
 		v.js_joint6_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint6_valor_1 =(float)v.js_joint6_valor_1.getValue()/100;
		v.jl_joint6_valor_1.setText(String.valueOf(v.f_joint6_valor_1));
		}});

		v.js_joint6_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint6_valor_2);
 		v.f_joint6_valor_2  =(float) v.js_joint6_valor_2.getValue()/100;
 		v.jl_joint6_valor_2 = new JLabel(String.valueOf(v.f_joint6_valor_2));
 		
 		v.js_joint6_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint6_valor_2 =(float)v.js_joint6_valor_2.getValue()/100;
		v.jl_joint6_valor_2.setText(String.valueOf(v.f_joint6_valor_2));
		
		}});

		v.js_joint6_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint6_valor_3);
 		v.f_joint6_valor_3  =(float) v.js_joint6_valor_3.getValue()/100;
 		v.jl_joint6_valor_3 = new JLabel(String.valueOf(v.f_joint6_valor_3));
 		
 		v.js_joint6_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint6_valor_3 =(float)v.js_joint6_valor_3.getValue()/100;
		v.jl_joint6_valor_3.setText(String.valueOf(v.f_joint6_valor_3));
		
		}});
		

		v.jb_joint6_insert = new JButton("Insertar Componente");
		v.jb_joint6_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
				String s = v.jtf_joint6_nombre.getText();
						
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
					v.joint6 = new Joint6(v.f_joint6_valor_1,v.f_joint6_valor_2,v.f_joint6_valor_3,v.jtf_joint6_nombre.getText());
					p(v.jtf_joint6_nombre.getText());
					robot.adherirPieza(v.joint6,indice_robot,v.JOINT_6);
					indice_robot++;
		


								cambios=true;
							}
						}	
							
		




					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_joint6_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint6_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint6_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint6_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint6_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint6_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_joint6_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint6_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint6_valor_3 ,2,2,1,1,0.1,0.1,true);
		

		adherirAGridBag(panel,v.jl_nombre		 				   ,0,3,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_joint6_nombre ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_joint6_insert  ,0,5,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()					   ,0,6,3,1,1.0,0.3,true);




		
	}





		void crearPanelLinkExtend2(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_link_extend_2_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_extend_2_valor_1);
		
 		v.f_link_extend_2_valor_1  =(float) (v.js_link_extend_2_valor_1.getValue()/100f);
 		v.jl_link_extend_2_valor_1 = new JLabel(Float.toString(v.f_link_extend_2_valor_1));
 		
 		v.js_link_extend_2_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_extend_2_valor_1 =(float)v.js_link_extend_2_valor_1.getValue()/100;
		v.jl_link_extend_2_valor_1.setText(String.valueOf(v.f_link_extend_2_valor_1));
		}});

		v.js_link_extend_2_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_extend_2_valor_2);
 		v.f_link_extend_2_valor_2  =(float) v.js_link_extend_2_valor_2.getValue()/100;
 		v.jl_link_extend_2_valor_2 = new JLabel(String.valueOf(v.f_link_extend_2_valor_2));
 		
 		v.js_link_extend_2_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_extend_2_valor_2 =(float)v.js_link_extend_2_valor_2.getValue()/100;
		v.jl_link_extend_2_valor_2.setText(String.valueOf(v.f_link_extend_2_valor_2));
		
		}});



		v.jb_link_extend_2_insert = new JButton("Insertar Componente");
		v.jb_link_extend_2_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
				String s = v.jtf_link_extend_2_nombre.getText();
						
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
		
					v.link_extend_2 = new LinkExtensor2(v.f_link_extend_2_valor_1,v.f_link_extend_2_valor_2,v.jtf_link_extend_2_nombre.getText());
					p(v.jtf_link_extend_2_nombre.getText());
					robot.adherirPieza(v.link_extend_2,indice_robot,v.LINK_EXTENSOR2);
					indice_robot++;


								cambios=true;
							}
						}	
							
							
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_link_extend_2_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_extend_2_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_extend_2_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link_extend_2_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_extend_2_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_extend_2_valor_2 ,2,1,1,1,0.1,0.1,true);

		

		adherirAGridBag(panel,v.jl_nombre		 				     ,0,2,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_link_extend_2_nombre ,0,3,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_link_extend_2_insert  ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()					     ,0,5,3,1,1.0,0.7,true);




		
	}

		void crearPanelLinkConCaja(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_link_con_caja_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_valor_1);
 		v.f_link_con_caja_valor_1  =(float) (v.js_link_con_caja_valor_1.getValue()/100f);
 		v.jl_link_con_caja_valor_1 = new JLabel(Float.toString(v.f_link_con_caja_valor_1));
 		v.js_link_con_caja_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_valor_1 =(float)v.js_link_con_caja_valor_1.getValue()/100;
		v.jl_link_con_caja_valor_1.setText(String.valueOf(v.f_link_con_caja_valor_1));
		}});

		v.js_link_con_caja_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_valor_2);
 		v.f_link_con_caja_valor_2  =(float) v.js_link_con_caja_valor_2.getValue()/100;
 		v.jl_link_con_caja_valor_2 = new JLabel(String.valueOf(v.f_link_con_caja_valor_2));
 		v.js_link_con_caja_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_valor_2 =(float)v.js_link_con_caja_valor_2.getValue()/100;
		v.jl_link_con_caja_valor_2.setText(String.valueOf(v.f_link_con_caja_valor_2));
		}});

		v.js_link_con_caja_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_valor_3);
 		v.f_link_con_caja_valor_3  =(float) v.js_link_con_caja_valor_3.getValue()/100;
 		v.jl_link_con_caja_valor_3 = new JLabel(String.valueOf(v.f_link_con_caja_valor_3));
 		v.js_link_con_caja_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_valor_3 =(float)v.js_link_con_caja_valor_3.getValue()/100;
		v.jl_link_con_caja_valor_3.setText(String.valueOf(v.f_link_con_caja_valor_3));
		}});

		v.js_link_con_caja_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_valor_4);
 		v.f_link_con_caja_valor_4  =(float) v.js_link_con_caja_valor_4.getValue()/100;
 		v.jl_link_con_caja_valor_4 = new JLabel(String.valueOf(v.f_link_con_caja_valor_4));
 		v.js_link_con_caja_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_valor_4 =(float)v.js_link_con_caja_valor_4.getValue()/100;
		v.jl_link_con_caja_valor_4.setText(String.valueOf(v.f_link_con_caja_valor_4));
		}});
		
		
		v.js_link_con_caja_valor_5 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_valor_5);
 		v.f_link_con_caja_valor_5  =(float) v.js_link_con_caja_valor_5.getValue()/100;
 		v.jl_link_con_caja_valor_5 = new JLabel(String.valueOf(v.f_link_con_caja_valor_5));
 		v.js_link_con_caja_valor_5.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_valor_5 =(float)v.js_link_con_caja_valor_5.getValue()/100;
		v.jl_link_con_caja_valor_5.setText(String.valueOf(v.f_link_con_caja_valor_5));
		}});
		
		
		v.js_link_con_caja_valor_6 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_valor_6);
 		v.f_link_con_caja_valor_6  =(float) v.js_link_con_caja_valor_6.getValue()/100;
 		v.jl_link_con_caja_valor_6 = new JLabel(String.valueOf(v.f_link_con_caja_valor_6));
 		v.js_link_con_caja_valor_6.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_valor_6 =(float)v.js_link_con_caja_valor_6.getValue()/100;
		v.jl_link_con_caja_valor_6.setText(String.valueOf(v.f_link_con_caja_valor_6));
		}});
		
		

		v.jb_link_con_caja_insert = new JButton("Insertar Componente");
		v.jb_link_con_caja_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
				
			String s = v.jtf_link_con_caja_nombre.getText();
						
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
		
					v.link_con_caja = new Link_Con_Caja(v.f_link_con_caja_valor_1,v.f_link_con_caja_valor_2,v.f_link_con_caja_valor_3,v.f_link_con_caja_valor_4,v.f_link_con_caja_valor_5,v.f_link_con_caja_valor_6,v.jtf_link_con_caja_nombre.getText());
					p(v.jtf_link_con_caja_nombre.getText());
					robot.adherirPieza(v.link_con_caja,indice_robot,v.LINK_CON_CAJA);
					indice_robot++;


								cambios=true;
							}
						}	
							
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				

					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_link_con_caja_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link_con_caja_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_link_con_caja_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link_con_caja_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_valor_4 ,2,3,1,1,0.1,0.1,true);
		
				
		adherirAGridBag(panel,v.jl_link_con_caja_var_5   ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_valor_5 ,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_valor_5 ,2,4,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_link_con_caja_var_6   ,0,5,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_valor_6 ,1,5,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_valor_6 ,2,5,1,1,0.1,0.1,true);


		adherirAGridBag(panel,v.jl_nombre		   					 ,0,6,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_link_con_caja_nombre ,0,7,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_link_con_caja_insert  ,0,8,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()							 ,0,9,3,1,1.0,0.2,true);




		
	}
	
	
			void crearPanelJoint(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_joint_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint_valor_1);
 		v.f_joint_valor_1  =(float) (v.js_joint_valor_1.getValue()/100f);
 		v.jl_joint_valor_1 = new JLabel(Float.toString(v.f_joint_valor_1));
 		v.js_joint_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint_valor_1 =(float)v.js_joint_valor_1.getValue()/100;
		v.jl_joint_valor_1.setText(String.valueOf(v.f_joint_valor_1));
		}});

		v.js_joint_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint_valor_2);
 		v.f_joint_valor_2  =(float) v.js_joint_valor_2.getValue()/100;
 		v.jl_joint_valor_2 = new JLabel(String.valueOf(v.f_joint_valor_2));
 		v.js_joint_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint_valor_2 =(float)v.js_joint_valor_2.getValue()/100;
		v.jl_joint_valor_2.setText(String.valueOf(v.f_joint_valor_2));
		}});

		v.js_joint_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint_valor_3);
 		v.f_joint_valor_3  =(float) v.js_joint_valor_3.getValue()/100;
 		v.jl_joint_valor_3 = new JLabel(String.valueOf(v.f_joint_valor_3));
 		v.js_joint_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint_valor_3 =(float)v.js_joint_valor_3.getValue()/100;
		v.jl_joint_valor_3.setText(String.valueOf(v.f_joint_valor_3));
		}});

		v.js_joint_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint_valor_4);
 		v.f_joint_valor_4  =(float) v.js_joint_valor_4.getValue()/100;
 		v.jl_joint_valor_4 = new JLabel(String.valueOf(v.f_joint_valor_4));
 		v.js_joint_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint_valor_4 =(float)v.js_joint_valor_4.getValue()/100;
		v.jl_joint_valor_4.setText(String.valueOf(v.f_joint_valor_4));
		}});
		
		
		v.js_joint_valor_5 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint_valor_5);
 		v.f_joint_valor_5  =(float) v.js_joint_valor_5.getValue()/100;
 		v.jl_joint_valor_5 = new JLabel(String.valueOf(v.f_joint_valor_5));
 		v.js_joint_valor_5.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint_valor_5 =(float)v.js_joint_valor_5.getValue()/100;
		v.jl_joint_valor_5.setText(String.valueOf(v.f_joint_valor_5));
		}});
		
		
		v.js_joint_valor_6 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint_valor_6);
 		v.f_joint_valor_6  =(float) v.js_joint_valor_6.getValue()/100;
 		v.jl_joint_valor_6 = new JLabel(String.valueOf(v.f_joint_valor_6));
 		v.js_joint_valor_6.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint_valor_6 =(float)v.js_joint_valor_6.getValue()/100;
		v.jl_joint_valor_6.setText(String.valueOf(v.f_joint_valor_6));
		}});
		
		

		v.jb_joint_insert = new JButton("Insertar Componente");
		v.jb_joint_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
						String s = v.jtf_joint_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
								v.joint = new Joint(v.f_joint_valor_1,v.f_joint_valor_2,v.f_joint_valor_3,v.f_joint_valor_4,v.f_joint_valor_5,v.f_joint_valor_6,v.jtf_joint_nombre.getText());
								p(v.jtf_joint_nombre.getText());
								robot.adherirPieza(v.joint,indice_robot,v.JOINT);
								cambios=true;
							}
						}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_joint_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_joint_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint_valor_4 ,2,3,1,1,0.1,0.1,true);
		
				
		adherirAGridBag(panel,v.jl_joint_var_5   ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint_valor_5 ,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint_valor_5 ,2,4,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_joint_var_6   ,0,5,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint_valor_6 ,1,5,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint_valor_6 ,2,5,1,1,0.1,0.1,true);


		adherirAGridBag(panel,v.jl_nombre		   					 ,0,6,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_joint_nombre ,0,7,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_joint_insert  ,0,8,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()							 ,0,9,3,1,1.0,0.2,true);




		
	}

			void crearPanelJoint4(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_joint4_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint4_valor_1);
 		v.f_joint4_valor_1  =(float) (v.js_joint4_valor_1.getValue()/100f);
 		v.jl_joint4_valor_1 = new JLabel(Float.toString(v.f_joint4_valor_1));
 		v.js_joint4_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint4_valor_1 =(float)v.js_joint4_valor_1.getValue()/100;
		v.jl_joint4_valor_1.setText(String.valueOf(v.f_joint4_valor_1));
		}});

		v.js_joint4_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint4_valor_2);
 		v.f_joint4_valor_2  =(float) v.js_joint4_valor_2.getValue()/100;
 		v.jl_joint4_valor_2 = new JLabel(String.valueOf(v.f_joint4_valor_2));
 		v.js_joint4_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint4_valor_2 =(float)v.js_joint4_valor_2.getValue()/100;
		v.jl_joint4_valor_2.setText(String.valueOf(v.f_joint4_valor_2));
		}});

		v.js_joint4_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint4_valor_3);
 		v.f_joint4_valor_3  =(float) v.js_joint4_valor_3.getValue()/100;
 		v.jl_joint4_valor_3 = new JLabel(String.valueOf(v.f_joint4_valor_3));
 		v.js_joint4_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint4_valor_3 =(float)v.js_joint4_valor_3.getValue()/100;
		v.jl_joint4_valor_3.setText(String.valueOf(v.f_joint4_valor_3));
		}});

		v.js_joint4_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint4_valor_4);
 		v.f_joint4_valor_4  =(float) v.js_joint4_valor_4.getValue()/100;
 		v.jl_joint4_valor_4 = new JLabel(String.valueOf(v.f_joint4_valor_4));
 		v.js_joint4_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint4_valor_4 =(float)v.js_joint4_valor_4.getValue()/100;
		v.jl_joint4_valor_4.setText(String.valueOf(v.f_joint4_valor_4));
		}});
		
		
		v.js_joint4_valor_5 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint4_valor_5);
 		v.f_joint4_valor_5  =(float) v.js_joint4_valor_5.getValue()/100;
 		v.jl_joint4_valor_5 = new JLabel(String.valueOf(v.f_joint4_valor_5));
 		v.js_joint4_valor_5.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint4_valor_5 =(float)v.js_joint4_valor_5.getValue()/100;
		v.jl_joint4_valor_5.setText(String.valueOf(v.f_joint4_valor_5));
		}});
		
		
		v.js_joint4_valor_6 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint4_valor_6);
 		v.f_joint4_valor_6  =(float) v.js_joint4_valor_6.getValue()/100;
 		v.jl_joint4_valor_6 = new JLabel(String.valueOf(v.f_joint4_valor_6));
 		v.js_joint4_valor_6.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint4_valor_6 =(float)v.js_joint4_valor_6.getValue()/100;
		v.jl_joint4_valor_6.setText(String.valueOf(v.f_joint4_valor_6));
		}});
		
		

		v.jb_joint4_insert = new JButton("Insertar Componente");
		v.jb_joint4_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
									String s = v.jtf_joint4_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
						  	v.joint4 = new Joint4(v.f_joint4_valor_1,v.f_joint4_valor_2,v.f_joint4_valor_3,v.f_joint4_valor_4,v.f_joint4_valor_5,v.f_joint4_valor_6,v.jtf_joint4_nombre.getText());
								p(v.jtf_joint4_nombre.getText());
								robot.adherirPieza(v.joint4,indice_robot,v.JOINT_4);
		
								cambios=true;
							}
						}	
				
								
					
					
					





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_joint4_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint4_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint4_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint4_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint4_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint4_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_joint4_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint4_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint4_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint4_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint4_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint4_valor_4 ,2,3,1,1,0.1,0.1,true);
		
				
		adherirAGridBag(panel,v.jl_joint4_var_5   ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint4_valor_5 ,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint4_valor_5 ,2,4,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_joint4_var_6   ,0,5,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint4_valor_6 ,1,5,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint4_valor_6 ,2,5,1,1,0.1,0.1,true);


		adherirAGridBag(panel,v.jl_nombre		   					 ,0,6,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_joint4_nombre ,0,7,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_joint4_insert  ,0,8,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()							 ,0,9,3,1,1.0,0.2,true);




		
	}



			void crearPanelRiel(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_riel_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_riel_valor_1);
 		v.f_riel_valor_1  =(float) (v.js_riel_valor_1.getValue()/100f);
 		v.jl_riel_valor_1 = new JLabel(Float.toString(v.f_riel_valor_1));
 		v.js_riel_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_riel_valor_1 =(float)v.js_riel_valor_1.getValue()/100;
		v.jl_riel_valor_1.setText(String.valueOf(v.f_riel_valor_1));
		}});

		v.js_riel_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_riel_valor_2);
 		v.f_riel_valor_2  =(float) v.js_riel_valor_2.getValue()/100;
 		v.jl_riel_valor_2 = new JLabel(String.valueOf(v.f_riel_valor_2));
 		v.js_riel_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_riel_valor_2 =(float)v.js_riel_valor_2.getValue()/100;
		v.jl_riel_valor_2.setText(String.valueOf(v.f_riel_valor_2));
		}});

		v.js_riel_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_riel_valor_3);
 		v.f_riel_valor_3  =(float) v.js_riel_valor_3.getValue()/100;
 		v.jl_riel_valor_3 = new JLabel(String.valueOf(v.f_riel_valor_3));
 		v.js_riel_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_riel_valor_3 =(float)v.js_riel_valor_3.getValue()/100;
		v.jl_riel_valor_3.setText(String.valueOf(v.f_riel_valor_3));
		}});

		v.js_riel_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_riel_valor_4);
 		v.f_riel_valor_4  =(float) v.js_riel_valor_4.getValue()/100;
 		v.jl_riel_valor_4 = new JLabel(String.valueOf(v.f_riel_valor_4));
 		v.js_riel_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_riel_valor_4 =(float)v.js_riel_valor_4.getValue()/100;
		v.jl_riel_valor_4.setText(String.valueOf(v.f_riel_valor_4));
		}});
		
		
		v.js_riel_valor_5 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_riel_valor_5);
 		v.f_riel_valor_5  =(float) v.js_riel_valor_5.getValue()/100;
 		v.jl_riel_valor_5 = new JLabel(String.valueOf(v.f_riel_valor_5));
 		v.js_riel_valor_5.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_riel_valor_5 =(float)v.js_riel_valor_5.getValue()/100;
		v.jl_riel_valor_5.setText(String.valueOf(v.f_riel_valor_5));
		}});
		
		
		v.js_riel_valor_6 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_riel_valor_6);
 		v.f_riel_valor_6  =(float) v.js_riel_valor_6.getValue()/100;
 		v.jl_riel_valor_6 = new JLabel(String.valueOf(v.f_riel_valor_6));
 		v.js_riel_valor_6.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_riel_valor_6 =(float)v.js_riel_valor_6.getValue()/100;
		v.jl_riel_valor_6.setText(String.valueOf(v.f_riel_valor_6));
		}});
		
		

		v.jb_riel_insert = new JButton("Insertar Componente");
		v.jb_riel_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
				String s = v.jtf_riel_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
								v.riel = new Riel(v.f_riel_valor_1,v.f_riel_valor_2,v.f_riel_valor_3,v.f_riel_valor_4,v.f_riel_valor_5,v.f_riel_valor_6,v.jtf_riel_nombre.getText());
								p(v.jtf_riel_nombre.getText());
								robot.adherirPieza(v.riel,indice_robot,v.RIEL);
								indice_robot++;


								cambios=true;
							}
						}	
				
								
					
					
					















					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_riel_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_riel_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_riel_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_riel_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_riel_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_riel_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_riel_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_riel_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_riel_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_riel_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_riel_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_riel_valor_4 ,2,3,1,1,0.1,0.1,true);
		
				
		adherirAGridBag(panel,v.jl_riel_var_5   ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_riel_valor_5 ,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_riel_valor_5 ,2,4,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_riel_var_6   ,0,5,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_riel_valor_6 ,1,5,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_riel_valor_6 ,2,5,1,1,0.1,0.1,true);


		adherirAGridBag(panel,new JPanel()      ,0,6,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_riel_nombre ,0,7,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_riel_insert  ,0,8,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()			,0,9,3,1,1.0,0.2,true);




		
	}


		void crearPanelLinkConCaja2(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_link_con_caja_2_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_2_valor_1);
		
 		v.f_link_con_caja_2_valor_1  =(float) (v.js_link_con_caja_2_valor_1.getValue()/100f);
 		v.jl_link_con_caja_2_valor_1 = new JLabel(Float.toString(v.f_link_con_caja_2_valor_1));
 		
 		v.js_link_con_caja_2_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_2_valor_1 =(float)v.js_link_con_caja_2_valor_1.getValue()/100;
		v.jl_link_con_caja_2_valor_1.setText(String.valueOf(v.f_link_con_caja_2_valor_1));
		}});

		v.js_link_con_caja_2_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_2_valor_2);
 		v.f_link_con_caja_2_valor_2  =(float) v.js_link_con_caja_2_valor_2.getValue()/100;
 		v.jl_link_con_caja_2_valor_2 = new JLabel(String.valueOf(v.f_link_con_caja_2_valor_2));
 		
 		v.js_link_con_caja_2_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_2_valor_2 =(float)v.js_link_con_caja_2_valor_2.getValue()/100;
		v.jl_link_con_caja_2_valor_2.setText(String.valueOf(v.f_link_con_caja_2_valor_2));
		
		}});

		v.js_link_con_caja_2_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_2_valor_3);
 		v.f_link_con_caja_2_valor_3  =(float) v.js_link_con_caja_2_valor_3.getValue()/100;
 		v.jl_link_con_caja_2_valor_3 = new JLabel(String.valueOf(v.f_link_con_caja_2_valor_3));
 		
 		v.js_link_con_caja_2_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_2_valor_3 =(float)v.js_link_con_caja_2_valor_3.getValue()/100;
		v.jl_link_con_caja_2_valor_3.setText(String.valueOf(v.f_link_con_caja_2_valor_3));
		
		}});
		
		v.js_link_con_caja_2_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_link_con_caja_2_valor_4);
 		v.f_link_con_caja_2_valor_4  =(float) v.js_link_con_caja_2_valor_4.getValue()/100;
 		v.jl_link_con_caja_2_valor_4 = new JLabel(String.valueOf(v.f_link_con_caja_2_valor_4));
 		
 		v.js_link_con_caja_2_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_link_con_caja_2_valor_4 =(float)v.js_link_con_caja_2_valor_4.getValue()/100;
		v.jl_link_con_caja_2_valor_4.setText(String.valueOf(v.f_link_con_caja_2_valor_4));
		
		}});

		v.jb_link_con_caja_2_insert = new JButton("Insertar Componente");
		v.jb_link_con_caja_2_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
								String s = v.jtf_link_con_caja_2_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{

					v.link_con_caja_2 = new Link_Con_Caja_2(v.f_link_con_caja_2_valor_1,v.f_link_con_caja_2_valor_2,v.f_link_con_caja_2_valor_3,v.f_link_con_caja_2_valor_4,v.jtf_link_con_caja_2_nombre.getText());
					p(v.jtf_link_con_caja_2_nombre.getText());
					robot.adherirPieza(v.link_con_caja_2,indice_robot,v.LINK_CON_CAJA_2);
								cambios=true;
							}
						}	
				
								
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					

					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_link_con_caja_2_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_2_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_2_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link_con_caja_2_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_2_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_2_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_link_con_caja_2_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_2_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_2_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_link_con_caja_2_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_link_con_caja_2_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_link_con_caja_2_valor_4 ,2,3,1,1,0.1,0.1,true);
		

		adherirAGridBag(panel,v.jl_nombre		  						 ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_link_con_caja_2_nombre ,0,5,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_link_con_caja_2_insert  ,0,6,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()		 						 ,0,4,3,1,1.0,0.3,true);




		
	}



		void crearPanelJoint2(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_joint2_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint2_valor_1);
 		v.f_joint2_valor_1  =(float) (v.js_joint2_valor_1.getValue()/100f);
 		v.jl_joint2_valor_1 = new JLabel(Float.toString(v.f_joint2_valor_1));
 		v.js_joint2_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint2_valor_1 =(float)v.js_joint2_valor_1.getValue()/100;
		v.jl_joint2_valor_1.setText(String.valueOf(v.f_joint2_valor_1));
		}});

		v.js_joint2_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint2_valor_2);
 		v.f_joint2_valor_2  =(float) v.js_joint2_valor_2.getValue()/100;
 		v.jl_joint2_valor_2 = new JLabel(String.valueOf(v.f_joint2_valor_2));
 		v.js_joint2_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint2_valor_2 =(float)v.js_joint2_valor_2.getValue()/100;
		v.jl_joint2_valor_2.setText(String.valueOf(v.f_joint2_valor_2));
		}});

		v.js_joint2_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint2_valor_3);
 		v.f_joint2_valor_3  =(float) v.js_joint2_valor_3.getValue()/100;
 		v.jl_joint2_valor_3 = new JLabel(String.valueOf(v.f_joint2_valor_3));
 		v.js_joint2_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint2_valor_3 =(float)v.js_joint2_valor_3.getValue()/100;
		v.jl_joint2_valor_3.setText(String.valueOf(v.f_joint2_valor_3));
		}});

		v.js_joint2_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint2_valor_4);
 		v.f_joint2_valor_4  =(float) v.js_joint2_valor_4.getValue()/100;
 		v.jl_joint2_valor_4 = new JLabel(String.valueOf(v.f_joint2_valor_4));
 		v.js_joint2_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint2_valor_4 =(float)v.js_joint2_valor_4.getValue()/100;
		v.jl_joint2_valor_4.setText(String.valueOf(v.f_joint2_valor_4));
		}});
		
		
		v.js_joint2_valor_5 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint2_valor_5);
 		v.f_joint2_valor_5  =(float) v.js_joint2_valor_5.getValue()/100;
 		v.jl_joint2_valor_5 = new JLabel(String.valueOf(v.f_joint2_valor_5));
 		v.js_joint2_valor_5.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint2_valor_5 =(float)v.js_joint2_valor_5.getValue()/100;
		v.jl_joint2_valor_5.setText(String.valueOf(v.f_joint2_valor_5));
		}});
		
		
		v.js_joint2_valor_6 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint2_valor_6);
 		v.f_joint2_valor_6  =(float) v.js_joint2_valor_6.getValue()/100;
 		v.jl_joint2_valor_6 = new JLabel(String.valueOf(v.f_joint2_valor_6));
 		v.js_joint2_valor_6.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint2_valor_6 =(float)v.js_joint2_valor_6.getValue()/100;
		v.jl_joint2_valor_6.setText(String.valueOf(v.f_joint2_valor_6));
		}});
		
				v.js_joint2_valor_7 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint2_valor_7);
 		v.f_joint2_valor_7  =(float) v.js_joint2_valor_7.getValue()/100;
 		v.jl_joint2_valor_7 = new JLabel(String.valueOf(v.f_joint2_valor_7));
 		v.js_joint2_valor_7.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint2_valor_7 =(float)v.js_joint2_valor_7.getValue()/100;
		v.jl_joint2_valor_7.setText(String.valueOf(v.f_joint2_valor_7));
		}});
		
		v.js_joint2_valor_8 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint2_valor_8);
 		v.f_joint2_valor_8  =(float) v.js_joint2_valor_8.getValue()/100;
 		v.jl_joint2_valor_8 = new JLabel(String.valueOf(v.f_joint2_valor_8));
 		v.js_joint2_valor_8.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint2_valor_8 =(float)v.js_joint2_valor_8.getValue()/100;
		v.jl_joint2_valor_8.setText(String.valueOf(v.f_joint2_valor_8));
		}});
		

		v.jb_joint2_insert = new JButton("Insertar Componente");
		v.jb_joint2_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				

						String s = v.jtf_joint2_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
								v.joint2 = new Joint2(v.f_joint2_valor_1,v.f_joint2_valor_2,v.f_joint2_valor_3,v.f_joint2_valor_4,v.f_joint2_valor_5,v.f_joint2_valor_6,v.f_joint2_valor_7,v.f_joint2_valor_8,v.jtf_joint2_nombre.getText());
								p(v.jtf_joint2_nombre.getText());
								robot.adherirPieza(v.joint2,indice_robot,v.JOINT_2);
								cambios=true;
							}
						}

					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_joint2_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint2_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint2_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint2_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint2_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint2_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_joint2_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint2_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint2_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint2_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint2_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint2_valor_4 ,2,3,1,1,0.1,0.1,true);
		
				
		adherirAGridBag(panel,v.jl_joint2_var_5   ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint2_valor_5 ,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint2_valor_5 ,2,4,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_joint2_var_6   ,0,5,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint2_valor_6 ,1,5,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint2_valor_6 ,2,5,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_joint2_var_7   ,0,6,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint2_valor_7 ,1,6,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint2_valor_7 ,2,6,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint2_var_8   ,0,7,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint2_valor_8 ,1,7,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint2_valor_8 ,2,7,1,1,0.1,0.1,true);



		adherirAGridBag(panel,v.jl_nombre		   	  ,0,8 ,3,1,1.0,0.05,true);
		adherirAGridBag(panel,v.jtf_joint2_nombre ,0,9 ,3,1,1.0,0.05,true);
		adherirAGridBag(panel,v.jb_joint2_insert  ,0,10,3,1,1.0,0.1,true);
		



		
	}

		void crearPanelHand(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_hand_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_1);
 		v.f_hand_valor_1  =(float) (v.js_hand_valor_1.getValue()/100f);
 		v.jl_hand_valor_1 = new JLabel(Float.toString(v.f_hand_valor_1));
 		v.js_hand_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_1 =(float)v.js_hand_valor_1.getValue()/100;
		v.jl_hand_valor_1.setText(String.valueOf(v.f_hand_valor_1));
		}});

		v.js_hand_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_2);
 		v.f_hand_valor_2  =(float) v.js_hand_valor_2.getValue()/100;
 		v.jl_hand_valor_2 = new JLabel(String.valueOf(v.f_hand_valor_2));
 		v.js_hand_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_2 =(float)v.js_hand_valor_2.getValue()/100;
		v.jl_hand_valor_2.setText(String.valueOf(v.f_hand_valor_2));
		}});

		v.js_hand_valor_3 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_3);
 		v.f_hand_valor_3  =(float) v.js_hand_valor_3.getValue()/100;
 		v.jl_hand_valor_3 = new JLabel(String.valueOf(v.f_hand_valor_3));
 		v.js_hand_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_3 =(float)v.js_hand_valor_3.getValue()/100;
		v.jl_hand_valor_3.setText(String.valueOf(v.f_hand_valor_3));
		}});

		v.js_hand_valor_4 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_4);
 		v.f_hand_valor_4  =(float) v.js_hand_valor_4.getValue()/100;
 		v.jl_hand_valor_4 = new JLabel(String.valueOf(v.f_hand_valor_4));
 		v.js_hand_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_4 =(float)v.js_hand_valor_4.getValue()/100;
		v.jl_hand_valor_4.setText(String.valueOf(v.f_hand_valor_4));
		}});
		
		
		v.js_hand_valor_5 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_5);
 		v.f_hand_valor_5  =(float) v.js_hand_valor_5.getValue()/100;
 		v.jl_hand_valor_5 = new JLabel(String.valueOf(v.f_hand_valor_5));
 		v.js_hand_valor_5.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_5 =(float)v.js_hand_valor_5.getValue()/100;
		v.jl_hand_valor_5.setText(String.valueOf(v.f_hand_valor_5));
		}});
		
		
		v.js_hand_valor_6 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_6);
 		v.f_hand_valor_6  =(float) v.js_hand_valor_6.getValue()/100;
 		v.jl_hand_valor_6 = new JLabel(String.valueOf(v.f_hand_valor_6));
 		v.js_hand_valor_6.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_6 =(float)v.js_hand_valor_6.getValue()/100;
		v.jl_hand_valor_6.setText(String.valueOf(v.f_hand_valor_6));
		}});
		
				v.js_hand_valor_7 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_7);
 		v.f_hand_valor_7  =(float) v.js_hand_valor_7.getValue()/100;
 		v.jl_hand_valor_7 = new JLabel(String.valueOf(v.f_hand_valor_7));
 		v.js_hand_valor_7.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_7 =(float)v.js_hand_valor_7.getValue()/100;
		v.jl_hand_valor_7.setText(String.valueOf(v.f_hand_valor_7));
		}});
		
		v.js_hand_valor_8 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_8);
 		v.f_hand_valor_8  =(float) v.js_hand_valor_8.getValue()/100;
 		v.jl_hand_valor_8 = new JLabel(String.valueOf(v.f_hand_valor_8));
 		v.js_hand_valor_8.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_8 =(float)v.js_hand_valor_8.getValue()/100;
		v.jl_hand_valor_8.setText(String.valueOf(v.f_hand_valor_8));
		}});
		
		v.js_hand_valor_9 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_9);
 		v.f_hand_valor_9  =(float) v.js_hand_valor_9.getValue()/100;
 		v.jl_hand_valor_9 = new JLabel(String.valueOf(v.f_hand_valor_9));
 		v.js_hand_valor_9.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_9 =(float)v.js_hand_valor_9.getValue()/100;
		v.jl_hand_valor_9.setText(String.valueOf(v.f_hand_valor_9));
		}});
		
		
		
		
		v.js_hand_valor_10 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand_valor_10);
 		v.f_hand_valor_10  =(float) v.js_hand_valor_10.getValue()/100;
 		v.jl_hand_valor_10 = new JLabel(String.valueOf(v.f_hand_valor_10));
 		v.js_hand_valor_10.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand_valor_10 =(float)v.js_hand_valor_10.getValue()/100;
		v.jl_hand_valor_10.setText(String.valueOf(v.f_hand_valor_10));
		}});

		v.jb_hand_insert = new JButton("Insertar Componente");
		v.jb_hand_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
	
	
							String s = v.jtf_hand_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
					v.hand = new Hand(v.f_hand_valor_1,v.f_hand_valor_2,v.f_hand_valor_3,v.f_hand_valor_4,v.f_hand_valor_5,v.f_hand_valor_6,
					v.f_hand_valor_7,v.f_hand_valor_8,v.f_hand_valor_9,v.f_hand_valor_10,v.jtf_hand_nombre.getText());
					p(v.jtf_hand_nombre.getText());
					robot.adherirPieza(v.hand,indice_robot,v.HAND);
					indice_robot++;

								cambios=true;
							}
						}	
				
				
				
				
	
				
				

	
	
	
	
	
	
	
	
	
	
				

					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_hand_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_hand_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_hand_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_hand_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_4 ,2,3,1,1,0.1,0.1,true);
		
				
		adherirAGridBag(panel,v.jl_hand_var_5   ,0,4,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_5 ,1,4,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_5 ,2,4,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_hand_var_6   ,0,5,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_6 ,1,5,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_6 ,2,5,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_hand_var_7   ,0,6,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_7 ,1,6,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_7 ,2,6,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_hand_var_8   ,0,7,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_8 ,1,7,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_8 ,2,7,1,1,0.1,0.1,true);

		
		adherirAGridBag(panel,v.jl_hand_var_9   ,0,8,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_9 ,1,8,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_9 ,2,8,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_hand_var_10   ,0,9,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand_valor_10 ,1,9,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand_valor_10 ,2,9,1,1,0.1,0.1,true);


		adherirAGridBag(panel,v.jl_nombre		   	  ,0,10 ,3,1,1.0,0.05,true);
		adherirAGridBag(panel,v.jtf_hand_nombre 	,0,11 ,3,1,1.0,0.05,true);
		adherirAGridBag(panel,v.jb_hand_insert  	,0,12,3,1,1.0,0.1,true);
		



		
	}

		void crearPanelJoint3(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_joint3_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint3_valor_1);
 		v.f_joint3_valor_1  =(float) (v.js_joint3_valor_1.getValue()/100f);
 		v.jl_joint3_valor_1 = new JLabel(Float.toString(v.f_joint3_valor_1));
 		v.js_joint3_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint3_valor_1 =(float)v.js_joint3_valor_1.getValue()/100;
		v.jl_joint3_valor_1.setText(String.valueOf(v.f_joint3_valor_1));
		}});

		v.js_joint3_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_joint3_valor_2);
 		v.f_joint3_valor_2  =(float) v.js_joint3_valor_2.getValue()/100;
 		v.jl_joint3_valor_2 = new JLabel(String.valueOf(v.f_joint3_valor_2));
 		v.js_joint3_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_joint3_valor_2 =(float)v.js_joint3_valor_2.getValue()/100;
		v.jl_joint3_valor_2.setText(String.valueOf(v.f_joint3_valor_2));
		}});



		v.jb_joint3_insert = new JButton("Insertar Componente");
		v.jb_joint3_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					String s = v.jtf_joint3_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{
				v.joint3 = new Joint3(v.f_joint3_valor_1,v.f_joint3_valor_2,v.jtf_joint3_nombre.getText());
					p(v.jtf_joint3_nombre.getText());
					robot.adherirPieza(v.joint3,indice_robot,v.JOINT_3);


								cambios=true;
							}
						}
					

					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_joint3_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint3_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint3_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_joint3_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_joint3_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_joint3_valor_2 ,2,1,1,1,0.1,0.1,true);

	
		adherirAGridBag(panel,v.jl_nombre		   	  ,0,2 ,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_joint3_nombre ,0,3 ,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_joint3_insert  ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()        ,0,5,3,1,1.0,0.6,true);
		
		



		
	}


		void crearPanelHand2(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_hand2_valor_1 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand2_valor_1);
 		v.f_hand2_valor_1  =(float) (v.js_hand2_valor_1.getValue()/100f);
 		v.jl_hand2_valor_1 = new JLabel(Float.toString(v.f_hand2_valor_1));
 		v.js_hand2_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand2_valor_1 =(float)v.js_hand2_valor_1.getValue()/100;
		v.jl_hand2_valor_1.setText(String.valueOf(v.f_hand2_valor_1));
		}});

		v.js_hand2_valor_2 = new JSlider (JSlider.HORIZONTAL,0,1000,v.vi_hand2_valor_2);
 		v.f_hand2_valor_2  =(float) v.js_hand2_valor_2.getValue()/100;
 		v.jl_hand2_valor_2 = new JLabel(String.valueOf(v.f_hand2_valor_2));
 		v.js_hand2_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_hand2_valor_2 =(float)v.js_hand2_valor_2.getValue()/100;
		v.jl_hand2_valor_2.setText(String.valueOf(v.f_hand2_valor_2));
		}});



		v.jb_hand2_insert = new JButton("Insertar Componente");
		v.jb_hand2_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
						String s = v.jtf_hand2_nombre.getText();
					
					// si no se le asigno nombre -> debe nombrar
						if (s.compareTo("")==0)
						{
							debenombrar();
						}
				
						else
						{
						
							if (existe_el_nombre(robot.nombre,s,robot.piloto)==true)
							{
								elnombredebeserunico();
							}
							else
							{

					v.hand2 = new Hand2(v.f_hand2_valor_1,v.f_hand2_valor_2,v.jtf_hand2_nombre.getText());
					p(v.jtf_hand2_nombre.getText());
					robot.adherirPieza(v.hand2,indice_robot,v.HAND_2);
					indice_robot++;

								cambios=true;
							}
						}	
				
				
				
				
	
				
				
				
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_hand2_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand2_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand2_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_hand2_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_hand2_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_hand2_valor_2 ,2,1,1,1,0.1,0.1,true);

	
		adherirAGridBag(panel,v.jl_nombre		   	  ,0,2 ,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jtf_hand2_nombre ,0,3 ,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_hand2_insert  ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()        ,0,5,3,1,1.0,0.6,true);
		
		



		
	}














//--------------transfomaciones paneles 

		void crearPanelRotacionX(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_rotacion_x_valor_1 = new JSlider (JSlider.HORIZONTAL,-3600,3600,v.vi_rotacion_x_valor_1);
 		v.f_rotacion_x_valor_1  =(float) (v.js_rotacion_x_valor_1.getValue()/10f);
 		v.jl_rotacion_x_valor_1 = new JLabel(Float.toString(v.f_rotacion_x_valor_1));
 		v.js_rotacion_x_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_rotacion_x_valor_1 =(float)v.js_rotacion_x_valor_1.getValue()/10;
		v.jl_rotacion_x_valor_1.setText(String.valueOf(v.f_rotacion_x_valor_1));
		}});


		v.jb_rotacion_x_insert = new JButton("Insertar Componente");
		v.jb_rotacion_x_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.rotacion_x = new RotacionX(v.f_rotacion_x_valor_1);
					p("Rotacion X");
					robot.adherirPieza(v.rotacion_x,indice_robot,v.ROTACION_X);
			
									cambios=true;
				
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_rotacion_x_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_rotacion_x_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_rotacion_x_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		//adherirAGridBag(panel,v.jl_nombre		   			  ,0,1,3,1,1.0,0.1,true);
		//adherirAGridBag(panel,v.jtf_rotacion_x_nombre ,0,2,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_rotacion_x_insert  ,0,1,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()      		  ,0,2,3,1,1.0,0.6,true);
			
		



		
	}



		void crearPanelRotacionY(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_rotacion_y_valor_1 = new JSlider (JSlider.HORIZONTAL,-3600,3600,v.vi_rotacion_y_valor_1);
 		v.f_rotacion_y_valor_1  =(float) (v.js_rotacion_y_valor_1.getValue()/10f);
 		v.jl_rotacion_y_valor_1 = new JLabel(Float.toString(v.f_rotacion_y_valor_1));
 		v.js_rotacion_y_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_rotacion_y_valor_1 =(float)v.js_rotacion_y_valor_1.getValue()/10;
		v.jl_rotacion_y_valor_1.setText(String.valueOf(v.f_rotacion_y_valor_1));
		}});


		v.jb_rotacion_y_insert = new JButton("Insertar Componente");
		v.jb_rotacion_y_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.rotacion_y = new RotacionY(v.f_rotacion_y_valor_1);
					p("Rotacion Y");
					robot.adherirPieza(v.rotacion_y,indice_robot,v.ROTACION_Y);
								cambios=true;
					
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_rotacion_y_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_rotacion_y_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_rotacion_y_valor_1 ,2,0,1,1,0.1,0.1,true);
		
	//	adherirAGridBag(panel,v.jl_nombre		   			  ,0,1 ,3,1,1.0,0.1,true);
	//	adherirAGridBag(panel,v.jtf_rotacion_y_nombre ,0,2 ,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_rotacion_y_insert  ,0,1,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()      		  ,0,2,3,1,1.0,0.6,true);
			
		



		
	}

		void crearPanelRotacionZ(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_rotacion_z_valor_1 = new JSlider (JSlider.HORIZONTAL,-3600,3600,v.vi_rotacion_z_valor_1);
 		v.f_rotacion_z_valor_1  =(float) (v.js_rotacion_z_valor_1.getValue()/10f);
 		v.jl_rotacion_z_valor_1 = new JLabel(Float.toString(v.f_rotacion_z_valor_1));
 		v.js_rotacion_z_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_rotacion_z_valor_1 =(float)v.js_rotacion_z_valor_1.getValue()/10;
		v.jl_rotacion_z_valor_1.setText(String.valueOf(v.f_rotacion_z_valor_1));
		}});


		v.jb_rotacion_z_insert = new JButton("Insertar Componente");
		v.jb_rotacion_z_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.rotacion_z = new RotacionZ(v.f_rotacion_z_valor_1);
					robot.adherirPieza(v.rotacion_z,indice_robot,v.ROTACION_Z);
					p("Rotacion Z");
								cambios=true;
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_rotacion_z_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_rotacion_z_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_rotacion_z_valor_1 ,2,0,1,1,0.1,0.1,true);
		
	//	adherirAGridBag(panel,v.jl_nombre		   			  ,0,1 ,3,1,1.0,0.1,true);
	//	adherirAGridBag(panel,v.jtf_rotacion_z_nombre ,0,2 ,3,1,1.0,0.1,true);
		adherirAGridBag(panel,v.jb_rotacion_z_insert  ,0,1,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()      		  ,0,2,3,1,1.0,0.6,true);
			
	
	}

		void crearPanelEscalamiento(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		v.js_escalamiento_valor_1 = new JSlider (JSlider.HORIZONTAL,0,500,v.vi_escalamiento_valor_1);
 		v.f_escalamiento_valor_1  =(float) (v.js_escalamiento_valor_1.getValue()/100f);
 		v.jl_escalamiento_valor_1 = new JLabel(Float.toString(v.f_escalamiento_valor_1));
 		v.js_escalamiento_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_escalamiento_valor_1 =(float)v.js_escalamiento_valor_1.getValue()/100;
		v.jl_escalamiento_valor_1.setText(String.valueOf(v.f_escalamiento_valor_1));
		}});


		v.jb_escalamiento_insert = new JButton("Insertar Componente");
		v.jb_escalamiento_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.escalamiento = new Escalamiento(v.f_escalamiento_valor_1);
					p("Escalamiento");
					robot.adherirPieza(v.escalamiento,indice_robot,v.ESCALAMIENTO);
				
								cambios=true;
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_escalamiento_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_escalamiento_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_escalamiento_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jb_escalamiento_insert  ,0,1,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()      		    ,0,2,3,1,1.0,0.6,true);
			
		



		
	}

	void crearPanelRotarVector(JPanel panel)
	{

		panel.setLayout(new GridBagLayout());
		
		v.js_rotar_vector_valor_1 = new JSlider (JSlider.HORIZONTAL,-36000,36000,v.vi_rotar_vector_valor_1);
		
 		v.f_rotar_vector_valor_1  =(float) (v.js_rotar_vector_valor_1.getValue()/100f);
 		v.jl_rotar_vector_valor_1 = new JLabel(Float.toString(v.f_rotar_vector_valor_1));
 		
 		v.js_rotar_vector_valor_1.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_rotar_vector_valor_1 =(float)v.js_rotar_vector_valor_1.getValue()/100;
		v.jl_rotar_vector_valor_1.setText(String.valueOf(v.f_rotar_vector_valor_1));
		}});

		v.js_rotar_vector_valor_2 = new JSlider (JSlider.HORIZONTAL,-1000,1000,v.vi_rotar_vector_valor_2);
 		v.f_rotar_vector_valor_2  =(float) v.js_rotar_vector_valor_2.getValue()/100;
 		v.jl_rotar_vector_valor_2 = new JLabel(String.valueOf(v.f_rotar_vector_valor_2));
 		
 		v.js_rotar_vector_valor_2.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_rotar_vector_valor_2 =(float)v.js_rotar_vector_valor_2.getValue()/100;
		v.jl_rotar_vector_valor_2.setText(String.valueOf(v.f_rotar_vector_valor_2));
		
		}});

		v.js_rotar_vector_valor_3 = new JSlider (JSlider.HORIZONTAL,-1000,1000,v.vi_rotar_vector_valor_3);
 		v.f_rotar_vector_valor_3  =(float) v.js_rotar_vector_valor_3.getValue()/100;
 		v.jl_rotar_vector_valor_3 = new JLabel(String.valueOf(v.f_rotar_vector_valor_3));
 		
 		v.js_rotar_vector_valor_3.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_rotar_vector_valor_3 =(float)v.js_rotar_vector_valor_3.getValue()/100;
		v.jl_rotar_vector_valor_3.setText(String.valueOf(v.f_rotar_vector_valor_3));
		
		}});
		
		v.js_rotar_vector_valor_4 = new JSlider (JSlider.HORIZONTAL,-1000,1000,v.vi_rotar_vector_valor_4);
 		v.f_rotar_vector_valor_4  =(float) v.js_rotar_vector_valor_4.getValue()/100;
 		v.jl_rotar_vector_valor_4 = new JLabel(String.valueOf(v.f_rotar_vector_valor_4));
 		
 		v.js_rotar_vector_valor_4.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_rotar_vector_valor_4 =(float)v.js_rotar_vector_valor_4.getValue()/100;
		v.jl_rotar_vector_valor_4.setText(String.valueOf(v.f_rotar_vector_valor_4));
		
		}});

		v.jb_rotar_vector_insert = new JButton("Insertar Componente");
		v.jb_rotar_vector_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					v.rotar_vector = new RotarVector(v.f_rotar_vector_valor_1,v.f_rotar_vector_valor_2,v.f_rotar_vector_valor_3,v.f_rotar_vector_valor_4,v.jtf_rotar_vector_nombre.getText());
					p("Rotacion al Vector");
					robot.adherirPieza(v.rotar_vector,indice_robot,v.ROTAR_VECTOR);
								cambios=true;
			
					
				}	
				
			}
			
			
			);
		
		adherirAGridBag(panel,v.jl_rotar_vector_var_1   ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_rotar_vector_valor_1 ,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_rotar_vector_valor_1 ,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_rotar_vector_var_2   ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_rotar_vector_valor_2 ,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_rotar_vector_valor_2 ,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_rotar_vector_var_3   ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_rotar_vector_valor_3 ,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_rotar_vector_valor_3 ,2,2,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_rotar_vector_var_4   ,0,3,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_rotar_vector_valor_4 ,1,3,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_rotar_vector_valor_4 ,2,3,1,1,0.1,0.1,true);
		

		adherirAGridBag(panel,v.jb_rotar_vector_insert  ,0,4,3,1,1.0,0.1,true);
		adherirAGridBag(panel,new JPanel()		         	,0,5,3,1,1.0,0.3,true);




		
	}








//---------------------------------------------


	void crearPanelMesa(JPanel panel)
	{
		
		panel.setLayout(new GridBagLayout());
		
		v.js_mesa_x = new JSlider (JSlider.HORIZONTAL,0,1000,v.mesa_vi_x);
 		v.f_mesa_x  =(float) v.js_mesa_x.getValue()/100;
 		v.jl_mesa_x = new JLabel(String.valueOf(v.f_mesa_x));
 		
 		v.js_mesa_x.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_mesa_x =(float)v.js_mesa_x.getValue()/100;
		v.jl_mesa_x.setText(String.valueOf(v.f_mesa_x));
		
		
		
		
		}});
		
		v.js_mesa_y = new JSlider (JSlider.HORIZONTAL,0,1000,v.mesa_vi_y);
 		v.f_mesa_y  =(float) v.js_mesa_y.getValue()/100;
 		v.jl_mesa_y = new JLabel(String.valueOf(v.f_mesa_y));
 		
 		v.js_mesa_y.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_mesa_y =(float)v.js_mesa_y.getValue()/100;
		v.jl_mesa_y.setText(String.valueOf(v.f_mesa_y));
		
		}});
		
		v.js_mesa_z = new JSlider (JSlider.HORIZONTAL,0,1000,v.mesa_vi_z);
 		v.f_mesa_z  =(float) v.js_mesa_z.getValue()/100;
 		v.jl_mesa_z = new JLabel(String.valueOf(v.f_mesa_z));
 		
 		v.js_mesa_z.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_mesa_z =(float)v.js_mesa_z.getValue()/100;
		v.jl_mesa_z.setText(String.valueOf(v.f_mesa_z));
		
		}});
		
		
		
		
		
		v.jb_mesa_insert = new JButton("Insertar Componente");
		v.jb_mesa_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					mesa = new Mesa(v.f_mesa_x,v.f_mesa_y,v.f_mesa_z);

          

		adherirAGridBag(jp_interno_bot,v.jp_traslada,0,6,1,1,1.0,0.10,true);
					tg_all.addChild(mesa);



					
					indice_robot++;
					print();
				//	System.out.println(" "+v.f_mesa_x+" "+v.f_mesa_y+" "+v.f_mesa_z);
					
				}	
				
			}
			
			
			);
			
			
		
		adherirAGridBag(panel,v.jl_x1     ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_mesa_x,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_mesa_x,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_y1     ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_mesa_y,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_mesa_y,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_z1     ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_mesa_z,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_mesa_z,2,2,1,1,0.1,0.1,true);
		
	
		adherirAGridBag(panel,v.jb_mesa_insert    ,0,3,3,1,1.0,0.1,true);

		
		adherirAGridBag(panel,new JPanel(),0,4,3,1,1.0,0.5,true);
		

		
		
	}

	
	
	

		void crearPanelTranslacion(JPanel panel)
	{
		
		panel.setLayout(new GridBagLayout());
		
		v.js_translacion_x = new JSlider (JSlider.HORIZONTAL,-500,500,v.translacion_vi_x);
 		v.f_translacion_x  =(float) v.js_translacion_x.getValue()/1000;
 		v.jl_translacion_x = new JLabel(String.valueOf(v.f_translacion_x));
 		
 		v.js_translacion_x.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_translacion_x =(float)v.js_translacion_x.getValue()/100;
		v.jl_translacion_x.setText(String.valueOf(v.f_translacion_x));
		
		}});
		
		

		v.js_translacion_y = new JSlider (JSlider.HORIZONTAL,-1000,1000,v.translacion_vi_y);
 		v.f_translacion_y  =(float) v.js_translacion_y.getValue()/100;
 		v.jl_translacion_y = new JLabel(String.valueOf(v.f_translacion_y));
 		
 		v.js_translacion_y.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		
					
			

		
		
		
		v.f_translacion_y =(float)v.js_translacion_y.getValue()/100;
		v.jl_translacion_y.setText(String.valueOf(v.f_translacion_y));

		
		
		
		}});
		
		v.js_translacion_z = new JSlider (JSlider.HORIZONTAL,-1000,1000,v.translacion_vi_z);
 		v.f_translacion_z  =(float) v.js_translacion_z.getValue()/100;
 		v.jl_translacion_z = new JLabel(String.valueOf(v.f_translacion_z));
 		
 		v.js_translacion_z.addChangeListener(new ChangeListener()
		{public void stateChanged(ChangeEvent e)
		{ 
		v.f_translacion_z =(float)v.js_translacion_z.getValue()/100;
		v.jl_translacion_z.setText(String.valueOf(v.f_translacion_z));
		
		}});
		
		
		
		
		
		v.jb_translacion_insert = new JButton("Insertar Componente");
		v.jb_translacion_insert.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
					translacion = new Translacion(v.f_translacion_x,v.f_translacion_y,v.f_translacion_z);
					dlm_listmodel.addElement("Translacion");

					
					robot.adherirPieza(translacion,indice_robot,v.TRANSLACION);
					indice_robot++;
								cambios=true;
					
				}	
				
			}
			
			
			);
			
			
		v.jb_translacion_eliminar = new JButton("Eliminar Componente");
				v.jb_translacion_eliminar.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
				
				translacion.detach();
					
				}	
				
			}
			
			
			);
			
			
		
		adherirAGridBag(panel,v.jl_x1     ,0,0,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_translacion_x,1,0,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_translacion_x,2,0,1,1,0.1,0.1,true);
		
		adherirAGridBag(panel,v.jl_y1     ,0,1,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_translacion_y,1,1,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_translacion_y,2,1,1,1,0.1,0.1,true);

		adherirAGridBag(panel,v.jl_z1     ,0,2,1,1,0.1,0.1,true);
		adherirAGridBag(panel,v.js_translacion_z,1,2,1,1,0.8,0.1,true);
		adherirAGridBag(panel,v.jl_translacion_z,2,2,1,1,0.1,0.1,true);
		
	
		adherirAGridBag(panel,v.jb_translacion_insert    ,0,3,3,1,1.0,0.1,true);
		//adherirAGridBag(panel,v.jb_translacion_eliminar  ,0,4,3,1,1.0,0.1,true);
		
		adherirAGridBag(panel,new JPanel(),0,4,3,1,1.0,0.5,true);
		

		
		
	}




	void crearPanelProgramar(JPanel panel)
	{
		//jt_texto = new JTextArea();				
		jb_ejecutar = new JButton("Ejecutar");
		jb_ejecutar.addActionListener(this);
		jsp_scrollpane = new JScrollPane(jt_texto );

		panel.setLayout(new BorderLayout());
		panel.add(BorderLayout.CENTER,jsp_scrollpane);
		panel.add(BorderLayout.SOUTH,jb_ejecutar);
		
	}
	
	public void adherirAGridBag(JPanel panel, Component comp,int x, int y, int w, int h, double weightx, double weighty,boolean fill)
	{
		GridBagLayout gbl = (GridBagLayout) panel.getLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		if (fill == true)
		{
		c.fill = GridBagConstraints.BOTH;
		}
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		c.weightx = weightx;
		c.weighty = weighty;
		panel.add(comp);
		gbl.setConstraints(comp, c);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		
		Object src = e.getSource();
		
		if (src==jmi_item_salir)
		{
			
			if (verificarSalida()==true)		
			{
			System.exit(0);
			}
			else
			{
			//nada cancelado 
			}
		}	


		else 
		
		if (src==jmi_item_nuevo)
		{
			 
			if (verificarSalida()==true)
			{
			robot.detach();
			robot= new Robot();
			tg_all.addChild(robot);
			nombre_de_archivo="";
			dlm_listmodel.clear();
			}
				
		}	
		
		
		else 
		
		if (src==jmi_item_tarea_nueva)
		{
			 
		
			jt_texto.setText("");
				
		}	
		else 
	
		if (src==jmi_acerca_de)
		{
			 
				acercade();
				
		}	

		else
		
				if (src==jmi_ayuda)
		{
			 
				muestraAyuda();
				
		}	


		else		
		if (src==jmi_item_abrir)
		{
			File archivo; 
			
			if (verificarAbrir()==true)
			{
				robot.detach();
				robot= new Robot();
				tg_all.addChild(robot);
				jfc_robot.setDialogTitle("Abrir Archivo de Robot"); 
				int returnVal=jfc_robot.showOpenDialog(this);
				if (returnVal==JFileChooser.APPROVE_OPTION)
				{
					dlm_listmodel.clear();
					cambios=false;
					archivo = jfc_robot.getSelectedFile();
					nombre_de_archivo=archivo.getName();
					abrir(archivo.getName()	);
					
				}
			}	
	
	
		
		
		
		}
		else 

		if (src==jmi_item_tarea_abrir)
		{
			
						
			File archivo_tarea; 
			
			jfc_tarea.setDialogTitle("Abrir Archivo de Tarea"); 

			int returnVal=jfc_tarea.showOpenDialog(this);
			
			
			if (returnVal==JFileChooser.APPROVE_OPTION)
			{
				
			archivo_tarea = jfc_tarea.getSelectedFile();
			
			abrir_tarea(archivo_tarea.getName());
				
			
			}
		
			
		
		}	
	
	
		else 



		if (src==jmi_item_guardar)
		{

				
					
					
					File archivo; 
					jfc_robot.setDialogTitle("Guardar Archivo de Robot"); 

					if(nombre_de_archivo.compareTo("")==0)
					{
					
							int returnVal=jfc_robot.showSaveDialog(this);
							if (returnVal==JFileChooser.APPROVE_OPTION)
							{
									archivo = jfc_robot.getSelectedFile();
								  nombre_de_archivo=archivo.getName();
								
									salvar(archivo.getName()	);
							}
						
					}		
					
					else
					
					{
						
						  salvar(nombre_de_archivo); 	
						
					}
		
		
		}	
		
		else

		if (src==jmi_item_guardar_robot_como)
		{

				
					
					
					File archivo; 
					jfc_robot.setDialogTitle("Guardar Archivo de Robot Como..."); 

					
							int returnVal=jfc_robot.showSaveDialog(this);
							if (returnVal==JFileChooser.APPROVE_OPTION)
							{
									archivo = jfc_robot.getSelectedFile();

								  nombre_de_archivo=archivo.getName();
								
									salvar(archivo.getName()	);
							}
						
						

		
		
		}	
				
		
		
		else 
		

		if (src==jmi_item_tarea_guardar)
		{
			//	String t = jt_texto.getText();
			//	if (t.compareTo("")==0)
			//	{
			//		JOptionPane.showMessageDialog(this,"No hay Tarea Que Salvar","Error",JOptionPane.ERROR_MESSAGE);			
			//	}
			//	else
				
					File archivo; 
					jfc_tarea.setDialogTitle("Guardar Archivo de Tarea"); 

					int returnVal=jfc_tarea.showSaveDialog(this);
					if (returnVal==JFileChooser.APPROVE_OPTION)
					{
						archivo = jfc_tarea.getSelectedFile();
						salvar_tarea(archivo.getName()	);
					}
				
		
		
		
		}	
		
		if (src==jmi_item_guardar_tarea_como)
		{

					File archivo; 
					jfc_tarea.setDialogTitle("Guardar Archivo de Tarea"); 

					int returnVal=jfc_tarea.showSaveDialog(this);
					if (returnVal==JFileChooser.APPROVE_OPTION)
					{
						archivo = jfc_tarea.getSelectedFile();
						salvar_tarea(archivo.getName()	);
					}
				
		
		
		
		}			
		
		
		
		else 		
		
		
		if (src==jc_combo_piezas)
		{
			
			String s = (String)jc_combo_piezas.getSelectedItem();
			jl_show_picture.setIcon(new ImageIcon("imagenes/"+s+".jpg"));	
			int ind = jc_combo_piezas.getSelectedIndex();
			CardLayout cl = (CardLayout)(jp_cardmain.getLayout());
			cl.show(jp_cardmain,s_piezas[ind]);
			
		}
		
		else
		if (src == jtb_ejes)
	  {
	  	
	  	if (ejesOn==false)
	  	{
	  	jtb_ejes.setText("Ejes ON ");	
	  	
	  	tg_all.addChild(bg_ejes);
	  	
	  	ejesOn=true;
	  	}	
	  	else
	  	{
	  	jtb_ejes.setText("Ejes OFF");	
	  	bg_ejes.detach();
	  	ejesOn=false;	
	  	}
	  	
	  }
	  
				else
		if (src == jb_reset)
	  {
	  	v.t3d_reset.set(new Vector3f(0.f,-1.0f,-4.0f));
	  	tg_all.setTransform(v.t3d_reset);
	  	
	  	
	  	
	  }

		else
		if (src == jb_ejecutar)
	  {
	  	
			s=jt_texto.getText();
			ejecutar(s);	  	
	  	
	  	
	  }
	  
	  else
	  
		if (src == jb_eliminar)
	  	{







					if(	robot.quitarPieza(indice_robot))
						{
						
						 dlm_listmodel.remove(robot.piloto);	
						 cambios=true;

						}

						
						

	 		}
			
		else

		
						if (src == jb_abrir)
	  {


			
	  	
	  }
	
	
	
	
	
		
		
	}
	
	
	public void muestraAyuda()
	{
	
	
	  ayuda.show();
		ayuda.displayPage( "ayuda/ayuda.htm" ) ;

	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		
				if (e.getValueIsAdjusting()==false)		
				{
					int t = lista.getSelectedIndex();


					jl_metodos.setText(muestraMetodos(t));


					
				}
	}
	
	
	void mostrarDialogoTarea()
	{
				File archivo; 
					jfc_tarea.setDialogTitle("Guardar Archivo de Tarea"); 

					int returnVal=jfc_tarea.showSaveDialog(this);
					if (returnVal==JFileChooser.APPROVE_OPTION)
					{
						archivo = jfc_tarea.getSelectedFile();
						salvar_tarea(archivo.getName()	);
					}
					
		
		
		
		
		
	}
	
	
	String muestraMetodos(int indice)
	{
			int numero_de_pieza;
			String string_out=" ";
			
			numero_de_pieza = robot.identificadores[indice];
			
			switch(numero_de_pieza)
			{
				
			case v.BASE_CUADRADA_1DOF:
			case v.BASE_CIRCULAR:
			case v.JOINT_5:
			case v.JOINT_6:
			case v.BASE_WEDGE_ROTOR:
			case v.JOINT_3:
				string_out = " metodo:ROTAR";
			break; 	
			
			case v.JOINT_2:
				string_out = " metodos:ROTARSUP,ROTARINF";
			break; 	
			case v.JOINT:
				string_out = " metodos:ROTARBASE,ROTARMED";
			break; 	

			case v.JOINT_4:
				string_out = " met.:ROTARA,ROTARB,ROTARC";
			break; 	
			
			case v.LINK_EXTENSOR:
			case v.LINK_EXTENSOR2:
			case v.LINK_CON_CAJA:
			case v.RIEL:
				string_out = " metodo:DESPLAZAR";
			break;	
			
			case v.LINK_CON_CAJA_2:
			  string_out = "metodos:DESPLAZAR,ROTAR";
			break;
			case v.HAND:
			  string_out = "met:ROTAR,ABRIRMED,ABRIRSUP";
			break;
			case v.HAND_2:
			  string_out = "metodos:ROTAR,ABRIR,CERRAR";
			break;

			}
			
			
			return string_out;
		
		
		
	}
	
		public synchronized void ejecutar(String string)
	{
		
		String sentencias [] = new String[10];
		//StringTokenizer st = new StringTokenizer(string, ",. ();\t\n\r\f");
		StringTokenizer st = new StringTokenizer(string, " ;\t\n\r\f");
		
		
		boolean modo=false;
		cambios_tarea=true;
		
//		String nom;

	
		
		int i = 0;
		int j = 0;
		
		while (st.hasMoreTokens())
		{
			
			s_arreglo[i] = st.nextToken();
			i++;
				
		}


//cada cadena individualmente 

		sentencias[0]="";
		sentencias[1]="";
		sentencias[2]="";
		sentencias[3]="";
		


//recorre lista de instruccciones 
		Thread t = Thread.currentThread();

		for (int K = 0; K<i;K++)
		{

		int p_sen=0;
		
		StringTokenizer st2 = new StringTokenizer(s_arreglo[K],"." );

			while (st2.hasMoreTokens())
			{
			
				sentencias[p_sen]=st2.nextToken();
				p_sen++;
			}
		/*System.out.println("a continuacion escribo mis valores");
		System.out.println(sentencias[0]);
		System.out.println(sentencias[1]);
		System.out.println(sentencias[2]);
		System.out.println(sentencias[3]);*/

	
	
	
			for (int l=0;l<robot.piloto;l++)
			{
				//si sin iguales la pieza del robot y la instruccion
				//			String cad_aux=sentencias[0];
				if (sentencias[0].compareTo("PARALELO")==0)			
				{
				modo=true;	
				continue;	
				}
			
				else
				
				
				if (sentencias[0].compareTo("FINPARALELO")==0)			
				{
					
					try{
					
								t.sleep(500);
							
						 }
									
					catch(InterruptedException e){}					
									
					modo=false;	
					continue;	
				}
				else
				if (sentencias[0].compareTo("ESPERAR")==0)			
				{
					
					try{
								
								t.sleep(esperar(sentencias[1]));
							
						 }
									
					catch(InterruptedException e){}					
									
					continue;	
				}
				   
			   
			   else
			
				
				
				if (sentencias[0].compareTo(robot.nombre[l])==0)
				{
			
						
						int parte = robot.identificadores[l];
						
						switch (parte)
						{
						
							case v.BASE_CUADRADA_1DOF :
							{
						
							BaseCuadrada1DoF c= (BaseCuadrada1DoF)robot.componente.elementAt(l);
							int parametro = parametro(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotar")==0)
									{
								  
										c.rotar(parametro,resolucion,modo);
										
									}
								//	System.out.println("parametro="+parametro);
							}
							
							break;
							case v.BASE_WEDGE_ROTOR:
							{
						
							BaseWedgeRotor c= (BaseWedgeRotor)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotar")==0)
									{
										c.rotar(parametro,resolucion,modo);
										
									}
									
									
									
									//System.out.println("parametro="+parametro);
							}
							break;	
							
							case v.BASE_CIRCULAR :
							{
						
							BaseCircular c= (BaseCircular)robot.componente.elementAt(l);
							int parametro = Integer.parseInt(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotar")==0)
									{
										
										c.rotar(parametro,resolucion,modo);
										
									}
								//	System.out.println("parametro="+parametro);
							}
							break;
							
							case v.JOINT :
							{
						
							Joint c= (Joint)robot.componente.elementAt(l);
							int parametro = Integer.parseInt(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotarmed")==0)
									{
									c.rotar(parametro,resolucion,modo);
										
									}
									else
									if (sentencias[1].compareTo("rotarbase")==0)
									{
									c.rotar_a(parametro,resolucion,modo);
										
									}
									
									
							}
							break;
			
							case v.JOINT_2:
							{
						
							Joint2 c= (Joint2)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotarinf")==0)
									{
										c.rotar_a(parametro,resolucion,modo);
										
									}
									else
									if(sentencias[1].compareTo("rotarsup")==0)
									{
										c.rotar_b(parametro,resolucion,modo);
										
									}
									
									
									
								
							}
							break;						
							
							case v.JOINT_3:
							{
						
							Joint3 c= (Joint3)robot.componente.elementAt(l);
							int parametro = Integer.parseInt(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotar")==0)
									{
									c.rotar(parametro,resolucion,modo);
										
									}
									
									
							}
							break;
							case v.JOINT_4:
							{
						
							Joint4 c= (Joint4)robot.componente.elementAt(l);
							int parametro = Integer.parseInt(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotarC")==0)
									{
									c.rotar_cil_rite(parametro,resolucion,modo);
										
									}
									else
									if (sentencias[1].compareTo("rotarB")==0)
									{
									c.rotar_cil_med(parametro,resolucion,modo);
										
									}
									else
									if (sentencias[1].compareTo("rotarA")==0)
									{
									c.rotar_cil_left(parametro,resolucion,modo);
										
									}
									
							}
							break;
							case v.JOINT_5:
							{
						
							Joint5 c= (Joint5)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotar")==0)
									{
										c.rotar(parametro,resolucion,modo);
										
									}
									
									
						
									
									
							}
							break;
							
							case v.JOINT_6:
							{
						
							Joint6 c= (Joint6)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotar")==0)
									{
										c.rotar(parametro,resolucion,modo);
										
									}
									
									
							}
							break;
							
							
														
							case v.LINK_EXTENSOR:
							{
						
							LinkExtensor c= (LinkExtensor)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("extender")==0)
									{
										c.extender(parametro, resolucion, modo);
										
									}
									else
									if (sentencias[1].compareTo("contraer")==0)
									{
										c.contraer(parametro, resolucion, modo);
										
									}
									else
									if (sentencias[1].compareTo("desplazar")==0)
									{
										c.desplazar(parametro, resolucion, modo);
										
									}
								
									
									
							}
							break;
							
							case v.LINK_EXTENSOR2:
							{
						
							LinkExtensor2 c= (LinkExtensor2)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("extender")==0)
									{
										c.extender(parametro, resolucion, modo);
										
									}
									else
									if (sentencias[1].compareTo("contraer")==0)
									{
										c.contraer(parametro, resolucion,modo);
										
									}
									else
									if (sentencias[1].compareTo("desplazar")==0)
									{
										c.desplazar(parametro, resolucion,modo);
										
									}
						
									
									
							}
							break;
							
							case v.LINK_CON_CAJA:
							{
						
							Link_Con_Caja c= (Link_Con_Caja)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("extender")==0)
									{
										c.extender(parametro, resolucion, modo);
										
									}
									else
									if (sentencias[1].compareTo("contraer")==0)
									{
										c.contraer(parametro, resolucion,modo);
										
									}
										else
									if (sentencias[1].compareTo("desplazar")==0)
									{
										c.desplazar(parametro, resolucion,modo);
										
									}
								
						
									
									
							}
							break;
	
							case v.LINK_CON_CAJA_2:
							{
						
							Link_Con_Caja_2 c= (Link_Con_Caja_2)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("extender")==0)
									{
										c.extender(parametro, resolucion, modo);
										
									}
									else
									if (sentencias[1].compareTo("contraer")==0)
									{
										c.contraer(parametro, resolucion,modo);
										
									}
													else
									if (sentencias[1].compareTo("rotar")==0)
									{
										c.rotar(parametro, resolucion,modo);
										
									}
													else
									if (sentencias[1].compareTo("desplazar")==0)
									{
										c.desplazar(parametro, resolucion,modo);
										
									}									
						
									
									
							}
							break;							
							
							
							case v.HAND:
							{
						
							Hand c= (Hand)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotar")==0)
									{
										c.rotar(parametro,resolucion,modo);
										
									}
									else
									
									if (sentencias[1].compareTo("abrirmed")==0)
									{
										c.abrir_med(parametro,resolucion,modo);
									}
									
									if (sentencias[1].compareTo("abrirsup")==0)
									{
										c.abrir_sup(parametro,resolucion,modo);
									}
							
						
									
									
							}
							break;
							
							
							
							
							
							case v.HAND_2:
							{
						
							Hand2 c= (Hand2)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("rotar")==0)
									{
										c.rotar(parametro,resolucion,modo);
										
									}
									
									
									else
									if(sentencias[1].compareTo("abrir")==0)
									{
										c.abrir(parametro);
										
									}
									else
									if(sentencias[1].compareTo("cerrar")==0)
									{
										c.cerrar(parametro);
										
									}
						
									
									
							}
							break;
							
						
							case v.RIEL:
							{
						
							Riel c= (Riel)robot.componente.elementAt(l);
							
							int parametro = aEntero(sentencias[2]);
							int resolucion= velocidad(sentencias[3]);
					
									if (sentencias[1].compareTo("extender")==0)
									{
										c.extender(parametro, resolucion, modo);
										
									}
									else
									if (sentencias[1].compareTo("contraer")==0)
									{
										c.contraer(parametro, resolucion,modo);
										
									}
									else
									if (sentencias[1].compareTo("desplazar")==0)
									{
										c.desplazar_h(parametro, resolucion,modo);
										
									}


									
						
									
									
							}
							break;							
									

	
				

	
							
							
							


	
		
	
	
	
	
	
					
							
						}
			
			
				}//if
				
				
				
			}//for







		sentencias[0]="";
		sentencias[1]="";
		sentencias[2]="";
		sentencias[3]="";

		
		
		
		
}


		
	}
	
	
	int aEntero(String s)
	{
		
		if (s.compareToIgnoreCase("")==0)
		{
		return 0;
		}
		else
		{
		return Integer.parseInt(s);
		}
		
		
	}
	
	
	int parametro(String s)
	
	{
		
		if (s.compareTo("")==0)	
		{
			
			return 0	;	
			
		}
		else 
		
		return Integer.parseInt(s);
				
	}
	
	int esperar (String s)
	{
		
	  
	  if (s.compareTo("")==0)
			return 500;
			
		else 	
			return Integer.parseInt(s);
		
	}
	
	
	
	
	
	int velocidad(String velocidad)
	{
		
		if (velocidad.compareTo("LENTO")==0)
		return 100;
		else
		if (velocidad.compareTo("RAPIDO")==0)
		return 1;
		else
		return 10;
		
		
		
		
	}
	
	boolean existe_el_nombre(String g[], String nom, int indice)
		{
			
			
			for (int t = 0; t<indice; t++)
			{
				
				if (nom.compareTo(g[t])==0)
				{
				
//				System.out.println("Se repitio la cadena ");
				return true;
				
				}
				
				
			}
			
			return false;
			
		}

	
	
	
	
	
	public void salvar(String archivo)
	{
		
		FileOutputStream fos;
		ObjectOutputStream oos;
		FileInputStream fis;
		ObjectInputStream ois;
		RobotInfo ri;
		Datos data;
		/*
	  if (robot.vacio==true)
		{
		
					JOptionPane.showMessageDialog(this,"No hay Nada Que Salvar","Error",JOptionPane.ERROR_MESSAGE);			
		}
		
		else
		{
		
		File archivo; 
		int returnVal=jfc_robot.showSaveDialog(this);
			if (returnVal==JFileChooser.APPROVE_OPTION)
				{
						archivo = jfc_robot.getSelectedFile();
						salvar(archivo.getName()	);
				}
		}
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		int i;
		int k;
		
		
	
		try 
		{
			
			fos = new FileOutputStream(archivo);		
		  oos = new ObjectOutputStream(fos);
		  
		  
		  for (i=0 ;i<robot.piloto; i++)
		  
		  {

		  Componente c = (Componente)robot.componente.elementAt(i);
			data = c.getDatos();
			
			ri=new RobotInfo(robot.nombre[i], robot.identificadores[i], data,robot.piloto);
		  
	//	  System.out.println("salvando "+ri);
		  
		  
		  
		  oos.writeObject(ri);


			}


			cambios=false;//se guardaron los cambios
	 		oos.flush();
			oos.close();

			
		}
		
		catch (Exception e)
		{
			
		JOptionPane.showMessageDialog(this,"Error al Guardar el Archivo");			
			
		}
		
		
	
		
	}
	
	public void abrir(String archivo)
	{
		
		
		FileInputStream fis;
		ObjectInputStream ois;
		RobotInfo ri;
		Datos data;
		int tp;
		
		int i;
		int k;
		
		try
		{
			fis = new FileInputStream(archivo);		
		  ois = new ObjectInputStream(fis);
		//robot pieza 0 y cuantas piezas hay	
			ri = (RobotInfo)ois.readObject();
			crearPieza(ri);
			robot.adherirPieza(crearPieza(ri),0,ri.identificador);
			p(ri.nombre);
  		tp=ri.totalpiezas;

		  for (i = 1; i<tp ;i++)
		  {
			ri = (RobotInfo)ois.readObject();
			crearPieza(ri);
	//		System.out.println("creando pieza "+ri);
			robot.adherirPieza(crearPieza(ri),i,ri.identificador);
			p(ri.nombre);
			}
			ois.close();
	
			
		}

		catch (Exception e)
		{
					
		JOptionPane.showMessageDialog(this,"Error al Abrir el Archivo");			

		
		}
		
		
		
		
		
	}
	
	
	
		public void salvar_tarea(String archivo)
	{
		
		FileOutputStream fos;
		ObjectOutputStream oos;
		TareaInfo ti;
		
		
		int i;
		int k;
		
		
	
		try 
		{
			
			fos = new FileOutputStream(archivo);		
		  oos = new ObjectOutputStream(fos);
		  
		  
		  
			ti=new TareaInfo(jt_texto.getText(),1,1);
			
		  
	//	  System.out.println("salvando "+ti);
		  
		  
		  
		  oos.writeObject(ti);


			cambios_tarea=false;



	 		oos.flush();
			oos.close();

			
		}
		
		catch (Exception e)
		{
			
		JOptionPane.showMessageDialog(this,"Error al Guardar el Archivo");			
			
		}
	}
	
	
		public void abrir_tarea(String archivo)
	{
		
		
		FileInputStream fis;
		ObjectInputStream ois;
		TareaInfo ti = new TareaInfo();
		int tp;
		int i;
		int k;
	
//	System.out.println(archivo);
	
	
		try
		{
		
			fis = new FileInputStream(archivo);		
		  ois = new ObjectInputStream(fis);

			ti = (TareaInfo)ois.readObject();
			
			jt_texto.setText(ti.tarea);
			ois.close();
		}

		catch (Exception e)
		{
	
	
	
		JOptionPane.showMessageDialog(this,"Error al Abrir el Archivo");			
		}
		
	
	}
	
	
	public String formato(float valor)
	{
		String salida;	
		String pattern = "##.###";
		DecimalFormat formato = new DecimalFormat(pattern);
		return salida=formato.format(valor);
		
	}
	
	
	public void debenombrar()
	{
		
//		JOptionPane.showMessageDialog(this,"Facultad de Computacion. BUAP. \nEditor Simulador de Robots 2002, \ndiseñado e implementado por: \nDe los Angeles Salgado Jose Arturo \narturoas7@yahoo.com \nAsesor: Dr. Manuel Martin O.");			
		JOptionPane.showMessageDialog(this,"Debe Dar Nombre al Componente");			
		
}
	public void acercade()
	{
		
		JOptionPane.showMessageDialog(this,"Facultad de Computación. BUAP. \nEditor Simulador de Robots 2002, \ndiseñado e implementado por: \nDe los Angeles Salgado José Arturo \narturoas7@yahoo.com \nAsesor: Dr. Manuel Martín O.");			
		
  }
		public void elnombredebeserunico()
{
		
		JOptionPane.showMessageDialog(this,"El Nombre Ya Existe");			
		
}
	
	
	void p(String s)
	{
	
	   dlm_listmodel.addElement(s);
		
	}
	
	
	
		
	void print()
	{
	
	
//	System.out.println("");	
		
	}

	public static void main(String s[])
	{
		
		EditorSimulador es = new EditorSimulador ("Editor Simulador de Robots 2002");	
		
	}
	

	
}