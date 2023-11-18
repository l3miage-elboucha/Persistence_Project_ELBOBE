package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import edu.uga.miage.m1.polygons.gui.shapes.Cube;

/**
 * @author <a href=
 *         "mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;

    public JSonVisitor() {
        // empty pour le moment

    }

    @Override
    public void visit(Circle circle) {
        this.representation = jsonGeneratorForShapes("circle", circle.getX(), circle.getY());
    }

    @Override
    public void visit(Square square) {
        this.representation = jsonGeneratorForShapes("square", square.getX(), square.getY());
    }

    @Override
    public void visit(Triangle triangle) {
        this.representation = jsonGeneratorForShapes("triangle", triangle.getX(), triangle.getY());
    }

    @Override
    public void visit(Cube cube) {
        this.representation = jsonGeneratorForShapes("cube", cube.getX(), cube.getY());
    }

    // logique de génération de de Json pour les 3 figures
    private String jsonGeneratorForShapes(String type, int x, int y) {
        return "{\n" +
                "    \"type\": \"" + type + "\",\n" +
                "    \"x\": " + x + ",\n" +
                "    \"y\": " + y + "\n" +
                "}";
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
