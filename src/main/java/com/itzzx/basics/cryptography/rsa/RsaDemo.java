package com.itzzx.basics.cryptography.rsa;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * TODO
 *
 * @author Martin
 * @since 1.0
 */
public class RsaDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, Base64DecodingException, IOException, NoSuchProviderException, InvalidKeySpecException {
        String input = "{\"productKey\":\"e3C2523Fab\",\"deviceName\":\"ZTCS06201200010\",\"notify\":\"deviceInfo\",\"netType\": \"gprs\",\"iccid\":\"89860468192080153994\",\"hwVer\":\"S.B1.K3\",\"gprsAppVer\":\"GD1.39\",\"gprsExtVer\":\"WD1.15\",\"battery\":\"1\",\"ver\":0}";
        String algorithm = "RSA";
        //生成rsa密钥对，保存到本地
        generatorKeyPair(algorithm, "a.pub", "a.pri");

        //读取本地私钥
        PrivateKey privateKey = getPrivateKey("a.pri", algorithm);

        //读取本地公钥
        PublicKey publicKey = getPublicKey("a.pub", algorithm);

        //加密
        String s = rsaEncrypt(input, algorithm, privateKey);
        System.out.println("加密: " + s);

        //解密
        String s1 = rsaDecrypt("edyVXPQmbNHLZTzuoZPvaCcwO0OjCGYDiV5gkRQohblzJB7S2nxU3h8q6YqnyIhPLwIQLCKg/ufO\\nbe5jS7jugy0xkBBQU4YLdtZg5kjJtQnre3saNO9bju1UfEQk4v5pzWLAbf9oHG3HLlRMoipw1i89\\nujKvdavdG6iP1enAOuAncF/EBkkbqa56oKhyO8ozcQz2ooOBBh+wWTJ9zV58/8ADAMn67AEyilMU\\nqNJPCT9CpjefV9/PB971Lg9AAQHccAPVgsLs1SMezbuLiyzH1imjwgVQ7N3FJssc3gKp3snrhmgO\\nCld8w5r9VYAYCBLN0pnimHbi4CbmcsyfReYfyA==", algorithm, publicKey);
        System.out.println("解密: " + s1);
    }


    /**
     * 读取公钥钥
     *
     * @param pubPath
     * @param algorithm
     * @return
     */
    public static PublicKey getPublicKey(String pubPath, String algorithm) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, Base64DecodingException {
        String publicKeyString = FileUtils.readFileToString(new File(pubPath), Charset.defaultCharset());
        // 创建key的工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建公钥规则
        X509EncodedKeySpec pkcs8EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        return keyFactory.generatePublic(pkcs8EncodedKeySpec);
    }


    /**
     * 读取私钥
     *
     * @param priPath
     * @param algorithm
     * @return
     */
    public static PrivateKey getPrivateKey(String priPath, String algorithm) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, Base64DecodingException {
        String privateKeyString = FileUtils.readFileToString(new File(priPath), Charset.defaultCharset());
        // 创建key的工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建私钥规则
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 解密
     *
     * @param encrypt   加密密文
     * @param algorithm 加密方式
     * @param publicKey 公钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws Base64DecodingException
     */
    private static String rsaDecrypt(String encrypt, String algorithm, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Base64DecodingException {
        Cipher cipher = Cipher.getInstance(algorithm);

        // 使用私钥进行加密 参数一: 加密的模式; 参数二: 你想使用公钥加密还是私钥加密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        byte[] bytes = cipher.doFinal(Base64.decode(encrypt));

        return new String(bytes);
    }

    /**
     * 加密
     *
     * @param input      原文
     * @param algorithm  加密方式
     * @param privateKey 私钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String rsaEncrypt(String input, String algorithm, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);

        // 使用私钥进行加密 参数一: 加密的模式; 参数二: 你想使用公钥加密还是私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        byte[] bytes = cipher.doFinal(input.getBytes());

        return Base64.encode(bytes);
    }

    /**
     * 生成公钥和私钥
     *
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static void generatorKeyPair(String algorithm, String pubPath, String priPath) throws NoSuchAlgorithmException, IOException {
        // 创建密钥对 (KeyPairGenerator密钥对生成器对象)
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥和私钥
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥的字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 使用 base64 进行编码
        String privateKeyEncodeString = Base64.encode(privateKeyEncoded);
        String publicKeyEncodeString = Base64.encode(publicKeyEncoded);
        //将公钥和私钥保存到根目录
        FileUtils.writeStringToFile(new File(pubPath), publicKeyEncodeString, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), privateKeyEncodeString, Charset.forName("UTF-8"));
    }
}
