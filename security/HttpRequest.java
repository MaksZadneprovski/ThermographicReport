package security;

import utils.Exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class HttpRequest {
    public void doRequest() throws IOException {
        URL url = new URL("http://google.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (final Exception ex) {
            System.out.println("Нет подключения к интернету");
            new Exit().exitProgram();
        }
    }

}
