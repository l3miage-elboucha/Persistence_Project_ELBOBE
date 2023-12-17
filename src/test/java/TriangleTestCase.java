import com.anasat.shapes.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleTestCase {
    @Test
    @DisplayName("Test de la methode getX() de la classe Triangle")
    void testGetX() {
        System.out.println("getX");
        Triangle instance = new Triangle(15, 15);
        int expResult = 15 - 25;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("Test de la methode getY() de la classe Triangle")
    void testGetY() {
        System.out.println("getY");
        Triangle instance = new Triangle(15, 15);
        int expResult = 15 - 25;
        int result = instance.getY();
        assertEquals(expResult, result);
    }
}
