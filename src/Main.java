import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Series seriesApp = new Series(scanner);

        System.out.println("LATEST SERIES - 2025");
        System.out.println("************************************");
        System.out.print("Enter (1) to launch or any other key to exit: ");

        String input = scanner.nextLine();
        if (!input.equals("1")) {
            exitProgram(scanner);
            return;
        }

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    seriesApp.captureSeries();
                    break;
                case "2":
                    seriesApp.searchSeries();
                    break;
                case "3":
                    seriesApp.updateSeries();
                    break;
                case "4":
                    seriesApp.deleteSeries();
                    break;
                case "5":
                    seriesApp.seriesReport();
                    break;
                case "6":
                    seriesApp.exitSeriesApplication();
                    exitProgram(scanner);
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.print("\nEnter (1) to continue or any other key to exit: ");
            if (!scanner.nextLine().equals("1")) {
                exitProgram(scanner);
                return;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nPlease select one of the following menu items:");
        System.out.println("1. Capture a new series");
        System.out.println("2. Search for a series");
        System.out.println("3. Update a series");
        System.out.println("4. Delete a series");
        System.out.println("5. Print series report - 2025");
        System.out.println("6. Exit Application");
    }

    private static void exitProgram(Scanner scanner) {
        System.out.println("\nThank you for using Series Manager! See you next time!");
        scanner.close();
    }
}