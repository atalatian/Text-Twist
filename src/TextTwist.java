import java.util.*;

public class TextTwist {
    private char[] evaluatedWords;
    private String guessLength;
    private int occurWordCount = 0;
    private int primaryOccurWordCount = 0;
    private char occurredChar;
    private int guessWordLength;
    private int wrongWordCount = 0;
    private Map<String, String> map = new HashMap<String, String>();
    private int result = 0;

    public boolean multipleOccurEvaluate(String guessedWord, String unscrambledLetter){
        for (int i=0; i<guessedWord.length(); i++){
            for (int j=0; j<unscrambledLetter.length(); j++){
                if (guessedWord.charAt(i) == unscrambledLetter.charAt(j)){
                    this.evaluatedWords[i] = guessedWord.charAt(i);
                }
            }
        }
        this.guessWordLength = guessedWord.length();
        this.primaryOccurWordCount = 0;
        this.occurWordCount = 0;
        for (int i=0; i<this.guessWordLength; i++){
            for (int j=0; j<this.guessWordLength; j++){
                if (this.evaluatedWords[i] == this.evaluatedWords[j]){
                    occurWordCount++;
                }
            }
            if (this.occurWordCount >= 2){
                this.occurredChar = evaluatedWords[i];
                for (int z=0; z<unscrambledLetter.length(); z++){
                    if (this.occurredChar == unscrambledLetter.charAt(z)){
                        primaryOccurWordCount++;
                    }
                }
                if (this.primaryOccurWordCount < this.occurWordCount){
                    return false;
                }
            }
            this.primaryOccurWordCount = 0;
            this.occurWordCount = 0;
        }
        return true;
    }


    public boolean wrongWordEvaluate(String guessWord, String unscrambledLetter){
        for (char guessChar: guessWord.toCharArray()){
            for (char unscrambledChar: unscrambledLetter.toCharArray()){
                if (guessChar == unscrambledChar){
                    this.wrongWordCount++;
                }
            }
            if (this.wrongWordCount >= 1){
                this.wrongWordCount = 0;
                continue;
            }else if (this.wrongWordCount < 1){
                return false;
            }
        }
        return true;
    }


    public TextTwist(String [] guessedWords, String unscrambledLetter){
        this.map.put("3", "1");
        this.map.put("4", "2");
        this.map.put("5", "3");
        this.map.put("6", "4");
        for (String guessedWord : guessedWords) {
            this.evaluatedWords = new char[100];
            boolean multipleOccurEvaluateResult = multipleOccurEvaluate(guessedWord,unscrambledLetter);
            boolean wrongWordEvaluateResult = wrongWordEvaluate(guessedWord,unscrambledLetter);
            if (multipleOccurEvaluateResult && wrongWordEvaluateResult){
                if (guessedWord.length() == unscrambledLetter.length()){
                    this.result = this.result + 50;
                }
                this.guessLength = Integer.toString(guessedWord.length());
                this.result = this.result + Integer.parseInt(map.get(guessLength));
            }
        }
        System.out.println(this.result);
    }
}
