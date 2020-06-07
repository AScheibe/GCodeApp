package Code;

public class MCode implements CodeBasic{


    private String name;
    
    public MCode(String name)
    {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAction() {
        // TODO Auto-generated method stub
        return null;
    }

}