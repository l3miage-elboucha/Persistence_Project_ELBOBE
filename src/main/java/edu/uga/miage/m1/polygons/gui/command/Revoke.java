package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

public class Revoke implements Command {
    //the receiver of our command
    private JDrawingFrame jDrawingFrame;

    public Revoke(JDrawingFrame jDrawingFrame){
        this.jDrawingFrame = jDrawingFrame;
    }

    public void execute() {
        jDrawingFrame.withdraw();
    }

}
