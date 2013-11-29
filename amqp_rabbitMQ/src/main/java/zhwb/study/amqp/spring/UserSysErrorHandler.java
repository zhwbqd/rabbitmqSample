package zhwb.study.amqp.spring;

import org.springframework.util.ErrorHandler;

/**
 * Date: 13-11-26
 * Time: 下午1:52
 *
 * @author jack.zhang
 */
public class UserSysErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
}
