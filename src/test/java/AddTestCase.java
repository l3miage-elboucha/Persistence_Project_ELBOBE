import static org.junit.jupiter.api.Assertions.*;

import edu.uga.miage.m1.polygons.gui.TheClient;
import edu.uga.miage.m1.polygons.gui.command.Add;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import com.anasat.shapes.SimpleShape;

import java.awt.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddTestCase {

    private Add addCommand;
    private DummyShape dummyShape;
    private JDrawingFrame dummyFrame;

    @BeforeEach
    void setUp() {
        TheClient mockClient = new TheClient("testingclient");
        dummyFrame = new JDrawingFrame("testingframe", mockClient);
        dummyShape = new DummyShape();
        addCommand = new Add(dummyFrame, dummyShape);
    }

    @Test
    void execute() {
        addCommand.execute();
        assertTrue(dummyFrame.getListOfShapes().contains(dummyShape));
    }

    @Test
    void done() {
        addCommand.setDone(true);
        assertTrue(addCommand.isDone());
    }

    private static class DummyShape implements SimpleShape {
        @Override
        public void draw(Graphics2D graphics2D) {
            // Dummy implementation
        }

        @Override
        public void drag(int newX, int newY) {
            // Dummy implementation
        }

        @Override
        public void dragBack(int originalX, int originalY) {
            // Dummy implementation
        }

        @Override
        public int getX() {
            return 0; // Dummy implementation
        }

        @Override
        public int getY() {
            return 0; // Dummy implementation
        }
    }
}
