package Commands;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/**
 * Class for actions with command: execute_script
 */
public class Execute_script extends Command{
    private static final String name = "execute_script";
    private final static String description = ": Load commands from file;";
    private static String[] arg;

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
     * Function take file and execute commands
     */
    public static String execute_script(){
        if (arg.length == 0) {
            return "Please input filename in format: 'execute_script filename'\n";
        }
        String filename = arg[0];
        File file = new File(filename);
        String str = "";
        String[] lines;
        String executeReturn = "";
        try (Scanner reader = new Scanner(new FileReader(file.getAbsolutePath()))) {
            while (reader.hasNext()) {
                str += reader.next() + "\n";
            }
            lines = str.split("\n");
            for (String command_arg : lines) {
                if(Command.validate(command_arg))
                    continue;
                String[] split = command_arg.split("\s");
                String command = split.length > 1 ? split[0] : command_arg;
                String[] arg = split.length > 1 ? Arrays.copyOfRange(split, 1, split.length) : new String[0];
                executeReturn += "'" + command_arg + "'\n";
                Command com = (Command) Command.getCommand(command);
                com.setArg(arg);
                executeReturn += com.execute();
            }
        } catch (FileNotFoundException e){
            return "Sorry, I don't find this file: '" + filename + "'";
        } catch (Exception e) {
            return "Something is going wrong(class: Execute_script)";
        }
        return executeReturn;
    }
    public String execute(){return execute_script();}
}
