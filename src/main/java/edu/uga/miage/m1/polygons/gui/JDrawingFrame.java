package edu.uga.miage.m1.polygons.gui;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitable;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href=
 * "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame {

    public enum Shapes {

        SQUARE, TRIANGLE, CIRCLE, CUBE
    }

    private static final long serialVersionUID = 1L;

    private final JToolBar mtoolbar;

    private Shapes mSelected;

    private final JPanel mPanel;

    private final JLabel mLabel;
    private final transient XMLVisitor xmlVisitor;
    private final transient JSonVisitor jsonVisitor;

    private final transient ActionListener mReusableActionListener = new ShapeActionListener();
    private final List<SimpleShape> listOfShapes = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(JDrawingFrame.class.getName());

    /**
     * Tracks buttons to manage the background.
     */
    private final EnumMap<Shapes, JButton> mButtons = new EnumMap<>(Shapes.class);

    /**
     * Default constructor that populates the main window.
     *
     * @param frameName est un String
     */
    public JDrawingFrame(String frameName, TheClient theClient) {
        super(frameName);
        // Instantiates components
        mtoolbar = new JToolBar("Toolbar");
        mPanel = new JPanel();
        mPanel.setBackground(Color.WHITE);
        mPanel.setLayout(null);
        mPanel.setMinimumSize(new Dimension(400, 400));
        mPanel.addMouseListener(theClient);
        mPanel.addMouseMotionListener(theClient);
        mLabel = new JLabel(" ", SwingConstants.LEFT);
        // Fills the panel
        setLayout(new BorderLayout());
        add(mtoolbar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);
        // Square
        URL squareImageUrl = getClass().getResource("images/square.png");
        if (squareImageUrl != null) {

            addShapeButton(Shapes.SQUARE, new ImageIcon(squareImageUrl));
        }
        // Triangle
        URL triangleImageUrl = getClass().getResource("images/triangle.png");
        if (triangleImageUrl != null) {
            addShapeButton(Shapes.TRIANGLE, new ImageIcon(triangleImageUrl));
        }

        // Circle
        URL circleImageUrl = getClass().getResource("images/circle.png");
        if (circleImageUrl != null) {
            addShapeButton(Shapes.CIRCLE, new ImageIcon(circleImageUrl));
        }

        URL cubeImageUrl = getClass().getResource("images/underc.png");
        if (cubeImageUrl != null) {
            addShapeButton(Shapes.CUBE, new ImageIcon(cubeImageUrl));
        }

        setPreferredSize(new Dimension(450, 450));
        jsonVisitor = new JSonVisitor();
        xmlVisitor = new XMLVisitor();

        JButton exportToXmlButton = new JButton("Export to XML");
        exportToXmlButton.addActionListener(e -> exportToXML());

        mtoolbar.add(exportToXmlButton);
        JButton exportToJsonButton = new JButton("Export to JSON");
        exportToJsonButton.addActionListener(e -> exportToJSON());

        mtoolbar.add(exportToJsonButton);


        JButton undoButton = new JButton("Undo");
        mtoolbar.add(undoButton);

    }

    public Shapes getSelectedShape() {
        return mSelected;
    }

    public JLabel getLabel() {
        return mLabel;
    }

    public JPanel getPanel() {
        return mPanel;
    }

    public void exportToXML() {
        try (FileWriter writer = new FileWriter("dessin.xml")) {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlWriter = factory.createXMLStreamWriter(writer);
            xmlWriter.writeStartDocument();
            xmlWriter.writeCharacters("\n");
            xmlWriter.writeStartElement("dessin");

            for (SimpleShape shape : listOfShapes) {
                if (shape instanceof Visitable visitable) {
                    visitable.accept(xmlVisitor);
                    xmlWriter.writeCharacters("\n\t");
                    writer.write(xmlVisitor.getRepresentation());
                }
            }
            xmlWriter.writeCharacters("\n");
            xmlWriter.writeEndElement();
            xmlWriter.writeEndDocument();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "erreur:", e);
        }
    }

    public void exportToJSON() {
        try (FileWriter writer = new FileWriter("dessin.json")) {
            writer.write("{\n\t\"shapes\": [\n\t");
            Iterator<SimpleShape> iterator = listOfShapes.iterator();

            while (iterator.hasNext()) {
                SimpleShape shape = iterator.next();

                if (shape instanceof Visitable visitable) {
                    visitable.accept(jsonVisitor);
                    writer.write("\t\t{\n\t\t\t\"shape\": \n");
                    writer.write(jsonVisitor.getRepresentation());

                    if (iterator.hasNext()) {
                        writer.write("\n\t\t},\n");
                    } else {
                        writer.write("\n\t\t}\n");
                    }
                }
            }

            writer.write("\t]\n}");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "erreur:", e);
        }
    }

    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     *
     * @param shape The name of the injected <tt>SimpleShape</tt>.
     * @param icon  The icon associated with the injected <tt>SimpleShape</tt>.
     */
    private void addShapeButton(Shapes shape, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        mButtons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(mReusableActionListener);
        if (mSelected == null) {
            button.doClick();
        }
        mtoolbar.add(button);
        mtoolbar.validate();
        repaint();
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D newGraph = (Graphics2D) mPanel.getGraphics();
        newGraph.setColor(Color.WHITE);
        newGraph.fillRect(0, 0, mPanel.getWidth(), mPanel.getHeight());
        for (SimpleShape simpleShape : listOfShapes) {
            simpleShape.draw((Graphics2D) this.mPanel.getGraphics());
        }
    }

    public void addShape(SimpleShape shape) {
        shape.draw((Graphics2D) mPanel.getGraphics());
        listOfShapes.add(shape);
    }

    public void removeShape(SimpleShape shape) {
        if (!listOfShapes.isEmpty()) {
            listOfShapes.remove(shape);
        }
        paintComponents(getGraphics());
    }

    

    /**
     * Simple action listener for shape toolbar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     */
    private class ShapeActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            // Itère sur tous les boutons
            for (Map.Entry<Shapes, JButton> entry : mButtons.entrySet()) {
                Shapes shape = entry.getKey();
                JButton btn = entry.getValue();
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    mSelected = shape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }

        }
    }

}
