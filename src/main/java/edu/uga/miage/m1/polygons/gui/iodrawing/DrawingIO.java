package edu.uga.miage.m1.polygons.gui.iodrawing;

import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

public class DrawingIO {

    private JFileChooser fileChooser;
    private List<SimpleShape> importedShapes = new ArrayList<>();
    private final transient XMLVisitor xmlVisitor;
    private final transient JSonVisitor jsonVisitor;
    private static final Logger LOGGER = Logger.getLogger(DrawingIO.class.getName());

    public DrawingIO() {
        jsonVisitor = new JSonVisitor();
        xmlVisitor = new XMLVisitor();
        importedShapes = new ArrayList<>();
        fileChooser = new JFileChooser();
    }

    public void exportToJSON(List<SimpleShape> shapesList) {
        try (FileWriter writer = new FileWriter("dessin.json")) {
            writer.write("{\n\t\"shapes\": [\n\t");
            Iterator<SimpleShape> iterator = shapesList.iterator();

            while (iterator.hasNext()) {
                SimpleShape shape = iterator.next();

                if (shape instanceof Visitable visitable) {
                    visitable.accept(jsonVisitor);
                    writer.write("\t\t{\n\t\t\t\"shape\": \n");
                    writer.write(jsonVisitor.getRepresentation());

                    if (iterator.hasNext()) {
                        writer.write("\n\t\t},\n");
                    } else {
                        writer.write("\n\t\t}\n");
                    }
                }
            }

            writer.write("\t]\n}");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "erreur:", e);
        }
    }

    // Check if the shape already exists in the XML file (to avoid duplicates)
    private boolean shapeExistsInXML(SimpleShape shape, List<SimpleShape> shapesList) {
        for (SimpleShape existingShape : shapesList) {
            // Compare x and y coordinates to determine if the shape already exists
            if (existingShape.getX() == shape.getX() && existingShape.getY() == shape.getY()) {
                return true; // Shape with the same coordinates already exists
            }
        }
        return false; // Shape does not exist in the XML file
    }

    public void exportToXML(List<SimpleShape> shapesList) {
        try (FileWriter writer = new FileWriter("dessin.xml")) {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlWriter = factory.createXMLStreamWriter(writer);
            xmlWriter.writeStartDocument();
            xmlWriter.writeCharacters("\n");
            xmlWriter.writeStartElement("dessin");

            for (SimpleShape shape : shapesList) {
                // Check if the shape with the same x and y coordinates already exists in the XML file
                    if (shape instanceof Visitable visitable) {
                        visitable.accept(xmlVisitor);
                        xmlWriter.writeCharacters("\n\t");
                        writer.write(xmlVisitor.getRepresentation());
                }
            }
            xmlWriter.writeCharacters("\n");
            xmlWriter.writeEndElement();
            xmlWriter.writeEndDocument();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "erreur:", e);
        }
    }

    public List<SimpleShape> importFromXML(String filePath, List<SimpleShape> drawnShapes) {
        List<SimpleShape> importedShapes = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Load the XML document from the file
            Document document = documentBuilder.parse(new File(filePath));

            // Get the "dessin" element
            Element dessinElement = document.getDocumentElement();

            // Get the "shape" elements
            NodeList shapeList = dessinElement.getElementsByTagName("shape");

            // Iterate over the "shape" elements
            for (int i = 0; i < shapeList.getLength(); i++) {
                Node shapeNode = shapeList.item(i);

                if (shapeNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element shapeElement = (Element) shapeNode;

                    // Get the properties of the shape
                    String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
                    int x = getIntegerContent(shapeElement, "x") + 25;
                    int y = getIntegerContent(shapeElement, "y") + 25;

                    // Create a SimpleShape instance
                    SimpleShape shape = createShape(type, x, y);

                    // Check if the shape already exists in the drawnShapes list
                    if (!shapeExistsInList(shape, drawnShapes)) {
                        // Add the shape to the importedShapes list
                        importedShapes.add(shape);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error:", e);
        }

        System.out.println("These are the imported shapes: " + importedShapes);
        return importedShapes;
    }
    private boolean shapeExistsInList(SimpleShape shape, List<SimpleShape> shapeList) {
        for (SimpleShape existingShape : shapeList) {
            if (existingShape.getX() == shape.getX() && existingShape.getY() == shape.getY()) {
                return true; // Shape with the same coordinates already exists in the list
            }
        }
        return false; // Shape does not exist in the list
    }

    public void showImportDialog(List<SimpleShape> shapesList) {
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));
        fileChooser.setDialogTitle("Choose an XML file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            importedShapes = importFromXML(filePath, shapesList);
            // Add the imported shapes to shapesList
            shapesList.addAll(importedShapes);
        }
    }
    
    private int getIntegerContent(Element element, String tagName) {
        return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent());
    }
    
    private SimpleShape createShape(String type, int x, int y) {
        switch (type) {
            case "circle":
                return new Circle(x, y);
            case "square":
                return new Square(x, y);
            case "triangle":
                return new Triangle(x, y);
            default:
                return new Cube(x, y);
        }
    }

}
