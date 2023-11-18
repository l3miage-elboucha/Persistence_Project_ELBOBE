package edu.uga.miage.m1.polygons.gui.command;
import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;


/*each time we press a shape we have to get the shape and where it started and the frame
and we need to add this move command to the command list.
Once dragged we need to keep changing the x and y of the shape while dragging continue
Once released we need to remove it from the commands and add it to hisoty so we can undo it later*/

public class Move implements Command {
    private int startX;
    private int startY;

    private int newX;
    private int newY;

    private SimpleShape shape;
     private JDrawingFrame frame;

    public Move(int startX, int startY, SimpleShape shape, JDrawingFrame frame) {
        this.startX = startX;
        this.startY = startY;
        this.shape = shape;
        this.frame = frame;
    }

    @Override
    public void undo(){

    }
    @Override
    public void execute(){
    }

}
