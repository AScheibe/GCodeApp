package Code;

public enum MCodes implements CodeBasic{
    M1("M1"), M2("M2"), M3("M3");

    private String name;

    private MCodes(String name)
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