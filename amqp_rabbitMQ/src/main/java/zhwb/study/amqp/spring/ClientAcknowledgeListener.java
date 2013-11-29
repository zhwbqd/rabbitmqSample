package zhwb.study.amqp.spring;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * Date: 13-11-26
 * Time: 上午11:23
 *
 * @author jack.zhang
 */
public class ClientAcknowledgeListener extends MessageListenerAdapter {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        LoginMessage obj = (LoginMessage) extractMessage(message);
        System.out.println(obj.getUserId() + " ," + obj.getUserName());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
