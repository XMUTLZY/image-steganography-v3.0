package sch.xmut.jake.imagestegangraphy.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
     * 获取当前年月日
     */
    public static String dateToRedis(Date date) {
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dFormat.format(date);
    }

    /**
     * 获取前一天日期
     */
    public static String getLastDate(Date date) {
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
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

    /**
     * 获取本地图片字节数
     */
    public static byte[] getByteByLocalImage(String imageUrl) throws IOException {
        int len = -1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        File file = new File(imageUrl);
        byte[] bytes = new byte[(int) file.length()];
        InputStream inputStream = new FileInputStream(file);
        while ((len = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 网络图片转byte
     */
    public static byte[] getByteByNetImage(String path) throws IOException {
        byte[] data = null;
        URL url = null;
        InputStream input = null;
        try{
            url = new URL(path);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            input = httpUrl.getInputStream();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int numBytesRead = 0;
        while ((numBytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, numBytesRead);
        }
        data = output.toByteArray();
        output.close();
        input.close();
        return data;
    }
}
