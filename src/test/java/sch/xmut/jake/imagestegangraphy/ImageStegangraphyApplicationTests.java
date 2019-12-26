package sch.xmut.jake.imagestegangraphy;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;

import java.io.UnsupportedEncodingException;
import java.security.Key;

class ImageStegangraphyApplicationTests {
    public static void main(String[] args) throws UnsupportedEncodingException {
        AesCipherService aesCipherService = new AesCipherService();
        byte[] bytes = aesCipherService.generateNewKey().getEncoded();
        String byteStr = "+jJ6SpHecovxxDD07ZrcHA==";
        byte[] test = Base64.decodeBase64(byteStr);
        String code = "d41d8cd98f00b204e980";
        String encrptText = aesCipherService.encrypt(code.getBytes(), test).toHex();
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), test).getBytes());
    }
}
