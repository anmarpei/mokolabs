package business;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class urlAccessComponent {

	
	/**
	  * This function tests the seriousness of a String.
	  * Returns false if the string contains the word
	  * "FUNNY", returns true otherwise.
	  */
	  public static boolean testSeriousness( String text ) {
	    return !text.toUpperCase().contains( "FUNNY" );
	  }
	  
	  public static String testEncodeXml() {
	    File xmlFile = new File("src/main/resources/acceso.xml");
		return encodeXml(xmlFile);
	  }
		  
	  
	  public static String buildAccessURL(File xmlFile){
		  
		  String dataXml = encodeXml(xmlFile);
		  
		  //http://localhost:8080/piae/visorexpediente/carga.htm?
		  String protocol = "http://";
		  String hostname = "localhost";
		  String hostportnumber = "8080";
		  String appName = "piae";
		  String viewer = "visorexpedientes";
		  String serviceName = "carga";
		  String htmExtension = ".htm";
		  
		  String parametro = "url=";
		  
		  String startParams = "?";
		  
		  System.out.println("\n");
		  System.out.println("URL de acceso a visorExpediente:");
		  String viewerURL = protocol+hostname+":"+hostportnumber+"/"+appName+"/"+viewer+"/"+serviceName+htmExtension+startParams+parametro+dataXml;
		  
		  System.out.println(viewerURL);
		  
		  return viewerURL;
		  
	  }
	  
	  public static String encodeXml(File xmlFile){
		  
		  //Se crea un SAXBuilder para poder parsear el archivo
		  SAXBuilder builder = new SAXBuilder();
		  
		  //Se crea el documento a traves del archivo
		  Document document = null;
		try {
			document = (Document) builder.build( xmlFile );
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  //Se obtiene la raiz 'VisorAuth'
		  Element rootNode = document.getRootElement();
		  
		  //Se obtiene el atributo 'authinfo' que esta en el tag 'VisorAuth'...
          String authinfo = rootNode.getChild("authinfo").getValue();
          String datetime = rootNode.getChild("datetime").getValue();
          String idapli = rootNode.getChild("idapli").getValue();
          String codexp = rootNode.getChild("codexp").getValue();
          String coduni = rootNode.getChild("coduni").getValue();
          
          //Password pactada entre app extrerna y PIAE...
          //TODO: De momento se pone una psw de ejemplo...
          String pwd = "instalador";
          
          
          //Para validar el acceso por tiempo expirado
          datetime = String.valueOf((System.currentTimeMillis() / 1000L));
          
          String urlAuthinfo = String.format("%s#%s#%s#%s#%s", idapli, codexp, coduni, pwd, String.valueOf(datetime));
          try {
        	  urlAuthinfo = convertToMD5(urlAuthinfo);
          } catch (Exception e) {
				// TODO Auto-generated catch block
        	  e.printStackTrace();
          }
		  
          //Creamos un elemento root
          Element root = new Element("VisorAuth");
          
          Element element0 = new Element("authinfo").setText(urlAuthinfo);
          root.addContent(element0);
          
          
          
          Element element1 = new Element("datetime").setText(datetime);
          root.addContent(element1);
          
          Element element2 = new Element("idapli").setText(idapli);
          root.addContent(element2);
          
          Element element3 = new Element("codexp").setText(codexp);
          root.addContent(element3);
          
          Element element4 = new Element("coduni").setText(coduni);
          root.addContent(element4);
          
          Document doc = new Document(root);//Creamos el documento
          
          XMLOutputter outputter3 = new XMLOutputter(Format.getRawFormat());
          String xmlString3 = outputter3.outputString(doc);
          
          System.out.println("Cadena sin codificar:");
          System.out.println(xmlString3);
          
          System.out.println("Cadena codificada a Base64:");
          System.out.println(new String(Base64.encodeBase64(xmlString3.trim().getBytes())));
          
          
//          System.out.println(datetime);
//          System.out.println(System.currentTimeMillis() / 1000L);
          
          //return xmlString3.trim();
          return new String(Base64.encodeBase64(xmlString3.trim().getBytes()));
	  }
	  
	  private static String convertToMD5(String clear) throws Exception {
		  MessageDigest md = MessageDigest.getInstance("MD5");
		  byte[] b = md.digest(clear.getBytes());

		  int size = b.length;
		  StringBuffer h = new StringBuffer(size);
		  for (int i = 0; i < size; i++) {
		  int u = b[i] & 255;
		  if (u < 16) {
		  h.append("0" + Integer.toHexString(u));
		  } else {
		  h.append(Integer.toHexString(u));
		  }
		  }
		  return h.toString();
	  }
	  
	  
}
