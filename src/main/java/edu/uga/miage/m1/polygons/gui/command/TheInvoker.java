package edu.uga.miage.m1.polygons.gui.command;

import java.util.ArrayList;
import java.util.List;

public class TheInvoker {
    
    private final List<Command> commandes = new ArrayList<>();

    private final List<Command> historyOfCommands = new ArrayList<>();

    public void addCommand(Command commande) {
        this.commandes.add(commande);
    }

    public void execute() {
        if (!this.commandes.isEmpty()) {
            Command c = this.commandes.get(this.commandes.size() - 1);
            c.execute();
            this.commandes.remove(c);
            this.historyOfCommands.add(c);
        }
    }

    public void undo() {
        if (!this.historyOfCommands.isEmpty()) {
            Command c = this.historyOfCommands.get(this.historyOfCommands.size() - 1);
            c.undo();
            this.historyOfCommands.remove(c);
            this.commandes.add(c);
        }
    }

    public Command getCommand() {
        return this.commandes.get(this.commandes.size() - 1);
    }

}
