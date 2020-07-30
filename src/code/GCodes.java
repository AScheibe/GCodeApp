package src.code;

public enum GCodes implements CodeBasic{
    G1("G1"), G2("G2"), G3("G3"), G4("G4");

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
        // TODO setActions
        return name;
    }

}