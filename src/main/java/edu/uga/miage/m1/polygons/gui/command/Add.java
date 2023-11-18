package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class Add implements Command {
    
    private JDrawingFrame frame;

    private SimpleShape shape;

    public Add(JDrawingFrame frame, SimpleShape shape) {
        this.frame = frame;
        this.shape = shape;
    }

    @Override
    public void execute() {
        this.frame.addShape(this.shape);
    }

    @Override
    public void undo() {
        this.frame.removeShape(this.shape);
    }
}
