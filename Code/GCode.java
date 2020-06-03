package Code;

public class GCode implements CodeBasic{

    private String cat;
    private boolean isMotion;
    private boolean isCoordinate;

    public GCode(String name, String category){
        cat = category.toLowerCase();
        isMotion = cat.equals("motion");
        isCoordinate = cat.equals("coordinate");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getAction() {
        // TODO Auto-generated method stub
        return null;
    }


    public boolean getIsMotion() {
        return isMotion;
    }

    public boolean getIsCoordinate() {
        return isCoordinate;
    }

    public String getCategory(){
        return cat;
    }
    
}