import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherFetcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Latitude: ");
        String lat = scanner.nextLine();
        System.out.print("Enter Longitude: ");
        String lon = scanner.nextLine();

        String apiUrl = "https://www.7timer.info/bin/astro.php?lon=" + lon + "&lat=" + lat + "&ac=0&unit=metric&output=json";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println(response.body());
            } else {
                System.out.println("Error: Received status code " + response.statusCode());
            }

        } catch (Exception e) {
            System.out.println("Request failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}