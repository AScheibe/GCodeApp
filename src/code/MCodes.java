package src.code;

public enum MCodes implements CodeBasic{
    M1("M1"), M2("M2"), M3("M3"), M4("M4");

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
    // TODO setActions
        return name;
    }

}