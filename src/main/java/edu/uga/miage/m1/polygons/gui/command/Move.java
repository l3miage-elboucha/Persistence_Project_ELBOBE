package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

/*each time we press a shape we have to get the shape and where it started and the frame
and we need to add this move command to the command list.
Once dragged we need to keep changing the x and y of the shape while dragging continue
Once released we need to remove it from the commands and add it to hisoty so we can undo it later*/

public class Move implements Command {
    private int oldX;
    private int oldY;

    private int newX;
    private int newY;

    private SimpleShape shape;
    private JDrawingFrame frame;

    public Move(int oldX, int oldY, SimpleShape shape, JDrawingFrame frame) {
        this.oldX = oldX;
        this.oldY = oldY;
        this.shape = shape;
        this.frame = frame;
    }

    @Override
    public void undo() {
        shape.dragBack(oldX, oldY);
        frame.repaint();
    }

    @Override
    public void execute() {
        shape.drag(newX, newY);
    }

}
