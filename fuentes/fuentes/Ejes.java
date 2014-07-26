import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.Font;



public class Ejes extends Group
{

	private float radio = 0.003f;
	private float high  = 10.0f;
	Color3f rojo    = new Color3f(1.0f,0.0f,0.0f);
	Color3f amarillo   = new Color3f(0.0f,1.0f,0.0f);
	Color3f blanco  = new Color3f(1.0f,1.0f,0.0f);
	
	

		
	Ejes()
	{
		TransformGroup tg = new TransformGroup();
		TransformGroup tg_z = new TransformGroup();
		TransformGroup tg_text = new TransformGroup();
		TransformGroup tg_1 =new TransformGroup();
		TransformGroup tg_2 =new TransformGroup();
		TransformGroup tg_3 =new TransformGroup();
 
  	Transform3D	t3d = new Transform3D();
  	Transform3D t3d_t = new Transform3D();
  	
  	Material material = new Material(rojo, rojo, rojo, blanco, 64);
		Appearance appearance = new Appearance();
    
    Cylinder y = new Cylinder(radio,high,appearance);
 		appearance.setMaterial(material);
		addChild(y);
   	
		
		t3d.rotZ(Math.toRadians(90));
		tg.setTransform(t3d);
		material.setAmbientColor(blanco);
		material.setEmissiveColor(blanco);
		material.setDiffuseColor(blanco);
		appearance.setMaterial(material);
		Cylinder x = new Cylinder(radio,high,appearance);
		tg.addChild(x);
		
		
		addChild(tg);
		
		
		
		
		
		
		
		
		
		Cylinder z = new Cylinder(radio,high,appearance);
		t3d.rotX(Math.toRadians(90));
		tg_z= new TransformGroup(t3d);
		
		tg_z.addChild(z);
		
		addChild(tg_z);
		
		
		t3d.set(new Vector3f(0.0f,0.0f,3.5f));
		tg_text= new TransformGroup(t3d);
		
		Font3D font3D = new Font3D(new Font("Helvetica", Font.PLAIN, 1),new FontExtrusion());
    Text3D textgeom = new Text3D(font3D, new String("Z+"));
    textgeom.setAlignment(Text3D.ALIGN_CENTER);
    Shape3D text = new Shape3D(textgeom,appearance);
    tg_text.addChild(text);
    t3d_t.set(0.08f);
    t3d.mul(t3d_t);
    tg_text.setTransform(t3d);
    addChild(tg_text);
		


		t3d.set(new Vector3f(0.0f,1.0f,0.0f));
		tg_1.setTransform(t3d);
		Sphere y1 = new Sphere(0.03f,appearance);
		tg_1.addChild(y1);
		addChild(tg_1);
		


		t3d.set(new Vector3f(0.0f,2.0f,0.0f));
		tg_2.setTransform(t3d);
		Sphere y2 = new Sphere(0.03f,appearance);
		tg_2.addChild(y2);
		addChild(tg_2);

		t3d.set(new Vector3f(0.0f,3.0f,0.0f));
		tg_3.setTransform(t3d);
		Sphere y3 = new Sphere(0.03f,appearance);
		tg_3.addChild(y3);
		addChild(tg_3);



		
		Transform3D t3dr = new Transform3D();
		t3dr.set(new Vector3f(1.0f,0.0f,0.0f));
		TransformGroup  tg_des_x1 = new TransformGroup(t3dr);
		Sphere cx1 = new Sphere(0.03f,appearance);
		tg_des_x1.addChild(cx1);
		addChild(tg_des_x1);
	
		t3dr.set(new Vector3f(2.0f,0.0f,0.0f));
		TransformGroup  tg_des_x2 = new TransformGroup(t3dr);
		Sphere cx2 = new Sphere(0.03f,appearance);
		tg_des_x2.addChild(cx2);
		addChild(tg_des_x2);

			
		t3dr.set(new Vector3f(3.0f,0.0f,0.0f));
		TransformGroup  tg_des_x3 = new TransformGroup(t3dr);
		Sphere cx3 = new Sphere(0.03f,appearance);
		tg_des_x3.addChild(cx3);
		addChild(tg_des_x3);

		
		//  -x
		t3dr.set(new Vector3f(-1.0f,0.0f,0.0f));
		TransformGroup  tg_des_menos_x1 = new TransformGroup(t3dr);
		Sphere c_menosx1 = new Sphere(0.03f,appearance);
		tg_des_menos_x1.addChild(c_menosx1);
		addChild(tg_des_menos_x1);

			
		t3dr.set(new Vector3f(-2.0f,0.0f,0.0f));
		TransformGroup  tg_des_menos_x2 = new TransformGroup(t3dr);
		Sphere c_menosx2 = new Sphere(0.03f,appearance);
		tg_des_menos_x2.addChild(c_menosx2);
		addChild(tg_des_menos_x2);

		t3dr.set(new Vector3f(-3.0f,0.0f,0.0f));
		TransformGroup  tg_des_menos_x3 = new TransformGroup(t3dr);
		Sphere c_menosx3 = new Sphere(0.03f,appearance);
		tg_des_menos_x3.addChild(c_menosx3);
		addChild(tg_des_menos_x3);


//+z
   	
   	t3dr.set(new Vector3f(0.0f,0.0f,1.0f));
		TransformGroup  tg_des_z1 = new TransformGroup(t3dr);
		Sphere cz1 = new Sphere(0.03f,appearance);
		tg_des_z1.addChild(cz1);
		addChild(tg_des_z1);

			
		t3dr.set(new Vector3f(0.0f,0.0f,2.0f));
		TransformGroup  tg_des_z2 = new TransformGroup(t3dr);
		Sphere cz2 = new Sphere(0.03f,appearance);
		tg_des_z2.addChild(cz2);
		addChild(tg_des_z2);

		t3dr.set(new Vector3f(0.0f,0.0f,3.0f));
		TransformGroup  tg_des_z3 = new TransformGroup(t3dr);
		Sphere cz3 = new Sphere(0.03f,appearance);
		tg_des_z3.addChild(cz3);
		addChild(tg_des_z3);

   	
   	
	}	
	
	
	
	
}
		