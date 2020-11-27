package blog;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Subscriber {
    public static final String TASK_QUEUE_NAME = "BlogQueue";
    public static final String EXCHANGE_NAME = "BlogExchanger";

    public static void main(String[] args) throws Exception {
        if (args.length == 2) {
            String command = args[0];
            String title = "";

            if (command.equals("/title")) title = args[1];

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            try {
                channel.queueDeclarePassive(TASK_QUEUE_NAME);
            } catch (IOException ex) {
                channel = connection.createChannel();
                channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            }

            System.out.println("Waiting for your article: " + title + " to be published");

            channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, title);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received publication '" + message + "'");
            };

            channel.basicConsume(TASK_QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        }
    }
}