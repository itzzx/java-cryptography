package com.itzzx.basics.cryptography.ascii;

/**
 * TODO
 *
 * @author Martin
 * @since 1.0
 */
public class ASCIIDemo {

    public static void main(String[] args) {
        char a = 'A';
        int b = a;
        //打印b 查看十进制的数字对应的 ascii的编码是多少
        //System.out.println(b);


        //定义字符串 使用string
        String c = "啊";
        //需要拆开字符串
        char[] chars = c.toCharArray();
        for (char Achar :chars){
            int asciiCode = Achar;
            System.out.println(asciiCode);
        }

    }
}
