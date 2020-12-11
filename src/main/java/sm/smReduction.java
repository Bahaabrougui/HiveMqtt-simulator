package sm;

import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class smReduction implements Runnable {

    private void start() {
        // 1. create the client
        final Mqtt5Client client = Mqtt5Client.builder()
                .identifier("sm-reduction-subscriber")
                .serverHost("0.0.0.0")
                .automaticReconnectWithDefaultConfig() // the client automatically reconnects
                .build();

        // 2. connect the client
        client.toBlocking().connectWith()
                .cleanStart(false)
                .sessionExpiryInterval(TimeUnit.HOURS.toSeconds(1)) // buffer messages
                .send();

        // 3. subscribe and consume messages
            client.toAsync().subscribeWith()
                    .topicFilter("reduction")
                    .callback(publish -> {
                        System.out.println("Received message on topic " + publish.getTopic() + ": " +
                                new String(publish.getPayloadAsBytes(), StandardCharsets.UTF_8));
                    })
                    .send();
    }

    @Override
    public void run() {
        this.start();
    }
}
