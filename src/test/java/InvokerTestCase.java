import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.uga.miage.m1.polygons.gui.command.TheInvoker;
import edu.uga.miage.m1.polygons.gui.command.Command;

class InvokerTestCase {

    private TheInvoker invoker;
    private DummyCommand dummyCommand;

    @BeforeEach
    void setUp() {
        invoker = new TheInvoker();
        dummyCommand = new DummyCommand();
    }

    @Test
    void addCommand() {
        invoker.addCommand(dummyCommand);
        assertEquals(1, invoker.getCommandes().size());
    }

    @Test
    void execute() {
        invoker.addCommand(dummyCommand);
        invoker.execute();
        assertTrue(dummyCommand.isExecuted());
        assertTrue(invoker.getHistoryOfCommands().contains(dummyCommand));
    }

    @Test
    void undo() {
        invoker.addCommand(dummyCommand);
        invoker.execute();
        dummyCommand.setDone(true);
        invoker.undo();
        assertFalse(dummyCommand.isExecuted());
        assertTrue(invoker.getHistoryOfCommands().isEmpty());
        assertEquals(1, invoker.getCommandes().size());
    }

    @Test
    void getCommand() {
        invoker.addCommand(dummyCommand);
        assertEquals(dummyCommand, invoker.getCommand());
    }

    private static class DummyCommand implements Command {
        private boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }

        @Override
        public void undo() {
            executed = false;
        }

        @Override
        public boolean isDone() {
            return executed;
        }

        @Override
        public void setDone(boolean done) {
            executed = done;
        }

        boolean isExecuted() {
            return executed;
        }
    }
}