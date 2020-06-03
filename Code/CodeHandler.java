package Code;

import java.util.HashMap;
import java.util.Map;

public class CodeHandler{
    public CodeHandler(){
        Map<String, CodeBasic> codeMap = new HashMap<String, CodeBasic>();
        codeMap.put("G00", new GCode("G00", "motion"));
        codeMap.put("G01", new GCode("G01", "motion"));
        codeMap.put("G01", new GCode("G01", "motion"));
        codeMap.put("G01", new GCode("G01", "motion"));
        codeMap.put("G01", new GCode("G01", "motion"));
        codeMap.put("G01", new GCode("G01", "motion"));
        codeMap.put("G01", new GCode("G01", "motion"));
        codeMap.put("G01", new GCode("G01", "motion"));




    }
}