package com.itzzx.basics.cryptography.diget;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要算法，防止篡改
 * <p>
 * 常见的加密算法 MD5 SHA1 SHA256  SHA512
 *
 * @author Martin
 * @since 1.0
 */
public class DigestDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "123456";
        String algorithm = "MD5";
        String md5 = getDigest(input, algorithm);
        System.out.println(md5);

        String sha1 = getDigest(input, "SHA-1");
        System.out.println(sha1);

        String sha256 = getDigest(input, "SHA-256");
        System.out.println(sha256);

        String sha512 = getDigest(input, "SHA-512");
        System.out.println(sha512);
    }

    /**
     * 加密
     *
     * @param input     原文
     * @param algorithm 算法
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String getDigest(String input, String algorithm) throws NoSuchAlgorithmException {
        //创建消息摘要对象
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        //加密
        byte[] bytes = digest.digest(input.getBytes());
        return toHex(bytes);
    }

    /**
     * 转换16进制
     *
     * @param bytes
     * @return
     */
    private static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        //对密文进行迭代
        for (byte aByte : bytes) {
            //转换成16进制
            String s = Integer.toHexString(aByte & 0xff);
            //如果s是一位的话，我们在高位补0
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }

}
