package sch.xmut.jake.imagestegangraphy.constants;

/**
 * Created by jake.lin on 2019/12/30
 */
public class UserConstant {
    public static final String ENCRYPTION_TYPE = "md5";//加密类型
    public static final Integer ENCRYPTION_TIMES = 2;//加密次数
    public static final Integer USER_STATUS_PASS = 1;//通过
    public static final Integer USER_STATUS_NOPASS = 0;//不通过(软删除)
    public static final String DEFAULT_PORTRAIT = "https://image-steganography.oss-cn-hangzhou.aliyuncs.com/portrait/IMG_7506.JPG";//用户默认头像
}
