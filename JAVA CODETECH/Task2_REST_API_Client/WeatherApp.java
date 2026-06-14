/*
---------------------------------------------------------
CODTECH INTERNSHIP - TASK 2
REST API CLIENT IN JAVA

TASK:
Create a Java application that consumes a public REST API
and displays the data in a structured format.

API USED:
https://api.agify.io

AUTHOR : GUDIMETLA AYESHA
LANGUAGE : JAVA
---------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // Get user input
            System.out.print("Enter a name: ");
            String name = sc.nextLine();

            // API URL
            String apiUrl = "https://api.agify.io/?name=" + name;

            // Create URL object
            URL url = new URL(apiUrl);

            // Open HTTP connection
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                // Read API response
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        connection.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }

                reader.close();

                // Convert response to string
                String jsonResponse = response.toString();

                // Display raw JSON
                System.out.println("\nRAW JSON RESPONSE:");
                System.out.println(jsonResponse);

                // Extract values manually
                String extractedName =
                        jsonResponse.split("\"name\":\"")[1]
                                .split("\"")[0];

                String extractedAge =
                        jsonResponse.split("\"age\":")[1]
                                .split(",")[0];

                String extractedCount =
                        jsonResponse.split("\"count\":")[1]
                                .split(",")[0];

                // Display structured output
                System.out.println("\n===== STRUCTURED DATA =====");
                System.out.println("Name             : " + extractedName);
                System.out.println("Predicted Age    : " + extractedAge);
                System.out.println("Prediction Count : " + extractedCount);
                System.out.println("===========================");

            } else {

                System.out.println("HTTP Request Failed");
                System.out.println("Response Code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {

            System.out.println("Error occurred while accessing API.");
            e.printStackTrace();
        }

        sc.close();
    }
}