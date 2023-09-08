package Commands;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class for work with command: history
 */
public class History extends Command{
    private static final String name = "history";
    private final static String description = ": Show 8 last commands;";
    private static String[] arg;
    private static Deque<String> history = new ArrayDeque<>(8);


    /**
     * Function to get name of command
     * @return name of command
     */
    public static String getName(){return name;}
    /**
     * Function to get description of command
     * @return description of command
     */
    public static String getDescription(){return description;}

    /**
     * Function print last 8 commands which user inputted
     */
    public static String history() {

        if (arg.length > 0){
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0];
        }
        String[] historyReturn = {""};
        history.forEach(h -> historyReturn[0] += h);
        return historyReturn[0];
    }
    public String execute(){return history();}

    public static void setHistory(Deque<String> his){history = his;}
}
