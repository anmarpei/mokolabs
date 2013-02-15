package kitchen;

import java.io.File;

import business.urlAccessComponent;
import junit.framework.TestCase;

public class urlAccessVisorExp extends TestCase{

	public void testSeriousness() throws Exception {
        assertTrue( urlAccessComponent.testSeriousness( "SAD" ) );
        assertTrue( urlAccessComponent.testSeriousness( "SERIOUS" ) );
        assertTrue( urlAccessComponent.testSeriousness( "CRAZY" ) );
        assertTrue( !urlAccessComponent.testSeriousness( "FUNNY" ) );
	}
	
	public void testEncodeXml() throws Exception {
		String result = urlAccessComponent.testEncodeXml();
		assertNotNull(result);
	}
	
	public void testBuildAccessURL() throws Exception {
		String result = urlAccessComponent.buildAccessURL(new File("src/main/resources/acceso.xml"));
		assertNotNull(result);
	}
	
}
