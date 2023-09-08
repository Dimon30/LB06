package Commands;

import Modules.SendRequest;
import Organization.Organization;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "239";
    private String[] arg;
    private Vector<Organization> org;
    private Organization o;
    private String message;
    private static Map<String, Object> commandMap = new HashMap<String, Object>();
    static {
        commandMap.put("testCommand", new TestCommand());
        commandMap.put(Help.getName(), new Help());
        commandMap.put(Info.getName(), new Info());
        commandMap.put(Show.getName(), new Show());
        commandMap.put(Add.getName(), new Add());
        //commandMap.put(Update.getName(), new Update());
        commandMap.put(Remove.getName(), new Remove());
        commandMap.put(Clear.getName(), new Clear());
        commandMap.put(Save.getName(), new Save());
        commandMap.put(Execute_script.getName(), new Execute_script());
        commandMap.put(Exit.getName(), new Exit());
        commandMap.put(Reorder.getName(), new Reorder());
        commandMap.put(Sort.getName(), new Sort());
        commandMap.put(History.getName(), new History());
        commandMap.put(FilterByTypeAsc.getName(), new FilterByTypeAsc());
        commandMap.put(PrintByAddressAscending.getName(), new PrintByAddressAscending());
        commandMap.put(PrintByAddressDescending.getName(), new PrintByAddressDescending());
    }
    public static boolean validate(String command_arg){
        String command = command_arg.split("\s").length > 1 ? command_arg.split("\s")[0] : command_arg;
        if(!commandMap.containsKey(command))
            return false;
        return true;
    }

    public static void sendCommand(Socket socket, String command_arg, Organization t) {
        String[] split = command_arg.split("\s");
        String command = split.length > 1 ? split[0] : command_arg;
        String[] arg = split.length > 1 ? Arrays.copyOfRange(split, 1, split.length) : new String[0];

        try{
            // Serialize command
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            Command o = (Command)commandMap.get(command);
            o.setOrganization(t);
            o.setArg(arg);
            //System.out.println(o);
            output.writeObject(o);
        }catch (IOException e){
            System.out.println("Error sending command\nProbably server unavailable");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error sending command");
        }
        //SendRequest.sendRequest(socket, "command_is_ready");
    }

    public String execute(){
        return "I'm in command";
    }

    public static String getName(){return name;}

    public static Object getCommand(String command){
        return commandMap.get(command);
    }
    public String getCommandArg(){return name + arg;}

    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {this.org = organizations;}
    public void setArg(String[] arg){this.arg = arg;}
    public void setOrganization(Organization or){o = or;}
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
}
