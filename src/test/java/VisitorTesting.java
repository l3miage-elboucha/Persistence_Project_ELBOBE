import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

class VisitorTest {

    @Test
    @DisplayName("Test Circle JSONvisitor")
    void testJsonVisitForCircle() {
        Circle circle = new Circle(26, 26);
        JSonVisitor visiteur = new JSonVisitor();

        visiteur.visit(circle);

        String expectedResult = "{\n" +
                "    \"type\": \"circle\",\n" +
                "    \"x\": 1,\n" +
                "    \"y\": 1\n" +
                "}";
        String result = visiteur.getRepresentation();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test Square JSONvisitor")
    void testJsonVisitSquare() {
        Square square = new Square(27, 27);
        JSonVisitor visitor = new JSonVisitor();

        visitor.visit(square);

        String expectedResult = "{\n" +
                "    \"type\": \"square\",\n" +
                "    \"x\": 2,\n" +
                "    \"y\": 2\n" +
                "}";
        String result = visitor.getRepresentation();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test Triangle JSONvisitor")
    void testJsonVisitTriangle() {
        Triangle triangle = new Triangle(25, 25);
        JSonVisitor visitor = new JSonVisitor();

        visitor.visit(triangle);

        String expectedResult = "{\n" +
                "    \"type\": \"triangle\",\n" +
                "    \"x\": 0,\n" +
                "    \"y\": 0\n" +
                "}";
        String result = visitor.getRepresentation();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test Circle XMLvisitor")
    void testXmlVisitCircle() {
        Circle circle = new Circle(25, 25);
        XMLVisitor visitor = new XMLVisitor();

        visitor.visit(circle);

        String expectedResult = "<shape><type>circle</type><x>0</x><y>0</y></shape>";
        String result = visitor.getRepresentation();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test Square XMLvisitor")
    void testXmlVisitSquare() {
        Square square = new Square(26,26);
        XMLVisitor visitor = new XMLVisitor();

        visitor.visit(square);

        String expectedResult = "<shape><type>square</type><x>1</x><y>1</y></shape>";
        String result = visitor.getRepresentation();
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test Triangle XMLvisitor")
    void testXmlVisitTriangle() {
        Triangle triangle = new Triangle(27,27);
        XMLVisitor visitor = new XMLVisitor();

        visitor.visit(triangle);

        String expectedResult = "<shape><type>triangle</type><x>2</x><y>2</y></shape>";
        String result = visitor.getRepresentation();
        assertEquals(expectedResult, result);
    }

}