package sch.xmut.jake.imagestegangraphy.service.common;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;

@Service
public class CommonService {
    public BaseResponse getLocation(String ip) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            //创建默认连接
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建HttpGet对象,处理get请求,转发到A站点
            HttpGet httpGet = new HttpGet("http://api.map.baidu.com/location/ip?ak=g8oPktpkZFFZFOapLjvBmWpW5RNjKaq7&coor=bd09ll&ip=" + ip);
            //执行
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int code = response.getStatusLine().getStatusCode();
            //获取状态
            System.out.println("http请求结果为:"+code);
            if(code == 200){
                String result = EntityUtils.toString(response.getEntity());
                baseResponse.setMessage(result);
            }
            response.close();
            httpClient.close();
        } catch (Exception e) {
            SystemUtils.buildErrorResponse(baseResponse, "定位错误");
        }
        return baseResponse;
    }
}
