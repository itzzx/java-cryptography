package com.itzzx.basics.cryptography.kaiser;

/**
 * 凯撒加密
 *
 * @author Martin
 * @since 1.0
 */
public class KaiserDemo {

    public static void main(String[] args) {
        // 定义原文
        String input = "Hello World";

        //把原文右移三位
        int key = 3;
        // 凯撒加密
        String encrypt = encryptKaiser(input);
        System.out.println("密文: " + encrypt);
        // 凯撒解密
        String message = decryptKaiser(encrypt, key);
        System.out.println("解密: " + message);
    }

    /**
     * @param encrypt 原文
     * @param key     密钥
     * @return
     */
    private static String decryptKaiser(String encrypt, int key) {
        char[] chars = encrypt.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char aChar : chars) {
            int b = aChar;
            //偏移数据
            b = b - key;
            char newB = (char) b;
            sb.append(newB);
        }
        return sb.toString();
    }

    private static String encryptKaiser(String input) {
        char[] chars = input.toCharArray();

        StringBuffer sb = new StringBuffer();
        for (char Achar : chars) {
            int b = Achar;
            //将对应的ascii码往右边移动三位
            b = b + 3;
            char newB = (char) b;
            sb.append(newB);
        }
        return sb.toString();
    }
}
