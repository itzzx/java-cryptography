package com.itzzx.basics.cryptography.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 3 des 加密
 */
public class ThreDesDemo {


    private static final String Algorithm = "DESede"; // 定义 加密算法,可用
    // DES,DESede,Blowfish

    /**
     * 加密方法
     *
     * @param keybyte
     *            加密密钥，长度为24字节
     * @param src
     *            被加密的数据缓冲区（源）
     * @return
     * @author SHANHY
     * @date 2015-8-18
     */
    public static String encryptMode(byte[] keybyte, byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return Base64.getEncoder().encodeToString(c1.doFinal(src));
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param keybyte
     *            加密密钥，长度为24字节
     * @param src
     *            加密后的缓冲区
     * @return
     * @author SHANHY
     * @date 2015-8-18
     */
    public static String decryptMode(byte[] keybyte, byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return new String(c1.doFinal(src),"utf-8");
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 添加新安全算法,如果用JCE就要把它添加进去
        //Security.addProvider(new com.sun.crypto.provider.SunJCE());

        // 24字节的密钥（我们可以取apk签名的指纹的前12个byte和后12个byte拼接在一起为我们的密钥）
        String key = "11112123456#############";
        String szSrc = "this is three des encrypt content";

        System.out.println("加密前的字符串:" + szSrc);
        String encoded = encryptMode(key.getBytes(), szSrc.getBytes("GB2312"));
        System.out.println("加密后的字符串:" + encoded);

        String srcBytes = decryptMode(key.getBytes(), Base64.getDecoder().decode(encoded));
        System.out.println("解密后的字符串:" + srcBytes);
    }
}
