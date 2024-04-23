import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class WeatherForecast {

    public static void main(String[] args) {

        try {

            // parameters defined
            String url = "https://api.open-meteo.com/v1/forecast?";
            String X = "39.168804";
            String Y = "-86.536659";
            String Z = "temperature_2m";
            String W = "fahrenheit";
            String V = "EST";

            // contact the url with the parameters
            HttpURLConnection connect = (HttpURLConnection) new URL(url+"latitude="+X+"&longitude="+Y+"&hourly="+Z+"&temperature_unit="+W+"&timezone="+V).openConnection();

            // set request method to "GET"
            connect.setRequestMethod("GET");
            int responseCode = connect.getResponseCode();

            // check if the response code is 200
            if(responseCode == 200){
                StringBuilder sb = new StringBuilder();

                // read the connection's input stream into a String
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()))){
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }

                JsonElement results = new JsonParser().parseString(sb.toString());

                // prints out weather report for next 7 days in Bloomington
                System.out.println("Bloomington 7-Day Forecast in Fahrenheit: ");
                for (int i = 0; i < 168; i+=24) {
                    String date = results.getAsJsonObject().get("hourly").getAsJsonObject().get("time").getAsJsonArray().get(i).getAsString();
                    System.out.println("Forecast for " + date.substring(0,date.lastIndexOf('T')) + ": ");
                    for (int j = i; j < i+24; j+=3) {
                        String time = results.getAsJsonObject().get("hourly").getAsJsonObject().get("time").getAsJsonArray().get(j).getAsString();
                        String temp = results.getAsJsonObject().get("hourly").getAsJsonObject().get("temperature_2m").getAsJsonArray().get(j).getAsString();
                        System.out.println("\t"+ time.substring(time.lastIndexOf('T')+1) + ": " + temp + "°F");
                    }
                }
            }
            else{
                throw new IOException("Response code is " + responseCode);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
