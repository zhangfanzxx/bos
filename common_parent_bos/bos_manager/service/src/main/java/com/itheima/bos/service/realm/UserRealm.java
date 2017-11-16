package com.itheima.bos.service.realm;

import com.itheima.bos.dao.system.UserRepository;
import com.itheima.system.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:${CLASS_NAME} <br/>
 * Function: <br/>
 * Date:2017/11/14 19:23 <br/>
 * Author zzff
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermission("courier:delete");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(authenticationToken instanceof UsernamePasswordToken){
            UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
            String username = usernamePasswordToken.getUsername();
            User user = userRepository.findByUsername(username);
            if(user==null){
                return null;
            }
            AuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(),getName());
            return  info;
        }
        return null;
    }
}
