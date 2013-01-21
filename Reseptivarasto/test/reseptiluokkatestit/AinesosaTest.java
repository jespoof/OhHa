package reseptiluokkatestit;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reseptivarasto.Ainesosa;

/**
 *
 * @author Johanna
 */
public class AinesosaTest {
    
    
    @Test
      public void nimiMaaraJaYksikkoOikein() {
      Ainesosa osa = new Ainesosa("jauho", "5 dl");
      
      String tulostus = osa.toString(); 

      assertEquals("  jauho, 5 dl", tulostus);
  }
}
