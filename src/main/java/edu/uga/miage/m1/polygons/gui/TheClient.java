package edu.uga.miage.m1.polygons.gui;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import edu.uga.miage.m1.polygons.gui.command.Add;
import edu.uga.miage.m1.polygons.gui.command.Command;
import edu.uga.miage.m1.polygons.gui.command.Move;
import edu.uga.miage.m1.polygons.gui.command.TheInvoker;
import com.anasat.shapes.*;
import edu.uga.miage.m1.polygons.gui.JDrawingFrame.Shapes;

public class TheClient implements MouseListener, MouseMotionListener{

    private JDrawingFrame mFrame;

    private SimpleShape chosenShape;

    private TheInvoker invoker = new TheInvoker();

    public TheClient(String frameName) {
        this.mFrame = new JDrawingFrame(frameName, this);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED
                    && e.getKeyCode() == KeyEvent.VK_Z
                    && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
                // Handle Ctrl + Z action
                keyPressed(e);
            }
            return false; // Continue processing the event
        });
    }

    public JDrawingFrame getFrame() {
        return this.mFrame;
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     *
     * @param evt The associated mouse event.
     */
    public void mouseClicked(MouseEvent evt) {
        Shapes shape = null;
        shape = mFrame.getSelectedShape();
        SimpleShape s = null;
        switch (shape) {
            case SQUARE:
                s = new Square(evt.getX(), evt.getY());
                break;
            case TRIANGLE:
                s = new Triangle(evt.getX(), evt.getY());
                break;
            case CIRCLE:
                s = new Circle(evt.getX(), evt.getY());
                break;
            case CUBE:
                s = new Cube(evt.getX(), evt.getY());
                break;
            default:
                break;
        }
        if (s != null) {
            invoker.addCommand(new Add(mFrame, s));
            invoker.execute();
        }
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     *
     * @param evt The associated mouse event.
     */
    public void mouseEntered(MouseEvent evt) {
        // empty pour le moment
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     *
     * @param evt The associated mouse event.
     */
    public void mouseExited(MouseEvent evt) {
        mFrame.getLabel().setText(" ");
        mFrame.getLabel().repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     *
     * @param evt The associated mouse event.
     */
    public void mousePressed(MouseEvent evt) {
        int pressedX = evt.getX();
        int pressedY = evt.getY();

        for (SimpleShape shape : mFrame.getListOfShapes()) {
            int mX = shape.getX();
            int mY = shape.getY();
            Ellipse2D simpleShape = new Ellipse2D.Double(mX,mY , 50, 50);
            if (simpleShape.contains(pressedX, pressedY)) {
                chosenShape = shape;
                Command mCom = new Move(mX, mY , shape, mFrame);
                invoker.addCommand(mCom);
                break;
            }
        }

    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     *
     * @param evt The associated mouse event.
     */
    public void mouseReleased(MouseEvent evt) {
        if(chosenShape != null){
            this.invoker.getCommand().setDone(true);
            this.invoker.execute();
            chosenShape = null;
        }
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     *
     * @param evt The associated mouse event.
     */
    public void mouseDragged(MouseEvent evt) {
        if(chosenShape != null){
            int newX = evt.getX();
            int newY = evt.getY();
            Move mCom = (Move) this.invoker.getCommand();
            mCom.setNewX(newX);
            mCom.setNewY(newY);
            this.invoker.execute();
            mFrame.paintComponents(mFrame.getGraphics());
        }

    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     *
     * @param evt The associated mouse event.
     */
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        mFrame.getLabel().setText("(" + evt.getX() + "," + evt.getY() + ")");
        mFrame.getLabel().repaint();

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown()) {
            invoker.undo();
        }
    }
}
