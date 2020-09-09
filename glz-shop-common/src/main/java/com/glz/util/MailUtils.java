package com.glz.util;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

import java.util.Random;

public class MailUtils {

    private static String HOST = "smtp.163.com";
    private static boolean AUTH = true;
    private static String FROM = "15225407491@163.com";
    private static Integer PORT = 25;
    private static String PASS = "XWNRJMELACZEFSTR";

    public static MailAccount init(){
        MailAccount account = new MailAccount();
        account.setHost(HOST);
        account.setAuth(AUTH);
        account.setFrom(FROM);
        account.setPort(PORT);
        account.setPass(PASS);
        return account;
    }

    public static void sendMail(){
        MailAccount account = init();
        MailUtil.send(account,"15225407491@163.com","glz商城","恭喜你,注册成功",false);
    }


    public static void sendForgotPassword(String email){
        // String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        MailAccount account = init();
        MailUtil.send(account,email,"glz商城","<a href='localhost/user/password'>修改密码</a>",true);
    }
}
