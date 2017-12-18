package test;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * 测试java日志框架
 * <p>
 * Created by cliffyan on 2017/12/18.
 */
public class LogTest {
    public static void main(String[] args) throws Exception {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("LogTest");
        logger.setLevel(Level.ALL);
        logger.addHandler(new FileHandler());
        logger.addHandler(new Handler() {
            @Override
            public void publish(LogRecord record) {
                System.out.println("haha----"+record.getMessage());
            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        });

        logger.info("info msg");
        logger.warning("warn msg");

    }
}
