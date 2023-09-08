package Commands;

import java.io.Serial;

/**
 * Class for work with command: help
 */
public class Help extends Command{

    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "help";
    private static final String description = ": Output all commands;";
    private static String[] arg = null;


    /**
     * Function print all command
     */
    public static String help(){
        if (arg != null && arg.length > 0){
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0];
        }
        String res = "-" + getName() + getDescription() + "\n" + "-" + Info.getName() + Info.getDescription() + "\n" +
        "-" + Show.getName() + Show.getDescription() + "\n" +
        "-" + Add.getName() + Add.getDescription()  + "\n" +
        "-" + Update.getName() + Update.getDescription() + "\n" +
        "-" + Remove.getName() + Remove.getDescription() + "\n" +
        "-" + Clear.getName() + Clear.getDescription() + "\n" +
        "-" + Save.getName() + Save.getDescription() + "\n" +
        "-" + Execute_script.getName() + Execute_script.getDescription() + "\n" +
        "-" + Exit.getName() + Exit.getDescription() + "\n" +
        "-" + Reorder.getName() + Reorder.getDescription() + "\n" +
        "-" + Sort.getName() + Sort.getDescription() + "\n" +
        "-" + History.getName() + History.getDescription() + "\n" +
        "-" + FilterByTypeAsc.getName() + FilterByTypeAsc.getDescription() + "\n" +
        "-" + PrintByAddressAscending.getName() + PrintByAddressAscending.getDescription() + "\n" +
        "-" + PrintByAddressDescending.getName() + PrintByAddressDescending.getDescription();
        return res;
    }

    public String execute(){
         return help();
    }

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
    
    public void setArg(String[] arg){
        Help.arg = arg;}
}
