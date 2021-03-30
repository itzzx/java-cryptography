package com.itzzx.basics.cryptography.bytebit;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * TODO
 *
 * @author Martin
 * @since 1.0
 */
public class ByteBitDemo {

    public static void main(String[] args) {
        String str = "啊的请问2312312313";
        String msg = Base64.encode(str.getBytes());
        System.out.println(msg);

        char[] chars = str.toCharArray();

        for (char aChar : chars) {
            int c = aChar;
            System.out.println(Integer.toBinaryString(c));
        }
    }
}
