import edu.uga.miage.m1.polygons.gui.shapes.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTestCase {
    @Test
    @DisplayName("Test de la methode getX() de la classe Square")
    void testGetX() {
        System.out.println("getX");
        Square instance = new Square(15, 15);
        int expResult = 15 - 25;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("Test de la methode getY() de la classe Square")
    void testGetY() {
        System.out.println("getY");
        Square instance = new Square(15, 15);
        int expResult = 15 - 25;
        int result = instance.getY();
        assertEquals(expResult, result);
    }
}
