package zhwb.study.amqp.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Date: 13-11-26
 * Time: 上午11:27
 *
 * @author jack.zhang
 */
public class Listerner {
    public static void main(String[] args) {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("receive_context.xml");
    }
}
