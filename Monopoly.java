/**
 * The Monopoly class runs one game of Monopoly and determines how many
 * runs it takes for a player of Monopoly to land in jail.
 * @author Ajay Jain
 * @version 5/26/2015
 */
import java.lang.Math;
import java.awt.*;
public class Monopoly
{   
    /**
     * twoInARow: logs if more than one set of doubles is rolled in a row
     */
    public int twoInARow; 
    /**
     * space: logs what space the player is on
     */
    public int space; 
    /**
     * dice1: holds the value of dice1 (between 1 and 6)
     */
    public int dice1; 
    /**
     * dice2: holds the value of dice2 (between 1 and 6)
     */
    public int dice2; 
    /**
     * turn: logs how many turns it takes for the player to land in jail
     */
    public int turn; 
    /**
     * chance: determines which chance card will be picked up
     */
    public int chance;
    /**
     * community: determines which community chest card will be picked up
     */
    public int community;
    /**
     * jailChance: determines which chance card is the Go to Jail card (between 1 and 16)
     */
    public int jailChance;
    /**
     * jailCommunity: determines which community chest card is the Go to Jail card (between 1 and 16)
     */
    public int jailCommunity;

   /**
     * Constructor for objects of class Monopoly
    */
   public Monopoly()
   {
        space = 1; // 1 is the Go Space
        turn = 0;
        chance = 1;
        jailChance = (int)(Math.random() * 15 + 1.5);
        jailCommunity = (int)(Math.random() * 15 + 1.5);
   }
    /**
     * The run method runs one Monopoly simulation and determines how many runs it takes for the user to land in jail.
     */
    public void run()
    {
       while (space % 31 != 0){
            dice1 = (int)(Math.random() * 5 + 1.5);
            dice2 = (int)(Math.random() * 5 + 1.5); // Set two random values for the dice.
            space = space + dice1 + dice2;
            turn++;
            if (dice1 == dice2){
                if (twoInARow == 3){
                    break;
                }
                else{
                    twoInARow++;
                }
            }
            else{
                twoInARow = 0;
            }
            if (space == 8 || space == 23 || space == 37){ // If the player lands on the chance spaces
                if (jailChance == chance){
                    break;
                }
                else{
                    chance++;
                }
            }
            else if (space == 3 || space == 18 || space == 34){ // If the player lands on community chest
                if (jailCommunity == community){
                    break;
                }
                else{
                    community++;
                }
            }
            if (space >= 41){ // there are forty spaces on a monopoly board
                space = space - 40;
            }
     }
   }
   /**
     * Returns how the user got to jail and after how many turns.
     */   
   public String toString(){
       if (twoInARow == 3){
           return "You rolled three doubles in a row. You hit jail after " + turn + " turns.";
       }
       else if (jailChance == chance){
           return "You picked up the Go to Jail chance card. You hit jail after " + turn + " turns.";
       }
       else if (jailCommunity == community){
           return "You picked up the Go to Jail community chest card. You hit jail after " + turn + " turns.";
       }
       else{
           return "You landed on the Go to Jail space after " + turn + " turns.";
        }
   }
}