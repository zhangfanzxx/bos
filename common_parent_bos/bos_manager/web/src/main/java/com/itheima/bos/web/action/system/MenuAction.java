package com.itheima.bos.web.action.system;

import com.itheima.bos.service.system.MenuService;
import com.itheima.bos.web.action.BaseAction;
import com.itheima.system.domain.Menu;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * ClassName:com.itheima.bos.web.action.system <br/>
 * Function: <br/>
 * Date:2017/11/15 18:13 <br/>
 * Author zzff
 */
@Scope("prototype")
@Controller
@Namespace("/")
@ParentPackage("struts-default")
public class MenuAction extends BaseAction<Menu> {
    @Autowired
    private MenuService service;
    private final String nameAction = "menuAction_";
    private final String[] filters = new String[]{"childrenMenus", "roles", "parentMenu"};

    /**
     * 分页条件查询
     * 条件需要自己去封装
     */
    @Action(value = nameAction + "pageQuery")
    @Override
    public void pageQuery() { // 分页查询所有的快递员
        String temp=getModel().getPage();
        page =Integer.parseInt(temp);
        Specification<Menu> specification = specification();
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Menu> page = service.pageQuery(specification, pageable);
        page2json(page, filters);
    }
    /***
     * 查询所有能用的数据
     */
    @Action(nameAction + "findAllIsCanUse")
    @Override
    public void findAllIsCanUse() {
        List<Menu> list = service.findAllIsCanUse();
        list2json(list, filters);
    }

    /***
     * 查询所有数据
     */
    @Action(nameAction + "findAll")
    @Override
    public void findAll() {
        List<Menu> list = service.findAll();
        list2json(list, filters);
    }

    /**
     * 保存对象
     */
    @Action(value = nameAction + "save",
            results = {@Result(name = "success", location = "pages/system/menu.html", type = "redirect")})
    @Override
    public String save() {
        service.save(getModel());
        return SUCCESS;
    }

    @Action(nameAction + "findMenuParent")
    public void findMenuParent() {
        List<Menu> list = service.findMenuParent();
        list2json(list, filters);
    }

    /***
     * 私有方法
     */
    private Specification<Menu> specification() {
        return null;
    }
}
