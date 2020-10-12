package src.chat.words;

public enum ObjectWords implements Words{
    o1("line"), o2("arc"), o3("circle"), o4("rectangle");

    private String word;

    private ObjectWords(String word){
        this.word = word;
    }

    public String getWord(){
        return word;
    }

}
