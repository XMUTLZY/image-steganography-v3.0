package sch.xmut.jake.imagestegangraphy.service.api;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import sch.xmut.jake.cache.apicache.http.request.CacheRequest;
import sch.xmut.jake.cache.apicache.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.constants.CacheConstant;
import sch.xmut.jake.imagestegangraphy.constants.CodeConstant;
import sch.xmut.jake.imagestegangraphy.constants.OssConstant;
import sch.xmut.jake.imagestegangraphy.domain.api.ApiAlipayEntity;
import sch.xmut.jake.imagestegangraphy.domain.api.ApiOssEntity;
import sch.xmut.jake.imagestegangraphy.domain.api.ApiSmsEntity;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.api.CodeResponse;
import sch.xmut.jake.imagestegangraphy.http.response.api.OssImageResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.api.ApiAlipay;
import sch.xmut.jake.imagestegangraphy.http.vo.api.ApiOss;
import sch.xmut.jake.imagestegangraphy.http.vo.api.ApiSms;
import sch.xmut.jake.imagestegangraphy.repository.api.ApiAlipayRepository;
import sch.xmut.jake.imagestegangraphy.repository.api.ApiOssRepository;
import sch.xmut.jake.imagestegangraphy.repository.api.ApiSmsRepository;
import sch.xmut.jake.imagestegangraphy.service.cache.CacheService;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by jake.lin on 2019/12/26
 */
