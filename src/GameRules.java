import java.util.*;

/**
 * keep game rules
 *
 * @author Narges Salehi
 */
public class GameRules {
    //define static String for each color
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";

    //define a Card to use for giving cards to players and base
    private Cards cards;
    //define a repository to keep extra cards
    private Repository repository;
    //array list pf Player ti keep players
    private ArrayList<Player> players;

    //this String keep base card - card on the ground !
    private String base;
    // this String keep color when wild cards change color
    public String sideBase;
    //keep CW as clockwise or NCW as nonclock-wise
    private String direction;
    //keep turn at any moment
    private int turn;
    //keep number of player
    private int numberOfPlayer;
    //keep 0 or 1 - if previous player turn lost or not
    private int skipCounter;
    //keep 0 or 1 - if previous player pick card or not
    public int plus2Counter;
    //keep 0 or 1 - if previous player pick card or not
    public int plus4Counter;

    /**
     * creat a new GameRules
     */
    public GameRules() {
    }

    /**
     * creat a new GameRules with given number of player
     * @param num number of player
     */
    public GameRules(int num) {
        //first direction
        direction = "CW";
        //random player start game
        Random rand = new Random();
        turn = rand.nextInt(num);
        numberOfPlayer = num;
        //first value should be zero - as player put a card count to one
        skipCounter = 0;
        plus2Counter = 0;
        plus4Counter = 0;
        cards = new Cards();
        repository = new Repository();
        players = new ArrayList<>();
        //add player to array list
        while (num > 0) {
            Player player = new Player();
            players.add(player);
            num--;
        }
    }

