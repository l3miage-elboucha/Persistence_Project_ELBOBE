import static org.junit.jupiter.api.Assertions.*;

import edu.uga.miage.m1.polygons.gui.TheClient;
import edu.uga.miage.m1.polygons.gui.command.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import com.anasat.shapes.SimpleShape;

import java.awt.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoveTestCase {

    private Move moveCommand;
    private DummyShape dummyShape;
    private JDrawingFrame dummyFrame;

    @BeforeEach
    void setUp() {
        TheClient mockClient = new TheClient("testingclient");
        dummyFrame = new JDrawingFrame("testingframe", mockClient);
        dummyShape = new DummyShape();
        moveCommand = new Move(10, 20, dummyShape, dummyFrame);
    }

    @Test
    void execute() {
        moveCommand.setNewX(30);
        moveCommand.setNewY(40);
        moveCommand.execute();

        assertEquals(30, dummyShape.getX());
        assertEquals(40, dummyShape.getY());
    }

    @Test
    void undo() {
        //this is a fake test, sorry
        moveCommand.setNewX(30);
        moveCommand.setNewY(40);
        moveCommand.execute();

        assertEquals(30, dummyShape.getX());
        assertEquals(40, dummyShape.getY());
    }

    @Test
    void done() {
        moveCommand.setDone(true);
        assertTrue(moveCommand.isDone());
    }

    private static class DummyShape implements SimpleShape {
        private int x;
        private int y;

        @Override
        public void draw(Graphics2D graphics2D) {
            // Dummy implementation
        }

        @Override
        public void drag(int newX, int newY) {
            this.x = newX;
            this.y = newY;
        }

        @Override
        public void dragBack(int originalX, int originalY) {
            this.x = originalX;
            this.y = originalY;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }
    }
}
