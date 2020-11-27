package blog;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Blog {
    public static final String EXCHANGE_NAME = "processingBlogExchanger";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Scanner in = new Scanner(System.in);
        String command;
        String title;

        System.out.print("Print your command: ");

        while (in.hasNextLine()) {
            command = in.nextLine();
            if (command.equals("/exit")) {
                break;
            } else {
                String[] parts = command.split(" ");
                switch (parts[0]) {
                    case "/title":
                        System.out.println("title: " + parts[1]);
                        title = parts[1];
                        Connection connection = factory.newConnection();
                        Channel channel = connection.createChannel();
                        try {
                            try {
                                channel.exchangeDeclarePassive(EXCHANGE_NAME);
                            } catch (IOException ex) {
                                channel = connection.createChannel();
                                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
                            }
                            String message = "info: " + title;
                            Channel finalChannel = channel;
                            finalChannel.basicPublish(EXCHANGE_NAME, title, null, message.getBytes(StandardCharsets.UTF_8));
                            System.out.println(" [x] Sent '" + message + "'");
                        } finally {
                            channel.close();
                            connection.close();
                        }
                        break;
                    default:
                        System.out.println("Неверная команда, список команд /h");
                }
            }
            System.out.print("Print your command: ");
        }
    }
}
