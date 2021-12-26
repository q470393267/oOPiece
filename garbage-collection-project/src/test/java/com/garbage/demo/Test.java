package com.garbage.demo;

import cn.hutool.crypto.digest.DigestUtil;

public class Test {

    public static void main(String[] args) {
        System.out.println(DigestUtil.md5Hex("123456"));
    }
}
