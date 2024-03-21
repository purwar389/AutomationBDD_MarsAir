package com.gp.au.pageObjects;

import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtil {

    public boolean checkAuthentication(String endpointUrl) {
        try {
            // API endpoint URL
            URL url = new URL(endpointUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("POST");

            // Set request body (empty for this case)
            connection.setDoOutput(true);

            // Get response code
            int responseCode = connection.getResponseCode();

            // Close connection
            connection.disconnect();

            // Check if response code is 200 (OK)
            return responseCode == HttpURLConnection.HTTP_OK;

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of any exception
        }
    }

//    public static void main(String[] args) {
//        // Test the function
//        boolean authenticationRequired = checkAuthentication();
//        System.out.println("Authentication required: " + authenticationRequired);
//    }
}
