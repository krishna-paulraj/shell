import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    while (true) {
      System.out.print("$ ");

      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();

      if (input.equals("exit 0")) {
        break;
      } else if (input.startsWith("echo")) {
        System.out.println(input.substring(5));
      } else if (input.startsWith("type")) {
        if (input.substring(5).equals("echo") || input.substring(5).equals("exit")) {
          System.out.println(input.substring(5) + " is a shell builtin");
        }
      } else {
        System.out.println(input + ": command not found");
      }
    }
  }
}
