/**
 * Created by cliffyan on 2017/11/8.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * 读取实时的配置文件信息
 *
 * @author frank
 * @date 2015-07-02
 */
public class SysProperties {


    private static Properties SysLocalPropObject = null;

    //配置文件路径
    private static String defaultPropFileName = "/Users/cliffyan/dev/data/config/apigw.properties";
    //文件更新标识
    protected long lastModifiedData = -1;

    private static SysProperties instance;

    public static SysProperties getInstance() {
        if (instance == null) {
            instance = new SysProperties();
        }
        return instance;
    }


    /**
     * @description 私有构造器启动一个线程实时监控配置文件
     */
    private SysProperties() {
        SysLocalPropObject = new Properties();
//        String tempPath = SysProperties.class.getResource(defaultPropFileName).getFile();
        String tempPath = defaultPropFileName;
        File tempFile = new File(tempPath);
        final String filePath;
        if (tempFile.exists()) {
            filePath = tempPath;
        } else {
            filePath = "system.properties";
        }

        final SysProperties self = this;
        File propertyFile = new File(filePath);
        if (propertyFile.exists()) reloadFile(propertyFile);

        //循环监控配置文件的变化，一旦发现文件发生变化了则重读配置文件
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    //间隔1秒
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }

                    try {
                        File propertyFile = new File(filePath);
                        if (self.lastModifiedData != propertyFile.lastModified()) {
                            self.reloadFile(propertyFile);
                            self.lastModifiedData = propertyFile.lastModified();
                        }
                    } catch (Exception e) {

                    }
                }
            }
        };
        t.start();
    }

    /**
     * 重新加载文件
     *
     * @param propertyFile
     * @author frank 2015-07-02
     */
    private void reloadFile(File propertyFile) {
        FileInputStream inputStreamLocal = null;
        try {
            inputStreamLocal = new FileInputStream(propertyFile);
            SysLocalPropObject.load(inputStreamLocal);
            if (instance != null) {
                int dbFlag = instance.getIntProperty("db.flag");
                int dcFlag = instance.getIntProperty("dc.flag");
                String api = instance.getProperty("api.config");
                System.out.println("db.flag:" + dbFlag);
                System.out.println("dc.flag:" + dcFlag);
                System.out.println("api.config:" + api);
            } else {
                System.out.println("instance == null");
            }


        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.print("No Local Properties File Found");
                SysLocalPropObject = null;
            } else {
                e.printStackTrace();
            }
        } finally {
            try {
                if (inputStreamLocal != null)
                    inputStreamLocal.close();
            } catch (IOException e) {
                System.out.print("Exception is happened when to close file stream");
            }
        }
    }


    /**
     * 根据key获取value
     *
     * @param property
     * @return String
     * @author frank 2015-07-02
     */
    public String getProperty(String property) {
        String val = null;

        if (SysLocalPropObject != null)
            val = SysLocalPropObject.getProperty(property);

        return (val);

    }

    /**
     * 根据key获取value
     *
     * @param property
     * @param defaultValue 指定默认值
     * @return String
     * @author frank 2015-07-02
     */
    public String getProperty(String property, String defaultValue) {
        String val = null;

        if (SysLocalPropObject != null) {
            val = SysLocalPropObject.getProperty(property, defaultValue);
        } else {
            val = defaultValue;
        }

        return (val);
    }

    /**
     * 根据key获取value
     *
     * @param property
     * @return Integer
     * @author frank 2015-07-02
     */
    public Integer getIntProperty(String property) {
        String val = getProperty(property);
        Integer nVal = null;
        if (val != null) {
            try {
                nVal = Integer.parseInt(val);
            } catch (Exception e) {

            }
        }
        return nVal;

    }

    /**
     * 根据key获取value
     *
     * @param property
     * @param defaultValue 指定默认值
     * @return Integer
     * @author frank 2015-07-02
     */
    public Integer getIntProperty(String property, Integer defaultValue) {
        Integer val = getIntProperty(property);

        if (val == null) {
            val = defaultValue;
        }

        return (val);
    }

    /**
     * 根据key获取value
     *
     * @param property
     * @return
     * @author frank 2015-07-02
     */
    public Long getLongProperty(String property) {
        String val = getProperty(property);
        Long nVal = null;
        try {
            nVal = Long.parseLong(val);
        } catch (Exception e) {

        }
        return nVal;

    }

    /**
     * 根据key获取value
     *
     * @param property
     * @param defaultValue
     * @return
     * @author frank 2015-07-02
     */
    public Long getLongProperty(String property, Long defaultValue) {
        Long val = getLongProperty(property);

        if (val == null) {
            val = defaultValue;
        }

        return (val);
    }

    /**
     * 根据key获取value
     *
     * @param property
     * @param defaultValue
     * @return
     * @author frank 2015-07-02
     */
    public boolean getBooleanProperty(String property, boolean defaultValue) {
        boolean retval = false;
        String val = getProperty(property);

        if (val == null || val.equals(""))
            retval = defaultValue;
        else if (val.trim().equalsIgnoreCase("true") || val.trim().equals("1"))
            retval = true;

        return (retval);
    }

    /**
     * 根据key获取value
     *
     * @param property
     * @return
     * @author frank 2015-07-02
     */
    public Double getDoubleProperty(String property) {
        String val = getProperty(property);
        Double nVal = null;
        try {
            nVal = Double.parseDouble(val);
        } catch (Exception e) {
        }
        return nVal;
    }


    /**
     * 根据key获取value
     *
     * @param property
     * @param defaultValue
     * @return
     * @author frank 2015-07-02
     */
    public Double getDoubleProperty(String property, Double defaultValue) {
        Double val = getDoubleProperty(property);
        if (val == null) {
            val = defaultValue;
        }
        return (val);
    }
}

