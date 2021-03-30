package com.itzzx.basics.cryptography.base64;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * TODO
 *
 * @author Martin
 * @since 1.0
 */
public class Base64Demo {

        public static void main(String[] args) {
            String man = "硅谷我22 ";
            String a = "A";
            String bc = "BC";

            System.out.println("Man base64结果为：" + Base64.encode(man.getBytes()));
            System.out.println("BC base64结果为：" + Base64.encode(bc.getBytes()));
            System.out.println("A base64结果为：" + Base64.encode(a.getBytes()));
    }
}
