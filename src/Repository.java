import java.util.ArrayList;
import java.util.HashMap;

public class Repository {

    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";

    private ArrayList<String> repository;
    private Cards cards;

    public Repository() {
        repository = new ArrayList<>();
        cards = new Cards();
    }

    public void addCard(Integer key) {
        repository.add(cards.getCard(key));
    }

    public String getCard(int key) {
        return cards.getCard(key);
    }

    public void removeCard(String card) {
        repository.remove(card);

    }

    public String getColor(String card){
        if(card.contains("\u001B[30m"))
            return black;
        if(card.contains("\u001B[33m"))
            return yellow;
        if(card.contains("\u001B[31m"))
            return red;
        if(card.contains("\u001B[32m"))
            return green;
        if(card.contains("\u001B[34m"))
            return blue;
        else
            return black;
    }
    public void print() {
        for (String card : repository)
            System.out.print(getColor(card)+"\u250F\u2501\u2501\u2501\u2513");
        System.out.println();
        for (String card : repository)
            System.out.print(card);
        System.out.println();
        for (String card : repository)
            System.out.print(getColor(card) +"\u2517\u2501\u2501\u2501\u251B");
    }
}
