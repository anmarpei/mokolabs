package kitchen;


import java.util.Date;

import junit.framework.TestCase;

public class castings extends TestCase {

	public void testSeriousness() throws Exception {
        
		//131820130000490021
		Long kaka = 131820130000490021L;
		String value =  String.valueOf(kaka);
		
		System.out.println(value);
		//assertNotNull(value);
		
		
		
	}
	
	
	public void testSeriousness2() throws Exception {
    
		String queryWhere = "WHERE  EXP.ANYO_ENTRADA = :ANYO_ENTRADA AND EXP.ID_EXPEDIENTE = :ID_EXPEDIENTE";
		
		int fromIndex=0;
		for (int i = 0; i < 2; i++) {
			
			
			int beginIndex = queryWhere.indexOf(":", fromIndex);
			int endIndex = queryWhere.indexOf(" ", beginIndex);
			
			if(endIndex==-1){
				endIndex = queryWhere.length();
			}
			
			//131820130000490021
			Long kaka = 131820130000490021L;
			String value =  String.valueOf(kaka);
			
			System.out.println(queryWhere.substring(beginIndex+1,endIndex));
			
			fromIndex = endIndex+1;
			//assertNotNull(value);
		}
		
		
	}
	
	/**
	 * 
	 * Se requiere que se añada en la peticion el campo opcional y de tipo date "Fecha-alta-aporta" fecha alta aportación. 
		Este campo ha de tener el comportamiento siguiente:
		 
		- Si la fecha introducida por el usuario coincide con la del sistema o es anterior a esta, la aportacion se puede 
		realizar.
		- Si la fecha introducida por el usuario  es anterior a la fecha de entrada de la instancia, se lanzará un error indic
		ándolo y no se realizará la aportación.
		- Si la fecha introducida por el usuario es posterior a la del sistema, se lanzará un error indicandolo y no se 
		realizara la aportacion.
		- Si no se informa el campo, la aportacion se realizará con la fecha del sistema.
	 * 
	 * 
	 * @throws Exception
	 */
	public void seriousness3(Date fechaIntroducida, Date fechaEntradaInstancia) throws Exception {
	    
		//Fecha sistema
		Date systemDate = new Date();
		systemDate.setYear(2013);
		Date otherDate = fechaIntroducida;//new Date(2013, 01, 05);
		
		System.out.println("fechaIntroducida: "+otherDate);
		System.out.println("fechaEntradaInstancia: "+fechaEntradaInstancia);
		
		if(otherDate!=null){
			if(otherDate.before(systemDate) || otherDate.equals(systemDate)){
				//Se puede realizar la aportacion
				if(otherDate.before(fechaEntradaInstancia)){
					//Error indicando que la fecha introducida es anterior a la fecha de entrada de la instancia...
					System.out.println("Error indicando que la fecha introducida es anterior a la fecha de entrada de la instancia");
				} else{
					//Se realiza la aportación!!! ;)
					System.out.println("Se realiza la aportación!!! ;)");
				}
				
			} else {
				//Lanzar error, no se puede realizar la aportacion...
				System.out.println("Lanzar error, no se puede realizar la aportacion");
			}//fin else
		}else{
			//Se inserta con la fecha del sistema
			System.out.println("Se inserta con la fecha del sistema");
		}//fin else
		
	}
	
	public void testSeriousness3() throws Exception {
	    //CASO: fechaIntroducida: actual, fechaInstancia: actual
		System.out.println("CASO: fechaIntroducida: actual, fechaInstancia: actual");
		seriousness3(new Date(2013-1900,06,06), new Date(2013-1900,06,06));
		
		//CASO: fechaIntroducida: anterior a fechainstancia, fechaInstancia: actual
		System.out.println("CASO: fechaIntroducida: anterior a fechainstancia, fechaInstancia: actual");
		seriousness3(new Date(2013-1900,05,06), new Date(2013-1900,06,06));
		
		//CASO: fechaIntroducida: nula, fechaInstancia: actual
		System.out.println("CASO: fechaIntroducida: nula, fechaInstancia: actual");
		seriousness3(null, new Date(2013-1900,06,06));
		
		//CASO: fechaIntroducida: posterior a fecha instancia, fechaInstancia: actual
		System.out.println("CASO: fechaIntroducida: posterior a fecha instancia, fechaInstancia: actual");
		seriousness3(new Date(2013-1900,06,07), new Date(2013-1900,06,06));
		
	}
	
}
