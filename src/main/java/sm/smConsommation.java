package sm;

import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;

import java.util.concurrent.TimeUnit;

public class smConsommation implements Runnable{

    private void start() throws InterruptedException {

        // 1. create the client
        final Mqtt5Client client = Mqtt5Client.builder()
                .identifier("sm-consommation-publisher")
                .serverHost("0.0.0.0")
                .automaticReconnectWithDefaultConfig() // the client automatically reconnects
                .build();


        // 2. connect the client
        client.toBlocking().connectWith()
                .willPublish()
                .topic("home")
                .payload("Connection lost".getBytes())
                .applyWillPublish()
                .send();


        // 3. simulate the flow
        while (true) {
            client.toBlocking().publishWith()
                    .topic("consommation")
                    .payload("flow1".getBytes())
                    .send();
            System.out.println("Sent consumption");


            TimeUnit.MINUTES.sleep(60);
        }
    }


    @Override
    public void run() {
        try {
            this.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
