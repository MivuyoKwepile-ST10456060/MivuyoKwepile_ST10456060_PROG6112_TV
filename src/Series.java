import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Series {
    public final List<SeriesModel> seriesList;
    private final Scanner scanner;

    public Series(Scanner scanner) {
        this.scanner = scanner;
        this.seriesList = new ArrayList<>();
    }

    public boolean isValidAge(String age) {
        if (age == null) return false;
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue >= 2 && ageValue <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isValidEpisodeCount(String episodes) {
        if (episodes == null) return false;
        try {
            int count = Integer.parseInt(episodes);
            return count > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void addSeries(SeriesModel series) {
        if (getSeriesById(series.getSeriesId()) == null) {
            seriesList.add(series);
        }
    }

    public SeriesModel getSeriesById(String id) {
        for (SeriesModel series : seriesList) {
            if (series.getSeriesId().equalsIgnoreCase(id)) {
                return series;
            }
        }
        return null;
    }

    public void captureSeries() {
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("****************************");

        String seriesId;
        while (true) {
            System.out.print("Enter the series ID: ");
            seriesId = scanner.nextLine().trim();
            if (!seriesId.isEmpty() && getSeriesById(seriesId) == null) break;
            System.out.println("Please enter a non-empty, unique ID.");
        }

        System.out.print("Enter the series name: ");
        String seriesName = scanner.nextLine().trim();

        String seriesAge;
        while (true) {
            System.out.print("Enter the age restriction (2-18): ");
            seriesAge = scanner.nextLine();
            if (isValidAge(seriesAge)) break;
            System.out.println("You have entered an incorrect series age!!!");
        }

        int episodes;
        while (true) {
            System.out.print("Enter the number of episodes: ");
            String episodesInput = scanner.nextLine();
            if (isValidEpisodeCount(episodesInput)) {
                episodes = Integer.parseInt(episodesInput);
                break;
            }
            System.out.println("Please enter a positive number.");
        }

        SeriesModel newSeries = new SeriesModel(seriesId, seriesName, seriesAge, episodes);
        addSeries(newSeries);
        System.out.println("\nSeries processed successfully!!!");
    }

    public void searchSeries() {
        System.out.println("\nFIND A SERIES");
        System.out.println("***********************");
        System.out.print("Enter series ID to search: ");
        String id = scanner.nextLine();

        SeriesModel foundSeries = getSeriesById(id);
        if (foundSeries != null) {
            System.out.println("\nSeries Found!");
            System.out.println(foundSeries);
        } else {
            System.out.println("\nSorry, we couldn't find a series with ID: " + id);
        }
    }

    public void updateSeries() {
        System.out.println("\nUPDATE A SERIES");
        System.out.println("*************************");
        System.out.print("Enter series ID to update: ");
        String id = scanner.nextLine();

        SeriesModel seriesToUpdate = getSeriesById(id);
        if (seriesToUpdate == null) {
            System.out.println("\nSeries not found. Please check the ID and try again.");
            return;
        }

        System.out.println("\nCurrent details:");
        System.out.println(seriesToUpdate);
        System.out.println("\nEnter new details (press Enter to keep current value):");

        System.out.print("Name [" + seriesToUpdate.getSeriesName() + "]: ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) seriesToUpdate.setSeriesName(newName);

        String newAge;
        while (true) {
            System.out.print("Age restriction [" + seriesToUpdate.getSeriesAge() + "]: ");
            newAge = scanner.nextLine();
            if (newAge.isEmpty()) break;
            if (isValidAge(newAge)) {
                seriesToUpdate.setSeriesAge(newAge);
                break;
            }
            System.out.println("Age must be between 2 and 18.");
        }

        int newEpisodes = seriesToUpdate.getSeriesNumberOfEpisodes();
        while (true) {
            System.out.print("Episode count [" + newEpisodes + "]: ");
            String episodesInput = scanner.nextLine();
            if (episodesInput.isEmpty()) break;
            if (isValidEpisodeCount(episodesInput)) {
                newEpisodes = Integer.parseInt(episodesInput);
                seriesToUpdate.setSeriesNumberOfEpisodes(newEpisodes);
                break;
            }
            System.out.println("Please enter a positive number.");
        }

        System.out.println("\nSuccess! The series has been updated!");
    }

    public void deleteSeries() {
        System.out.print("Enter the series id to delete: ");
        String id = scanner.nextLine();
        SeriesModel s = getSeriesById(id);
        if (s == null) {
            System.out.println("---    ---");
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println("---    ---");
            return;
        }

        System.out.print("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete: ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            boolean deleted = deleteSeriesById(id);
            if (deleted) {
                System.out.println("---    ---");
                System.out.println("Series with Series Id: " + id + " WAS deleted!");
                System.out.println("---    ---");
            } else {
                System.out.println("Deletion failed unexpectedly.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    public boolean deleteSeriesById(String id) {
        SeriesModel seriesToDelete = getSeriesById(id);
        if (seriesToDelete != null) {
            return seriesList.remove(seriesToDelete);
        }
        return false;
    }

    public void seriesReport() {
        System.out.println("\nSERIES CATALOG-2025");
        System.out.println("========================");

        if (seriesList.isEmpty()) {
            System.out.println("No series in the catalog yet.");
            return;
        }

        System.out.println("Total series: " + seriesList.size());
        System.out.println("-----------------------");

        for (int i = 0; i < seriesList.size(); i++) {
            System.out.println("Series #" + (i + 1));
            System.out.println(seriesList.get(i));
            if (i < seriesList.size() - 1) {
                System.out.println("-----------------------");
            }
        }
    }

    public void exitSeriesApplication() {
        System.out.println("\nThank you for using Series Manager!");
        System.out.println("Goodbye!");
    }
}