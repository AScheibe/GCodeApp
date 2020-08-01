package src.util;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public abstract class ButtonsUtil {
    private static final Button g1, g2, g3, g4;
    private static final Button m1, m2, m3, m4;

    public static final List<Button> MASTER_LIST = new ArrayList<Button>(); 
    public static final List<Button> GCODE_LIST = new ArrayList<Button>();
    public static final List<Button> MCODE_LIST = new ArrayList<Button>();

    private static final Insets paddingValues = new Insets(5, 5, 5, 5);

    static{
        g1 = new Button("G1");
        g2 = new Button("G2");
        g3 = new Button("G3");
        g4 = new Button("G4");

        m1 = new Button("M1");
        m2 = new Button("M2");
        m3 = new Button("M3");
        m4 = new Button("M4");

        GCODE_LIST.add(g1);
        GCODE_LIST.add(g2);
        GCODE_LIST.add(g3);
        GCODE_LIST.add(g4);

        MCODE_LIST.add(m1);
        MCODE_LIST.add(m2);
        MCODE_LIST.add(m3);
        MCODE_LIST.add(m4);

        for(Button b : GCODE_LIST)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            MASTER_LIST.add(b);
        }

        for(Button b : MCODE_LIST)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            MASTER_LIST.add(b);
        }
    }
}