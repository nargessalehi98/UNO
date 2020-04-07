import java.util.HashMap;

public class Cards {
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";

    private String YSkip = yellow + "\u2503skip\u2503";
    private String YRotate = yellow + "\u2503rvrs\u2503";
    private String YPlus2 = yellow + "\u2503 +2 \u2503";
    private String Y0 = yellow + "\u2503 0 \u2503";
    private String Y1 = yellow + "\u2503 1  \u2503";
    private String Y2 = yellow + "\u2503 2  \u2503";
    private String Y3 = yellow + "\u2503 3  \u2503";
    private String Y4 = yellow + "\u2503 4  \u2503";
    private String Y5 = yellow + "\u2503 5  \u2503";
    private String Y6 = yellow + "\u2503 6  \u2503";
    private String Y7 = yellow + "\u2503 7  \u2503";
    private String Y8 = yellow + "\u2503 8  \u2503";
    private String Y9 = yellow + "\u2503 9  \u2503";

    private String BSkip = blue + "\u2503skip\u2503";
    private String BRotate = blue + "\u2503rvrs\u2503";
    private String BPlus2 = blue + "\u2503 +2 \u2503";
    private String B0 = blue + "\u2503 0 \u2503";
    private String B1 = blue + "\u2503 1  \u2503";
    private String B2 = blue + "\u2503 2  \u2503";
    private String B3 = blue + "\u2503 3  \u2503";
    private String B4 = blue + "\u2503 4  \u2503";
    private String B5 = blue + "\u2503 5  \u2503";
    private String B6 = blue + "\u2503 6  \u2503";
    private String B7 = blue + "\u2503 7  \u2503";
    private String B8 = blue + "\u2503 8  \u2503";
    private String B9 = blue + "\u2503 9  \u2503";

    private String RSkip = red + "\u2503skip\u2503";
    private String RRotate = red + "\u2503rvrs\u2503";
    private String RPlus2 = red + "\u2503 +2 \u2503";
    private String R0 = red + "\u2503 0 \u2503";
    private String R1 = red + "\u2503 1  \u2503";
    private String R2 = red + "\u2503 2  \u2503";
    private String R3 = red + "\u2503 3  \u2503";
    private String R4 = red + "\u2503 4  \u2503";
    private String R5 = red + "\u2503 5  \u2503";
    private String R6 = red + "\u2503 6  \u2503";
    private String R7 = red + "\u2503 7  \u2503";
    private String R8 = red + "\u2503 8  \u2503";
    private String R9 = red + "\u2503 9  \u2503";

    private String GSkip = green + "\u2503skip\u2503";
    private String GRotate = green + "\u2503rvrs\u2503";
    private String GPlus2 = green + "\u2503 +2 \u2503";
    private String G0 = green + "\u2503 0 \u2503";
    private String G1 = green + "\u2503 1  \u2503";
    private String G2 = green + "\u2503 2  \u2503";
    private String G3 = green + "\u2503 3  \u2503";
    private String G4 = green + "\u2503 4  \u2503";
    private String G5 = green + "\u2503 5  \u2503";
    private String G6 = green + "\u2503 6  \u2503";
    private String G7 = green + "\u2503 7  \u2503";
    private String G8 = green + "\u2503 8  \u2503";
    private String G9 = green + "\u2503 9  \u2503";

    //14
    private String wildPlus4 = black + "\u2503+4🌈" + green + black + "\u2503";
    private String wildColor = black + "\u2503" + " 🌈 " + green + black + "\u2503";

    private HashMap<Integer, String> cards;

    public Cards() {
        cards = new HashMap();
        cards.put(111,wildPlus4);
        cards.put(112,wildPlus4);
        cards.put(113,wildPlus4);
        cards.put(114,wildPlus4);
        cards.put(121,wildColor);
        cards.put(122,wildColor);
        cards.put(123,wildColor);
        cards.put(124,wildColor);
        cards.put(201,G0);
        cards.put(211,G1);
        cards.put(212,G1);
        cards.put(221,G2);
        cards.put(222,G2);
        cards.put(231,G3);
        cards.put(232,G3);
        cards.put(241,G4);
        cards.put(242,G4);
        cards.put(251,G5);
        cards.put(252,G5);
        cards.put(261,G6);
        cards.put(262,G6);
        cards.put(271,G7);
        cards.put(272,G7);
        cards.put(281,G8);
        cards.put(282,G8);
        cards.put(291,G9);
        cards.put(292,G9);
        cards.put(2101,GRotate);
        cards.put(2102,GRotate);
        cards.put(2111,GSkip);
        cards.put(2112,GSkip);
        cards.put(2121,GPlus2);
        cards.put(2122,GPlus2);

        cards.put(301,B0);
        cards.put(311,B1);
        cards.put(312,B1);
        cards.put(321,B2);
        cards.put(322,B2);
        cards.put(331,B3);
        cards.put(332,B3);
        cards.put(341,B4);
        cards.put(342,B4);
        cards.put(351,B5);
        cards.put(352,B5);
        cards.put(361,B6);
        cards.put(362,B6);
        cards.put(371,B7);
        cards.put(372,B7);
        cards.put(381,B8);
        cards.put(382,B8);
        cards.put(391,B9);
        cards.put(392,B9);
        cards.put(3101,BRotate);
        cards.put(3102,BRotate);
        cards.put(3111,BSkip);
        cards.put(3112,BSkip);
        cards.put(3121,BPlus2);
        cards.put(3122,BPlus2);

        cards.put(401,Y0);
        cards.put(411,Y1);
        cards.put(412,Y1);
        cards.put(421,Y2);
        cards.put(422,Y2);
        cards.put(431,Y3);
        cards.put(432,Y3);
        cards.put(441,Y4);
        cards.put(442,Y4);
        cards.put(451,Y5);
        cards.put(452,Y5);
        cards.put(461,Y6);
        cards.put(462,Y6);
        cards.put(471,Y7);
        cards.put(472,Y7);
        cards.put(481,Y8);
        cards.put(482,Y8);
        cards.put(491,Y9);
        cards.put(492,Y9);
        cards.put(4101,YRotate);
        cards.put(4102,YRotate);
        cards.put(4111,YSkip);
        cards.put(4112,YSkip);
        cards.put(4121,YPlus2);
        cards.put(4122,YPlus2);

        cards.put(501,R0);
        cards.put(511,R1);
        cards.put(512,R1);
        cards.put(521,R2);
        cards.put(5222,R2);
        cards.put(531,R3);
        cards.put(532,R3);
        cards.put(541,R4);
        cards.put(542,R4);
        cards.put(551,R5);
        cards.put(552,R5);
        cards.put(561,R6);
        cards.put(562,R6);
        cards.put(571,R7);
        cards.put(572,R7);
        cards.put(581,R8);
        cards.put(582,R8);
        cards.put(591,R9);
        cards.put(592,R9);
        cards.put(5101,RRotate);
        cards.put(5102,RRotate);
        cards.put(5111,RSkip);
        cards.put(5112,RSkip);
        cards.put(5121,RPlus2);
        cards.put(5122,RPlus2);



    }

    public void addCard(Integer key, String value) {
        cards.put(key, value);
    }

    public void removeCard(Integer key, String  value) {
        cards.replace(key, value);
    }

    public String getCard(Integer key) {
        return cards.get(key);
    }
}
