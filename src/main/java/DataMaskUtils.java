/**
 * 数据脱敏
 * Created by cliffyan on 2017/11/1.
 */
public class DataMaskUtils {
    //email
    private static final String EMAIL_REGX = "([a-z0-9_\\.\\-\\+]+)@([\\da-z\\.\\-]+)\\.([a-z\\.]{2,6})";
    private static final String EMAIL_REPLACE = "xxx@domain.host";

    //身份证号
    private static final String IDCARD_REGX = "(\\d{18})|(\\d{15})";
    private static final String IDCARD_REPLACE = "xxxxxxx";

    //移动电话
    private static final String MOBILE_PHONE_REGX = "((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}";
    private static final String MOBILE_PHONE_REPLACE = "xxxxxxxxxxx";

    //固定电话
    private static final String TELEPHONE_REGX = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
    private static final String TELEPHONE_REPLACE = "xxx-xxxxxxx";

    //IP地址
    private static final String NUM = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    private static final String IP_REGX = NUM + "\\." + NUM + "\\." + NUM + "\\." + NUM;
    private static final String IP_REPLACE = "xxx.xxx.xxx.xxx";

    //url地址
    private static final String URL_REGX = "http://([w-]+.)+[w-]+(/[w./?%&=]*)?";
    private static final String URL_REPLACE = "http://xxxxxxx";

    //数字
    private static final String NUM_REGX = "^[0-9]*$";
    private static final String NUM_REPLACE = "xxx-xxx";


    /**
     * 字符串的替换
     *
     * @param sourceStr
     * @param regx
     * @param replaceStr
     */
    private static String getReplace(String sourceStr, String regx, String replaceStr) {
        String strResult = sourceStr.replaceAll(regx, replaceStr);
        return strResult;
    }

    /**
     * 字符串的分割
     */
    private static String[] getDivision(String str, String regx) {
        String[] dataStr = str.split(regx);
        return dataStr;
    }

    public static String dataMask(String sourceStr) {
        String strResult = sourceStr
                .replaceAll(EMAIL_REGX, EMAIL_REPLACE)
                .replaceAll(IDCARD_REGX, IDCARD_REPLACE)
                .replaceAll(MOBILE_PHONE_REGX, MOBILE_PHONE_REPLACE)
                .replaceAll(TELEPHONE_REGX, TELEPHONE_REPLACE)
                .replaceAll(URL_REGX, URL_REPLACE)
                .replaceAll(IP_REGX, IP_REPLACE);

        return strResult;
    }


    public static void main(String[] args) {
        String str = "我的电话号码是18941134883和18567433410," +
                "我的邮箱是cliffyan@tencent.com和cliffyan@qq.com," +
                "我的身份证号是14222519911226453X,14222519981226453X," +
                "IP是192.168.12.33和123.45.67.87" +
                "url地址是:http://www.cliffyan.com和https://www.cliffyan.com 以及 www.cliffyan.com  12 345";
        System.out.print(dataMask(str));

    }


}
