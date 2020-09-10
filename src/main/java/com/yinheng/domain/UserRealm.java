package com.yinheng.domain;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author yinheng
 * @create 2020/9/2 22:14
 * @description:自定义realm类
 */
public class UserRealm extends AuthorizingRealm {
    /**
     *  @author: yinheng
     *  @description:执行授权逻辑
     *  @date:  2020/9/2 22:23
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加资源授权字符串
        simpleAuthorizationInfo.addStringPermission("user:add");
        return simpleAuthorizationInfo;
    }
    /**
     *  @author: yinheng
     *  @description: 执行认证逻辑
     *  @date:  2020/9/2 22:23
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设数据库的用户名和密码
        String name="殷恒";
        String password="123456";
        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        if (!token.getUsername().equals(name)){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException，这也是为什么能判断用户登录是账号错误还是密码错误的原理所在。

        }
        //2.判断密码
        return new SimpleAuthenticationInfo("",password,"");

    }
}
