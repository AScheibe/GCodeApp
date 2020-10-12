package src.chat.words;

public enum QuestionWords implements Words{
   q1("how"), q2("what"), q3("where"), q4("why");

    private String word;

    private QuestionWords(String word){
        this.word = word;
    }

    @Override
    public String getWord(){
        return word;
    }
}
