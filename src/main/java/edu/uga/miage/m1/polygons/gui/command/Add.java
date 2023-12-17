package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import com.anasat.shapes.*;

public class Add implements Command {
    
    private JDrawingFrame frame;

    private SimpleShape shape;

    private boolean done = true;

    public Add(JDrawingFrame frame, SimpleShape shape) {
        this.frame = frame;
        this.shape = shape;
    }

    @Override
    public void execute() {
        this.frame.addShape(this.shape);
    }

    @Override
    public void setDone(boolean d){
        this.done = d;
    }

    public boolean isDone(){
        return done;
    }

    @Override
    public void undo() {
        this.frame.removeShape(this.shape);
    }
}
