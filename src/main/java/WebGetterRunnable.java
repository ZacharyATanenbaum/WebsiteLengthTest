import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zacht on 7/7/2017.
 */
public class WebGetterRunnable implements Runnable {

    private String url;

    // Constructor to get URL
    WebGetterRunnable(String url) {
        this.url = url;
    }

    // Executed on Thread.start()
    public void run() {

        try {
            // Create connection to provided url
            URL urlObj = new URL(this.url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlObj.openConnection();

            // Set Connection to a GET Request
            httpURLConnection.setRequestMethod("GET");

            // Read HTTP Connection using Buffered Reader
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = inputStream.readLine()) != null) {
                response.append(inputLine);
            }
            inputStream.close();

            // Save Response to Hashmap
            Main.websiteResponses.put(url, response.length());

            // Print out Response once it has been completed
            System.out.println(url+ " " +response.length());

        } catch (Exception e) {
            // Print out Error in Connection
            System.out.println("Error in HTTP Get Request: "+e);
        }
    }


}
