import java.io.* ;
import javax.swing.* ;
import java.awt.* ;
import javax.swing.event.* ;
import java.awt.event.* ;
import java.net.URL ;
import java.awt.font.*;
 

public class JavaBrowser extends JFrame

      implements HyperlinkListener,

                 ActionListener {


   private JEditorPane browser =new JEditorPane();

  
   public JavaBrowser ( )
    {


			Font fuente= new Font("ARIAL",Font.PLAIN,20);

		
      setTitle("Ayuda - Editor Simulador de Robots");


      setBounds(50,50,800,600);


             browser.setEditable(false);

    browser.setFont(fuente);


      browser.addHyperlinkListener(this);

   JPanel panel = new JPanel ( ) ;

   panel.setLayout 

     (new BoxLayout(panel,BoxLayout.X_AXIS));


   Container cp = getContentPane ( ) ;


   cp.add (panel, "Center" ) ;

   cp.add (new JScrollPane (browser,

     JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,

     JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS)); 

 

}

 





 


 






public void displayPage ( String page ) {


if ( page != null && page.trim().length() > 0 ) {

 



 

File localFile = new File ( page ) ;

 


if ( localFile.exists ( ) && localFile.isFile () ) {


 

page = "file:///" + localFile.getAbsolutePath ( ) ;

try {

browser.setPage ( page ) ;

}

catch ( Exception e1 ) {


browser.setText ( "error:" + page + "\n" + 

"Error:" + e1.getMessage ( ) ) ;

}

}

else {


try {

URL url = new URL ( page ) ;

browser.setPage ( url ) ;

}

catch ( Exception e ) {


browser.setText ( "pagina no valida:" + page + "\n" + 

"Error:" + e.getMessage ( ) ) ;

}

 

}

 

}

else {

browser.setText ( "pagina no valida:" + page ) ;

}

 

}

 

 

 

public void hyperlinkUpdate ( HyperlinkEvent e ) {


 

if ( e.getEventType ( ) == HyperlinkEvent.EventType.ACTIVATED ) {

try {


URL url = e.getURL ( ) ;

browser.setPage ( url ) ;

 displayPage(url.toString()); 

}

catch ( Exception exc ) {

}

}

}

 

public void actionPerformed ( ActionEvent e ) {


String page = "..\\oconnor.htm" ;

try {

 


displayPage ( page ) ;

}

catch ( Exception exc ) {

browser.setText ( "error--" + page + "\n" + 

"Error:" + exc.getMessage ( ) ) ;

}

}

} 

 

