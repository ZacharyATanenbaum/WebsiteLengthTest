import java.util.HashMap;
import java.util.Scanner;

/**
 * The main class for the Language Zen Interview Test.
 */

public class Main {

    public static HashMap<String, Integer> websiteResponses = new HashMap<>();

    public static void main(String[] args) {

        // Websites
        String[] websites = {"http://www.google.com",
                "http://www.cnn.com",
                "http://www.yahoo.com"};

        String command = "";
        int selection = -1;
        int response = -1;

        // Start Scanning for Commands
        Scanner scanner = new Scanner(System.in);

        // Keep scanning for new commands until quit
        while (!command.equals("quit")) {

            // Display Prompt:
            System.out.println("Please select a choice or type \"quit\" to exit");
            for (int i=0; i<websites.length; i++) {
                System.out.println(""+(i+1)+" "+websites[i]);
            }

            // Scan Line
            command = scanner.nextLine();

            // Check if quit command
            if (command == "quit") {
                break;
            }

            // Check if valid command
            selection = -1;
            try {
                selection = Integer.parseInt(command);
            } catch (Exception e) {
                System.out.println("Please enter a valid command.");
            }

            // If a valid number in the Websites array return response
            if( selection <= websites.length && selection >= 1) {

                String website = websites[selection-1];

                // Check if response is cached
                if (websiteResponses.get(website) == null) {
                    // If not cashed get response
                    websiteResponses.put(website, -1);
                    Runnable runnable = new WebGetterRunnable(website);
                    new Thread(runnable).start();

                } else {
                    // If the response is cashed and not -1 display the response
                    response = websiteResponses.get(website);

                    if (response != -1) {
                        System.out.println(website+" "+response);
                    } else {
                        System.out.println("Please wait for query to finish");
                    }
                }

            // If not a valid website choice
            } else {
                System.out.print("Please enter a valid website choice.");
            }

            // Add a space to separate querys
            System.out.println();

        }

        // Closing Server and all threads
        System.out.println("Closing down server.");
        scanner.close();
        System.exit(0);

    }

}
