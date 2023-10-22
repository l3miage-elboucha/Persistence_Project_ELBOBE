package edu.uga.miage.m1.polygons.gui.test.java;

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
    @DisplayName("Test visit Circle")
    void testJsonVisitCircle() {
        Circle circle = new Circle(25, 25);
        JSonVisitor visitor = new JSonVisitor();

        visitor.visit(circle);

        String expected = "{\n" +
                "    \"type\": \"circle\",\n" +
                "    \"x\": 0,\n" +
                "    \"y\": 0\n" +
                "}";
        String actual = visitor.getRepresentation();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test visit square")
    void testJsonVisitSquare() {
        Square square = new Square(25, 25);
        JSonVisitor visitor = new JSonVisitor();

        visitor.visit(square);

        String expected = "{\n" +
                "    \"type\": \"square\",\n" +
                "    \"x\": 0,\n" +
                "    \"y\": 0\n" +
                "}";
        String actual = visitor.getRepresentation();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test visit triangle")
    void testJsonVisitTriangle() {
        Triangle triangle = new Triangle(25, 25);
        JSonVisitor visitor = new JSonVisitor();

        visitor.visit(triangle);

        String expected = "{\n" +
                "    \"type\": \"triangle\",\n" +
                "    \"x\": 0,\n" +
                "    \"y\": 0\n" +
                "}";
        String actual = visitor.getRepresentation();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test visit Circle xml")
    void testXmlVisitCircle() {
        Circle circle = new Circle(25, 25);
        XMLVisitor visitor = new XMLVisitor();

        visitor.visit(circle);

        String expected = "<shape><type>circle</type><x>0</x><y>0</y></shape>";
        String actual = visitor.getRepresentation();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test visit Square xml")
    void testXmlVisitSquare() {
        Square square = new Square(25,25);
        XMLVisitor visitor = new XMLVisitor();

        visitor.visit(square);

        String expected = "<shape><type>square</type><x>0</x><y>0</y></shape>";
        String actual = visitor.getRepresentation();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test visit Triangle xml")
    void testXmlVisitTriangle() {
        Triangle triangle = new Triangle(25,25);
        XMLVisitor visitor = new XMLVisitor();

        visitor.visit(triangle);

        String expected = "<shape><type>triangle</type><x>0</x><y>0</y></shape>";
        String actual = visitor.getRepresentation();
        assertEquals(expected, actual);
    }

}