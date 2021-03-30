package com.itzzx.basics.cryptography.aes;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * TODO
 *
 * @author Martin
 * @since 1.0
 */
public class AESDemo {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, Base64DecodingException {

        //原文
        String input = "请问请问啊";
        //定义Key,aes 的key大小长度必须是16位
        String key = "1234567812345678";
        //算法
        String transformation = "AES";
        //加密类型
        String algorithm = "AES";
        String encrypt = encryptAES(input, key, transformation, algorithm);
        System.out.println("加密: " + encrypt);

        String inputRes = decryptAES(encrypt, key, transformation, algorithm);
        System.out.println("解密: " + inputRes);
    }

    /**
     * @param encryptAES     密文
     * @param key
     * @param transformation
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String decryptAES(String encryptAES, String key, String transformation, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Base64DecodingException {
        // 创建解密对象
        Cipher cipher = Cipher.getInstance(transformation);

        //创建解密规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);

        //初始化解密
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // 因为之前加密通过了 base64 进行转码，所以在解密的时候，需要解码后在进行加密 Base64.decode(encryptAES)
        byte[] bytes = cipher.doFinal(Base64.decode(encryptAES));

        return new String(bytes);
    }

    /**
     * 加密
     *
     * @param input          原文
     * @param key            密钥
     * @param transformation 加密算法
     * @param algorithm      加密类型
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String encryptAES(String input, String key, String transformation, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //常见加密对象
        Cipher cipher = Cipher.getInstance(transformation);

        //创建加密规则 第一个参数表示key ; 第二参数表示加密的类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);

        //进行加密初始化 第一个参数表示模式，加密模式/解密模式
        //     第二个参数表示 加密规则

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        //调用加密方法 参数表示原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());

        //打印密文
        //如果直接打印密文会出现乱码 。是因为在ascii编码表上面找不到对应的字符
        //System.out.println(new String(bytes));

        String encode = Base64.encode(bytes);
        return encode;
    }
}
