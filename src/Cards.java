import java.util.Collection;
import java.util.HashMap;

/**
 * this class manage a full set cards
 * its one of GameRules subclass
 *
 * @author Narges Salehi
 */
public class Cards extends GameRules {

    //defining cards as String with color + kind for each color and each kind
    //yellow cards
    //skip card String
    private String Yskip = yellow + "\u2503skp\u2503";
    //revers card String
    private String Yrotate = yellow + "\u2503rvs\u2503";
    //plus 2 card String
    private String Yplus2 = yellow + "\u2503+ 2\u2503";
    //number cards String
    private String Y0 = yellow + "\u2503 0 \u2503";
    private String Y1 = yellow + "\u2503 1 \u2503";
    private String Y2 = yellow + "\u2503 2 \u2503";
    private String Y3 = yellow + "\u2503 3 \u2503";
    private String Y4 = yellow + "\u2503 4 \u2503";
    private String Y5 = yellow + "\u2503 5 \u2503";
    private String Y6 = yellow + "\u2503 6 \u2503";
    private String Y7 = yellow + "\u2503 7 \u2503";
    private String Y8 = yellow + "\u2503 8 \u2503";
    private String Y9 = yellow + "\u2503 9 \u2503";
    //same for blue
    private String Bskip = blue + "\u2503skp\u2503";
    private String Brotate = blue + "\u2503rvs\u2503";
    private String Bplus2 = blue + "\u2503+ 2\u2503";
    private String B0 = blue + "\u2503 0 \u2503";
    private String B1 = blue + "\u2503 1 \u2503";
    private String B2 = blue + "\u2503 2 \u2503";
    private String B3 = blue + "\u2503 3 \u2503";
    private String B4 = blue + "\u2503 4 \u2503";
    private String B5 = blue + "\u2503 5 \u2503";
    private String B6 = blue + "\u2503 6 \u2503";
    private String B7 = blue + "\u2503 7 \u2503";
    private String B8 = blue + "\u2503 8 \u2503";
    private String B9 = blue + "\u2503 9 \u2503";
    //same for red
    private String Rskip = red + "\u2503skp\u2503";
    private String Rrotate = red + "\u2503rvs\u2503";
    private String Rplus2 = red + "\u2503+ 2\u2503";
    private String R0 = red + "\u2503 0 \u2503";
    private String R1 = red + "\u2503 1 \u2503";
    private String R2 = red + "\u2503 2 \u2503";
    private String R3 = red + "\u2503 3 \u2503";
    private String R4 = red + "\u2503 4 \u2503";
    private String R5 = red + "\u2503 5 \u2503";
    private String R6 = red + "\u2503 6 \u2503";
    private String R7 = red + "\u2503 7 \u2503";
    private String R8 = red + "\u2503 8 \u2503";
    private String R9 = red + "\u2503 9 \u2503";
    //same for green
    private String Gskip = green + "\u2503skp\u2503";
    private String Grotate = green + "\u2503rvs\u2503";
    private String Gplus2 = green + "\u2503+ 2\u2503";
    private String G0 = green + "\u2503 0 \u2503";
    private String G1 = green + "\u2503 1 \u2503";
    private String G2 = green + "\u2503 2 \u2503";
    private String G3 = green + "\u2503 3 \u2503";
    private String G4 = green + "\u2503 4 \u2503";
    private String G5 = green + "\u2503 5 \u2503";
    private String G6 = green + "\u2503 6 \u2503";
    private String G7 = green + "\u2503 7 \u2503";
    private String G8 = green + "\u2503 8 \u2503";
    private String G9 = green + "\u2503 9 \u2503";
    //wild cards
    //plus four card
    private String wildPlus4 = black + "\u2503" + blue + "+ " + red + "4" + black + "\u2503";
    //change color card
    private String wildColor = black + "\u2503" + blue + "\u2503" + green + "\u2503" + red + "\u2503" + green + black + "\u2503";

    //new hash map for a card set  with a key as String
    private HashMap<String, String> cards;

