import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Runs multiple simulations of the Monopoly Simulation and determines stas
 * 
 * @author Ajay Jain
 * @version 5/20/2015
 */
public class MonopolyStatistics extends Monopoly
{    /**
     * mostFreq: holds the most frequent way of how each simulation ended
     */
    public String mostFreq;
    /**
     * values: holds how many turns each simulation lasts for 
     */
    public ArrayList<Integer> values = new ArrayList<Integer>();
    /**
     * highOutliers: holds all of the high outliers in values 
     */
    public ArrayList<Integer> highOutliers = new ArrayList<Integer>();
    /**
     * jail: determines how each simulation ends
     */
    public int[] jail;
    /**
     * doublesLanded: holds how many simulations ended by rolling three doubles in a row
     */
    public int doublesLanded;
    /**
     * spaceLanded: holds how many simulations ended by landing on the Go to Jail space.
     */
    public int spaceLanded;
    /**
     * doublesLanded: holds how many simulations ended by picking a Go to Jail Card
     */
    public int chanceLanded;
    /**
     * max: holds the maximum number of rolls needed to land in jail in a range of simulations.
     */
    public int max;
    
    /**
     * numRuns: holds how many rolls necessary to land in jail during one simulation
     */
    public int numRuns;
    /**
     * sum: holds the sum of the values array
     */
    public int sum;
    /**
     * mean: holds the mean of all of the values
     */
    public int mean;
    /**
     * median: holds the median of all of the values
     */
    public int median;
    /**
     * firstQuartile: holds the first quartile of the values
     */
    public int firstQuartile;
    /**
     * thirdQuartile: holds the third quartile of the values
     */
    public int thirdQuartile;
    /**
     * iqr: holds the interquartile range
     */
    public int iqr;
    /**
     * highEndOutlier: holds the minimum value for a high end outlier
     */
    public int highEndOutlier;
    /**
     * mode: holds the most frequent method of landing in jail.
     */
    public int mode;
    /**
     * output: determines what should be outputted with toString()
     */
    public int output;
    
    
    /**
     * Constructor for MonopolyStatistics objects.
     */
    public MonopolyStatistics()
    {
        super();
        sum = 0;
        doublesLanded = 0;
        chanceLanded =  0;
        spaceLanded = 0;
        numRuns = 0;
    }

    /**
     * Sets how many runs will occur
     * @param a Input from the user as to how many simulations he wishes to run
     */
    public void numRuns(int a){
        numRuns = a;
   }
    public void output(int a){
        if (a > 2){
            output = 2;
        }
        else if (a < 1){
            output = 1;
        }
        else{
            output = a;
        }
    }
    /**
     * Runs the desired number of simulations and determines statistical values and maximum value.
     */
    public void run()
    {
        for (int c = 0; c < numRuns + 1; c++){
            space = 1;
            while (space % 31 != 0){
               dice1 = (int)(Math.random() * 5 + 1.5);
               dice2 = (int)(Math.random() * 5 + 1.5); // Set two random values for the dice.
               space = space + dice1 + dice2;
               turn++;
               if (dice1 == dice2){
                    if (twoInARow == 3){
                        doublesLanded++;
                        twoInARow = 0;
                        break;
                    }
                    else{
                        twoInARow++;
                    }
               }
               else{
                    twoInARow = 0;
               }
               if (space >= 41){ // there are forty spaces on a monopoly board
                space = space - 40;
               }
               if (space == 8 || space == 23 || space == 37){ // If the player lands on the chance spaces
                    if (jailChance == chance){
                        chanceLanded++;
                        break;
                    }
                    else{
                        chance++;
                    }
               }
               else if (space == 3 || space == 18 || space == 34){ // If the player lands on community chest
                if (jailCommunity == community){
                    chanceLanded++;
                    break;
                }
                else{
                    community++;
                }
               }
            }
            if (space == 31){
                spaceLanded++;
            }
            
            values.add(turn);
            sum = sum + turn;
            turn = 0;
            //space = 0;
            jailChance = (int)(Math.random() * 15 + 1.5);
            jailCommunity = (int)(Math.random() * 15 + 1.5);
        }
        Collections.sort(values); // SORT VALUES
        mean = sum / numRuns;
        
        if (numRuns % 2 != 0){
            median = values.get(numRuns / 2 + 1);
            firstQuartile = values.get(numRuns / 4 + 1);
            thirdQuartile = values.get((int)(numRuns * .75) + 1);
        }
        else{
            median = (values.get(numRuns / 2) + values.get(numRuns / 2 + 1)) / 2;
            firstQuartile = (values.get(numRuns / 4) + values.get(numRuns / 4 + 1)) / 2;
            thirdQuartile = (values.get((int)(numRuns * .75)) + values.get((int)(numRuns * .75) + 1)) / 2;
        }
        iqr = thirdQuartile - firstQuartile;
        highEndOutlier = (int)(thirdQuartile + 1.5 * iqr);
        int y = values.get((int)(numRuns * .75) + 2);
        
        if (output == 1){
            for (int c = thirdQuartile; c < numRuns; c++){
                if (values.get(c) > highEndOutlier){
                    int x = values.get(c);
                    highOutliers.add(x);
                }
            }
        }
        else{
            max = values.get(values.size() - 1);
       }
        jail = new int[]{doublesLanded, chanceLanded, spaceLanded};
        Arrays.sort(jail);
        mode = jail[2];
        if (mode == doublesLanded){
            mostFreq = "rolling doubles.";
        }
        else if (mode == chanceLanded){
            mostFreq = "picking a go to jail card.";
        }
        else{
            mostFreq = "landing in jail.";
        }
            
    }
    /**
     * Returns either the number of rolls for one simulation, the statistical values for multiple simulations, or the maximum roll. 
     * @return Desired output, depending on the output chosen with the radio buttons. 
     */
    public String toString(){
       if (output == 1){
            return "The mean is " + mean + "\n " + "The median is " + median + "\n " + "The first and third quartiles are " + firstQuartile+" & "
            +thirdQuartile + "\n " + "The player's most common method of landing in jail was by " + mostFreq + "\n " +"The high end outliers are"
            + highOutliers;
       }
       else{
           return "The maximum number of rolls it took to land in jail was " + max;
       }
    }
   
}
        