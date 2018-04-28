package pm;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class JavaFXHelper {


    public static void printTree(Node node) {
        printNode(node, 0);
    }

    public static void printNode(Node node, int indent) {
        if (node == null) {
            return;
        }
        System.out.println(indent(indent) + node.toString());
        if (node instanceof BorderPane) {
            BorderPane borderPane = (BorderPane) node;
            if (borderPane.getTop() != null) {
                System.out.println(indent(indent) + "- Top");
                printNode(borderPane.getTop(), indent + 4);
            }
            if (borderPane.getLeft() != null) {
                System.out.println(indent(indent) + "- Left");
                printNode(borderPane.getLeft(), indent + 4);
            }
            if (borderPane.getRight() != null) {
                System.out.println(indent(indent) + "- Right");
                printNode(borderPane.getRight(), indent + 4);
            }
            if (borderPane.getCenter() != null) {
                System.out.println(indent(indent) + "- Center");
                printNode(borderPane.getCenter(), indent + 4);
            }

            if (borderPane.getBottom() != null) {
                System.out.println(indent(indent) + "- Bottom");
                printNode(borderPane.getBottom(), indent + 4);
            }
        } else if (node instanceof Pane) {
            Pane pane = (Pane) node;
            for (Node child :
                    pane.getChildren()) {
                printNode(child, indent + 4);

            }
        } else if (node instanceof MenuBar) {
            MenuBar pane = (MenuBar) node;
            for (Menu child :
                    pane.getMenus()) {
                System.out.println(indent(indent + 4) + child.getClass().getName() + " " + child.getText());

                for (MenuItem item :
                        child.getItems()) {
                    System.out.println(indent(indent + 8) + item.getClass().getName() + " " + item.getText());

                }

            }
        }
    }

    public static String indent(int indent) {
        return new String(new char[indent]).replace('\0', ' ');
    }
}