    /**
     * creat a new cards
     */
    public Cards() {
        cards = new HashMap();
        //add all the 108 card to each cars set
        //add wild cards
        cards.put("000", wildPlus4);
        cards.put("001", wildPlus4);
        cards.put("002", wildPlus4);
        cards.put("003", wildPlus4);
        cards.put("010", wildColor);
        cards.put("011", wildColor);
        cards.put("012", wildColor);
        cards.put("013", wildColor);
        //add green cards
        cards.put("100", G0);
        cards.put("110", G1);
        cards.put("111", G1);
        cards.put("120", G2);
        cards.put("121", G2);
        cards.put("130", G3);
        cards.put("131", G3);
        cards.put("140", G4);
        cards.put("141", G4);
        cards.put("150", G5);
        cards.put("151", G5);
        cards.put("160", G6);
        cards.put("161", G6);
        cards.put("170", G7);
        cards.put("171", G7);
        cards.put("180", G8);
        cards.put("181", G8);
        cards.put("190", G9);
        cards.put("191", G9);
        cards.put("1100", Grotate);
        cards.put("1101", Grotate);
        cards.put("1110", Gskip);
        cards.put("1111", Gskip);
        cards.put("1120", Gplus2);
        cards.put("1121", Gplus2);
        //blue cards
        cards.put("200", B0);
        cards.put("210", B1);
        cards.put("211", B1);
        cards.put("220", B2);
        cards.put("221", B2);
        cards.put("230", B3);
        cards.put("231", B3);
        cards.put("240", B4);
        cards.put("241", B4);
        cards.put("250", B5);
        cards.put("251", B5);
        cards.put("260", B6);
        cards.put("261", B6);
        cards.put("270", B7);
        cards.put("271", B7);
        cards.put("280", B8);
        cards.put("281", B8);
        cards.put("290", B9);
        cards.put("291", B9);
        cards.put("2100", Brotate);
        cards.put("2101", Brotate);
        cards.put("2110", Bskip);
        cards.put("2111", Bskip);
        cards.put("2120", Bplus2);
        cards.put("2121", Bplus2);
        //add yellow cards
        cards.put("300", Y0);
        cards.put("310", Y1);
        cards.put("311", Y1);
        cards.put("320", Y2);
        cards.put("321", Y2);
        cards.put("330", Y3);
        cards.put("331", Y3);
        cards.put("340", Y4);
        cards.put("341", Y4);
        cards.put("350", Y5);
        cards.put("351", Y5);
        cards.put("360", Y6);
        cards.put("361", Y6);
        cards.put("370", Y7);
        cards.put("371", Y7);
        cards.put("380", Y8);
        cards.put("381", Y8);
        cards.put("390", Y9);
        cards.put("391", Y9);
        cards.put("3100", Yrotate);
        cards.put("3101", Yrotate);
        cards.put("3110", Yskip);
        cards.put("3111", Yskip);
        cards.put("3120", Yplus2);
        cards.put("3121", Yplus2);
        //add red cards
        cards.put("400", R0);
        cards.put("410", R1);
        cards.put("411", R1);
        cards.put("420", R2);
        cards.put("421", R2);
        cards.put("430", R3);
        cards.put("431", R3);
        cards.put("440", R4);
        cards.put("441", R4);
        cards.put("450", R5);
        cards.put("451", R5);
        cards.put("460", R6);
        cards.put("461", R6);
        cards.put("470", R7);
        cards.put("471", R7);
        cards.put("480", R8);
        cards.put("481", R8);
        cards.put("490", R9);
        cards.put("491", R9);
        cards.put("4100", Rrotate);
        cards.put("4101", Rrotate);
        cards.put("4110", Rskip);
        cards.put("4111", Rskip);
        cards.put("4120", Rplus2);
        cards.put("4121", Rplus2);
    }

    /**
     * return list of 108 cards
     *
     * @return values of cards
     */
    public Collection<String> getValues() {
        return cards.values();
    }

    /**
     * get card String for given key
     *
     * @param key of card
     * @return card
     */
    public String getValue(String key) {
        return cards.get(key);
    }

    /**
     * remove a card with given key and value
     *
     * @param key   of card
     * @param value of card
     */
    public void removeCard(String key, String value) {
        cards.remove(key, value);
    }

    /**
     * check if the given key is available or not
     *
     * @param key of card
     * @return true of false
     */
    public boolean containKey(String key) {
        return cards.containsKey(key);
    }

}