    /**
     * make a random base but not wild card
     */
    public void firstBase() {
        int random;
        Random rand = new Random();
        random = rand.nextInt(repository.getCards().size());
        //check if its a wild card recall method
        if (repository.getCard(random).contains(black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503")
                || repository.getCard(random).contains(black + "\u2503" + blue + "\u2503" + green + "\u2503" + red + "\u2503" + green + black + "\u2503"))
            firstBase();

        //manage spacial cards as base
        else {
            //set card as base
            this.base = repository.getCard(random);
            //chang direction
            if (base.contains("rvs"))
                setDirection();
            //make first player pick two card and lost the turn
            if (base.contains("+ 2")) {
                designGround();
                System.out.println(black + "\nRotate Direction : " + getDirection());
                System.out.println(black + "player :" + getTurn());
                Random random1 = new Random();
                Random random2 = new Random();
                int index1 = random1.nextInt(repository.getCards().size());
                int index2 = random2.nextInt(repository.getCards().size());
                String temp1 = repository.getCard(index1);
                String temp2 = repository.getCard(index1);
                players.get(turn).addCard(repository.getCard(index1));
                players.get(turn).addCard(repository.getCard(index2));
                repository.removeCard(index1);
                repository.removeCard(index2);
                System.out.println(black + "player picked card !");
                setTurn();
            }
        }
    }

    /**
     * get direction
     * @return direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * change direction an any moment
     */
    public void setDirection() {
        if (direction.equals("CW"))
            direction = "NCW";
        else
            direction = "CW";
    }

    /**
     * set turn at any moment -  based on direction pass turn to next player
     */
    public void setTurn() {
        if (direction.equals("CW")) {
            if (turn < numberOfPlayer - 1)
                this.turn++;
            else
                this.turn = 0;
        }
        if (direction.equals("NCW")) {
            if (turn > 0)
                this.turn--;
            else
                this.turn = numberOfPlayer - 1;
        }
    }

    /**
     * get plus2Counter
     * @return plus2Counter
     */
    public int getPlusTwoState() {
        return plus2Counter;
    }

    /**
     * get plus4Counter
     * @return plus4Counter
     */
    public int getPlusFourState() {
        return plus4Counter;
    }

    /**
     * get color of each card by checking its start
     * @param card given card
     * @return color
     */
    public String getColor(String card) {
        if (card.startsWith(black))
            return black;
        else if (card.startsWith(yellow))
            return yellow;
        else if (card.startsWith(red))
            return red;
        else if (card.startsWith(green))
            return green;
        else if (card.startsWith(blue))
            return blue;
        return "no color";
    }

    /**
     * get number of number card
     * @param card given card
     * @return number
     */
    public int getNumber(String card) {
        if (card.contains(" 0 "))
            return 0;
        else if (card.contains(" 1 "))
            return 1;
        else if (card.contains(" 2 "))
            return 2;
        else if (card.contains(" 3 "))
            return 3;
        else if (card.contains(" 4 "))
            return 4;
        else if (card.contains(" 5 "))
            return 5;
        else if (card.contains(" 6 "))
            return 6;
        else if (card.contains(" 7 "))
            return 7;
        else if (card.contains(" 8 "))
            return 8;
        else if (card.contains(" 9 "))
            return 9;
        else
            return -1;

    }

    /**
     * give random card to players + put rest of the card  in repository
     */
    public void giveCard() {
        //three random help making random number as key to find cards values
        //this random make a number < 5 for color
        int random1;
        //this random make a number based on color for type of card
        int random2;
        //this random make a number for number of spacial card - more than one card for each type
        int random3;
        //for all the players
        for (Player player : players) {
            //7 cards to each player
            for (int i = 1; i <= 7; i++) {
                Random random = new Random();
                random1 = random.nextInt(5);
                if (random1 == 0) {
                    random2 = random.nextInt(2);
                    random3 = random.nextInt(4);
                } else {
                    random2 = random.nextInt(13);
                    if (random2 == 0)
                        random3 = 0;
                    else
                        random3 = random.nextInt(2);
                }
                //make a 4 or 3 digit number into String to make a key
                String key = String.valueOf(random1) +
                        String.valueOf(random2) +
                        String.valueOf(random3);
                //find card and give it to player
                if (cards.containKey(key)) {
                    player.addCard(key, cards);
                    cards.removeCard(key, cards.getValue(key));
                } else
                    i--;
            }
        }
        // add rest of card to repository
        for (String card : cards.getValues()) {
            repository.addCard(card);
        }
    }

    /**
     * print score of each player
     */
    public void printScore() {
        System.out.println(black);
        for (int i = 0; i < players.size(); i++) {
            int j = i + 1;
            System.out.print("player " + j + ":");
            System.out.println(players.get(i).getScore());
        }
    }

    /**
     * print final score chart at the end of game
     */
    public void printScoreChart() {
        System.out.println();
        //new array list to keep scores
        ArrayList scoreChart = new ArrayList();
        for (int i = 0; i < players.size(); i++) {
            scoreChart.add(players.get(i).getScore());
        }
        //sort scores
        Collections.sort(scoreChart);
        //print sorted scores
        for (int i = 0; i < scoreChart.size(); i++) {
            for (int j = 0; j < players.size(); j++) {
                if (String.valueOf(players.get(j).getScore()).equals(scoreChart.get(i).toString())) {
                    int num = j + 1;
                    System.out.println(black + "player" + num + " :" + scoreChart.get(i));
                    break;
                }
            }
        }
    }

    /**
     * check if we can put given temp String to base
     * @param temp given card
     * @param base card
     * @return true or false
     */
    public boolean checkPutCard(String temp, String base) {
        //check +2 base
        if (base.contains("+ 2") && getPlusTwoState() != 0 && (temp.contains("+ 2") || temp.contains("+ 4")))
            return true;
        //check +4 base
        else if (base.contains(blue + "+ " + red + "4" + black) && getPlusFourState() != 0 && temp.contains("+ 4"))
            return true;
        //check change color base
        else if (base.equals(black + "\u2503" + blue + "\u2503" + green + "\u2503" + red + "\u2503" + green + black + "\u2503") &&
                getColor(temp).equals(getColor(sideBase)))
            return true;
        //if both card are the same
        else if (base.equals(temp))
            return true;
        //if their color are the same
        else if (getColor(base).equals(getColor(temp)))
            return true;
        //if their number are the same
        else if (getNumber(base) == getNumber(temp) && getNumber(temp) != -1 && getNumber(base) != -1)
            return true;
        //put rvs on rvs
        else if (base.contains("rvs") && temp.contains("rvs"))
            return true;
        //put skp on skp
        else if (base.contains("skp") && temp.contains("skp"))
            return true;
        //put skp on skp
        else if (base.contains("+ 2") && temp.contains("+ 2"))
            return true;
        //put wild card on any card
        else return getColor(temp).equals(black);
    }

    /**
     * apply effect of each card after place as base
     * @param temp given card
     */
    public void cardAction(String temp) {
        if (temp.contains("skp")) {
            skipCounter = 0;
        } else if (temp.contains("rvs")) {
            setDirection();
        } else if (temp.contains("+ 2")) {
            plus2Counter++;
        } else if (temp.equals(black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503") && turn == 0) {
            plus4Counter++;
            //ask player to choose color - player 0 only
            System.out.println(getPlusFourState());
            System.out.println(black + "Enter base Color :");
            Scanner scanner = new Scanner(System.in);
            String color = scanner.nextLine();
            switch (color) {
                case "red":
                    sideBase = red;
                    System.out.println(sideBase);
                    break;
                case "blue":
                    sideBase = blue;
                    System.out.println(sideBase);
                    break;
                case "green":
                    sideBase = green;
                    System.out.println(sideBase);
                    break;
                case "yellow":
                    sideBase = yellow;
                    System.out.println(sideBase);
                    break;
            }
        }
        //choose random color for other players
        else if (temp.equals(black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503")) {
            plus4Counter++;
            Random random = new Random();
            int color = random.nextInt(4);
            if (color == 0) {
                sideBase = red;
                System.out.println(sideBase);
                System.out.println(black + "base color is red .");
            }
            if (color == 1) {
                sideBase = blue;
                System.out.println(sideBase);
                System.out.println(black + "base color is blue .");
            }
            if (color == 2) {
                sideBase = green;
                System.out.println(sideBase);
                System.out.println(black + "base color is green .");
            }
            if (color == 3) {
                sideBase = yellow;
                System.out.println(sideBase);
                System.out.println(black + "base color is yellow .");
            }
        }
        //ask player to choose color - player 0 only
        else if (temp.equals(black + "\u2503" + blue + "\u2503" + green + "\u2503" + red + "\u2503" + green + black + "\u2503") && turn == 0) {
            System.out.println(black + "Enter base Color :");
            Scanner scanner = new Scanner(System.in);
            String color = scanner.nextLine();
            if (color.equals("red"))
                sideBase = red;
            System.out.println(sideBase);
            if (color.equals("blue"))
                sideBase = blue;
            if (color.equals("green"))
                sideBase = green;
            if (color.equals("yellow"))
                sideBase = yellow;
        }
        //choose random color for other player
        else if (temp.equals(black + "\u2503" + blue + "\u2503" + green + "\u2503" + red + "\u2503" + green + black + "\u2503")) {
            Random random = new Random();
            int color = random.nextInt(4);
            if (color == 0) {
                sideBase = red;
                System.out.println(black + "base color is red .");
            }
            if (color == 1) {
                sideBase = blue;
                System.out.println(black + "base color is blue .");
            }
            if (color == 2) {
                sideBase = green;
                System.out.println(black + "base color is green .");
            }
            if (color == 3) {
                sideBase = yellow;
                System.out.println(black + "base color is yellow .");
            }
        }
    }

    /**
     * get turn at any moment
     * @return turn
     */
    public int getTurn() {
        return turn + 1;
    }

    /**
     * put card for player considering base
     */
    public void putCart() {
        //skip base
        if (base.contains("skp")) {
            //for computer players
            if (turn != 0) {
                //no one turn skipped
                if (skipCounter == 0) {
                    System.out.println("player turn lost !");
                    //set turn to next player
                    setTurn();
                    //also set skip counter - someone turn skipped
                    skipCounter = 1;
                } else {
                    //find proper card
                    String temp = players.get(turn).findProperCard(base);
                    //if there is no proper card
                    if (temp.equals("no card")) {
                        //pick a random card
                        Random random = new Random();
                        int index = random.nextInt(repository.getCards().size());
                        //chang temp to new card - picked card from repository
                        temp = repository.getCard(index);
                        players.get(turn).addCard(repository.getCard(index));
                        repository.removeCard(index);
                        System.out.println();
                        System.out.println(black + "player picked card !");
                    }
                    //check if player can put card
                    if (checkPutCard(temp, base)) {
                        //return old base to repository
                        repository.addCard(base);
                        //replace base with temp
                        base = temp;
                        //delete card from player cards
                        players.get(turn).removeCard(temp);
                        //apply new base effect
                        cardAction(base);
                    }
                    //set turn to next player
                    setTurn();
                }
            }
            //same for player 0 - user
            else {
                if (skipCounter == 0) {
                    System.out.println("player turn lost !");
                    setTurn();
                    skipCounter = 1;
                } else {
                    while (true) {
                        String temp = players.get(0).receiveCard(base);
                        if (temp.equals("no card")) {
                            Random random = new Random();
                            int index = random.nextInt(repository.getCards().size());
                            String temp2 = repository.getCard(index);
                            players.get(0).addCard(repository.getCard(index));
                            repository.removeCard(index);
                            System.out.println(black + "player picked card !");
                            if (checkPutCard(temp2, base)) {
                                repository.addCard(base);
                                base = temp2;
                                players.get(0).removeCard(temp2);
                                cardAction(base);
                            }
                            setTurn();
                            break;
                        } else if (players.get(0).containCard(temp)) {
                            if (checkPutCard(temp, base)) {
                                repository.addCard(base);
                                base = temp;
                                players.get(0).removeCard(temp);
                                cardAction(base);
                                setTurn();
                                break;
                            } else
                                System.out.println("Enter right card !");
                        } else
                            System.out.println("Enter right card !");
                    }
                }
            }
        }
        //+2 base
        else if (base.contains("+ 2")) {
            //if before no one pick card
            if (plus2Counter != 0) {
                //look for +2 card
                String temp = players.get(turn).findGivenCard("\u2503+ 2\u2503");
                if (temp.equals("no card")) {
                    //look for +4 card
                    temp = players.get(turn).findGivenCard(black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503");
                    if (temp.equals("no card")) {
                        //pick card
                        int num = plus2Counter * 2 + plus4Counter * 4;
                        while (num > 0) {
                            Random random = new Random();
                            int index = random.nextInt(repository.getCards().size());
                            players.get(turn).addCard(repository.getCard(index));
                            repository.removeCard(index);
                            num--;
                        }
                        //make counter 0
                        System.out.println(black + "player picked card !");
                        plus4Counter = 0;
                        plus2Counter = 0;
                        //pass the turn
                        setTurn();
                    }
                    //if there was proper card
                    else {
                        repository.addCard(base);
                        base = temp;
                        players.get(turn).removeCard(temp);
                        cardAction(base);
                        setTurn();
                    }
                } else {
                    repository.addCard(base);
                    base = temp;
                    players.get(turn).removeCard(temp);
                    cardAction(base);
                    setTurn();
                }
            }
            //if some one else picked cards
            else {
                //for computer player
                if (turn != 0) {
                    String temp = players.get(turn).findProperCard(base);
                    //no card make player pick a card
                    if (temp.equals("no card")) {
                        Random random = new Random();
                        int index = random.nextInt(repository.getCards().size());
                        temp = repository.getCard(index);
                        players.get(turn).addCard(repository.getCard(index));
                        repository.removeCard(index);
                        System.out.println(black + "player picked card !");
                    }
                    //check if its proper card
                    if (checkPutCard(temp, base)) {
                        repository.addCard(base);
                        base = temp;
                        players.get(turn).removeCard(temp);
                        cardAction(base);
                    }
                    setTurn();
                }
                //user turn
                else {
                    while (true) {
                        String temp = players.get(0).receiveCard(base);
                        //no card make player pick a card
                        if (temp.equals("no card")) {
                            Random random = new Random();
                            int index = random.nextInt(repository.getCards().size());
                            String temp2 = repository.getCard(index);
                            players.get(0).addCard(repository.getCard(index));
                            repository.removeCard(index);
                            System.out.println(black + "player picked card !");
                            if (checkPutCard(temp2, base)) {
                                repository.addCard(base);
                                base = temp2;
                                players.get(0).removeCard(temp2);
                                cardAction(base);
                            }
                            setTurn();
                            break;
                        } else {
                            if (checkPutCard(temp, base)) {
                                if (players.get(0).containCard(temp)) {
                                    repository.addCard(base);
                                    base = temp;
                                    players.get(0).removeCard(temp);
                                    cardAction(base);
                                    setTurn();
                                    break;
                                } else
                                    System.out.println("Enter right card !");
                            } else
                                System.out.println("Enter right card !");
                        }
                    }
                }
            }
        }
        //+4 base
        else if (base.equals(black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503")) {
            //if no one picked card
            if (plus4Counter != 0) {
                //look for +4 card
                String temp = players.get(turn).findGivenCard(black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503");
                if (temp.equals(black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503")) {
                    repository.addCard(base);
                    base = temp;
                    players.get(turn).removeCard(temp);
                    cardAction(base);
                    setTurn();
                }
                //pick card
                else {
                    int num = plus2Counter * 2 + plus4Counter * 4;
                    while (num > 0) {
                        Random random = new Random();
                        int index = random.nextInt(repository.getCards().size());
                        players.get(turn).addCard(repository.getCard(index));
                        repository.removeCard(index);
                        num--;
                    }
                    System.out.println(black + "player picked card !");
                    //make counter 0
                    plus4Counter = 0;
                    plus2Counter = 0;
                    //pass turn to next player
                    setTurn();
                }
            }
            //no need to pick card
            else {
                if (turn != 0) {
                    String temp = players.get(turn).findProperCard(sideBase);
                    //no card make player pick a card
                    if (temp.equals("no card")) {
                        Random random = new Random();
                        int index = random.nextInt(repository.getCards().size());
                        temp = repository.getCard(index);
                        players.get(turn).addCard(repository.getCard(index));
                        repository.removeCard(index);
                        System.out.println(black + "player picked card !");
                    }
                    if (checkPutCard(temp, sideBase)) {
                        repository.addCard(base);
                        base = temp;
                        players.get(turn).removeCard(temp);
                        cardAction(base);
                    }
                    setTurn();
                } else {
                    while (true) {
                        String temp = players.get(0).receiveCard(sideBase);
                        if (temp.equals("no card")) {
                            Random random = new Random();
                            int index = random.nextInt(repository.getCards().size());
                            String temp2 = repository.getCard(index);
                            players.get(0).addCard(repository.getCard(index));
                            repository.removeCard(index);
                            System.out.println(black + "player picked card !");
                            if (checkPutCard(temp2, base)) {
                                repository.addCard(base);
                                base = temp2;
                                players.get(turn).removeCard(temp2);
                                cardAction(base);
                            }
                            setTurn();
                            break;
                        } else if (checkPutCard(temp, sideBase)) {
                            if (players.get(0).containCard(temp)) {
                                repository.addCard(base);
                                base = temp;
                                players.get(turn).removeCard(temp);
                                cardAction(base);
                                setTurn();
                                break;
                            } else
                                System.out.println("Enter right card !");
                        } else
                            System.out.println("Enter right card !");
                    }
                }
            }
        }
        //change base color
        else if (base.equals(black + "\u2503" + blue + "\u2503" + green + "\u2503" + red + "\u2503" + green + black + "\u2503")) {
            if (turn == 0) {
                while (true) {
                    //look for proper card based on side base color
                    String temp = players.get(0).receiveCard(sideBase);
                    if (temp.equals("no card")) {
                        Random random = new Random();
                        int index = random.nextInt(repository.getCards().size());
                        temp = repository.getCard(index);
                        players.get(0).addCard(repository.getCard(index));
                        repository.removeCard(index);
                        System.out.println("player picked card !");
                        if (checkPutCard(temp, sideBase)) {
                            repository.addCard(base);
                            base = temp;
                            players.get(0).removeCard(temp);
                            cardAction(base);
                        }
                        setTurn();
                        break;
                    } else {
                        if (players.get(0).containCard(temp)) {
                            if (checkPutCard(temp, base)) {
                                repository.addCard(base);
                                base = temp;
                                players.get(0).removeCard(temp);
                                cardAction(base);
                                setTurn();
                                break;
                            } else
                                System.out.println("Enter right card !");
                        } else
                            System.out.println("Enter right card !");
                    }
                }
            }
            //user player
            else {
                String temp = players.get(turn).findProperCard(sideBase);
                if (temp.equals("no card")) {
                    Random random = new Random();
                    int index = random.nextInt(repository.getCards().size());
                    temp = repository.getCard(index);
                    players.get(turn).addCard(repository.getCard(index));
                    repository.removeCard(index);
                    System.out.println("player picked card !");
                    if (checkPutCard(temp, base)) {
                        repository.addCard(base);
                        base = temp;
                        players.get(turn).removeCard(temp);
                        cardAction(base);
                    }
                    setTurn();
                } else {
                    if (checkPutCard(temp, sideBase)) {
                        repository.addCard(base);
                        base = temp;
                        players.get(turn).removeCard(temp);
                        cardAction(base);
                        setTurn();
                    } else {
                        if (checkPutCard(temp, base)) {
                            repository.addCard(base);
                            base = temp;
                            players.get(turn).removeCard(temp);
                            cardAction(base);
                            setTurn();
                        }
                    }
                }
            }
        }
        //other cards
        else {
            //computer players
            if (turn != 0) {
                //find proper card
                String temp = players.get(turn).findProperCard(base);
                //no card make player pick a card
                if (temp.equals("no card")) {
                    Random random = new Random();
                    int index = random.nextInt(repository.getCards().size());
                    temp = repository.getCard(index);
                    players.get(turn).addCard(repository.getCard(index));
                    repository.removeCard(index);
                    //also check if picked card is proper or not
                    System.out.println("player picked card !");
                    if (checkPutCard(temp, base)) {
                        repository.addCard(base);
                        base = temp;
                        players.get(turn).removeCard(temp);
                        cardAction(base);
                    }
                    setTurn();
                } else {
                    //check returned card is proper or not
                    if (checkPutCard(temp, base)) {
                        repository.addCard(base);
                        base = temp;
                        players.get(turn).removeCard(temp);
                        cardAction(base);
                    }
                    setTurn();
                }
            }
            //for user player
            else {
                //while loop break if proper card that is set or not available at all
                while (true) {
                    String temp = players.get(0).receiveCard(base);
                    //no card make player pick a card
                    if (temp.equals("no card")) {
                        Random random = new Random();
                        int index = random.nextInt(repository.getCards().size());
                        String temp2 = repository.getCard(index);
                        players.get(0).addCard(repository.getCard(index));
                        repository.removeCard(index);
                        System.out.println("player picked card !");
                        //also check if picked card is proper or not
                        if (checkPutCard(temp2, base)) {
                            repository.addCard(base);
                            base = temp2;
                            players.get(0).removeCard(temp2);
                            cardAction(base);
                        }
                        setTurn();
                        break;
                    } else {
                        //check returned card is proper or not
                        if (players.get(0).containCard(temp)) {
                            if (checkPutCard(temp, base)) {
                                repository.addCard(base);
                                base = temp;
                                players.get(0).removeCard(temp);
                                cardAction(base);
                                setTurn();
                                break;
                            } else
                                System.out.println("Enter right card !");
                        } else
                            System.out.println("Enter right card !");
                    }
                }
            }
        }
    }

    /**
     * print ground each moment
     */
    public void designGround() {
        System.out.println();
        //for 3 players
        if (numberOfPlayer == 3) {
            System.out.print("                                             ");
            for (String card : players.get(2).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println();
            System.out.print("                                    player 3:");
            for (String card : players.get(2).getCards())
                System.out.print("\u2503UNO\u2503");
            System.out.println();
            System.out.print("                                             ");
            for (String card : players.get(2).getCards())
                System.out.print("\u2517\u2501\u2501\u2501\u251B");
            System.out.println();

            System.out.print("         ");
            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println("               " + getColor(base) + "\u250F\u2501\u2501\u2501\u2513");
            System.out.print(black + "player 2:");
            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u2503UNO\u2503");
            System.out.println("               " + getColor(base) + base);
            System.out.print("         ");
            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u2517\u2501\u2501\u2501\u251B");
            System.out.println("               " + getColor(base) + "\u2517\u2501\u2501\u2501\u251B");

            System.out.print("                                             ");
            for (String card : players.get(0).getCards())
                System.out.print(getColor(card) + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println();
            System.out.print("                                    " + black + "player 1:");
            for (String card : players.get(0).getCards())
                System.out.print(card);
            System.out.println();
            System.out.print("                                             ");
            for (String card : players.get(0).getCards())
                System.out.print(getColor(card) + "\u2517\u2501\u2501\u2501\u251B");

        }
        //for 4 players
        if (numberOfPlayer == 4) {
            System.out.print("                                             ");
            for (String card : players.get(2).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println();
            System.out.print("                                    player 3:");
            for (String card : players.get(2).getCards())
                System.out.print("\u2503UNO\u2503");
            System.out.println();
            System.out.print("                                             ");
            for (String card : players.get(2).getCards())
                System.out.print("\u2517\u2501\u2501\u2501\u251B");
            System.out.println();

            System.out.print("         ");
            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.print("               " + getColor(base) + "\u250F\u2501\u2501\u2501\u2513");
            System.out.print("                 ");
            for (String card : players.get(3).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println();
            System.out.print("player 2:");

            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u2503UNO\u2503");
            System.out.print("               " + getColor(base) + base);
            System.out.print(black + "        player 4:");
            for (String card : players.get(3).getCards())
                System.out.print("\u001B[30m" + "\u2503UNO\u2503");
            System.out.println();
            System.out.print("         ");

            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u2517\u2501\u2501\u2501\u251B");
            System.out.print("               " + getColor(base) + "\u2517\u2501\u2501\u2501\u251B");
            System.out.print("                 ");
            for (String card : players.get(3).getCards())
                System.out.print("\u001B[30m" + "\u2517\u2501\u2501\u2501\u251B");
            System.out.println();

            System.out.print("                                             ");
            for (String card : players.get(0).getCards())
                System.out.print(getColor(card) + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println();
            System.out.print("                                    " + black + "player 1:");
            for (String card : players.get(0).getCards())
                System.out.print(card);
            System.out.println();
            System.out.print("                                             ");
            for (String card : players.get(0).getCards())
                System.out.print(getColor(card) + "\u2517\u2501\u2501\u2501\u251B");
        }

        //for 5 players
        if (numberOfPlayer == 5) {
            System.out.print("                 ");
            for (String card : players.get(2).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.print("                 ");
            for (String card : players.get(3).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println();

            System.out.print("        player 3:");
            for (String card : players.get(2).getCards())
                System.out.print("\u2503UNO\u2503");
            System.out.print("        player 4:");
            for (String card : players.get(3).getCards())
                System.out.print("\u2503UNO\u2503");
            System.out.println();

            System.out.print("                 ");
            for (String card : players.get(2).getCards())
                System.out.print("\u2517\u2501\u2501\u2501\u251B");
            System.out.print("                 ");
            for (String card : players.get(3).getCards())
                System.out.print("\u2517\u2501\u2501\u2501\u251B");
            System.out.println();

            System.out.print("         ");
            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.print("               " + getColor(base) + "\u250F\u2501\u2501\u2501\u2513");
            System.out.print("               ");
            for (String card : players.get(4).getCards())
                System.out.print("\u001B[30m" + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println();

            System.out.print("player 2:");
            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u2503UNO\u2503");
            System.out.print("               " + getColor(base) + base);
            System.out.print(black + "      player 5:");
            for (String card : players.get(4).getCards())
                System.out.print("\u001B[30m" + "\u2503UNO\u2503");
            System.out.println();

            System.out.print("         ");
            for (String card : players.get(1).getCards())
                System.out.print("\u001B[30m" + "\u2517\u2501\u2501\u2501\u251B");
            System.out.print("               " + getColor(base) + "\u2517\u2501\u2501\u2501\u251B");
            System.out.print("               ");
            for (String card : players.get(4).getCards())
                System.out.print("\u001B[30m" + "\u2517\u2501\u2501\u2501\u251B");
            System.out.println();

            System.out.print("                                             ");
            for (String card : players.get(0).getCards())
                System.out.print(getColor(card) + "\u250F\u2501\u2501\u2501\u2513");
            System.out.println();
            System.out.print("                                    " + black + "player 1:");
            for (String card : players.get(0).getCards())
                System.out.print(card);
            System.out.println();
            System.out.print("                                             ");
            for (String card : players.get(0).getCards())
                System.out.print(getColor(card) + "\u2517\u2501\u2501\u2501\u251B");

        }
    }

    /**
     * check end of game by checking players cards size
     * @return true false
     */
    public boolean endGame() {
        for (Player player : players) {
            if (player.getCards().size() == 0) {
                System.out.println(black+"player won !");
                return true;
            }
        }
        return false;
    }
}

