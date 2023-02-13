import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FetchDataAPI {
    public static void main(String[] args) {
        try {
            String website = "https://www.example.com";
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(website);
            HttpResponse response = client.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}