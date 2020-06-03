package Code;

public class MCode extends GCode{

    private String name;
    private int n;

    public MCode(String name, String category)
    {
        super(name, category);
        this.name = name;
        this.n = n;
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
    
    public int getN()
    {
        return n;
    }
}