@Service
public class ApiService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApiSmsRepository apiSmsRepository;
    @Autowired
    private ApiOssRepository apiOssRepository;
    @Autowired
    private ApiAlipayRepository apiAlipayRepository;
    @Autowired
    private CacheService cacheService;

    public ApiSms getApiSmsInfo() {
        List<ApiSmsEntity> apiSmsEntityList = apiSmsRepository.findAll();
        if (CollectionUtils.isEmpty(apiSmsEntityList)) {
            return null;
        }
        ApiSmsEntity apiSmsEntity = apiSmsEntityList.get(0);
        ApiSms apiSms = new ApiSms();
        buildApiSms(apiSmsEntity, apiSms);
        return apiSms;
    }

    private void buildApiSms(ApiSmsEntity apiSmsEntity, ApiSms apiSms) {
        String aesKey = apiSmsEntity.getEncrypt();
        byte[] aesKeyByte = Base64.decodeBase64(aesKey);
        apiSms.setSmsContentType(apiSmsEntity.getSmsContentType());
        apiSms.setSmsText(apiSmsEntity.getSmsText());
        apiSms.setSmsUid(SystemUtils.aesDecode(aesKeyByte, apiSmsEntity.getSmsUid()));
        apiSms.setSmsKey(SystemUtils.aesDecode(aesKeyByte, apiSmsEntity.getSmsKey()));
    }

    public CodeResponse sendCode(UserRequest userRequest) {
        CodeResponse codeResponse = new CodeResponse();
        int code = (int)(100000 + Math.random()*899999);
        ApiSms apiSms = getApiSmsInfo();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn/");
        post.addRequestHeader("Content-Type", apiSms.getSmsContentType());
        int lifeTime = new Double(CodeConstant.CODE_LIFE_TIME/60).intValue();
        NameValuePair[] data = {new NameValuePair("Uid", apiSms.getSmsUid()),
                new NameValuePair("Key", apiSms.getSmsKey()),
                new NameValuePair("smsMob", userRequest.getMobile()),
                new NameValuePair("smsText",apiSms.getSmsText() + code + "(有效期为" + lifeTime + "分钟)")};
        post.setRequestBody(data);
        String res = null;
        try {
            client.executeMethod(post);
            res = new String(post.getResponseBodyAsString().getBytes("utf-8"));
        } catch (IOException e) {
            logger.error("发送验证码失败");
        }
        post.releaseConnection();
        codeResponse.setCodeStatus(res);
        if ("1".equals(res)) {
            BaseResponse baseResponse = setCodeCache(code); //将验证码缓存在redis中，并设置生存周期
            if (baseResponse.getStatusCode() == BaseResponse.FAILD_CODE) {
                codeResponse.setMessage("验证码缓存设置失败");
                return codeResponse;
            }
        }
        buildCodeResponse(codeResponse, res);
        return codeResponse;
    }

    private void buildCodeResponse(CodeResponse codeResponse, String res) {
        if ("1".equals(res)) {
            codeResponse.setMessage("发送验证码成功");
        } else if ("-4".equals(res)) {
            codeResponse.setMessage("手机格式不正确");
        } else if ("-41".equals(res)) {
            codeResponse.setMessage("请输入手机号");
        }
    }

    private BaseResponse setCodeCache(int code) {
        BaseResponse response = new BaseResponse();
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.CODE_KEY);
        cacheRequest.setValue(String.valueOf(code));
        BaseResponse baseResponse1 = cacheService.stringAdd(cacheRequest); //设置验证码缓存 key，value
        if (baseResponse1.getStatusCode() == BaseResponse.FAILD_CODE) {
            return baseResponse1;
        }
        cacheRequest.setLifeTime(CodeConstant.CODE_LIFE_TIME);
        BaseResponse baseResponse2 = cacheService.keySetTime(cacheRequest); //设置验证码生存时间
        if (baseResponse2.getStatusCode() == BaseResponse.FAILD_CODE) {
            return baseResponse2;
        }
        return response;
    }

    //文件上传
    public OssImageResponse imageUploadOss(MultipartFile file) {
        OssImageResponse ossImageResponse = new OssImageResponse();
        ApiOss apiOss = getApiOssInfo();
        OSSClient ossClient = new OSSClient(apiOss.getEndPoint(), apiOss.getAccessKey(), apiOss.getAccessSecret());
        String resultImageUrl = null;
        String fileName = file.getOriginalFilename();
        String imageName = UUID.randomUUID().toString() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        Long fileSize = file.getSize();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileSize);
        metadata.setCacheControl("no-cache");
        metadata.setHeader("Pragma", "no-cache");
        metadata.setContentEncoding("utf-8");
        metadata.setContentType(getContentType(imageName));
        metadata.setContentDisposition("filename/filesize=" + imageName + "/" + fileSize + "Byte.");
        //上传文件
        try {
            ossClient.putObject(OssConstant.BUCKET_NAME, OssConstant.IMAGE_FOLDER + imageName, file.getInputStream(), metadata);
            resultImageUrl = "http://" + OssConstant.BUCKET_NAME + "." + apiOss.getEndPoint() + "/" + OssConstant.IMAGE_FOLDER + imageName;
        } catch (IOException e) {
            logger.error("上传图片异常");
        }
        ossImageResponse.setIamgeUrl(resultImageUrl);
        return ossImageResponse;
    }

    private String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        return null;
    }

    public ApiOss getApiOssInfo() {
        List<ApiOssEntity> apiOssEntityList = apiOssRepository.findAll();
        if (CollectionUtils.isEmpty(apiOssEntityList)) {
            return null;
        }
        ApiOssEntity apiOssEntity = apiOssEntityList.get(0);
        String aesKey = apiOssEntity.getEncrypt();
        byte[] aesByte = Base64.decodeBase64(aesKey);
        ApiOss apiOss = new ApiOss();
        BeanUtils.copyProperties(apiOssEntity, apiOss);
        apiOss.setAccessSecret(SystemUtils.aesDecode(aesByte, apiOssEntity.getAccessSecret()));
        return apiOss;
    }

    public ApiAlipay getApiAlipayInfo() {
        List<ApiAlipayEntity> apiAlipayEntityList = apiAlipayRepository.findAll();
        if (CollectionUtils.isEmpty(apiAlipayEntityList)) {
            return null;
        }
        ApiAlipayEntity apiAlipayEntity = apiAlipayEntityList.get(0);
        String aesKey = apiAlipayEntity.getEncrypt();
        byte[] aesByte = Base64.decodeBase64(aesKey);
        ApiAlipay apiAlipay = new ApiAlipay();
        BeanUtils.copyProperties(apiAlipayEntity, apiAlipay);
        apiAlipay.setMechartPrivateKey(SystemUtils.aesDecode(aesByte, apiAlipayEntity.getMechartPrivateKey()));
        apiAlipay.setAlipayPublicKey(SystemUtils.aesDecode(aesByte, apiAlipayEntity.getAlipayPublicKey()));
        return apiAlipay;
    }
}
