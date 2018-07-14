package com.sucl.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.config.WebIniSecurityManagerFactory;

public class AuthTest {

    public static void main(String[] args) {
        /**
         *  通过该工厂，创建SecurtyManager实例
         *  shiro.ini仅定义了 Realm 信息
         */

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken("admin","123"));
        } catch (AuthenticationException e) {
            System.out.println( "login faiule");
            e.printStackTrace();
        }

        System.out.println( "login succes" );

        boolean hasAdminRole = subject.hasRole("admin");
        System.out.println("hasAdminRole :  "+hasAdminRole);

        boolean hasPerm1 = subject.isPermitted("perm1");
        System.out.println("hasPerm1  :  "+hasPerm1);

        boolean hasPerm3 = subject.isPermitted("perm3");
        System.out.println("hasPerm3  :  "+hasPerm3);

    }
}
