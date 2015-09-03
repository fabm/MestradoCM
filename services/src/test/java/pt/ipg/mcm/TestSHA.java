package pt.ipg.mcm;

import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestSHA {
    @Test
    public void testHashing() throws Exception {
        String password = "francisco";


        final String method1 = getMethod1(password);
        final String method2 = getMethod2(password);

        Assert.assertEquals(method1, method2);


        System.out.println(getMethod1("bruno"));
    }

    private byte[] getDigest(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        return md.digest();
    }

    private String getMethod1(String password) throws NoSuchAlgorithmException {
        byte[] byteData = getDigest(password);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    private String getMethod2(String password) throws NoSuchAlgorithmException {
        byte[] byteData = getDigest(password);
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
