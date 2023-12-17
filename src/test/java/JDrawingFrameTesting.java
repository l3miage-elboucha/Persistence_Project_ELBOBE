import org.junit.jupiter.api.Test;
import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.TheClient;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class JDrawingFrameTest {
    
    private TheClient client = new TheClient("Test Frame");

    /*
    @Test
    void testExportToXML() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test Frame", client);

        // Trigger the exportToXML method
        drawingFrame.exportToXML();

        // Verify that the XML file (dessin.xml) is created and exists in the file system
        File xmlFile = new File("dessin.xml");
        assertTrue(xmlFile.exists());

        // Test the content of the XML file
        try (BufferedReader reader = new BufferedReader(new FileReader(xmlFile))) {
            StringBuilder xmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlContent.append(line).append("\n");
            }

            // Define the expected XML content
            String expectedXMLContent = "<?xml version=\"1.0\" ?>\n<dessin>\n</dessin>\n";

            // Compare the actual XML content with the expected content
            assertEquals(expectedXMLContent, xmlContent.toString());
        } catch (Exception e) {
            fail("Failed to read or compare XML content: " + e.getMessage());
        }
    }

    @Test
    void testExportToJSON() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test Frame", client);

        // Trigger the exportToJSON method
        drawingFrame.exportToJSON();

        // Verify that the JSON file (dessin.json) is created and exists in the file system
        File jsonFile = new File("dessin.json");
        assertTrue(jsonFile.exists());
    }*/
}
