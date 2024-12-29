import java.io.FileReader;
import java.lang.reflect.GenericDeclaration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    String[] builtInCommands = { "type", "echo", "exit" };

    while (true) {
      System.out.print("$ ");

      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();

      if (input.equals("exit 0")) {
        break;
      } else if (input.startsWith("echo")) {
        System.out.println(input.substring(5));
      } else if (input.startsWith("type")) {
        String command = input.substring(5);
        if (Arrays.asList(builtInCommands).contains(command)) {
          System.out.println(command + " is a shell builtin");
        } else {
          String path = getPath(command);
          if (path != null) {
            System.out.println(path);
          } else {
            System.out.println(command + ": not found");
          }
        }
      } else {
        System.out.println(input + ": command not found");
      }
    }
  }

  private static String getPath(String command) {
    String[] paths = System.getenv("PATH").split(":");
    for (String path : paths) {
      Path fullPath = Path.of(path, command);
      if (Files.isRegularFile(fullPath)) {
        return command + " is " + path + "/" + command;
      }
    }
    return null;
  }
}
