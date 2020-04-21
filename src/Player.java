import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class keeps a player cards and manage player action
 * its one of GameRules subclass
 *
 * @author Narges Salehi
 */
public class Player extends GameRules {

    //new array list to keep player card
    private ArrayList<String> cards;

    /**
     * creat a new player
     */
    public Player() {
        cards = new ArrayList<>();
    }

    /**
     * add a card with given key from given Cards class
     *
     * @param key  of card
     * @param bank Class of Cards
     */
    public void addCard(String key, Cards bank) {
        cards.add(bank.getValue(key));
    }

    /**
     * add a card only by its value
     * @param card to be added
     */
    public void addCard(String card) {
        cards.add(card);
    }

    /**
     * remove a card by its value
     * @param card to be deleted
     */
    public void removeCard(String card) {
        cards.remove(card);
    }

    /**
     * get player card list
     * @return array list of cards
     */
    public ArrayList<String> getCards() {
        return cards;
    }

    /**
     * check if player have the given card or no
     * @param temp given card
     * @return true or false
     */
    public boolean containCard(String temp) {
        for (String card : cards)
            if (card.equals(temp))
                return true;
        return false;

    }

    /**
     * find proper card to put on base card
     * @param base card
     * @return String proper card
     */
    public String findProperCard(String base) {
        //search based on color or number
        for (String card : cards) {
            if ((getColor(card).equals(getColor(base)) && !getColor(card).equals("no color")) || (getNumber(card) == getNumber(base) && getNumber(card) != -1))
                return card;
        }
        //check if base is skip card player can play skip card
        if (base.contains("skp")) {
            for (String card : cards)
                if (card.contains("skp"))
                    return card;
        }
        //check if base is revers card player can play reverse card
        else if (base.contains("rvs")) {
            for (String card : cards) {
                if (card.contains("rvs"))
                    return card;
            }
        }
        //for other cases player can play black cards based on preference
        for (String card : cards)
            if (card.equals(black + "\u2503" + blue + "\u2503" + green + "\u2503" + red + "\u2503" + green + black + "\u2503"))
                return card;
            else if (card.equals(black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503"))
                return card;
        return "no card";
    }

    /**
     * receive a card from user - for player with index 0 only
     * @param base card of game
     * @return card
     */
    public String receiveCard(String base) {
        //check if there is any proper card available in player cards
        if (!(findProperCard(base).equals("no card"))) {
            while (true) {
                //get card color and type from user - player 0 only
                System.out.println(black + "Enter your card like ,color + next line + name (copy the card for name)  :");
                Scanner scan = new Scanner(System.in);
                String color = scan.nextLine();
                String kind = scan.nextLine();
                String card;
                //built a card by given input
                switch (color) {
                    case "red":
                        card = red + kind;
                        return card;
                    case "green":
                        card = green + kind;
                        return card;

                    case "blue":
                        card = blue + kind;
                        return card;

                    case "yellow":
                        card = yellow + kind;
                        return card;

                    case "black":
                        if (kind.contains("+ 4"))
                            return black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503";
                        if (kind.contains("┃┃┃┃┃"))
                            return black + "\u2503" + blue + "\u2503" + green + "\u2503" + red + "\u2503" + green + black + "\u2503";
                        break;
                }
                //if there is proper card but player enter the wrong card - player should play
                System.out.println("Enter right card :");
            }

        }
        //else return no card to make player pick a card
        return "no card";
    }

    /**
     * search for an special given card
     * @param card looking for
     * @return String card
     */
    public String findGivenCard(String card) {
        for (String temp : cards) {
            if (temp.contains(card))
                return temp;
        }
        return "no card";
    }

    /**
     * count score of player cards
     * @return int score
     */
    public int getScore() {
        int score = 0;
        //check out all the cards and count their score
        for (String card : cards) {
            if (card.contains(black))
                score += 50;
            else if (card.contains("skp") || card.contains("rvs") || card.contains("+ 2"))
                score += 20;
            else if (card.contains(" 1 "))
                score += 1;
            else if (card.contains(" 2 "))
                score += 2;
            else if (card.contains(" 3 "))
                score += 3;
            else if (card.contains(" 4 "))
                score += 4;
            else if (card.contains(" 5 "))
                score += 5;
            else if (card.contains(" 6 "))
                score += 6;
            else if (card.contains(" 7 "))
                score += 7;
            else if (card.contains(" 8 "))
                score += 8;
            else if (card.contains(" 9 "))
                score += 9;
            else
                score += 0;
        }
        return score;
    }
}

