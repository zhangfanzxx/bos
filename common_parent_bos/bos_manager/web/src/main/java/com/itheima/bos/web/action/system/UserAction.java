package com.itheima.bos.web.action.system;

import com.itheima.base.domain.Area;
import com.itheima.bos.web.action.BaseAction;
import com.itheima.system.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;


/**
 * ClassName:UserAction <br/>
 * Function: <br/>
 * Date:2017/11/14 17:49 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class UserAction extends BaseAction<User> {
    private final String nameAction = "userAction_";
    private String checkCode;

    /**
     * 分页条件查询
     * 条件需要自己去封装
     */
    @Action(value = nameAction + "pageQuery")
    @Override
    public void pageQuery() { // 分页查询所有的快递员
       /* Specification<Area> specification=specification();
        Pageable pageable =new PageRequest(page-1,rows);
        Page<Area> page =service.pageQuery(specification,pageable);
        page2json(page,new String[]{""});*/
    }


    /***
     * 查询所有能用的数据
     */
    @Action(nameAction + "findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {
      /*  List<> list =service.findAllIsCanUse();
        list2json(list,new String[]{""});*/
    }

    /***
     * 查询所有数据
     */
    @Action(nameAction + "findAll")
    @Override
    public void findAll() {
       /* List<> list = service.findAll();
        list2json(list,new String[]{""});*/
    }

    /**
     * 保存对象
     */
   /* @Action(value=nameAction+"save",
            results = {@Result(name="success",location ="pages/base/area.html",type="redirect" )})
*/
    @Override
    public String save() {
        // service.save(getModel());
        return SUCCESS;
    }

    @Action(value = nameAction + "login",
            results = {@Result(name = "success", location = "index.html", type = "redirect"),
                    @Result(name = "login", location = "index.html", type = "redirect"),
                    @Result(name = "nouser", location = "login.html"),
                    @Result(name = "nopassword", location = "login.html")})
    public String login() {
        String validateCode = (String) getSession().getAttribute("validateCode");
        if (!StringUtils.isEmpty(validateCode) && validateCode.equals(checkCode)) {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(getModel().getUsername(), getModel().getPassword());
            try {
                subject.login(token);
                return SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return LOGIN;
    }
    @Action(value = nameAction + "logout",
            results = {@Result(name = "success", location = "login.html", type = "redirect")})
    public String logout() {
        SecurityUtils.getSubject().logout();
        getSession().invalidate();
        return SUCCESS;
    }


    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    /***
     * 私有方法
     */

    private Specification<User> specification() {
        return null;
    }
}
