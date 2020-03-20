package sch.xmut.jake.imagestegangraphy.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jake.lin on 2019/12/24
 */
public class SystemUtils {
    /**
     * 时间标准化
     */
    public static String dateToFormat(Date date) {
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dFormat.format(date);
    }

    /**
     * 获取ip地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 编辑错误response
     */
    public static void buildErrorResponse(BaseResponse baseResponse, String message) {
        baseResponse.setStatusCode(BaseResponse.FAILD_CODE);
        baseResponse.setStatus(BaseResponse.FAILD_STATUS);
        baseResponse.setMessage(message);
    }

    /**
     * AES加密算法
     */
    public static String aesEncode(byte[] bytes, String info) {
        AesCipherService aesCipherService = new AesCipherService();
        return aesCipherService.encrypt(info.getBytes(), bytes).toHex();
    }

    /**
     * AES解密算法
     */
    public static String aesDecode(byte[] bytes, String info) {
        AesCipherService aesCipherService = new AesCipherService();
        return new String(aesCipherService.decrypt(Hex.decode(info), bytes).getBytes());
    }
}
