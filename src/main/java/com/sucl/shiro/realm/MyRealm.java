package com.sucl.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 *  可控的三个对象
 *  1、Principal 最终封装成 PrincipalCollection，一般是用户实体
 *  2、AuthenticationToken，默认是UsernamePasswordToken，在authc中通过createToken生成
 *  3、AuthenticationInfo/AuthorizationInfo
 *
 *  AuthenticationToken->AuthenticationInfo
 *  Principal->AuthorizationInfo
 */
public class MyRealm extends AuthorizingRealm {
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("___________________授权");
        principals.getPrimaryPrincipal();//身份转换成SimplePrincipalCollection ，next，即下面的 new Object()
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //添加roles prems
        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("___________________认证");
        //Form登录传入的token，下面的申明 默认是UsernamePasswordToken
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(new Object(),"123456",this.getClass().getName());
        // 认证阶段仅有shiro校验密码，是否认证通过主要由开发者和框架两个实现
        //1、开发者通过token核验，并获取需要的数据，定制AuthenticationInfo
        //2、框架校验定制AuthenticationInfo对应的credentials，即token的password与AuthenticationInfo的credentials
        return authenticationInfo;
    }

    @Override
    public Class getAuthenticationTokenClass() {
        return super.getAuthenticationTokenClass();
    }
}
