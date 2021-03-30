package com.itzzx.basics.cryptography.signature;

import com.itzzx.basics.cryptography.rsa.RsaDemo;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * TODO
 *
 * @author Martin
 * @since 1.0
 */
public class SignatureDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, Base64DecodingException, IOException, SignatureException, InvalidKeyException {
        String algorithm = "sha256withrsa";
        String input = "123456";
        PublicKey publicKey = RsaDemo.getPublicKey("a.pub", "RSA");
        PrivateKey privateKey = RsaDemo.getPrivateKey("a.pri", "RSA");

        //进行数字签名
        String signatureData = getSignature(input, algorithm, privateKey);
        System.out.println(signatureData);

        //校验签名
        boolean flag = verifySignature(input, algorithm, publicKey, signatureData);
        System.out.println("签名是否正确: " + flag);
    }

    /**
     * 校验签名
     *
     * @param input
     * @param algorithm
     * @param publicKey
     * @param signatureData
     * @return
     */
    private static boolean verifySignature(String input, String algorithm, PublicKey publicKey, String signatureData) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, Base64DecodingException {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名 initVerify 传入公钥
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验签名
        return signature.verify(Base64.decode(signatureData));
    }

    /**
     * 生成数字签名
     *
     * @param input      原文
     * @param algorithm  加密方式
     * @param privateKey 密钥
     * @return
     */
    private static String getSignature(String input, String algorithm, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 获取签名
        byte[] sign = signature.sign();
        // 使用base64进行转码
        return Base64.encode(sign);
    }
}
