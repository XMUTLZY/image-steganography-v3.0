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
        String code = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmnCD2WgmT8C2o5GHTNzMHPEPVRuwgpHdJxvB3ActwNcnxmiiafHmxNmmxA\" +\n" +
                "            \"H8tLlZp2H6KKhdSJK6vzLBRmAQSrf3ny04NJaGeTutpmXA6YMIJCT7I3Ooc55X1pz10ZO+hILEAEBm9teFSZnM+dfri9CqEfd4NCSKWUfeWRGkhYMXWiIQyjCfH+Ib3hJxLfg\" +\n" +
                "            \"+pvMb2QX1hhIw1oiJ7CF0oHwUHGJYVxQeM8Xu039Hrh+lhGwmSnCDv9EyVVWAh0KKXUSM2MrKROJayPEnmXjSa3AZ2GcblkRq49GBby1nBr+9p2iXZC2+CsEOng3pnTEvOk/\" +\n" +
                "            \"z3ArHgsYAfDfUwjk3ZwIDAQAB";
        String text = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmnCD2WgmT8C2o5GHTNzMHPEPVRuwgpHdJxvB3ActwNcnxmiiafHmxNmmxA" +
        "H8tLlZp2H6KKhdSJK6vzLBRmAQSrf3ny04NJaGeTutpmXA6YMIJCT7I3Ooc55X1pz10ZO+hILEAEBm9teFSZnM+dfri9CqEfd4NCSKWUfeWRGkhYMXWiIQyjCfH+Ib3hJxLfg" +
                "+pvMb2QX1hhIw1oiJ7CF0oHwUHGJYVxQeM8Xu039Hrh+lhGwmSnCDv9EyVVWAh0KKXUSM2MrKROJayPEnmXjSa3AZ2GcblkRq49GBby1nBr+9p2iXZC2+CsEOng3pnTEvOk/" +
                "z3ArHgsYAfDfUwjk3ZwIDAQAB";
        if (text.equals(code)) {
            System.out.println("1");
        }
        String encrptText = aesCipherService.encrypt(code.getBytes(), test).toHex();
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), test).getBytes());
    }
}
