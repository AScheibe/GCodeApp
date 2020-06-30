package Util;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class Buttons {
    private static final Button g1, g2, g3, g4;
    private static final Button m1, m2, m3, m4;

    public static final List<Button> masterList = new ArrayList<Button>(); 
    public static final List<Button> gCodeList = new ArrayList<Button>();
    public static final List<Button> mCodeList = new ArrayList<Button>();

    private static final Insets paddingValues = new Insets(5, 5, 5, 5);

    static{
        g1 = new Button("g1");
        g2 = new Button("g2");
        g3 = new Button("g3");
        g4 = new Button("g4");

        m1 = new Button("m1");
        m2 = new Button("m2");
        m3 = new Button("m3");
        m4 = new Button("m4");

        gCodeList.add(g1);
        gCodeList.add(g2);
        gCodeList.add(g3);
        gCodeList.add(g4);

        mCodeList.add(m1);
        mCodeList.add(m2);
        mCodeList.add(m3);
        mCodeList.add(m4);

        for(Button b : gCodeList)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            masterList.add(b);
        }

        for(Button b : mCodeList)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            masterList.add(b);
        }
    }
}