package edu.uga.miage.m1.polygons.gui.command;

public class RevokeControl {
    protected Command command;

    public RevokeControl(Command command) {
        this.command = command;
    }

    public void undo(){
        command.execute();
    }


}
