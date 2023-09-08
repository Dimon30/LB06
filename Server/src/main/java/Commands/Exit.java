package Commands;

/**
 * Class for actions with command: exit
 */
public class Exit extends Command{
    private static final String name = "exit";
    private static final String description = ": exit;";
    private static String[] arg;

    /**
     * Function to abort program
     */
    public static String exit(){

        if (arg.length > 0){
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0] + "\n";
        }
        System.exit(30);
        return "exit";
    }

    //public String execute(){return exit();}

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

    public void setArg(String[] ar){arg = ar;}
}
