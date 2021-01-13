package windpark.windengine;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import windpark.jms.JmsConsumer;
import windpark.jms.JmsProducer;
import windpark.model.WindengineData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class WindengineSimulation {

    private final JmsProducer producer;
    //private final JmsConsumer consumer;

    public WindengineSimulation(JmsProducer producer, JmsConsumer consumer) {
        this.producer = producer;
        //this.consumer = consumer;
    }

    @PostMapping("/api/windengine")
    public ResponseEntity<Void> post(@RequestBody String id) {
//        if (id.length() == 0) {
//            int temp = (int)(Math.random() * (100) + 1);
//            id += String.valueOf(temp);
//        }
        WindengineData data = new WindengineData();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/windengine/01"))
                        .POST(HttpRequest.BodyPublishers.ofString(data.getDataforJson(String.valueOf(id))))
                        .setHeader("Content-Type", "application/json; utf-8")
                        .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e1) {

        }
        return ResponseEntity.ok().build();
    }

}
