import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * class run whole program
 * @author Narges Salehi
 */
public class Main {
    //to make string black
    public static final String black = "\u001B[30m";

    public static void main(String[] args) throws InterruptedException {
        //number of player
        int num;
        //check number of player is more than 2 and less than 6
        do {
            //read number of player
            System.out.println("Enter Number Of Players 3 to 5 :");
            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
        } while (num <= 2 || num >= 6);
        GameRules game = new GameRules(num);
        //give card to player
        game.giveCard();
        //set base
        game.firstBase();
        //print game design
        game.designGround();
        //sleep for 2 second
        TimeUnit.SECONDS.sleep(2);
        //game starts
        if (num == 3) {
            while (true) {
                if (turn(game)) break;
                if (turn(game)) break;
                if (turn(game)) break;
                game.printScore();
            }
        }
        if (num == 4) {
            while (true) {
                if (turn(game)) break;
                if (turn(game)) break;
                if (turn(game)) break;
                if (turn(game)) break;
                game.printScore();
            }
        }
        if (num == 5) {
            while (true) {
                if (turn(game)) break;
                if (turn(game)) break;
                if (turn(game)) break;
                if (turn(game)) break;
                if (turn(game)) break;
                game.printScore();
            }
        }
    }

    /**
     *  this method run game for each player - ask or put proper card - print all the data
     */
    private static boolean turn(GameRules game) throws InterruptedException {
        //show direction at every turn
        System.out.println(black + "\nRotate Direction : " + game.getDirection());
        //show who's turn is it now
        System.out.println(black + "player :" + game.getTurn());
        //put card
        game.putCart();
        //check end game condition
        if (game.endGame()) {
            game.designGround();
            //show player with scores 
            game.printScoreChart();
            return true;
        }
        game.designGround();
        TimeUnit.SECONDS.sleep(5);
        return false;
    }
}
