package sch.xmut.jake.imagestegangraphy.constants;

/**
 * Created by jake.lin on 2019/12/26
 */
public class CacheConstant {
    public static final String WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER = "WEB:CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER";
    public static final String KEY_PATTERN = "^[A-Z_]+$";
    public static final String CODE_KEY = "USER_REGISTER_CODE";
    public static final String USER_INFO_KEY = "USER_INFO";
    public static final String ADMIN_INFO_KEY = "ADMIN_INFO";
    public static final String PAYMENT_INDEX_KEY = "PAYMENT_INDEX";     //支付主页
    public static final String ORDER_INFO_KEY = "ORDER_INFO";           //订单信息
    public static final String USER_VISIT_NUMBER = "USER_VISIT_NUMBER"; //用户访问量
    public static final Double USER_VISIT_NUMBER_DAY = 60*60*24*1000.00;//用户访问量 日缓存时间
}
