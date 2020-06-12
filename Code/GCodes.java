package Code;

public enum GCodes implements CodeBasic{
    G1("G1"), G2("G2"), G3("G3");

    private String name;

    private GCodes(String name)
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