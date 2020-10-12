package src.util;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public abstract class ButtonsUtil {
    private static final Button horizontaLine, verticalLine;
    private static final Button arc;

    public static final List<Button> MASTER_LIST = new ArrayList<Button>(); 
    public static final List<Button> LINES_LIST = new ArrayList<Button>();
    public static final List<Button> ARCS_LIST = new ArrayList<Button>();
    public static final List<Button> TWO_D_SHAPES_LIST = new ArrayList<Button>();
    public static final List<Button> UTIL_LIST = new ArrayList<Button>();
    public static final List<Button> MISC_LIST = new ArrayList<Button>();

    private static final Insets paddingValues = new Insets(5, 5, 5, 5);

    static{
        horizontaLine = new Button("Horizontal Line");
        verticalLine = new Button("Vertical Line");

        arc = new Button("Arc");
        
        LINES_LIST.add(horizontaLine);
        LINES_LIST.add(verticalLine);

        ARCS_LIST.add(arc);

        for(Button b : LINES_LIST)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            MASTER_LIST.add(b);
        }

        for(Button b : ARCS_LIST)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            MASTER_LIST.add(b);
        }
    }
}