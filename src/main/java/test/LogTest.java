package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * 测试java日志框架
 * <p>
 * Created by cliffyan on 2017/12/18.
 */
public class LogTest {
    private static final LogManager logManager = LogManager.getLogManager();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("LogTest");

    static {
        try {
            System.out.printf("static block\n");
//            logManager.readConfiguration(new FileInputStream("./logging.properties"));
            //Java读取Properties文件的六种方法 http://blog.csdn.net/Senton/article/details/4083127
            InputStream in = getProperties.class.getResourceAsStream("/logging.properties");//注意配置
            logManager.readConfiguration(in);
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Error in loading configuration", exception);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.printf("static main block\n");

        logger.setLevel(Level.ALL);//设置日志级别
        //1.使用系统自带文件处理Handler
        FileHandler fileHandler = new FileHandler("./log/LogTest.log");
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFilter(new Filter() {
            public boolean isLoggable(LogRecord record) {
                return true;
            }
        });
        fileHandler.setFormatter(new SimpleFormatter());
//        fileHandler.setFormatter(new Formatter() {
//            @Override
//            public String format(LogRecord record) {
//                return "日志文件输出:"
//                        +record.getLoggerName()+" | "
//                        +record.getLevel()+" | "
//                        +record.getSourceClassName()+" | "
//                        +record.getMillis()+" | "
//                        +record.getMessage()+"\n";
//            }
//        });

        logger.addHandler(fileHandler);
        //2.自定义Handler
        logger.addHandler(new Handler() {
            @Override
            public void publish(LogRecord record) {
                System.out.println("自定义日志输出:" + record.getMessage());
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
