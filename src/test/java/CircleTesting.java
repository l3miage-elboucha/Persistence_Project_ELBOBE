


import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircleTesting {

    @Test
    @DisplayName("Test de la m�thode getX() de la classe Circle")
    public void testGetX() {
        System.out.println("getX");
        Circle instance = new Circle(5, 5);
        int expResult = 5 - 25;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("Test de la m�thode getY() de la classe Circle")
    public void testGetY() {
        System.out.println("getY");
        Circle instance = new Circle(5, 5);
        int expResult = 5 - 25;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("Test de la m�thode accept() avec JSON de la classe Circle")
    public void testAcceptJSON() {
        System.out.println("accept");
        Circle instance = new Circle(5, 5);
        JSonVisitor visitor = new JSonVisitor();
        instance.accept(visitor);
        String result = visitor.getRepresentation();
        String expResult = "{\n" +
                "    \"type\": \"circle\",\n" +
                "    \"x\": -20,\n" +
                "    \"y\": -20\n" +
                "}";
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("Test de la m�thode accept() avec XML de la classe Circle")
    public void testAcceptXML() {
        System.out.println("accept");
        Circle instance = new Circle(5, 5);
        XMLVisitor visitor = new XMLVisitor();
        instance.accept(visitor);
        String result = visitor.getRepresentation();
        String expResult = "<shape><type>circle</type><x>-20</x><y>-20</y></shape>";
        assertEquals(expResult, result);
    }
}


