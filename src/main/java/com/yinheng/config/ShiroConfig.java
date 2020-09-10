package com.yinheng.config;

        import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
        import com.yinheng.domain.UserRealm;
        import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
        import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

        import java.util.LinkedHashMap;
        import java.util.Map;

/**
 * @Author yinheng
 * @create 2020/9/2 22:11
 * @description:shiro配置类
 */
@Configuration
public class ShiroConfig {
    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager)
    {
        //添加shiro的内置过滤器
        //内置过滤器，可以实现权限相关的拦截器
            //anon:无需认证（登录）可以访问
            //authc:必须认证才可以访问
            //user:如果使用rememberMe功能可以直接访问
            //perms:该资源必须得到资源权限才可以访问
            //role:该资源必须的得到角色权限才可以访问
        Map<String,String>filterMap=new LinkedHashMap<>();
        //filterMap.put("/add","authc");
       // filterMap.put("/update","authc");
        //设置放行路径
        filterMap.put("/testThymeleaf","anon");
        filterMap.put("/login","anon");
        filterMap.put("/add","perms[user：add]");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }

    //创建DefaultWebSecurityManager
    @Bean(value = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //创建Realm
    @Bean(value="userRealm")
    public UserRealm getRealm(){
        return  new UserRealm();
    }

    /**
     *  @description:配置shiroDialect，用于thymeleaf和shiro标签配合使用
     *  @author: yinheng
     *  @date: 2020/9/8 22:06
     *  @param:
     *  @return:
     *  @exception:
     */

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
