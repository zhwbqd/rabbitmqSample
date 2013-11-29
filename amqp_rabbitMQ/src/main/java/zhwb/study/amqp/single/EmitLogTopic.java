package zhwb.study.amqp.single;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.codehaus.jackson.map.ObjectMapper;
import zhwb.study.amqp.spring.LoginMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "passportExchange";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] argv) {
        if (argv.length < 3) {
            System.err.println("Usage: EmitLogTopic route_key user_id user_name");
            System.exit(1);
        }

        Connection connection = null;
        Channel channel;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setVirtualHost("/users");

            connection = factory.newConnection();  //default executor
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);

            String routingKey = getRouting(argv);
            byte[] message = getMessage(argv);

            Map<String, Object> headers = new HashMap<String, Object>();
            headers.put("__TypeId__", "zhwb.study.amqp.spring.LoginMessage");
            AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().contentType("application/json").headers(headers).build();
            channel.basicPublish(EXCHANGE_NAME, routingKey, props, message);
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ignore) {
                }
            }
        }
    }

    private static String getRouting(String[] strings) {
        if (strings.length < 3)
            return "anonymous.info";
        return strings[0];
    }

    private static byte[] getMessage(String[] strings) throws IOException {
        LoginMessage loginMessage = new LoginMessage();
        loginMessage.setUserId(Integer.valueOf(strings[1]));
        loginMessage.setUserName(strings[2]);
        return OBJECT_MAPPER.writeValueAsBytes(loginMessage);
    }

}

