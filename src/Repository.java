import java.util.ArrayList;

/**
 * this class keep a game cards
 *its one of GameRules subclass
 *
 * @author Narges Salehi
 */
public class Repository extends GameRules {

    //new array lost to keep cards of each game
    private ArrayList<String> tank;

    /**
     * creat a new repository
     */
    public Repository() {
        tank = new ArrayList<>();
    }

    /**
     * add a card to repository
     *
     * @param value of card
     */
    public void addCard(String value) {
        tank.add(value);
    }

    /**
     * get a card by its index
     *
     * @param index of card
     * @return card
     */
    public String getCard(int index) {
        return tank.get(index);
    }

    /**
     * remove a card by its index
     *
     * @param index of card
     */
    public void removeCard(int index) {
        tank.remove(index);
    }

    /**
     * get all the cards of a game
     *
     * @return array list of cards
     */
    public ArrayList<String> getCards() {
        return tank;
    }
}
