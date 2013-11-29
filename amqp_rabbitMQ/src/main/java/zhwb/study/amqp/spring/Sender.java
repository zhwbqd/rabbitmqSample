package zhwb.study.amqp.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Date: 13-11-15
 * Time: 下午2:51
 *
 * @author jack.zhang
 */
public class Sender {
    public static void main(final String... args) throws Exception {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("send_context.xml");
        RabbitTemplate template = (RabbitTemplate) ctx.getBean("loginSuccessTemplate");
        LoginMessage message = new LoginMessage();
        message.setUserId(1);
        message.setUserName("jack");
        template.convertAndSend(message);
        ctx.destroy();
    }
}
